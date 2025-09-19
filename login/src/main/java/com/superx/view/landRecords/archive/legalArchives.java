package com.superx.view.landRecords.archive;

import com.superx.Controller.ViewController;
import com.superx.view.AI.AIChatBotPane;
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

public class legalArchives {

    Stage legalStage;
    Scene legalScene;

    public void setLegalStage(Stage legalStage) {
        this.legalStage = legalStage;
    }

    public void setLegalScene(Scene legalScene) {
        this.legalScene = legalScene;
    }

    public BorderPane legalbox(Runnable showProfileScreen, Runnable showLoginScreen, Runnable showlegal,
            Runnable showdoc, Runnable showland, Runnable showrti, Runnable showCourtProcedures,
            Runnable showIntroToLaw, Runnable showRightRespons, Runnable showAboutUsScreen) {

        BorderPane mainbox = new BorderPane();
        mainbox.setStyle(
            "-fx-background-color: linear-gradient(from 0% 0% to 100% 100%, #e6f0ff, #d6e4ff); " +
            "-fx-font-family: 'Inter', 'Segoe UI', sans-serif;");

        // SIDEBAR
        VBox sidebar = createSidebar(showProfileScreen, showLoginScreen, showAboutUsScreen, 
                                   showlegal, showdoc, showland, showrti);

        // MAIN CONTENT with ScrollPane
        ScrollPane mainScrollPane = createMainScrollPane(showProfileScreen, showLoginScreen, 
                                                       showdoc, showrti, showlegal, showCourtProcedures, 
                                                       showIntroToLaw, showRightRespons);

        mainbox.setLeft(sidebar);
        mainbox.setCenter(mainScrollPane);

        return mainbox;
    }

    private VBox createSidebar(Runnable showProfileScreen, Runnable showLoginScreen, 
                              Runnable showAboutUsScreen, Runnable showlegal, Runnable showdoc, 
                              Runnable showland, Runnable showrti) {
        VBox sidebar = new VBox(20);
        sidebar.setPadding(new Insets(20, 15, 20, 15));
        sidebar.setStyle("-fx-background-color: #f5f9ff; -fx-pref-width: 280px;");
        sidebar.setAlignment(Pos.TOP_CENTER);

        Label sidebarTitle = new Label("e-help Desk");
        sidebarTitle.setFont(Font.font("Inter", FontWeight.BOLD, 24));
        sidebarTitle.setStyle("-fx-text-fill: #1e3a8a; -fx-padding: 10px 0 30px 0;");

        // Profile section
        Label profileIcon = new Label("👤");
        profileIcon.setFont(Font.font("System", 36));

        Label profileLabel = new Label("Profile");
        profileLabel.setFont(Font.font("Inter", FontWeight.SEMI_BOLD, 15));
        profileLabel.setStyle("-fx-text-fill: #4b5563;");

        HBox pBox = new HBox(15, profileIcon, profileLabel);
        pBox.setAlignment(Pos.CENTER_LEFT);
        pBox.setStyle("-fx-padding: 12px 15px; -fx-background-radius: 10px;");
        pBox.setCursor(Cursor.HAND);
        pBox.setOnMouseEntered(event -> pBox
                .setStyle("-fx-padding: 12px 15px; -fx-background-radius: 10px; -fx-background-color: #eef2ff;"));
        pBox.setOnMouseExited(event -> pBox
                .setStyle("-fx-padding: 12px 15px; -fx-background-radius: 10px; -fx-background-color: transparent;"));
        pBox.setOnMouseClicked(event -> showProfileScreen.run());

        VBox navButtons = createNavigationButtons(showlegal, showdoc, showland, showrti, showAboutUsScreen);

        Region sidebarSpacer = new Region();
        VBox.setVgrow(sidebarSpacer, Priority.ALWAYS);

        HBox links = createFooterLinks();

        sidebar.getChildren().addAll(sidebarTitle, pBox, navButtons, sidebarSpacer, links);
        return sidebar;
    }

    private VBox createNavigationButtons(Runnable showlegal, Runnable showdoc, Runnable showland, 
                                       Runnable showrti, Runnable showAboutUsScreen) {
        VBox navButtons = new VBox(10);

        HBox navBtn1 = createNavButton("📄", "Legal Case Management", "#4b5563", showlegal);
        HBox navBtn2 = createNavButton("📜", "Document & Certificate", "#4b5563", showdoc);
        HBox navBtn3 = createNavButton("🏠", "Land & Property Services", "#4b5563", showland);
        HBox navBtn4 = createNavButton("⇄", "RTI & Grievance", "#4b5563", showrti);

        // Highlighted current page
        HBox navBtn5 = new HBox(15, new Label("📚"), new Label("Legal Knowledge Base"));
        navBtn5.getChildren().get(0).setStyle("-fx-font-size: 24px;");
        navBtn5.getChildren().get(1).setStyle("-fx-font-size: 15px; -fx-font-weight: 600; -fx-text-fill: #3b82f6;");
        navBtn5.setAlignment(Pos.CENTER_LEFT);
        navBtn5.setStyle("-fx-padding: 12px 15px; -fx-background-radius: 10px; -fx-background-color: #e0e7ff;");
        navBtn5.setCursor(Cursor.HAND);

        HBox navBtn6 = createNavButton("ℹ️", "About Us", "#4b5563", showAboutUsScreen);

        navButtons.getChildren().addAll(navBtn1, navBtn2, navBtn3, navBtn4, navBtn5, navBtn6);
        return navButtons;
    }

    private HBox createNavButton(String icon, String text, String color, Runnable action) {
        HBox navBtn = new HBox(15, new Label(icon), new Label(text));
        navBtn.getChildren().get(0).setStyle("-fx-font-size: 24px;");
        navBtn.getChildren().get(1).setStyle("-fx-font-size: 15px; -fx-font-weight: 500; -fx-text-fill: " + color + ";");
        navBtn.setAlignment(Pos.CENTER_LEFT);
        navBtn.setStyle("-fx-padding: 12px 15px; -fx-background-radius: 10px; -fx-background-color: transparent;");
        navBtn.setCursor(Cursor.HAND);
        navBtn.setOnMouseEntered(event -> navBtn
                .setStyle("-fx-padding: 12px 15px; -fx-background-radius: 10px; -fx-background-color: #eef2ff;"));
        navBtn.setOnMouseExited(event -> navBtn
                .setStyle("-fx-padding: 12px 15px; -fx-background-radius: 10px; -fx-background-color: transparent;"));
        navBtn.setOnMouseClicked(evt -> action.run());
        return navBtn;
    }

    private HBox createFooterLinks() {
        HBox links = new HBox(15);
        links.setAlignment(Pos.CENTER);

        Label contact = new Label("Contact");
        Label terms = new Label("Terms");
        Label privacy = new Label("Privacy");

        String footerStyle = "-fx-font-size: 13px; -fx-text-fill: #6b7280; -fx-cursor: hand;";
        contact.setStyle(footerStyle);
        terms.setStyle(footerStyle);
        privacy.setStyle(footerStyle);

        contact.setCursor(Cursor.HAND);
        terms.setCursor(Cursor.HAND);
        privacy.setCursor(Cursor.HAND);

        links.getChildren().addAll(contact, terms, privacy);
        return links;
    }

    private ScrollPane createMainScrollPane(Runnable showProfileScreen, Runnable showLoginScreen, 
                                          Runnable showdoc, Runnable showrti, Runnable showlegal, 
                                          Runnable showCourtProcedures, Runnable showIntroToLaw, 
                                          Runnable showRightRespons) {
        VBox mainContent = createMainContent(showProfileScreen, showLoginScreen, showdoc, showrti, 
                                           showlegal, showCourtProcedures, showIntroToLaw, showRightRespons);

        ScrollPane scrollPane = new ScrollPane(mainContent);
        scrollPane.setFitToWidth(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");

        return scrollPane;
    }

    private VBox createMainContent(Runnable showProfileScreen, Runnable showLoginScreen, Runnable showdoc,
                                  Runnable showrti, Runnable showlegal, Runnable showCourtProcedures,
                                  Runnable showIntroToLaw, Runnable showRightRespons) {
        VBox mainContent = new VBox(30);
        mainContent.setPadding(new Insets(20, 40, 40, 40));
        mainContent.setStyle("-fx-background-color: transparent;");

        HBox topNav = createTopNavigation(showdoc, showrti, showlegal, showLoginScreen);

        // Enhanced page title section
        VBox titleSection = new VBox(8);
        Label mainTitle = new Label("Legal Knowledge Base");
        mainTitle.setFont(Font.font("Inter", FontWeight.BOLD, 32));
        mainTitle.setStyle("-fx-text-fill: #1e3a8a; -fx-padding: 10px 0;");
        
        Label subtitle = new Label("Get instant legal guidance and explore comprehensive legal resources");
        subtitle.setFont(Font.font("Inter", FontWeight.MEDIUM, 16));
        subtitle.setStyle("-fx-text-fill: #6b7280;");
        
        titleSection.getChildren().addAll(mainTitle, subtitle);

        VBox content = createContentSection(showCourtProcedures, showIntroToLaw, showRightRespons);

        mainContent.getChildren().addAll(topNav, titleSection, content);
        return mainContent;
    }

    private HBox createTopNavigation(Runnable showdoc, Runnable showrti, Runnable showlegal, Runnable showLoginScreen) {
        HBox topNav = new HBox(30);
        topNav.setAlignment(Pos.CENTER_LEFT);

        HBox navLinks = new HBox(30);
        String topNavLinkStyle = "-fx-font-size: 15px; -fx-text-fill: #4b5563; -fx-font-weight: 500; -fx-cursor: hand;";
        String topNavLinkHoverStyle = "-fx-font-size: 15px; -fx-text-fill: #1d4ed8; -fx-font-weight: 500; -fx-cursor: hand;";

        Label home = createTopNavLink("Home", topNavLinkStyle, topNavLinkHoverStyle, showdoc);
        Label services = createTopNavLink("Services", topNavLinkStyle, topNavLinkHoverStyle, null);
        Label rtiLink = createTopNavLink("RTI", topNavLinkStyle, topNavLinkHoverStyle, showrti);
        Label help = createTopNavLink("Legal Help", topNavLinkStyle, topNavLinkHoverStyle, showlegal);
        Label resourcesLink = createTopNavLink("Resources", topNavLinkStyle, topNavLinkHoverStyle, null);

        navLinks.getChildren().addAll(home, rtiLink, help, resourcesLink);

        Region topNavSpacer = new Region();
        HBox.setHgrow(topNavSpacer, Priority.ALWAYS);

        HBox loginButtons = createTopNavButtons(showLoginScreen);

        topNav.getChildren().addAll(navLinks, topNavSpacer, loginButtons);
        return topNav;
    }

    private Label createTopNavLink(String text, String normalStyle, String hoverStyle, Runnable action) {
        Label link = new Label(text);
        link.setStyle(normalStyle);
        link.setCursor(Cursor.HAND);
        link.setOnMouseEntered(event -> link.setStyle(hoverStyle));
        link.setOnMouseExited(event -> link.setStyle(normalStyle));
        if (action != null) {
            link.setOnMouseClicked(evt -> action.run());
        }
        return link;
    }

    private HBox createTopNavButtons(Runnable showLoginScreen) {
        Button notificationButton = new Button("🔔");
        notificationButton.setFont(Font.font("System", 14));
        notificationButton.setStyle(
            "-fx-background-color: transparent; " +
            "-fx-border-color: #d1d5db; " +
            "-fx-border-width: 1.5px; " +
            "-fx-border-radius: 8px; " +
            "-fx-background-radius: 8px; " +
            "-fx-padding: 8px 20px;");
        notificationButton.setCursor(Cursor.HAND);

        notificationButton.setOnMouseEntered(event -> notificationButton.setStyle(
            "-fx-background-color: #f6f3f3; " +
            "-fx-border-color: #d1d5db; " +
            "-fx-border-width: 1.5px; " +
            "-fx-border-radius: 8px; " +
            "-fx-background-radius: 8px; " +
            "-fx-padding: 8px 20px;"));

        notificationButton.setOnMouseExited(event -> notificationButton.setStyle(
            "-fx-background-color: transparent; " +
            "-fx-border-color: #d1d5db; " +
            "-fx-border-width: 1.5px; " +
            "-fx-border-radius: 8px; " +
            "-fx-background-radius: 8px; " +
            "-fx-padding: 8px 20px;"));

        Button logoutButton = new Button("LogOut");
        logoutButton.setFont(Font.font("Inter", FontWeight.BOLD, 14));
        logoutButton.setStyle(
            "-fx-background-color: #f63b3b; " +
            "-fx-background-radius: 8px; " +
            "-fx-text-fill: white; " +
            "-fx-padding: 8px 20px;");
        logoutButton.setCursor(Cursor.HAND);

        logoutButton.setOnMouseEntered(event -> logoutButton.setStyle(
            "-fx-background-color: #eb2525; " +
            "-fx-background-radius: 8px; " +
            "-fx-text-fill: white; " +
            "-fx-padding: 8px 20px;"));

        logoutButton.setOnMouseExited(event -> logoutButton.setStyle(
            "-fx-background-color: #f63b3b; " +
            "-fx-background-radius: 8px; " +
            "-fx-text-fill: white; " +
            "-fx-padding: 8px 20px;"));

        logoutButton.setOnAction(event -> showLoginScreen.run());

        HBox loginButtons = new HBox(10, notificationButton, logoutButton);
        loginButtons.setAlignment(Pos.CENTER);
        return loginButtons;
    }

    private VBox createContentSection(Runnable showCourtProcedures, Runnable showIntroToLaw, Runnable showRightRespons) {
        VBox content = new VBox(40);
        content.setPadding(new Insets(20, 0, 0, 0));

        // Enhanced AI Assistant section - Made bigger
        VBox aiSection = createAIAssistantSection();
        
        // Categories section
        VBox categoriesSection = createCategoriesSection(showCourtProcedures, showIntroToLaw, showRightRespons);

        content.getChildren().addAll(aiSection, categoriesSection);
        return content;
    }

    private VBox createAIAssistantSection() {
        VBox aiSection = new VBox(20);
        
        // Section header
        HBox aiHeader = new HBox(15);
        aiHeader.setAlignment(Pos.CENTER_LEFT);
        
        Label aiIcon = new Label("🤖");
        aiIcon.setFont(Font.font("System", 28));
        
        Label aiTitle = new Label("AI Legal Assistant");
        aiTitle.setFont(Font.font("Inter", FontWeight.BOLD, 24));
        aiTitle.setStyle("-fx-text-fill: #1e3a8a;");
        
        Label betaLabel = new Label("BETA");
        betaLabel.setFont(Font.font("Inter", FontWeight.BOLD, 11));
        betaLabel.setStyle("-fx-background-color: #10b981; " +
                          "-fx-text-fill: white; " +
                          "-fx-padding: 4 10; " +
                          "-fx-background-radius: 12;");
        
        aiHeader.getChildren().addAll(aiIcon, aiTitle, betaLabel);
        
        Label aiDescription = new Label("Get instant answers to your legal questions with our AI-powered assistant. " +
                                       "Ask about laws, procedures, rights, and get personalized legal guidance.");
        aiDescription.setFont(Font.font("Inter", FontWeight.MEDIUM, 15));
        aiDescription.setStyle("-fx-text-fill: #6b7280;");
        aiDescription.setWrapText(true);

        // Make AI chatbot bigger
        AIChatBotPane aiAssistant = new AIChatBotPane(
            ViewController.getCurrentUserId() != null ? ViewController.getCurrentUserId() : "guest");
        aiAssistant.setPrefHeight(600); // Increased height

        aiSection.getChildren().addAll(aiHeader, aiDescription, aiAssistant);
        return aiSection;
    }

    private VBox createCategoriesSection(Runnable showCourtProcedures, Runnable showIntroToLaw, Runnable showRightRespons) {
        VBox categoriesSection = new VBox(25);
        
        Label categoriesTitle = new Label("📖 Legal Categories");
        categoriesTitle.setFont(Font.font("Inter", FontWeight.BOLD, 24));
        categoriesTitle.setStyle("-fx-text-fill: #1e3a8a;");

        Label categoriesDesc = new Label("Explore comprehensive legal resources organized by category");
        categoriesDesc.setFont(Font.font("Inter", FontWeight.MEDIUM, 15));
        categoriesDesc.setStyle("-fx-text-fill: #6b7280;");

        GridPane categoriesGrid = new GridPane();
        categoriesGrid.setVgap(30);
        categoriesGrid.setHgap(30);
        categoriesGrid.setPadding(new Insets(20, 0, 0, 0));

        // Enhanced category cards with better text visibility
        VBox constitutionalCard = createCategoryCard(
            "", "⚖️ Introduction to Law", "#6366f1",
            "Learn about fundamental rights, directive principles, and constitutional amendments. Understanding the supreme law of the land.",
            "• Constitution Basics\n• Fundamental Rights\n• Legal Framework\n• Judicial System",
            showIntroToLaw);

        VBox courtCard = createCategoryCard(
            "", "🏛️ Court Procedures", "#059669",
            "Step-by-step guide to court proceedings, filing procedures, and legal documentation required for various cases.",
            "• Filing Procedures\n• Court Hierarchy\n• Legal Documentation\n• Case Management",
            showCourtProcedures);

        VBox rightsCard = createCategoryCard(
            "", "👥 Rights & Responsibilities", "#dc2626",
            "Comprehensive guide to citizen rights and responsibilities, including consumer rights, privacy laws, and civic duties.",
            "• Citizen Rights\n• Consumer Protection\n• Privacy Laws\n• Civic Duties",
            showRightRespons);

        categoriesGrid.add(constitutionalCard, 0, 0);
        categoriesGrid.add(courtCard, 1, 0);
        categoriesGrid.add(rightsCard, 0, 1);

        categoriesSection.getChildren().addAll(categoriesTitle, categoriesDesc, categoriesGrid);
        return categoriesSection;
    }

    private VBox createCategoryCard(String icon, String title, String accentColor, String description, 
                                   String topics, Runnable action) {
        VBox card = new VBox(18);
        card.setStyle(
            "-fx-background-color: rgba(255, 255, 255, 0.95); " +
            "-fx-border-color: #e5e7eb; " +
            "-fx-border-style: solid; " +
            "-fx-border-width: 2; " +
            "-fx-border-radius: 20; " +
            "-fx-background-radius: 20; " +
            "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.08), 12, 0, 0, 3);");
        card.setPadding(new Insets(32));
        card.setPrefHeight(320);
        card.setPrefWidth(380);
        card.setCursor(Cursor.HAND);

        Label cardIcon = new Label(icon);
        cardIcon.setFont(Font.font("System", 42));

        Label cardTitle = new Label(title);
        cardTitle.setFont(Font.font("Inter", FontWeight.BOLD, 20));
        cardTitle.setStyle("-fx-text-fill: #1e3a8a;");

        HBox titleBox = new HBox(18, cardIcon, cardTitle);
        titleBox.setAlignment(Pos.CENTER_LEFT);

        Label cardDesc = new Label(description);
        cardDesc.setFont(Font.font("Inter", FontWeight.NORMAL, 14));
        cardDesc.setStyle("-fx-text-fill: #374151;"); // Better text visibility
        cardDesc.setWrapText(true);
        cardDesc.setPrefHeight(90);

        Label cardTopics = new Label(topics);
        cardTopics.setFont(Font.font("Inter", FontWeight.SEMI_BOLD, 13));
        cardTopics.setStyle("-fx-text-fill: " + accentColor + ";");

        card.getChildren().addAll(titleBox, cardDesc, cardTopics);

        // Enhanced hover effects
        card.setOnMouseEntered(e -> {
            card.setStyle(
                "-fx-background-color: rgba(255, 255, 255, 1.0); " +
                "-fx-border-color: #3b82f6; " +
                "-fx-border-style: solid; " +
                "-fx-border-width: 2; " +
                "-fx-border-radius: 20; " +
                "-fx-background-radius: 20; " +
                "-fx-effect: dropshadow(gaussian, rgba(59, 130, 246, 0.25), 20, 0, 0, 8);");
        });

        card.setOnMouseExited(e -> {
            card.setStyle(
                "-fx-background-color: rgba(255, 255, 255, 0.95); " +
                "-fx-border-color: #e5e7eb; " +
                "-fx-border-style: solid; " +
                "-fx-border-width: 2; " +
                "-fx-border-radius: 20; " +
                "-fx-background-radius: 20; " +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.08), 12, 0, 0, 3);");
        });

        card.setOnMouseClicked(e -> action.run());

        return card;
    }
}
