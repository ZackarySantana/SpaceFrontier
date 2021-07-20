package resources;

import java.util.HashMap;
import java.util.Map;
import javafx.scene.image.Image;
import javafx.scene.media.Media;

public class Resources {
	private static final Map<String, Image> _LOADED_IMAGES = new HashMap<>();
	private static final Map<String, Media> _LOADED_SOUNDS = new HashMap<>();

	public static Image getImage(String name, Type type) {
		String path = "/resources/" + type.getPath() + name.concat(".png");
		return _LOADED_IMAGES.containsKey(path) ? _LOADED_IMAGES.get(path) : new Image(path);
	}

	public static Image loadImage(String name, Type type) {
		String path = "/resources/" + type.getPath() + name.concat(".png");
		if (!_LOADED_IMAGES.containsKey(path)) {
			_LOADED_IMAGES.put(path, new Image(path));
		}
		return _LOADED_IMAGES.get(path);
	}

	public static Media getSoundEffect(String name) {
		String path = "music/" + name.concat(".mp3");
		return _LOADED_SOUNDS.containsKey(path) ? _LOADED_SOUNDS.get(path)
				: new Media(Resources.class.getResource(path).toString());
	}

	public static Media loadSoundEffect(String name) {
		String path = "music/" + name.concat(".mp3");
		if (!_LOADED_SOUNDS.containsKey(path)) {
			_LOADED_SOUNDS.put(path, new Media(Resources.class.getResource(path).toString()));
		}
		return _LOADED_SOUNDS.get(path);
	}

	public enum Type {
		ENEMY("sprites/enemy/"), MAP("maps/"), CHARACTER("sprites/player/"), TEXTBOX("misc/textboxes/"),
		WEAPON("weapons/"), ICON("misc/");

		private String _path;

		Type(String path) {
			this._path = path;
		}

		public String getPath() {
			return this._path;
		}
	}
}
