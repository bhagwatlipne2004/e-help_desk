package com.superx.view.Profile;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.cloud.FirestoreClient;

import com.superx.Controller.ViewController;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
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
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class Storage {

    public void setStoStage(Stage stoStage) {
        this.stoStage = stoStage;
    }

    public void setStoScene(Scene stoScene) {
        this.stoScene = stoScene;
    }

    Stage stoStage;
    Scene stoScene;

    private Label welcomeLabel;
    private GridPane documentGrid;
    private BooleanProperty pdfOnlyToggle = new SimpleBooleanProperty();
    private BooleanProperty cloudSyncToggle = new SimpleBooleanProperty(true);
    private BooleanProperty autoDeleteToggle = new SimpleBooleanProperty();
    private BooleanProperty clearCacheToggle = new SimpleBooleanProperty(true);

    public BorderPane createStoragePage(Runnable showProfileScreen, Runnable showAccountScreen,
            Runnable showSecurityScreen, Runnable shownotification,Runnable showdocscene) {

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

        Region topSpacer = new Region();
        HBox.setHgrow(topSpacer, Priority.ALWAYS);

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
            icon.setCursor(Cursor.HAND);
            if (item[0].equals("👤")) {
                icon.setStyle(
                        "-fx-font-size: 40px; -fx-text-fill: #3b82f6; -fx-effect: dropshadow(gaussian, rgba(59,130,246,0.5), 10, 0, 0, 0);");
            } else {
                icon.setStyle("-fx-font-size: 40px; -fx-text-fill: #4b5563;");
                icon.setOnMouseEntered(e -> icon.setStyle(
                        "-fx-font-size: 40px; -fx-text-fill: #3b82f6; -fx-effect: dropshadow(gaussian, rgba(59,130,246,0.5), 10, 0, 0, 0);"));
                icon.setOnMouseExited(e -> icon.setStyle("-fx-font-size: 40px; -fx-text-fill: #4b5563;"));
                icon.setOnMouseClicked(event->{
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

            if (item[1].equals("Storage")) {
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
            } else if (item[1].equals("Notifications")) {
                navButton.setOnMouseClicked(event -> shownotification.run());
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

        Label contentTitle = new Label("Storage Settings");
        contentTitle.setFont(Font.font("Inter", FontWeight.BOLD, 32));
        contentTitle.setStyle("-fx-text-fill: #1e3a8a; -fx-padding: 10px 0;");

        VBox storageSettingsCard = new VBox(15);
        storageSettingsCard.setPadding(new Insets(20));
        storageSettingsCard.setStyle("-fx-background-color: rgba(255, 255, 255, 0.8); -fx-background-radius: 10px;");

        Label uploadTitleLabel = new Label("Upload Settings");
        uploadTitleLabel.setFont(Font.font("Inter", FontWeight.BOLD, 20));
        uploadTitleLabel.setStyle("-fx-text-fill: #1e3a8a;");

        HBox itemBox1 = new HBox();
        itemBox1.setAlignment(Pos.CENTER_LEFT);
        itemBox1.setPadding(new Insets(10, 0, 10, 0));
        VBox textBox1 = new VBox(4);
        Label titleLabel1 = new Label("Allow PDF Only");
        titleLabel1.setFont(Font.font("Inter", FontWeight.SEMI_BOLD, 16));
        titleLabel1.setStyle("-fx-text-fill: #374151;");
        Label descLabel1 = new Label("Restrict all file uploads to PDF format for better security.");
        descLabel1.setFont(Font.font("Inter", FontWeight.NORMAL, 13));
        descLabel1.setStyle("-fx-text-fill: #6b7280;");
        descLabel1.setWrapText(true);
        textBox1.getChildren().addAll(titleLabel1, descLabel1);
        Region spacer1 = new Region();
        HBox.setHgrow(spacer1, Priority.ALWAYS);

        HBox toggle1 = createToggle(pdfOnlyToggle);
        pdfOnlyToggle.set(false);

        itemBox1.getChildren().addAll(textBox1, spacer1, toggle1);

        HBox itemBox2 = new HBox();
        itemBox2.setAlignment(Pos.CENTER_LEFT);
        itemBox2.setPadding(new Insets(10, 0, 10, 0));
        VBox textBox2 = new VBox(4);
        Label titleLabel2 = new Label("Enable Cloud Sync");
        titleLabel2.setFont(Font.font("Inter", FontWeight.SEMI_BOLD, 16));
        titleLabel2.setStyle("-fx-text-fill: #374151;");
        Label descLabel2 = new Label("Automatically back up your documents to a secure cloud.");
        descLabel2.setFont(Font.font("Inter", FontWeight.NORMAL, 13));
        descLabel2.setStyle("-fx-text-fill: #6b7280;");
        descLabel2.setWrapText(true);
        textBox2.getChildren().addAll(titleLabel2, descLabel2);
        Region spacer2 = new Region();
        HBox.setHgrow(spacer2, Priority.ALWAYS);

        HBox toggle2 = createToggle(cloudSyncToggle);
        cloudSyncToggle.set(true);

        itemBox2.getChildren().addAll(textBox2, spacer2, toggle2);

        storageSettingsCard.getChildren().addAll(uploadTitleLabel, itemBox1, itemBox2);

        VBox retentionSettingsCard = new VBox(15);
        retentionSettingsCard.setPadding(new Insets(20));
        retentionSettingsCard.setStyle("-fx-background-color: rgba(255, 255, 255, 0.8); -fx-background-radius: 10px;");

        Label retentionTitleLabel = new Label("Data Retention");
        retentionTitleLabel.setFont(Font.font("Inter", FontWeight.BOLD, 20));
        retentionTitleLabel.setStyle("-fx-text-fill: #1e3a8a;");

        HBox itemBox3 = new HBox();
        itemBox3.setAlignment(Pos.CENTER_LEFT);
        itemBox3.setPadding(new Insets(10, 0, 10, 0));
        VBox textBox3 = new VBox(4);
        Label titleLabel3 = new Label("Auto-Delete After 1 Year");
        titleLabel3.setFont(Font.font("Inter", FontWeight.SEMI_BOLD, 16));
        titleLabel3.setStyle("-fx-text-fill: #374151;");
        Label descLabel3 = new Label("Automatically delete documents that are older than one year.");
        descLabel3.setFont(Font.font("Inter", FontWeight.NORMAL, 13));
        descLabel3.setStyle("-fx-text-fill: #6b7280;");
        descLabel3.setWrapText(true);
        textBox3.getChildren().addAll(titleLabel3, descLabel3);
        Region spacer3 = new Region();
        HBox.setHgrow(spacer3, Priority.ALWAYS);

        HBox toggle3 = createToggle(autoDeleteToggle);
        autoDeleteToggle.set(false);

        itemBox3.getChildren().addAll(textBox3, spacer3, toggle3);

        HBox itemBox4 = new HBox();
        itemBox4.setAlignment(Pos.CENTER_LEFT);
        itemBox4.setPadding(new Insets(10, 0, 10, 0));
        VBox textBox4 = new VBox(4);
        Label titleLabel4 = new Label("Clear Cache on Exit");
        titleLabel4.setFont(Font.font("Inter", FontWeight.SEMI_BOLD, 16));
        titleLabel4.setStyle("-fx-text-fill: #374151;");
        Label descLabel4 = new Label("Automatically clear temporary files when you close the application.");
        descLabel4.setFont(Font.font("Inter", FontWeight.NORMAL, 13));
        descLabel4.setStyle("-fx-text-fill: #6b7280;");
        descLabel4.setWrapText(true);
        textBox4.getChildren().addAll(titleLabel4, descLabel4);
        Region spacer4 = new Region();
        HBox.setHgrow(spacer4, Priority.ALWAYS);

        HBox toggle4 = createToggle(clearCacheToggle);
        clearCacheToggle.set(true);

        itemBox4.getChildren().addAll(textBox4, spacer4, toggle4);

        retentionSettingsCard.getChildren().addAll(retentionTitleLabel, itemBox3, itemBox4);

        VBox documentsCard = new VBox(15);
        documentsCard.setPadding(new Insets(20));
        documentsCard.setStyle("-fx-background-color: rgba(255, 255, 255, 0.8); -fx-background-radius: 10px;");

        Label documentsTitle = new Label("Your Documents");
        documentsTitle.setFont(Font.font("Inter", FontWeight.BOLD, 20));
        documentsTitle.setStyle("-fx-text-fill: #1e3a8a;");

        documentGrid = new GridPane();
        documentGrid.setHgap(20);
        documentGrid.setVgap(15);
        documentGrid.setPadding(new Insets(10, 0, 0, 0));

        documentsCard.getChildren().addAll(documentsTitle, documentGrid);

        mainContent.getChildren().addAll(contentTitle, storageSettingsCard, retentionSettingsCard, documentsCard);

        ScrollPane scrollPane = new ScrollPane(mainContent);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background-color: transparent; -fx-background-insets: 0;");
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        mainbox.setLeft(leftSidebarContainer);
        mainbox.setCenter(scrollPane);
        mainbox.setTop(welboxBar);

        loadUsernameAndRecentDocuments();

        return mainbox;
    }

    private HBox createToggle(BooleanProperty selectedProperty) {
        HBox toggle = new HBox();
        Label toggleLabel = new Label();
        toggleLabel.setFont(Font.font("Inter", FontWeight.BOLD, 10));
        Button toggleButton = new Button();
        toggle.getChildren().addAll(toggleLabel, toggleButton);
        toggle.setCursor(Cursor.HAND);
        toggleButton.setCursor(Cursor.HAND);
        toggle.setMinSize(50, 25);
        toggle.setMaxSize(50, 25);
        toggle.setPadding(new Insets(2));
        toggleButton.setPrefSize(21, 21);
        toggleButton.setMinSize(21, 21);
        toggleButton.setMaxSize(21, 21);
        toggleButton.setStyle("-fx-background-radius: 25; -fx-background-color: white;");

        selectedProperty.addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                toggleLabel.setText("ON");
                toggle.setStyle("-fx-background-color: #3b82f6; -fx-background-radius: 25;");
                toggleLabel.setStyle("-fx-text-fill: white;");
                toggle.setAlignment(Pos.CENTER_RIGHT);
            } else {
                toggleLabel.setText("OFF");
                toggle.setStyle("-fx-background-color: #d1d5db; -fx-background-radius: 25;");
                toggleLabel.setStyle("-fx-text-fill: #4b5563;");
                toggle.setAlignment(Pos.CENTER_LEFT);
            }
        });

        toggle.setOnMouseClicked(event -> selectedProperty.set(!selectedProperty.get()));
        toggleButton.setOnMouseClicked(event -> selectedProperty.set(!selectedProperty.get()));

        return toggle;
    }

   private void loadUsernameAndRecentDocuments() {
    String uid = ViewController.getCurrentUserId();
    if (uid == null) {
        return;
    }

    // 1. Get a reference to your Firestore database
    Firestore db = FirestoreClient.getFirestore();
    
    // 2. Point to the specific user's document in the "users" collection
    DocumentReference userDocRef = db.collection("users").document(uid);

    // 3. Asynchronously retrieve the document
    ApiFuture<DocumentSnapshot> future = userDocRef.get();

    // 4. Add a listener to process the result when it arrives
    future.addListener(() -> {
        try {
            DocumentSnapshot document = future.get();
            String displayName = "User"; // A default value

            if (document.exists()) {
                // If the document exists, get the firstName field
                String firstName = document.getString("firstName");
                if (firstName != null && !firstName.isEmpty()) {
                    displayName = firstName; // Set displayName to "Shivam"
                }
            } else {
                System.out.println("User document does not exist in Firestore for UID: " + uid);
            }

            // 5. Update the JavaFX UI on the main application thread
            final String finalDisplayName = displayName;
            Platform.runLater(() -> {
                welcomeLabel.setText("Welcome " + finalDisplayName);
            });

        } catch (Exception e) {
            e.printStackTrace();
            // Handle any errors, e.g., by setting a default name
            Platform.runLater(() -> {
                welcomeLabel.setText("Welcome User");
            });
        }
    }, Runnable::run); // The executor to run the listener



        db.collection("users").document(uid).collection("documents")
                .orderBy("uploadedAt", Query.Direction.DESCENDING).limit(5).get()
                .addListener(() -> {
                    try {
                        QuerySnapshot querySnapshot = db.collection("users").document(uid).collection("documents")
                                .orderBy("uploadedAt", Query.Direction.DESCENDING).limit(5).get().get();
                        List<QueryDocumentSnapshot> docs = querySnapshot.getDocuments();
                        Platform.runLater(() -> populateDocumentGrid(docs));
                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                    }
                }, Platform::runLater);
    }

    private void populateDocumentGrid(List<QueryDocumentSnapshot> docs) {
        documentGrid.getChildren().clear();

        String headerStyle = "-fx-font-size: 14px; -fx-font-weight: 600; -fx-text-fill: #4b5563;";
        Label nameHeader = new Label("NAME");
        nameHeader.setStyle(headerStyle);
        Label typeHeader = new Label("UPLOAD TYPE");
        typeHeader.setStyle(headerStyle);
        Label dateHeader = new Label("DATE");
        dateHeader.setStyle(headerStyle);
        Label legalHeader = new Label("LEGAL TYPE");
        legalHeader.setStyle(headerStyle);
        Label actionHeader = new Label("ACTIONS");
        actionHeader.setStyle(headerStyle);

        documentGrid.add(nameHeader, 1, 0);
        documentGrid.add(typeHeader, 2, 0);
        documentGrid.add(dateHeader, 3, 0);
        documentGrid.add(legalHeader, 4, 0);
        documentGrid.add(actionHeader, 5, 0);

        int row = 1;
        for (QueryDocumentSnapshot doc : docs) {

            Label icon = new Label("📄");
            icon.setFont(Font.font(20));
            documentGrid.add(icon, 0, row);

            Label name = new Label(doc.getString("fileName"));
            name.setStyle("-fx-font-weight: 500;");
            documentGrid.add(name, 1, row);

            Label uploadType = new Label("Manual Upload");
            documentGrid.add(uploadType, 2, row);

            com.google.cloud.Timestamp ts = doc.getTimestamp("uploadedAt");
            String date = (ts != null) ? new SimpleDateFormat("dd MMM, yyyy").format(ts.toDate()) : "N/A";
            documentGrid.add(new Label(date), 3, row);

            String fileName = doc.getString("fileName");
            String legalType = determineLegalType(fileName);
            Label legalTypeLabel = new Label(legalType);
            legalTypeLabel.setStyle(
                    "-fx-background-color: #e0e7ff; -fx-text-fill: #3730a3; -fx-padding: 3px 8px; -fx-background-radius: 5px; -fx-font-weight: 500;");
            documentGrid.add(legalTypeLabel, 4, row);

            Button viewButton = new Button("View");
            viewButton.setCursor(Cursor.HAND);
            viewButton.setStyle(
                    "-fx-background-color: #3b82f6; -fx-text-fill: white; -fx-background-radius: 5px; -fx-padding: 5px 10px;");
            viewButton.setOnMouseEntered(e -> viewButton.setStyle(
                    "-fx-background-color: #2563eb; -fx-text-fill: white; -fx-background-radius: 5px; -fx-padding: 5px 10px;"));
            viewButton.setOnMouseExited(e -> viewButton.setStyle(
                    "-fx-background-color: #3b82f6; -fx-text-fill: white; -fx-background-radius: 5px; -fx-padding: 5px 10px;"));

            viewButton.setOnAction(e -> {
                String downloadUrl = doc.getString("downloadUrl");
                if (downloadUrl != null) {

                    System.out.println("Viewing document: " + fileName);
                }
            });
            documentGrid.add(viewButton, 5, row);

            row++;
        }

        if (docs.isEmpty()) {
            Label noDocsLabel = new Label("No documents found. Upload some documents to see them here.");
            noDocsLabel.setStyle("-fx-text-fill: #6b7280; -fx-font-style: italic;");
            documentGrid.add(noDocsLabel, 1, 1);
            GridPane.setColumnSpan(noDocsLabel, 5);
        }
    }

    private String determineLegalType(String fileName) {
        if (fileName == null)
            return "Document";

        String lowerFileName = fileName.toLowerCase();
        if (lowerFileName.contains("aadhar") || lowerFileName.contains("aadhaar")
                || lowerFileName.contains("passport")) {
            return "Identity";
        } else if (lowerFileName.contains("case") || lowerFileName.contains("legal")) {
            return "Case File";
        } else if (lowerFileName.contains("property") || lowerFileName.contains("deed")
                || lowerFileName.contains("land")) {
            return "Property";
        } else if (lowerFileName.contains("certificate") || lowerFileName.contains("cert")) {
            return "Certificate";
        } else {
            return "Document";
        }
    }
}
