package application;

import javafx.scene.Group;
import javafx.scene.shape.*;
import javafx.scene.paint.*;
public class Labyrinthe extends Entite{
<<<<<<< HEAD
=======
	
>>>>>>> 8f41e3fc44a027d12ce972a048058cbf373903ff
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
<<<<<<< HEAD
		for(int i=7; i<13;i++) {matrice[8][i] = Cases.MUR;}
		for(int i=7; i<13;i++) {matrice[10][i] = Cases.MUR;}
		matrice[9][7] = Cases.MUR;
=======
		for(int i =7;i<13;i++) {matrice[8][i] = Cases.MUR;}
		for(int i =7;i<13;i++) {matrice[10][i] = Cases.MUR;}
		matrice[9][7] = Cases.MUR;
		matrice[9][12] = Cases.MUR;
		matrice[8][9] = Cases.VIDE;
		matrice[8][10] = Cases.VIDE;
		for(int i =8;i<12;i++) {matrice[9][i] = Cases.VIDE;}
		for(int i =6;i<10;i++) {matrice[i][2] = Cases.MUR;matrice[i][3] = Cases.MUR;}
		matrice[12][1] = Cases.MUR;
		matrice[12][2] = Cases.MUR;
		matrice[12][3] = Cases.MUR;
		matrice[12][4] = Cases.MUR;
		for(int i =14;i<18;i++) {matrice[i][2] = Cases.MUR;}
		for(int i =4;i<9;i++) {matrice[15][i] = Cases.MUR;}
		matrice[16][4] = Cases.MUR;
		matrice[17][4] = Cases.MUR;
		matrice[17][6] = Cases.MUR;
		matrice[17][7] = Cases.MUR;
		matrice[17][8] = Cases.MUR;
		matrice[15][9] = Cases.MUR;
		matrice[15][10] = Cases.MUR;
		matrice[16][10] = Cases.MUR;
		matrice[17][10] = Cases.MUR;
		matrice[13][4] = Cases.MUR;
		for(int i =6;i<14;i++) {matrice[12][i] = Cases.MUR;}
		matrice[13][8] = Cases.MUR;
		matrice[14][6] = Cases.MUR;
		matrice[14][10] = Cases.MUR;
		matrice[4][7] = Cases.MUR;
		matrice[14][12] = Cases.MUR;
		matrice[15][12] = Cases.MUR;
		matrice[16][12] = Cases.MUR;
		matrice[17][12] = Cases.MUR;
		matrice[17][13] = Cases.MUR;
		matrice[17][14] = Cases.MUR;
		matrice[17][15] = Cases.MUR;
		matrice[17][16] = Cases.MUR;
		matrice[17][17] = Cases.MUR;
		matrice[16][17] = Cases.MUR;
		matrice[15][17] = Cases.MUR;
		matrice[14][17] = Cases.MUR;
		matrice[14][16] = Cases.MUR;
		matrice[14][15] = Cases.MUR;
		matrice[14][14] = Cases.MUR;
		matrice[15][14] = Cases.MUR;	
		matrice[15][15] = Cases.MUR;
		matrice[6][16] = Cases.MUR;
		matrice[6][17] = Cases.MUR;
		matrice[7][16] = Cases.MUR;
		matrice[7][17] = Cases.MUR;
		matrice[9][16] = Cases.MUR;
		matrice[9][17] = Cases.MUR;
		matrice[9][15] = Cases.MUR;
		matrice[9][14] = Cases.MUR;
		matrice[11][17] = Cases.MUR;
		matrice[12][17] = Cases.MUR;
		matrice[11][16] = Cases.MUR;
		matrice[12][16] = Cases.MUR;
		matrice[11][15] = Cases.MUR;
		matrice[12][15] = Cases.MUR;
		matrice[10][2] = Cases.MUR;
		matrice[11][4] = Cases.MUR;
		matrice[9][5] = Cases.MUR;
		matrice[6][9] = Cases.MUR;
		matrice[6][10] = Cases.MUR;
		matrice[15][16] = Cases.BONUS;
		matrice[2][14] = Cases.BONUS;
		matrice[1][1] = Cases.BONUS;
		matrice[13][3] = Cases.BONUS;
		
>>>>>>> 8f41e3fc44a027d12ce972a048058cbf373903ff
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
					b.setRadiusX(3);
					b.setRadiusY(3);
					break;
				case BONUS:
					b.setFill(Color.PINK);
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
<<<<<<< HEAD
	public Cases[][] getMatrice(){
		return matrice;
	}
=======
	
	public Cases[][] getMatrice(){
		return matrice;
	}
	
>>>>>>> 8f41e3fc44a027d12ce972a048058cbf373903ff
}