package com.superx.view.Document;

import com.google.api.core.ApiFuture;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.cloud.StorageClient;
import com.superx.Controller.ViewController;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class documents {

    Stage docStage, profStage;
    Scene docScene, profScene, landRecordsScene, rtiScene;

    private GridPane documentGrid;

    public void setDocStage(Stage docStage) {
        this.docStage = docStage;
    }

    public void setDocScene(Scene docScene) {
        this.docScene = docScene;
    }

    public BorderPane docScenBox(Runnable showProfileScreen, Runnable showLoginScreen, Runnable showlegalmana,
            Runnable showlandrecord, Runnable showRTI, Runnable shownotification2, Runnable showarchive,Runnable showAboutUsScreen) {

        BorderPane mainbox = new BorderPane();
        mainbox.setStyle(
                "-fx-background-color: linear-gradient(from 0% 0% to 100% 100%, #e6f0ff, #d6e4ff); -fx-font-family: 'Inter', 'Segoe UI', sans-serif;");

        VBox sidebar = new VBox(20);
        sidebar.setPadding(new Insets(20, 15, 20, 15));
        sidebar.setStyle("-fx-background-color: #f5f9ff; -fx-pref-width: 280px;");
        sidebar.setAlignment(Pos.TOP_CENTER);

        Label sidebarTitle = new Label("e-help Desk");
        sidebarTitle.setFont(Font.font("Inter", FontWeight.BOLD, 24));
        sidebarTitle.setStyle("-fx-text-fill: #1e3a8a; -fx-padding: 10px 0 30px 0;");

        Label profileIcon = new Label("👤");
        profileIcon.setFont(Font.font("System", 36));
        Label profileLabel = new Label("Profile");
        profileLabel.setFont(Font.font("Inter", FontWeight.SEMI_BOLD, 15));
        profileLabel.setStyle("-fx-text-fill: #4b5563;");
        HBox pBox = new HBox(15, profileIcon, profileLabel);
        pBox.setAlignment(Pos.CENTER_LEFT);
        pBox.setStyle("-fx-padding: 12px 15px; -fx-background-radius: 10px;");
        pBox.setOnMouseEntered(event -> pBox
                .setStyle("-fx-padding: 12px 15px; -fx-background-radius: 10px; -fx-background-color: #eef2ff;"));
        pBox.setOnMouseExited(event -> pBox
                .setStyle("-fx-padding: 12px 15px; -fx-background-radius: 10px; -fx-background-color: transparent;"));
        pBox.setOnMouseClicked(event -> {
            showProfileScreen.run();
        });

        VBox navButtons = new VBox(10);

        HBox navBtn1 = new HBox(15, new Label("📄"), new Label("Legal Case Management"));
        navBtn1.getChildren().get(0).setStyle("-fx-font-size: 24px;");
        navBtn1.getChildren().get(1).setStyle("-fx-font-size: 15px; -fx-font-weight: 500; -fx-text-fill: #4b5563;");
        navBtn1.setAlignment(Pos.CENTER_LEFT);
        navBtn1.setStyle("-fx-padding: 12px 15px; -fx-background-radius: 10px; -fx-background-color: transparent;");
        navBtn1.setCursor(Cursor.HAND);
        navBtn1.setOnMouseEntered(event -> navBtn1
                .setStyle("-fx-padding: 12px 15px; -fx-background-radius: 10px; -fx-background-color: #eef2ff;"));
        navBtn1.setOnMouseExited(event -> navBtn1
                .setStyle("-fx-padding: 12px 15px; -fx-background-radius: 10px; -fx-background-color: transparent;"));
        navBtn1.setOnMouseClicked(evt -> {
            showlegalmana.run();
        });

        HBox navBtn2 = new HBox(15, new Label("📜"), new Label("Document & Certificate"));
        navBtn2.getChildren().get(0).setStyle("-fx-font-size: 24px;");
        navBtn2.getChildren().get(1).setStyle("-fx-font-size: 15px; -fx-font-weight: 600; -fx-text-fill: #3b82f6;");
        navBtn2.setAlignment(Pos.CENTER_LEFT);
        navBtn2.setStyle("-fx-padding: 12px 15px; -fx-background-radius: 10px; -fx-background-color: #e0e7ff;");
        navBtn2.setCursor(Cursor.HAND);

        HBox navBtn3 = new HBox(15, new Label("🏠"), new Label("Land & Property Services"));
        navBtn3.getChildren().get(0).setStyle("-fx-font-size: 24px;");
        navBtn3.getChildren().get(1).setStyle("-fx-font-size: 15px; -fx-font-weight: 500; -fx-text-fill: #4b5563;");
        navBtn3.setAlignment(Pos.CENTER_LEFT);
        navBtn3.setStyle("-fx-padding: 12px 15px; -fx-background-radius: 10px; -fx-background-color: transparent;");
        navBtn3.setCursor(Cursor.HAND);
        navBtn3.setOnMouseEntered(event -> navBtn3
                .setStyle("-fx-padding: 12px 15px; -fx-background-radius: 10px; -fx-background-color: #eef2ff;"));
        navBtn3.setOnMouseExited(event -> navBtn3
                .setStyle("-fx-padding: 12px 15px; -fx-background-radius: 10px; -fx-background-color: transparent;"));
        navBtn3.setOnMouseClicked(event -> {
            showlandrecord.run();
        });

        HBox navBtn4 = new HBox(15, new Label("⇄"), new Label("RTI & Grievance"));
        navBtn4.getChildren().get(0).setStyle("-fx-font-size: 24px;");
        navBtn4.getChildren().get(1).setStyle("-fx-font-size: 15px; -fx-font-weight: 500; -fx-text-fill: #4b5563;");
        navBtn4.setAlignment(Pos.CENTER_LEFT);
        navBtn4.setStyle("-fx-padding: 12px 15px; -fx-background-radius: 10px; -fx-background-color: transparent;");
        navBtn4.setCursor(Cursor.HAND);
        navBtn4.setOnMouseEntered(event -> navBtn4
                .setStyle("-fx-padding: 12px 15px; -fx-background-radius: 10px; -fx-background-color: #eef2ff;"));
        navBtn4.setOnMouseExited(event -> navBtn4
                .setStyle("-fx-padding: 12px 15px; -fx-background-radius: 10px; -fx-background-color: transparent;"));
        navBtn4.setOnMouseClicked(event -> {
            showRTI.run();
        });

        HBox navBtn5 = new HBox(15, new Label("📚"), new Label("Legal Knowledge Base"));
        navBtn5.getChildren().get(0).setStyle("-fx-font-size: 24px;");
        navBtn5.getChildren().get(1).setStyle("-fx-font-size: 15px; -fx-font-weight: 500; -fx-text-fill: #4b5563;");
        navBtn5.setAlignment(Pos.CENTER_LEFT);
        navBtn5.setStyle("-fx-padding: 12px 15px; -fx-background-radius: 10px; -fx-background-color: transparent;");
        navBtn5.setCursor(Cursor.HAND);
        navBtn5.setOnMouseEntered(event -> navBtn5
                .setStyle("-fx-padding: 12px 15px; -fx-background-radius: 10px; -fx-background-color: #eef2ff;"));
        navBtn5.setOnMouseExited(event -> navBtn5
                .setStyle("-fx-padding: 12px 15px; -fx-background-radius: 10px; -fx-background-color: transparent;"));
        navBtn5.setOnMouseClicked(evt -> {
            showarchive.run();
        });
         HBox navBtn6 = new HBox(15, new Label("ℹ️"), new Label("About Us"));
        navBtn6.getChildren().get(0).setStyle("-fx-font-size: 24px;");
        navBtn6.getChildren().get(1).setStyle("-fx-font-size: 15px; -fx-font-weight: 600; -fx-text-fill: #4b5563;");
        navBtn6.setAlignment(Pos.CENTER_LEFT);
        navBtn6.setOnMouseEntered(event -> navBtn6
                .setStyle("-fx-padding: 12px 15px; -fx-background-radius: 10px; -fx-background-color: #eef2ff;"));
        navBtn6.setOnMouseExited(event -> navBtn6
                .setStyle("-fx-padding: 12px 15px; -fx-background-radius: 10px; -fx-background-color: transparent;"));
        
        navBtn6.setCursor(Cursor.HAND);
        navBtn6.setOnMouseClicked(evt ->{
            showAboutUsScreen.run();
        });

        navButtons.getChildren().addAll(navBtn1, navBtn2, navBtn3, navBtn4, navBtn5, navBtn6);

        Region sidebarSpacer = new Region();
        VBox.setVgrow(sidebarSpacer, Priority.ALWAYS);

        HBox links = new HBox(15);
        links.setAlignment(Pos.CENTER);
        Label contact = new Label("Contact");
        Label terms = new Label("Terms");
        Label privacy = new Label("Privacy");
        String footerStyle = "-fx-font-size: 13px; -fx-text-fill: #6b7280;";
        contact.setStyle(footerStyle);
        terms.setStyle(footerStyle);
        privacy.setStyle(footerStyle);
        links.getChildren().addAll(contact, terms, privacy);

        sidebar.getChildren().addAll(sidebarTitle, pBox, navButtons, sidebarSpacer, links);

        VBox mainContent = new VBox(25);
        mainContent.setPadding(new Insets(20, 40, 40, 40));
        mainContent.setStyle("-fx-background-color: transparent;");

        HBox topNav = new HBox(30);
        topNav.setAlignment(Pos.CENTER_LEFT);


        HBox navLinks = new HBox(30);
        String topNavLinkStyle = "-fx-font-size: 15px; -fx-text-fill: #4b5563; -fx-font-weight: 500; -fx-cursor: hand;";
        String topNavLinkHoverStyle = "-fx-font-size: 15px; -fx-text-fill: #1d4ed8; -fx-font-weight: 500; -fx-cursor: hand;";

        Label home = new Label("Home");
        home.setStyle(topNavLinkStyle);
        home.setOnMouseEntered(event -> home.setStyle(topNavLinkHoverStyle));
        home.setOnMouseExited(event -> home.setStyle(topNavLinkStyle));
        home.setOnMouseClicked(evt -> {
           
        });

        Label services = new Label("Services");
        services.setStyle(topNavLinkStyle);
        services.setOnMouseEntered(event -> services.setStyle(topNavLinkHoverStyle));
        services.setOnMouseExited(event -> services.setStyle(topNavLinkStyle));

        Label rtiLink = new Label("RTI");
        rtiLink.setStyle(topNavLinkStyle);
        rtiLink.setOnMouseEntered(event -> rtiLink.setStyle(topNavLinkHoverStyle));
        rtiLink.setOnMouseExited(event -> rtiLink.setStyle(topNavLinkStyle));
        rtiLink.setOnMouseClicked(evt -> {
            showRTI.run();
        });

        Label help = new Label("Legal Help");
        help.setStyle(topNavLinkStyle);
        help.setOnMouseEntered(event -> help.setStyle(topNavLinkHoverStyle));
        help.setOnMouseExited(event -> help.setStyle(topNavLinkStyle));
        help.setOnMouseClicked(evt -> {
            showlegalmana.run();
        });

        Label resourcesLink = new Label("Resources");
        resourcesLink.setStyle(topNavLinkStyle);
        resourcesLink.setOnMouseEntered(event -> resourcesLink.setStyle(topNavLinkHoverStyle));
        resourcesLink.setOnMouseExited(event -> resourcesLink.setStyle(topNavLinkStyle));
        resourcesLink.setOnMouseClicked(evt -> {
            showarchive.run();
        });

        navLinks.getChildren().addAll(home, rtiLink, help, resourcesLink);

        Region topNavSpacer = new Region();
        HBox.setHgrow(topNavSpacer, Priority.ALWAYS);

        Button notificationButton = new Button("🔔");
        notificationButton.setFont(Font.font("System", 14));
        notificationButton.setStyle(
                "-fx-background-color: transparent; -fx-border-color: #d1d5db; -fx-border-width: 1.5px; -fx-border-radius: 8px; -fx-background-radius: 8px; -fx-padding: 8px 20px;");
        notificationButton.setCursor(Cursor.HAND);
        notificationButton.setOnMouseEntered(event -> notificationButton.setStyle(
                "-fx-background-color: #f6f3f3; -fx-border-color: #d1d5db; -fx-border-width: 1.5px; -fx-border-radius: 8px; -fx-background-radius: 8px; -fx-padding: 8px 20px;"));
        notificationButton.setOnMouseExited(event -> notificationButton.setStyle(
                "-fx-background-color: transparent; -fx-border-color: #d1d5db; -fx-border-width: 1.5px; -fx-border-radius: 8px; -fx-background-radius: 8px; -fx-padding: 8px 20px;"));
        notificationButton.setOnMouseClicked(evt -> {
            shownotification2.run();
        });

        Button logoutButton = new Button("LogOut");
        logoutButton.setFont(Font.font("Inter", FontWeight.BOLD, 14));
        logoutButton.setStyle(
                "-fx-background-color: #f63b3b; -fx-background-radius: 8px; -fx-text-fill: white; -fx-padding: 8px 20px;");
        logoutButton.setCursor(Cursor.HAND);
        logoutButton.setOnMouseEntered(event -> logoutButton.setStyle(
                "-fx-background-color: #eb2525; -fx-background-radius: 8px; -fx-text-fill: white; -fx-padding: 8px 20px;"));
        logoutButton.setOnMouseExited(event -> logoutButton.setStyle(
                "-fx-background-color: #f63b3b; -fx-background-radius: 8px; -fx-text-fill: white; -fx-padding: 8px 20px;"));
        logoutButton.setOnAction(event -> {
            showLoginScreen.run();
        });

        HBox loginButtons = new HBox(10, notificationButton, logoutButton);
        loginButtons.setAlignment(Pos.CENTER);
        topNav.getChildren().addAll(navLinks, topNavSpacer, loginButtons);

        Label mainTitle = new Label("Document & Certificate");
        mainTitle.setFont(Font.font("Inter", FontWeight.BOLD, 32));
        mainTitle.setStyle("-fx-text-fill: #1e3a8a; -fx-padding: 10px 0;");

        VBox content = new VBox(30);
        content.setPadding(new Insets(20, 0, 0, 0));

        VBox uploadSection = new VBox(15);
        Label uploadTitle = new Label("Upload & Store Documents");
        uploadTitle.setFont(Font.font("Inter", FontWeight.SEMI_BOLD, 20));
        uploadTitle.setStyle("-fx-text-fill: #1e3a8a;");

        VBox dropZone = new VBox(10);
        dropZone.setAlignment(Pos.CENTER);
        dropZone.setStyle(
                "-fx-background-color: rgba(255, 255, 255, 0.6); -fx-border-color: #a0b3d7; -fx-border-style: dashed; -fx-border-width: 2; -fx-border-radius: 10; -fx-background-radius: 10;");
        dropZone.setPadding(new Insets(30));
        dropZone.setPrefHeight(300);

        Label dropIcon = new Label("☁️");
        dropIcon.setFont(Font.font("System", 30));
        Label dropText = new Label("Drag and drop files here. Or");
        dropText.setFont(Font.font("Inter", FontWeight.NORMAL, 16));
        dropText.setStyle("-fx-text-fill: #4b5563;");

        Button selectDocButton = new Button("Select File");
        selectDocButton.setFont(Font.font("Inter", FontWeight.SEMI_BOLD, 14));
        selectDocButton.setStyle(
                "-fx-background-color: #d1d5db; -fx-background-radius: 8px; -fx-text-fill: #1f2937; -fx-padding: 5px 15px;");
        selectDocButton.setCursor(Cursor.HAND);
        // MODIFIED: Connect button to upload handler
        selectDocButton.setOnAction(event -> handleSelectAndUploadFile());

        HBox dropZoneContent = new HBox(10, dropText, selectDocButton);
        dropZoneContent.setAlignment(Pos.CENTER);

        dropZone.getChildren().addAll(dropIcon, dropZoneContent);
        uploadSection.getChildren().addAll(uploadTitle, dropZone);

        VBox verifySection = new VBox(15);
        Label verifyTitle = new Label("Verify Documents");
        verifyTitle.setFont(Font.font("Inter", FontWeight.SEMI_BOLD, 20));
        verifyTitle.setStyle("-fx-text-fill: #1e3a8a;");
        Button verifyButton = new Button("Verify");
        verifyButton.setFont(Font.font("Inter", FontWeight.BOLD, 15));
        verifyButton.setStyle(
                "-fx-background-color: #3b82f6; -fx-text-fill: white; -fx-background-radius: 10px; -fx-padding: 10px 25px;");
        verifyButton.setCursor(Cursor.HAND);
        verifyButton.setOnMouseEntered(event -> verifyButton.setStyle(
                "-fx-background-color: #2563eb; -fx-text-fill: white; -fx-background-radius: 10px; -fx-font-weight: bold; -fx-font-size: 15px; -fx-padding: 10px 25px;"));
        verifyButton.setOnMouseExited(event -> verifyButton.setStyle(
                "-fx-background-color: #3b82f6; -fx-text-fill: white; -fx-background-radius: 10px; -fx-font-weight: bold; -fx-font-size: 15px; -fx-padding: 10px 25px;"));
        verifySection.getChildren().addAll(verifyTitle, verifyButton);

        VBox documentsListSection = new VBox(15);
        Label listTitle = new Label("Your Documents");
        listTitle.setFont(Font.font("Inter", FontWeight.SEMI_BOLD, 20));
        listTitle.setStyle("-fx-text-fill: #1e3a8a;");

        documentGrid = new GridPane();
        documentGrid.setVgap(15);
        documentGrid.setHgap(10);
        documentGrid.setPadding(new Insets(10, 0, 0, 0));

        documentsListSection.getChildren().addAll(listTitle, documentGrid);
        content.getChildren().addAll(uploadSection, verifySection, documentsListSection);

        mainContent.getChildren().addAll(topNav, mainTitle, content);

        mainbox.setLeft(sidebar);
        mainbox.setCenter(mainContent);

        loadUserDocuments();

        return mainbox;
    }

    private void handleSelectAndUploadFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose File to Upload");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PDF Files", "*.pdf"),
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));
        File selectedFile = fileChooser.showOpenDialog(docStage);

        if (selectedFile != null) {
            uploadFile(selectedFile);
        }
    }

    private void uploadFile(File file) {
        String uid = ViewController.getCurrentUserId();
        if (uid == null) {
            showAlert(Alert.AlertType.ERROR, "Error", "You must be logged in to upload files.");
            return;
        }

        try {
            StorageClient storageClient = StorageClient.getInstance();
            String bucketName = storageClient.bucket().getName();
            String blobName = "documents/" + uid + "/" + file.getName();

            String contentType = Files.probeContentType(file.toPath());
            if (contentType == null) {
                contentType = "application/octet-stream";
            }

            storageClient.bucket().create(blobName, Files.readAllBytes(file.toPath()), contentType);

            String downloadUrl = "https://firebasestorage.googleapis.com/v0/b/" + bucketName + "/o/" +
                    java.net.URLEncoder.encode(blobName, "UTF-8") + "?alt=media";

            addDocumentToFirestore(file.getName(), downloadUrl, Files.size(file.toPath()));

        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Upload Failed", "Could not upload file: " + e.getMessage());
        }
    }

    private void addDocumentToFirestore(String fileName, String downloadUrl, long fileSize) {
        String uid = ViewController.getCurrentUserId();
        Firestore db = FirestoreClient.getFirestore();

        Map<String, Object> docData = new HashMap<>();
        docData.put("fileName", fileName);
        docData.put("downloadUrl", downloadUrl);
        docData.put("fileSize", fileSize);
        docData.put("uploadedAt", Timestamp.now());
        docData.put("status", "Verified");

        db.collection("users").document(uid).collection("documents").add(docData)
                .addListener(() -> {
                    System.out.println("Document metadata saved to Firestore.");
                    showAlert(Alert.AlertType.INFORMATION, "Success", "File uploaded successfully!");

                    Platform.runLater(this::loadUserDocuments);
                }, Platform::runLater);
    }

    private void loadUserDocuments() {
        String uid = ViewController.getCurrentUserId();
        if (uid == null)
            return;

        documentGrid.getChildren().clear();

        Label nameHeader = new Label("Document Name");
        nameHeader.setFont(Font.font("Inter", FontWeight.SEMI_BOLD, 14));
        Label statusHeader = new Label("Status");
        statusHeader.setFont(Font.font("Inter", FontWeight.SEMI_BOLD, 14));
        Label dateHeader = new Label("Uploaded Date");
        dateHeader.setFont(Font.font("Inter", FontWeight.SEMI_BOLD, 14));
        documentGrid.add(nameHeader, 1, 0);
        documentGrid.add(statusHeader, 2, 0);
        documentGrid.add(dateHeader, 3, 0);

        Firestore db = FirestoreClient.getFirestore();
        CollectionReference documentsRef = db.collection("users").document(uid).collection("documents");

        ApiFuture<QuerySnapshot> future = documentsRef.orderBy("uploadedAt", Query.Direction.DESCENDING).get();
        future.addListener(() -> {
            try {
                List<QueryDocumentSnapshot> documents = future.get().getDocuments();
                Platform.runLater(() -> {
                    int row = 1;
                    for (QueryDocumentSnapshot doc : documents) {
                        addDocumentRow(doc, row++);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, Platform::runLater);
    }

    private void addDocumentRow(QueryDocumentSnapshot doc, int row) {
        Label docIconLabel = new Label("📄");
        docIconLabel.setStyle("-fx-font-size: 18px;");

        Label nameLabel = new Label(doc.getString("fileName"));
        nameLabel.setStyle("-fx-font-size: 15px; -fx-text-fill: #374151; -fx-cursor: hand;");
        nameLabel.setOnMouseClicked(e -> downloadDocument(doc.getString("downloadUrl"), doc.getString("fileName")));

        Label statusLabel = new Label("✅");
        statusLabel.setStyle("-fx-font-size: 18px;");

        Timestamp uploadedAt = doc.getTimestamp("uploadedAt");
        String dateString = uploadedAt != null ? new SimpleDateFormat("dd MMM yyyy").format(uploadedAt.toDate())
                : "N/A";
        Label dateLabel = new Label(dateString);
        dateLabel.setStyle("-fx-font-size: 15px; -fx-text-fill: #6b7280;");

        Button deleteButton = new Button("❌");
        deleteButton.setStyle("-fx-background-color: transparent; -fx-font-size: 14px; -fx-cursor: hand;");
        deleteButton.setOnAction(e -> deleteDocument(doc.getId(), doc.getString("fileName")));

        documentGrid.add(docIconLabel, 0, row);
        documentGrid.add(nameLabel, 1, row);
        documentGrid.add(statusLabel, 2, row);
        documentGrid.add(dateLabel, 3, row);
        documentGrid.add(deleteButton, 4, row);
    }

    private void downloadDocument(String downloadUrl, String fileName) {
        if (downloadUrl == null || downloadUrl.isEmpty())
            return;

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Document");
        fileChooser.setInitialFileName(fileName);
        File file = fileChooser.showSaveDialog(docStage);

        if (file != null) {
            try {
                StorageClient storageClient = StorageClient.getInstance();
                byte[] content = storageClient.bucket().get(getBlobNameFromUrl(downloadUrl)).getContent();

                try (FileOutputStream fos = new FileOutputStream(file.getAbsolutePath())) {
                    fos.write(content);
                }
                showAlert(Alert.AlertType.INFORMATION, "Success",
                        "File downloaded successfully to:\n" + file.getAbsolutePath());
            } catch (Exception e) {
                e.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Download Failed", "Could not download file.");
            }
        }
    }

    private void deleteDocument(String docId, String fileName) {
        String uid = ViewController.getCurrentUserId();
        if (uid == null)
            return;

        Firestore db = FirestoreClient.getFirestore();
        db.collection("users").document(uid).collection("documents").document(docId).delete()
                .addListener(() -> {
                    System.out.println("Document " + docId + " deleted from Firestore.");
                    try {
                        StorageClient storageClient = StorageClient.getInstance();
                        String blobName = "documents/" + uid + "/" + fileName;
                        storageClient.bucket().get(blobName).delete();
                        System.out.println("File " + fileName + " deleted from Storage.");
                    } catch (Exception e) {
                        System.err.println("Info: Could not delete from Storage (might not exist): " + e.getMessage());
                    }
                    Platform.runLater(this::loadUserDocuments);
                }, Platform::runLater);
    }

    private String getBlobNameFromUrl(String url) {
        String prefix = "/o/";
        String suffix = "?alt=media";
        try {
            String decodedUrl = java.net.URLDecoder.decode(url, "UTF-8");
            int startIndex = decodedUrl.indexOf(prefix) + prefix.length();
            int endIndex = decodedUrl.indexOf(suffix);
            if (startIndex > -1 && endIndex > -1) {
                return decodedUrl.substring(startIndex, endIndex);
            }
            return "";
        } catch (Exception e) {
            return "";
        }
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
