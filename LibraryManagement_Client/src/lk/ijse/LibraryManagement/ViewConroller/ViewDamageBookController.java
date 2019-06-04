package lk.ijse.LibraryManagement.ViewConroller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ViewDamageBookController implements Initializable {
    @FXML
    private TableView<?> tblDamageBook;

    @FXML
    private JFXButton btnHome;

    @FXML
    private JFXButton btnView;

    @FXML
    private Label btnAddDamageBook;

    @FXML
    private Label btnViewDamageBook;

    @FXML
    private Label btnModifyBook;

    @FXML
    void ViewDamageBooks(MouseEvent event) {

    }

    @FXML
    void openAddDamageBook(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/LibraryManagement/View/AddDamageBook.fxml"));
            Scene temp = new Scene(root);
            Stage stage =(Stage) this.btnAddDamageBook.getScene().getWindow();
            stage.setScene(temp);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @FXML
    void openHome(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/LibraryManagement/View/LibraryDashboard.fxml"));
            Scene temp = new Scene(root);
            Stage stage =(Stage) this.btnHome.getScene().getWindow();
            stage.setScene(temp);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @FXML
    void openModifyBook(MouseEvent event) {

        try {
            Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/LibraryManagement/View/ModifyBook.fxml"));
            Scene temp = new Scene(root);
            Stage stage =(Stage) this.btnModifyBook.getScene().getWindow();
            stage.setScene(temp);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
