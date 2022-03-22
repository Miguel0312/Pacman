package application;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Jeu extends Application{
	private int score;
	private Label etiquetteScore;
	private Group root;
	private Scene scene;
	private GridPane fond;
	private Screen ecranOrdi;
	private PacMan pacMan;
	private Labyrinthe labyrinthe;
	private final int WIDTH = 800;
	private final int HEIGHT = 800;
	
	public void start(Stage primaryStage) {
		
		try {
			ecranOrdi = Screen.getPrimary();
			

			root = new Group();
			scene = new Scene(root,WIDTH,HEIGHT); //taille fenêtre 
			primaryStage.setResizable(false);
			
			score = 0;
			gestionScore(0);
			
			
			fond = new GridPane();
			remplissageFond();
			
			scene.getStylesheets().add(getClass().getResource("../application.css").toExternalForm());
			
			primaryStage.setTitle("PACMAN TEST");// configure le titre de la fenêtre
			primaryStage.setScene(scene);//on met la scene sur la fenetre
			primaryStage.show();// afficher la fenetre
			
			/*Arc b = new Arc();
			b.setCenterX(800); //permet de regler le centre du cercle
			b.setCenterY(800);//permet de regler le centre du cercle
			b.setRadiusX(10);//permet de regler le rayon du cercle
			b.setRadiusY(10);//permet de regler le rayon du cercle
			b.setStartAngle(30);//permet de regler le début de l'angle pour le remplissage
			b.setLength(360);//permet de regler la taille de l'angle pour le remplissage
			b.setType(ArcType.ROUND);//type d'arc pour le remplissage (ici permet avoir bouche)
			b.setFill(Color.YELLOW); //permet de configurer le cercle en jaune.*/ 
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		labyrinthe = new Labyrinthe();
		labyrinthe.affichage(root);
		
		pacMan = new PacMan(scene);
		pacMan.affichage(root);
		
		new AnimationTimer() {
			private long lastUpdate = System.nanoTime();
			public void handle(long now) {
				int deltaTemps = (int)((now-lastUpdate)/1000000);
				root.getChildren().clear();
				labyrinthe.affichage(root);
				pacMan.update(deltaTemps);
				pacMan.affichage(root);
				lastUpdate = now;
			}
		}.start();
	}
	
	public void gestionScore(int point) {
		score = score + point;
		etiquetteScore = new Label("Score = "+score);
		etiquetteScore.setLayoutX(300);
		etiquetteScore.setLayoutY(0);
		etiquetteScore.layoutXProperty().bind(scene.widthProperty());
		//etiquetteScore.setScaleX(0.5);
		//etiquetteScore.setScaleY(10);
	

	}
	public void remplissageFond() {
		/*for(int i = 0; i<scene.getWidth()/20;i++) {
			for(int k = 0; k<scene.getHeight()/20;k++) {
				if(Math.random()<0.5) {
					fond.add(new Rectangle(20,20, Color.BLACK), i, k);
				}else {
					fond.add(new Rectangle(20,20, Color.GREY), i, k);
					
				}
			}*/
		for(int i = 0; i<scene.getWidth()/20;i++) {
			for(int k = 0; k<scene.getHeight()/20;k++) {
				fond.add(new Rectangle(20,20,Color.WHITE), 20*i, 20*k);
			}
		}
	}
	public void tempsPause(int duree) {
		try {
			Thread.sleep(duree);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}
