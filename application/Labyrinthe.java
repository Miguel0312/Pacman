package application;

import javafx.scene.Group;
import javafx.scene.shape.*;
import javafx.scene.paint.*;
public class Labyrinthe extends Entite{
	private final int SIDE = 40;
	private Cases matrice[][] = new Cases[20][20];
	
	public Labyrinthe() {
		for(int i=0;i<matrice.length;i++) {
			for(int j=0;j<matrice[0].length;j++) {
				matrice[i][j] = Cases.BOMBOM;
				if(i==0 || j==0 || i==19 || j==19)
					matrice[i][j] = Cases.MUR;
			}
		}
		matrice[2][2] = Cases.MUR;
		matrice[2][3] = Cases.MUR;
		matrice[2][4] = Cases.MUR;
		matrice[2][6] = Cases.MUR;
		matrice[2][7] = Cases.MUR;
		matrice[2][8] = Cases.MUR;
		matrice[1][10] = Cases.MUR;
		matrice[2][10] = Cases.MUR;
		matrice[2][12] = Cases.MUR;
		matrice[2][13] = Cases.MUR;
		
		matrice[2][15] = Cases.MUR;
		matrice[2][16] = Cases.MUR;
		matrice[2][17] = Cases.MUR;
		matrice[4][8] = Cases.MUR;
		matrice[4][9] = Cases.MUR;
		matrice[4][10] = Cases.MUR;
		matrice[4][12] = Cases.MUR;
		matrice[5][10] = Cases.MUR;
		matrice[4][2] = Cases.MUR;
		matrice[4][3] = Cases.MUR;
		matrice[4][16] = Cases.MUR;
		matrice[4][17] = Cases.MUR;
		matrice[4][5] = Cases.MUR;
		matrice[4][14] = Cases.MUR;
		matrice[5][14] = Cases.MUR;
		matrice[6][14] = Cases.MUR;
		matrice[7][14] = Cases.MUR;
		matrice[8][14] = Cases.MUR;
		matrice[6][13] = Cases.MUR;
		matrice[6][12] = Cases.MUR;
		matrice[5][5] = Cases.MUR;
		matrice[6][5] = Cases.MUR;
		matrice[7][5] = Cases.MUR;
		matrice[8][5] = Cases.MUR;
		matrice[6][6] = Cases.MUR;
		matrice[6][7] = Cases.MUR;
		matrice[4][11] = Cases.MUR;
		matrice[4][11] = Cases.MUR;
		matrice[4][11] = Cases.MUR;
		matrice[4][11] = Cases.MUR;
		for(int i=7; i<13;i++) {matrice[8][i] = Cases.MUR;}
		for(int i=7; i<13;i++) {matrice[10][i] = Cases.MUR;}
		matrice[9][7] = Cases.MUR;
	}

	public void update(int deltaTemps) {
	}

	public void affichage(Group root) {
		for(int i=0;i<matrice.length;i++) {
			for(int j=0;j<matrice[0].length;j++) {
				Rectangle r = new Rectangle();
				r.setX(SIDE*j);
				r.setY(SIDE*i);
				r.setWidth(SIDE);
				r.setHeight(SIDE);
				Arc b = new Arc();
				b.setRadiusX(0);
				b.setRadiusY(0);
				b.setCenterX(SIDE*j+SIDE/2);
				b.setCenterY(SIDE*i+SIDE/2);
				b.setLength(360);
				b.setType(ArcType.ROUND);
				b.setFill(Color.YELLOW);
				switch(matrice[i][j]) {
				case MUR:
					r.setFill(Color.BLUE);
					break;
				case VIDE:
					r.setFill(Color.BLACK);
					break;
				case BOMBOM:
					r.setFill(Color.BLACK);
					b.setRadiusX(10);
					b.setRadiusY(10);
					break;
				default:
					r.setFill(Color.WHITE);
					break;
				}
				root.getChildren().add(r);
				root.getChildren().add(b);
			}
		}
	}
	public Cases[][] getMatrice(){
		return matrice;
	}
}