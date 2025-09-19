package com.superx.view.Aboutus;

import org.checkerframework.checker.units.qual.t;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class AboutUsPage {

    public void setAboutStage(Stage aboutStage) {
        this.aboutStage = aboutStage;
    }

    public void setAboutusScene(Scene aboutusScene) {
        this.aboutusScene = aboutusScene;
    }

    Stage aboutStage;
    Scene aboutusScene;

    public BorderPane createAboutUsBox(Runnable showProfile, Runnable showLogin, 
                                   Runnable showLegal, Runnable showDoc, 
                                   Runnable showLandRecords, Runnable showRTI, Runnable showarchive, 
                                   Runnable showNotification){
        BorderPane mainbox = new BorderPane();
        mainbox.setStyle("-fx-background-color: linear-gradient(from 0% 0% to 100% 100%, #e6f0ff, #d6e4ff); -fx-font-family: 'Inter', 'Segoe UI', sans-serif;");

        // SIDEBAR - Exact copy from documents.java
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
        pBox.setOnMouseEntered(event -> pBox.setStyle("-fx-padding: 12px 15px; -fx-background-radius: 10px; -fx-background-color: #eef2ff;"));
        pBox.setOnMouseExited(event -> pBox.setStyle("-fx-padding: 12px 15px; -fx-background-radius: 10px; -fx-background-color: transparent;"));
        pBox.setOnMouseClicked(event -> {
            showProfile.run();
        });

        VBox navButtons = new VBox(10);

        // Navigation Buttons - Exact copy from documents.java
        HBox navBtn1 = new HBox(15, new Label("📄"), new Label("Legal Case Management"));
        navBtn1.getChildren().get(0).setStyle("-fx-font-size: 24px;");
        navBtn1.getChildren().get(1).setStyle("-fx-font-size: 15px; -fx-font-weight: 500; -fx-text-fill: #4b5563;");
        navBtn1.setAlignment(Pos.CENTER_LEFT);
        navBtn1.setStyle("-fx-padding: 12px 15px; -fx-background-radius: 10px; -fx-background-color: transparent;");
        navBtn1.setCursor(Cursor.HAND);
        navBtn1.setOnMouseEntered(event -> navBtn1.setStyle("-fx-padding: 12px 15px; -fx-background-radius: 10px; -fx-background-color: #eef2ff;"));
        navBtn1.setOnMouseExited(event -> navBtn1.setStyle("-fx-padding: 12px 15px; -fx-background-radius: 10px; -fx-background-color: transparent;"));
        navBtn1.setOnMouseClicked(evt->{
            showLegal.run();
        });

        HBox navBtn2 = new HBox(15, new Label("📜"), new Label("Document & Certificate"));
        navBtn2.getChildren().get(0).setStyle("-fx-font-size: 24px;");
        navBtn2.getChildren().get(1).setStyle("-fx-font-size: 15px; -fx-font-weight: 500; -fx-text-fill: #4b5563;");
        navBtn2.setAlignment(Pos.CENTER_LEFT);
        navBtn2.setStyle("-fx-padding: 12px 15px; -fx-background-radius: 10px; -fx-background-color: transparent;");
        navBtn2.setCursor(Cursor.HAND);
        navBtn2.setOnMouseEntered(event -> navBtn2.setStyle("-fx-padding: 12px 15px; -fx-background-radius: 10px; -fx-background-color: #eef2ff;"));
        navBtn2.setOnMouseExited(event -> navBtn2.setStyle("-fx-padding: 12px 15px; -fx-background-radius: 10px; -fx-background-color: transparent;"));
        navBtn2.setOnMouseClicked(evt->{
            showDoc.run();
        });
        
        HBox navBtn3 = new HBox(15, new Label("🏠"), new Label("Land & Property Services"));
        navBtn3.getChildren().get(0).setStyle("-fx-font-size: 24px;");
        navBtn3.getChildren().get(1).setStyle("-fx-font-size: 15px; -fx-font-weight: 500; -fx-text-fill: #4b5563;");
        navBtn3.setAlignment(Pos.CENTER_LEFT);
        navBtn3.setStyle("-fx-padding: 12px 15px; -fx-background-radius: 10px; -fx-background-color: transparent;");
        navBtn3.setCursor(Cursor.HAND);
        navBtn3.setOnMouseEntered(event -> navBtn3.setStyle("-fx-padding: 12px 15px; -fx-background-radius: 10px; -fx-background-color: #eef2ff;"));
        navBtn3.setOnMouseExited(event -> navBtn3.setStyle("-fx-padding: 12px 15px; -fx-background-radius: 10px; -fx-background-color: transparent;"));
        navBtn3.setOnMouseClicked(evt->{
            showLandRecords.run();
        });

        HBox navBtn4 = new HBox(15, new Label("⇄"), new Label("RTI & Grievance"));
        navBtn4.getChildren().get(0).setStyle("-fx-font-size: 24px;");
        navBtn4.getChildren().get(1).setStyle("-fx-font-size: 15px; -fx-font-weight: 500; -fx-text-fill: #4b5563;");
        navBtn4.setAlignment(Pos.CENTER_LEFT);
        navBtn4.setStyle("-fx-padding: 12px 15px; -fx-background-radius: 10px; -fx-background-color: transparent;");
        navBtn4.setCursor(Cursor.HAND);
        navBtn4.setOnMouseEntered(event -> navBtn4.setStyle("-fx-padding: 12px 15px; -fx-background-radius: 10px; -fx-background-color: #eef2ff;"));
        navBtn4.setOnMouseExited(event -> navBtn4.setStyle("-fx-padding: 12px 15px; -fx-background-radius: 10px; -fx-background-color: transparent;"));
        navBtn4.setOnMouseClicked(evt->{
            showRTI.run();
        });

        HBox navBtn5 = new HBox(15, new Label("📚"), new Label("Legal Knowledge Base"));
        navBtn5.getChildren().get(0).setStyle("-fx-font-size: 24px;");
        navBtn5.getChildren().get(1).setStyle("-fx-font-size: 15px; -fx-font-weight: 500; -fx-text-fill: #4b5563;");
        navBtn5.setAlignment(Pos.CENTER_LEFT);
        navBtn5.setStyle("-fx-padding: 12px 15px; -fx-background-radius: 10px; -fx-background-color: transparent;");
        navBtn5.setCursor(Cursor.HAND);
        navBtn5.setOnMouseEntered(event -> navBtn5.setStyle("-fx-padding: 12px 15px; -fx-background-radius: 10px; -fx-background-color: #eef2ff;"));
        navBtn5.setOnMouseExited(event -> navBtn5.setStyle("-fx-padding: 12px 15px; -fx-background-radius: 10px; -fx-background-color: transparent;"));
        navBtn5.setOnMouseClicked(evt->{
            showarchive.run();
        });

        // Add "About Us" navigation item as active
        HBox navBtn6 = new HBox(15, new Label("ℹ️"), new Label("About Us"));
        navBtn6.getChildren().get(0).setStyle("-fx-font-size: 24px;");
        navBtn6.getChildren().get(1).setStyle("-fx-font-size: 15px; -fx-font-weight: 600; -fx-text-fill: #3b82f6;");
        navBtn6.setAlignment(Pos.CENTER_LEFT);
        navBtn6.setStyle("-fx-padding: 12px 15px; -fx-background-radius: 10px; -fx-background-color: #e0e7ff;");
        navBtn6.setCursor(Cursor.HAND);

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
        mainbox.setLeft(sidebar);

        // MAIN CONTENT - Exact structure from documents.java
        VBox mainContent = new VBox(25);
        mainContent.setPadding(new Insets(20, 40, 40, 40));
        mainContent.setStyle("-fx-background-color: transparent;");

        // Top Navigation Bar - FIXED WITH NAVIGATION
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
            showDoc.run();
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
            showLegal.run();
        });

        Label resourcesLink = new Label("Resources");
        resourcesLink.setStyle(topNavLinkStyle);
        resourcesLink.setOnMouseEntered(event -> resourcesLink.setStyle(topNavLinkHoverStyle));
        resourcesLink.setOnMouseExited(event -> resourcesLink.setStyle(topNavLinkStyle));
        resourcesLink.setOnMouseClicked(evt -> {
            showarchive.run();
        });

        navLinks.getChildren().addAll(home, services, rtiLink, help, resourcesLink);

        Region topNavSpacer = new Region();
        HBox.setHgrow(topNavSpacer, Priority.ALWAYS);

        Button notificationButton = new Button("🔔");
        notificationButton.setFont(Font.font("System", 14));
        notificationButton.setStyle("-fx-background-color: transparent; -fx-border-color: #d1d5db; -fx-border-width: 1.5px; -fx-border-radius: 8px; -fx-background-radius: 8px; -fx-padding: 8px 20px;");
        notificationButton.setCursor(Cursor.HAND);
        notificationButton.setOnMouseEntered(event -> notificationButton.setStyle("-fx-background-color: #f6f3f3; -fx-border-color: #d1d5db; -fx-border-width: 1.5px; -fx-border-radius: 8px; -fx-background-radius: 8px; -fx-padding: 8px 20px;"));
        notificationButton.setOnMouseExited(event -> notificationButton.setStyle("-fx-background-color: transparent; -fx-border-color: #d1d5db; -fx-border-width: 1.5px; -fx-border-radius: 8px; -fx-background-radius: 8px; -fx-padding: 8px 20px;"));
        notificationButton.setOnMouseClicked(evt -> {
            showNotification.run();
        });

        Button logoutButton = new Button("LogOut");
        logoutButton.setFont(Font.font("Inter", FontWeight.BOLD, 14));
        logoutButton.setStyle("-fx-background-color: #f63b3b; -fx-background-radius: 8px; -fx-text-fill: white; -fx-padding: 8px 20px;");
        logoutButton.setCursor(Cursor.HAND);
        logoutButton.setOnMouseEntered(event -> logoutButton.setStyle("-fx-background-color: #eb2525; -fx-background-radius: 8px; -fx-text-fill: white; -fx-padding: 8px 20px;"));
        logoutButton.setOnMouseExited(event -> logoutButton.setStyle("-fx-background-color: #f63b3b; -fx-background-radius: 8px; -fx-text-fill: white; -fx-padding: 8px 20px;"));
        logoutButton.setOnAction(event -> showLogin.run());

        HBox loginButtons = new HBox(10, notificationButton, logoutButton);
        loginButtons.setAlignment(Pos.CENTER);

        topNav.getChildren().addAll(navLinks, topNavSpacer, loginButtons);

        // Main Title - Same style as documents.java
        Label mainTitle = new Label("About Us");
        mainTitle.setFont(Font.font("Inter", FontWeight.BOLD, 32));
        mainTitle.setStyle("-fx-text-fill: #1e3a8a; -fx-padding: 10px 0;");

        // Content container - Same structure as documents.java
        VBox content = new VBox(30);
        content.setPadding(new Insets(20, 0, 0, 0));

        // Core2Web Logo and Header Section
        VBox logoSection = new VBox(15);
        HBox logoContainer = new HBox(20);
        logoContainer.setAlignment(Pos.CENTER_LEFT);

        // Core2Web Logo
        try {
            ImageView logoImage = new ImageView();
            logoImage.setImage(new Image("images/Core2web.png"));
            logoImage.setFitWidth(150);
            logoImage.setFitHeight(150);
            logoImage.setPreserveRatio(true);
            logoImage.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 8, 0.3, 0, 0);");
            logoContainer.getChildren().add(logoImage);
        } catch (Exception e) {
            Label logoFallback = new Label("🌐");
            logoFallback.setFont(Font.font("System", 60));
            logoFallback.setStyle("-fx-text-fill: #3b82f6;");
            logoContainer.getChildren().add(logoFallback);
        }

        VBox titleSection = new VBox(5);
        Label companyTitle = new Label("Core2Web");
        companyTitle.setFont(Font.font("Inter", FontWeight.BOLD, 28));
        companyTitle.setStyle("-fx-text-fill: #1e3a8a;");

        Label tagline = new Label("Empowering Learners, Building Careers");
        tagline.setFont(Font.font("Inter", FontWeight.SEMI_BOLD, 18));
        tagline.setStyle("-fx-text-fill: #2563eb;");

        titleSection.getChildren().addAll(companyTitle, tagline);
        logoContainer.getChildren().add(titleSection);
        logoSection.getChildren().add(logoContainer);

        // About Section with Enhanced Content
        VBox aboutSection = new VBox(20);
        Label aboutTitle = new Label("About Core2Web");
        aboutTitle.setFont(Font.font("Inter", FontWeight.SEMI_BOLD, 20));
        aboutTitle.setStyle("-fx-text-fill: #1e3a8a;");

        VBox aboutBox = new VBox(20);
        aboutBox.setStyle("-fx-background-color: rgba(255, 255, 255, 0.8); -fx-border-color: #a0b3d7; -fx-border-width: 1; -fx-border-radius: 15; -fx-background-radius: 15; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 10, 0.3, 0, 2);");
        aboutBox.setPadding(new Insets(30));
        aboutBox.setMinHeight(250);

        Label aboutDesc = new Label(
            "Core2Web is India's premier career transformation platform dedicated to bridging the gap between academic learning and industry requirements. " +
            "Founded with a vision to democratize quality tech education, we have successfully trained over 10,000+ students and helped them secure " +
            "positions in top companies like TCS, Infosys, Wipro, Accenture, and many startups.\n\n" +
            "We specialize in providing comprehensive training programs in Data Structures & Algorithms, Java Programming, Full Stack Web Development, " +
            "System Design, and Interview Preparation. Our curriculum is designed by industry experts and updated regularly to match current market demands.\n\n" +
            "What sets us apart is our practical approach to learning - every concept is taught through real-world projects, coding challenges, and " +
            "hands-on exercises that prepare students for actual industry scenarios."
        );
        aboutDesc.setFont(Font.font("Inter", FontWeight.NORMAL, 16));
        aboutDesc.setStyle("-fx-text-fill: #374151; -fx-line-spacing: 5px;");
        aboutDesc.setWrapText(true);

        // Statistics Section
        HBox statsBox = new HBox(30);
        statsBox.setAlignment(Pos.CENTER);

        VBox stat1 = createStatCard("10,000+", "Students Trained");
        VBox stat2 = createStatCard("95%", "Placement Rate");
        VBox stat3 = createStatCard("500+", "Companies Hiring");

        statsBox.getChildren().addAll(stat1, stat2, stat3);

        aboutBox.getChildren().addAll(aboutDesc, statsBox);
        aboutSection.getChildren().addAll(aboutTitle, aboutBox);

        // Mission & Vision Section Enhanced
        VBox missionSection = new VBox(20);
        Label missionTitle = new Label("Our Mission & Vision");
        missionTitle.setFont(Font.font("Inter", FontWeight.SEMI_BOLD, 20));
        missionTitle.setStyle("-fx-text-fill: #1e3a8a;");

        HBox missionVisionContainer = new HBox(25);
        
        // Mission Box
        VBox missionBox = new VBox(15);
        missionBox.setStyle("-fx-background-color: rgba(255, 255, 255, 0.8); -fx-border-color: #a0b3d7; -fx-border-width: 1; -fx-border-radius: 15; -fx-background-radius: 15; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 10, 0.3, 0, 2);");
        missionBox.setPadding(new Insets(25));
        missionBox.setMinHeight(200);
        HBox.setHgrow(missionBox, Priority.ALWAYS);

        HBox missionHeader = new HBox(10);
        missionHeader.setAlignment(Pos.CENTER_LEFT);
        Label missionIcon = new Label("🎯");
        missionIcon.setStyle("-fx-text-fill: #eb2525ff;");
        missionIcon.setFont(Font.font("System",24));
        Label missionSubTitle = new Label("Mission");
        missionSubTitle.setFont(Font.font("Inter", FontWeight.BOLD, 18));
        missionSubTitle.setStyle("-fx-text-fill: #2563eb;");
        missionHeader.getChildren().addAll(missionIcon, missionSubTitle);

        Label missionText = new Label(
            "To bridge the gap between theoretical concepts and industry expectations by providing practical, hands-on learning experiences. " +
            "We are committed to making quality tech education accessible to everyone, regardless of their background, and ensuring every student " +
            "gains the confidence and skills needed for a successful tech career."
        );
        missionText.setFont(Font.font("Inter", FontWeight.NORMAL, 14));
        missionText.setStyle("-fx-text-fill: #4b5563; -fx-line-spacing: 3px;");
        missionText.setWrapText(true);

        missionBox.getChildren().addAll(missionHeader, missionText);

        // Vision Box
        VBox visionBox = new VBox(15);
        visionBox.setStyle("-fx-background-color: rgba(255, 255, 255, 0.8); -fx-border-color: #a0b3d7; -fx-border-width: 1; -fx-border-radius: 15; -fx-background-radius: 15; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 10, 0.3, 0, 2);");
        visionBox.setPadding(new Insets(25));
        HBox.setHgrow(visionBox, Priority.ALWAYS);

        HBox visionHeader = new HBox(10);
        visionHeader.setAlignment(Pos.CENTER_LEFT);
        Label visionIcon = new Label("🚀");
        visionIcon.setStyle("-fx-text-fill: #2563eb;");
        visionIcon.setFont(Font.font("System", 24));
        Label visionSubTitle = new Label("Vision");
        visionSubTitle.setFont(Font.font("Inter", FontWeight.BOLD, 18));
        visionSubTitle.setStyle("-fx-text-fill: #2563eb;");
        visionHeader.getChildren().addAll(visionIcon, visionSubTitle);

        Label visionText = new Label(
            "To become India's leading platform for tech education and career transformation, creating a community of skilled professionals " +
            "ready to innovate and lead in the digital world. We envision a future where every aspiring developer has access to world-class " +
            "education and mentorship to achieve their career goals."
        );
        visionText.setFont(Font.font("Inter", FontWeight.NORMAL, 14));
        visionText.setStyle("-fx-text-fill: #4b5563; -fx-line-spacing: 3px;");
        visionText.setWrapText(true);

        visionBox.getChildren().addAll(visionHeader, visionText);

        missionVisionContainer.getChildren().addAll(missionBox, visionBox);
        missionSection.getChildren().addAll(missionTitle, missionVisionContainer);

        // Founder Section Enhanced
        VBox founderSection = new VBox(20);
        Label founderTitle = new Label("Meet Our Founder");
        founderTitle.setFont(Font.font("Inter", FontWeight.SEMI_BOLD, 20));
        founderTitle.setStyle("-fx-text-fill: #1e3a8a;");

        VBox sirsectionBox = new VBox(20);
        sirsectionBox.setPadding(new Insets(30));
        sirsectionBox.setStyle("-fx-background-color: rgba(255, 255, 255, 0.8); -fx-border-color: #a0b3d7; -fx-border-width: 1; -fx-border-radius: 15; -fx-background-radius: 15; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 10, 0.3, 0, 2);");

        HBox sirsection = new HBox(30);
        sirsection.setAlignment(Pos.CENTER_LEFT);

        // Shashi Sir Image with enhanced styling
        VBox imageContainer = new VBox();
        imageContainer.setAlignment(Pos.CENTER);
        try {
            Image im1 = new Image("images/ShashiSirMobile.png");
            ImageView sirpng = new ImageView(im1);
            sirpng.setFitHeight(200);
            sirpng.setPreserveRatio(true);
            sirpng.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 15, 0.5, 0, 0);");
            imageContainer.getChildren().add(sirpng);
        } catch (Exception e) {
            Label fallbackImage = new Label("👨‍💼");
            fallbackImage.setFont(Font.font("System", 100));
            fallbackImage.setStyle("-fx-text-fill: #3b82f6;");
            imageContainer.getChildren().add(fallbackImage);
        }
        sirsection.getChildren().add(imageContainer);

        // Shashi Sir Info Enhanced
        VBox sirInfo = new VBox(15);
        HBox.setHgrow(sirInfo, Priority.ALWAYS);

        Label sirName = new Label("Shashi Bagal");
        sirName.setFont(Font.font("Inter", FontWeight.BOLD, 26));
        sirName.setStyle("-fx-text-fill: #1e3a8a;");

        Label sirTitle1 = new Label("Founder & CEO, Core2Web");
        sirTitle1.setFont(Font.font("Inter", FontWeight.SEMI_BOLD, 18));
        sirTitle1.setStyle("-fx-text-fill: #2563eb;");

        Label sirDesc = new Label(
            "Shashi Bagal is a visionary educator and technology leader with over 10 years of experience in software development, " +
            "training, and mentoring. His journey began as a software engineer at leading IT companies, where he recognized the gap " +
            "between academic curriculum and industry requirements.\n\n" +
            "This realization led him to establish Core2Web with a mission to provide practical, industry-relevant education. " +
            "Under his leadership, Core2Web has grown from a small training center to India's most trusted platform for tech education. " +
            "His unique teaching methodology, combining theoretical concepts with real-world applications, has helped thousands of students " +
            "transition into successful tech careers.\n\n" +
            "Shashi believes in the power of personalized mentoring and ensures that every student receives individual attention and guidance " +
            "throughout their learning journey."
        );
        sirDesc.setFont(Font.font("Inter", FontWeight.NORMAL, 14));
        sirDesc.setStyle("-fx-text-fill: #4b5563; -fx-line-spacing: 4px;");
        sirDesc.setWrapText(true);

        // Achievements section for founder
        VBox achievementsBox = new VBox(8);
        Label achievementsTitle = new Label("Key Achievements:");
        achievementsTitle.setFont(Font.font("Inter", FontWeight.SEMI_BOLD, 14));
        achievementsTitle.setStyle("-fx-text-fill: #1e3a8a;");

        Label achievement1 = new Label("• Trained 10,000+ students with 95% placement success rate");
        Label achievement2 = new Label("• Industry expert in Java, Data Structures, and System Design");
        Label achievement3 = new Label("• Regular speaker at tech conferences and workshops");
        Label achievement4 = new Label("• Mentor to 500+ software engineers currently working in top companies");

        String achievementStyle = "-fx-font-size: 13px; -fx-text-fill: #059669; -fx-font-weight: 500;";
        achievement1.setStyle(achievementStyle);
        achievement2.setStyle(achievementStyle);
        achievement3.setStyle(achievementStyle);
        achievement4.setStyle(achievementStyle);

        achievementsBox.getChildren().addAll(achievementsTitle, achievement1, achievement2, achievement3, achievement4);

        sirInfo.getChildren().addAll(sirName, sirTitle1, sirDesc, achievementsBox);
        sirsection.getChildren().add(sirInfo);
        sirsectionBox.getChildren().add(sirsection);
        founderSection.getChildren().addAll(founderTitle, sirsectionBox);

        // Team Members Section Enhanced
        VBox teamSection = new VBox(20);
        Label teamTitle = new Label("Our Development Team");
        teamTitle.setFont(Font.font("Inter", FontWeight.SEMI_BOLD, 20));
        teamTitle.setStyle("-fx-text-fill: #1e3a8a;");

        HBox teamContainer = new HBox(20);
        teamContainer.setAlignment(Pos.CENTER);

        // Enhanced Team Member Cards
        VBox member1 = createEnhancedTeamMemberCard("images/pawan.jpeg", "PawanKumar bari", "", 
            "", "", "");
        VBox member2 = createEnhancedTeamMemberCard("images/bhagwat.jpg", "Bhagwat Lipne", "(Team Leader)", 
            "", "", "");
        VBox member3 = createEnhancedTeamMemberCard("images/jayesh.jpg", "Jayesh Benke", "", 
            "", "", "");

        teamContainer.getChildren().addAll(member1, member2, member3);
        teamSection.getChildren().addAll(teamTitle, teamContainer);

        // Add all sections to content
        content.getChildren().addAll(logoSection, aboutSection, founderSection,missionSection, 
              teamSection);

        // Add everything to main content
        mainContent.getChildren().addAll(topNav, mainTitle, content);

        // Create ScrollPane and wrap the main content
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(mainContent);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setStyle("-fx-background-color: transparent; -fx-background: transparent;");
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        
        // Smooth scrolling
        scrollPane.getStyleClass().add("edge-to-edge");
        scrollPane.setStyle(scrollPane.getStyle() + "-fx-focus-color: transparent; -fx-faint-focus-color: transparent;");

        mainbox.setCenter(scrollPane);
        return mainbox;
    }

    private VBox createStatCard(String number, String label) {
        VBox statCard = new VBox(5);
        statCard.setAlignment(Pos.CENTER);
        statCard.setStyle("-fx-background-color: rgba(59, 130, 246, 0.1); -fx-border-color: #3b82f6; -fx-border-width: 1; -fx-border-radius: 10; -fx-background-radius: 10; -fx-padding: 20px;");
        statCard.setPrefWidth(150);

        Label numberLabel = new Label(number);
        numberLabel.setFont(Font.font("Inter", FontWeight.BOLD, 24));
        numberLabel.setStyle("-fx-text-fill: #3b82f6;");

        Label textLabel = new Label(label);
        textLabel.setFont(Font.font("Inter", FontWeight.SEMI_BOLD, 12));
        textLabel.setStyle("-fx-text-fill: #4b5563;");
        textLabel.setWrapText(true);

        statCard.getChildren().addAll(numberLabel, textLabel);
        return statCard;
    }

    // Helper method to create enhanced team member cards
    private VBox createEnhancedTeamMemberCard(String imagePath, String name, String role, String skills, String experience, String specialty) {
        VBox memberCard = new VBox(10);
        memberCard.setPadding(new Insets(20));
        memberCard.setStyle("-fx-background-color: rgba(255, 255, 255, 0.9); -fx-border-color: #cbd5e1; -fx-border-width: 1; -fx-border-radius: 12; -fx-background-radius: 12; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 8, 0.3, 0, 2);");
        memberCard.setPrefWidth(220);
        memberCard.setAlignment(Pos.CENTER);
        VBox imageContainer = new VBox();
        imageContainer.setAlignment(Pos.CENTER);
        imageContainer.setPadding(new Insets(0, 0, 10, 0));

        try {
            // Try to load the image
            Image memberImage = new Image(imagePath);
            ImageView memberImageView = new ImageView(memberImage);
            memberImageView.setFitWidth(80);
            memberImageView.setFitHeight(80);
            memberImageView.setPreserveRatio(true);
            
            // Create circular image effect
            memberImageView.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 10, 0.5, 0, 0);");
            
            // Optional: Add circular clipping (requires additional setup)
            // Circle clip = new Circle(40, 40, 40);
            // memberImageView.setClip(clip);
            
            imageContainer.getChildren().add(memberImageView);
        } catch (Exception e) {
            // Fallback if image not found
        }

        Label memberName = new Label(name);
        memberName.setFont(Font.font("Inter", FontWeight.BOLD, 16));
        memberName.setStyle("-fx-text-fill: #1e3a8a;");

        Label memberRole = new Label(role);
        memberRole.setFont(Font.font("Inter", FontWeight.BOLD, 13));
        memberRole.setStyle("-fx-text-fill: #2563eb;");

        Label memberSkills = new Label(skills);
        memberSkills.setFont(Font.font("Inter", FontWeight.NORMAL, 11));
        memberSkills.setStyle("-fx-text-fill: #059669;");
        memberSkills.setWrapText(true);
        memberSkills.setAlignment(Pos.CENTER);

        Label memberExp = new Label(experience + "  " + specialty);
        memberExp.setFont(Font.font("Inter", FontWeight.NORMAL, 12));
        memberExp.setStyle("-fx-text-fill: #6b7280;");

        memberCard.getChildren().addAll(imageContainer, memberName, memberRole, memberSkills, memberExp);
        return memberCard;
    }
}
