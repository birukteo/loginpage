
//import java.beans.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class controller {

    @FXML
    private Button loginbutton;

    @FXML
    private PasswordField passwordtextfield;

    @FXML
    private TextField usernametextfield;

    @FXML
    private Label loginmessagelabel;

    public void loginbuttononaction(ActionEvent e) {

        if (usernametextfield.getText().isBlank() == false && passwordtextfield.getText().isBlank() == false) {
            // loginmessagelabel.setText("you try to login");
            validatelogin();

        } else {
            loginmessagelabel.setText("please enter username and password");

        }

    }

    @FXML
    private Button signupbutton1;

    @FXML
    void signupbuttononaction(ActionEvent event) {
        createaccountform();

    }

    @FXML
    private Button canclebutton;

    public void canclebuttononaction(ActionEvent e) {
        Stage stage = (Stage) canclebutton.getScene().getWindow();
        stage.close();
    }

    public void validatelogin() {
        databaseconnection connectNow = new databaseconnection();
        Connection connectDB = connectNow.getConnection();

        String verifylogin = "SELECT count(1) FROM useraccount WHERE username = '" + usernametextfield.getText()
                + "' AND password ='" + passwordtextfield.getText() + "'";

        try {

            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifylogin);

            while (queryResult.next()) {
                if (queryResult.getInt(1) == 1) {
                    loginmessagelabel.setText("WELCOME");

                } else {
                    loginmessagelabel.setText("INVALID LOGIN.PLEASE TRY AGAIN");

                }

            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public void createaccountform() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("signup.fxml"));
            // primaryStage.setTitle("helloworld");
            Stage signupStage = new Stage();
            signupStage.initStyle(StageStyle.UNDECORATED);

            signupStage.setScene(new Scene(root, 600, 441));
            signupStage.show();

        } catch (Exception e) {
            e.printStackTrace();

        }

    }

}
