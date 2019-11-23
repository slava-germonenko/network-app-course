package collections;

import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

class Frame extends Dialog<Transfer> {

    static private ButtonType submitType = new ButtonType("Submit", ButtonBar.ButtonData.OK_DONE);
    static private ButtonType cancelType = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

    private TextField ContractIdField;
    private TextField CustomerField;
    private TextField LoadField;
    private TextField LoadMassField;
    private TextField TransportField;
    private TextField CostField;

    Frame() {
        super();
        setResultConverter(this::convertResult);
        GridPane bodyGrid = new GridPane();
        bodyGrid.setHgap(2);
        bodyGrid.setVgap(4);
        bodyGrid.add(new Label("Contract ID"), 0, 0);
        bodyGrid.add(new Label("Customer"), 0, 1);
        bodyGrid.add(new Label("Load Type"), 0, 2);
        bodyGrid.add(new Label("Load Mass"), 0, 3);
        bodyGrid.add(new Label("Transport"), 0, 4);
        bodyGrid.add(new Label("Cost"), 0, 5);
        bodyGrid.add(ContractIdField = new TextField(), 1, 0);
        bodyGrid.add(CustomerField = new TextField(), 1, 1);
        bodyGrid.add(LoadField = new TextField(), 1, 2);
        bodyGrid.add(LoadMassField = new TextField(), 1, 3);
        bodyGrid.add(TransportField = new TextField(), 1, 4);
        bodyGrid.add(CostField = new TextField(), 1, 5);

        getDialogPane().setContent(bodyGrid);
        getDialogPane().getButtonTypes().addAll(cancelType, submitType);
        getDialogPane().lookupButton(submitType);
        setTitle("Form");
    }

    void setForm(Transfer transfer) {
        ContractIdField.setText(String.valueOf(transfer.getContractId()));
        CustomerField.setText(transfer.getCustomer());
        LoadField.setText(transfer.getLoad());
        LoadMassField.setText(String.valueOf(transfer.getLoadMass()));
        TransportField.setText(transfer.getTransport());
        CostField.setText(String.valueOf(transfer.getCost()));
    }

    void resetForm() {
        ContractIdField.setText("");
        CustomerField.setText("");
        LoadField.setText("");
        LoadMassField.setText("");
        TransportField.setText("");
        CostField.setText("");
    }

    private Transfer convertResult(ButtonType buttonType) {
        if(buttonType != submitType) return null;

        int contractId, cost;
        double loadMass;
        String customer = CustomerField.getText();
        String load = LoadField.getText();
        String transport = TransportField.getText();

        try {
            contractId = Integer.parseInt(ContractIdField.getText());
            cost = Integer.parseInt(CostField.getText());
            loadMass = Double.parseDouble(LoadMassField.getText());
        } catch (NumberFormatException ignored) {
            System.out.println("Invalid data!");
            return null;
        }

        if (contractId < 0 || loadMass <= 0 || cost <= 0) {
            System.out.println("Wrong input data");
            return null;
        }

        var result = new Transfer();
        result.setContractId(contractId);
        result.setCustomer(customer);
        result.setLoad(load);
        result.setLoadMass(loadMass);
        result.setTransport(transport);
        result.setCost(cost);

        return result;
    }
}
