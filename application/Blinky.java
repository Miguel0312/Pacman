package application;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;

public class Blinky extends Fantome{
	public Blinky(Labyrinthe labyrinthe) {
		super(labyrinthe);
	}
	
	public void update(int deltaTemps) {
		if(x!=20+40*18 || y!=60) {
			int prochain[] = this.trouverRoute(1,18);
			setPosition(20+40*prochain[0],20+40*prochain[1]);
			try {
				Thread.sleep(500);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
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