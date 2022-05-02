package application;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;

public class Pinky extends Fantome{

	public Pinky(Labyrinthe labyrinthe, PacMan pacman) {
		super(labyrinthe, pacman);
		this.setPosition(10, 8);
		this.cibleFuite = new int[] {1,19};
		this.timerDebut = 6500;
	}

	public void update(int deltaTemps) {
		if ((vx == 0 && vy == 0) || (!nouvelleCible && ((x - 20 + (vx * deltaTemps) / 1000) / 40 != (x - 20) / 40)
				|| (y - 20 + (vy * deltaTemps) / 1000) / 40 != (y - 20) / 40)) {
			if(!fuite) {
				cible[0] = pacman.getPosition()[0] / 40;
				cible[1] = pacman.getPosition()[1] / 40;
				Direction directionPacman = this.pacman.getDirection();
				int deltaX = 0, deltaY = 0;
				if(directionPacman == Direction.NORD)
					deltaY = -1;
				if(directionPacman == Direction.SUD)
					deltaY = 1;
				if(directionPacman == Direction.OUEST)
					deltaX = -1;
				if(directionPacman == Direction.EST)
					deltaX = 1;
				int ecart = 0;
				while(ecart < 2 && labyrinthe.getMatrice()[cible[1]+deltaY][cible[0]+deltaX]!=Cases.MUR) {
					cible[0] += deltaX;
					cible[1] += deltaY;
					ecart += 1;
				}
			}
			else {
				cible[0] = cibleFuite[0];
				cible[1] = cibleFuite[1];
			}
			prochain = trouverRoute(cible[1], cible[0]);
			vx = (prochain[0] - x / 40) * VITESSE_FANTOME;
			vy = (prochain[1] - y / 40) * VITESSE_FANTOME;
			x = 40 * (x / 40) + 20;
			y = 40 * (y / 40) + 20;
			nouvelleCible = true;
		} else if ((x + (vx * deltaTemps) / 1000) / 40 != x / 40 || (y + (vy * deltaTemps) / 1000) / 40 != y / 40) {
			nouvelleCible = false;
		}
		if(timerFuite <= 0) {
			fuite = false;
		} else {
			timerFuite -= deltaTemps;
		}
		if(timerDebut > 0) {
			vx = 0;
			vy = 0;
			timerDebut -= deltaTemps;
		}
		x += (vx * deltaTemps) / 1000;
		y += (vy * deltaTemps) / 1000;
	}

	public void affichage(Group root) {
		Arc a = new Arc();
		a.setCenterX(x);
		a.setCenterY(y);
		a.setRadiusX(RAYON);
		a.setRadiusY(RAYON);
		a.setLength(360);
		a.setType(ArcType.ROUND);
		Color color = fuite ? Color.NAVY : Color.PINK;
		a.setFill(color);
		
		root.getChildren().add(a);
	}
    
}