package sf;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;
import resources.Resources;

public class DeathMenu {
	@FXML
	void exit(ActionEvent event) {
		System.exit(0);
	}

	@FXML
	void retry(ActionEvent event) {
		((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
		(AchievementMenu.getMe()).one.setImage(Resources.getImage("x", Resources.Type.ICON));
		(AchievementMenu.getMe()).two.setImage(Resources.getImage("x", Resources.Type.ICON));
		(AchievementMenu.getMe()).three.setImage(Resources.getImage("x", Resources.Type.ICON));
		(AchievementMenu.getMe()).four.setImage(Resources.getImage("x", Resources.Type.ICON));
		(AchievementMenu.getMe()).five.setImage(Resources.getImage("x", Resources.Type.ICON));
		(AchievementMenu.getMe()).six.setImage(Resources.getImage("x", Resources.Type.ICON));

		(CheatcodeMenu.getMe())._unlocked = false;

		(InventoryMenu.getMe()).selected.setLayoutX(21.0D);
		(InventoryMenu.getMe()).selected.setLayoutY(37.0D);

		(WorldsMenu.getMe()).mars.setDisable(true);
		(WorldsMenu.getMe()).pluto.setDisable(true);
		(WorldsMenu.getMe()).saturn.setDisable(true);
		(WorldsMenu.getMe()).sun.setDisable(true);

		Controller.getMe().retry();
	}
}
