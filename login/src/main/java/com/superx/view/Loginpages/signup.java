package com.superx.view.Loginpages;

import com.superx.Controller.SignupController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class signup {

        public HBox signupBox(com.superx.Controller.ViewController controller) {

                BackgroundImage bg = new BackgroundImage(
                                new Image("images/purple.png", 1920, 1080, false, true),
                                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                                BackgroundSize.DEFAULT);

                DropShadow dp = new DropShadow();
                dp.setOffsetX(3.0);
                dp.setOffsetY(3.0);
                dp.setHeight(100);
                dp.setWidth(100);
                dp.setBlurType(BlurType.GAUSSIAN);
                dp.setColor(Color.rgb(146, 146, 146, 1));

                Rectangle signupBox = new Rectangle();
                signupBox.setArcHeight(100);
                signupBox.setArcWidth(100);
                signupBox.setHeight(900);
                signupBox.setWidth(800);
                signupBox.setFill(Color.rgb(247, 247, 251));
                signupBox.setEffect(dp);

                Image image = new Image("images/logo.png");
                ImageView iv = new ImageView(image);
                iv.setFitHeight(700);
                iv.setPreserveRatio(true);

                VBox logoBox = new VBox(iv);
                logoBox.setAlignment(Pos.CENTER);

                Label signupText = new Label("Sign Up");
                signupText.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 50));
                Label subtext = new Label("to create your account");
                subtext.setFont(Font.font("arial", 10));
                subtext.setTextFill(Color.GREY);
                VBox vbtext = new VBox(10, signupText, subtext);

                Label fNameLabel = new Label("First Name");
                fNameLabel.setFont(Font.font("arial", FontWeight.BOLD, 20));
                TextField fNameField = new TextField();
                fNameField.setPromptText("Enter First Name");
                fNameField.setMinHeight(50);
                fNameField.setPrefWidth(250); // Increased width
                fNameField.setMaxWidth(250); // Set maximum width for consistency
                fNameField.setBorder(new Border(
                                new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, new CornerRadii(5),
                                                BorderWidths.DEFAULT)));
                VBox vbFName = new VBox(10, fNameLabel, fNameField);

                Label lNameLabel = new Label("Last Name");
                lNameLabel.setFont(Font.font("arial", FontWeight.BOLD, 20));
                TextField lNameField = new TextField();
                lNameField.setPromptText("Enter Last Name");
                lNameField.setMinHeight(50);
                lNameField.setPrefWidth(250); // Increased width to match first name
                lNameField.setMaxWidth(250);
                lNameField.setBorder(new Border(
                                new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, new CornerRadii(5),
                                                BorderWidths.DEFAULT)));
                VBox vbLName = new VBox(10, lNameLabel, lNameField);

                HBox nameBox = new HBox(20, vbFName, vbLName);

                Label emailLabel = new Label("Email");
                emailLabel.setFont(Font.font("arial", FontWeight.BOLD, 20));
                TextField emailField = new TextField();
                emailField.setPromptText("Enter Email");
                emailField.setMinHeight(50);
                emailField.setBorder(new Border(
                                new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, new CornerRadii(5),
                                                BorderWidths.DEFAULT)));
                VBox vbemail = new VBox(10, emailLabel, emailField);

                Label passwordLabel = new Label("Password");
                passwordLabel.setFont(Font.font("arial", FontWeight.BOLD, 20));
                PasswordField passwordField = new PasswordField();
                passwordField.setPromptText("Enter Password");
                passwordField.setMinHeight(50);
                passwordField.setBorder(new Border(
                                new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, new CornerRadii(5),
                                                BorderWidths.DEFAULT)));
                Label guideTx = new Label("Use 8 or more characters with a mix of letters, numbers & symbols");
                guideTx.setFont(Font.font("arial", 10));
                guideTx.setTextFill(Color.GREY);
                VBox vbpass = new VBox(10, passwordLabel, passwordField, guideTx);

                Label repeatLabel = new Label("Repeat Password");
                repeatLabel.setFont(Font.font("arial", FontWeight.BOLD, 20));
                PasswordField repeatPasswordField = new PasswordField();
                repeatPasswordField.setPromptText("Enter Password Again");
                repeatPasswordField.setMinHeight(50);
                repeatPasswordField.setBorder(new Border(
                                new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, new CornerRadii(5),
                                                BorderWidths.DEFAULT)));
                VBox vbRepeat = new VBox(10, repeatLabel, repeatPasswordField);

                CheckBox termsBox = new CheckBox();
                Label termsLabel = new Label("I accept the");
                termsLabel.setFont(Font.font("arial", FontWeight.BOLD, 10));
                Hyperlink termsLink = new Hyperlink("terms");
                termsLink.setTextFill(Color.BLUE);
                termsLink.setOnAction(e -> controller.showTermsScene());
                HBox termsAgreement = new HBox(10, termsBox, termsLabel, termsLink);
                termsAgreement.setAlignment(Pos.CENTER_LEFT);

                Image gImage = new Image("images/Group 26.png");
                ImageView gIcon = new ImageView(gImage);
                Button signupGoogle = new Button("Sign Up with Google", gIcon);
                signupGoogle.setMinHeight(40);

                Image mImage = new Image("images/Vector (1).png");
                ImageView mIcon = new ImageView(mImage);
                Button signupMobile = new Button("Sign Up with Mobile No", mIcon);
                signupMobile.setMinHeight(40);

                HBox signupAlt = new HBox(10);
                signupAlt.setAlignment(Pos.CENTER);

                Button signupBtn = new Button("Sign Up");
                signupBtn.setFont(Font.font("arial", FontWeight.BOLD, 20));
                signupBtn.setStyle("-fx-background-color: #007BFF; -fx-text-fill: white; -fx-background-radius: 10;");
                signupBtn.setMaxWidth(750);
                signupBtn.setOnMouseEntered(e -> signupBtn
                                .setStyle("-fx-background-color: #0056b3; -fx-text-fill: white; -fx-background-radius: 10;"));
                signupBtn.setOnMouseExited(e -> signupBtn
                                .setStyle("-fx-background-color: #007BFF; -fx-text-fill: white; -fx-background-radius: 10;"));
                signupBtn.setOnAction(event -> {
                        SignupController signupController = controller.getSignupController();
                        signupController.handleSignup(
                                        fNameField,
                                        lNameField,
                                        emailField,
                                        passwordField,
                                        repeatPasswordField,
                                        termsBox);
                });
                signupBtn.setOnKeyPressed(evt -> {
                        if (evt.getCode() == KeyCode.ENTER) {
                                SignupController signupController = controller.getSignupController();
                                signupController.handleSignup(
                                                fNameField,
                                                lNameField,
                                                emailField,
                                                passwordField,
                                                repeatPasswordField,
                                                termsBox);
                        }
                });
                signupBtn.setDefaultButton(true);

                Text questionText = new Text("Already have an account?");
                questionText.setFont(Font.font("arial", 10));
                questionText.setFill(Color.GREY);
                Hyperlink loginLink = new Hyperlink("Sign In");
                loginLink.setTextFill(Color.BLUE);
                loginLink.setOnAction(e -> controller.showLoginScreen());
                HBox signupFooter = new HBox(10, questionText, loginLink);
                signupFooter.setAlignment(Pos.CENTER);

                VBox vblock = new VBox(10, signupAlt, signupBtn, signupFooter);
                VBox vbForm = new VBox(30, vbtext, nameBox, vbemail, vbpass, vbRepeat, termsAgreement, vblock);
                vbForm.setAlignment(Pos.CENTER_LEFT);
                vbForm.setPadding(new Insets(30));

                StackPane sp = new StackPane(signupBox, vbForm);
                sp.setAlignment(Pos.CENTER_RIGHT);
                sp.setPadding(new Insets(50));

                HBox root = new HBox(250, logoBox, sp);
                root.setPrefSize(1920, 1080);
                root.setBackground(new Background(bg));

                return root;
        }
}
