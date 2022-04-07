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
	private final int WIDTH = 1000;
	private final int HEIGHT = 840;
	private Blinky blinky;
	private Image scoreImage;
	private Image healthImage;	
	static Stage classStage = new Stage();

	public void start(Stage primaryStage) {
		
		
		try {
			ecranOrdi = Screen.getPrimary();
			root = new Group();
			scene = new Scene(root,WIDTH,HEIGHT); //taille fen�tre 
			primaryStage.setResizable(false);
			classStage = primaryStage;
			score = 0;
			ajoutPointScore(100);
			
			fond = new GridPane();
			remplissageFond();
			
			scene.getStylesheets().add(getClass().getResource("../application.css").toExternalForm());
			
			primaryStage.setTitle("PACMAN TEST");// configure le titre de la fen�tre
			primaryStage.setScene(scene);//on met la scene sur la fenetre
			primaryStage.show();// afficher la fenetre
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		labyrinthe = new Labyrinthe();
		labyrinthe.affichage(root);
		
		pacMan = new PacMan(scene, labyrinthe, this);
		pacMan.affichage(root);
		
		blinky = new Blinky(labyrinthe);
		blinky.affichage(root);
		// Image score :
		
		scoreImage = new Image("file:score-image.png", 160, 100, true, true);
		ImageView imageAfficherScore = new ImageView(scoreImage);
		imageAfficherScore.setX(820);
		imageAfficherScore.setY(40);
		healthImage = new Image("file:health-image.png", 120, 100, true, true);
		ImageView imageAfficherHealth = new ImageView(healthImage);
		imageAfficherHealth.setX(840);
		imageAfficherHealth.setY(175);
		
		gestionScore();
		root.getChildren().addAll(imageAfficherHealth,imageAfficherScore);
	
		// Cette classe s'occupe d'�xecuter le code dans sa m�thode handle avec une fr�quence constante
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
				gestionScore();
				root.getChildren().addAll(imageAfficherHealth,imageAfficherScore);
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
		for(int i =0; i<pacMan.getHealthPacMan(); i++) {
			Arc a = new Arc();
			a.setCenterX(865+36*i);
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
	public void ajoutPointScore(int point) {
		score = score + point;
	}
	public void gestionScore() {
		int i = Integer.toString(score).length()-1;
		etiquetteScore = new Label(""+score);
		etiquetteScore.setFont(Font.font("Arial",30));
		etiquetteScore.setTextFill(Color.WHITE);
		etiquetteScore.setLayoutX(890-i*8);
		etiquetteScore.setLayoutY(100);
		affichageHealthPacMan();
		root.getChildren().add(etiquetteScore);

	}
}
