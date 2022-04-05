package application;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class interfaceAccueil extends Application{
	private Group root;
	private Scene scene;	
	private final int HEIGHT = 800;
	private final int WIDTH = 800;

	public void start(Stage primaryStage) {
		try {
			root = new Group();
			scene = new Scene(root,HEIGHT,WIDTH); //taille fenêtre 
			primaryStage.setResizable(false);
			
			//Backgroud 
			Rectangle bgrd = new Rectangle();
			bgrd.setX(0);
			bgrd.setY(0);
			bgrd.setHeight(HEIGHT);
			bgrd.setWidth(WIDTH);
			bgrd.setFill(Color.BLACK);
			
			//titre 
			Image titreImage = new Image("file:logoPacMan-image.png", 600, 800, true, true);
			ImageView imageAfficherTitre = new ImageView(titreImage);
			imageAfficherTitre.setX(100);
			imageAfficherTitre.setY(50);
			//lesBoutons :
			Image playImage = new Image("file:playAccueil-image.png", 200, 800, true, true);
			ImageView imageAfficherPlay = new ImageView(playImage);
			imageAfficherPlay.setX(300);
			imageAfficherPlay.setY(300);
			imageAfficherPlay.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
				public void handle(MouseEvent event) {
		            Jeu ctc = new Jeu();
		            ctc.start(Jeu.classStage);
					primaryStage.close();


				event.consume();
				}
			});
			
			Image scoreImage = new Image("file:scoreAccueil-image.png", 200, 800, true, true);
			ImageView imageAfficherScore = new ImageView(scoreImage);
			imageAfficherScore.setX(300);
			imageAfficherScore.setY(400);
			imageAfficherScore.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
				public void handle(MouseEvent event) {
				System.out.println("Tile pressed ");
				event.consume();
				}
			});
			
			Image rulesImage = new Image("file:rulesAccueil-image.png", 200, 800, true, true);
			ImageView imageAfficherRules = new ImageView(rulesImage);
			imageAfficherRules.setX(300);
			imageAfficherRules.setY(500);
			imageAfficherRules.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
				public void handle(MouseEvent event) {
				System.out.println("Tile pressed ");
				event.consume();
				}
			});
			
			Image exitImage = new Image("file:exitAccueil-image.png", 200, 800, true, true);
			ImageView imageAfficherExit = new ImageView(exitImage);
			imageAfficherExit.setX(300);
			imageAfficherExit.setY(600);
			imageAfficherExit.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
				public void handle(MouseEvent event) {
				Platform.exit();

				event.consume();
				}
			});
			
			scene.getStylesheets().add(getClass().getResource("../application.css").toExternalForm());
			root.getChildren().addAll(bgrd,imageAfficherTitre, imageAfficherScore, imageAfficherPlay,imageAfficherRules, imageAfficherExit);
			primaryStage.setTitle("INTERFACE PACMAN TEST");// configure le titre de la fenêtre
			primaryStage.setScene(scene);//on met la scene sur la fenetre
			primaryStage.show();// afficher la fenetre
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}