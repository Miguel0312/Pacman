package application;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class interfaceFin extends Application{
	private Group root;
	private Scene scene;
	static Stage classStage = new Stage();
	private Label etiquetteScore;
	private int score;
	private boolean victoire;

	private final int HEIGHT = 600;
	private final int WIDTH = 600;

	public void start(Stage primaryStage) {
		try {
			root = new Group();
			scene = new Scene(root,HEIGHT,WIDTH); //taille fenêtre 
			primaryStage.setResizable(false);
			classStage = primaryStage;
			
			primaryStage.getIcons().addAll(new Image("file:pacman-image.png"));

			
			Rectangle bgrd = new Rectangle();
			bgrd.setX(0);
			bgrd.setY(0);
			bgrd.setHeight(HEIGHT);
			bgrd.setWidth(WIDTH);
			bgrd.setFill(Color.BLACK);

			Image resetImage = new Image("file:reset-image.png", 180, 800, true, true);
			ImageView imageAfficherReset = new ImageView(resetImage);
			imageAfficherReset.setX(50);
			imageAfficherReset.setY(480);
			imageAfficherReset.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
				public void handle(MouseEvent event) {
				Jeu ctc = new Jeu();
				ctc.start(Jeu.classStage);
				classStage.close();
				event.consume();
				}
			});
			
			Image exitImage = new Image("file:exitAccueil-image.png", 200, 800, true, true);
			ImageView imageAfficherExit = new ImageView(exitImage);
			imageAfficherExit.setX(350);
			imageAfficherExit.setY(465);
			imageAfficherExit.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
				public void handle(MouseEvent event) {
				Platform.exit();
				event.consume();
				}
			});
			
			etiquetteScore = new Label("Score : "+score);
			etiquetteScore.setFont(Font.font("Arial",50));
			etiquetteScore.setTextFill(Color.WHITE);
			etiquetteScore.setLayoutX(170);
			etiquetteScore.setLayoutY(350);
			
			root.getChildren().addAll(bgrd,imageAfficherReset,imageAfficherExit,etiquetteScore);
			AudioClip gameOver = new AudioClip("file:gameover-sound.mp3");
			AudioClip win = new AudioClip("file:win-sound.mp3");


			if(victoire) {
				gagner();
				win.play();
			}else {
				perdu();
				gameOver.play();
			}
			
			

			scene.getStylesheets().add(getClass().getResource("../application.css").toExternalForm());
			primaryStage.setTitle("INTERFACE Fin TEST");// configure le titre de la fenêtre
			primaryStage.setScene(scene);//on met la scene sur la fenetre
			primaryStage.show();// afficher la fenetre
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void gagner() {
		//les boutons 
		
		Image titleImage = new Image("file:theEnd-image.png", 400, 800, true, true);
		ImageView imageAfficherTitle = new ImageView(titleImage);
		imageAfficherTitle.setX(100);
		imageAfficherTitle.setY(0);
		/*imageAfficherTitle.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				System.out.println("aa");
				event.consume();
			}
		});*/
		
		
		root.getChildren().addAll(imageAfficherTitle);
	}
	public void perdu() {
		Image perduImage = new Image("file:lose-image.png", 400, 800, true, true);
		ImageView imageAfficherPerdu = new ImageView(perduImage);
		imageAfficherPerdu.setX(100);
		imageAfficherPerdu.setY(20);
		

		root.getChildren().addAll(imageAfficherPerdu);

	}
	public void setVictoire(boolean b) {
		victoire = b;
	}
	public void setScore(int s) {
		score = s;
	}
	
}