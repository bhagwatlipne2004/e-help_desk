package com.superx.Controller;

import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.json.JSONObject;

public class AdminLoginController extends AuthController {
    private ViewController mainController;

    public AdminLoginController(ViewController mainController) {
        this.mainController = mainController;
    }

    public void handleAdminLogin(TextField emailField, PasswordField passwordField) {
        String email = emailField.getText().trim();
        String password = passwordField.getText();

        if (validateLoginInput(email, password)) {
            String response = signIn(email, password);

            if (response != null && !response.contains("error")) {
                JSONObject jsonResponse = new JSONObject(response);
                String localId = jsonResponse.getString("localId");

                
                if (verifyAdminRole(localId)) {
                    ViewController.setCurrentUserId(localId);
                    ViewController.setCurrentUserRole("admin");
                    showAlert(Alert.AlertType.INFORMATION, "Success", "Admin Login Successful!");
                    mainController.showAdminDashboard();
                } else {
                    showAlert(Alert.AlertType.ERROR, "Access Denied", "You don't have admin privileges.");
                }
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

    public void navigateToUserLogin() {
        mainController.showLoginScreen();
    }
}
