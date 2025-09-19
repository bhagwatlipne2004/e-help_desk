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

public class lodgeGrievance {
    Stage lodgeGrievanceStage;
    Scene lodgeGrievanceScene;
    private HostServices hostServices;

    public void setLodgeGrievanceStage(Stage lodgeGrievanceStage) {
        this.lodgeGrievanceStage = lodgeGrievanceStage;
    }

    public void setLodgeGrievanceScene(Scene lodgeGrievanceScene) {
        this.lodgeGrievanceScene = lodgeGrievanceScene;
    }

    public void setHostServices(HostServices hostServices) {
        this.hostServices = hostServices;
    }

    // UPDATED METHOD SIGNATURE - Now accepts Runnable parameter
    public BorderPane lodgeGrievanceBox(Runnable backToRti) {
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
        Label mainTitle = new Label("Lodge Grievance");
        mainTitle.setFont(Font.font("Inter", FontWeight.BOLD, 32));
        mainTitle.setStyle("-fx-text-fill: #1e3a8a; -fx-padding: 10px 0;");

        // Grievance Content
        VBox grievanceCard = createLodgeGrievanceCard();
        ScrollPane scroll = new ScrollPane(grievanceCard);
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

    // Your existing methods remain the same but update styling
    public VBox createLodgeGrievanceCard() {
    VBox box = new VBox(15);
    box.setPadding(new Insets(20));
    box.setAlignment(Pos.TOP_LEFT);

    TitledPane centralGrievance = createSchemeCategory("Central Government Grievance Portals",
            createLink("CPGRAMS - Lodge Public Grievance", "https://pgportal.gov.in/"),
            createLink("CPGRAMS User Manual (PDF)", "https://pgportal.gov.in/"));

    TitledPane stateGrievance = createSchemeCategory("State Government Grievance Portals",
            createLink("Maharashtra Aaple Sarkar", "https://aaplesarkar.mahaonline.gov.in/"),
            createLink("Delhi Public Grievance", "https://delhi.gov.in/"),
            createLink("Uttar Pradesh Jansunwai", "https://up.gov.in/"),
            createLink("Tamil Nadu CM Cell", "https://www.tn.gov.in/"),
            createLink("Karnataka IGRS", "https://www.karnataka.gov.in/"),
            createLink("Rajasthan Sampark Portal", "https://rajasthan.gov.in/"),
            createLink("Gujarat SWAGAT", "https://gujaratindia.gov.in/"),
            createLink("West Bengal Public Grievance", "https://wb.gov.in/"));

    TitledPane grievanceGuides = createSchemeCategory("Grievance Filing Guides",
            createLink("Grievance Redress Manual (DARPG PDF)", "https://darpg.gov.in/"),
            createLink("Citizen Charter Guidelines (PDF)", "https://darpg.gov.in/"));

    VBox allPanes = new VBox(10, centralGrievance, stateGrievance, grievanceGuides);
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
