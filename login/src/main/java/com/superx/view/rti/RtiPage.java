package com.superx.view.rti;

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
import javafx.stage.Stage;
import javafx.application.HostServices;

public class RtiPage {
    Stage rtiStage;
    Scene rtiScene;
    private HostServices hostServices;

    public void setRtiStage(Stage rtiStage) {
        this.rtiStage = rtiStage;
    }

    public void setRtiScene(Scene rtiScene) {
        this.rtiScene = rtiScene;
    }

    public void setHostServices(HostServices hostServices) {
        this.hostServices = hostServices;
    }

    // UPDATED METHOD SIGNATURE - Added missing navigation parameters
    public BorderPane rtiPageBox(Runnable showProfileScreen, Runnable showLoginScreen, Runnable showlegal,
            Runnable showdoc, Runnable showland, Runnable showarchive, Runnable shownotificatio2,
            Runnable showFileRti, Runnable showGuidelines, Runnable showGrievance, Runnable showAboutUsScreen) {

        BorderPane mainbox = new BorderPane();
        mainbox.setStyle(
                "-fx-background-color: linear-gradient(from 0% 0% to 100% 100%, #e6f0ff, #d6e4ff); -fx-font-family: 'Inter', 'Segoe UI', sans-serif;");

        // SIDEBAR SECTION
        VBox sidebar = createSidebar(showProfileScreen, showlegal, showdoc, showland, showarchive, shownotificatio2, showAboutUsScreen);

        // MAIN CONTENT SECTION - Now passing RTI navigation callbacks
        VBox mainContent = createMainContent(showProfileScreen, showLoginScreen, showlegal, showdoc, showland, 
                showarchive, shownotificatio2, showFileRti, showGuidelines, showGrievance);

        mainbox.setLeft(sidebar);
        mainbox.setCenter(mainContent);
        return mainbox;
    }

    private VBox createSidebar(Runnable showProfileScreen, Runnable showlegal, Runnable showdoc, Runnable showland,
            Runnable showarchive, Runnable shownotificatio2, Runnable showAboutUsScreen) {
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
            showProfileScreen.run();
        });

        // Navigation Buttons
        VBox navButtons = createNavigationButtons(showlegal, showdoc, showland, showarchive, shownotificatio2, showAboutUsScreen);

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

        links.getChildren().addAll(contact, terms, privacy);

        sidebar.getChildren().addAll(sidebarTitle, pBox, navButtons, sidebarSpacer, links);
        return sidebar;
    }

    private VBox createNavigationButtons(Runnable showlegal, Runnable showdoc, Runnable showland, Runnable showarchive,
            Runnable shownotificatio2, Runnable showAboutUsScreen) {
        VBox navButtons = new VBox(10);

        HBox navBtn1 = createNavButton("📄", "Legal Case Management", false);
        navBtn1.setOnMouseClicked(evt -> {
            showlegal.run();
        });

        HBox navBtn2 = createNavButton("📜", "Document & Certificate", false);
        navBtn2.setOnMouseClicked(evt -> {
            showdoc.run();
        });

        HBox navBtn3 = createNavButton("🏠", "Land & Property Services", false);
        navBtn3.setOnMouseClicked(evt -> {
            showland.run();
        });

        HBox navBtn4 = createNavButton("⇄", "RTI & Grievance", true);

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

    // Updated method signature to include all navigation callbacks
    private VBox createMainContent(Runnable showProfileScreen, Runnable showLoginScreen, Runnable showlegal,
            Runnable showdoc, Runnable showland, Runnable showarchive, Runnable shownotificatio2,
            Runnable showFileRti, Runnable showGuidelines, Runnable showGrievance) {
        VBox mainContent = new VBox(25);
        mainContent.setPadding(new Insets(20, 40, 40, 40));
        mainContent.setStyle("-fx-background-color: transparent;");

        // Top Navigation
        HBox topNav = createTopNavigation(showProfileScreen, showLoginScreen, showdoc, showlegal, 
                showland, showarchive, shownotificatio2);

        // Main Title
        Label mainTitle = new Label("RTI & Grievance");
        mainTitle.setFont(Font.font("Inter", FontWeight.BOLD, 32));
        mainTitle.setStyle("-fx-text-fill: #1e3a8a; -fx-padding: 10px 0;");

        // Service Cards Grid - Now passing RTI navigation callbacks
        GridPane servicesGrid = createServicesGrid(showFileRti, showGuidelines, showGrievance);

        mainContent.getChildren().addAll(topNav, mainTitle, servicesGrid);
        return mainContent;
    }

    private HBox createTopNavigation(Runnable showProfileScreen, Runnable showLoginScreen, Runnable showdoc,
            Runnable showlegal, Runnable showland, Runnable showarchive, Runnable shownotificatio2) {
        HBox topNav = new HBox(30);
        topNav.setAlignment(Pos.CENTER_LEFT);

        HBox navLinks = new HBox(30);
        String topNavLinkStyle = "-fx-font-size: 15px; -fx-text-fill: #4b5563; -fx-font-weight: 500; -fx-cursor: hand;";
        String topNavLinkHoverStyle = "-fx-font-size: 15px; -fx-text-fill: #1d4ed8; -fx-font-weight: 500; -fx-cursor: hand;";

        // FIXED: Home navigation
        Label home = createTopNavLink("Home", topNavLinkStyle, topNavLinkHoverStyle);
        home.setOnMouseClicked(evt -> {
            showdoc.run();
        });
        
        Label services = createTopNavLink("Services", topNavLinkStyle, topNavLinkHoverStyle);
        
        Label rtiLink = createTopNavLink("RTI", topNavLinkStyle, topNavLinkHoverStyle);
        
        // FIXED: Legal Help navigation
        Label help = createTopNavLink("Legal Help", topNavLinkStyle, topNavLinkHoverStyle);
        help.setOnMouseClicked(evt -> {
            showlegal.run();
        });
        
        // FIXED: Resources navigation
        Label resourcesLink = createTopNavLink("Resources", topNavLinkStyle, topNavLinkHoverStyle);
        resourcesLink.setOnMouseClicked(evt -> {
            showarchive.run();
        });

        navLinks.getChildren().addAll(home, rtiLink, help, resourcesLink);

        Region topNavSpacer = new Region();
        HBox.setHgrow(topNavSpacer, Priority.ALWAYS);

        // Action Buttons
        Button notificationButton = new Button("🔔");
        notificationButton.setFont(Font.font("System", 14));
        notificationButton.setStyle(
                "-fx-background-color: transparent; -fx-border-color: #d1d5db; -fx-border-width: 1.5px; -fx-border-radius: 8px; -fx-background-radius: 8px; -fx-padding: 8px 20px;");
        notificationButton.setCursor(Cursor.HAND);
        notificationButton.setOnMouseEntered(e -> notificationButton.setStyle(
                "-fx-background-color: #f6f3f3; -fx-border-color: #d1d5db; -fx-border-width: 1.5px; -fx-border-radius: 8px; -fx-background-radius: 8px; -fx-padding: 8px 20px;"));
        notificationButton.setOnMouseExited(e -> notificationButton.setStyle(
                "-fx-background-color: transparent; -fx-border-color: #d1d5db; -fx-border-width: 1.5px; -fx-border-radius: 8px; -fx-background-radius: 8px; -fx-padding: 8px 20px;"));
        // FIXED: Notification button navigation
        notificationButton.setOnMouseClicked(evt -> {
            shownotificatio2.run();
        });

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

    // UPDATED METHOD WITH NAVIGATION CALLBACKS INSTEAD OF OPENING NEW WINDOWS
    private GridPane createServicesGrid(Runnable showFileRti, Runnable showGuidelines, Runnable showGrievance) {
        GridPane servicesGrid = new GridPane();
        servicesGrid.setVgap(20);
        servicesGrid.setHgap(20);
        servicesGrid.setPadding(new Insets(20, 0, 0, 0));

        // Create service cards with navigation callbacks
        VBox rtiFilingBox = createServiceCard("📄", "File RTI Application",
                "Submit right to information request to obtain information about certain type");
        rtiFilingBox.setOnMouseClicked(e -> showFileRti.run());

        VBox lodgeGrievanceBox = createServiceCard("📅", "Lodge Grievance",
                "Submit a grievance to raise concerns or complaints");
        lodgeGrievanceBox.setOnMouseClicked(e -> showGrievance.run());

        VBox trackStatusBox = createServiceCard("📌", "Track Status",
                "Check the status of your RTI applications");
        trackStatusBox.setOnMouseClicked(e -> showTrackStatus());

        VBox guidelinesBox = createServiceCard("📃", "Guidelines",
                "View instructions & guidelines for filing RTI & Grievance");
        guidelinesBox.setOnMouseClicked(e -> showGuidelines.run());

        // Add cards to grid (2x2 layout)
        servicesGrid.add(rtiFilingBox, 0, 0);
        servicesGrid.add(lodgeGrievanceBox, 1, 0);
        servicesGrid.add(trackStatusBox, 0, 1);
        servicesGrid.add(guidelinesBox, 1, 1);

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

        // Enhanced hover effects with click feedback
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

        // Click feedback
        serviceCard.setOnMousePressed(e -> {
            serviceCard.setStyle("-fx-background-color: rgba(255, 255, 255, 0.7); " +
                    "-fx-border-color: #1e40af; -fx-border-style: solid; " +
                    "-fx-border-width: 2; -fx-border-radius: 15; " +
                    "-fx-background-radius: 15; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 5, 0, 0, 2);");
        });

        serviceCard.setOnMouseReleased(e -> {
            serviceCard.setStyle("-fx-background-color: rgba(255, 255, 255, 0.95); " +
                    "-fx-border-color: #3b82f6; -fx-border-style: solid; " +
                    "-fx-border-width: 2; -fx-border-radius: 15; " +
                    "-fx-background-radius: 15; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 15, 0, 0, 5);");
        });

        return serviceCard;
    }

    // Keep Track Status as is since it shows an alert dialog
    private void showTrackStatus() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Track Status");
        alert.setHeaderText("Track RTI Application Status");
        alert.setContentText(
                "This feature will allow you to track your RTI application status.\n\nFeatures coming soon:\n• Application status tracking\n• Timeline view\n• Status notifications\n• Document downloads");
        alert.showAndWait();
    }
}
