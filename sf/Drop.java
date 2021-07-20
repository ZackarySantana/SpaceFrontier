package sf;

import javafx.scene.image.Image;

public class Drop {
	private Image _drop;
	private Weapon _weapon;
	private double _chance;

	public Drop(Image drop, Weapon weapon, double chance) {
		this._drop = drop;
		this._weapon = weapon;
		this._chance = chance;
	}

	public Image getDrop() {
		return this._drop;
	}

	public double getChance() {
		return this._chance;
	}

	public Weapon getWeapon() {
		return this._weapon;
	}
}
