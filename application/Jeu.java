package application;

import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.media.AudioClip;
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
	private PacMan pacMan;
	private Labyrinthe labyrinthe;
	private final int WIDTH = 1000;
	private final int HEIGHT = 840;
	private Blinky blinky;
	private Inky inky;
	private Pinky pinky;
	private Clyde clyde;
	private Image scoreImage;
	private Image healthImage;	
	static Stage classStage = new Stage();
	private Cases[][] matrice;
	private Color mode = Color.BLACK;
	private ArrayList<Entite> entites = new ArrayList<Entite>();
	private ArrayList<Fantome> fantomes = new ArrayList<Fantome>();

	public void start(Stage primaryStage) {
		
		try {
			root = new Group();
			scene = new Scene(root,WIDTH,HEIGHT); //taille fenêtre 
			primaryStage.setResizable(false);
			classStage = primaryStage;
			score = 0;
			
			fond = new GridPane();
			remplissageFond();
			
			scene.getStylesheets().add(getClass().getResource("../application.css").toExternalForm());
			
			primaryStage.setTitle("PACMAN");// configure le titre de la fenêtre
			primaryStage.setScene(scene);//on met la scene sur la fenetre
			primaryStage.show();// afficher la fenetre
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		labyrinthe = new Labyrinthe(mode);
		labyrinthe.affichage(root);
		matrice = labyrinthe.getMatrice();
		
		pacMan = new PacMan(scene, labyrinthe);
		pacMan.affichage(root);
		
		blinky = new Blinky(labyrinthe, pacMan);
		inky = new Inky(labyrinthe, pacMan);
		pinky = new Pinky(labyrinthe, pacMan);
		clyde = new Clyde(labyrinthe, pacMan);
		
		entites.add(pacMan);
		entites.add(blinky);
		entites.add(inky);
		entites.add(pinky);
		entites.add(clyde);
		
		fantomes.add(blinky);
		fantomes.add(inky);
		fantomes.add(pinky);
		fantomes.add(clyde);
		
		// Image score :
		scoreImage = new Image("file:score-image.png", 160, 100, true, true);
		ImageView imageAfficherScore = new ImageView(scoreImage);
		imageAfficherScore.setX(820);
		imageAfficherScore.setY(40);
		healthImage = new Image("file:health-image.png", 120, 100, true, true);
		ImageView imageAfficherHealth = new ImageView(healthImage);
		imageAfficherHealth.setX(840);
		imageAfficherHealth.setY(175);
		
		//testFini();
		
		primaryStage.getIcons().addAll(new Image("file:pacman-image.png"));
		
		gestionScore();
		root.getChildren().addAll(imageAfficherHealth,imageAfficherScore);
		

		// Cette classe s'occupe d'éxecuter le code dans sa méthode handle avec une fréquence constante
		new AnimationTimer() {
			private long lastUpdate = System.nanoTime();
			public void handle(long now) {
				int deltaTemps = (int)((now-lastUpdate)/1000000);
				root.getChildren().clear();
				labyrinthe.affichage(root);
				for(Entite e : entites) {
					e.update(deltaTemps);
					e.affichage(root);
				}
				affichageHealthPacMan();
				gestionScore();
				mangerBonbon();
				collisionFantome();
				if(estFini()) {
					interfaceFin ctc = new interfaceFin();
					ctc.setVictoire(true);
					ctc.setScore(score);
					ctc.start(interfaceFin.classStage);
					stop();
					primaryStage.close();
				}
				if(pacMan.getHealthPacMan() ==0 ) {
					interfaceFin ctc = new interfaceFin();
					ctc.setVictoire(false);
					ctc.setScore(score);
					ctc.start(interfaceFin.classStage);
					stop();
					primaryStage.close();
					System.out.println("abc");
				}
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
	public void mangerBonbon() {		
		AudioClip bonus = new AudioClip("file:bonus-sound1.mp3");
		AudioClip bonbon = new AudioClip("file:bonbon-sound.mp3");
		AudioClip fuite = new AudioClip("file:fuite-sound.mp3");
		
		int[] position = pacMan.getPosition();
		if(matrice[position[1]/40][position[0]/40]==Cases.BOMBOM) {
			bonbon.play();
			ajoutPointScore(100);
			matrice[position[1]/40][position[0]/40]= Cases.VIDE;
		} else if(matrice[position[1]/40][position[0]/40]==Cases.BONUS) {
			bonus.play();
			fuite.play();
			ajoutPointScore(100);
			matrice[position[1]/40][position[0]/40]= Cases.VIDE;
			blinky.commencerFuite();
			inky.commencerFuite();
			pinky.commencerFuite();
			clyde.commencerFuite();
		}
		
	}
	public void testFini() {
		for(int i=0;i<matrice.length;i++) {
			for(int j=0;j<matrice[0].length;j++) {
				if(matrice[i][j]==Cases.BOMBOM|| matrice[i][j]==Cases.BONUS) {
					matrice[i][j]=Cases.VIDE;
					
				}	
			}
		}
		matrice[1][1]=Cases.BOMBOM;
	}
<<<<<<< HEAD
	public void testPerdu() {
		pacMan.setHealthPacMan(0);
	}
=======
	
	
	public void setPositionDebut() {
		pacMan.recommencer(2000);
		blinky.recommencer(7000);
		inky.recommencer(4000);
		pinky.recommencer(8500);
		clyde.recommencer(5500);
	}
	
	public void collisionFantome() { 	
		int[] p=pacMan.getPosition();
		for(Fantome fantome : fantomes) {
			int[] positionFantome = fantome.getPosition();
			if(Math.abs(positionFantome[0]-p[0])<=40&&Math.abs(positionFantome[1]-p[1])<=40) {
				if(fantome.getFuite()) {
					fantome.recommencer(2000);
					ajoutPointScore(100);
				} else {
					setPositionDebut();
					pacMan.setHealthPacMan(pacMan.getHealthPacMan()-1);
				}
			}
		}
	}
	
>>>>>>> c14793c6dd2c8697de56e6b4473f70c6410d4c2c
	public boolean estFini() {
		int nbBombom = 0;
		for(int i=0;i<matrice.length;i++) {
			for(int j=0;j<matrice[0].length;j++) {
				if(matrice[i][j]==Cases.BOMBOM || matrice[i][j]==Cases.BONUS) {
					nbBombom++;
					break;
				}
			}
		}
		return (nbBombom ==0);
	}
	
	public void setMode(Color mode) {
		this.mode = mode;
	}
}
