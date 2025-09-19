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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Notification {
    Stage notiStage;
    Scene notiScene, profScene, accScene, Secscene, stoScene;
    private Label welcomeLabel; // Added this field

    public void setNotiStage(Stage notiStage) {
        this.notiStage = notiStage;
    }

    public void setNotiScene(Scene notiScene) {
        this.notiScene = notiScene;
    }

    public BorderPane notificationPageBox(Runnable showDocScene, Runnable showAccountScreen,
            Runnable showSecurityScreen,
            Runnable showProfileScreen, Runnable showStorage) {

        BorderPane mainbox = new BorderPane();
        mainbox.setStyle(
                "-fx-background-color: linear-gradient(from 0% 0% to 100% 100%, #e6f0ff, #d6e4ff); -fx-font-family: 'Inter', 'Segoe UI', sans-serif;");

        HBox welboxBar = new HBox(50);
        welboxBar.setPadding(new Insets(15, 40, 15, 40));
        welboxBar.setAlignment(Pos.CENTER_LEFT);
        welboxBar.setStyle(
                "-fx-background-color: #f5f9ff; -fx-border-width: 0 0 1 0; -fx-border-color: #e0e7ff;");

        Label logoLabel = new Label("e-help Desk");
        logoLabel.setFont(Font.font("Inter", FontWeight.BOLD, 20));
        logoLabel.setStyle("-fx-text-fill: #1e3a8a;");

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
            } else {
                icon.setStyle("-fx-font-size: 40px; -fx-text-fill: #4b5563;");
                icon.setOnMouseEntered(e -> icon.setStyle(
                        "-fx-font-size: 40px; -fx-text-fill: #3b82f6; -fx-effect: dropshadow(gaussian, rgba(59,130,246,0.5), 10, 0, 0, 0);"));
                icon.setOnMouseExited(e -> icon.setStyle("-fx-font-size: 40px; -fx-text-fill: #4b5563;"));
                icon.setOnMouseClicked(evt -> {
                    showDocScene.run();
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

            if (item[1].equals("Notifications")) {
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
            }

            if (item[1].equals("Profile")) {
                navButton.setOnMouseClicked(event -> showProfileScreen.run());
            } else if (item[1].equals("Account Settings")) {
                navButton.setOnMouseClicked(event -> showAccountScreen.run());
            } else if (item[1].equals("Security")) {
                navButton.setOnMouseClicked(event -> showSecurityScreen.run());
            } else if (item[1].equals("Storage")) {
                navButton.setOnMouseClicked(event -> showStorage.run());
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

        Label contentTitle = new Label("Notification Settings");
        contentTitle.setFont(Font.font("Inter", FontWeight.BOLD, 32));
        contentTitle.setStyle("-fx-text-fill: #1e3a8a; -fx-padding: 10px 0;");

        VBox protectionCard = new VBox(10);
        protectionCard.setPadding(new Insets(20));
        protectionCard.setStyle(
                "-fx-background-color: rgba(230, 255, 230, 0.8); -fx-background-radius: 12px; -fx-border-color: #22c55e; -fx-border-width: 1px; -fx-border-radius: 12px;");
        protectionCard.setAlignment(Pos.CENTER_LEFT);

        Label protectionIcon = new Label("✅");
        protectionIcon.setFont(Font.font("System", 24));

        Label protectionText = new Label("Your account is protected.");
        protectionText.setFont(Font.font("Inter", FontWeight.SEMI_BOLD, 18));
        protectionText.setStyle("-fx-text-fill: #166534;");

        HBox protectionBox = new HBox(15, protectionIcon, protectionText);
        protectionBox.setAlignment(Pos.CENTER_LEFT);
        protectionCard.getChildren().add(protectionBox);

        VBox settingsList = new VBox(25);

        VBox accessControlSection = createSettingsSection("Access Control",
                createSettingRow("Enable/Disable Notifications", "Control all app notifications.", true),
                createSettingRow("Enable/Disable Reminders", "Get reminders for incomplete forms or deadlines.", true));

        VBox otherSection = createSettingsSection("Other Notifications",
                createSettingRow("Case Updates", "Receive alerts for new documents or status changes in your cases.",
                        true),
                createSettingRow("Promotional", "Get updates on new features and offers.", false),
                createSettingRow("Tips and Articles", "Receive helpful tips and legal articles.", false));

        settingsList.getChildren().addAll(accessControlSection, otherSection);

        mainContent.getChildren().addAll(contentTitle, protectionCard, settingsList);

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

    private VBox createSettingsSection(String title, HBox... items) {
        VBox sectionBox = new VBox(15);
        sectionBox.setPadding(new Insets(20));
        sectionBox.setStyle("-fx-background-color: rgba(255, 255, 255, 0.8); -fx-background-radius: 10px;");

        Label titleLabel = new Label(title);
        titleLabel.setFont(Font.font("Inter", FontWeight.BOLD, 20));
        titleLabel.setStyle("-fx-text-fill: #1e3a8a;");

        sectionBox.getChildren().add(titleLabel);
        sectionBox.getChildren().addAll(items);

        return sectionBox;
    }

    private HBox createSettingRow(String title, String description, boolean selected) {
        HBox itemBox = new HBox();
        itemBox.setAlignment(Pos.CENTER_LEFT);
        itemBox.setPadding(new Insets(10, 0, 10, 0));

        VBox textBox = new VBox(4);

        Label titleLabel = new Label(title);
        titleLabel.setFont(Font.font("Inter", FontWeight.SEMI_BOLD, 16));
        titleLabel.setStyle("-fx-text-fill: #374151;");

        Label descLabel = new Label(description);
        descLabel.setFont(Font.font("Inter", FontWeight.NORMAL, 13));
        descLabel.setStyle("-fx-text-fill: #6b7280;");
        descLabel.setWrapText(true);

        textBox.getChildren().addAll(titleLabel, descLabel);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Switch toggle = new Switch();
        toggle.setSelected(selected);

        itemBox.getChildren().addAll(textBox, spacer, toggle);

        return itemBox;
    }

    static class Switch extends HBox {
        private final Label label = new Label();
        private final Button button = new Button();
        private boolean selected;

        public boolean isSelected() {
            return selected;
        }

        public void setSelected(boolean selected) {
            this.selected = selected;
            update();
        }

        private void update() {
            if (selected) {
                label.setText("ON");
                setStyle("-fx-background-color: #3b82f6;");
                label.setStyle("-fx-text-fill: white;");
                button.setStyle("-fx-background-color: white;");
                setAlignment(Pos.CENTER_RIGHT);
            } else {
                label.setText("OFF");
                setStyle("-fx-background-color: #d1d5db;");
                label.setStyle("-fx-text-fill: #4b5563;");
                button.setStyle("-fx-background-color: white;");
                setAlignment(Pos.CENTER_LEFT);
            }
        }

        public Switch() {
            label.setFont(Font.font("Inter", FontWeight.BOLD, 10));
            getChildren().addAll(label, button);

            button.setCursor(Cursor.HAND);
            setCursor(Cursor.HAND);

            setMinSize(50, 25);
            setMaxSize(50, 25);
            setPadding(new Insets(2));

            button.setPrefSize(21, 21);
            button.setMinSize(21, 21);
            button.setMaxSize(21, 21);

            setStyle("-fx-background-radius: 25;");
            button.setStyle("-fx-background-radius: 25; -fx-background-color: white;");

            setOnMouseClicked(event -> {
                selected = !selected;
                update();
            });

            button.setOnMouseClicked(event -> {
                selected = !selected;
                update();
            });

            setSelected(false);
        }
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
