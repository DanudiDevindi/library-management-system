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
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lk.ijse.LibraryManagement.Proxy.ProxyHandler;
import lk.ijse.LibraryManagement.dto.AuthorDTO;
import lk.ijse.LibraryManagement.dto.BookDTO;
import lk.ijse.LibraryManagement.service.ServiceFactory;
import lk.ijse.LibraryManagement.service.custom.AuthorService;
import lk.ijse.LibraryManagement.service.custom.BookService;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ModifyBookController implements Initializable {

    @FXML
    private Label btnaddBook;

    @FXML
    private Label btnViewBooks;

    @FXML
    private Label btnModifyBook;

    @FXML
    private TextField txtSearchBook;

    @FXML
    private ComboBox<String> comboBook;

    @FXML
    private JFXTextField txtMachiningNo;

    @FXML
    private JFXTextField txtBatchNo;

    @FXML
    private DatePicker ReceiveDate;

    @FXML
    private JFXTextField txtPublisher;

    @FXML
    private DatePicker publisherDate;

    @FXML
    private JFXTextField txtPages;

    @FXML
    private JFXTextField txtPrice;

    @FXML
    private JFXTextField txtSupplier;

    @FXML
    private JFXTextField txtOther;

    @FXML
    private DatePicker RemoveDate;

    @FXML
    private Label lblPic;

    @FXML
    private ComboBox<?> comboSelectCategory;

    @FXML
    private ComboBox<?> comboRackNo;

    @FXML
    private ComboBox<?> comboRowNo;

    @FXML
    private ComboBox<?> comboColoumNo;

    @FXML
    private TextField txtDvDecimal;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private JFXButton btnHome;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private ComboBox<String> comboAuthor;

    private BookService bService;
    private AuthorService aservice;

    @FXML
    void DeleteBook(MouseEvent event) {

    }

    @FXML
    void OpenViewBooks(MouseEvent event) {

        try {
            Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/LibraryManagement/View/ViewBooks.fxml"));
            Scene temp = new Scene(root);
            Stage stage =(Stage) this.btnViewBooks.getScene().getWindow();
            stage.setScene(temp);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void UpdateBook(MouseEvent event) {

    }

    @FXML
    void comboAuthorMouseClick(MouseEvent event) {

    }

    @FXML
    void comboBookMouseClick(MouseEvent event) {

    }

    @FXML
    void openAdBook(MouseEvent event) {

        try {
            Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/LibraryManagement/View/AddBook.fxml"));
            Scene temp = new Scene(root);
            Stage stage =(Stage) this.btnaddBook.getScene().getWindow();
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
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            bService =ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.BOOK);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            loadBookName();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            aservice=ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.AUTHOR);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            loadAuthorName();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void loadAuthorName() throws Exception{
        ArrayList<AuthorDTO> allList=(ArrayList<AuthorDTO>)aservice.getAll();

        ObservableList<String> name=FXCollections.observableArrayList();

        for(AuthorDTO p:allList){
            name.add(String.valueOf(p.getAuthorName()));


        }
        comboAuthor.setItems(name);

    }

    private void loadBookName() throws Exception{

        ArrayList<BookDTO> allList=(ArrayList<BookDTO>)bService.getAll();

        ObservableList<String> name=FXCollections.observableArrayList();

        for(BookDTO p:allList){
            name.add(String.valueOf(p.getBookName()));


        }
        comboBook.setItems(name);

    }
    }




