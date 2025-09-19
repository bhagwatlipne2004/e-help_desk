package com.superx.view.Loginpages;

import com.superx.Controller.ViewController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class terms {

    private Scene termsScene;
    private Stage termsStage;

    public void setTermsScene(Scene termsScene) {
        this.termsScene = termsScene;
    }

    public void setTermsStage(Stage termsStage) {
        this.termsStage = termsStage;
    }

    public HBox termsBox(Runnable back) {

        // Enhanced background with better sizing
        BackgroundImage bg = new BackgroundImage(
                new Image("images/green.png", 1920, 1080, false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                new BackgroundSize(1920, 1080, false, false, false, true));

        // Enhanced shadow effect for better depth
        DropShadow dp = new DropShadow();
        dp.setOffsetX(4.0);
        dp.setOffsetY(4.0);
        dp.setRadius(15.0);
        dp.setSpread(0.2);
        dp.setBlurType(BlurType.GAUSSIAN);
        dp.setColor(Color.rgb(0, 0, 0, 0.3));

        // Main content box with better proportions
        Rectangle termsBoxRect = new Rectangle();
        termsBoxRect.setArcHeight(120);
        termsBoxRect.setArcWidth(120);
        termsBoxRect.setHeight(980);
        termsBoxRect.setWidth(920);
        termsBoxRect.setFill(Color.rgb(255, 255, 255, 0.98));
        termsBoxRect.setStroke(Color.rgb(220, 220, 220));
        termsBoxRect.setStrokeWidth(1);
        termsBoxRect.setEffect(dp);

        // Logo section with better sizing
        Image image = new Image("images/logo.png");
        ImageView iv = new ImageView(image);
        iv.setFitHeight(650);
        iv.setFitWidth(600);
        iv.setPreserveRatio(true);
        
        VBox logoBox = new VBox(iv);
        logoBox.setAlignment(Pos.CENTER);
        logoBox.setPadding(new Insets(40));

        // Enhanced title section
        Text titleText = new Text("Terms & Conditions");
        titleText.setFont(Font.font("Arial", FontWeight.BOLD, 42));
        titleText.setFill(Color.rgb(33, 37, 41));

        Text subtitleText = new Text("E-Legal Desk Application Agreement");
        subtitleText.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
        subtitleText.setFill(Color.rgb(108, 117, 125));

        VBox titleSection = new VBox(8, titleText, subtitleText);
        titleSection.setAlignment(Pos.CENTER_LEFT);
        titleSection.setPadding(new Insets(0, 0, 20, 0));

        // Enhanced terms content with better formatting
        Text termsContent = new Text(
                """
                Effective Date: January 1, 2024
                Version: 1.0
                Application: E-Legal Desk (Desktop Legal Assistant)

                ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

                1. ACCEPTANCE OF TERMS
                By downloading, installing, or using E-Legal Desk, you acknowledge that you have read, understood, and agree to be bound by these Terms and Conditions. If you do not agree to these terms, please discontinue use immediately.

                2. APPLICATION PURPOSE & SCOPE
                E-Legal Desk is designed to assist users with:
                • Drafting legal documents using provided templates
                • Case tracking and management
                • Access to verified legal resources and information
                • Facilitating connections with qualified legal professionals

                ⚠️ IMPORTANT DISCLAIMER: This application does not file legal cases directly, provide legal advice, or act as your legal representative.

                3. USER RESPONSIBILITIES & OBLIGATIONS
                As a user of E-Legal Desk, you agree to:
                • Provide accurate, complete, and truthful information
                • Use the application solely for lawful purposes
                • Maintain confidentiality of your account credentials
                • Not attempt to reverse engineer or modify the application
                • Respect intellectual property rights of all content

                4. DOCUMENT TEMPLATES & USAGE
                • All templates are provided for informational purposes only
                • Templates may require customization for specific legal requirements
                • Users must verify compliance with local laws and regulations
                • Final submission of legal documents must be done through official channels
                • We do not guarantee acceptance by courts or legal authorities

                5. PRIVACY & DATA PROTECTION
                Your privacy is important to us. We commit to:
                • Storing data securely with industry-standard encryption
                • Obtaining explicit consent before data collection
                • Never selling or sharing personal information with third parties
                • Providing data access and deletion rights upon request
                • Complying with applicable data protection regulations

                6. LEGAL PROFESSIONAL CONNECTIONS
                • E-Legal Desk serves only as a connection platform
                • All legal advice is provided independently by licensed professionals
                • We do not endorse or guarantee the quality of third-party services
                • Attorney-client relationships are established directly with legal professionals
                • Payment and service agreements are between users and legal professionals

                7. LIMITATIONS OF LIABILITY
                E-Legal Desk and its developers shall not be liable for:
                • Legal outcomes, case results, or judicial decisions
                • Data loss due to technical failures or user error
                • Delays in government or court processes
                • Errors in legal templates or information
                • Actions of third-party legal professionals

                8. INTELLECTUAL PROPERTY
                • All content, templates, and software remain our property
                • Users receive a limited license for personal use only
                • Unauthorized distribution or commercial use is prohibited

                9. SERVICE AVAILABILITY & MODIFICATIONS
                • We reserve the right to modify or discontinue services
                • Updates may be required for continued access
                • Maintenance periods may temporarily limit availability

                10. TERMINATION
                • Users may terminate their account at any time
                • We reserve the right to suspend accounts for terms violations
                • Upon termination, user data will be handled per our Privacy Policy

                11. GOVERNING LAW
                These terms are governed by the laws of [Your Jurisdiction] without regard to conflict of law principles.

                12. CONTACT & SUPPORT
                For questions, support, or legal concerns:

                📧 General Support: support@elegaldesk.in
                📧 Legal Inquiries: legal@elegaldesk.in  
                📧 Privacy Concerns: privacy@elegaldesk.in
                📞 Phone: +91-XXXX-XXXXXX
                🌐 Website: www.elegaldesk.in
                📍 Address: [Your Business Address]

                ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

                Last Updated: January 1, 2024
                Document Version: 1.0

                By clicking "Accept & Continue," you acknowledge that you have read and agree to these Terms and Conditions.
                """);
        
        termsContent.setFont(Font.font("Arial", FontWeight.NORMAL, 13));
        termsContent.setFill(Color.rgb(52, 58, 64));
        termsContent.setWrappingWidth(830);
        termsContent.setLineSpacing(2.0);

        // Enhanced scrollable content area
        ScrollPane scrollPane = new ScrollPane(termsContent);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefHeight(580);
        scrollPane.setMaxHeight(580);
        scrollPane.setStyle("""
            -fx-background-color: transparent;
            -fx-background: transparent;
            -fx-focus-color: transparent;
            -fx-faint-focus-color: transparent;
            """);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        // Enhanced back button
        Button backButton = new Button("← Back");
        backButton.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        backButton.setStyle("""
            -fx-background-color: #6c757d;
            -fx-text-fill: white;
            -fx-background-radius: 12;
            -fx-padding: 12 24 12 24;
            -fx-cursor: hand;
            """);
        backButton.setMinHeight(50);
        backButton.setPrefWidth(160);
        backButton.setOnMouseEntered(e -> backButton.setStyle("""
            -fx-background-color: #5a6268;
            -fx-text-fill: white;
            -fx-background-radius: 12;
            -fx-padding: 12 24 12 24;
            -fx-cursor: hand;
            -fx-scale-x: 1.05;
            -fx-scale-y: 1.05;
            """));
        backButton.setOnMouseExited(e -> backButton.setStyle("""
            -fx-background-color: #6c757d;
            -fx-text-fill: white;
            -fx-background-radius: 12;
            -fx-padding: 12 24 12 24;
            -fx-cursor: hand;
            -fx-scale-x: 1.0;
            -fx-scale-y: 1.0;
            """));
        backButton.setOnAction(evt -> back.run());

        // Enhanced accept button
        Button acceptButton = new Button("Accept & Continue ✓");
        acceptButton.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        acceptButton.setStyle("""
            -fx-background-color: #007BFF;
            -fx-text-fill: white;
            -fx-background-radius: 12;
            -fx-padding: 12 24 12 24;
            -fx-cursor: hand;
            """);
        acceptButton.setMinHeight(50);
        acceptButton.setPrefWidth(220);
        acceptButton.setOnMouseEntered(e -> acceptButton.setStyle("""
            -fx-background-color: #0056b3;
            -fx-text-fill: white;
            -fx-background-radius: 12;
            -fx-padding: 12 24 12 24;
            -fx-cursor: hand;
            -fx-scale-x: 1.05;
            -fx-scale-y: 1.05;
            """));
        acceptButton.setOnMouseExited(e -> acceptButton.setStyle("""
            -fx-background-color: #007BFF;
            -fx-text-fill: white;
            -fx-background-radius: 12;
            -fx-padding: 12 24 12 24;
            -fx-cursor: hand;
            -fx-scale-x: 1.0;
            -fx-scale-y: 1.0;
            """));
        acceptButton.setOnAction(evt -> back.run());
        acceptButton.setDefaultButton(true);

        // Enhanced button container
        HBox buttonBox = new HBox(25, backButton, acceptButton);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPadding(new Insets(20, 0, 0, 0));

        // Main content layout with better spacing
        VBox contentLayout = new VBox(25, titleSection, scrollPane, buttonBox);
        contentLayout.setAlignment(Pos.TOP_LEFT);
        contentLayout.setPadding(new Insets(40, 45, 40, 45));
        contentLayout.setMaxWidth(900);

        // Stack the rectangle background with content
        StackPane sp = new StackPane(termsBoxRect, contentLayout);
        sp.setAlignment(Pos.CENTER);
        sp.setPadding(new Insets(40));

        // Root layout with better proportions
        HBox root = new HBox(200, logoBox, sp);
        root.setPrefSize(1920, 1080);
        root.setBackground(new Background(bg));
        root.setAlignment(Pos.CENTER);

        return root;
    }
}
