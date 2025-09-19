package com.superx.Controller;

import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.json.JSONObject;

public class LoginController extends AuthController {

    private ViewController mainController;

    public LoginController(ViewController mainController) {
        this.mainController = mainController;
    }

    public void handleLogin(TextField emailField, PasswordField passwordField) {
        String email = emailField.getText().trim();
        String password = passwordField.getText();

        if (validateLoginInput(email, password)) {
            String response = signIn(email, password);
            if (response != null && !response.contains("error")) {

                JSONObject jsonResponse = new JSONObject(response);
                String localId = jsonResponse.getString("localId");

                ViewController.setCurrentUserId(localId);

                showAlert(Alert.AlertType.INFORMATION, "Success", "Login Successful!");
                mainController.showDocScene();
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "Login failed. Please check your credentials.");
            }
        }
    }

    private boolean validateLoginInput(String email, String password) {
        if (email.isEmpty() || password.isEmpty() || !isValidEmail(email)) {
            showAlert(Alert.AlertType.WARNING, "Validation Error", "Please enter a valid email and password.");
            return false;
        }
        return true;
    }

    private boolean isValidEmail(String email) {
        return email.contains("@") && email.contains(".");
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void navigateToSignup() {
        mainController.showSignupScreen();
    }

    // public void navigateToForgotPassword() {
    //     mainController.showForgotScreen();
    // }
}