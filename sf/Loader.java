package sf;

import java.util.HashMap;
import java.util.Map;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import resources.Resources;

public class Loader {
	private static final Map<String, Parent> _LOADED_PARENTS = new HashMap<>();

	public static Parent loadRoot(String path) {
		if (_LOADED_PARENTS.containsKey(path)) {
			return _LOADED_PARENTS.get(path);
		}
		FXMLLoader loader = new FXMLLoader(Resources.class.getResource(String.valueOf(path) + ".fxml"));
		try {
			Parent parent = loader.<Parent>load();
			_LOADED_PARENTS.put(path, parent);
			return parent;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}
}
