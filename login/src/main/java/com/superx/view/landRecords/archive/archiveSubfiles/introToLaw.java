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

public class introToLaw {
    Stage introToLawStage;
    Scene introToLawScene;
    private HostServices hostServices;

    public void setIntroToLawStage(Stage introToLawStage) {
        this.introToLawStage = introToLawStage;
    }

    public void setIntroToLawScene(Scene introToLawScene) {
        this.introToLawScene = introToLawScene;
    }

    public void setHostServices(HostServices hostServices) {
        this.hostServices = hostServices;
    }

    public BorderPane introTolawBox(Runnable showArchive) {
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

        Label title = new Label("Introduction to Law");
        title.setFont(Font.font("Inter", FontWeight.BOLD, 32));
        title.setStyle("-fx-text-fill: #1e3a8a;");

        header.getChildren().addAll(backButton, spacer, title);

        VBox lawCard = createIntroToLawCard();
        content.getChildren().addAll(header, lawCard);

        ScrollPane scroll = new ScrollPane(content);
        scroll.setFitToWidth(true);
        scroll.setStyle("-fx-background-color: linear-gradient(from 0% 0% to 100% 100%, #e6f0ff, #d6e4ff);");
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        mainBox.setCenter(scroll);
        return mainBox;
    }

   public VBox createIntroToLawCard() {
    VBox box = new VBox(20);
    box.setPadding(new Insets(20));
    box.setAlignment(Pos.TOP_LEFT);
    box.setStyle(
            "-fx-background-color: rgba(255, 255, 255, 0.8); -fx-background-radius: 15px; -fx-border-color: #a0b3d7; -fx-border-width: 1px; -fx-border-radius: 15px;");

    // Category 1: Basics of Indian Legal System
    TitledPane basicsPane = createSchemeCategory("Understanding Indian Laws",
            createLink("Indian Constitution (Bare Act PDF)", "https://legislative.gov.in/constitution-of-india"),
            createLink("Basic Rights & Duties (NCERT PDF)", "https://ncert.nic.in/"));

    // Category 2: Types of Laws
    TitledPane categoryPane = createSchemeCategory("Types of Laws",
            createLink("Criminal Law (IPC Basics PDF)", "https://www.indiacode.nic.in/"),
            createLink("Civil Procedure Code (CPC PDF)", "https://www.indiacode.nic.in/"),
            createLink("Consumer Protection Act (2019 PDF)", "https://consumeraffairs.nic.in/"));

    // Category 3: Important Acts
    TitledPane keyActsPane = createSchemeCategory("Key Acts and Guides",
            createLink("RTI Act Manual (English PDF)", "https://rti.gov.in/"),
            createLink("Legal Rights of Women (NCW PDF)", "http://ncw.nic.in/sites/default/files/Module-%20Laws%20relating%20to%20Women_0.pdf"),
            createLink("Information Technology Act (IT Act PDF)", "https://www.meity.gov.in/"));

    VBox allPanes = new VBox(15, basicsPane, categoryPane, keyActsPane);
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
