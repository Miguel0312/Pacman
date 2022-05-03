package application;

import javafx.scene.Group;

//Il ne peut pas avoir une instance d'une classe abstraite
public abstract class Entite{

    protected int x; 
    protected int y;
    protected int vx;
    protected int vy;

    //Ces methodes sont les mêmes pour tous les sous-classes d'Entite
    public int[] getPosition() {
		int[] position = {x,y};
		return position;
    }
    
    
    public int[] getVitesse() {
    	int[] vitesse = {vx,vy};
		return vitesse;
    };

    public void setPosition(int x, int y) {
    	this.x = x*40+20;
    	this.y = y*40+20;
    }
    public void setVitesse(int vx, int vy) {
    	this.vx = vx;
    	this.vy = vy;
    }
    public abstract void recommencer(int attente);
    
    //L'implementation de ces methodes change pour des differentes sous-classes
    //deltaTemps doit être donné en ms
    abstract public void update(int deltaTemps);
    abstract public void affichage(Group root);
}