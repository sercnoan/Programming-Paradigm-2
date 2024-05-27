import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import java.util.ArrayList;
import java.util.List;

public class BorrowingSystem extends Application {
    UserLogin userLogin; // User login information
    List<BorrowRequest> borrowRequests = new ArrayList<>(); // List to store borrow requests
    Stage primaryStage; // Primary stage of the application
    FacultyApproval facultyApproval; // Faculty approval instance to manage borrow requests

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        showLoginScreen(); // Display the login screen when the application starts
    }

    // Method to display the login screen
    private void showLoginScreen() {
        VBox vbox = new VBox(10); // Vertical box layout
        vbox.setPadding(new Insets(10)); // Set padding around the layout
        vbox.setStyle("-fx-background-color: #f0f0f0;"); // Background color

        // Labels and text fields for user login
        Label schoolIdLabel = new Label("School ID:");
        schoolIdLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        TextField schoolIdField = new TextField();
        schoolIdField.setPromptText("Enter your School ID");

        Label passwordLabel = new Label("Password:");
        passwordLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter your password");

        // Button for user login
        Button loginButton = new Button("Login");
        loginButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold;");
        loginButton.setOnAction(e -> {
            String schoolId = schoolIdField.getText();
            String password = passwordField.getText();
            if (schoolId.isEmpty()) {
                displayErrorMessage("Please fill out username.");
            } else if (password.isEmpty()) {
                displayErrorMessage("Please fill out password.");
            } else {
                userLogin = new UserLogin(schoolId, password);
                boolean logged = userLogin.isAuthenticated();
                if (logged) {
                    openBorrowerInfo(); // If login successful, proceed to borrower info screen
                } else {
                    displayErrorMessage("Wrong username or password.");
                }
            }
            schoolIdField.clear();
            passwordField.clear();
        });

        // Button to login as faculty member
        Button facultyLoginButton = new Button("Faculty Login");
        facultyLoginButton.setStyle("-fx-background-color: #f44336; -fx-text-fill: white; -fx-font-weight: bold;");
        facultyLoginButton.setOnAction(e -> openFacultyLogin());

        // Add components to the layout
        vbox.getChildren().addAll(schoolIdLabel, schoolIdField, passwordLabel, passwordField, loginButton, facultyLoginButton);

        // Set the scene title
        Scene scene = new Scene(vbox, 350, 250);
        primaryStage.setScene(scene);
        primaryStage.setTitle("LE3 Borrowing System");
        primaryStage.show();
    }

    // Method to display borrower information screen
    private void openBorrowerInfo() {
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10));
        vbox.setStyle("-fx-background-color: #f0f0f0;"); // Background color

        // Labels and text fields for borrower information
        Label firstNameLabel = new Label("First Name:");
        firstNameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        TextField firstNameField = new TextField();
        firstNameField.setPromptText("Enter your First Name");

        Label lastNameLabel = new Label("Last Name:");
        lastNameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        TextField lastNameField = new TextField();
        lastNameField.setPromptText("Enter your Last Name");

        Label courseLabel = new Label("Course:");
        courseLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        TextField courseField = new TextField();
        courseField.setPromptText("Enter your Course");

        // Button to proceed to student application
        Button nextButton = new Button("Next");
        nextButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold;");
        nextButton.setOnAction(e -> {
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String course = courseField.getText();

            BorrowerInfo borrowerInfo = new BorrowerInfo(firstName, lastName, course);
            openStudentApp(borrowerInfo);
        });

        vbox.getChildren().addAll(firstNameLabel, firstNameField, lastNameLabel, lastNameField, courseLabel, courseField, nextButton);

        Scene scene = new Scene(vbox, 350, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Method to display student application screen
    private void openStudentApp(BorrowerInfo borrowerInfo) {
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10));
        vbox.setStyle("-fx-background-color: #f0f0f0;"); // Background color

        // Welcome label
        Label titleLabel = new Label("Welcome Student!");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        titleLabel.setTextFill(Color.BLUE);

        // Items to borrow
        ItemsToBorrow items = new ItemsToBorrow();

        // Text field for key number
        TextField keyNumberField = new TextField();
        keyNumberField.setPromptText("Enter Key Number");

        // Button to submit request
        Button submitRequestButton = new Button("Submit Request");
        submitRequestButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold;");
        submitRequestButton.setOnAction(e -> {
            String borrowedItems = "";
            if (items.getDoorKeysCheckBox().isSelected()) {
                borrowedItems += "Door Keys (Key Number: " + keyNumberField.getText() + ", Return Date: " + items.getReturnDatePicker().getValue() + ")\n";
            }
            if (items.getAirconRemoteCheckBox().isSelected()) {
                borrowedItems += "Aircon Remote (Return Date: " + items.getReturnDatePicker().getValue() + ")\n";
            }
            if (items.getTvRemoteCheckBox().isSelected()) {
                borrowedItems += "TV Remote (Return Date: " + items.getReturnDatePicker().getValue() + ")\n";
            }

            if (!borrowedItems.isEmpty()) {
                BorrowRequest request = new BorrowRequest(borrowerInfo, borrowedItems);
                borrowRequests.add(request);
                displayInformationMessage("Request submitted. Waiting for faculty approval.");
            } else {
                displayErrorMessage("Please select at least one item to borrow.");
            }
        });

        // Button to logout
        Button logoutButton = new Button("Logout");
        logoutButton.setStyle("-fx-background-color: #f44336; -fx-text-fill: white; -fx-font-weight: bold;");
        logoutButton.setOnAction(e -> showLoginScreen());

        vbox.getChildren().addAll(titleLabel, items.getDoorKeysCheckBox(),
                keyNumberField, items.getAirconRemoteCheckBox(),
                items.getTvRemoteCheckBox(),
                items.getReturnDatePicker(),
                submitRequestButton, logoutButton);

        Scene scene = new Scene(vbox, 300, 320);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Method to display faculty login screen
    private void openFacultyLogin() {
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10));
        vbox.setStyle("-fx-background-color: #f0f0f0;"); // Background color

        // Labels and text fields for faculty login
        Label facultyIdLabel = new Label("Faculty ID:");
        facultyIdLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        TextField facultyIdField = new TextField();
        facultyIdField.setPromptText("Enter your Faculty ID");

        Label passwordLabel = new Label("Password:");
        passwordLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter your password");

        // Button for faculty login
        Button loginButton = new Button("Login");
        loginButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold;");
        loginButton.setOnAction(e -> {
            String facultyId = facultyIdField.getText();
            String password = passwordField.getText();
            // Implement faculty authentication logic here
            Faculty faculty = new Faculty(facultyId, password);
            boolean isAuthenticated = faculty.isAuthenticated();
            if (isAuthenticated) {
                openFacultyApproval(); // If login successful, proceed to faculty approval screen
            } else {
                displayErrorMessage("Wrong faculty ID or password");
            }
        });

        vbox.getChildren().addAll(facultyIdLabel, facultyIdField, passwordLabel, passwordField, loginButton);

        Scene scene = new Scene(vbox, 350, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Method to display faculty approval screen
    private void openFacultyApproval() {
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10));
        vbox.setStyle("-fx-background-color: #f0f0f0;"); // Background color

        // Title label for pending borrow requests
        Label titleLabel = new Label("Pending Borrow Requests");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        titleLabel.setTextFill(Color.BLUE);

        ListView<String> requestListView = new ListView<>();
        for (BorrowRequest request : borrowRequests) {
            requestListView.getItems().add(request.toString());
        }

        // Initialize facultyApproval if it's null
        if (facultyApproval == null) {
            facultyApproval = new FacultyApproval();
            facultyApproval.setBorrowRequests(borrowRequests);
        }

        // Button to approve a request
        Button approveButton = new Button("Approve");
        approveButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold;");
        approveButton.setOnAction(e -> {
            String selectedRequest = requestListView.getSelectionModel().getSelectedItem();
            if (selectedRequest != null) {
                // Call approveRequest method of facultyApproval
                facultyApproval.approveRequest(borrowRequests.get(requestListView.getSelectionModel().getSelectedIndex()));
                // Remove request from ListView
                requestListView.getItems().remove(selectedRequest);
                displayInformationMessage("Request Approved.");
            } else {
                displayErrorMessage("Please select a request to approve.");
            }
        });

        // Button to deny a request
        Button denyButton = new Button("Deny");
        denyButton.setStyle("-fx-background-color: #f44336; -fx-text-fill: white; -fx-font-weight: bold;");
        denyButton.setOnAction(e -> {
            String selectedRequest = requestListView.getSelectionModel().getSelectedItem();
            if (selectedRequest != null) {
                // Call denyRequest method of facultyApproval
                facultyApproval.denyRequest(borrowRequests.get(requestListView.getSelectionModel().getSelectedIndex()));
                // Remove request from ListView
                requestListView.getItems().remove(selectedRequest);
                displayInformationMessage("Request Denied.");
            } else {
                displayErrorMessage("Please select a request to deny.");
            }
        });

        // Button to logout
        Button logoutButton = new Button("Logout");
        logoutButton.setStyle("-fx-background-color: #f44336; -fx-text-fill: white; -fx-font-weight: bold;");
        logoutButton.setOnAction(e -> showLoginScreen());

        vbox.getChildren().addAll(titleLabel, requestListView, approveButton, denyButton, logoutButton);

        Scene scene = new Scene(vbox, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void displayErrorMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void displayInformationMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}