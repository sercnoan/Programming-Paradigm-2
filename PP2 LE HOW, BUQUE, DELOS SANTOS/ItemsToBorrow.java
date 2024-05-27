import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;

public class ItemsToBorrow {
    private CheckBox doorKeysCheckBox;
    private CheckBox airconRemoteCheckBox;
    private CheckBox tvRemoteCheckBox;
    private DatePicker returnDatePicker;

    public ItemsToBorrow() {
        doorKeysCheckBox = new CheckBox("Door Keys");
        airconRemoteCheckBox = new CheckBox("Aircon Remote");
        tvRemoteCheckBox = new CheckBox("TV Remote");
        returnDatePicker = new DatePicker();
    }

    public CheckBox getDoorKeysCheckBox() {
        return doorKeysCheckBox;
    }

    public CheckBox getAirconRemoteCheckBox() {
        return airconRemoteCheckBox;
    }

    public CheckBox getTvRemoteCheckBox() {
        return tvRemoteCheckBox;
    }

    public DatePicker getReturnDatePicker() {
        return returnDatePicker;
    }
}
