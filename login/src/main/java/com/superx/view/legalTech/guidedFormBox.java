package com.superx.view.legalTech;

import javafx.application.HostServices;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class guidedFormBox {
    private Stage guidedFormStage;
    private Scene guidedFormScene;
    private HostServices hostServices;

    public void setHostServices(HostServices hostServices) {
        this.hostServices = hostServices;
    }

    public void setGuidedFormStage(Stage guidedFormStage) {
        this.guidedFormStage = guidedFormStage;
    }

    public void setGuidedFormScene(Scene guidedFormScene) {
        this.guidedFormScene = guidedFormScene;
    }

    public BorderPane rtiPageBox(Runnable showProfileScreen, Runnable showLoginScreen, Runnable showdoc,Runnable showlegal) {
        BorderPane mainbox = new BorderPane();
        mainbox.setStyle(
                "-fx-background-color: linear-gradient(from 0% 0% to 100% 100%, #e6f0ff, #d6e4ff); -fx-font-family: 'Inter', 'Segoe UI', sans-serif;");

        // SIDEBAR SECTION
        VBox sidebar = createSidebar(showdoc,showlegal);

        // MAIN CONTENT SECTION
        VBox mainContent = createMainContent(showProfileScreen, showLoginScreen, showdoc,showlegal);

        mainbox.setLeft(sidebar);
        mainbox.setCenter(mainContent);

        return mainbox;
    }

    private VBox createSidebar(Runnable showdoc,Runnable showlegal) {
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

        // Navigation Buttons
        VBox navButtons = createNavigationButtons(showdoc,showlegal);

        Region sidebarSpacer = new Region();
        VBox.setVgrow(sidebarSpacer, Priority.ALWAYS);

        // Footer Links
        HBox links = createFooterLinks();

        sidebar.getChildren().addAll(sidebarTitle, pBox, navButtons, sidebarSpacer, links);
        return sidebar;
    }

    private VBox createNavigationButtons(Runnable showdoc,Runnable showlegal) {
        VBox navButtons = new VBox(10);

        HBox navBtn1 = createNavButton("📄", "Legal Case Management", true);
        navBtn1.setOnMouseClicked(evt->{
                showlegal.run();
        });
        HBox navBtn2 = createNavButton("📜", "Document & Certificate", false);

        // Add navigation functionality
        navBtn2.setOnMouseClicked(evt -> showdoc.run());

        HBox navBtn3 = createNavButton("🏠", "Land & Property Services", false); // Active
        HBox navBtn4 = createNavButton("⇄", "RTI & Grievance", false);
        HBox navBtn5 = createNavButton("📚", "Legal Knowledge Base", false);

        navButtons.getChildren().addAll(navBtn1, navBtn2, navBtn3, navBtn4, navBtn5);
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

        // Add click handlers
        contact.setOnMouseClicked(e -> openWebsite("mailto:contact@ehelpdesk.gov.in"));
        terms.setOnMouseClicked(e -> openWebsite("https://ehelpdesk.gov.in/terms"));
        privacy.setOnMouseClicked(e -> openWebsite("https://ehelpdesk.gov.in/privacy"));

        links.getChildren().addAll(contact, terms, privacy);
        return links;
    }

    private VBox createMainContent(Runnable showProfileScreen, Runnable showLoginScreen, Runnable showdoc,Runnable showlegal) {
        VBox mainContent = new VBox(25);
        mainContent.setPadding(new Insets(20, 40, 40, 40));
        mainContent.setStyle("-fx-background-color: transparent;");

        // Top Navigation
        HBox topNav = createTopNavigation(showProfileScreen, showLoginScreen, showdoc);

        // Main Title
        Label mainTitle = new Label("Government Scheme Guidelines & Forms");
        mainTitle.setFont(Font.font("Inter", FontWeight.BOLD, 32));
        mainTitle.setStyle("-fx-text-fill: #1e3a8a; -fx-padding: 10px 0;");

        // Schemes Content
        VBox schemesContent = createGovCard("Government Schemes");
        ScrollPane scrollPane = new ScrollPane(schemesContent);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background-color: transparent; -fx-background: transparent;");

        mainContent.getChildren().addAll(topNav, mainTitle, scrollPane);
        return mainContent;
    }

    private HBox createTopNavigation(Runnable showProfileScreen, Runnable showLoginScreen, Runnable showdoc) {
        HBox topNav = new HBox(30);
        topNav.setAlignment(Pos.CENTER_LEFT);

        HBox navLinks = new HBox(30);
        String topNavLinkStyle = "-fx-font-size: 15px; -fx-text-fill: #4b5563; -fx-font-weight: 500; -fx-cursor: hand;";
        String topNavLinkHoverStyle = "-fx-font-size: 15px; -fx-text-fill: #1d4ed8; -fx-font-weight: 500; -fx-cursor: hand;";

        Label home = createTopNavLink("Home", topNavLinkStyle, topNavLinkHoverStyle);
        home.setOnMouseClicked(e -> showdoc.run());

        Label services = createTopNavLink("Services", topNavLinkStyle, topNavLinkHoverStyle);
        Label rtiLink = createTopNavLink("RTI", topNavLinkStyle, topNavLinkHoverStyle);
        Label help = createTopNavLink("Legal Help", topNavLinkStyle, topNavLinkHoverStyle);
        Label resourcesLink = createTopNavLink("Resources", topNavLinkStyle, topNavLinkHoverStyle);

        navLinks.getChildren().addAll(home, rtiLink, help, resourcesLink);

        Region topNavSpacer = new Region();
        HBox.setHgrow(topNavSpacer, Priority.ALWAYS);

        // Action Buttons
        Button notificationButton = createActionButton("🔔");
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

    public VBox createGovCard(String title) {
        VBox cardBox = new VBox(20);
        cardBox.setPadding(new Insets(30));
        cardBox.setAlignment(Pos.TOP_LEFT);
        cardBox.setStyle(
                "-fx-background-color: rgba(255, 255, 255, 0.9); -fx-background-radius: 15; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 10, 0, 0, 2);");

        // Header
        Text headerText = new Text("Government Scheme Guidelines & Application Forms");
        headerText.setFont(Font.font("Inter", FontWeight.BOLD, 20));
        headerText.setStyle("-fx-fill: #1e3a8a;");

        // Scheme categories
        TitledPane[] schemePanes = {
                createSchemeCategory("PMAY - Urban (Housing for All)",
                        createLink("PMAY‑U 2.0 Operational Guidelines",
                                "https://pmay-urban.gov.in/uploads/guidelines/Operational-Guidelines-of-PMAY-U-2.pdf"),
                        createLink("PMAY‑U User Manual (PLI)",
                                "https://pmaymis.gov.in/PMAYMIS2_2024/PDF/UserManual/PLI-USER-MANUAL_27122024.pdf"),
                        createLink("PMAY‑Urban Financial Norms",
                                "https://mohua.gov.in/upload/uploadfiles/files/1Financial_norms_CBA_booklet.pdf")),

                createSchemeCategory("PMAY - Gramin (Rural Housing)",
                        createLink("PMAY‑G Implementation Framework",
                                "https://rdpri.andaman.gov.in/admin-pannel/achievements/25-Framework%20For%20Implementation%20FFIof%20PMAYG.pdf")),

                createSchemeCategory("PMFME - Food Processing",
                        createLink("PMFME Scheme Guidelines",
                                "https://pmfme.mofpi.gov.in/newsletters/docs/SchemeGuidelines.pdf")),

                createSchemeCategory("Startup India Seed Fund",
                        createLink("Seed Fund Scheme Guidelines",
                                "https://www.startupindia.gov.in/content/dam/invest-india/Templates/public/Guidelines%20for%20Startup%20India%20Seed%20Fund%20Scheme.pdf")),

                createSchemeCategory("Operation Greens",
                        createLink("Operation Greens Guidelines",
                                "https://www.mofpi.gov.in/sites/default/files/2._operational_guidelines_dated_25.05.2022_0.pdf")),

                createSchemeCategory("Social Justice Schemes",
                        createLink("Social Justice Scheme Guidelines",
                                "https://socialjustice.gov.in/public/ckeditor/upload/66671696827061.pdf")),

                createSchemeCategory("Medical Device Parks",
                        createLink("Promotion of Medical Device Parks",
                                "https://pharma-dept.gov.in/sites/default/files/Guidelines%20of%20the%20Scheme%20Promotion%20of%20Medical%20Devices%20Parks_1.pdf")),

                createSchemeCategory("PM Jan Dhan Yojana (PMJDY)",
                        createLink("PMJDY Scheme Guide", "https://pmjdy.gov.in/files/QuickLinks/guide.pdf"),
                        createLink("Continuation Guidelines",
                                "https://pmjdy.gov.in/files/E-Documents/Continuation_of_PMJDY.pdf"),
                        createLink("PMJDY FAQ (SLBC)", "https://slbcne.nic.in/NE/Pmjdy%20FAQ.pdf")),

                createSchemeCategory("PM Kaushal Vikas Yojana (PMKVY)",
                        createLink("PMKVY 4.0 Guidelines (2024)",
                                "https://www.msde.gov.in/static/uploads/2024/02/PMKVY-4.0-Guidelines_final-copy.pdf"),
                        createLink("PMKVY 3.0 Guidelines",
                                "https://dsde.goa.gov.in/wp-content/uploads/2021/02/PMKY-Guideline_report_V3.pdf"),
                        createLink("Monitoring Guidelines (3.0)",
                                "https://www.msde.gov.in/static/uploads/2024/04/Monitoring-Guidelines_PMKVY-3.0.pdf"),
                        createLink("Branding Guidelines (3.0)",
                                "https://www.msde.gov.in/static/uploads/2024/04/Branding-and-Communication-Guidelines-and-Social-Media-Guidelines-for-PMKVY-3.0.pdf"))
        };

        VBox allPanes = new VBox(15);
        allPanes.getChildren().addAll(schemePanes);

        cardBox.getChildren().addAll(headerText, allPanes);
        return cardBox;
    }

    public TitledPane createSchemeCategory(String title, Hyperlink... links) {
        VBox box = new VBox(10);
        box.setPadding(new Insets(15));
        box.getChildren().addAll(links);

        TitledPane pane = new TitledPane(title, box);
        pane.setExpanded(false);
        pane.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

        return pane;
    }

    public Hyperlink createLink(String text, String url) {
        Hyperlink link = new Hyperlink(text);
        link.setFont(Font.font("Inter", 14));
        link.setStyle("-fx-text-fill: #2563eb; -fx-underline: false;");
        link.setOnAction(e -> openWebsite(url));

        // Hover effects
        link.setOnMouseEntered(e -> {
            link.setStyle("-fx-text-fill: #1d4ed8; -fx-underline: true;");
        });
        link.setOnMouseExited(e -> {
            link.setStyle("-fx-text-fill: #2563eb; -fx-underline: false;");
        });

        return link;
    }

    private void openWebsite(String url) {
        try {
            if (hostServices != null) {
                hostServices.showDocument(url);
                System.out.println("Opening URL: " + url);
            } else {
                System.err.println("HostServices not available. Cannot open: " + url);
            }
        } catch (Exception e) {
            System.err.println("Failed to open website: " + url);
            e.printStackTrace();
        }
    }
}
