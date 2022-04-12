package application;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;

public class Blinky extends Fantome {
	public Blinky(Labyrinthe labyrinthe, PacMan pacman) {
		super(labyrinthe, pacman);
	}

	public void update(int deltaTemps) {
		if ((vx == 0 && vy == 0) || (!nouvelleCible && ((x - 20 + (vx * deltaTemps) / 1000) / 40 != (x - 20) / 40)
				|| (y - 20 + (vy * deltaTemps) / 1000) / 40 != (y - 20) / 40)) {
			cible[0] = pacman.getPosition()[0] / 40;
			cible[1] = pacman.getPosition()[1] / 40;
			prochain = trouverRoute(cible[1], cible[0]);
			vx = (prochain[0] - x / 40) * VITESSE_FANTOME;
			vy = (prochain[1] - y / 40) * VITESSE_FANTOME;
			x = 40 * (x / 40) + 20;
			y = 40 * (y / 40) + 20;
			nouvelleCible = true;
		} else if ((x + (vx * deltaTemps) / 1000) / 40 != x / 40 || (y + (vy * deltaTemps) / 1000) / 40 != y / 40) {
			nouvelleCible = false;
		}
		x += (vx * deltaTemps) / 1000;
		y += (vy * deltaTemps) / 1000;
		if (tempsPasse < 5000)
			tempsPasse += deltaTemps;
	}

	public void affichage(Group root) {
		Arc a = new Arc();
		a.setCenterX(x);
		a.setCenterY(y);
		a.setRadiusX(RAYON);
		a.setRadiusY(RAYON);
		a.setLength(360);
		a.setType(ArcType.ROUND);
		a.setFill(Color.RED);

		root.getChildren().add(a);
	}

}