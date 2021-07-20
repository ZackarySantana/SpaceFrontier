package sf;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class AchievementMenu {
	@FXML
	public ImageView one;

	@FXML
	public ImageView two;

	@FXML
	public ImageView three;

	@FXML
	public ImageView four;

	@FXML
	public ImageView five;

	@FXML
	public ImageView six;

	private static AchievementMenu _ME;

	@FXML
	public void initialize() {
		_ME = this;
	}

	@FXML
	void openMainPane(ActionEvent event) {
		((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
		Controller.getMe().play();
	}

	public static AchievementMenu getMe() {
		return _ME;
	}
}
