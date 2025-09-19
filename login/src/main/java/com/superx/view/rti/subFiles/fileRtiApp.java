package com.superx.view.rti.subFiles;

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
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class fileRtiApp {
    Stage fileRtiAppStage;
    Scene fileRtiAppScene;
    private HostServices hostServices;

    public void setFileRtiAppStage(Stage fileRtiAppStage) {
        this.fileRtiAppStage = fileRtiAppStage;
    }

    public void setFileRtiAppScene(Scene fileRtiAppScene) {
        this.fileRtiAppScene = fileRtiAppScene;
    }

    public void setHostServices(HostServices hostServices) {
        this.hostServices = hostServices;
    }

    // UPDATED METHOD SIGNATURE - Now accepts Runnable parameter
    public BorderPane fileRtiAppBox(Runnable backToRti) {
        BorderPane mainBox = new BorderPane();
        mainBox.setStyle(
                "-fx-background-color: linear-gradient(from 0% 0% to 100% 100%, #e6f0ff, #d6e4ff); -fx-font-family: 'Inter', 'Segoe UI', sans-serif;");

        VBox mainContent = createMainContent(backToRti);
        mainBox.setCenter(mainContent);
        return mainBox;
    }

    private VBox createMainContent(Runnable backToRti) {
        VBox mainContent = new VBox(25);
        mainContent.setPadding(new Insets(20, 40, 40, 40));
        mainContent.setStyle("-fx-background-color: transparent;");

        // Header with back button
        HBox header = createHeader(backToRti);

        // Main Title
        Label mainTitle = new Label("File RTI Application");
        mainTitle.setFont(Font.font("Inter", FontWeight.BOLD, 32));
        mainTitle.setStyle("-fx-text-fill: #1e3a8a; -fx-padding: 10px 0;");

        // RTI Content
        VBox rtiCard = createRTIApplicationCard();
        ScrollPane scroll = new ScrollPane(rtiCard);
        scroll.setFitToWidth(true);
        scroll.setStyle("-fx-background-color: transparent; -fx-background: transparent;");

        mainContent.getChildren().addAll(header, mainTitle, scroll);
        return mainContent;
    }

    private HBox createHeader(Runnable backToRti) {
        HBox header = new HBox();
        header.setAlignment(Pos.CENTER_LEFT);

        Button backButton = new Button("← Back to RTI");
        backButton.setFont(Font.font("Inter", FontWeight.SEMI_BOLD, 14));
        backButton.setStyle(
                "-fx-background-color: #3b82f6; -fx-text-fill: white; -fx-background-radius: 8px; -fx-padding: 10px 20px;");
        backButton.setCursor(Cursor.HAND);
        backButton.setOnAction(e -> backToRti.run());

        header.getChildren().add(backButton);
        return header;
    }

    // Your existing createRTIApplicationCard, createSchemeCategory, and createLink
    // methods remain the same
   public VBox createRTIApplicationCard() {
    VBox box = new VBox(15);
    box.setPadding(new Insets(20));
    box.setAlignment(Pos.TOP_LEFT);

    TitledPane rtiForms = createSchemeCategory("RTI Application Forms",
            createLink("Central Government RTI Form", "https://rtionline.gov.in/"),
            createLink("RTI Application Format (PDF)", "https://rtionline.gov.in/"));

    TitledPane filingInstructions = createSchemeCategory("How to File RTI",
            createLink("Step-by-step RTI Filing Guide", "https://rti.gov.in/"),
            createLink("RTI for State Government", "https://rti.gov.in/"));

    TitledPane onlineRTI = createSchemeCategory("File RTI Online",
            createLink("RTI Online Portal (India)", "https://rtionline.gov.in/"));

    TitledPane stateRTI = createSchemeCategory("State RTI Forms",
            createLink("Maharashtra RTI Form (PDF)", "https://mahrti.gov.in/"),
            createLink("Tamil Nadu RTI Form", "https://www.tn.gov.in/"),
            createLink("Uttar Pradesh RTI Form", "https://up.gov.in/"),
            createLink("Karnataka RTI Application Form", "https://www.karnataka.gov.in/"),
            createLink("Delhi RTI Application Form", "https://delhi.gov.in/"),
            createLink("Gujarat RTI Application Form", "https://gujaratindia.gov.in/"));

    VBox allPanes = new VBox(10, rtiForms, filingInstructions, onlineRTI, stateRTI);
    box.getChildren().add(allPanes);
    return box;
}


    public TitledPane createSchemeCategory(String title, Hyperlink... links) {
        VBox box = new VBox(10);
        box.setPadding(new Insets(15));
        box.setStyle("-fx-background-color: rgba(255, 255, 255, 0.9); -fx-background-radius: 10px;");
        box.getChildren().addAll(links);

        TitledPane pane = new TitledPane(title, box);
        pane.setExpanded(true);
        pane.setStyle("-fx-background-color: rgba(255, 255, 255, 0.8); -fx-background-radius: 10px;");
        return pane;
    }

   public Hyperlink createLink(String text, String url) {
    Hyperlink link = new Hyperlink(text);
    link.setFont(Font.font("Inter", 14));
    link.setStyle(
        "-fx-text-fill: #3b82f6; " +
        "-fx-font-weight: 500; " +
        "-fx-underline: false; " +
        "-fx-font-size: 14px;"  // Explicitly set font size
    );
    
    // Set hover effect that doesn't change size
    link.setOnMouseEntered(e -> {
        link.setStyle(
            "-fx-text-fill: #1d4ed8; " +
            "-fx-font-weight: 500; " +
            "-fx-underline: true; " +
            "-fx-font-size: 14px; " +
            "-fx-cursor: hand;"
        );
    });
    
    link.setOnMouseExited(e -> {
        link.setStyle(
            "-fx-text-fill: #3b82f6; " +
            "-fx-font-weight: 500; " +
            "-fx-underline: false; " +
            "-fx-font-size: 14px;"
        );
    });
    
    link.setOnAction(e -> {
        if (hostServices != null) {
            hostServices.showDocument(url);
        } else {
            System.err.println("HostServices not set.");
        }
    });
    return link;
}

}
