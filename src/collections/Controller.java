package collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
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

    public void onAdd() {
        inputFrame.resetForm();
        Optional<Transfer> result = inputFrame.showAndWait();
        result.ifPresent(transfer -> observable.add(transfer));
    }

    public void onEdit() {
        var item = view.getFocusModel().getFocusedItem();
        if (item == null) return;
        inputFrame.setForm(item);
        Optional<Transfer> result = inputFrame.showAndWait();
        result.ifPresent(transfer -> observable.add(transfer));
    }

    public void onSort() {
        view.setItems(view.getItems().sorted());
    }

    public void onReload() {
        view.setItems(observable);
    }

    public void onDelete() {
        observable.remove(view.getSelectionModel().getSelectedIndex());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        contractIdColumn.setCellValueFactory(new PropertyValueFactory<>("ContractId"));
        customerColumn.setCellValueFactory(new PropertyValueFactory<>("Customer"));
        loadColumn.setCellValueFactory(new PropertyValueFactory<>("Load"));
        loadMassColumn.setCellValueFactory(new PropertyValueFactory<>("LoadMass"));
        transportColumn.setCellValueFactory(new PropertyValueFactory<>("Transport"));
        costColumn.setCellValueFactory(new PropertyValueFactory<>("Cost"));

        view.setItems(observable);
        view.setVisible(true);
    }
}
