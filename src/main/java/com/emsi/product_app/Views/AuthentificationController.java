package com.emsi.product_app.Views;

import com.emsi.Maven.jdbc.Entites.User;
import com.emsi.Maven.jdbc.Services.UserServices;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AuthentificationController {
    private void openTablePage() {
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

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        UserServices userServices=new UserServices();
        User user=userServices.findByLogin(username);
        // Vérifier les informations d'authentification
        if (user.getLogin().equals(username) && user.getPassword().equals(password)) {
            openTablePage();
            System.out.println("Authentification réussie !");
            // Faire quelque chose après l'authentification réussie
        } else {
            System.out.println("Échec de l'authentification !");
            // Afficher un message d'erreur ou prendre une action appropriée
        }
    }

}

