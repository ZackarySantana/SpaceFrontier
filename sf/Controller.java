package sf;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import resources.Resources;
import sf.animations.MainAnimation;
import sf.animations.PlayerAnimation;

public class Controller {
	@FXML
	public ImageView player;
	@FXML
	public ImageView enemy1;
	@FXML
	public ImageView enemy2;
	@FXML
	public ImageView enemy3;
	@FXML
	public ImageView pdrop1_image;
	@FXML
	public ImageView pdrop2_image;
	@FXML
	public ImageView pdrop3_image;
	@FXML
	public ImageView currentSword_image;
	@FXML
	public ImageView background;
	@FXML
	public Button worlds_btn;
	@FXML
	public Button inventory_btn;
	@FXML
	public Button cheatcodes_btn;
	@FXML
	public Button achievements_btn;
	@FXML
	public Button backward_btn;
	@FXML
	public Button forward_btn;
	@FXML
	public Text pdrop1_chance;
	@FXML
	public Text pdrop2_chance;
	@FXML
	public Text pdrop3_chance;

	public void retry() {
		this._playerAnimation.stop();
		this._playerAnimation.stp();
		this._played = false;
		this._forward = false;
		this.player.setImage(Resources.getImage("character", Resources.Type.CHARACTER));
		this.enemy1.setImage(Resources.getImage("earth-mob", Resources.Type.ENEMY));
		this.enemy2.setImage(Resources.getImage("earth-mob", Resources.Type.ENEMY));
		this.enemy3.setImage(Resources.getImage("earth-boss", Resources.Type.ENEMY));
		this.pdrop1_image.setImage(Resources.getImage("earth-sword", Resources.Type.WEAPON));
		this.pdrop2_image.setImage((Image) null);
		this.pdrop3_image.setImage((Image) null);
		this.currentSword_image.setImage(Resources.getImage("default-sword", Resources.Type.WEAPON));
		this.background.setImage(Resources.getImage("earth", Resources.Type.MAP));
		this.pdrop1_chance.setText("100%");
		this.pdrop2_chance.setText("");
		this.pdrop3_chance.setText("");
		this.health_text.setText("100/100");
		this.level_text.setText("0");
		this.player_level_text.setText("1");
		this.power_text.setText("1");
		this.backward_btn.setDisable(true);
		this.forward_btn.setDisable(false);

		this._player.setX(this.player.getLayoutX());
		this._player.setBar(1.0D);
		this._enemy1.setX(this.enemy1.getLayoutX());
		this._enemy2.setX(this.enemy2.getLayoutX());
		this._enemy3.setX(this.enemy3.getLayoutX());

		this._playerAnimation.pause();
		Main.getInstance().getMusic().play();
	}

	@FXML
	public Text health_text;
	@FXML
	public Text level_text;
	@FXML
	public Text player_level_text;
	@FXML
	public Text power_text;
	@FXML
	public Text information;
	@FXML
	public Pane pane;
	public PlayerAnimation _playerAnimation;
	public Stage _worldsMenu;
	public Stage _inventoryMenu;
	public Stage _cheatcodeMenu;
	public Stage _achievementMenu;
	public Stage _deathMenu;
	public HealthBar _player;
	public HealthBar _enemy1;
	public HealthBar _enemy2;
	public HealthBar _enemy3;
	private boolean _forward;
	private boolean _played;
	private static Controller _ME;

	@FXML
	public void initialize() {
		MainAnimation.setController(this);
		_ME = this;
		this._played = false;

		ObservableList<Node> children = this.pane.getChildren();

		this._player = new HealthBar(this.player.getLayoutX(), this.player.getLayoutY(), this.player.getFitWidth(),
				children);
		this._enemy1 = new HealthBar(this.enemy1.getLayoutX(),
				this.enemy1.getLayoutY() + this.enemy1.getFitHeight() + 25.0D, this.enemy1.getFitWidth(), children);
		this._enemy2 = new HealthBar(this.enemy2.getLayoutX(),
				this.enemy2.getLayoutY() + this.enemy2.getFitHeight() + 25.0D, this.enemy2.getFitWidth(), children);
		this._enemy3 = new HealthBar(this.enemy3.getLayoutX(),
				this.enemy3.getLayoutY() + this.enemy3.getFitHeight() + 25.0D, this.enemy3.getFitWidth(), children);

		this._worldsMenu = new Stage();
		this._worldsMenu.setScene(new Scene(Loader.loadRoot("WorldsMenu")));
		this._worldsMenu.initModality(Modality.WINDOW_MODAL);
		this._worldsMenu.initOwner(Main.getInstance().getMainStage());
		this._worldsMenu.setResizable(false);
		this._worldsMenu.setTitle("Worlds Menu");
		this._worldsMenu.setOnCloseRequest(event -> play());

		this._worldsMenu.getIcons().add(Resources.getImage("earth", Resources.Type.ICON));

		this._inventoryMenu = new Stage();
		this._inventoryMenu.setScene(new Scene(Loader.loadRoot("InventoryMenu")));
		this._inventoryMenu.initModality(Modality.WINDOW_MODAL);
		this._inventoryMenu.initOwner(Main.getInstance().getMainStage());
		this._inventoryMenu.setResizable(false);
		this._inventoryMenu.setTitle("Inventory Menu");
		this._inventoryMenu.setOnCloseRequest(event -> play());

		this._inventoryMenu.getIcons().add(Resources.getImage("inventory", Resources.Type.ICON));

		this._cheatcodeMenu = new Stage();
		this._cheatcodeMenu.setScene(new Scene(Loader.loadRoot("CheatcodeMenu")));
		this._cheatcodeMenu.initModality(Modality.WINDOW_MODAL);
		this._cheatcodeMenu.initOwner(Main.getInstance().getMainStage());
		this._cheatcodeMenu.setResizable(false);
		this._cheatcodeMenu.setTitle("Cheatcode Menu");
		this._cheatcodeMenu.setOnCloseRequest(event -> play());

		this._cheatcodeMenu.getIcons().add(Resources.getImage("cheatcode", Resources.Type.ICON));

		this._achievementMenu = new Stage();
		this._achievementMenu.setScene(new Scene(Loader.loadRoot("AchievementMenu")));
		this._achievementMenu.initModality(Modality.WINDOW_MODAL);
		this._achievementMenu.initOwner(Main.getInstance().getMainStage());
		this._achievementMenu.setResizable(false);
		this._achievementMenu.setTitle("Achievement Menu");
		this._achievementMenu.setOnCloseRequest(event -> play());

		this._deathMenu = new Stage();
		this._deathMenu.setScene(new Scene(Loader.loadRoot("DeathMenu")));
		this._deathMenu.initModality(Modality.WINDOW_MODAL);
		this._deathMenu.initOwner(Main.getInstance().getMainStage());
		this._deathMenu.setResizable(false);
		this._deathMenu.setTitle("Death");
		this._deathMenu.setOnCloseRequest(event -> play());

		this._deathMenu.getIcons().add(Resources.getImage("death", Resources.Type.ICON));

		this._playerAnimation = new PlayerAnimation();
		this._playerAnimation.pause();
		this.information.setText("Use the forward button and back button\nto change the direction of your\ncharacter!");
	}

	@FXML
	void goBackward(ActionEvent event) {
		if (this._forward && !this._playerAnimation.isDead()) {
			this.backward_btn.setDisable(true);
			this.forward_btn.setDisable(false);
			this._forward = false;
			this._playerAnimation.backward();
		}
	}

	@FXML
	void goForward(ActionEvent event) {
		if (!this._forward) {
			this.backward_btn.setDisable(false);
			this.forward_btn.setDisable(true);
			this._forward = true;
			if (!this._played) {
				this._played = true;
				this._playerAnimation.play();
			}
			this._playerAnimation.forward();
		}
	}

	@FXML
	void openWorlds(ActionEvent event) {
		if (!this._playerAnimation.isDead()) {
			this._worldsMenu.show();
			this._playerAnimation.pause();
		}
	}

	@FXML
	void openInventory(ActionEvent event) {
		if (!this._playerAnimation.isDead()) {
			this._inventoryMenu.show();
			this._playerAnimation.pause();
		}
	}

	@FXML
	void openCheatcode(ActionEvent event) {
		if (!this._playerAnimation.isDead()) {
			this._cheatcodeMenu.show();
			this._playerAnimation.pause();
		}
	}

	@FXML
	void openAchievement(ActionEvent event) {
		if (!this._playerAnimation.isDead()) {
			this._achievementMenu.show();
			this._playerAnimation.pause();
		}
	}

	public void play() {
		if (this._played) {
			this._playerAnimation.play();
		}
	}

	public static Controller getMe() {
		return _ME;
	}
}
