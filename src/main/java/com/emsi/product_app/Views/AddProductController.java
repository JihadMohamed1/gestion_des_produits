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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AddProductController {

    @FXML
    private TextField nomField;

    @FXML
    private TextField prixField;

    @FXML
    private TextField descriptionField;

    @FXML
    private TextField quantiteField;

    @FXML
    private ComboBox<String> categorieComboBox;
    @FXML
    private Button loginButton;
    public void initialize() {
        CategorieServices categorieServices=new CategorieServices();
        List<String> list=new ArrayList<>();
        for (Categorie categorie:categorieServices.findAll())
        {
            list.add(categorie.getNom());
        }
        categorieComboBox.getItems().addAll(list);
    }

    @FXML
    private void handleAddButton() {
        String nom = nomField.getText();
        double prix = Double.parseDouble(prixField.getText());
        String description = descriptionField.getText();
        int quantite = Integer.parseInt(quantiteField.getText());
        String categorie = categorieComboBox.getValue();

        CategorieServices categorieServices=new CategorieServices();
        ProductServices productServices=new ProductServices();
        Product product=new Product(nom,prix,description,quantite, categorieServices.findByName(categorie));
        productServices.save(product);
        handlebackButton();
        // Logique pour ajouter l'élément à la base de données ou à la liste du tableau

        closeForm(); // Fermer la fenêtre du formulaire après l'ajout
    }

    private void closeForm() {
        Stage stage = (Stage) nomField.getScene().getWindow();
        stage.close();
    }

    public void handlebackButton() {
        try {
            // Charger la vue du tableau
            FXMLLoader loader = new FXMLLoader(getClass().getResource("TableProduct.fxml"));
            Parent root = loader.load();

            // Obtenir le contrôleur du tableau
            TableProductController tableController = loader.getController();

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



}

