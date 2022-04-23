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
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class interfaceAccueil extends Application{
	private Group root;
	private Scene scene;	
	private final int HEIGHT = 800;
	private final int WIDTH = 800;
	private Rectangle bgrd;
	private Stage primaryStage;
	private Color mode;
	private boolean modeChoisi = true;

	public void start(Stage primaryStage) {
		try {
			this.primaryStage = primaryStage;
			root = new Group();
			scene = new Scene(root,HEIGHT,WIDTH); //taille fenêtre 
			primaryStage.setResizable(false);
	
			primaryStage.getIcons().addAll(new Image("file:pacman-image.png"));
			
			//Backgroud 
			bgrd = new Rectangle();
			bgrd.setX(0);
			bgrd.setY(0);
			bgrd.setHeight(HEIGHT);
			bgrd.setWidth(WIDTH);
			bgrd.setFill(Color.BLACK);
			
			//titre 
			interfacePrincipal();
			
			scene.getStylesheets().add(getClass().getResource("../application.css").toExternalForm());
			primaryStage.setTitle("INTERFACE PACMAN TEST");// configure le titre de la fenêtre
			primaryStage.setScene(scene);//on met la scene sur la fenetre
			primaryStage.show();// afficher la fenetre
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	
	public void interfacePrincipal() {
		if(modeChoisi) {
			mode = Color.BLACK;
		}else {
			mode = Color.WHITE;
		}
		Image titreImage = new Image("file:logoPacMan-image.png", 600, 800, true, true);
		ImageView imageAfficherTitre = new ImageView(titreImage);
		imageAfficherTitre.setX(100);
		imageAfficherTitre.setY(50);
		//lesBoutons :
		Image playImage = new Image("file:playAccueil-image.png", 200, 800, true, true);
		ImageView imageAfficherPlay = new ImageView(playImage);
		imageAfficherPlay.setX(300);
		imageAfficherPlay.setY(250);
		imageAfficherPlay.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				Jeu ctc = new Jeu();
				ctc.setMode(mode);
				ctc.start(Jeu.classStage);
				primaryStage.close();
				event.consume();
			}
		});
		
		Image scoreImage = new Image("file:scoreAccueil-image.png", 220, 800, true, true);
		ImageView imageAfficherScore = new ImageView(scoreImage);
		imageAfficherScore.setX(290);
		imageAfficherScore.setY(350);
		imageAfficherScore.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
			System.out.println("Tile pressed ");
			event.consume();
			}
		});
		
		Image creditImage = new Image("file:credit-image.png", 200, 800, true, true);
		ImageView imageAfficherCredit = new ImageView(creditImage);
		imageAfficherCredit.setX(300);
		imageAfficherCredit.setY(460);
		imageAfficherCredit.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
			credit();
			event.consume();
			}
		});
		
		Image settingImage = new Image("file:settings-image.png", 70, 800, true, true);
		ImageView imageAfficherSetting = new ImageView(settingImage);
		imageAfficherSetting.setX(700);
		imageAfficherSetting.setY(700);
		imageAfficherSetting.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
			settings();
			event.consume();
			}
		});
		Image exitImage = new Image("file:exitAccueil-image.png", 225, 800, true, true);
		ImageView imageAfficherExit = new ImageView(exitImage);
		imageAfficherExit.setX(285);
		imageAfficherExit.setY(550);
		imageAfficherExit.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
			Platform.exit();

			event.consume();
			}
		});
		root.getChildren().addAll(bgrd,imageAfficherTitre, imageAfficherScore, imageAfficherPlay, imageAfficherExit,imageAfficherCredit,imageAfficherSetting);

	}
	public void settings() {
		root.getChildren().clear();
		
		Label etiquetteMode = new Label("Mode nuit");
		etiquetteMode.setFont(Font.font("Arial",30));
		etiquetteMode.setTextFill(Color.WHITE);
		etiquetteMode.setLayoutX(300);
		etiquetteMode.setLayoutY(75);
		Image exitImage = new Image("file:exitAccueil-image.png", 225, 800, true, true);
		ImageView imageAfficherExit = new ImageView(exitImage);
		imageAfficherExit.setX(285);
		imageAfficherExit.setY(550);
		imageAfficherExit.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
			root.getChildren().clear();
			interfacePrincipal();
			event.consume();
			}
		});
		if(modeChoisi) {
			Image yesModeImage = new Image("file:yes-image.png", 150, 800, true, true);
			ImageView imageAfficherYesMode = new ImageView(yesModeImage);
			imageAfficherYesMode.setX(100);
			imageAfficherYesMode.setY(50);
			imageAfficherYesMode.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
				public void handle(MouseEvent event) {
					root.getChildren().clear();
					modeChoisi = false;
					settings();
					event.consume();
				}
			});
			root.getChildren().addAll(bgrd,imageAfficherExit,etiquetteMode,imageAfficherYesMode);

		}else {
			Image noModeImage = new Image("file:no-image.png", 150, 800, true, true);
			ImageView imageAfficherNoMode = new ImageView(noModeImage);
			imageAfficherNoMode.setX(100);
			imageAfficherNoMode.setY(50);
			imageAfficherNoMode.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
				public void handle(MouseEvent event) {
					root.getChildren().clear();
					modeChoisi = true;
					settings();
					event.consume();
				}
			});
			root.getChildren().addAll(bgrd,imageAfficherExit,etiquetteMode,imageAfficherNoMode);
		}
	}
	public void credit() {
		root.getChildren().clear();
		Image teteImage = new Image("file:tete-image.png", 700, 1500, true, true);
		ImageView imageAfficherTete = new ImageView(teteImage);
		imageAfficherTete.setX(50);
		imageAfficherTete.setY(0);
		
		Image exitImage = new Image("file:exitAccueil-image.png", 200, 800, true, true);
		ImageView imageAfficherExit = new ImageView(exitImage);
		imageAfficherExit.setX(550);
		imageAfficherExit.setY(700);
		imageAfficherExit.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
			root.getChildren().clear();
			interfacePrincipal();
			event.consume();
			}
		});
		root.getChildren().addAll(bgrd,imageAfficherExit,imageAfficherTete);
		
	}
}