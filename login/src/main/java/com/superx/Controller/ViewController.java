package com.superx.Controller;

import com.superx.view.Aboutus.AboutUsPage;
import com.superx.view.Document.documents;
import com.superx.view.Loginpages.login;
import com.superx.view.Loginpages.signup;
import com.superx.view.Loginpages.terms;
import com.superx.view.Notification.Notificationtab;
import com.superx.view.Profile.AccountSetting;
import com.superx.view.Profile.Notification;
import com.superx.view.Profile.ProfilePage;
import com.superx.view.Profile.SecurityPage;
import com.superx.view.Profile.Storage;
import com.superx.view.admin.adminDashboard;
import com.superx.view.admin.adminlogin;
import com.superx.view.landRecords.landRecords;
import com.superx.view.landRecords.archive.legalArchives;
import com.superx.view.landRecords.archive.archiveSubfiles.courtProcedures;
import com.superx.view.landRecords.archive.archiveSubfiles.introToLaw;
import com.superx.view.landRecords.archive.archiveSubfiles.rightRespons;
import com.superx.view.landRecords.subfilesLandrecords.lrecords;
import com.superx.view.landRecords.subfilesLandrecords.propertyReg;
import com.superx.view.legalTech.governOfficail;
import com.superx.view.legalTech.legalCaseManagement;
import com.superx.view.legalTech.guidedFormBox;
import com.superx.view.rti.RtiPage;

import javafx.application.HostServices;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ViewController {
    private LoginController loginController;
    private SignupController signupController;
    private AdminLoginController adminLoginController;
    private HostServices hostServices;
    private Stage mainStage;
    private static String currentUserId;
    private static String currentUserRole;
    

    private Scene loginScene, signupScene, forgotScene, termsScene, docScene, profileScene,
            accScene, securityScene, notiScene, stoScene, landRecordsScene, rtiScene,
            notiScenetab, legalScene, legalcaseScene, governOffScene, guidedFormScene,
            fileRtiAppScene, rtiGuidelinesScene, lodgeGrievanceScene, courtProceduresScene,
            introToLawScene, rightResponsScene, lRecordsScene, propertyRegScene,
            adminLoginScene, adminDashboardScene, aboutusScene;

    public ViewController(Stage stage, HostServices hostServices) {
        this.mainStage = stage;
        this.hostServices = hostServices;
        this.loginController = new LoginController(this);
        this.signupController = new SignupController(this);
        this.adminLoginController = new AdminLoginController(this);
        showLoginScreen();
    }

    // User management methods
    public static String getCurrentUserId() {
        return currentUserId;
    }

    public static void setCurrentUserId(String uid) {
        currentUserId = uid;
    }

    public static String getCurrentUserRole() {
        return currentUserRole;
    }

    public static void setCurrentUserRole(String role) {
        currentUserRole = role;
    }

    public static boolean isCurrentUserAdmin() {
        return "admin".equals(currentUserRole) || "super_admin".equals(currentUserRole);
    }

    // Controller getters
    public LoginController getLoginController() {
        return loginController;
    }

    public SignupController getSignupController() {
        return signupController;
    }

    public AdminLoginController getAdminLoginController() {
        return adminLoginController;
    }

    // Admin screens
    public void showAdminLoginScreen() {
        adminlogin adminLoginPage = new adminlogin();
        adminLoginScene = new Scene(adminLoginPage.getAdminLoginBox(this), 1920, 1080);
        mainStage.setScene(adminLoginScene);
        mainStage.setTitle("Admin Login - e-help Desk");
    }

    public void showAdminDashboard() {
        adminDashboard adminDashboard = new com.superx.view.admin.adminDashboard();
        adminDashboardScene = new Scene(
                adminDashboard.adminDashboardScene(this::showLoginScreen), 1920, 1080);
        adminDashboard.setAdminStage(mainStage);
        adminDashboard.setAdminScene(adminDashboardScene);
        mainStage.setScene(adminDashboardScene);
        mainStage.setTitle("Admin Dashboard - e-help Desk");
    }

    // Authentication screens
    public void showLoginScreen() {
        setCurrentUserId(null);
        setCurrentUserRole(null);
        login loginPage = new login();
        loginScene = new Scene(loginPage.getLoginBox(this), 1920, 1080);
        mainStage.setScene(loginScene);
        mainStage.setTitle("Login - e-help Desk");
    }

    public void showSignupScreen() {
        signup signupPage = new signup();
        signupScene = new Scene(signupPage.signupBox(this), 1920, 1080);
        mainStage.setScene(signupScene);
        mainStage.setTitle("Sign Up - e-help Desk");
    }

    // public void showForgotScreen() {
    //     forgot forgotPage = new forgot();
    //     forgotScene = new Scene(forgotPage.forgotBox(this::showLoginScreen, this), 1920, 1080);
    //     forgotPage.setForgotStage(mainStage);
    //     forgotPage.setForgotScene(forgotScene);
    //     mainStage.setScene(forgotScene);
    //     mainStage.setTitle("Reset Password - e-help Desk");
    // }

    public void showTermsScene() {
        terms termsPage = new terms();
        termsScene = new Scene(termsPage.termsBox(this::showSignupScreen), 1920, 1080);
        termsPage.setTermsStage(mainStage);
        termsPage.setTermsScene(termsScene);
        mainStage.setScene(termsScene);
        mainStage.setTitle("Terms & Conditions - e-help Desk");
    }

    // Main application screens
    public void showDocScene() {
        documents docPage = new documents();
        docScene = new Scene(
                docPage.docScenBox(this::showProfileScreen, this::showLoginScreen, this::showLegal,
                        this::showLandRecordsScreen, this::showRTI, this::shownotification2,
                        this::showarchive, this::showAboutUsScreen),
                1920, 1080);
        docPage.setDocStage(mainStage);
        docPage.setDocScene(docScene);
        mainStage.setScene(docScene);
        mainStage.setTitle("Documents - e-help Desk");
    }

    public void shownotification2() {
        Notificationtab notipage = new Notificationtab();
        notiScenetab = new Scene(notipage.notificationPage(this::showDocScene), 1920, 1080);
        notipage.setNoti2Stage(mainStage);
        notipage.setNoti2Scene(notiScenetab);
        mainStage.setScene(notiScenetab);
        mainStage.setTitle("Notifications - e-help Desk");
    }

    public void showAboutUsScreen() {
        AboutUsPage aboutUsPage = new AboutUsPage();
        aboutusScene = new Scene(aboutUsPage.createAboutUsBox(
                this::showProfileScreen,
                this::showLoginScreen,
                this::showLegal,
                this::showDocScene,
                this::showLandRecordsScreen,
                this::showRTI,
                this::showarchive,
                this::shownotification2
        ), 1920, 1080);
        aboutUsPage.setAboutStage(mainStage);
        aboutUsPage.setAboutusScene(aboutusScene);
        mainStage.setScene(aboutusScene);
        mainStage.setTitle("About Us - e-help Desk");
    }

    // Profile screens
    public void showProfileScreen() {
        ProfilePage profile = new ProfilePage();
        profileScene = new Scene(profile.profilebox(this::showAccountScreen, this::showSecurityScreen,
                this::showDocScene, this::showNotification, this::showStorage), 1920, 1080);
        profile.setProfStage(mainStage);
        profile.setProfScene(profileScene);
        mainStage.setScene(profileScene);
        mainStage.setTitle("Profile - e-help Desk");
    }

    public void showAccountScreen() {
        AccountSetting accPage = new AccountSetting();
        accScene = new Scene(accPage.accountSettingsBox(this::showProfileScreen, this::showSecurityScreen,
                this::showDocScene, this::showNotification, this::showStorage,this::showLoginScreen), 1920, 1080);
        accPage.setAccStage(mainStage);
        accPage.setAccScene(accScene);
        mainStage.setScene(accScene);
        mainStage.setTitle("Account Settings - e-help Desk");
    }

    public void showSecurityScreen() {
        SecurityPage secPage = new SecurityPage();
        securityScene = new Scene(secPage.securityPageBox(this::showProfileScreen, this::showAccountScreen,
                this::showDocScene, this::showNotification,this::showNotification ,this::showStorage), 1920, 1080);
        secPage.setSecStage(mainStage);
        secPage.setSecscene(securityScene);
        mainStage.setScene(securityScene);
        mainStage.setTitle("Security - e-help Desk");
    }

    public void showNotification() {
        Notification notpage = new Notification();
        notiScene = new Scene(notpage.notificationPageBox(this::showDocScene, this::showAccountScreen,
                this::showSecurityScreen, this::showProfileScreen, this::showStorage), 1920, 1080);
        notpage.setNotiStage(mainStage);
        notpage.setNotiScene(notiScene);
        mainStage.setScene(notiScene);
        mainStage.setTitle("Notification Settings - e-help Desk");
    }

    public void showStorage() {
        Storage stopage = new Storage();
        stoScene = new Scene(stopage.createStoragePage(this::showProfileScreen, this::showAccountScreen,
                this::showSecurityScreen, this::showNotification,this::showDocScene), 1920, 1080);
        stopage.setStoStage(mainStage);
        stopage.setStoScene(stoScene);
        mainStage.setScene(stoScene);
        mainStage.setTitle("Storage - e-help Desk");
    }

    // Legal screens
    public void showLegal() {
    legalCaseManagement Page = new legalCaseManagement();
    Page.setHostServices(hostServices);
    legalcaseScene = new Scene(Page.legalmanabox(this::showProfileScreen, this::showDocScene,
            this::showLandRecordsScreen, this::showRTI, this::showarchive, this::showgovOff, this::showGuidedForm,
            this::showAboutUsScreen, this::shownotification2, this::showLoginScreen), // Added missing parameters
            1920, 1080);
    Page.setLegalCaseStage(mainStage);
    Page.setLegalCaseScene(legalcaseScene);
    mainStage.setScene(legalcaseScene);
    mainStage.setTitle("Legal Case Management - e-help Desk");
}


    public void showgovOff() {
        governOfficail Page = new governOfficail();
        Page.setHostServices(hostServices);
        governOffScene = new Scene(Page.govOff(this::showDocScene, this::showarchive,this::showLegal), 1920, 1080);
        Page.setGuidedFormStage(mainStage);
        Page.setGuidedFormScene(governOffScene);
        mainStage.setScene(governOffScene);
        mainStage.setTitle("Government Officials - e-help Desk");
    }

    public void showGuidedForm() {
        guidedFormBox Page = new guidedFormBox();
        Page.setHostServices(hostServices);
        guidedFormScene = new Scene(Page.rtiPageBox(this::showProfileScreen, this::showLoginScreen, this::showDocScene,this::showLegal),
                1920, 1080);
        Page.setGuidedFormStage(mainStage);
        Page.setGuidedFormScene(guidedFormScene);
        mainStage.setScene(guidedFormScene);
        mainStage.setTitle("Guided Forms - e-help Desk");
    }

    // Land Records screens
    public void showLandRecordsScreen() {
        landRecords landRecordsPage = new landRecords();
        landRecordsScene = new Scene(landRecordsPage.landRecordsBox(this::showProfileScreen, this::showLoginScreen,
                this::showLegal, this::showDocScene, this::showRTI, this::showarchive, this::showLRecords,
                this::showPropertyReg, this::showAboutUsScreen,this::shownotification2), 1920, 1080);
        landRecordsPage.setLandStage(mainStage);
        landRecordsPage.setLandScene(landRecordsScene);
        mainStage.setScene(landRecordsScene);
        mainStage.setTitle("Land Records - e-help Desk");
    }

    public void showLRecords() {
        lrecords lRecordsPage = new lrecords();
        lRecordsPage.setHostServices(hostServices);
        lRecordsScene = new Scene(lRecordsPage.landRecordsBox(this::showLandRecordsScreen), 1920, 1080);
        lRecordsPage.setLandRedordsStage(mainStage);
        lRecordsPage.setLandRecordsScene(lRecordsScene);
        mainStage.setScene(lRecordsScene);
        mainStage.setTitle("Land Records Search - e-help Desk");
    }

    public void showPropertyReg() {
        propertyReg propertyRegPage = new propertyReg();
        propertyRegPage.setHostServices(hostServices);
        propertyRegScene = new Scene(propertyRegPage.propertyRegBox(this::showLandRecordsScreen), 1920, 1080);
        propertyRegPage.setPropertyRegStage(mainStage);
        propertyRegPage.setPropertyRegScene(propertyRegScene);
        mainStage.setScene(propertyRegScene);
        mainStage.setTitle("Property Registration - e-help Desk");
    }

    // RTI screens
   public void showRTI() {
    RtiPage Page = new RtiPage();
    rtiScene = new Scene(Page.rtiPageBox(this::showProfileScreen, this::showLoginScreen,
            this::showLegal, this::showDocScene, this::showLandRecordsScreen,
            this::showarchive, this::shownotification2,
            this::showFileRtiApp, this::showRtiGuidelines, this::showLodgeGrievance, 
            this::showAboutUsScreen), 1920, 1080); // Added showAboutUsScreen parameter
    Page.setRtiStage(mainStage);
    Page.setRtiScene(rtiScene);
    mainStage.setScene(rtiScene);
    mainStage.setTitle("RTI & Grievance - e-help Desk");
}


    public void showFileRtiApp() {
        com.superx.view.rti.subFiles.fileRtiApp fileRtiAppPage = new com.superx.view.rti.subFiles.fileRtiApp();
        fileRtiAppPage.setHostServices(hostServices);
        fileRtiAppScene = new Scene(fileRtiAppPage.fileRtiAppBox(this::showRTI), 1920, 1080);
        fileRtiAppPage.setFileRtiAppStage(mainStage);
        fileRtiAppPage.setFileRtiAppScene(fileRtiAppScene);
        mainStage.setScene(fileRtiAppScene);
        mainStage.setTitle("File RTI Application - e-help Desk");
    }

    public void showRtiGuidelines() {
        com.superx.view.rti.subFiles.rtiGuidelines rtiGuidelinesPage = new com.superx.view.rti.subFiles.rtiGuidelines();
        rtiGuidelinesPage.setHostServices(hostServices);
        rtiGuidelinesScene = new Scene(rtiGuidelinesPage.rtiGuidelinesBox(this::showRTI), 1920, 1080);
        rtiGuidelinesPage.setRtiGuidelinesStage(mainStage);
        rtiGuidelinesPage.setRtiGuidelinesScene(rtiGuidelinesScene);
        mainStage.setScene(rtiGuidelinesScene);
        mainStage.setTitle("RTI Guidelines - e-help Desk");
    }

    public void showLodgeGrievance() {
        com.superx.view.rti.subFiles.lodgeGrievance lodgeGrievancePage = new com.superx.view.rti.subFiles.lodgeGrievance();
        lodgeGrievancePage.setHostServices(hostServices);
        lodgeGrievanceScene = new Scene(lodgeGrievancePage.lodgeGrievanceBox(this::showRTI), 1920, 1080);
        lodgeGrievancePage.setLodgeGrievanceStage(mainStage);
        lodgeGrievancePage.setLodgeGrievanceScene(lodgeGrievanceScene);
        mainStage.setScene(lodgeGrievanceScene);
        mainStage.setTitle("Lodge Grievance - e-help Desk");
    }

    // Archive screens
    public void showarchive() {
    legalArchives Page = new legalArchives();
    legalScene = new Scene(Page.legalbox(this::showProfileScreen, this::showLoginScreen, this::showLegal,
            this::showDocScene, this::showLandRecordsScreen, this::showRTI, this::showCourtProcedures,
            this::showIntroToLaw, this::showRightRespons, this::showAboutUsScreen), 1920, 1080); // Added showAboutUsScreen
    Page.setLegalStage(mainStage);
    Page.setLegalScene(legalScene);
    mainStage.setScene(legalScene);
    mainStage.setTitle("Legal Archives - e-help Desk");
}
    

    public void showCourtProcedures() {
        courtProcedures courtProceduresPage = new courtProcedures();
        courtProceduresPage.setHostServices(hostServices);
        courtProceduresScene = new Scene(courtProceduresPage.courtProceduresBox(this::showarchive), 1920, 1080);
        courtProceduresPage.setCourtProceduresStage(mainStage);
        courtProceduresPage.setCourtProceduresScene(courtProceduresScene);
        mainStage.setScene(courtProceduresScene);
        mainStage.setTitle("Court Procedures - e-help Desk");
    }

    public void showIntroToLaw() {
        introToLaw introToLawPage = new introToLaw();
        introToLawPage.setHostServices(hostServices);
        introToLawScene = new Scene(introToLawPage.introTolawBox(this::showarchive), 1920, 1080);
        introToLawPage.setIntroToLawStage(mainStage);
        introToLawPage.setIntroToLawScene(introToLawScene);
        mainStage.setScene(introToLawScene);
        mainStage.setTitle("Introduction to Law - e-help Desk");
    }

    public void showRightRespons() {
        rightRespons rightResponsPage = new rightRespons();
        rightResponsPage.setHostServices(hostServices);
        rightResponsScene = new Scene(rightResponsPage.rightResponsBox(this::showarchive), 1920, 1080);
        rightResponsPage.setRightResponsStage(mainStage);
        rightResponsPage.setRightResponsScene(rightResponsScene);
        mainStage.setScene(rightResponsScene);
        mainStage.setTitle("Rights & Responsibilities - e-help Desk");
    }

    // Utility method for track status (placeholder)
    public void showTrackStatus() {
        // Placeholder for track status functionality
        System.out.println("Track Status feature - Coming Soon!");
    }
}
