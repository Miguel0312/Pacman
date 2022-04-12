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
	private int health;
	private Labyrinthe labyrinthe;
	private Cases[][] matrice;

	public PacMan(Scene scene, Labyrinthe labyrinthe) {
		x = 2*40 + RAYON;
		y = 1*40 + RAYON;
		vx = 0;
		vy = 0;
		direction = Direction.EST;
		this.scene = scene;
		this.labyrinthe = labyrinthe;
		matrice = labyrinthe.getMatrice();
		health = 3;
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

		if (vy < 0 || (vy == 0 && vx == 0 && direction == Direction.NORD))
			angle = 110;
		else if (vx > 0 || (vx == 0 && vy == 0 && direction == Direction.EST))
			angle = 30;
		else if (vx < 0 || (vx == 0 && vy == 0 && direction == Direction.OUEST))
			angle = 200;
		else if (vy > 0 || (vy == 0 && vx == 0 && direction == Direction.SUD))
			angle = 290;

		a.setStartAngle(angle);
		root.getChildren().add(a);
	}

	public void update(int deltaTemps) {
		scene.setOnKeyPressed(e -> {
			switch (e.getCode()) {
			case Z:
				direction = Direction.NORD;
				break;
			case D:
				direction = Direction.EST;
				break;
			case S:
				direction = Direction.SUD;
				break;
			case Q:
				direction = Direction.OUEST;
				break;
			default:
				break;
			}
		});
		switch (direction) {
		case NORD:
			if (((x - 20) % 40 < 10 || (x - 20) % 40 > 30) && !collisionMur2()) {
				vx = 0;
				vy = -VITESSE_PACMAN;
				x = (x / 40) * 40 + RAYON;
			}
			break;
		case EST:
			if (((y - 20) % 40 < 10 || (y - 20) % 40 > 30) && !collisionMur2()) {
				vx = VITESSE_PACMAN;
				vy = 0;
				y = (y / 40) * 40 + RAYON;
			}
			break;
		case SUD:
			if (((x - 20) % 40 < 10 || (x - 20) % 40 > 30) && !collisionMur2()) {
				vx = 0;
				vy = VITESSE_PACMAN;
				x = (x / 40) * 40 + RAYON;
			}
			break;
		case OUEST:
			if (((y - 20) % 40 < 10 || (y - 20) % 40 > 30) && !collisionMur2()) {
				vx = -VITESSE_PACMAN;
				vy = 0;
				y = (y / 40) * 40 + RAYON;
			}
			break;
		}
		collisionMur(deltaTemps);
		x += (vx * deltaTemps) / 1000;
		y += (vy * deltaTemps) / 1000;
	}

	public void collisionMur(int deltaTemps) {
		if (vx > 0) {
			if (matrice[y / 40][(x + RAYON + (vx * deltaTemps) / 1000) / 40] == Cases.MUR) {
				vx = 0;
				x = 40 * (x / 40) + RAYON;
			}
		} else if (vx < 0) {
			if (matrice[y / 40][(x - RAYON + (vx * deltaTemps) / 1000) / 40] == Cases.MUR) {
				vx = 0;
				x = 40 * (x / 40) + RAYON;
			}
		} else if (vy < 0) {
			if (matrice[(y - RAYON + (vy * deltaTemps) / 1000) / 40][x / 40] == Cases.MUR) {
				vy = 0;
				y = 40 * (y / 40) + RAYON;
			}
		} else if (vy > 0) {
			if (matrice[(y + RAYON + (vy * deltaTemps) / 1000) / 40][x / 40] == Cases.MUR) {
				vy = 0;
				y = 40 * (y / 40) + RAYON;
			}
		}
	}

	public boolean collisionMur2() {
		if (direction == Direction.EST) {
			if (matrice[y / 40][x / 40 + 1] == Cases.MUR) {
				return true;
			}
		} else if (direction == Direction.OUEST) {
			if (matrice[y / 40][x / 40 - 1] == Cases.MUR) {
				return true;
			}
		} else if (direction == Direction.NORD) {
			if (matrice[y / 40 - 1][x / 40] == Cases.MUR) {
				return true;
			}
		} else if (direction == Direction.SUD) {
			if (matrice[y / 40 + 1][x / 40] == Cases.MUR) {
				return true;
			}
		}
		return false;
	}

	public void setHealthPacMan(int h) {
		health = h;
	}

	public int getHealthPacMan() {
		return health;
	}
	
	
}