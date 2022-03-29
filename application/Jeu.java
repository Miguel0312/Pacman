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
	private Blinky blinky;
	
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
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		labyrinthe = new Labyrinthe();
		labyrinthe.affichage(root);
		
		pacMan = new PacMan(scene, labyrinthe);
		pacMan.affichage(root);
		
		blinky = new Blinky(labyrinthe);
		blinky.affichage(root);
		
		// Cette classe s'occupe d'éxecuter le code dans sa méthode handle avec une fréquence constante
		new AnimationTimer() {
			private long lastUpdate = System.nanoTime();
			public void handle(long now) {
				int deltaTemps = (int)((now-lastUpdate)/1000000);
				root.getChildren().clear();
				labyrinthe.affichage(root);
				pacMan.update(deltaTemps);
				pacMan.affichage(root);
				blinky.update(deltaTemps);
				blinky.affichage(root);
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
	

	}
	public void remplissageFond() {
		for(int i = 0; i<scene.getWidth()/20;i++) {
			for(int k = 0; k<scene.getHeight()/20;k++) {
				fond.add(new Rectangle(20,20,Color.WHITE), 20*i, 20*k);
			}
		}
		
	}
}
