package collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public TableView<Transfer> view;
    public TableColumn<Transfer, Integer> contractIdColumn;
    public TableColumn<Transfer, String> customerColumn;
    public TableColumn<Transfer, String> loadColumn;
    public TableColumn<Transfer, Double> loadMassColumn;
    public TableColumn<Transfer, String> transportColumn;
    public TableColumn<Transfer, Integer> costColumn;

    private ObservableList<Transfer> observable;
    private Frame inputFrame;


    public Controller() {
        observable = FXCollections.observableList(new ArrayList<>());
        inputFrame = new Frame();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        contractIdColumn.setCellValueFactory(new PropertyValueFactory<>("Contract ID"));
        customerColumn.setCellValueFactory(new PropertyValueFactory<>("Customer"));
        loadColumn.setCellValueFactory(new PropertyValueFactory<>("Load Type"));
        loadMassColumn.setCellValueFactory(new PropertyValueFactory<>("Load Mass"));
        transportColumn.setCellValueFactory(new PropertyValueFactory<>("Transport"));
        costColumn.setCellValueFactory(new PropertyValueFactory<>("Cost"));

        view.setItems(observable);
        view.setVisible(true);
    }
}
