package com.superx.view.Loginpages;

import com.superx.Controller.ViewController;

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
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class login {

    public HBox getLoginBox(com.superx.Controller.ViewController controller) {

        BackgroundImage bg = new BackgroundImage(
                new Image("images/artistic-blurry-colorful-wallpaper-background.jpg", 1920, 1080, false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);

        DropShadow dp = new DropShadow();
        dp.setOffsetX(3.0);
        dp.setOffsetY(3.0);
        dp.setHeight(100);
        dp.setWidth(100);
        dp.setBlurType(BlurType.GAUSSIAN);
        dp.setColor(Color.rgb(146, 146, 146, 1));

        Rectangle signinBox = new Rectangle();
        signinBox.setArcHeight(100);
        signinBox.setArcWidth(100);
        signinBox.setHeight(800);
        signinBox.setWidth(800);
        signinBox.setFill(Color.rgb(247, 247, 251));
        signinBox.setEffect(dp);

        Image image = new Image(
                "images/logo.png");
                
        ImageView iv = new ImageView(image);
        iv.setFitHeight(700);
        iv.setPreserveRatio(true);
        
        VBox logoBox = new VBox(iv);
        logoBox.setAlignment(Pos.CENTER);
        Label signinText = new Label("Sign In");
        signinText.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 50));
        Label subtext = new Label("to your account");
        subtext.setFont(Font.font("arial", 10));
        subtext.setTextFill(Color.GREY);
        VBox vbtext = new VBox(10, signinText, subtext);

        Label emailLabel = new Label("Email");
        emailLabel.setFont(Font.font("arial", FontWeight.BOLD, 20));
        TextField emailField = new TextField();
        emailField.setPromptText("Enter Email");
        emailField.setMinHeight(50);
        emailField.setBorder(new Border(
                new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, new CornerRadii(5), BorderWidths.DEFAULT)));
        VBox vbemail = new VBox(10, emailLabel, emailField);

        Label passwordLabel = new Label("Password");
        passwordLabel.setFont(Font.font("arial", FontWeight.BOLD, 20));
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter Password");
        passwordField.setMinHeight(50);
        passwordField.setBorder(new Border(
                new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, new CornerRadii(5), BorderWidths.DEFAULT)));
        Hyperlink forgotPassword = new Hyperlink("Forgot Password ?");
        forgotPassword.setTextFill(Color.BLUE);
        forgotPassword.setAlignment(Pos.CENTER_RIGHT);
        VBox vbpass = new VBox(10, passwordLabel, passwordField);

       

        Image gImage = new Image("images/Group 26.png");
        ImageView gIcon = new ImageView(gImage);
        Button signinGoogle = new Button("Sign Up with Google", gIcon);
        signinGoogle.setMinHeight(40);

        Image mImage = new Image("images/Vector (1).png");
        ImageView mIcon = new ImageView(mImage);
        Button signinMobile = new Button("Sign Up with Mobile No", mIcon);
        signinMobile.setMinHeight(40);

        HBox signupAlt = new HBox(10);
        signupAlt.setAlignment(Pos.CENTER);

        Button signInButton = new Button("Sign In");
        signInButton.setFont(Font.font("arial", FontWeight.BOLD, 20));
        signInButton.setStyle("-fx-background-color: #007BFF; -fx-text-fill: white; -fx-background-radius: 10;");
        signInButton.setMaxWidth(750);
        signInButton.setOnMouseEntered(e -> signInButton
                .setStyle("-fx-background-color: #0056b3; -fx-text-fill: white; -fx-background-radius: 10;"));
        signInButton.setOnMouseExited(e -> signInButton
                .setStyle("-fx-background-color: #007BFF; -fx-text-fill: white; -fx-background-radius: 10;"));
        signInButton.setOnAction(event -> controller.getLoginController().handleLogin(emailField, passwordField));
        signInButton.setOnKeyPressed(evt->{
if (evt.getCode() == KeyCode.ENTER) {
        controller.getLoginController().handleLogin(emailField, passwordField);
    }        });
    signInButton.setDefaultButton(true);

        Text questionText = new Text("Not a member yet?");
        questionText.setFont(Font.font("arial", 10));
        questionText.setFill(Color.GREY);
        Hyperlink signupLink = new Hyperlink("Sign Up");
        signupLink.setTextFill(Color.BLUE);
        signupLink.setOnAction(event -> controller.getLoginController().navigateToSignup());
        HBox signinFooter = new HBox(10, questionText, signupLink);
        signinFooter.setAlignment(Pos.CENTER);
        HBox adminAccessBox = new HBox();
adminAccessBox.setAlignment(Pos.CENTER);
adminAccessBox.setPadding(new Insets(20, 0, 0, 0));

Hyperlink adminLoginLink = new Hyperlink("Admin Access →");
adminLoginLink.setFont(Font.font("Inter", FontWeight.MEDIUM, 14));
adminLoginLink.setStyle("-fx-text-fill: #6366f1;");
adminLoginLink.setOnAction(e -> controller.showAdminLoginScreen());

adminAccessBox.getChildren().add(adminLoginLink);



        VBox vblock = new VBox(10, signupAlt, signInButton, signinFooter);
        VBox vbForm = new VBox(50, vbtext, vbemail, vbpass, vblock,adminAccessBox);
        vbForm.setAlignment(Pos.CENTER_LEFT);
        vbForm.setPadding(new Insets(30));

        StackPane sp = new StackPane(signinBox, vbForm);
        sp.setAlignment(Pos.CENTER_RIGHT);
        sp.setPadding(new Insets(50));

        HBox root = new HBox(250, logoBox, sp);
        root.setPrefSize(1920, 1080);
        root.setBackground(new Background(bg));

        return root;
    }
}
