package com.superx.view.Profile;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.cloud.FirestoreClient;
import com.superx.Controller.ViewController;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

/**
 * AccountSettings page with fully-working “Delete My Account” feature.
 * After successful deletion the user is returned to the login screen.
 */
public class AccountSetting {

    /* ────────── UI state ────────── */
    private Stage accStage;
    private Label welcomeLabel;

    public void setAccStage(Stage accStage) { this.accStage = accStage; }
    public void setAccScene(Scene accScene) { /* reserved */ }

    /* ────────── factory ────────── */
    public BorderPane accountSettingsBox(Runnable showProfileScreen,
                                         Runnable showSecurityScreen,
                                         Runnable showDocScene,
                                         Runnable showNotification,
                                         Runnable showStorage,
                                         Runnable showLoginScreen) {

        /* ── top bar ── */
        Label logo = new Label("e-help Desk");
        logo.setFont(Font.font("Inter", FontWeight.BOLD, 20));
        logo.setStyle("-fx-text-fill:#1e3a8a;");

        welcomeLabel = new Label("Welcome");
        welcomeLabel.setFont(Font.font("Inter", FontWeight.BOLD, 20));
        welcomeLabel.setStyle("-fx-text-fill:#4b5563;");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        HBox topBar = new HBox(logo, welcomeLabel);
        topBar.setPadding(new Insets(15, 40, 15, 40));
        topBar.setAlignment(Pos.CENTER_LEFT);
        topBar.setStyle("-fx-background-color:#f5f9ff; -fx-border-width:0 0 1 0; -fx-border-color:#e0e7ff;");

        /* ── sidebar ── */
        VBox iconCol = createIconColumn(showDocScene);
        VBox navList = createNavList(showProfileScreen, showSecurityScreen, showNotification, showStorage);
        HBox leftSidebar = new HBox(10, iconCol, navList);

        /* ── main content ── */
        VBox content = new VBox(30);
        content.setPadding(new Insets(20, 40, 40, 40));

        Label title = new Label("Account Settings");
        title.setFont(Font.font("Inter", FontWeight.BOLD, 32));
        title.setStyle("-fx-text-fill:#1e3a8a;");

        content.getChildren().addAll(
                title,
                createPasswordCard(),
                createSaveBar(),
                createDeleteCard(showLoginScreen)   // pass login callback here
        );

        ScrollPane scroll = new ScrollPane(content);
        scroll.setFitToWidth(true);
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setStyle("-fx-background-color:transparent; -fx-background-insets:0;");

        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color:linear-gradient(from 0% 0% to 100% 100%, #e6f0ff, #d6e4ff);" +
                      "-fx-font-family:'Inter','Segoe UI',sans-serif;");
        root.setTop(topBar);
        root.setLeft(leftSidebar);
        root.setCenter(scroll);

        loadUserName();          // populate “Welcome …”
        return root;
    }

    /* ───────────────── helper UI blocks ───────────────── */

    private VBox createIconColumn(Runnable showDocScene) {
        VBox col = new VBox(20);
        col.setPadding(new Insets(20, 15, 20, 15));
        col.setAlignment(Pos.TOP_CENTER);
        col.setStyle("-fx-background-color:#f5f9ff;");

        String[][] icons = {{"👤", ""}, {" 🔙 ", ""}};
        for (String[] it : icons) {
            Label icon = new Label(it[0]);
            icon.setStyle("-fx-font-size:40px;");
            icon.setCursor(Cursor.HAND);

            if ("👤".equals(it[0])) {
                icon.setStyle(
                    "-fx-font-size:40px; -fx-text-fill:#3b82f6;" +
                    "-fx-effect:dropshadow(gaussian, rgba(59,130,246,0.5),10,0,0,0);");
            } else {
                icon.setStyle("-fx-font-size:40px; -fx-text-fill:#4b5563;");
                icon.setOnMouseEntered(e -> icon.setStyle(
                    "-fx-font-size:40px; -fx-text-fill:#3b82f6;" +
                    "-fx-effect:dropshadow(gaussian, rgba(59,130,246,0.5),10,0,0,0);"));
                icon.setOnMouseExited(e -> icon.setStyle("-fx-font-size:40px; -fx-text-fill:#4b5563;"));
                icon.setOnMouseClicked(e -> showDocScene.run());
            }
            col.getChildren().add(icon);
        }
        return col;
    }

    private VBox createNavList(Runnable showProfile,
                               Runnable showSecurity,
                               Runnable showNotification,
                               Runnable showStorage) {

        VBox list = new VBox(8);
        list.setPadding(new Insets(20, 15, 20, 15));
        list.setAlignment(Pos.TOP_CENTER);
        list.setStyle("-fx-background-color:#f5f9ff; -fx-pref-width:280px;");

        String[][] nav = {
                {"👤", "Profile"},
                {"⚙️", "Account Settings"},
                {"🛡️", "Security"},
                {"🔔", "Notifications"},
                {"🏬", "Storage"}
        };

        for (String[] item : nav) {
            Label icon = new Label(item[0]);
            icon.setStyle("-fx-font-size:40px;");
            Label txt = new Label(item[1]);

            HBox row = new HBox(15, icon, txt);
            row.setAlignment(Pos.CENTER_LEFT);
            row.setCursor(Cursor.HAND);

            if ("Account Settings".equals(item[1])) {
                txt.setStyle("-fx-font-size:15px; -fx-font-weight:600; -fx-text-fill:#3b82f6;");
                row.setStyle("-fx-padding:12 15; -fx-background-radius:10; -fx-background-color:#e0e7ff;");
            } else {
                txt.setStyle("-fx-font-size:15px; -fx-font-weight:500; -fx-text-fill:#4b5563;");
                row.setStyle("-fx-padding:12 15; -fx-background-radius:10; -fx-background-color:transparent;");
                row.setOnMouseEntered(e -> row.setStyle(
                    "-fx-padding:12 15; -fx-background-radius:10; -fx-background-color:#eef2ff;"));
                row.setOnMouseExited(e -> row.setStyle(
                    "-fx-padding:12 15; -fx-background-radius:10; -fx-background-color:transparent;"));
            }

            switch (item[1]) {
                case "Profile"       -> row.setOnMouseClicked(e -> showProfile.run());
                case "Security"      -> row.setOnMouseClicked(e -> showSecurity.run());
                case "Notifications" -> row.setOnMouseClicked(e -> showNotification.run());
                case "Storage"       -> row.setOnMouseClicked(e -> showStorage.run());
            }
            list.getChildren().add(row);
        }
        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);
        list.getChildren().add(spacer);

        return list;
    }

    private VBox createPasswordCard() {
        VBox card = new VBox(20);
        card.setStyle(
            "-fx-background-color:rgba(255,255,255,0.7); -fx-background-radius:12; -fx-padding:25;");

        Label head = new Label("Change Password");
        head.setFont(Font.font("Inter", FontWeight.SEMI_BOLD, 20));
        head.setStyle("-fx-text-fill:#1e3a8a;");

        GridPane grid = new GridPane();
        grid.setHgap(20);
        grid.setVgap(15);

        grid.add(new Label("Current Password"),         0, 0);
        grid.add(new Label("New Password"),             0, 2);
        grid.add(new Label("Confirm New Password"),     1, 2);

        PasswordField current = new PasswordField();
        PasswordField newer   = new PasswordField();
        PasswordField confirm = new PasswordField();

        current.setPromptText("Enter current password");
        newer  .setPromptText("Enter new password");
        confirm.setPromptText("Confirm new password");

        GridPane.setColumnSpan(current, 2);
        grid.add(current, 0, 1);
        grid.add(newer,   0, 3);
        grid.add(confirm, 1, 3);

        grid.getChildren().forEach(n -> n.setStyle(
            "-fx-font-size:14; -fx-font-weight:500; -fx-text-fill:#1e3a8a;"));

        card.getChildren().addAll(head, grid);
        return card;
    }

    private HBox createSaveBar() {
        Button save = new Button("Save All Changes");
        save.setCursor(Cursor.HAND);
        save.setFont(Font.font("Inter", FontWeight.BOLD, 15));
        save.setStyle(
            "-fx-background-color:#3b82f6; -fx-text-fill:white; " +
            "-fx-background-radius:10; -fx-padding:10 25;");

        HBox bar = new HBox(save);
        bar.setAlignment(Pos.CENTER_RIGHT);
        return bar;
    }

    private VBox createDeleteCard(Runnable showLoginScreen) {
        VBox card = new VBox(20);
        card.setStyle(
            "-fx-background-color:rgba(255,255,255,0.7); -fx-background-radius:12; -fx-padding:25;" +
            "-fx-border-color:#fca5a5; -fx-border-width:1; -fx-border-radius:12;");

        Label title = new Label("Delete Account");
        title.setFont(Font.font("Inter", FontWeight.BOLD, 20));
        title.setStyle("-fx-text-fill:#b91c1c;");

        Label warn = new Label("Once you delete your account, there is no going back. Please be certain.");
        warn.setStyle("-fx-text-fill:#4b5563;");

        Button delete = new Button("Delete My Account");
        delete.setCursor(Cursor.HAND);
        delete.setFont(Font.font("Inter", FontWeight.BOLD, 15));
        delete.setStyle(
            "-fx-background-color:#dc2626; -fx-text-fill:white; -fx-background-radius:10; -fx-padding:10 25;");
        delete.setOnAction(e -> confirmAndDeleteAccount(showLoginScreen));

        HBox box = new HBox(delete);
        box.setAlignment(Pos.CENTER_RIGHT);
        card.getChildren().addAll(title, warn, box);
        return card;
    }

    /* ───────────────── deletion logic ───────────────── */

    private void confirmAndDeleteAccount(Runnable showLoginScreen) {

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION,
            "This will permanently delete your authentication record and ALL data stored in Firestore.\n\nProceed?",
            ButtonType.YES, ButtonType.CANCEL);
        confirm.setTitle("Confirm delete account");
        Optional<ButtonType> res = confirm.showAndWait();
        if (res.isEmpty() || res.get() != ButtonType.YES) return;

        ProgressIndicator pi1 = new ProgressIndicator();
        

        Task<Void> task = new Task<>() {
            @Override protected Void call() throws Exception {
                String uid = ViewController.getCurrentUserId();
                if (uid == null) throw new IllegalStateException("No logged-in user.");

                Firestore db = FirestoreClient.getFirestore();
                deleteCollection(db.collection("users")
                                   .document(uid)
                                   .collection("documents"), 50);
                db.collection("users").document(uid).delete().get();
                FirebaseAuth.getInstance().deleteUser(uid);
                return null;
            }
        };

        task.setOnSucceeded(e -> {
            
            new Alert(Alert.AlertType.INFORMATION,
                      "Account deleted successfully.").showAndWait();
            ViewController.setCurrentUserId(null);
            ViewController.setCurrentUserRole(null);
            showLoginScreen.run();    // back to login screen
        });

        task.setOnFailed(e -> {
            
            Throwable ex = task.getException();
            ex.printStackTrace();
            new Alert(Alert.AlertType.ERROR,
                      "Failed to delete account:\n" + ex.getMessage()).showAndWait();
        });

        new Thread(task).start();
    }

    /** Recursively deletes docs in batches of {@code batchSize}. */
    private void deleteCollection(CollectionReference col, int batchSize)
            throws InterruptedException, ExecutionException {

        ApiFuture<QuerySnapshot> future = col.limit(batchSize).get();
        List<QueryDocumentSnapshot> docs = future.get().getDocuments();
        if (docs.isEmpty()) return;

        WriteBatch batch = col.getFirestore().batch();
        for (QueryDocumentSnapshot d : docs) batch.delete(d.getReference());
        batch.commit().get();
        deleteCollection(col, batchSize);
    }

    /* ────────── welcome label ────────── */
    private void loadUserName() {
        String uid = ViewController.getCurrentUserId();
        if (uid == null) return;

        Firestore db = FirestoreClient.getFirestore();
        DocumentReference ref = db.collection("users").document(uid);
        ApiFuture<DocumentSnapshot> fut = ref.get();

        fut.addListener(() -> {
            try {
                DocumentSnapshot snap = fut.get();
                String name = snap.exists() ? snap.getString("firstName") : null;
                if (name == null || name.isBlank()) name = "User";
                String finalName = name;
                Platform.runLater(() -> welcomeLabel.setText("Welcome " + finalName));
            } catch (Exception ex) {
                ex.printStackTrace();
                Platform.runLater(() -> welcomeLabel.setText("Welcome User"));
            }
        }, Platform::runLater);
    }
}
