package com.superx.view.Notification;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Notificationtab {

    Stage noti2Stage;
    Scene noti2Scene;

    public void setNoti2Stage(Stage notiStage) {
        this.noti2Stage = notiStage;
    }

    public void setNoti2Scene(Scene notiScene) {
        this.noti2Scene = notiScene;
    }

    public BorderPane notificationPage(Runnable showdoc) {
        BorderPane mainbox = new BorderPane();
        mainbox.setStyle(
                "-fx-background-color: linear-gradient(from 0% 0% to 100% 100%, #e6f0ff, #d6e4ff); -fx-font-family: 'Inter', 'Segoe UI', sans-serif;");

        // SIDEBAR SECTION
        VBox sidebar = createSidebar(showdoc);

        // MAIN CONTENT SECTION
        VBox mainContent = createMainContent(showdoc);

        mainbox.setLeft(sidebar);
        mainbox.setCenter(mainContent);

        return mainbox;
    }

    private VBox createSidebar(Runnable showdoc) {
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
        pBox.setOnMouseEntered(event -> pBox
                .setStyle("-fx-padding: 12px 15px; -fx-background-radius: 10px; -fx-background-color: #eef2ff;"));
        pBox.setOnMouseExited(event -> pBox
                .setStyle("-fx-padding: 12px 15px; -fx-background-radius: 10px; -fx-background-color: transparent;"));

        // Navigation Buttons
        VBox navButtons = createNavigationButtons(showdoc);

        Region sidebarSpacer = new Region();
        VBox.setVgrow(sidebarSpacer, Priority.ALWAYS);

        // Footer Links
        HBox links = createFooterLinks();

        sidebar.getChildren().addAll(sidebarTitle, pBox, navButtons, sidebarSpacer, links);
        return sidebar;
    }

    private VBox createNavigationButtons(Runnable showdoc) {
        VBox navButtons = new VBox(10);

        HBox navBtn1 = createNavButton("📄", "Legal Case Management", false);
        HBox navBtn2 = createNavButton("📜", "Document & Certificate", true); // Active since we're in notifications
        HBox navBtn3 = createNavButton("🏠", "Land & Property Services", false);
        HBox navBtn4 = createNavButton("⇄", "RTI & Grievance", false);
        HBox navBtn5 = createNavButton("📚", "Legal Knowledge Base", false);

        // Add navigation functionality
        navBtn2.setOnMouseClicked(event -> showdoc.run());

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
            navBtn.setOnMouseEntered(event -> navBtn
                    .setStyle("-fx-padding: 12px 15px; -fx-background-radius: 10px; -fx-background-color: #eef2ff;"));
            navBtn.setOnMouseExited(event -> navBtn.setStyle(
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

        links.getChildren().addAll(contact, terms, privacy);
        return links;
    }

    private VBox createMainContent(Runnable showdoc) {
        VBox mainContent = new VBox(25);
        mainContent.setPadding(new Insets(20, 40, 40, 40));
        mainContent.setStyle("-fx-background-color: transparent;");

        // Top Navigation
        HBox topNav = createTopNavigation(showdoc);

        // Page Title
        Label pageTitle = new Label("Notifications");
        pageTitle.setFont(Font.font("Inter", FontWeight.BOLD, 32));
        pageTitle.setStyle("-fx-text-fill: #1e3a8a; -fx-padding: 10px 0;");

        // Notifications Section
        VBox notificationsSection = createNotificationsSection();

        mainContent.getChildren().addAll(topNav, pageTitle, notificationsSection);
        return mainContent;
    }

    private HBox createTopNavigation(Runnable showdoc) {
        HBox topNav = new HBox(30);
        topNav.setAlignment(Pos.CENTER_LEFT);

        HBox navLinks = new HBox(30);
        String topNavLinkStyle = "-fx-font-size: 15px; -fx-text-fill: #4b5563; -fx-font-weight: 500; -fx-cursor: hand;";
        String topNavLinkHoverStyle = "-fx-font-size: 15px; -fx-text-fill: #1d4ed8; -fx-font-weight: 500; -fx-cursor: hand;";

        Label home = createTopNavLink("Home", topNavLinkStyle, topNavLinkHoverStyle);
        home.setOnMouseClicked(event -> showdoc.run());

        Label services = createTopNavLink("Services", topNavLinkStyle, topNavLinkHoverStyle);
        Label rtiLink = createTopNavLink("RTI", topNavLinkStyle, topNavLinkHoverStyle);
        Label help = createTopNavLink("Legal Help", topNavLinkStyle, topNavLinkHoverStyle);
        Label resourcesLink = createTopNavLink("Resources", topNavLinkStyle, topNavLinkHoverStyle);

        navLinks.getChildren().addAll(home, rtiLink, help, resourcesLink);

        Region topNavSpacer = new Region();
        HBox.setHgrow(topNavSpacer, Priority.ALWAYS);

        // Action Buttons
        Button notificationButton = createActionButton("🔔");
        Button logoutButton = createLogoutButton(showdoc);

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

    private Button createLogoutButton(Runnable showdoc) {
        Button logoutButton = new Button("LogOut");
        logoutButton.setFont(Font.font("Inter", FontWeight.BOLD, 14));
        logoutButton.setStyle(
                "-fx-background-color: #f63b3b; -fx-background-radius: 8px; -fx-text-fill: white; -fx-padding: 8px 20px;");
        logoutButton.setCursor(Cursor.HAND);
        logoutButton.setOnMouseEntered(e -> logoutButton.setStyle(
                "-fx-background-color: #eb2525; -fx-background-radius: 8px; -fx-text-fill: white; -fx-padding: 8px 20px;"));
        logoutButton.setOnMouseExited(e -> logoutButton.setStyle(
                "-fx-background-color: #f63b3b; -fx-background-radius: 8px; -fx-text-fill: white; -fx-padding: 8px 20px;"));
        logoutButton.setOnAction(event -> showdoc.run());
        return logoutButton;
    }

    private VBox createNotificationsSection() {
        VBox notificationsSection = new VBox(15);
        notificationsSection.setPadding(new Insets(20, 0, 20, 0));

        // Header with notification count
        HBox header = new HBox(15);
        header.setAlignment(Pos.CENTER_LEFT);

        Label notificationHeader = new Label("Recent Notifications");
        notificationHeader.setFont(Font.font("Inter", FontWeight.SEMI_BOLD, 20));
        notificationHeader.setStyle("-fx-text-fill: #374151;");

        Label notificationCount = new Label("11 new");
        notificationCount.setFont(Font.font("Inter", FontWeight.BOLD, 14));
        notificationCount.setStyle(
                "-fx-text-fill: #dc2626; -fx-background-color: #fef2f2; -fx-background-radius: 12px; -fx-padding: 4px 12px;");

        Region headerSpacer = new Region();
        HBox.setHgrow(headerSpacer, Priority.ALWAYS);

        Button markAllRead = new Button("Mark All as Read");
        markAllRead.setFont(Font.font("Inter", FontWeight.SEMI_BOLD, 12));
        markAllRead.setStyle(
                "-fx-background-color: transparent; -fx-text-fill: #3b82f6; -fx-cursor: hand; -fx-border-color: transparent;");
        markAllRead.setOnMouseEntered(e -> markAllRead.setStyle(
                "-fx-background-color: transparent; -fx-text-fill: #1d4ed8; -fx-cursor: hand; -fx-border-color: transparent;"));
        markAllRead.setOnMouseExited(e -> markAllRead.setStyle(
                "-fx-background-color: transparent; -fx-text-fill: #3b82f6; -fx-cursor: hand; -fx-border-color: transparent;"));

        header.getChildren().addAll(notificationHeader, notificationCount, headerSpacer, markAllRead);

        // Notifications List
        VBox notificationsList = createNotificationsList();

        // Scroll Pane for notifications
        ScrollPane scrollPane = new ScrollPane(notificationsList);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background-color: transparent; -fx-background: transparent;");
        scrollPane.setPrefHeight(600);

        notificationsSection.getChildren().addAll(header, scrollPane);
        return notificationsSection;
    }

    private VBox createNotificationsList() {
        VBox notificationsList = new VBox(10);
        notificationsList.setPadding(new Insets(10, 0, 10, 0));

        // Create sample notifications with different types and icons
        String[][] notifications = {
                { "✅", "FIR Draft is ready to download!", "success", "1 hour ago",
                        "Your FIR draft has been successfully generated and is ready for download." },
                { "⚠️", "Missing evidence file for FIR draft! Please upload to continue.", "warning", "2 hours ago",
                        "Required documents are missing from your case file." },
                { "👩‍💼", "Lawyer Meera Joshi accepted your case request!", "info", "1 day ago",
                        "Your case has been assigned to an experienced lawyer." },
                { "📄", "New document uploaded to your case file", "info", "2 days ago",
                        "Additional evidence has been added to case #12345." },
                { "🏛️", "Court hearing scheduled for next week", "warning", "3 days ago",
                        "Your case hearing is scheduled for January 30, 2025." },
                { "💳", "Payment confirmation received", "success", "4 days ago",
                        "Your legal consultation fee has been processed." },
                { "📞", "Missed call from legal advisor", "info", "5 days ago",
                        "Please return the call at your earliest convenience." },
                { "📋", "Case status updated", "info", "1 week ago",
                        "Your case status has been changed to 'Under Review'." },
                { "🔔", "New message from support team", "info", "1 week ago",
                        "Our support team has responded to your inquiry." },
                { "📊", "Monthly case summary available", "info", "2 weeks ago",
                        "Your case progress report is now available for download." },
                { "⏰", "Reminder: Document submission deadline approaching", "warning", "2 weeks ago",
                        "Please submit required documents before the deadline." }
        };

        for (String[] notification : notifications) {
            VBox notificationCard = createNotificationCard(
                    notification[0], // icon
                    notification[1], // title
                    notification[2], // type
                    notification[3], // time
                    notification[4] // description
            );
            notificationsList.getChildren().add(notificationCard);
        }

        return notificationsList;
    }

    private VBox createNotificationCard(String icon, String title, String type, String timeAgo, String description) {
        VBox notificationCard = new VBox(8);
        notificationCard.setPadding(new Insets(20));
        notificationCard.setStyle(getNotificationCardStyle(type));
        notificationCard.setCursor(Cursor.HAND);

        // Header with icon, title and time
        HBox header = new HBox(12);
        header.setAlignment(Pos.CENTER_LEFT);

        Label iconLabel = new Label(icon);
        iconLabel.setFont(Font.font("System", 24));

        VBox titleSection = new VBox(2);

        Label titleLabel = new Label(title);
        titleLabel.setFont(Font.font("Inter", FontWeight.BOLD, 16));
        titleLabel.setStyle("-fx-text-fill: #111827;");
        titleLabel.setWrapText(true);

        Label timeLabel = new Label(timeAgo);
        timeLabel.setFont(Font.font("Inter", 12));
        timeLabel.setStyle("-fx-text-fill: #6b7280;");

        titleSection.getChildren().addAll(titleLabel, timeLabel);

        Region headerSpacer = new Region();
        HBox.setHgrow(headerSpacer, Priority.ALWAYS);

        // Status indicator
        Label statusIndicator = new Label("●");
        statusIndicator.setFont(Font.font("System", 12));
        statusIndicator.setStyle(getStatusIndicatorStyle(type));

        header.getChildren().addAll(iconLabel, titleSection, headerSpacer, statusIndicator);

        // Description
        Label descriptionLabel = new Label(description);
        descriptionLabel.setFont(Font.font("Inter", 14));
        descriptionLabel.setStyle("-fx-text-fill: #4b5563;");
        descriptionLabel.setWrapText(true);

        notificationCard.getChildren().addAll(header, descriptionLabel);

        // Hover effects
        notificationCard.setOnMouseEntered(e -> {
            notificationCard.setStyle(getNotificationCardHoverStyle(type));
        });

        notificationCard.setOnMouseExited(e -> {
            notificationCard.setStyle(getNotificationCardStyle(type));
        });

        // Click action to mark as read
        notificationCard.setOnMouseClicked(e -> {
            statusIndicator.setVisible(false);
            notificationCard.setStyle(getNotificationCardReadStyle());
        });

        return notificationCard;
    }

    private String getNotificationCardStyle(String type) {
        String baseStyle = "-fx-background-radius: 12px; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 8, 0, 0, 2);";

        switch (type) {
            case "success":
                return baseStyle
                        + " -fx-background-color: #f0fdf4; -fx-border-color: #bbf7d0; -fx-border-width: 1px; -fx-border-radius: 12px;";
            case "warning":
                return baseStyle
                        + " -fx-background-color: #fffbeb; -fx-border-color: #fed7aa; -fx-border-width: 1px; -fx-border-radius: 12px;";
            case "error":
                return baseStyle
                        + " -fx-background-color: #fef2f2; -fx-border-color: #fecaca; -fx-border-width: 1px; -fx-border-radius: 12px;";
            default: // info
                return baseStyle
                        + " -fx-background-color: #eff6ff; -fx-border-color: #bfdbfe; -fx-border-width: 1px; -fx-border-radius: 12px;";
        }
    }

    private String getNotificationCardHoverStyle(String type) {
        String baseStyle = "-fx-background-radius: 12px; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.15), 12, 0, 0, 4);";

        switch (type) {
            case "success":
                return baseStyle
                        + " -fx-background-color: #ecfdf5; -fx-border-color: #86efac; -fx-border-width: 1px; -fx-border-radius: 12px;";
            case "warning":
                return baseStyle
                        + " -fx-background-color: #fefce8; -fx-border-color: #facc15; -fx-border-width: 1px; -fx-border-radius: 12px;";
            case "error":
                return baseStyle
                        + " -fx-background-color: #fef2f2; -fx-border-color: #f87171; -fx-border-width: 1px; -fx-border-radius: 12px;";
            default: // info
                return baseStyle
                        + " -fx-background-color: #eff6ff; -fx-border-color: #93c5fd; -fx-border-width: 1px; -fx-border-radius: 12px;";
        }
    }

    private String getNotificationCardReadStyle() {
        return "-fx-background-radius: 12px; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.05), 4, 0, 0, 1); -fx-background-color: #f9fafb; -fx-border-color: #e5e7eb; -fx-border-width: 1px; -fx-border-radius: 12px; -fx-opacity: 0.7;";
    }

    private String getStatusIndicatorStyle(String type) {
        switch (type) {
            case "success":
                return "-fx-text-fill: #22c55e;";
            case "warning":
                return "-fx-text-fill: #f59e0b;";
            case "error":
                return "-fx-text-fill: #ef4444;";
            default: // info
                return "-fx-text-fill: #3b82f6;";
        }
    }
}
