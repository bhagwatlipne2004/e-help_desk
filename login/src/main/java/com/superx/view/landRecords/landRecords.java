package com.superx.view.landRecords;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class landRecords {
    private Stage landStage;
    private Scene landScene;
    private VBox propertyCalculatorPanel;
    private VBox titleDeedPanel;       // New panel for Title Deeds
    private BorderPane mainbox;

    // Setters (if needed)
    public void setLandStage(Stage landStage) {
        this.landStage = landStage;
    }
    public void setLandScene(Scene landScene) {
        this.landScene = landScene;
    }

    public BorderPane landRecordsBox(
            Runnable showProfileScreen, Runnable showLoginScreen, Runnable showlegal,
            Runnable showdoc, Runnable showRTI, Runnable showarchive, Runnable showLRecords,
            Runnable showPropertyReg, Runnable showAboutUsScreen, Runnable shownotif
    ) {
        mainbox = new BorderPane();
        mainbox.setStyle(
                "-fx-background-color: linear-gradient(from 0% 0% to 100% 100%, #e6f0ff, #d6e4ff); -fx-font-family: 'Inter', 'Segoe UI', sans-serif;");

        VBox sidebar = createSidebar(showProfileScreen, showlegal, showdoc, showRTI, showarchive, showAboutUsScreen);

        // create main content area
        VBox mainContent = createMainContent(showProfileScreen, showLoginScreen, showlegal, showdoc, showRTI,
                showarchive, showLRecords, showPropertyReg, shownotif);

        // Property calculator panel (initially hidden)
        propertyCalculatorPanel = createPropertyCalculatorPanel();
        propertyCalculatorPanel.setVisible(false);

        // Title Deeds Application panel (initially hidden)
        titleDeedPanel = createTitleDeedApplicationPanel();
        titleDeedPanel.setPadding(new Insets(50,50,50,50));
        titleDeedPanel.setVisible(false);
        titleDeedPanel.setAlignment(Pos.CENTER);

        // Horizontal box holds mainContent + propertyCalculatorPanel + titleDeedPanel
        HBox hb = new HBox(20, mainContent, propertyCalculatorPanel);
        VBox vb = new VBox(20,titleDeedPanel);
        HBox hb1 = new HBox(20,hb,vb);   
        mainbox.setLeft(sidebar);
        mainbox.setCenter(hb1);
        return mainbox;
    }


    private VBox createSidebar(Runnable showProfileScreen, Runnable showlegal, Runnable showdoc, Runnable showRTI,
                              Runnable showarchive, Runnable showAboutUsScreen) {
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
        pBox.setCursor(Cursor.HAND);
        pBox.setOnMouseEntered(e -> pBox.setStyle("-fx-padding: 12px 15px; -fx-background-radius: 10px; -fx-background-color: #eef2ff;"));
        pBox.setOnMouseExited(e -> pBox.setStyle("-fx-padding: 12px 15px; -fx-background-radius: 10px; -fx-background-color: transparent;"));
        pBox.setOnMouseClicked(evt -> showProfileScreen.run());

        VBox navButtons = createNavigationButtons(showlegal, showdoc, showRTI, showarchive, showAboutUsScreen);

        Region sidebarSpacer = new Region();
        VBox.setVgrow(sidebarSpacer, Priority.ALWAYS);

        HBox links = new HBox(15);
        links.setAlignment(Pos.CENTER);
        Label contact = new Label("Contact");
        Label terms = new Label("Terms");
        Label privacy = new Label("Privacy");
        String footerStyle = "-fx-font-size: 13px; -fx-text-fill: #6b7280; -fx-cursor: hand;";
        contact.setStyle(footerStyle);
        terms.setStyle(footerStyle);
        privacy.setStyle(footerStyle);
        links.getChildren().addAll(contact, terms, privacy);

        sidebar.getChildren().addAll(sidebarTitle, pBox, navButtons, sidebarSpacer, links);
        return sidebar;
    }

    private VBox createNavigationButtons(Runnable showlegal, Runnable showdoc, Runnable showRTI, Runnable showarchive,
                                         Runnable showAboutUsScreen) {
        VBox navButtons = new VBox(10);

        HBox navBtn1 = createNavButton("📄", "Legal Case Management", false);
        navBtn1.setOnMouseClicked(evt -> showlegal.run());

        HBox navBtn2 = createNavButton("📜", "Document & Certificate", false);
        navBtn2.setOnMouseClicked(evt -> showdoc.run());

        HBox navBtn3 = createNavButton("🏠", "Land & Property Services", true);

        HBox navBtn4 = createNavButton("⇄", "RTI & Grievance", false);
        navBtn4.setOnMouseClicked(evt -> showRTI.run());

        HBox navBtn5 = createNavButton("📚", "Legal Knowledge Base", false);
        navBtn5.setOnMouseClicked(evt -> showarchive.run());

        HBox navBtn6 = createNavButton("ℹ️", "About Us", false);
        navBtn6.setOnMouseClicked(evt -> showAboutUsScreen.run());

        navButtons.getChildren().addAll(navBtn1, navBtn2, navBtn3, navBtn4, navBtn5, navBtn6);
        return navButtons;
    }

    private HBox createNavButton(String icon, String text, boolean isActive) {
        HBox navBtn = new HBox(15, new Label(icon), new Label(text));
        navBtn.getChildren().get(0).setStyle("-fx-font-size: 24px;");
        String textStyle = isActive ? "-fx-font-size: 15px; -fx-font-weight: 600; -fx-text-fill: #3b82f6;"
                : "-fx-font-size: 15px; -fx-font-weight: 500; -fx-text-fill: #4b5563;";
        navBtn.getChildren().get(1).setStyle(textStyle);
        navBtn.setAlignment(Pos.CENTER_LEFT);
        String bgStyle = isActive
                ? "-fx-padding: 12px 15px; -fx-background-radius: 10px; -fx-background-color: #e0e7ff;"
                : "-fx-padding: 12px 15px; -fx-background-radius: 10px; -fx-background-color: transparent;";
        navBtn.setStyle(bgStyle);
        navBtn.setCursor(Cursor.HAND);

        if (!isActive) {
            navBtn.setOnMouseEntered(e -> navBtn.setStyle("-fx-padding: 12px 15px; -fx-background-radius: 10px; -fx-background-color: #eef2ff;"));
            navBtn.setOnMouseExited(e -> navBtn.setStyle("-fx-padding: 12px 15px; -fx-background-radius: 10px; -fx-background-color: transparent;"));
        }
        return navBtn;
    }

    private VBox createMainContent(Runnable showProfileScreen, Runnable showLoginScreen, Runnable showlegal,
                                   Runnable showdoc, Runnable showRTI, Runnable showarchive, Runnable showLRecords,
                                   Runnable showPropertyReg, Runnable shownotif) {
        VBox mainContent = new VBox(25);
        mainContent.setPadding(new Insets(20, 40, 40, 40));
        mainContent.setStyle("-fx-background-color: transparent;");

        HBox topNav = createTopNavigation(showProfileScreen, showLoginScreen, showlegal, showdoc, showRTI, showarchive, shownotif);

        Label mainTitle = new Label("Land and Property Services");
        mainTitle.setFont(Font.font("Inter", FontWeight.BOLD, 32));
        mainTitle.setStyle("-fx-text-fill: #1e3a8a; -fx-padding: 10px 0;");

        GridPane servicesGrid = createServicesGrid(showLRecords, showPropertyReg);

        mainContent.getChildren().addAll(topNav, mainTitle, servicesGrid);
        return mainContent;
    }

    private HBox createTopNavigation(Runnable showProfileScreen, Runnable showLoginScreen, Runnable showlegal,
                                     Runnable showdoc, Runnable showRTI, Runnable showarchive, Runnable shownotif) {
        HBox topNav = new HBox(30);
        topNav.setAlignment(Pos.CENTER_LEFT);

        HBox navLinks = new HBox(30);
        String topNavLinkStyle = "-fx-font-size: 15px; -fx-text-fill: #4b5563; -fx-font-weight: 500; -fx-cursor: hand;";
        String topNavLinkHoverStyle = "-fx-font-size: 15px; -fx-text-fill: #1d4ed8; -fx-font-weight: 500; -fx-cursor: hand;";

        Label home = createTopNavLink("Home", topNavLinkStyle, topNavLinkHoverStyle);
        home.setOnMouseClicked(et -> showdoc.run());

        Label services = new Label("Services");
        services.setStyle(topNavLinkStyle);
        services.setOnMouseEntered(event -> services.setStyle(topNavLinkHoverStyle));
        services.setOnMouseExited(event -> services.setStyle(topNavLinkStyle));

        Label rtiLink = new Label("RTI");
        rtiLink.setStyle(topNavLinkStyle);
        rtiLink.setOnMouseEntered(event -> rtiLink.setStyle(topNavLinkHoverStyle));
        rtiLink.setOnMouseExited(event -> rtiLink.setStyle(topNavLinkStyle));
        rtiLink.setOnMouseClicked(evt -> showRTI.run());

        Label help = new Label("Legal Help");
        help.setStyle(topNavLinkStyle);
        help.setOnMouseEntered(event -> help.setStyle(topNavLinkHoverStyle));
        help.setOnMouseExited(event -> help.setStyle(topNavLinkStyle));
        help.setOnMouseClicked(evt -> showlegal.run());

        Label resourcesLink = new Label("Resources");
        resourcesLink.setStyle(topNavLinkStyle);
        resourcesLink.setOnMouseEntered(event -> resourcesLink.setStyle(topNavLinkHoverStyle));
        resourcesLink.setOnMouseExited(event -> resourcesLink.setStyle(topNavLinkStyle));
        resourcesLink.setOnMouseClicked(evt -> showarchive.run());

        navLinks.getChildren().addAll(home, rtiLink, help, resourcesLink);

        Region topNavSpacer = new Region();
        HBox.setHgrow(topNavSpacer, Priority.ALWAYS);

        Button notificationButton = new Button("🔔");
        notificationButton.setFont(Font.font("System", 14));
        notificationButton.setStyle(
                "-fx-background-color: transparent; -fx-border-color: #d1d5db; -fx-border-width: 1.5px; -fx-border-radius: 8px; -fx-background-radius: 8px; -fx-padding: 8px 20px;");
        notificationButton.setCursor(Cursor.HAND);
        notificationButton.setOnMouseEntered(e -> notificationButton.setStyle(
                "-fx-background-color: #f6f3f3; -fx-border-color: #d1d5db; -fx-border-width: 1.5px; -fx-border-radius: 8px; -fx-background-radius: 8px; -fx-padding: 8px 20px;"));
        notificationButton.setOnMouseExited(e -> notificationButton.setStyle(
                "-fx-background-color: transparent; -fx-border-color: #d1d5db; -fx-border-width: 1.5px; -fx-border-radius: 8px; -fx-background-radius: 8px; -fx-padding: 8px 20px;"));
        notificationButton.setOnMouseClicked(evt -> shownotif.run());

        Button logoutButton = new Button("LogOut");
        logoutButton.setFont(Font.font("Inter", FontWeight.BOLD, 14));
        logoutButton.setStyle(
                "-fx-background-color: #f63b3b; -fx-background-radius: 8px; -fx-text-fill: white; -fx-padding: 8px 20px;");
        logoutButton.setCursor(Cursor.HAND);
        logoutButton.setOnMouseEntered(e -> logoutButton.setStyle(
                "-fx-background-color: #eb2525; -fx-background-radius: 8px; -fx-text-fill: white; -fx-padding: 8px 20px;"));
        logoutButton.setOnMouseExited(e -> logoutButton.setStyle(
                "-fx-background-color: #f63b3b; -fx-background-radius: 8px; -fx-text-fill: white; -fx-padding: 8px 20px;"));
        logoutButton.setOnAction(event -> showLoginScreen.run());

        HBox actionButtons = new HBox(10, notificationButton, logoutButton);
        actionButtons.setAlignment(Pos.CENTER);

        topNav.getChildren().addAll(navLinks, topNavSpacer, actionButtons);
        return topNav;
    }

    private Label createTopNavLink(String text, String normalStyle, String hoverStyle) {
        Label link = new Label(text);
        link.setStyle(normalStyle);
        link.setOnMouseEntered(e -> link.setStyle(hoverStyle));
        link.setOnMouseExited(e -> link.setStyle(normalStyle));
        return link;
    }


    private GridPane createServicesGrid(Runnable showLRecords, Runnable showPropertyReg) {
        GridPane servicesGrid = new GridPane();
        servicesGrid.setVgap(50);
        servicesGrid.setHgap(100);
        servicesGrid.setPadding(new Insets(20, 0, 0, 0));

        // Property Registration Card
        VBox propertyRegistration = createServiceCard("🌐", "Property Registration",
                "Information & online services for property registrations");
        propertyRegistration.setOnMouseClicked(e -> showPropertyReg.run());

        // Property Calculation Card
        VBox propertyCalculation = createServiceCard("📅", "Property Calculation",
                "Calculate market value of your land and property");
        propertyCalculation.setOnMouseClicked(e -> {
            // Hide Title Deed panel if open
            titleDeedPanel.setVisible(false);
            // Toggle property calculator
            propertyCalculatorPanel.setVisible(!propertyCalculatorPanel.isVisible());
        });

        // Land Records Search Card
        VBox landRecordsSearch = createServiceCard("🔍", "Land Records Search",
                "Access land records & ownership details");
        landRecordsSearch.setOnMouseClicked(e -> showLRecords.run());

        // Title Deeds Card - Show right side panel
        VBox titleDeeds = createServiceCard("↗️", "Title Deeds Application",
                "Submit an online application for Title Deeds");
        // titleDeeds.setOnMouseClicked(e -> {
        //     // Hide property calculator if open
        //     propertyCalculatorPanel.setVisible(false);
        //     // Show Title Deeds application on right
        //     titleDeedPanel.setVisible(true);
        // });

        servicesGrid.add(propertyRegistration, 0, 0);
        servicesGrid.add(propertyCalculation, 1, 0);
        servicesGrid.add(landRecordsSearch, 0, 1);
      

        return servicesGrid;
    }

    private VBox createServiceCard(String icon, String title, String description) {
        VBox serviceCard = new VBox(15);
        serviceCard.setAlignment(Pos.CENTER_LEFT);
        serviceCard.setStyle("-fx-background-color: rgba(255, 255, 255, 0.8); " +
                "-fx-border-color: #a0b3d7; -fx-border-style: solid; " +
                "-fx-border-width: 1.5; -fx-border-radius: 15; " +
                "-fx-background-radius: 15; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 10, 0, 0, 2);");
        serviceCard.setPadding(new Insets(30));
        serviceCard.setPrefHeight(200);
        serviceCard.setPrefWidth(400);
        serviceCard.setCursor(Cursor.HAND);

        Label iconLabel = new Label(icon);
        iconLabel.setFont(Font.font("System", 40));

        Label titleLabel = new Label(title);
        titleLabel.setFont(Font.font("Inter", FontWeight.BOLD, 20));
        titleLabel.setStyle("-fx-text-fill: #1e3a8a;");

        HBox titleBox = new HBox(15, iconLabel, titleLabel);
        titleBox.setAlignment(Pos.CENTER_LEFT);

        Label descriptionLabel = new Label(description);
        descriptionLabel.setFont(Font.font("Inter", 14));
        descriptionLabel.setStyle("-fx-text-fill: #4b5563;");
        descriptionLabel.setWrapText(true);

        serviceCard.getChildren().addAll(titleBox, descriptionLabel);

        serviceCard.setOnMouseEntered(e -> {
            serviceCard.setStyle("-fx-background-color: rgba(255, 255, 255, 0.95); " +
                    "-fx-border-color: #3b82f6; -fx-border-style: solid; " +
                    "-fx-border-width: 2; -fx-border-radius: 15; " +
                    "-fx-background-radius: 15; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 15, 0, 0, 5);");
        });
        serviceCard.setOnMouseExited(e -> {
            serviceCard.setStyle("-fx-background-color: rgba(255, 255, 255, 0.8); " +
                    "-fx-border-color: #a0b3d7; -fx-border-style: solid; " +
                    "-fx-border-width: 1.5; -fx-border-radius: 15; " +
                    "-fx-background-radius: 15; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 10, 0, 0, 2);");
        });

        return serviceCard;
    }

    private VBox createPropertyCalculatorPanel() {
        VBox calculatorPanel = new VBox(15);
        calculatorPanel.setPadding(new Insets(20));
        calculatorPanel.setStyle("-fx-background-color: rgba(255, 255, 255, 0.95); " +
                "-fx-border-color: #3b82f6; -fx-border-style: solid; " +
                "-fx-border-width: 2; -fx-border-radius: 15; " +
                "-fx-background-radius: 15; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 10, 0, 0, 2);");
        calculatorPanel.setPrefWidth(350);
        calculatorPanel.setMaxHeight(600);

        // Header with close button
        HBox header = new HBox();
        header.setAlignment(Pos.CENTER_LEFT);
        Label headerTitle = new Label("Property Calculator");
        headerTitle.setFont(Font.font("Inter", FontWeight.BOLD, 20));
        headerTitle.setStyle("-fx-text-fill: #1e3a8a;");

        Region headerSpacer = new Region();
        HBox.setHgrow(headerSpacer, Priority.ALWAYS);

        Button closeBtn = new Button("✖");
        closeBtn.setStyle("-fx-background-color: transparent; -fx-text-fill: #666; -fx-font-size: 14px; -fx-cursor: hand;");
        closeBtn.setOnAction(e -> calculatorPanel.setVisible(false));

        header.getChildren().addAll(headerTitle, headerSpacer, closeBtn);

        // Input fields and other controls here (same as your existing code)...
        // For brevity, omitted here but you can reuse existing code

        // --- Placeholder for inputGrid and calculateBtn ---
        GridPane inputGrid = new GridPane();
        inputGrid.setVgap(10);
        inputGrid.setHgap(10);
        inputGrid.setAlignment(Pos.CENTER_LEFT);

        // Area input
        Label areaLabel = new Label("Area (sq ft):");
        areaLabel.setFont(Font.font("Inter", FontWeight.SEMI_BOLD, 14));
        TextField areaField = new TextField();
        areaField.setPromptText("Enter area in square feet");
        styleTextField(areaField);

        // Location factor combo box
        Label locationLabel = new Label("Location Factor:");
        locationLabel.setFont(Font.font("Inter", FontWeight.SEMI_BOLD, 14));
        ComboBox<String> locationCombo = new ComboBox<>();
        locationCombo.getItems().addAll("Prime Location (1.5x)", "Good Location (1.2x)", "Average Location (1.0x)", "Remote Location (0.8x)");
        locationCombo.setValue("Average Location (1.0x)");
        styleComboBox(locationCombo);

        // Base price input
        Label basePriceLabel = new Label("Base Price (₹/sq ft):");
        basePriceLabel.setFont(Font.font("Inter", FontWeight.SEMI_BOLD, 14));
        TextField basePriceField = new TextField();
        basePriceField.setPromptText("Enter base price per sq ft");
        basePriceField.setText("5000"); // default value
        styleTextField(basePriceField);

        // Property type combo box
        Label typeLabel = new Label("Property Type:");
        typeLabel.setFont(Font.font("Inter", FontWeight.SEMI_BOLD, 14));
        ComboBox<String> typeCombo = new ComboBox<>();
        typeCombo.getItems().addAll("Residential (1.0x)", "Commercial (1.3x)", "Industrial (0.9x)", "Agricultural (0.6x)");
        typeCombo.setValue("Residential (1.0x)");
        styleComboBox(typeCombo);

        // Age combo box
        Label ageLabel = new Label("Property Age:");
        ageLabel.setFont(Font.font("Inter", FontWeight.SEMI_BOLD, 14));
        ComboBox<String> ageCombo = new ComboBox<>();
        ageCombo.getItems().addAll("New (1.0x)", "1-5 years (0.95x)", "6-10 years (0.9x)", "11-20 years (0.8x)", "20+ years (0.7x)");
        ageCombo.setValue("New (1.0x)");
        styleComboBox(ageCombo);

        inputGrid.add(areaLabel, 0, 0);
        inputGrid.add(areaField, 0, 1);
        inputGrid.add(basePriceLabel, 0, 2);
        inputGrid.add(basePriceField, 0, 3);
        inputGrid.add(locationLabel, 0, 4);
        inputGrid.add(locationCombo, 0, 5);
        inputGrid.add(typeLabel, 0, 6);
        inputGrid.add(typeCombo, 0, 7);
        inputGrid.add(ageLabel, 0, 8);
        inputGrid.add(ageCombo, 0, 9);

        Button calculateBtn = new Button("Calculate Property Value");
        calculateBtn.setFont(Font.font("Inter", FontWeight.BOLD, 14));
        calculateBtn.setStyle("-fx-background-color: #3b82f6; -fx-background-radius: 8px; -fx-text-fill: white; -fx-padding: 12px 20px; -fx-cursor: hand;");
        calculateBtn.setOnMouseEntered(e -> calculateBtn.setStyle("-fx-background-color: #2563eb; -fx-background-radius: 8px; -fx-text-fill: white; -fx-padding: 12px 20px; -fx-cursor: hand;"));
        calculateBtn.setOnMouseExited(e -> calculateBtn.setStyle("-fx-background-color: #3b82f6; -fx-background-radius: 8px; -fx-text-fill: white; -fx-padding: 12px 20px; -fx-cursor: hand;"));
        calculateBtn.setMaxWidth(Double.MAX_VALUE);

        VBox resultBox = new VBox(10);
        resultBox.setStyle("-fx-background-color: #f0f9ff; -fx-padding: 15px; -fx-background-radius: 8px; -fx-border-color: #3b82f6; -fx-border-radius: 8px;");

        Label resultTitle = new Label("Calculated Value:");
        resultTitle.setFont(Font.font("Inter", FontWeight.BOLD, 16));
        resultTitle.setStyle("-fx-text-fill: #1e3a8a;");
        Label resultValue = new Label("₹ 0");
        resultValue.setFont(Font.font("Inter", FontWeight.BOLD, 24));
        resultValue.setStyle("-fx-text-fill: #059669;");
        Label breakdown = new Label("");
        breakdown.setFont(Font.font("Inter", 12));
        breakdown.setStyle("-fx-text-fill: #4b5563;");
        breakdown.setWrapText(true);

        resultBox.getChildren().addAll(resultTitle, resultValue, breakdown);

        calculateBtn.setOnAction(e -> {
            try {
                double area = Double.parseDouble(areaField.getText());
                double basePrice = Double.parseDouble(basePriceField.getText());
                double locationFactor = extractMultiplier(locationCombo.getValue());
                double typeFactor = extractMultiplier(typeCombo.getValue());
                double ageFactor = extractMultiplier(ageCombo.getValue());
                double totalValue = area * basePrice * locationFactor * typeFactor * ageFactor;
                resultValue.setText("₹ " + String.format("%,.2f", totalValue));
                breakdown.setText(String.format("Calculation: %.0f sq ft × ₹%.0f × %.2fx × %.2fx × %.2fx = ₹%.2f",
                        area, basePrice, locationFactor, typeFactor, ageFactor, totalValue));
            } catch (NumberFormatException ex) {
                resultValue.setText("Invalid Input");
                breakdown.setText("Please enter valid numbers for area and base price.");
            }
        });

        calculatorPanel.getChildren().addAll(header, inputGrid, calculateBtn, resultBox);
        return calculatorPanel;
    }

    private void styleTextField(TextField field) {
        field.setStyle("-fx-background-color: white; -fx-border-color: #d1d5db; " +
                "-fx-border-radius: 6px; -fx-background-radius: 6px; -fx-padding: 8px;");
        field.setFont(Font.font("Inter", 14));
    }

    private void styleComboBox(ComboBox<String> combo) {
        combo.setStyle("-fx-background-color: white; -fx-border-color: #d1d5db; " +
                "-fx-border-radius: 6px; -fx-background-radius: 6px;");
        combo.setMaxWidth(Double.MAX_VALUE);
    }

    private double extractMultiplier(String selection) {
        if (selection.contains("1.5x"))
            return 1.5;
        if (selection.contains("1.3x"))
            return 1.3;
        if (selection.contains("1.2x"))
            return 1.2;
        if (selection.contains("1.0x"))
            return 1.0;
        if (selection.contains("0.95x"))
            return 0.95;
        if (selection.contains("0.9x"))
            return 0.9;
        if (selection.contains("0.8x"))
            return 0.8;
        if (selection.contains("0.7x"))
            return 0.7;
        if (selection.contains("0.6x"))
            return 0.6;
        return 1.0; // Default
    }

    // New Title Deeds Application panel shown at right
    public VBox createTitleDeedApplicationPanel() {
        VBox deedPanel = new VBox(20);
        deedPanel.setPadding(new Insets(20));
        deedPanel.setStyle(
                "-fx-background-color: rgba(255,255,255,0.97); " +
                "-fx-border-color: #3b82f6; -fx-border-width: 2; " +
                "-fx-border-radius: 15; -fx-background-radius: 15;" +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.12), 15, 0, 0, 3);");
        deedPanel.setPrefWidth(350);
        deedPanel.setMaxHeight(600);
        
        

        // Header with close button
        HBox header = new HBox();
        header.setAlignment(Pos.CENTER_LEFT);

        Label title = new Label("Title Deeds Application");
        title.setFont(Font.font("Inter", FontWeight.BOLD, 24));
        title.setStyle("-fx-text-fill: #1e3a8a;");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Button closeBtn = new Button("✖");
        closeBtn.setStyle("-fx-background-color: transparent; -fx-text-fill: #666; -fx-font-size: 16px; -fx-cursor: hand;");
        closeBtn.setOnAction(e -> deedPanel.setVisible(false));

        header.getChildren().addAll(title, spacer, closeBtn);

        // Applicant Name field
        Label nameLabel = new Label("Applicant Name:");
        nameLabel.setFont(Font.font("Inter", FontWeight.SEMI_BOLD, 15));
        TextField nameField = new TextField();
        nameField.setPromptText("Enter your full name");
        styleTextField(nameField);

        // Property ID / Details field
        Label propertyLabel = new Label("Property ID / Details:");
        propertyLabel.setFont(Font.font("Inter", FontWeight.SEMI_BOLD, 15));
        TextField propertyField = new TextField();
        propertyField.setPromptText("Enter property identification details");
        styleTextField(propertyField);

        // Upload Button (simulate upload)
        Label uploadLabel = new Label("Upload Ownership Proof (PDF/JPG):");
        uploadLabel.setFont(Font.font("Inter", FontWeight.SEMI_BOLD, 15));
        Button uploadBtn = new Button("Choose File");
        uploadBtn.setFont(Font.font("Inter", 13));
        uploadBtn.setStyle("-fx-background-color: #e0e7ff; -fx-background-radius: 7; " +
                "-fx-border-color: #a0b3d7; -fx-border-radius: 7; -fx-padding: 7px 15px;");
        uploadBtn.setCursor(Cursor.HAND);
        // You can add file chooser logic if needed here

        // Status label
        Label status = new Label("");
        status.setFont(Font.font("Inter", 13));

        // Submit Button
        Button submitBtn = new Button("Submit Application");
        submitBtn.setFont(Font.font("Inter", FontWeight.BOLD, 15));
        submitBtn.setStyle("-fx-background-color: #3b82f6; -fx-background-radius: 8px; -fx-text-fill: white; -fx-padding: 12px 20px;");
        submitBtn.setCursor(Cursor.HAND);
        submitBtn.setOnMouseEntered(e -> submitBtn.setStyle("-fx-background-color: #2563eb; -fx-background-radius: 8px; -fx-text-fill: white; -fx-padding: 12px 20px;"));
        submitBtn.setOnMouseExited(e -> submitBtn.setStyle("-fx-background-color: #3b82f6; -fx-background-radius: 8px; -fx-text-fill: white; -fx-padding: 12px 20px;"));

        submitBtn.setOnAction(e -> {
            if (nameField.getText().trim().isEmpty() || propertyField.getText().trim().isEmpty()) {
                status.setText("Please complete all required fields.");
                status.setStyle("-fx-text-fill: #f63b3b;");
            } else {
                status.setText("Application submitted successfully!");
                status.setStyle("-fx-text-fill: #059669;");
            }
        });

        deedPanel.getChildren().addAll(
                header,
                nameLabel, nameField,
                propertyLabel, propertyField,
                uploadLabel, uploadBtn,
                submitBtn,
                status
        );

        return deedPanel;
    }
}
