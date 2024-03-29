package lk.ijse.LibraryManagement.ViewConroller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lk.ijse.LibraryManagement.Proxy.ProxyHandler;
import lk.ijse.LibraryManagement.dto.BookDTO;
import lk.ijse.LibraryManagement.dto.StudentDTO;
import lk.ijse.LibraryManagement.service.ServiceFactory;
import lk.ijse.LibraryManagement.service.custom.BookService;
import lk.ijse.LibraryManagement.service.custom.StudentService;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ReserveBookController implements Initializable {

    @FXML
    private Label reserveB;

    @FXML
    private Label btnViewBooks;

    @FXML
    private Label btnSubmitBooks;

    @FXML
    private ComboBox<Integer> comboRegNo;

    @FXML
    private ComboBox<Integer> comboBookId;

    @FXML
    private DatePicker BorrowDate;

    @FXML
    private JFXTextField txtStudentName;

    @FXML
    private JFXTextField txtBookname;

    @FXML
    private JFXTextField txtGrade;

    @FXML
    private JFXTextField txtLastBookName;

    @FXML
    private DatePicker Borrowdate;

    @FXML
    private DatePicker ReturnDate;

    @FXML
    private JFXButton btnReserve;

    @FXML
    private Label lblPic;

    @FXML
    private JFXButton btnHome;

    @FXML
    private Label lblStatus;

   private StudentService sservice;

   private BookService bservice;

    @FXML
    void AddReservation(MouseEvent event) {

    }

    @FXML
    void comboRegNoMouseClick(MouseEvent event) {

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
    void openSubmitBooks(MouseEvent event) {

        try {
            Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/LibraryManagement/View/SubmitBook.fxml"));
            Scene temp = new Scene(root);
            Stage stage =(Stage) this.btnSubmitBooks.getScene().getWindow();
            stage.setScene(temp);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @FXML
    void openViewReservation(MouseEvent event) {

        try {
            Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/LibraryManagement/View/ViewReservation.fxml"));
            Scene temp = new Scene(root);
            Stage stage =(Stage) this.btnReserve.getScene().getWindow();
            stage.setScene(temp);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            sservice = ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.STUDENT);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            loadRegIds();

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            bservice=ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.BOOK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            loadBookId();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void loadBookId()throws Exception {

        ArrayList<BookDTO>allbk=(ArrayList<BookDTO>)bservice.getAll();
        ObservableList<Integer>bk=FXCollections.observableArrayList();
        for (BookDTO b: allbk){
            bk.add(b.getBid());
        }
        comboBookId.setItems(bk);

    }

    private void loadRegIds() throws Exception{

        ArrayList<StudentDTO> allIds=(ArrayList<StudentDTO>)sservice.getAll();
        ObservableList<Integer>ids=FXCollections.observableArrayList();
        for(StudentDTO s:allIds){
            ids.add(s.getRegId());
        }
        comboRegNo.setItems(ids);

    }
}
