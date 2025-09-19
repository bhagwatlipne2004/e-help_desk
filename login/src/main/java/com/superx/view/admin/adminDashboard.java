package com.superx.view.admin;

import com.google.api.core.ApiFuture;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.superx.Controller.ViewController;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutionException;

public class adminDashboard {
    
    public Stage adminStage;
    public Scene adminScene;
    public VBox userContainer,documentContainer;

    public Label totalUsersLabel,totalDocumentsLabel,activeUsersLabel;
    
    public TextField searchField;
    public ScrollPane userScrollPane,documentScrollPane;

    public List<QueryDocumentSnapshot> allUsers = new ArrayList<>();
    
    public void setAdminStage(Stage adminStage) {
        this.adminStage = adminStage;
    }
    
    public void setAdminScene(Scene adminScene) {
        this.adminScene = adminScene;
    }
    
    public BorderPane adminDashboardScene(Runnable showLoginScreen) {
        BorderPane mainBox = new BorderPane();
        mainBox.setStyle(
            "-fx-background-color: linear-gradient(from 0% 0% to 100% 100%, #e6f0ff, #d6e4ff); -fx-font-family: 'Inter', 'Segoe UI', sans-serif;");
        
        
        VBox sidebar = createSidebar();
        
        
        VBox mainContent = createMainContent(showLoginScreen);
        
        mainBox.setLeft(sidebar);
        mainBox.setCenter(mainContent);
        
        
        loadDashboardData();
        loadAllUsers();
        loadAllDocuments();
        
        return mainBox;
    }
    
    public VBox createSidebar() {
        VBox sidebar = new VBox();
        sidebar.setSpacing(20);
        sidebar.setPadding(new Insets(20, 15, 20, 15));
        sidebar.setStyle("-fx-background-color: #f5f9ff; -fx-pref-width: 280px;");
        sidebar.setAlignment(Pos.TOP_CENTER);
        
        
        Label sidebarTitle = new Label("Admin Dashboard");
        sidebarTitle.setFont(Font.font("Inter", FontWeight.BOLD, 24));
        sidebarTitle.setStyle("-fx-text-fill: #1e3a8a; -fx-padding: 10px 0 30px 0;");
        
        
        VBox navButtons = new VBox();
        navButtons.setSpacing(10);
        
        HBox dashboardBtn = createNavButton("📊", "Dashboard Overview", true);

        
        navButtons.getChildren().addAll(dashboardBtn);
        
        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);
        
        sidebar.getChildren().addAll(sidebarTitle, navButtons, spacer);
        return sidebar;
    }
    
    public HBox createNavButton(String icon, String text, boolean active) {
        HBox navBtn = new HBox();
        navBtn.setSpacing(15);
        navBtn.setAlignment(Pos.CENTER_LEFT);
        
        Label iconLabel = new Label(icon);
        iconLabel.setStyle("-fx-font-size: 24px;");
        
        Label textLabel = new Label(text);
        textLabel.setStyle("-fx-font-size: 15px; -fx-font-weight: " + 
            (active ? "600; -fx-text-fill: #3b82f6;" : "500; -fx-text-fill: #4b5563;"));
        
        navBtn.getChildren().addAll(iconLabel, textLabel);
        navBtn.setStyle("-fx-padding: 12px 15px; -fx-background-radius: 10px; -fx-background-color: " +
            (active ? "#e0e7ff;" : "transparent;"));
        navBtn.setCursor(Cursor.HAND);
        
        if (!active) {
            navBtn.setOnMouseEntered(event -> navBtn.setStyle(
                "-fx-padding: 12px 15px; -fx-background-radius: 10px; -fx-background-color: #eef2ff;"));
            navBtn.setOnMouseExited(event -> navBtn.setStyle(
                "-fx-padding: 12px 15px; -fx-background-radius: 10px; -fx-background-color: transparent;"));
        }
        
        return navBtn;
    }
    
    public VBox createMainContent(Runnable showLoginScreen) {
        VBox mainContent = new VBox();
        mainContent.setSpacing(25);
        mainContent.setPadding(new Insets(20, 40, 40, 40));
        
        
        HBox topNav = createTopNavigation(showLoginScreen);
        
        
        Label mainTitle = new Label("Admin Dashboard");
        mainTitle.setFont(Font.font("Inter", FontWeight.BOLD, 32));
        mainTitle.setStyle("-fx-text-fill: #1e3a8a; -fx-padding: 10px 0;");
        
        
        HBox statsCards = createStatsCards();
        
        
        HBox searchSection = createSearchSection();
        
        
        TabPane contentTabs = createContentTabs();
        
        mainContent.getChildren().addAll(topNav, mainTitle, statsCards, searchSection, contentTabs);
        return mainContent;
    }
    
    public HBox createTopNavigation(Runnable showLoginScreen) {
        HBox topNav = new HBox();
        topNav.setSpacing(30);
        topNav.setAlignment(Pos.CENTER_LEFT);
        
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        
        Button logoutButton = new Button("LogOut");
        logoutButton.setFont(Font.font("Inter", FontWeight.BOLD, 14));
        logoutButton.setStyle(
            "-fx-background-color: #f63b3b; -fx-background-radius: 8px; -fx-text-fill: white; -fx-padding: 8px 20px;");
        logoutButton.setCursor(Cursor.HAND);
        logoutButton.setOnAction(event -> showLoginScreen.run());
        
        topNav.getChildren().addAll(spacer, logoutButton);
        return topNav;
    }
    
    public HBox createStatsCards() {
        HBox statsCards = new HBox();
        statsCards.setSpacing(20);
        statsCards.setAlignment(Pos.CENTER);
        
        
        VBox totalUsersCard = createStatsCard("Total Users", "0", "👥", "#3b82f6");
        totalUsersLabel = (Label) ((VBox) totalUsersCard.getChildren().get(1)).getChildren().get(0);
        
        
        VBox activeUsersCard = createStatsCard("Active Users", "0", "✅", "#10b981");
        activeUsersLabel = (Label) ((VBox) activeUsersCard.getChildren().get(1)).getChildren().get(0);
        
        
        VBox totalDocsCard = createStatsCard("Total Documents", "0", "📄", "#f59e0b");
        totalDocumentsLabel = (Label) ((VBox) totalDocsCard.getChildren().get(1)).getChildren().get(0);
        
        
        VBox systemHealthCard = createStatsCard("System Status", "Online", "🟢", "#10b981");
        
        statsCards.getChildren().addAll(totalUsersCard, activeUsersCard, totalDocsCard, systemHealthCard);
        return statsCards;
    }
    
    public VBox createStatsCard(String title, String value, String icon, String color) {
        VBox card = new VBox();
        card.setSpacing(10);
        card.setAlignment(Pos.CENTER);
        card.setPadding(new Insets(20));
        card.setStyle("-fx-background-color: rgba(255, 255, 255, 0.8); -fx-background-radius: 15px; " +
                     "-fx-border-color: #e5e7eb; -fx-border-width: 1px; -fx-border-radius: 15px;");
        card.setPrefWidth(200);
        
        Label iconLabel = new Label(icon);
        iconLabel.setStyle("-fx-font-size: 32px;");
        
        VBox textBox = new VBox();
        textBox.setSpacing(5);
        textBox.setAlignment(Pos.CENTER);
        
        Label valueLabel = new Label(value);
        valueLabel.setFont(Font.font("Inter", FontWeight.BOLD, 24));
        valueLabel.setStyle("-fx-text-fill: " + color + ";");
        
        Label titleLabel = new Label(title);
        titleLabel.setFont(Font.font("Inter", FontWeight.MEDIUM, 14));
        titleLabel.setStyle("-fx-text-fill: #6b7280;");
        
        textBox.getChildren().addAll(valueLabel, titleLabel);
        card.getChildren().addAll(iconLabel, textBox);
        
        return card;
    }
    
    public HBox createSearchSection() {
        HBox searchSection = new HBox();
        searchSection.setSpacing(15);
        searchSection.setAlignment(Pos.CENTER_LEFT);
        searchSection.setPadding(new Insets(10, 0, 10, 0));
        
        searchField = new TextField();
        searchField.setPromptText("Search users by name or email...");
        searchField.setPrefWidth(300);
        searchField.setStyle("-fx-background-radius: 8px; -fx-padding: 10px;");
        searchField.setOnKeyReleased(event -> filterUsers());
        
        Button exportButton = new Button("📊 Export to Excel");
        exportButton.setFont(Font.font("Inter", FontWeight.SEMI_BOLD, 14));
        exportButton.setStyle(
            "-fx-background-color: #10b981; -fx-text-fill: white; -fx-background-radius: 8px; -fx-padding: 10px 20px;");
        exportButton.setCursor(Cursor.HAND);
        exportButton.setOnAction(event -> exportUsersToExcel());
        
        Button refreshButton = new Button("🔄 Refresh");
        refreshButton.setFont(Font.font("Inter", FontWeight.SEMI_BOLD, 14));
        refreshButton.setStyle(
            "-fx-background-color: #3b82f6; -fx-text-fill: white; -fx-background-radius: 8px; -fx-padding: 10px 20px;");
        refreshButton.setCursor(Cursor.HAND);
        refreshButton.setOnAction(event -> {
            loadDashboardData();
            loadAllUsers();
            loadAllDocuments();
        });
        
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        
        searchSection.getChildren().addAll(searchField, spacer, exportButton, refreshButton);
        return searchSection;
    }
    
    public TabPane createContentTabs() {
        TabPane tabPane = new TabPane();
        tabPane.setStyle("-fx-background-color: transparent;");
        
        
        Tab usersTab = new Tab("User Management");
        usersTab.setClosable(false);
        usersTab.setContent(createUsersContent());
        
        
        Tab documentsTab = new Tab("Document Control");
        documentsTab.setClosable(false);
        documentsTab.setContent(createDocumentsContent());
        
        tabPane.getTabs().addAll(usersTab, documentsTab);
        return tabPane;
    }
    
    public ScrollPane createUsersContent() {
        VBox content = new VBox();
        content.setSpacing(15);
        content.setPadding(new Insets(20));
        
        Label title = new Label("User Management");
        title.setFont(Font.font("Inter", FontWeight.SEMI_BOLD, 20));
        title.setStyle("-fx-text-fill: #1e3a8a;");
        
        
        HBox headerBox = createUserHeaderBox();
        
        
        userContainer = new VBox();
        userContainer.setSpacing(5);
        
        userScrollPane = new ScrollPane(userContainer);
        userScrollPane.setFitToWidth(true);
        userScrollPane.setStyle("-fx-background-color: transparent;");
        userScrollPane.setPrefHeight(400);
        
        content.getChildren().addAll(title, headerBox, userScrollPane);
        
        ScrollPane scrollPane = new ScrollPane(content);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background-color: transparent;");
        return scrollPane;
    }
    
    public ScrollPane createDocumentsContent() {
        VBox content = new VBox();
        content.setSpacing(15);
        content.setPadding(new Insets(20));
        
        Label title = new Label("Document Control Center");
        title.setFont(Font.font("Inter", FontWeight.SEMI_BOLD, 20));
        title.setStyle("-fx-text-fill: #1e3a8a;");
        
        
        HBox headerBox = createDocumentHeaderBox();
        
        
        documentContainer = new VBox();
        documentContainer.setSpacing(5);
        
        documentScrollPane = new ScrollPane(documentContainer);
        documentScrollPane.setFitToWidth(true);
        documentScrollPane.setStyle("-fx-background-color: transparent;");
        documentScrollPane.setPrefHeight(400);
        
        content.getChildren().addAll(title, headerBox, documentScrollPane);
        
        ScrollPane scrollPane = new ScrollPane(content);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background-color: transparent;");
        return scrollPane;
    }
    
    public HBox createUserHeaderBox() {
        HBox headerBox = new HBox();
        headerBox.setSpacing(20);
        headerBox.setPadding(new Insets(10));
        headerBox.setStyle("-fx-background-color: #f3f4f6; -fx-background-radius: 8px;");
        
        String headerStyle = "-fx-font-weight: bold; -fx-font-size: 14px; -fx-text-fill: #374151;";
        
        Label nameHeader = new Label("Name");
        nameHeader.setStyle(headerStyle);
        nameHeader.setPrefWidth(150);
        
        Label emailHeader = new Label("Email");
        emailHeader.setStyle(headerStyle);
        emailHeader.setPrefWidth(200);
        
        Label documentsHeader = new Label("Documents");
        documentsHeader.setStyle(headerStyle);
        documentsHeader.setPrefWidth(100);
        
        Label joinDateHeader = new Label("Join Date");
        joinDateHeader.setStyle(headerStyle);
        joinDateHeader.setPrefWidth(120);
        
        Label statusHeader = new Label("Status");
        statusHeader.setStyle(headerStyle);
        statusHeader.setPrefWidth(80);
        
        Label actionsHeader = new Label("Actions");
        actionsHeader.setStyle(headerStyle);
        actionsHeader.setPrefWidth(120);
        
        headerBox.getChildren().addAll(nameHeader, emailHeader, documentsHeader, 
                                     joinDateHeader, statusHeader, actionsHeader);
        return headerBox;
    }
    
    public HBox createDocumentHeaderBox() {
        HBox headerBox = new HBox();
        headerBox.setSpacing(20);
        headerBox.setPadding(new Insets(10));
        headerBox.setStyle("-fx-background-color: #f3f4f6; -fx-background-radius: 8px;");
        
        String headerStyle = "-fx-font-weight: bold; -fx-font-size: 14px; -fx-text-fill: #374151;";
        
        Label userHeader = new Label("User");
        userHeader.setStyle(headerStyle);
        userHeader.setPrefWidth(150);
        
        Label documentHeader = new Label("Document Name");
        documentHeader.setStyle(headerStyle);
        documentHeader.setPrefWidth(200);
        
        Label sizeHeader = new Label("Size");
        sizeHeader.setStyle(headerStyle);
        sizeHeader.setPrefWidth(80);
        
        Label uploadDateHeader = new Label("Upload Date");
        uploadDateHeader.setStyle(headerStyle);
        uploadDateHeader.setPrefWidth(120);
        
        Label statusHeader = new Label("Status");
        statusHeader.setStyle(headerStyle);
        statusHeader.setPrefWidth(100);
        
        Label actionsHeader = new Label("Actions");
        actionsHeader.setStyle(headerStyle);
        actionsHeader.setPrefWidth(120);
        
        headerBox.getChildren().addAll(userHeader, documentHeader, sizeHeader, 
                                     uploadDateHeader, statusHeader, actionsHeader);
        return headerBox;
    }
    
    public void loadDashboardData() {
        Firestore db = FirestoreClient.getFirestore();
        
        
        ApiFuture<QuerySnapshot> usersFuture = db.collection("users").get();
        usersFuture.addListener(() -> {
            try {
                List<QueryDocumentSnapshot> users = usersFuture.get().getDocuments();
                Platform.runLater(() -> {
                    totalUsersLabel.setText(String.valueOf(users.size()));
                    activeUsersLabel.setText(String.valueOf(users.size())); 
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, Platform::runLater);
        
        
        loadDocumentCount();
    }
    
    public void loadDocumentCount() {
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> usersFuture = db.collection("users").get();
        
        usersFuture.addListener(() -> {
            try {
                List<QueryDocumentSnapshot> users = usersFuture.get().getDocuments();
                int totalDocuments = 0;
                
                for (QueryDocumentSnapshot user : users) {
                    ApiFuture<QuerySnapshot> docsFuture = user.getReference().collection("documents").get();
                    try {
                        totalDocuments += docsFuture.get().getDocuments().size();
                    } catch (Exception e) {
                        
                    }
                }
                
                final int finalCount = totalDocuments;
                Platform.runLater(() -> totalDocumentsLabel.setText(String.valueOf(finalCount)));
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, Platform::runLater);
    }
    
    public void loadAllUsers() {
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = db.collection("users").get();
        
        future.addListener(() -> {
            try {
                List<QueryDocumentSnapshot> users = future.get().getDocuments();
                allUsers = users; 
                Platform.runLater(() -> {
                    userContainer.getChildren().clear();
                    for (QueryDocumentSnapshot user : users) {
                        addUserRow(user);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, Platform::runLater);
    }
    
    public void addUserRow(QueryDocumentSnapshot user) {
        HBox userRow = new HBox();
        userRow.setSpacing(20);
        userRow.setPadding(new Insets(10));
        userRow.setStyle("-fx-background-color: white; -fx-background-radius: 5px; " +
                        "-fx-border-color: #e5e7eb; -fx-border-width: 1px; -fx-border-radius: 5px;");
        
        String cellStyle = "-fx-font-size: 13px; -fx-text-fill: #374151;";
        
        
        Label nameLabel = new Label(user.getString("name") != null ? user.getString("name") : "N/A");
        nameLabel.setStyle(cellStyle);
        nameLabel.setPrefWidth(150);
        
        
        Label emailLabel = new Label(user.getString("email") != null ? user.getString("email") : "N/A");
        emailLabel.setStyle(cellStyle);
        emailLabel.setPrefWidth(200);
        
        
        Label docCountLabel = new Label("Loading...");
        docCountLabel.setStyle(cellStyle);
        docCountLabel.setPrefWidth(100);
        loadUserDocumentCount(user.getId(), docCountLabel);
        
        
        Timestamp createdAt = user.getTimestamp("createdAt");
        String joinDate = createdAt != null ? 
            new SimpleDateFormat("dd MMM yyyy").format(createdAt.toDate()) : "N/A";
        Label joinDateLabel = new Label(joinDate);
        joinDateLabel.setStyle(cellStyle);
        joinDateLabel.setPrefWidth(120);
        
        
        Label statusLabel = new Label("✅ Active");
        statusLabel.setStyle(cellStyle + " -fx-text-fill: #10b981;");
        statusLabel.setPrefWidth(80);
        
        
        HBox actionsBox = new HBox();
        actionsBox.setSpacing(5);
        actionsBox.setPrefWidth(120);
        
        Button viewButton = new Button("👁");
        viewButton.setStyle("-fx-background-color: #3b82f6; -fx-text-fill: white; -fx-background-radius: 5px; -fx-padding: 5px 8px;");
        viewButton.setCursor(Cursor.HAND);
        viewButton.setOnAction(e -> viewUserDetails(user));
        
        Button deleteButton = new Button("🗑");
        deleteButton.setStyle("-fx-background-color: #ef4444; -fx-text-fill: white; -fx-background-radius: 5px; -fx-padding: 5px 8px;");
        deleteButton.setCursor(Cursor.HAND);
        deleteButton.setOnAction(e -> deleteUser(user.getId(), user.getString("email")));
        
        actionsBox.getChildren().addAll(viewButton, deleteButton);
        
        userRow.getChildren().addAll(nameLabel, emailLabel, docCountLabel, 
                                   joinDateLabel, statusLabel, actionsBox);
        
        userContainer.getChildren().add(userRow);
    }
    
    public void loadUserDocumentCount(String userId, Label countLabel) {
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = db.collection("users").document(userId).collection("documents").get();
        
        future.addListener(() -> {
            try {
                int count = future.get().getDocuments().size();
                Platform.runLater(() -> countLabel.setText(String.valueOf(count)));
            } catch (Exception e) {
                Platform.runLater(() -> countLabel.setText("0"));
            }
        }, Platform::runLater);
    }
    
    public void loadAllDocuments() {
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> usersFuture = db.collection("users").get();
        
        usersFuture.addListener(() -> {
            try {
                List<QueryDocumentSnapshot> users = usersFuture.get().getDocuments();
                Platform.runLater(() -> {
                    documentContainer.getChildren().clear();
                });
                
                for (QueryDocumentSnapshot user : users) {
                    loadUserDocuments(user);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, Platform::runLater);
    }
    
    public void loadUserDocuments(QueryDocumentSnapshot user) {
        ApiFuture<QuerySnapshot> docsFuture = user.getReference().collection("documents").get();
        
        docsFuture.addListener(() -> {
            try {
                List<QueryDocumentSnapshot> documents = docsFuture.get().getDocuments();
                Platform.runLater(() -> {
                    for (QueryDocumentSnapshot doc : documents) {
                        addDocumentRow(user, doc);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, Platform::runLater);
    }
    
    public void addDocumentRow(QueryDocumentSnapshot user, QueryDocumentSnapshot doc) {
        HBox docRow = new HBox();
        docRow.setSpacing(20);
        docRow.setPadding(new Insets(10));
        docRow.setStyle("-fx-background-color: white; -fx-background-radius: 5px; " +
                       "-fx-border-color: #e5e7eb; -fx-border-width: 1px; -fx-border-radius: 5px;");
        
        String cellStyle = "-fx-font-size: 13px; -fx-text-fill: #374151;";
        
        
        Label userLabel = new Label(user.getString("email") != null ? user.getString("email") : "Unknown");
        userLabel.setStyle(cellStyle);
        userLabel.setPrefWidth(150);
        
        
        Label docNameLabel = new Label(doc.getString("fileName") != null ? doc.getString("fileName") : "N/A");
        docNameLabel.setStyle(cellStyle);
        docNameLabel.setPrefWidth(200);
        
        
        Long fileSize = doc.getLong("fileSize");
        String sizeText = fileSize != null ? formatFileSize(fileSize) : "N/A";
        Label sizeLabel = new Label(sizeText);
        sizeLabel.setStyle(cellStyle);
        sizeLabel.setPrefWidth(80);
        
        
        Timestamp uploadedAt = doc.getTimestamp("uploadedAt");
        String uploadDate = uploadedAt != null ? 
            new SimpleDateFormat("dd MMM yyyy").format(uploadedAt.toDate()) : "N/A";
        Label uploadDateLabel = new Label(uploadDate);
        uploadDateLabel.setStyle(cellStyle);
        uploadDateLabel.setPrefWidth(120);
        
        
        Label statusLabel = new Label("✅ Verified");
        statusLabel.setStyle(cellStyle + " -fx-text-fill: #10b981;");
        statusLabel.setPrefWidth(100);
        
        
        HBox actionsBox = new HBox();
        actionsBox.setSpacing(5);
        actionsBox.setPrefWidth(120);
        
        Button downloadButton = new Button("📥");
        downloadButton.setStyle("-fx-background-color: #10b981; -fx-text-fill: white; -fx-background-radius: 5px; -fx-padding: 5px 8px;");
        downloadButton.setCursor(Cursor.HAND);
        downloadButton.setOnAction(e -> downloadDocument(doc.getString("downloadUrl"), doc.getString("fileName")));
        
        Button deleteButton = new Button("🗑");
        deleteButton.setStyle("-fx-background-color: #ef4444; -fx-text-fill: white; -fx-background-radius: 5px; -fx-padding: 5px 8px;");
        deleteButton.setCursor(Cursor.HAND);
        deleteButton.setOnAction(e -> deleteDocument(user.getId(), doc.getId(), doc.getString("fileName")));
        
        actionsBox.getChildren().addAll(downloadButton, deleteButton);
        
        docRow.getChildren().addAll(userLabel, docNameLabel, sizeLabel, 
                                  uploadDateLabel, statusLabel, actionsBox);
        
        documentContainer.getChildren().add(docRow);
    }
    
    public void filterUsers() {
        String searchText = searchField.getText().toLowerCase();
        if (searchText.isEmpty()) {
            loadAllUsers();
            return;
        }
        
        Platform.runLater(() -> {
            userContainer.getChildren().clear();
            for (QueryDocumentSnapshot user : allUsers) {
                String name = user.getString("name");
                String email = user.getString("email");
                
                if ((name != null && name.toLowerCase().contains(searchText)) ||
                    (email != null && email.toLowerCase().contains(searchText))) {
                    addUserRow(user);
                }
            }
        });
    }
    
    public void exportUsersToExcel() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save User Data");
        fileChooser.setInitialFileName("user_data_export.xlsx");
        fileChooser.getExtensionFilters().add(
            new FileChooser.ExtensionFilter("Excel Files", "*.xlsx"));
        
        File file = fileChooser.showSaveDialog(adminStage);
        if (file != null) {
            exportToExcel(file);
        }
    }
    
    public void exportToExcel(File file) {
    Firestore db = FirestoreClient.getFirestore();
    ApiFuture<QuerySnapshot> future = db.collection("users").get();

    future.addListener(() -> {
        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Users");

            Row headerRow = sheet.createRow(0);
            String[] headers = {"Name", "Email", "Document Count", "Join Date", "Status"};

            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);

                CellStyle headerStyle = workbook.createCellStyle();
                XSSFFont font = workbook.createFont();
                font.setBold(true);
                headerStyle.setFont(font);
                cell.setCellStyle(headerStyle);
            }

            List<QueryDocumentSnapshot> users = future.get().getDocuments();
            int rowNum = 1;
            for (QueryDocumentSnapshot user : users) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(user.getString("name") != null ? user.getString("name") : "N/A");
                row.createCell(1).setCellValue(user.getString("email") != null ? user.getString("email") : "N/A");

                try {
                    QuerySnapshot docs = user.getReference().collection("documents").get().get();
                    row.createCell(2).setCellValue(docs.getDocuments().size());
                } catch (Exception e) {
                    row.createCell(2).setCellValue(0);
                }

                Timestamp createdAt = user.getTimestamp("createdAt");
                String joinDate = createdAt != null ?
                        new SimpleDateFormat("dd MMM yyyy").format(createdAt.toDate()) : "N/A";
                row.createCell(3).setCellValue(joinDate);
                row.createCell(4).setCellValue("Active");
            }

            for (int i = 0; i < headers.length; i++) {
                sheet.autoSizeColumn(i);
            }

            try (FileOutputStream outputStream = new FileOutputStream(file)) {
                workbook.write(outputStream);
            }

            Platform.runLater(() -> showAlert(Alert.AlertType.INFORMATION, "Success",
                    "User data exported successfully to:\n" + file.getAbsolutePath()));

        } catch (Exception e) {
            e.printStackTrace();
            Platform.runLater(() -> showAlert(Alert.AlertType.ERROR, "Export Failed",
                    "Could not export user data: " + e.getMessage()));
        }
    }, Platform::runLater);
}

    
    public void viewUserDetails(QueryDocumentSnapshot user) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("User Details");
        alert.setHeaderText("User Information");
        
        StringBuilder details = new StringBuilder();
        details.append("Email: ").append(user.getString("email")).append("\n");
        details.append("Name: ").append(user.getString("name")).append("\n");
        
        Timestamp createdAt = user.getTimestamp("createdAt");
        if (createdAt != null) {
            details.append("Join Date: ").append(new SimpleDateFormat("dd MMM yyyy HH:mm").format(createdAt.toDate())).append("\n");
        }
        
        details.append("User ID: ").append(user.getId()).append("\n");
        details.append("Status: Active");
        
        alert.setContentText(details.toString());
        alert.showAndWait();
    }
    
    public void deleteUser(String userId, String userEmail) {
        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Delete User");
        confirmAlert.setHeaderText("Are you sure?");
        confirmAlert.setContentText("This will permanently delete the user '" + userEmail + "' and all their documents. This action cannot be undone.");
        
        Optional<ButtonType> result = confirmAlert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            Firestore db = FirestoreClient.getFirestore();
            
            
            db.collection("users").document(userId).delete()
                .addListener(() -> {
                    Platform.runLater(() -> {
                        showAlert(Alert.AlertType.INFORMATION, "Success", "User deleted successfully.");
                        loadAllUsers();
                        loadAllDocuments();
                        loadDashboardData();
                    });
                }, Platform::runLater);
        }
    }
    
    public void deleteDocument(String userId, String docId, String fileName) {
        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Delete Document");
        confirmAlert.setHeaderText("Are you sure?");
        confirmAlert.setContentText("This will permanently delete the document '" + fileName + "'. This action cannot be undone.");
        
        Optional<ButtonType> result = confirmAlert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            Firestore db = FirestoreClient.getFirestore();
            
            db.collection("users").document(userId).collection("documents").document(docId).delete()
                .addListener(() -> {
                    Platform.runLater(() -> {
                        showAlert(Alert.AlertType.INFORMATION, "Success", "Document deleted successfully.");
                        loadAllDocuments();
                        loadDashboardData();
                    });
                }, Platform::runLater);
        }
    }
    
    public void downloadDocument(String downloadUrl, String fileName) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Document");
        fileChooser.setInitialFileName(fileName);
        File file = fileChooser.showSaveDialog(adminStage);
        
        if (file != null) {
            showAlert(Alert.AlertType.INFORMATION, "Success", "Document download initiated.");
        }
    }
    
    public String formatFileSize(long bytes) {
        if (bytes < 1024) return bytes + " B";
        if (bytes < 1024 * 1024) return String.format("%.1f KB", bytes / 1024.0);
        if (bytes < 1024 * 1024 * 1024) return String.format("%.1f MB", bytes / (1024.0 * 1024));
        return String.format("%.1f GB", bytes / (1024.0 * 1024 * 1024));
    }
    
    public void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
