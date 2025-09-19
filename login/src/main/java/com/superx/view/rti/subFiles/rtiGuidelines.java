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

public class rtiGuidelines {
    Stage rtiGuidelinesStage;
    Scene rtiGuidelinesScene;
    private HostServices hostServices;

    public void setRtiGuidelinesStage(Stage rtiGuidelinesStage) {
        this.rtiGuidelinesStage = rtiGuidelinesStage;
    }

    public void setRtiGuidelinesScene(Scene rtiGuidelinesScene) {
        this.rtiGuidelinesScene = rtiGuidelinesScene;
    }

    public void setHostServices(HostServices hostServices) {
        this.hostServices = hostServices;
    }

    // UPDATED METHOD SIGNATURE - Now accepts Runnable parameter
    public BorderPane rtiGuidelinesBox(Runnable backToRti) {
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
        Label mainTitle = new Label("RTI & Grievance Guidelines");
        mainTitle.setFont(Font.font("Inter", FontWeight.BOLD, 32));
        mainTitle.setStyle("-fx-text-fill: #1e3a8a; -fx-padding: 10px 0;");

        // Guidelines Content
        VBox guidelinesCard = createGuidelinesCard();
        ScrollPane scroll = new ScrollPane(guidelinesCard);
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

   public VBox createGuidelinesCard() {
    VBox box = new VBox(15);
    box.setPadding(new Insets(20));
    box.setAlignment(Pos.TOP_LEFT);

    TitledPane guidelinesPane = createSchemeCategory("RTI & Grievance Guidelines",
            createLink("RTI Guide Book (PDF)", "https://rti.gov.in/"),
            createLink("Public Grievance Manual (PDF)", "https://darpg.gov.in/"),
            createLink("Citizen Charter Guidelines (PDF)", "https://darpg.gov.in/"));

    box.getChildren().add(guidelinesPane);
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
