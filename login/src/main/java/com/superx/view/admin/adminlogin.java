package com.superx.view.admin;

import com.superx.Controller.ViewController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class adminlogin {

    public BorderPane getAdminLoginBox(ViewController viewController) {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: linear-gradient(from 0% 0% to 100% 100%, #1e3a8a, #3b82f6);");

        
        VBox leftSide = new VBox(20);
        leftSide.setAlignment(Pos.CENTER);
        leftSide.setPadding(new Insets(40));
        leftSide.setStyle("-fx-background-color: rgba(255, 255, 255, 0.1); -fx-background-radius: 0 20 20 0;");
        leftSide.setPrefWidth(500);

        Label brandTitle = new Label("e-help Desk");
        brandTitle.setFont(Font.font("Inter", FontWeight.BOLD, 48));
        brandTitle.setStyle("-fx-text-fill: white;");

        Label brandSubtitle = new Label("Administrator Access");
        brandSubtitle.setFont(Font.font("Inter", FontWeight.MEDIUM, 18));
        brandSubtitle.setStyle("-fx-text-fill: #e0e7ff;");

        Label adminIcon = new Label("👑");
        adminIcon.setFont(Font.font("System", 80));

        leftSide.getChildren().addAll(adminIcon, brandTitle, brandSubtitle);

        
        VBox rightSide = new VBox(30);
        rightSide.setAlignment(Pos.CENTER);
        rightSide.setPadding(new Insets(40, 60, 40, 60));
        rightSide.setPrefWidth(600);

        
        Label headerTitle = new Label("Admin Login");
        headerTitle.setFont(Font.font("Inter", FontWeight.BOLD, 32));
        headerTitle.setStyle("-fx-text-fill: white;");

        Label headerSubtitle = new Label("Access administrative features");
        headerSubtitle.setFont(Font.font("Inter", FontWeight.NORMAL, 16));
        headerSubtitle.setStyle("-fx-text-fill: #d1d5db;");

        VBox headerBox = new VBox(5, headerTitle, headerSubtitle);
        headerBox.setAlignment(Pos.CENTER);

        
        VBox formBox = new VBox(20);
        formBox.setAlignment(Pos.CENTER);

        
        VBox emailBox = new VBox(8);
        Label emailLabel = new Label("Admin Email");
        emailLabel.setFont(Font.font("Inter", FontWeight.MEDIUM, 14));
        emailLabel.setStyle("-fx-text-fill: #e5e7eb;");

        TextField emailField = new TextField();
        emailField.setPromptText("admin@ehelpdesk.com");
        emailField.setPrefHeight(45);
        emailField.setStyle(
            "-fx-background-color: rgba(255, 255, 255, 0.9); -fx-background-radius: 8px; " +
            "-fx-border-color: transparent; -fx-font-size: 14px; -fx-padding: 0 15px;"
        );

        emailBox.getChildren().addAll(emailLabel, emailField);

        
        VBox passwordBox = new VBox(8);
        Label passwordLabel = new Label("Password");
        passwordLabel.setFont(Font.font("Inter", FontWeight.MEDIUM, 14));
        passwordLabel.setStyle("-fx-text-fill: #e5e7eb;");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter admin password");
        passwordField.setPrefHeight(45);
        passwordField.setStyle(
            "-fx-background-color: rgba(255, 255, 255, 0.9); -fx-background-radius: 8px; " +
            "-fx-border-color: transparent; -fx-font-size: 14px; -fx-padding: 0 15px;"
        );

        passwordBox.getChildren().addAll(passwordLabel, passwordField);

        formBox.getChildren().addAll(emailBox, passwordBox);

        
        Button loginButton = new Button("Login to Admin Panel");
        loginButton.setPrefWidth(300);
        loginButton.setPrefHeight(45);
        loginButton.setFont(Font.font("Inter", FontWeight.BOLD, 16));
        loginButton.setStyle(
            "-fx-background-color: #f59e0b; -fx-text-fill: white; -fx-background-radius: 8px; " +
            "-fx-border-color: transparent; -fx-cursor: hand;"
        );
        loginButton.setCursor(Cursor.HAND);

        loginButton.setOnMouseEntered(e -> loginButton.setStyle(
            "-fx-background-color: #d97706; -fx-text-fill: white; -fx-background-radius: 8px; " +
            "-fx-border-color: transparent; -fx-cursor: hand;"
        ));
        loginButton.setOnMouseExited(e -> loginButton.setStyle(
            "-fx-background-color: #f59e0b; -fx-text-fill: white; -fx-background-radius: 8px; " +
            "-fx-border-color: transparent; -fx-cursor: hand;"
        ));

        
        loginButton.setOnAction(e -> {
            viewController.getAdminLoginController().handleAdminLogin(emailField, passwordField);
        });

        
        HBox navigationBox = new HBox(20);
        navigationBox.setAlignment(Pos.CENTER);

        Hyperlink userLoginLink = new Hyperlink("← Back to User Login");
        userLoginLink.setFont(Font.font("Inter", FontWeight.MEDIUM, 14));
        userLoginLink.setStyle("-fx-text-fill: #93c5fd;");
        userLoginLink.setOnAction(e -> viewController.showLoginScreen());

        navigationBox.getChildren().add(userLoginLink);

        rightSide.getChildren().addAll(headerBox, formBox, loginButton, navigationBox);

        root.setLeft(leftSide);
        root.setRight(rightSide);

        
        passwordField.setOnAction(e -> loginButton.fire());
        emailField.setOnAction(e -> passwordField.requestFocus());

        return root;
    }
}
