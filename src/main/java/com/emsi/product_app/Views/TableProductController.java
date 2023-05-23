package com.emsi.product_app.Views;
import com.emsi.Maven.jdbc.Entites.Categorie;
import com.emsi.Maven.jdbc.Entites.Product;
import com.emsi.Maven.jdbc.Services.CategorieServices;
import com.emsi.Maven.jdbc.Services.ProductServices;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class TableProductController {


    @FXML
    private Button importButtontxt;

    @FXML
    private Button exportButtontxt;
    @FXML
    private TableView<Product> tableView;
    @FXML
    private Button loginButton;
    @FXML
    private Button loginButton1;

    public void initialize() {
        ProductServices productServices=new ProductServices();
        // Configurer les colonnes du tableau
        TableColumn<Product, String> nomColumn = (TableColumn<Product, String>) tableView.getColumns().get(0);
        TableColumn<Product, Double> prixColumn = (TableColumn<Product, Double>) tableView.getColumns().get(1);
        TableColumn<Product, String> descriptionColumn = (TableColumn<Product, String>) tableView.getColumns().get(2);
        TableColumn<Product, Integer> quantiteColumn = (TableColumn<Product, Integer>) tableView.getColumns().get(3);
        TableColumn<Product, Categorie> categorieColumn = (TableColumn<Product, Categorie>) tableView.getColumns().get(4);
        TableColumn<Product, Void> SuppriemeColumn = (TableColumn<Product, Void>) tableView.getColumns().get(5);
        TableColumn<Product, Void> ModifierColumn = (TableColumn<Product, Void>) tableView.getColumns().get(6);
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prixColumn.setCellValueFactory(new PropertyValueFactory<>("prix"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        quantiteColumn.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        categorieColumn.setCellValueFactory(new PropertyValueFactory<>("categorie"));

        // Configurer les boutons d'action
        SuppriemeColumn.setCellFactory(column -> new TableCell<>() {

            private final Button deleteButton = new Button("Supprimer");

            {


                deleteButton.setOnAction(event -> {
                    Product item = getTableView().getItems().get(getIndex());
                    // Logique pour supprimer l'élément
                    productServices.remove(item);
                    System.out.println("Supprimer l'élément : " + item.getNom());
                    tableView.getItems().clear();
                    tableView.getItems().addAll(productServices.findAll());
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(deleteButton);
                }
            }
        });
        ModifierColumn.setCellFactory(column -> new TableCell<>() {

            private final Button editButton = new Button("Modifier");

            {


                editButton.setOnAction(event -> {
                    Product item = getTableView().getItems().get(getIndex());
                    try {
                        afficherFormulaireModification(item);
                    } catch (IOException e) {
                        throw new RuntimeException(" i'm here"+e);
                    }

                    // Logique pour éditer l'élément
                    System.out.println("Modifier l'élément : " + item.getNom());
                    tableView.getItems().clear();
                    tableView.getItems().addAll(productServices.findAll());
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(editButton);

                }
            }
        });

        // Remplir le tableau avec des données de base de données
        // Remplacez cette partie avec votre logique pour récupérer les données de la base de données
        tableView.getItems().addAll(productServices.findAll());

    }
public void handleAddButton()
{
    try {
        // Charger la vue du tableau
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddProduct.fxml"));
        Parent root = loader.load();

        // Obtenir le contrôleur du tableau
        AddProductController tableController = loader.getController();

        // Configurer d'autres informations nécessaires pour le contrôleur du tableau

        // Créer une nouvelle scène avec la vue du tableau
        Scene scene = new Scene(root);

        // Obtenir la scène actuelle à partir du bouton de connexion (ou d'une autre référence appropriée)
        Stage currentStage = (Stage) loginButton.getScene().getWindow();

        // Définir la nouvelle scène sur le stage actuel
        currentStage.setScene(scene);
        currentStage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
}

    @FXML
    private void afficherFormulaireModification(Product product) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("EditProduct.fxml"));
        Parent root = loader.load();
        EditProductController editProductController = loader.getController();

        // Récupère les attributs à modifier
        int id=product.getId();
        String nom = product.getNom();
        double prix = product.getPrix();
        String description = product.getDescription();
        int quantite = product.getQuantite();
        String categorie = product.getCategorie().getNom();

        // Passe les attributs au contrôleur du formulaire de modification
        editProductController.setAttributs(id,nom, prix, description, quantite, categorie);

        // Affiche la fenêtre correspondante pour modifier les attributs
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();

        // Effectue d'autres opérations après la fermeture de la fenêtre de modification
        // ...
    }
    public void handllogoutButton() {
        try {
            // Charger la vue du tableau
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Authentification.fxml"));
            Parent root = loader.load();

            // Obtenir le contrôleur du tableau
            AuthentificationController authentificationController = loader.getController();

            // Configurer d'autres informations nécessaires pour le contrôleur du tableau

            // Créer une nouvelle scène avec la vue du tableau
            Scene scene = new Scene(root);

            // Obtenir la scène actuelle à partir du bouton de connexion (ou d'une autre référence appropriée)
            Stage currentStage = (Stage) loginButton1.getScene().getWindow();

            // Définir la nouvelle scène sur le stage actuel
            currentStage.setScene(scene);
            currentStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void handleImporttxt(ActionEvent event) {
        CategorieServices categorieServices=new CategorieServices();
        ProductServices productServices=new ProductServices();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select a text file to import");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));

        File selectedFile = fileChooser.showOpenDialog(importButtontxt.getScene().getWindow());


        if (selectedFile != null) {
            try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile))) {
                tableView.getItems().clear();
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");

                        String nom = parts[0];
                        double prix = Double.parseDouble(parts[1]);
                        String description = parts[2];
                        int quantite = Integer.parseInt(parts[3]);
                        String categorie = parts[4];
                        // Parse and convert dateNaiss from parts[7]
                        Product product=new Product(nom,prix,description,quantite,categorieServices.findByName(categorie));
                        productServices.save(product);


                }
                tableView.getItems().clear();
                tableView.getItems().addAll(productServices.findAll());


            } catch (IOException e) {
                e.printStackTrace();

            }
        }
    }

    @FXML
    private void handleExporttxt(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select a file to export");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));

        File selectedFile = fileChooser.showSaveDialog(exportButtontxt.getScene().getWindow());
        if (selectedFile != null) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(selectedFile))) {
                List<Product> products = tableView.getItems();
                for (Product product : products) {
                    String line =  product.getNom() + "," + product.getPrix() + "," +
                            product.getDescription() + "," + product.getQuantite() + "," + product.getCategorie().getNom() ;
                    writer.write(line);
                    writer.newLine();
                }

            } catch (IOException e) {
                e.printStackTrace();

            }
        }
    }





}
