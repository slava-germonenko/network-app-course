package collections;

import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;

class Frame extends Dialog<Transfer> {

    static private ButtonType submitType = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
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
            loadMass = Double.parseDouble(LoadField.getText());
        } catch (NumberFormatException ignored) {
            System.out.println("Invalid data!");
            return null;
        }

        if (contractId <= 0 || loadMass <= 0 || cost <= 0) {
            System.out.println("Wrong input data");
            return null;
        }

        return new Transfer(contractId, customer, load, loadMass, transport, cost);
    }
}
