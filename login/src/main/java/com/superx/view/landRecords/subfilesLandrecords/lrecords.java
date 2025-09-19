package com.superx.view.landRecords.subfilesLandrecords;

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

public class lrecords {
    Stage lStage;
    Scene lScene;
    private HostServices hostServices;

    public void setLandRedordsStage(Stage landRedordsStage) {
        this.lStage = landRedordsStage;
    }

    public void setLandRecordsScene(Scene landRecordsScene) {
        this.lScene = landRecordsScene;
    }

    public void setHostServices(HostServices hostServices) {
        this.hostServices = hostServices;
    }

    public BorderPane landRecordsBox(Runnable showLandRecords) {
        BorderPane mainBox = new BorderPane();
        mainBox.setStyle(
                "-fx-background-color: linear-gradient(from 0% 0% to 100% 100%, #e6f0ff, #d6e4ff); -fx-font-family: 'Inter', 'Segoe UI', sans-serif;");

        VBox content = new VBox(20);
        content.setPadding(new Insets(20, 40, 40, 40));

        // Header with back button
        HBox header = new HBox(20);
        header.setAlignment(Pos.CENTER_LEFT);

        Button backButton = new Button("← Back to Land Records");
        backButton.setFont(Font.font("Inter", FontWeight.SEMI_BOLD, 14));
        backButton.setStyle(
                "-fx-background-color: #3b82f6; -fx-text-fill: white; -fx-background-radius: 8px; -fx-padding: 10px 20px;");
        backButton.setCursor(Cursor.HAND);
        backButton.setOnMouseEntered(e -> backButton.setStyle(
                "-fx-background-color: #2563eb; -fx-text-fill: white; -fx-background-radius: 8px; -fx-padding: 10px 20px;"));
        backButton.setOnMouseExited(e -> backButton.setStyle(
                "-fx-background-color: #3b82f6; -fx-text-fill: white; -fx-background-radius: 8px; -fx-padding: 10px 20px;"));
        backButton.setOnAction(e -> showLandRecords.run());

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Label title = new Label("Land Records Search");
        title.setFont(Font.font("Inter", FontWeight.BOLD, 32));
        title.setStyle("-fx-text-fill: #1e3a8a;");

        header.getChildren().addAll(backButton, spacer, title);

        VBox landRecordsCard = createLandRecordSearchCard();
        content.getChildren().addAll(header, landRecordsCard);

        ScrollPane scroll = new ScrollPane(content);
        scroll.setFitToWidth(true);
        scroll.setStyle("-fx-background-color: linear-gradient(from 0% 0% to 100% 100%, #e6f0ff, #d6e4ff);");
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        mainBox.setCenter(scroll);
        return mainBox;
    }

    public VBox createLandRecordSearchCard() {
        VBox cardBox = new VBox(15);
        cardBox.setPadding(new Insets(20));
        cardBox.setAlignment(Pos.TOP_LEFT);
        cardBox.setStyle(
                "-fx-background-color: rgba(255, 255, 255, 0.8); -fx-background-radius: 15px; -fx-border-color: #a0b3d7; -fx-border-width: 1px; -fx-border-radius: 15px;");

        // State-wise land records portals
       TitledPane maharashtraPane = createSchemeCategory("Maharashtra",
        createLink("Mahabhulekh Portal", "https://bhulekh.mahabhumi.gov.in/"),
        createLink("7/12 Extract Online", "https://bhulekh.mahabhumi.gov.in/"));

TitledPane karnatakaPane = createSchemeCategory("Karnataka",
        createLink("Bhoomi Online RTC", "https://landrecords.karnataka.gov.in/Service84/"),
        createLink("Survey Settlement Records", "https://landrecords.karnataka.gov.in/"));

TitledPane telanganaPane = createSchemeCategory("Telangana",
        createLink("Dharani Portal", "https://dharani.telangana.gov.in/"),
        createLink("Web Land Records", "https://ccla.telangana.gov.in/WeBLand/"));

TitledPane delhiPane = createSchemeCategory("Delhi",
        createLink("Bhulekh Delhi", "https://dlrc.delhigovt.nic.in/"),
        createLink("Revenue Records", "https://dlrc.delhigovt.nic.in/"));

TitledPane gujaratPane = createSchemeCategory("Gujarat",
        createLink("AnyROR Anywhere", "https://anyror.gujarat.gov.in/"),
        createLink("Village Information System", "https://anyror.gujarat.gov.in/"));

TitledPane upPane = createSchemeCategory("Uttar Pradesh",
        createLink("UP Bhulekh", "http://upbhulekh.gov.in/"),
        createLink("Khasra Khatauni", "http://upbhulekh.gov.in/"));

TitledPane rajasthanPane = createSchemeCategory("Rajasthan",
        createLink("Apna Khata Rajasthan", "https://apnakhata.raj.nic.in/"),
        createLink("E-Dharti Rajasthan", "https://apnakhata.raj.nic.in/"));

TitledPane tamilnaduPane = createSchemeCategory("Tamil Nadu",
        createLink("TN Patta Chitta", "https://eservices.tn.gov.in/eservicesnew/index.html"),
        createLink("Village Land Records", "https://eservices.tn.gov.in/eservicesnew/index.html"));

TitledPane biharPane = createSchemeCategory("Bihar",
        createLink("Bihar Bhumi", "http://biharbhumi.bihar.gov.in/Biharbhumi/"),
        createLink("Land Records Online", "http://biharbhumi.bihar.gov.in/Biharbhumi/"));

TitledPane madhyaPradeshPane = createSchemeCategory("Madhya Pradesh",
        createLink("MP Bhulekh", "https://mpbhulekh.gov.in/"),
        createLink("Khasra Details", "https://mpbhulekh.gov.in/"));


        VBox allPanes = new VBox(15,
                maharashtraPane, karnatakaPane, telanganaPane, delhiPane, gujaratPane,
                upPane, rajasthanPane, tamilnaduPane, biharPane, madhyaPradeshPane);

        cardBox.getChildren().add(allPanes);
        return cardBox;
    }

    public TitledPane createSchemeCategory(String title, Hyperlink... links) {
        VBox box = new VBox(10);
        box.setPadding(new Insets(15));
        box.setStyle("-fx-background-color: rgba(255, 255, 255, 0.5); -fx-background-radius: 8px;");
        box.getChildren().addAll(links);

        TitledPane pane = new TitledPane(title, box);
        pane.setExpanded(false);
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
