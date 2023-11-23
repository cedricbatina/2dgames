package moteur.generique;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.awt.Graphics;
import presentation.gui.generique.*;
import moteur.generique.IMoteur;
import moteur.generique.EtatMoteur;

public class Moteur implements IMoteur {
 private IMoteurSpecifique moteur;
 private IGui gui;
 private ISon son;
 protected EtatMoteur etat;
 private int score;
 private int niveau;
 private PropertyChangeSupport support;

 public Moteur() {
  support = new PropertyChangeSupport(this);
  etat = EtatMoteur.INITIALISE;
 }

 public void addPropertyChangeListener(PropertyChangeListener pcl) {
  support.addPropertyChangeListener(pcl);
 }

 public void demmarrerJeu() {
  Runnable Jeu = () -> {
   moteur.initialiserJeu();
   boucleDeJeu();
  };
  new Thread(jeu).start();
 }

 private void boucleDeJeu() {
  if (etat == EtatMoteur.INITIALISE || etat == EtatMoteur.GAMEOVER) {
   moteur.genererJeu();
   etat = EtatMoteur.EN_COURS;

  }
  long tempsDebut, tempsPris, tempsRestant;
  while (true) {
   tempsDebut = System.nanoTime();
   if (etat == EtatMoteur.GAMEOVER) {
    gui.resetPanneauControle();
    break;
   }
   if (etat == EtatMoteur.EN_COURS)
    moteur.mettreAJourJeu();
   gui.repaintPanneauJeu();
   tempsPris = (moteur.getPeriodeMaj() - tempsPris) / 1000000;
   if (tempsRestant < 10)
    tempsRestant = 10;
   try {
    Thread.sleep(tempsRestant);
   } catch (InterruptedException ex) {
   }
  }
 }

 public void terminerJeu() {
  son.jouerFin();
  setEtat(EtatMoteur.GAMEOVER);
  gui.getMeilleurScore().ajouterTop5(score);
 }

 public void setMoteurSpecifique(IFabriqueObjets spe) {
  moteur = spe;
 }

 public IFabriqueObjets getMoteurSpecifique() {
  return moteur;
 }

 public void setGui(IGui g) {
  gui = g;
 }

 public IGui getGui() {
  return gui;
 }

 public void dessine(Graphics g) {
  moteur.dessine(g);
 }

 public void toucheHaut() {
  moteur.toucheHaut();
 }

 public void toucheBas() {
  moteur.toucheBas();
 }

 public void toucheGauche() {
  moteur.toucheGauche();
 }

 public void toucheDroite() {
  moteur.toucheDroite();
 }

 public int getParamInt(int p) {
  return moteur.getParamInt(p);
 }

 public String getParamString(int p) {
  return moteur.getParamString(p);

 }

 public EtatMoteur getEtat() {
  return etat;
 }

 public void setEtat(EtatMoteur e) {
  etat = e;
 }

 public int getScore() {
  return score;
 }

 public int getNiveau() {
  return niveau;
 }

 public void setScore(int s) {
  support.firePropertyChange("score", this.score, s);
  score = s;
 }

 public void setNiveau(int n) {
  support.firePropertyChange("niveau", this.niveau, n);
  niveau = n;
 }

 public void setSon(ISon s) {
  son = s;
 }

 public ISon getSon() {
  return son;
 }
 /*
  * @Override
  * public void setMoteurSpecifique(IMoteurSpecifique spe) {
  * throw new
  * UnsupportedOperationException("Unimplemented method 'setMoteurSpecifique'");
  * }
  * 
  * @Override
  * public void setEtat(EtatMoteur e) {
  * throw new UnsupportedOperationException("Unimplemented method 'setEtat'");
  * }
  */

}
