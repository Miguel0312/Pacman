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
		x =1*40 + RAYON;
		y = 4*40 + RAYON;
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
				if(collisionMur2(Direction.NORD)) {
				direction = Direction.NORD;
				vx = 0;
				vy = -VITESSE_PACMAN;
				x = (int)(x/40)*40+RAYON;
				break;}
				
			case D:
				if(collisionMur2(Direction.EST)) {

				direction = Direction.EST;
				vx = VITESSE_PACMAN;
				vy = 0;
				y = (int)(y/40)*40+RAYON; 
				break;}
			case S:
				if(collisionMur2(Direction.SUD)) {

				direction = Direction.SUD;
				vx = 0;
				vy = VITESSE_PACMAN;	
				x = (int)(x/40)*40+RAYON;

				break;}
			case Q:
				if(collisionMur2(Direction.OUEST)) {

				direction = Direction.OUEST;
				vx = -VITESSE_PACMAN;
				vy = 0;
				y = (int)(y/40)*40+RAYON;
				break;}
			default:
				break;
			}
		});
		collisionMur();
		x += (vx * deltaTemps) / 1000;
		y += (vy * deltaTemps) / 1000;
	}
	public void collisionMur() {
		int[] position = getPosition();

		if(direction == Direction.EST) {
			//System.out.println((position[0] + RAYON+1)/40 +"  "+position[1]/40);
			if(matrice[(position[1] - RAYON + 5)/40][(position[0] + RAYON+1)/40]==Cases.MUR || matrice[(position[1] + RAYON - 5)/40][(position[0] + RAYON+1)/40]==Cases.MUR) {
				vx = 0;
			}
		}else if(direction == Direction.OUEST) {
			System.out.println((position[0] - RAYON-1)/40 +"  "+position[1]/40);
			if(matrice[(position[1] - RAYON + 5)/40][(position[0] - RAYON-1)/40]==Cases.MUR||matrice[(position[1] + RAYON - 5)/40][(position[0] - RAYON-1)/40]==Cases.MUR) {
				vx = 0;
			}
		}else if(direction == Direction.NORD) {
			System.out.println(position[0]/40 +"  "+(position[1]-RAYON-1)/40);
			if(matrice[(position[1]-RAYON-1)/40][(position[0]+RAYON-5)/40]==Cases.MUR || matrice[(position[1]-RAYON-1)/40][(position[0]-RAYON+5)/40]==Cases.MUR) {
				vy = 0;
			}
		}else if(direction == Direction.SUD) {
			System.out.println((position[0])/40 +"  "+(position[1]+RAYON+1)/40);
			if(matrice[(position[1]+RAYON+1)/40][(position[0]+RAYON-5)/40]==Cases.MUR || matrice[(position[1]+RAYON+1)/40][(position[0]-RAYON+5)/40]==Cases.MUR) {
				vy = 0;
			}
		}
	}
	public boolean collisionMur2(Direction d) {
		int[] position = getPosition();
		boolean retour = true;
		if(d == Direction.EST) {
			System.out.println((position[0] + RAYON+1)/40 +"  "+position[1]/40);
			if(matrice[(position[1] - RAYON + 5)/40][(position[0] + RAYON+1)/40]==Cases.MUR || matrice[(position[1] + RAYON - 5)/40][(position[0] + RAYON+1)/40]==Cases.MUR) {
				retour = false;
			}
		}else if(d == Direction.OUEST) {
			System.out.println((position[0] - RAYON-1)/40 +"  "+position[1]/40);
			if(matrice[(position[1] - RAYON + 5)/40][(position[0] - RAYON-1)/40]==Cases.MUR||matrice[(position[1] + RAYON - 5)/40][(position[0] - RAYON-1)/40]==Cases.MUR) {
				retour = false;
			}
		}else if(d == Direction.NORD) {
			System.out.println(position[0]/40 +"  "+(position[1]-RAYON-1)/40);
			if(matrice[(position[1]-RAYON-1)/40][(position[0]+RAYON-5)/40]==Cases.MUR || matrice[(position[1]-RAYON-1)/40][(position[0]-RAYON+5)/40]==Cases.MUR) {
				retour = false;
			}
		}else if(d == Direction.SUD) {
			System.out.println((position[0])/40 +"  "+(position[1]+RAYON+1)/40);
			if(matrice[(position[1]+RAYON+1)/40][(position[0]+RAYON-5)/40]==Cases.MUR || matrice[(position[1]+RAYON+1)/40][(position[0]-RAYON+5)/40]==Cases.MUR) {
				retour = false;
			}
		}
		return retour;
	}
	public void setHealthPacMan(int h ) {
		health = h;
	} 
	public int getHealthPacMan() {
		return health;
	} 
}