package sf;

public enum Weapon {
	DEFAUlT(1.0D), CHEAT(1.75D), EARTH(1.2D), MARS(2.25D), SATURN(2.5D), PLUTO(2.75D), SUN(3.0D), RARE(1.5D),
	EPIC(2.0D), LEGENDARY(2.75D);

	private double _power;

	Weapon(double power) {
		this._power = power;
	}

	public double getPower() {
		return this._power;
	}
}
