package sf;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class CheatcodeMenu {
	public boolean _unlocked;
	private static CheatcodeMenu _ME;

	@FXML
	public void initialize() {
		_ME = this;
	}

	@FXML
	void openMainPane(ActionEvent event) {
		((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
		Controller.getMe().play();
	}

	@FXML
	void cheatcode(MouseEvent event) {
		if (!this._unlocked) {
			(Controller.getMe())._playerAnimation._inventory.add(Weapon.CHEAT);
			(new Alert(Alert.AlertType.INFORMATION, "You have just unlocked the glitch weapon!",
					new ButtonType[] { ButtonType.OK })).show();
			this._unlocked = true;
		}
	}

	public static CheatcodeMenu getMe() {
		return _ME;
	}
}
