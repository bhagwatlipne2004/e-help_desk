package com.superx.view.landRecords.archive.archiveSubfiles;

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
import javafx.stage.Stage;

public class courtProcedures {
    Stage CourtProceduresStage;
    Scene CourtProceduresScene;
    private HostServices hostServices;

    public void setCourtProceduresStage(Stage courtProceduresStage) {
        CourtProceduresStage = courtProceduresStage;
    }

    public void setCourtProceduresScene(Scene courtProceduresScene) {
        CourtProceduresScene = courtProceduresScene;
    }

    public void setHostServices(HostServices hostServices) {
        this.hostServices = hostServices;
    }

    public BorderPane courtProceduresBox(Runnable showArchive) {
        BorderPane mainBox = new BorderPane();
        mainBox.setStyle(
                "-fx-background-color: linear-gradient(from 0% 0% to 100% 100%, #e6f0ff, #d6e4ff); -fx-font-family: 'Inter', 'Segoe UI', sans-serif;");

        VBox content = new VBox(20);
        content.setPadding(new Insets(20, 40, 40, 40));

        // Header with back button
        HBox header = new HBox(20);
        header.setAlignment(Pos.CENTER_LEFT);

        Button backButton = new Button("← Back to Legal Archives");
        backButton.setFont(Font.font("Inter", FontWeight.SEMI_BOLD, 14));
        backButton.setStyle(
                "-fx-background-color: #3b82f6; -fx-text-fill: white; -fx-background-radius: 8px; -fx-padding: 10px 20px;");
        backButton.setCursor(Cursor.HAND);
        backButton.setOnMouseEntered(e -> backButton.setStyle(
                "-fx-background-color: #2563eb; -fx-text-fill: white; -fx-background-radius: 8px; -fx-padding: 10px 20px;"));
        backButton.setOnMouseExited(e -> backButton.setStyle(
                "-fx-background-color: #3b82f6; -fx-text-fill: white; -fx-background-radius: 8px; -fx-padding: 10px 20px;"));
        backButton.setOnAction(e -> showArchive.run());

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Label title = new Label("Court Procedures & Legal Process");
        title.setFont(Font.font("Inter", FontWeight.BOLD, 32));
        title.setStyle("-fx-text-fill: #1e3a8a;");

        header.getChildren().addAll(backButton, spacer, title);

        VBox lawCard = createCourtProceduresCard();
        content.getChildren().addAll(header, lawCard);

        ScrollPane scroll = new ScrollPane(content);
        scroll.setFitToWidth(true);
        scroll.setStyle("-fx-background-color: linear-gradient(from 0% 0% to 100% 100%, #e6f0ff, #d6e4ff);");
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        mainBox.setCenter(scroll);
        return mainBox;
    }

    public VBox createCourtProceduresCard() {
    VBox box = new VBox(20);
    box.setPadding(new Insets(20));
    box.setAlignment(Pos.TOP_LEFT);
    box.setStyle(
            "-fx-background-color: rgba(255, 255, 255, 0.8); -fx-background-radius: 15px; -fx-border-color: #a0b3d7; -fx-border-width: 1px; -fx-border-radius: 15px;");

    // Section 1: Court Structure
    TitledPane structurePane = createSchemeCategory("Structure of Indian Judiciary",
            createLink("Introduction to Indian Judiciary", "https://main.sci.gov.in/"),
            createLink("Hierarchy of Courts in India", "https://main.sci.gov.in/"));

    // Section 2: Filing & Trial Process
    TitledPane filingPane = createSchemeCategory("Filing and Trial Process",
            createLink("How to File a Case in Civil Court", "https://ecourts.gov.in/ecourts_home/"),
            createLink("Criminal Case Procedure", "https://ecourts.gov.in/ecourts_home/"));

    // Section 3: E-Court Services & Guidelines
    TitledPane eCourtPane = createSchemeCategory("E-Courts & Legal Services",
            createLink("eCourts User Manual", "https://ecourts.gov.in/ecourts_home/"),
            createLink("Legal Aid Guidelines (NALSA)", "https://nalsa.gov.in/"));

    VBox allPanes = new VBox(15, structurePane, filingPane, eCourtPane);
    box.getChildren().add(allPanes);

    return box;
}


    public TitledPane createSchemeCategory(String title, Hyperlink... links) {
        VBox box = new VBox(10);
        box.setPadding(new Insets(15));
        box.setStyle("-fx-background-color: rgba(255, 255, 255, 0.5); -fx-background-radius: 8px;");
        box.getChildren().addAll(links);

        TitledPane pane = new TitledPane(title, box);
        pane.setExpanded(true);
        pane.setStyle("-fx-font-family: 'Inter'; -fx-font-weight: bold; -fx-font-size: 16px; -fx-text-fill: #1e3a8a;");
        return pane;
    }

    public Hyperlink createLink(String text, String url) {
        Hyperlink link = new Hyperlink(text);
        link.setFont(Font.font("Inter", FontWeight.NORMAL, 14));
        link.setStyle("-fx-text-fill: #3b82f6; -fx-underline: true;");
        link.setCursor(Cursor.HAND);
        link.setOnMouseEntered(e -> link.setStyle("-fx-text-fill: #1d4ed8; -fx-underline: true;"));
        link.setOnMouseExited(e -> link.setStyle("-fx-text-fill: #3b82f6; -fx-underline: true;"));
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
