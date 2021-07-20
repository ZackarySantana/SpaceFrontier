package sf;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
	private List<Weapon> _weapons;
	private Weapon _current;

	public Inventory() {
		this._weapons = new ArrayList<>();
		this._weapons.add(Weapon.DEFAUlT);
		this._current = Weapon.DEFAUlT;
	}

	public void add(Weapon weapon) {
		if (!contains(weapon)) {
			this._weapons.add(weapon);
		}
	}

	public boolean contains(Weapon weapon) {
		return !(!this._weapons.contains(weapon) && this._current != weapon);
	}

	public void setCurrent(Weapon current) {
		this._current = current;
	}

	public Weapon get() {
		return this._current;
	}
}

/*
 * Location: E:\Old Coding\Old Programs\SpaceFrontier.jar!\sf\Inventory.class
 * Java compiler version: 8 (52.0) JD-Core Version: 1.1.3
 */