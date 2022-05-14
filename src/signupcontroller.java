import java.sql.Connection;
import java.sql.Statement;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class signupcontroller {

    @FXML
    private Button closebutton;

    @FXML
    private PasswordField confirmpasswordfield;

    @FXML
    private TextField firstnametextfield;

    @FXML
    private Label userregisterationtextfield;

    @FXML
    private TextField lastnametextfield;

    @FXML
    private PasswordField passwordfield;

    @FXML
    private Button signupbutton;

    @FXML
    private TextField usernametextfield;

    @FXML
    private Label conformationpasswordLabel;

    @FXML
    void closebuttononaction(ActionEvent event) {
        Stage stage = (Stage) closebutton.getScene().getWindow();
        stage.close();
        Platform.exit();

    }

    @FXML
    void signupbuttononaction(ActionEvent event) {

        if (passwordfield.getText().equals(confirmpasswordfield.getText())) {
            registeruser();
            // conformationpasswordLabel.setText("");

        } else {
            conformationpasswordLabel.setText("password does not match");

        }
    }

    public void registeruser() {
        databaseconnection connectNow = new databaseconnection();
        Connection connectDB = connectNow.getConnection();

        String firstname = firstnametextfield.getText();
        String lastname = lastnametextfield.getText();
        String username = usernametextfield.getText();
        String password = passwordfield.getText();

        String insertfields = "INSERT INTO useraccount (firstname,lastname,username,password) VALUES ('";
        String insertvalues = firstname + "','" + lastname + "','" + username + "','" + password + "')";
        String inserttoregister = insertfields + insertvalues;

        try {
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(inserttoregister);
            userregisterationtextfield.setText("User has been registered successfully");
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();

        }

    }

}
