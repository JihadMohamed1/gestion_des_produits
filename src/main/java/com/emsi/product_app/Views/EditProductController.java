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

public class EditProductController{
    @FXML
    private TextField nomField;
    @FXML
    private TextField prixField;
    @FXML
    private Button loginButton;
    @FXML
    private TextField descriptionField;
    @FXML
    private TextField quantiteField;
    @FXML
    private ComboBox<String> categorieComboBox;


    private String nom;
    private double prix;
    private String description;
    private int quantite;
    private String categorie;
    private int id;

    public void setAttributs(int id,String nom, double prix, String description, int quantite, String categorie) {
        this.id=id;
        this.nom = nom;
        this.prix = prix;
        this.description = description;
        this.quantite = quantite;
        this.categorie = categorie;

        afficherAttributs(); // Affiche les attributs dans les champs correspondants
    }

    private void afficherAttributs() {
        nomField.setText(nom);
        prixField.setText(String.valueOf(prix));
        descriptionField.setText(description);
        quantiteField.setText(String.valueOf(quantite));

        CategorieServices categorieServices=new CategorieServices();
        List<String> list=new ArrayList<>();
        for (Categorie categorie:categorieServices.findAll())
        {
            list.add(categorie.getNom());
        }
        categorieComboBox.getItems().addAll(list);
    }

    @FXML
    private void handleModifierButton() {
        // Récupère les nouvelles valeurs saisies par l'utilisateur
        String nouveauNom = nomField.getText();
        double nouveauPrix = Double.parseDouble(prixField.getText());
        String nouvelleDescription = descriptionField.getText();
        int nouvelleQuantite = Integer.parseInt(quantiteField.getText());
        String nouvelleCategorie = categorieComboBox.getValue();
        CategorieServices categorieServices=new CategorieServices();
        ProductServices productServices=new ProductServices();
        // Effectue les opérations nécessaires avec les nouvelles valeurs
            Product product=new Product(id,nouveauNom,nouveauPrix,nouvelleDescription,nouvelleQuantite,categorieServices.findByName(nouvelleCategorie));
            productServices.update(product);
        // Ferme la fenêtre actuelle
        Stage stage = (Stage) nomField.getScene().getWindow();
        stage.close();
    }

    public void handlebackButton(ActionEvent actionEvent) {
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
