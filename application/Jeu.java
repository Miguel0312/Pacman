package application;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
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
	private final int WIDTH = 960;
	private final int HEIGHT = 800;
	private Blinky blinky;
	private Image scoreImage;
	private Image healthImage;
	private int healthPacMan;
	
	public void start(Stage primaryStage) {
		
		
		try {
			ecranOrdi = Screen.getPrimary();
			healthPacMan = 3;

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
		// Image score :
		
		scoreImage = new Image("file:score-image.png", 160, 100, true, true);
		ImageView imageAfficherScore = new ImageView(scoreImage);
		imageAfficherScore.setX(780);
		imageAfficherScore.setY(40);
		healthImage = new Image("file:health-image.png", 120, 100, true, true);
		ImageView imageAfficherHealth = new ImageView(healthImage);
		imageAfficherHealth.setX(800);
		imageAfficherHealth.setY(160);
		
		etiquetteScore = new Label(""+score);
		etiquetteScore.setFont(Font.font("Arial",30));
		etiquetteScore.setTextFill(Color.WHITE);
		etiquetteScore.setLayoutX(850);
		etiquetteScore.setLayoutY(100);
		affichageHealthPacMan();
		root.getChildren().addAll(imageAfficherHealth,imageAfficherScore,etiquetteScore);
	
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
				affichageHealthPacMan();
				root.getChildren().addAll(imageAfficherHealth,imageAfficherScore,etiquetteScore);
				lastUpdate = now;
			}
		}.start();
	}
	
	
	
	public void remplissageFond() {
		for(int i = 0; i<scene.getWidth()/20;i++) {
			for(int k = 0; k<scene.getHeight()/20;k++) {
				fond.add(new Rectangle(20,20,Color.WHITE), 20*i, 20*k);
			}
		}
		
	}
	public void affichageHealthPacMan() {
		for(int i =0; i<healthPacMan; i++) {
			Arc a = new Arc();
			a.setCenterX(825+36*i);
			a.setCenterY(230);
			a.setRadiusX(14);
			a.setRadiusY(14);
			a.setLength(310);
			a.setStartAngle(30);
			a.setType(ArcType.ROUND);
			a.setFill(Color.YELLOW);
			root.getChildren().add(a);
		}
	}
	
	public void setHealthPacMan(int h ) {
		healthPacMan = h;
	}
	public void gestionScore(int point) {
		score = score + point;
	}
}
