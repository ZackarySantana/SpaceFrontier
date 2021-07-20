package sf;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class InventoryMenu {
	@FXML
	public Rectangle selected;
	private static InventoryMenu _ME;

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
	void common(MouseEvent event) {
		if ((Controller.getMe())._playerAnimation.equipWeapon(Weapon.DEFAUlT)) {
			this.selected.setLayoutX(((ImageView) event.getSource()).getLayoutX());
			this.selected.setLayoutY(((ImageView) event.getSource()).getLayoutY());
		}
	}

	@FXML
	void earth(MouseEvent event) {
		if ((Controller.getMe())._playerAnimation.equipWeapon(Weapon.EARTH)) {
			this.selected.setLayoutX(((ImageView) event.getSource()).getLayoutX());
			this.selected.setLayoutY(((ImageView) event.getSource()).getLayoutY());
		} else {
			(new Alert(Alert.AlertType.INFORMATION, "You have not unlocked this weapon!",
					new ButtonType[] { ButtonType.OK })).show();
		}
	}

	@FXML
	void epic(MouseEvent event) {
		if ((Controller.getMe())._playerAnimation.equipWeapon(Weapon.EPIC)) {
			this.selected.setLayoutX(((ImageView) event.getSource()).getLayoutX());
			this.selected.setLayoutY(((ImageView) event.getSource()).getLayoutY());
		} else {
			(new Alert(Alert.AlertType.INFORMATION, "You have not unlocked this weapon!",
					new ButtonType[] { ButtonType.OK })).show();
		}
	}

	@FXML
	void glitch(MouseEvent event) {
		if ((Controller.getMe())._playerAnimation.equipWeapon(Weapon.CHEAT)) {
			this.selected.setLayoutX(((ImageView) event.getSource()).getLayoutX());
			this.selected.setLayoutY(((ImageView) event.getSource()).getLayoutY());
		} else {
			(new Alert(Alert.AlertType.INFORMATION, "You have not unlocked this weapon!",
					new ButtonType[] { ButtonType.OK })).show();
		}
	}

	@FXML
	void legendary(MouseEvent event) {
		if ((Controller.getMe())._playerAnimation.equipWeapon(Weapon.LEGENDARY)) {
			this.selected.setLayoutX(((ImageView) event.getSource()).getLayoutX());
			this.selected.setLayoutY(((ImageView) event.getSource()).getLayoutY());
		} else {
			(new Alert(Alert.AlertType.INFORMATION, "You have not unlocked this weapon!",
					new ButtonType[] { ButtonType.OK })).show();
		}
	}

	@FXML
	void mars(MouseEvent event) {
		if ((Controller.getMe())._playerAnimation.equipWeapon(Weapon.MARS)) {
			this.selected.setLayoutX(((ImageView) event.getSource()).getLayoutX());
			this.selected.setLayoutY(((ImageView) event.getSource()).getLayoutY());
		} else {
			(new Alert(Alert.AlertType.INFORMATION, "You have not unlocked this weapon!",
					new ButtonType[] { ButtonType.OK })).show();
		}
	}

	@FXML
	void pluto(MouseEvent event) {
		if ((Controller.getMe())._playerAnimation.equipWeapon(Weapon.PLUTO)) {
			this.selected.setLayoutX(((ImageView) event.getSource()).getLayoutX());
			this.selected.setLayoutY(((ImageView) event.getSource()).getLayoutY());
		} else {
			(new Alert(Alert.AlertType.INFORMATION, "You have not unlocked this weapon!",
					new ButtonType[] { ButtonType.OK })).show();
		}
	}

	@FXML
	void rare(MouseEvent event) {
		if ((Controller.getMe())._playerAnimation.equipWeapon(Weapon.RARE)) {
			this.selected.setLayoutX(((ImageView) event.getSource()).getLayoutX());
			this.selected.setLayoutY(((ImageView) event.getSource()).getLayoutY());
		} else {
			(new Alert(Alert.AlertType.INFORMATION, "You have not unlocked this weapon!",
					new ButtonType[] { ButtonType.OK })).show();
		}
	}

	@FXML
	void saturn(MouseEvent event) {
		if ((Controller.getMe())._playerAnimation.equipWeapon(Weapon.SATURN)) {
			this.selected.setLayoutX(((ImageView) event.getSource()).getLayoutX());
			this.selected.setLayoutY(((ImageView) event.getSource()).getLayoutY());
		} else {
			(new Alert(Alert.AlertType.INFORMATION, "You have not unlocked this weapon!",
					new ButtonType[] { ButtonType.OK })).show();
		}
	}

	@FXML
	void sun(MouseEvent event) {
		if ((Controller.getMe())._playerAnimation.equipWeapon(Weapon.SUN)) {
			this.selected.setLayoutX(((ImageView) event.getSource()).getLayoutX());
			this.selected.setLayoutY(((ImageView) event.getSource()).getLayoutY());
		} else {
			(new Alert(Alert.AlertType.INFORMATION, "You have not unlocked this weapon!",
					new ButtonType[] { ButtonType.OK })).show();
		}
	}

	public static InventoryMenu getMe() {
		return _ME;
	}
}
