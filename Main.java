package application;
	
import javafx.application.Application;

import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.QuadCurve;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.scene.web.*;
import javafx.*;


public class Main extends Application {
	private int score;
	private Label etiquetteScore;
	private Group root;
	private Scene scene;
	private GridPane fond;
	private  Screen ecranOrdi;
	public void start(Stage primaryStage) {
		try {
			ecranOrdi = Screen.getPrimary();
			

			root = new Group();
			scene = new Scene(root,ecranOrdi.getVisualBounds().getWidth(),ecranOrdi.getVisualBounds().getWidth()); //taille fenêtre 
			
			score = 0;
			gestionScore(0);
			
			
			fond = new GridPane();
			remplissageFond();
			
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			primaryStage.setTitle("PACMAN TEST");// configure le titre de la fenêtre
			primaryStage.setScene(scene);//on met la scene sur la fenetre
			primaryStage.show();// afficher la fenetre
			
			Arc a = new Arc(); 
			a.setCenterX(scene.getWidth()/2); //permet de regler le centre du cercle
			a.setCenterY(scene.getHeight()/2);//permet de regler le centre du cercle
			a.setRadiusX(20);//permet de regler le rayon du cercle
			a.setRadiusY(20);//permet de regler le rayon du cercle
			a.setStartAngle(30);//permet de regler le début de l'angle pour le remplissage
			a.setLength(310);//permet de regler la taille de l'angle pour le remplissage
			a.setType(ArcType.ROUND);//type d'arc pour le remplissage (ici permet avoir bouche)
			a.setFill(Color.YELLOW); //permet de configurer le cercle en jaune. 
			
			Arc b = new Arc();
			b.setCenterX(800); //permet de regler le centre du cercle
			b.setCenterY(800);//permet de regler le centre du cercle
			b.setRadiusX(10);//permet de regler le rayon du cercle
			b.setRadiusY(10);//permet de regler le rayon du cercle
			b.setStartAngle(30);//permet de regler le début de l'angle pour le remplissage
			b.setLength(360);//permet de regler la taille de l'angle pour le remplissage
			b.setType(ArcType.ROUND);//type d'arc pour le remplissage (ici permet avoir bouche)
			b.setFill(Color.YELLOW); //permet de configurer le cercle en jaune. 

			scene.setOnKeyPressed(e ->{
				System.out.println("X: " +a.getCenterX()+ " Y: "+a.getCenterY() +"fenetre +"+ (a.getRadiusX()/scene.getWidth()));
				if(e.getCode() == KeyCode.Z) {
					a.setCenterY(a.getCenterY() - 1);
					a.setStartAngle(110);
					a.setLength(310);
					tempsPause(2);
				}else if(e.getCode() == KeyCode.S) {
					a.setCenterY(a.getCenterY() + 1);
					a.setStartAngle(290);
					a.setLength(310);
					tempsPause(2);
				}else if(e.getCode() == KeyCode.Q) {
					a.setCenterX(a.getCenterX()- 1);
					a.setStartAngle(200);
					a.setLength(310);
					tempsPause(2);
				}else if(e.getCode() == KeyCode.D) {
					a.setCenterX(a.getCenterX()+ 1);
					a.setStartAngle(30);
					a.setLength(310);
					tempsPause(2);
				
				}
		}) ;

			//a.radiusXProperty().bind(scene.widthProperty().multiply(0.02));
			//a.radiusYProperty().bind(scene.heightProperty().multiply(0.02));
			root.getChildren().addAll(fond, a,b, etiquetteScore );
			
			
		} catch(Exception e) {
			
			e.printStackTrace();
		}
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
		for(int i = 0; i<scene.getWidth()/20;i++) {
			for(int k = 0; k<scene.getHeight()/20;k++) {
				if(Math.random()<0.5) {
					fond.add(new Rectangle(20,20, Color.BLACK), i, k);
				}else {
					fond.add(new Rectangle(20,20, Color.GREY), i, k);
					
				}
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
	public static void main(String[] args) {
		launch(args);
	}
}
