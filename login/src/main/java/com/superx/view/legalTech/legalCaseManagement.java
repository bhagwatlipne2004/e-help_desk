package com.superx.view.legalTech;

import javafx.application.HostServices;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
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
import javafx.stage.Stage;

public class legalCaseManagement {

    Stage legalCaseStage;
    Scene legalCaseScene;
    private HostServices hostServices;

    public void setHostServices(HostServices hostServices) {
        this.hostServices = hostServices;
    }

    public void setLegalCaseStage(Stage legalCaseStage) {
        this.legalCaseStage = legalCaseStage;
    }

    public void setLegalCaseScene(Scene legalCaseScene) {
        this.legalCaseScene = legalCaseScene;
    }

    // UPDATED METHOD SIGNATURE - Added missing navigation parameters
    public BorderPane legalmanabox(Runnable showprofile, Runnable showdoc, Runnable shoeland, Runnable showrti,
            Runnable showarchive, Runnable showgovoff, Runnable showGuidedForm, Runnable showAboutUsScreen, 
            Runnable showNotification, Runnable showLoginScreen) {
        BorderPane mainbox = new BorderPane();
        mainbox.setStyle(
                "-fx-background-color: linear-gradient(from 0% 0% to 100% 100%, #e6f0ff, #d6e4ff); -fx-font-family: 'Inter', 'Segoe UI', sans-serif;");

        // SIDEBAR SECTION
        VBox sidebar = createSidebar(showprofile, showdoc, shoeland, showrti, showarchive, showgovoff, showGuidedForm, showAboutUsScreen);

        // MAIN CONTENT SECTION
        VBox mainContent = createMainContent(showdoc, showgovoff, showGuidedForm, showrti, showarchive, shoeland, showNotification, showLoginScreen);

        mainbox.setLeft(sidebar);
        mainbox.setCenter(mainContent);

        return mainbox;
    }

    private VBox createSidebar(Runnable showprofile, Runnable showdoc, Runnable shoeland, Runnable showrti,
            Runnable showarchive, Runnable showgovoff, Runnable showGuidedForm, Runnable showAboutUsScreen) {
        VBox sidebar = new VBox(20);
        sidebar.setPadding(new Insets(20, 15, 20, 15));
        sidebar.setStyle("-fx-background-color: #f5f9ff; -fx-pref-width: 280px;");
        sidebar.setAlignment(Pos.TOP_CENTER);

        Label sidebarTitle = new Label("e-help Desk");
        sidebarTitle.setFont(Font.font("Inter", FontWeight.BOLD, 24));
        sidebarTitle.setStyle("-fx-text-fill: #1e3a8a; -fx-padding: 10px 0 30px 0;");

        // Profile Section
        Label profileIcon = new Label("👤");
        profileIcon.setFont(Font.font("System", 36));

        Label profileLabel = new Label("Profile");
        profileLabel.setFont(Font.font("Inter", FontWeight.SEMI_BOLD, 15));
        profileLabel.setStyle("-fx-text-fill: #4b5563;");

        HBox pBox = new HBox(15, profileIcon, profileLabel);
        pBox.setAlignment(Pos.CENTER_LEFT);
        pBox.setStyle("-fx-padding: 12px 15px; -fx-background-radius: 10px;");
        pBox.setCursor(Cursor.HAND);
        pBox.setOnMouseEntered(e -> pBox
                .setStyle("-fx-padding: 12px 15px; -fx-background-radius: 10px; -fx-background-color: #eef2ff;"));
        pBox.setOnMouseExited(e -> pBox
                .setStyle("-fx-padding: 12px 15px; -fx-background-radius: 10px; -fx-background-color: transparent;"));
        pBox.setOnMouseClicked(evt -> {
            showprofile.run();
        });
        
        // Navigation Buttons
        VBox navButtons = createNavigationButtons(showprofile, showdoc, shoeland, showrti, showarchive, showgovoff,
                showGuidedForm, showAboutUsScreen);

        Region sidebarSpacer = new Region();
        VBox.setVgrow(sidebarSpacer, Priority.ALWAYS);

        // Footer Links
        HBox links = new HBox(15);
        links.setAlignment(Pos.CENTER);
        Label contact = new Label("Contact");
        Label terms = new Label("Terms");  
        Label privacy = new Label("Privacy");
        String footerStyle = "-fx-font-size: 13px; -fx-text-fill: #6b7280; -fx-cursor: hand;";
        contact.setStyle(footerStyle);
        terms.setStyle(footerStyle);
        privacy.setStyle(footerStyle);

        // Add click handlers for footer links
        contact.setOnMouseClicked(e -> openWebsite("mailto:contact@ehelpdesk.gov.in"));
        terms.setOnMouseClicked(e -> openWebsite("https://ehelpdesk.gov.in/terms"));
        privacy.setOnMouseClicked(e -> openWebsite("https://ehelpdesk.gov.in/privacy"));

        links.getChildren().addAll(contact, terms, privacy);

        sidebar.getChildren().addAll(sidebarTitle, pBox, navButtons, sidebarSpacer, links);
        return sidebar;
    }

    private VBox createNavigationButtons(Runnable showprofile, Runnable showdoc, Runnable shoeland, Runnable showrti,
            Runnable showarchive, Runnable showgovoff, Runnable showGuidedForm, Runnable showAboutUsScreen) {
        VBox navButtons = new VBox(10);

        HBox navBtn1 = createNavButton("📄", "Legal Case Management", true);
        HBox navBtn2 = createNavButton("📜", "Document & Certificate", false);

        // Add navigation functionality
        navBtn2.setOnMouseClicked(evt -> showdoc.run());

        HBox navBtn3 = createNavButton("🏠", "Land & Property Services", false);
        navBtn3.setOnMouseClicked(evt -> {
            shoeland.run();
        });
        
        HBox navBtn4 = createNavButton("⇄", "RTI & Grievance", false);
        navBtn4.setOnMouseClicked(evt -> {
            showrti.run();
        });
        
        HBox navBtn5 = createNavButton("📚", "Legal Knowledge Base", false);
        navBtn5.setOnMouseClicked(evt -> {
            showarchive.run();
        });
        
        // FIXED: About Us navigation
        HBox navBtn6 = createNavButton("ℹ️", "About Us", false);
        navBtn6.setOnMouseClicked(evt -> {
            showAboutUsScreen.run();
        });

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
            navBtn.setOnMouseEntered(e -> navBtn
                    .setStyle("-fx-padding: 12px 15px; -fx-background-radius: 10px; -fx-background-color: #eef2ff;"));
            navBtn.setOnMouseExited(e -> navBtn.setStyle(
                    "-fx-padding: 12px 15px; -fx-background-radius: 10px; -fx-background-color: transparent;"));
        }

        return navBtn;
    }

    private VBox createMainContent(Runnable showdoc, Runnable showgovoff, Runnable showGuidedForm, 
            Runnable showrti, Runnable showarchive, Runnable shoeland, Runnable showNotification, Runnable showLoginScreen) {
        VBox mainContent = new VBox(25);
        mainContent.setPadding(new Insets(20, 40, 40, 40));
        mainContent.setStyle("-fx-background-color: transparent;");

        // Top Navigation
        HBox topNav = createTopNavigation(showdoc, showrti, showarchive, showNotification, showLoginScreen);

        // Main Title
        Label mainTitle = new Label("Legal Case Management");
        mainTitle.setFont(Font.font("Inter", FontWeight.BOLD, 32));
        mainTitle.setStyle("-fx-text-fill: #1e3a8a; -fx-padding: 10px 0;");

        // Service Cards Grid
        GridPane servicesGrid = createServicesGrid(showdoc, showgovoff, showGuidedForm);

        mainContent.getChildren().addAll(topNav, mainTitle, servicesGrid);
        return mainContent;
    }

    private HBox createTopNavigation(Runnable showdoc, Runnable showrti, Runnable showarchive, 
            Runnable showNotification, Runnable showLoginScreen) {
        HBox topNav = new HBox(30);
        topNav.setAlignment(Pos.CENTER_LEFT);

        HBox navLinks = new HBox(30);
        String topNavLinkStyle = "-fx-font-size: 15px; -fx-text-fill: #4b5563; -fx-font-weight: 500; -fx-cursor: hand;";
        String topNavLinkHoverStyle = "-fx-font-size: 15px; -fx-text-fill: #1d4ed8; -fx-font-weight: 500; -fx-cursor: hand;";

        Label home = createTopNavLink("Home", topNavLinkStyle, topNavLinkHoverStyle);
        home.setOnMouseClicked(e -> showdoc.run());

        Label services = createTopNavLink("Services", topNavLinkStyle, topNavLinkHoverStyle);
        
        // FIXED: RTI navigation
        Label rtiLink = createTopNavLink("RTI", topNavLinkStyle, topNavLinkHoverStyle);
        rtiLink.setOnMouseClicked(e -> showrti.run());
        
        Label help = createTopNavLink("Legal Help", topNavLinkStyle, topNavLinkHoverStyle);
        
        // FIXED: Resources navigation
        Label resourcesLink = createTopNavLink("Resources", topNavLinkStyle, topNavLinkHoverStyle);
        resourcesLink.setOnMouseClicked(e -> showarchive.run());

        navLinks.getChildren().addAll(home, rtiLink, help, resourcesLink);

        Region topNavSpacer = new Region();
        HBox.setHgrow(topNavSpacer, Priority.ALWAYS);

        // Action Buttons
        Button notificationButton = createActionButton("🔔");
        // FIXED: Notification button navigation
        notificationButton.setOnMouseClicked(e -> showNotification.run());
        
        Button logoutButton = createLogoutButton(showLoginScreen);

        HBox actionButtons = new HBox(10, notificationButton, logoutButton);
        actionButtons.setAlignment(Pos.CENTER);

        topNav.getChildren().addAll(navLinks, topNavSpacer, actionButtons);
        return topNav;
    }

    private Button createActionButton(String text) {
        Button button = new Button(text);
        button.setFont(Font.font("System", 14));
        button.setStyle(
                "-fx-background-color: transparent; -fx-border-color: #d1d5db; -fx-border-width: 1.5px; -fx-border-radius: 8px; -fx-background-radius: 8px; -fx-padding: 8px 20px;");
        button.setCursor(Cursor.HAND);
        button.setOnMouseEntered(e -> button.setStyle(
                "-fx-background-color: #f6f3f3; -fx-border-color: #d1d5db; -fx-border-width: 1.5px; -fx-border-radius: 8px; -fx-background-radius: 8px; -fx-padding: 8px 20px;"));
        button.setOnMouseExited(e -> button.setStyle(
                "-fx-background-color: transparent; -fx-border-color: #d1d5db; -fx-border-width: 1.5px; -fx-border-radius: 8px; -fx-background-radius: 8px; -fx-padding: 8px 20px;"));
        return button;
    }

    private Button createLogoutButton(Runnable showLoginScreen) {
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
        return logoutButton;
    }

    private Label createTopNavLink(String text, String normalStyle, String hoverStyle) {
        Label link = new Label(text);
        link.setStyle(normalStyle);
        link.setOnMouseEntered(e -> link.setStyle(hoverStyle));
        link.setOnMouseExited(e -> link.setStyle(normalStyle));
        return link;
    }

    private GridPane createServicesGrid(Runnable showdoc, Runnable showgovoff, Runnable showGuidedForm) {
        GridPane servicesGrid = new GridPane();
        servicesGrid.setVgap(20);
        servicesGrid.setHgap(20);
        servicesGrid.setPadding(new Insets(20, 0, 0, 0));

        // Create service cards with navigation
        VBox guidedFormBox = createServiceCard("📄", "Guided Forms",
                "Access government scheme application forms with step-by-step guidance",
                showGuidedForm);

        VBox storageBox = createServiceCard("📃", "Digital Certificate Verification",
                "Verify and validate digital certificates and documents",
                () -> openWebsite("https://digitallocker.gov.in/"));

        VBox certificateBox = createServiceCard("☁", "Upload & Storage",
                "Securely upload and store your legal documents in the cloud", showdoc);

        VBox govOffBox = createServiceCard("🌐", "Government Official Websites",
                "Access official government portals and services",
                showgovoff);

        // Add cards to grid
        servicesGrid.add(guidedFormBox, 0, 0);
        servicesGrid.add(storageBox, 1, 0);
        servicesGrid.add(certificateBox, 0, 1);
        servicesGrid.add(govOffBox, 1, 1);

        return servicesGrid;
    }

    private VBox createServiceCard(String icon, String title, String description, Runnable action) {
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

        // Icon and Title
        Label iconLabel = new Label(icon);
        iconLabel.setFont(Font.font("System", 40));

        Label titleLabel = new Label(title);
        titleLabel.setFont(Font.font("Inter", FontWeight.BOLD, 20));
        titleLabel.setStyle("-fx-text-fill: #1e3a8a;");

        HBox titleBox = new HBox(15, iconLabel, titleLabel);
        titleBox.setAlignment(Pos.CENTER_LEFT);

        // Description
        Label descriptionLabel = new Label(description);
        descriptionLabel.setFont(Font.font("Inter", 14));
        descriptionLabel.setStyle("-fx-text-fill: #4b5563;");
        descriptionLabel.setWrapText(true);

        serviceCard.getChildren().addAll(titleBox, descriptionLabel);

        // Click action
        serviceCard.setOnMouseClicked(e -> action.run());

        // Hover effects
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

    private void openWebsite(String url) {
        try {
            if (hostServices != null) {
                hostServices.showDocument(url);
            } else {
                System.err.println("HostServices not available. Cannot open: " + url);
            }
        } catch (Exception e) {
            System.err.println("Failed to open website: " + url);
            e.printStackTrace();
        }
    }
}
