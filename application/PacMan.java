package application;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.*;
import javafx.scene.paint.Color;

public class PacMan extends Entite {
	private enum Direction {
		NORD, EST, SUD, OUEST
	}

	private Direction direction;
	private final int RAYON = 20;
	private final int VITESSE_PACMAN = 160;
	private Scene scene;

	public PacMan(Scene scene) {
		x = 400+RAYON;
		y = 400+RAYON;
		vx = 0;
		vy = 0;
		direction = Direction.EST;
		this.scene = scene;
	}

	public void affichage(Group root) {
		Arc a = new Arc();
		a.setCenterX(x);
		a.setCenterY(y);
		a.setRadiusX(RAYON);
		a.setRadiusY(RAYON);
		a.setLength(310);
		a.setType(ArcType.ROUND);
		a.setFill(Color.YELLOW);

		int angle = 0;
		switch (direction) {
		case NORD:
			angle = 110;
			break;
		case EST:
			angle = 30;
			break;
		case OUEST:
			angle = 200;
			break;
		case SUD:
			angle = 290;
			break;
		}
		a.setStartAngle(angle);

		root.getChildren().add(a);
	}

	public void update(int deltaTemps) {
		scene.setOnKeyPressed(e -> {
			switch (e.getCode()) {
			case Z:
				direction = Direction.NORD;
				vx = 0;
				vy = -VITESSE_PACMAN;
				break;
			case D:
				direction = Direction.EST;
				vx = VITESSE_PACMAN;
				vy = 0;
				break;
			case S:
				direction = Direction.SUD;
				vx = 0;
				vy = VITESSE_PACMAN;
				break;
			case Q:
				direction = Direction.OUEST;
				vx = -VITESSE_PACMAN;
				vy = 0;
				break;
			default:
				break;
			}
		});
		x += (vx * deltaTemps) / 1000;
		y += (vy * deltaTemps) / 1000;
	}
}