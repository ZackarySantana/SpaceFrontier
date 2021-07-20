package sf;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class HealthBar {
	private Rectangle _outline;
	private Rectangle _bar;

	public HealthBar(double x, double y, double width, ObservableList<Node> list) {
		this._outline = new Rectangle(x, y - 20.0D, width, 20.0D);
		this._outline.setFill(Color.BLACK);
		this._outline.setStroke(Color.BLACK);
		this._outline.setStrokeWidth(2.0D);

		this._bar = new Rectangle(x, y - 20.0D, width, 20.0D);
		this._bar.setFill(Color.RED);

		list.add(this._outline);
		list.add(this._bar);
	}

	public void setX(double x) {
		if (x > 15.0D + this._outline.getStrokeWidth()
				&& x + this._outline.getWidth() + this._outline.getStrokeWidth() < 515.0D) {
			this._outline.setX(x);
			this._bar.setX(x);
		}
	}

	public void setBar(double bar) {
		this._bar.setWidth(this._outline.getWidth() * bar);
	}

	public void setVisible(boolean visible) {
		this._outline.setVisible(visible);
		this._bar.setVisible(visible);
	}
}
