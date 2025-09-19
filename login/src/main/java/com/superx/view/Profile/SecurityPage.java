package com.superx.view.Profile;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import com.superx.Controller.ViewController;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class SecurityPage {
    Stage secStage;
    Scene Secscene;
    private Label welcomeLabel; // Added this field

    public void setSecStage(Stage secStage) {
        this.secStage = secStage;
    }

    public void setSecscene(Scene secscene) {
        Secscene = secscene;
    }

    public BorderPane securityPageBox(Runnable onProfileClick, Runnable onAccountSettingClick, Runnable showsecurity,
            Runnable showdocscene, Runnable showNotification, Runnable showStorage) {

        BorderPane mainbox = new BorderPane();
        mainbox.setStyle(
                "-fx-background-color: linear-gradient(from 0% 0% to 100% 100%, #e6f0ff, #d6e4ff); -fx-font-family: 'Inter', 'Segoe UI', sans-serif;");

        HBox welboxBar = new HBox(50);
        welboxBar.setPadding(new Insets(15, 40, 15, 40));
        welboxBar.setAlignment(Pos.CENTER_LEFT);
        welboxBar.setStyle("-fx-background-color: #f5f9ff; -fx-border-width: 0 0 1 0; -fx-border-color: #e0e7ff;");

        Label logoLabel = new Label("e-help Desk");
        logoLabel.setFont(Font.font("Inter", FontWeight.BOLD, 20));
        logoLabel.setStyle("-fx-text-fill: #1e3a8a;");

        Region topSpacer = new Region();
        HBox.setHgrow(topSpacer, Priority.ALWAYS);

        // Changed from hardcoded to dynamic
        welcomeLabel = new Label("Welcome");
        welcomeLabel.setFont(Font.font("Inter", FontWeight.BOLD, 20));
        welcomeLabel.setStyle("-fx-text-fill: #4b5563;");

        welboxBar.getChildren().addAll(logoLabel, welcomeLabel);

        VBox iconbox = new VBox();
        iconbox.setPadding(new Insets(20, 15, 20, 15));
        iconbox.setSpacing(10);
        iconbox.setStyle("-fx-background-color: #f5f9ff;");
        iconbox.setAlignment(Pos.TOP_CENTER);

        VBox navButtons2 = new VBox(20);
        navButtons2.setAlignment(Pos.CENTER);

        String[][] navItems2 = {
                { "👤", "" }, { " 🔙 ", "" },
        };

        for (String[] item : navItems2) {
            Label icon = new Label(item[0]);
            icon.setStyle("-fx-font-size: 40px;");
            icon.setCursor(Cursor.HAND);

            if (item[0].equals("👤")) {
                icon.setStyle(
                        "-fx-font-size: 40px; -fx-text-fill: #3b82f6; -fx-effect: dropshadow(gaussian, rgba(59,130,246,0.5), 10, 0, 0, 0);");
                icon.setOnMouseClicked(event -> {
                });
            } else {
                icon.setStyle("-fx-font-size: 40px; -fx-text-fill: #4b5563;");
                icon.setOnMouseEntered(e -> icon.setStyle(
                        "-fx-font-size: 40px; -fx-text-fill: #3b82f6; -fx-effect: dropshadow(gaussian, rgba(59,130,246,0.5), 10, 0, 0, 0);"));
                icon.setOnMouseExited(e -> icon.setStyle("-fx-font-size: 40px; -fx-text-fill: #4b5563;"));
                icon.setOnMouseClicked(event -> {
                    showdocscene.run();
                });
            }

            navButtons2.getChildren().add(icon);
        }

        iconbox.getChildren().add(navButtons2);

        VBox probox = new VBox();
        probox.setPadding(new Insets(20, 15, 20, 15));
        probox.setSpacing(10);
        probox.setStyle("-fx-background-color: #f5f9ff; -fx-pref-width: 280px;");
        probox.setAlignment(Pos.TOP_CENTER);

        VBox navButtons = new VBox(8);

        String[][] navItems = {
                { "👤", "Profile" },
                { "⚙️", "Account Settings" },
                { "🛡️", "Security" },
                { "🔔", "Notifications" },
                { "🏬", "Storage" }
        };

        for (String[] item : navItems) {
            Label icon = new Label(item[0]);
            icon.setStyle("-fx-font-size: 40px;");
            Label text = new Label(item[1]);

            HBox navButton = new HBox(15, icon, text);
            navButton.setAlignment(Pos.CENTER_LEFT);
            navButton.setCursor(Cursor.HAND);

            if (item[1].equals("Security")) {
                text.setStyle("-fx-font-size: 15px; -fx-font-weight: 600; -fx-text-fill: #3b82f6;");
                navButton.setStyle(
                        "-fx-padding: 12px 15px; -fx-background-radius: 10px; -fx-background-color: #e0e7ff;");
            } else {
                text.setStyle("-fx-font-size: 15px; -fx-font-weight: 500; -fx-text-fill: #4b5563;");
                navButton.setStyle(
                        "-fx-padding: 12px 15px; -fx-background-radius: 10px; -fx-background-color: transparent;");
                navButton.setOnMouseEntered(e -> navButton.setStyle(
                        "-fx-padding: 12px 15px; -fx-background-radius: 10px; -fx-background-color: #eef2ff;"));
                navButton.setOnMouseExited(e -> navButton.setStyle(
                        "-fx-padding: 12px 15px; -fx-background-radius: 10px; -fx-background-color: transparent;"));

                switch (item[1]) {
                    case "Profile":
                        navButton.setOnMouseClicked(e -> onProfileClick.run());
                        break;
                    case "Account Settings":
                        navButton.setOnMouseClicked(e -> onAccountSettingClick.run());
                        break;
                    case "Notifications":
                        navButton.setOnMouseClicked(e -> showNotification.run());
                        break;
                    case "Storage":
                        navButton.setOnMouseClicked(e -> showStorage.run());
                        break;
                    default:
                        navButton.setOnMouseClicked(e -> System.out.println(item[1] + " clicked"));
                        break;
                }
            }

            navButtons.getChildren().add(navButton);
        }

        Region proboxSpacer = new Region();
        VBox.setVgrow(proboxSpacer, Priority.ALWAYS);
        probox.getChildren().addAll(navButtons, proboxSpacer);

        HBox leftSidebarContainer = new HBox(10, iconbox, probox);

        VBox mainContent = new VBox();
        mainContent.setPadding(new Insets(20, 40, 40, 40));
        mainContent.setSpacing(30);
        mainContent.setStyle("-fx-background-color: transparent;");

        Label contentTitle = new Label("Security Settings");
        contentTitle.setFont(Font.font("Inter", FontWeight.BOLD, 32));
        contentTitle.setStyle("-fx-text-fill: #1e3a8a; -fx-padding: 10px 0;");

        VBox twoFactorCard = new VBox(15);
        twoFactorCard.setStyle(
                "-fx-background-color: rgba(255, 255, 255, 0.7); -fx-background-radius: 12px; -fx-padding: 25px;");

        Label twoFactorTitle = new Label("Two-Factor Authentication");
        twoFactorTitle.setFont(Font.font("Inter", FontWeight.SEMI_BOLD, 20));
        twoFactorTitle.setStyle("-fx-text-fill: #1e3a8a;");

        Label twoFactorDesc = new Label(
                "Add an extra layer of security to your account by requiring a second authentication step.");
        twoFactorDesc.setWrapText(true);
        twoFactorDesc.setStyle("-fx-font-size: 14px; -fx-text-fill: #4b5563;");

        Button enable2FAButton = new Button("Enable Two-Factor Authentication");
        enable2FAButton.setFont(Font.font("Inter", FontWeight.BOLD, 15));
        enable2FAButton.setStyle(
                "-fx-background-color: #3b82f6; -fx-text-fill: white; -fx-background-radius: 10px; -fx-padding: 10px 25px;");
        enable2FAButton.setCursor(Cursor.HAND);

        HBox twoFactorButtonBox = new HBox(enable2FAButton);
        twoFactorButtonBox.setAlignment(Pos.CENTER_LEFT);

        twoFactorCard.getChildren().addAll(twoFactorTitle, twoFactorDesc, twoFactorButtonBox);

        VBox historyCard = new VBox(20);
        historyCard.setStyle(
                "-fx-background-color: rgba(255, 255, 255, 0.7); -fx-background-radius: 12px; -fx-padding: 25px;");

        Label historyTitle = new Label("Recent Login Activity");
        historyTitle.setFont(Font.font("Inter", FontWeight.SEMI_BOLD, 20));
        historyTitle.setStyle("-fx-text-fill: #1e3a8a;");

        GridPane historyGrid = new GridPane();
        historyGrid.setHgap(40);
        historyGrid.setVgap(15);
        historyGrid.setPadding(new Insets(10, 0, 0, 0));

        String headerStyle = "-fx-font-size: 14px; -fx-font-weight: 600; -fx-text-fill: #4b5563;";

        Label statusHeader = new Label("STATUS");
        statusHeader.setStyle(headerStyle);
        Label deviceHeader = new Label("DEVICE");
        deviceHeader.setStyle(headerStyle);
        Label locationHeader = new Label("LOCATION");
        locationHeader.setStyle(headerStyle);
        Label dateHeader = new Label("DATE");
        dateHeader.setStyle(headerStyle);
        Label ipHeader = new Label("IP ADDRESS");
        ipHeader.setStyle(headerStyle);

        historyGrid.add(statusHeader, 0, 0);
        historyGrid.add(deviceHeader, 1, 0);
        historyGrid.add(locationHeader, 2, 0);
        historyGrid.add(dateHeader, 3, 0);
        historyGrid.add(ipHeader, 4, 0);

        Object[][] historyData = {
                { true, "Chrome on Windows", "Pune, IN", "July 19, 2025 at 4:30 PM", "103.120.115.45" },
                { true, "Mobile App (iOS)", "Mumbai, IN", "July 18, 2025 at 11:00 AM", "103.120.110.12" },
                { false, "Firefox on Linux", "Unknown", "July 17, 2025 at 9:15 PM", "45.115.220.89" }
        };

        for (int i = 0; i < historyData.length; i++) {
            boolean success = (boolean) historyData[i][0];

            Circle statusIndicator = new Circle(5, success ? Color.web("#22c55e") : Color.web("#ef4444"));
            Label statusLabel = new Label(success ? "Success" : "Failed");
            statusLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: 600; -fx-text-fill: "
                    + (success ? "#22c55e" : "#ef4444") + ";");

            HBox statusBox = new HBox(5, statusIndicator, statusLabel);
            statusBox.setAlignment(Pos.CENTER_LEFT);

            historyGrid.add(statusBox, 0, i + 1);
            historyGrid.add(new Label((String) historyData[i][1]), 1, i + 1);
            historyGrid.add(new Label((String) historyData[i][2]), 2, i + 1);
            historyGrid.add(new Label((String) historyData[i][3]), 3, i + 1);
            historyGrid.add(new Label((String) historyData[i][4]), 4, i + 1);
        }

        historyCard.getChildren().addAll(historyTitle, historyGrid);

        VBox signOutCard = new VBox(15);
        signOutCard.setStyle(
                "-fx-background-color: rgba(255, 255, 255, 0.7); -fx-background-radius: 12px; -fx-padding: 25px;");

        Label signOutTitle = new Label("Sign out of all other sessions");
        signOutTitle.setFont(Font.font("Inter", FontWeight.SEMI_BOLD, 20));
        signOutTitle.setStyle("-fx-text-fill: #1e3a8a;");

        Label signOutDesc = new Label(
                "For your security, you can sign out of all other active sessions on other devices.");
        signOutDesc.setWrapText(true);
        signOutDesc.setStyle("-fx-font-size: 14px; -fx-text-fill: #4b5563;");

        Button signOutButton = new Button("Sign Out Everywhere");
        signOutButton.setFont(Font.font("Inter", FontWeight.BOLD, 15));
        signOutButton.setStyle(
                "-fx-background-color: #dc2626; -fx-text-fill: white; -fx-background-radius: 10px; -fx-padding: 10px 25px;");
        signOutButton.setCursor(Cursor.HAND);

        HBox signOutButtonBox = new HBox(signOutButton);
        signOutButtonBox.setAlignment(Pos.CENTER_LEFT);

        signOutCard.getChildren().addAll(signOutTitle, signOutDesc, signOutButtonBox);

        mainContent.getChildren().addAll(contentTitle, twoFactorCard, historyCard, signOutCard);

        ScrollPane scrollPane = new ScrollPane(mainContent);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background-color: transparent; -fx-background-insets: 0;");
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        mainbox.setLeft(leftSidebarContainer);
        mainbox.setCenter(scrollPane);
        mainbox.setTop(welboxBar);

        // Load user name after creating the UI
        loadUserName();

        return mainbox;
    }

    // Added this method to load user name from Firebase
    private void loadUserName() {
        String uid = ViewController.getCurrentUserId();
        if (uid == null) {
            return;
        }

        Firestore db = FirestoreClient.getFirestore();
        DocumentReference docRef = db.collection("users").document(uid);
        ApiFuture<DocumentSnapshot> future = docRef.get();

        future.addListener(() -> {
            try {
                DocumentSnapshot document = future.get();
                String displayName = "User";
                
                if (document.exists()) {
                    String firstName = document.getString("firstName");
                    if (firstName != null && !firstName.isEmpty()) {
                        displayName = firstName;
                    }
                }
                
                final String finalDisplayName = displayName;
                Platform.runLater(() -> {
                    welcomeLabel.setText("Welcome " + finalDisplayName);
                });
                
            } catch (Exception e) {
                e.printStackTrace();
                Platform.runLater(() -> {
                    welcomeLabel.setText("Welcome User");
                });
            }
        }, Platform::runLater);
    }
}
