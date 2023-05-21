package com.emsi.product_app.Views;
import com.emsi.Maven.jdbc.Entites.Categorie;
import com.emsi.Maven.jdbc.Entites.Product;
import com.emsi.Maven.jdbc.Services.ProductServices;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class TableProductController {



    @FXML
    private TableView<Product> tableView;
    @FXML
    private Button loginButton;

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

}
