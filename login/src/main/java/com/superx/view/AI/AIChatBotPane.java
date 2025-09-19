package com.superx.view.AI;

import org.fxmisc.richtext.InlineCssTextArea;
import com.superx.Controller.AI.AiApiController;
import com.superx.Controller.AI.FormatController;
import com.superx.Controller.AI.NotesController;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * A chatbot assistant pane for embedding in LegalArchives page.
 */
public class AIChatBotPane extends VBox {

    /* ----------  FIELDS  ---------- */
    private final VBox            chatBox     = new VBox(12);
    private final ScrollPane      scrollPane  = new ScrollPane(chatBox);
    private final AiApiController aiController     = new AiApiController();
    private final FormatController formatController = new FormatController();
    private final NotesController  notesController  = new NotesController();
    private final String userName;
    private VBox typingIndicator;           // shown while AI “thinks”

    /* ----------  CTOR  ---------- */
    public AIChatBotPane(String userName) {
        this.userName = userName;
        buildUI();
    }

    /* ----------  UI BUILDING  ---------- */
    private void buildUI() {
        setSpacing(15);
        setPadding(new Insets(24, 18, 24, 18));
        setStyle("-fx-background-color: rgba(255,255,255,0.96);" +
                 "-fx-border-color:#e0e7ff;-fx-border-width:2;" +
                 "-fx-border-radius:18;-fx-background-radius:18;");

        /*  title  */
        Label title = new Label("AI Legal Assistant");
        title.setFont(Font.font("Inter", FontWeight.BOLD, 18));
        title.setStyle("-fx-text-fill:#1e3a8a;");

        /*  chat viewport  */
        chatBox.setPadding(new Insets(15));
        chatBox.setStyle("-fx-background-color:transparent;");
        scrollPane.setFitToWidth(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setStyle("-fx-background-color:transparent;");
        VBox.setVgrow(scrollPane, Priority.ALWAYS);

        /*  input row  */
        HBox inputBar = buildInputBar();

        /*  welcome message  */
        addBotBubble(
            null,
            "👋 **Hello!**  I'm your AI Legal Assistant.  Ask me anything about laws, procedures or your rights."
        );

        getChildren().addAll(title, scrollPane, inputBar);
    }

    private HBox buildInputBar() {
        TextField input = new TextField();
        input.setPromptText("Ask your legal question …");
        input.setFont(Font.font("Inter", 14));
        input.setPrefHeight(42);
        input.setStyle("-fx-background-radius:21;-fx-border-radius:21;" +
                       "-fx-border-color:#cbd5e1;-fx-border-width:1.5;" +
                       "-fx-padding:0 18 0 18;");

        Button send = new Button("Send ✨");
        send.setFont(Font.font("Inter", FontWeight.SEMI_BOLD, 13));
        send.setCursor(Cursor.HAND);
        send.setStyle(buttonBlue());
        send.setDefaultButton(true);

        /* hover effect */
        send.setOnMouseEntered(e -> send.setStyle(buttonBlueHover()));
        send.setOnMouseExited (e -> send.setStyle(buttonBlue()));

        send.setOnAction(e -> {
            String q = input.getText().trim();
            if (q.isEmpty()) return;
            addUserBubble(q);
            showTyping();

            new Thread(() -> {
                String response = aiController.callGeminiAPI(q);
                Platform.runLater(() -> {
                    hideTyping();
                    addBotBubble(q, response);
                });
            }).start();

            input.clear();
        });

        HBox bar = new HBox(12, input, send);
        bar.setAlignment(Pos.CENTER);
        HBox.setHgrow(input, Priority.ALWAYS);
        return bar;
    }

    /* ----------  MESSAGE BUBBLES  ---------- */
    private void addUserBubble(String text) {
        Label lbl = new Label(text);
        lbl.setWrapText(true);
        lbl.setFont(Font.font("Inter", 14));
        lbl.setStyle("-fx-background-color:#ef476f;" +    // vivid pink-red
                     "-fx-text-fill:white;" +
                     "-fx-background-radius:18;" +
                     "-fx-padding:12 16 12 16;");

        HBox wrap = new HBox(lbl);
        wrap.setAlignment(Pos.CENTER_RIGHT);
        chatBox.getChildren().add(wrap);
        Platform.runLater(() -> scrollPane.setVvalue(1));
    }

    private void addBotBubble(String question, String answer) {
        InlineCssTextArea area = new InlineCssTextArea();
        
        area.setWrapText(true);
        area.setEditable(false);
        area.setPrefWidth(500);  
                  // wider default
        /*  NOTE: RichTextFX doesn’t obey -fx-text-fill;
            we enforce inner text colour like this  */
        
area.setStyle(
        "-fx-background-color:#4366bb;" +
        "-fx-border-color:#c7d2fe;" +
        "-fx-border-radius:16;" +
        "-fx-background-radius:16;" +
        "-fx-padding:14;" +
        "-fx-font-size:14px;"
    );

    /* ---------------------------------------------------- */
    /* 1) put the reply in first                            */
    area.replaceText(answer);

    /* 2) let your formatter (markdown, code, …) run        */
    formatController.formatAndDisplayAIResponse(area, answer);

    /* 3) NOW colour every character                        */
    area.setStyle(0, area.getLength(), "-fx-fill:#0f172a;");

    /*  this line colours EVERY character */
    area.setStyle(0, area.getLength(), "-fx-fill:#0f172a;");
        formatController.formatAndDisplayAIResponse(area, answer);

        Button add = new Button("Add to Notes");
        add.setFont(Font.font("Inter", 11));
        add.setCursor(Cursor.HAND);
        add.setStyle(buttonGreen());
        add.setOnMouseEntered(e -> add.setStyle(buttonGreenHover()));
        add.setOnMouseExited (e -> add.setStyle(buttonGreen()));
        add.setOnAction(e -> {
            notesController.addNote(new Note(question, answer, userName));
            add.setText("Added ✔");
            add.setDisable(true);
            add.setStyle("-fx-background-color:#9ca3af;-fx-text-fill:white;");
        });

        VBox v = new VBox(8, area);
        HBox wrap = new HBox(v);
        wrap.setAlignment(Pos.CENTER_LEFT);
        chatBox.getChildren().add(wrap);
        Platform.runLater(() -> scrollPane.setVvalue(1));
    }

    /* ----------  TYPING  ---------- */
    private void showTyping() {
        Label dot = new Label("🤖 typing …");
        dot.setStyle("-fx-text-fill:#6b7280;");
        typingIndicator = new VBox(dot);
        typingIndicator.setPadding(new Insets(6));
        typingIndicator.setAlignment(Pos.CENTER_LEFT);
        chatBox.getChildren().add(typingIndicator);
        Platform.runLater(() -> scrollPane.setVvalue(1));
    }
    private void hideTyping() {
        chatBox.getChildren().remove(typingIndicator);
    }

    /* ----------  BUTTON STYLES  ---------- */
    private String buttonBlue() {
        return "-fx-background-color:linear-gradient(to right,#3b82f6,#1d4ed8);" +
               "-fx-text-fill:white;-fx-background-radius:18;-fx-padding:9 20;";
    }
    private String buttonBlueHover() {
        return "-fx-background-color:linear-gradient(to right,#1d4ed8,#1e40af);" +
               "-fx-text-fill:white;-fx-background-radius:18;-fx-padding:9 20;";
    }
    private String buttonGreen() {
        return "-fx-background-color:#10b981;-fx-text-fill:white;" +
               "-fx-background-radius:14;-fx-padding:6 14;";
    }
    private String buttonGreenHover() {
        return "-fx-background-color:#059669;-fx-text-fill:white;" +
               "-fx-background-radius:14;-fx-padding:6 14;";
    }
}
