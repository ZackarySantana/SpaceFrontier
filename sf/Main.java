package sf;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import resources.Resources;

public class Main extends Application {
	private static Main _main;
	private Stage _mainStage;
	private AudioClip _player;

	public static void main(String[] args) {
		launch(args);
	}

	public static Main getInstance() {
		return _main;
	}

	public void start(Stage mainStage) throws Exception {
		_main = this;
		this._mainStage = mainStage;
		loadResources();
		Loader.loadRoot("WorldsMenu");
		Loader.loadRoot("InventoryMenu");
		Loader.loadRoot("CheatcodeMenu");
		Loader.loadRoot("AchievementMenu");
		this._mainStage.setScene(new Scene(Loader.loadRoot("MainMenu")));
		mainStage.setResizable(false);
		mainStage.setTitle("Space Frontier");
		mainStage.getIcons().add(Resources.getImage("main", Resources.Type.ICON));
		mainStage.show();

		this._player = new AudioClip(Resources.getSoundEffect("default-music").getSource());
		this._player.setCycleCount(-1);
		this._player.play();
	}

	private void loadResources() {
		Resources.loadImage("main", Resources.Type.ICON);
		Resources.loadImage("achievement", Resources.Type.ICON);
		Resources.loadImage("cheatcode", Resources.Type.ICON);
		Resources.loadImage("earth", Resources.Type.ICON);
		Resources.loadImage("inventory", Resources.Type.ICON);
		Resources.loadImage("x", Resources.Type.ICON);
		Resources.loadImage("g", Resources.Type.ICON);

		Resources.loadImage("earth", Resources.Type.MAP);
		Resources.loadImage("mars", Resources.Type.MAP);
		Resources.loadImage("pluto", Resources.Type.MAP);
		Resources.loadImage("saturn", Resources.Type.MAP);
		Resources.loadImage("sun", Resources.Type.MAP);

		Resources.loadSoundEffect("boss-death");
		Resources.loadSoundEffect("default-music");
		Resources.loadSoundEffect("player-damage");
		Resources.loadSoundEffect("player-death");
		Resources.loadSoundEffect("player-level_up");

		Resources.loadImage("earth-mob", Resources.Type.ENEMY);
		Resources.loadImage("earth-boss", Resources.Type.ENEMY);
		Resources.loadImage("mars-mob", Resources.Type.ENEMY);
		Resources.loadImage("mars-boss", Resources.Type.ENEMY);
		Resources.loadImage("saturn-mob", Resources.Type.ENEMY);
		Resources.loadImage("saturn-boss", Resources.Type.ENEMY);
		Resources.loadImage("sun-mob", Resources.Type.ENEMY);
		Resources.loadImage("sun-boss", Resources.Type.ENEMY);

		Resources.loadImage("character", Resources.Type.CHARACTER);
		Resources.loadImage("character-cheat", Resources.Type.CHARACTER);
		Resources.loadImage("character-earth", Resources.Type.CHARACTER);
		Resources.loadImage("character-epic", Resources.Type.CHARACTER);
		Resources.loadImage("character-legendary", Resources.Type.CHARACTER);
		Resources.loadImage("character-pluto", Resources.Type.CHARACTER);
		Resources.loadImage("character-rare", Resources.Type.CHARACTER);
		Resources.loadImage("character-saturn", Resources.Type.CHARACTER);
		Resources.loadImage("character-sun", Resources.Type.CHARACTER);

		Resources.loadImage("space", Resources.Type.CHARACTER);
		Resources.loadImage("space-cheat", Resources.Type.CHARACTER);
		Resources.loadImage("space-earth", Resources.Type.CHARACTER);
		Resources.loadImage("space-epic", Resources.Type.CHARACTER);
		Resources.loadImage("space-legendary", Resources.Type.CHARACTER);
		Resources.loadImage("space-pluto", Resources.Type.CHARACTER);
		Resources.loadImage("space-rare", Resources.Type.CHARACTER);
		Resources.loadImage("space-saturn", Resources.Type.CHARACTER);
		Resources.loadImage("space-sun", Resources.Type.CHARACTER);

		Resources.loadImage("cheat-sword", Resources.Type.WEAPON);
		Resources.loadImage("default-sword", Resources.Type.WEAPON);
		Resources.loadImage("earth-sword", Resources.Type.WEAPON);
		Resources.loadImage("epic-sword", Resources.Type.WEAPON);
		Resources.loadImage("legendary-sword", Resources.Type.WEAPON);
		Resources.loadImage("pluto-sword", Resources.Type.WEAPON);
		Resources.loadImage("rare-sword", Resources.Type.WEAPON);
		Resources.loadImage("saturn-sword", Resources.Type.WEAPON);
		Resources.loadImage("sun-sword", Resources.Type.WEAPON);
	}

	public Stage getMainStage() {
		return this._mainStage;
	}

	public AudioClip getMusic() {
		return this._player;
	}
}
