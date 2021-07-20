package sf.animations;

import javafx.animation.Transition;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import sf.Controller;

public abstract class MainAnimation extends Transition {
	protected static Controller _controller;
	protected ImageView _sprite;
	protected double _velX;

	public static void setController(Controller controller) {
		_controller = controller;
	}

	protected double _velY;
	protected double _begX;
	protected double _begY;
	protected double _toX;
	protected double _toY;
	protected double _first;
	protected double _borderWidth;
	protected double _borderHeight;
	protected long _delta;

	public MainAnimation(ImageView sprite, double toX, double toY, int duration) {
		this._sprite = sprite;
		this._delta = System.currentTimeMillis();
		this._begX = this._sprite.getLayoutX();
		this._begY = this._sprite.getLayoutY();
		this._toX = toX;
		this._toY = toY;
		this._velX = (this._toX - this._begX) / duration;
		this._velY = (this._toY - this._begY) / duration;
		this._first = 2.0D;
		this._borderWidth = 530.0D;
		this._borderHeight = 530.0D;
		setCycleDuration(Duration.millis(duration));
		setCycleCount(-1);
		playFromStart();
	}

	protected void interpolate(double frac) {
		long time = System.currentTimeMillis();
		long difference = time - this._delta;
		this._delta = time;
		double oldX = this._sprite.getLayoutX(), oldY = this._sprite.getLayoutY();
		double increaseX = this._sprite.getLayoutX() + this._velX * difference;
		if (this._sprite.getFitWidth() + increaseX <= this._borderWidth) {
			this._sprite.setLayoutX(increaseX);
		}
		double increaseY = this._sprite.getLayoutY() + this._velY * difference;
		if (this._sprite.getFitHeight() + increaseY <= this._borderHeight) {
			this._sprite.setLayoutY(increaseY);
		}
		if (oldX == this._sprite.getLayoutX() && oldY == this._sprite.getLayoutY()) {
			if (this._first == 0.0D) {
				this._first = 2.0D;
				this._sprite.setLayoutX(this._begX);
				this._sprite.setLayoutY(this._begY);
				jumpTo(Duration.ZERO);
			} else {

				this._first--;
			}
			return;
		}
	}
}
