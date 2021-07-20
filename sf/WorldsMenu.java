package sf;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import sf.worlds.Worlds;

public class WorldsMenu {
	@FXML
	public Button mars;
	@FXML
	public Button saturn;

	@FXML
	public void initialize() {
		_ME = this;
	}

	@FXML
	public Button pluto;
	@FXML
	public Button sun;
	private static WorldsMenu _ME;

	@FXML
	void openMainPane(ActionEvent event) {
		((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
		Controller.getMe().play();
	}

	@FXML
	void earth(ActionEvent event) {
		(Controller.getMe())._playerAnimation.changeWorlds(Worlds.WorldType.EARTH);
	}

	@FXML
	void mars(ActionEvent event) {
		(Controller.getMe())._playerAnimation.changeWorlds(Worlds.WorldType.MARS);
	}

	@FXML
	void pluto(ActionEvent event) {
		(Controller.getMe())._playerAnimation.changeWorlds(Worlds.WorldType.PLUTO);
	}

	@FXML
	void saturn(ActionEvent event) {
		(Controller.getMe())._playerAnimation.changeWorlds(Worlds.WorldType.SATURN);
	}

	@FXML
	void sun(ActionEvent event) {
		(Controller.getMe())._playerAnimation.changeWorlds(Worlds.WorldType.SUN);
	}

	public static WorldsMenu getMe() {
		return _ME;
	}
}
