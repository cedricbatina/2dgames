package jeu.generique;

import moteur.generique.IMoteurSpecifique;
import moteur.generique.Moteur;
import moteur.generique.IAireJeu;
import gui.generique.Gui;
import gui.generique.PanneauJeu;
import persist.getMeilleurScore;
import utils.son.Son;

public class MonteurGenerique extends MonteurAbstrait {

 private moteur.generique.Gui gui;
 private Moteur moteur;
 private IAireJeu aire;
 private IMoteurSpecifique ims;
 private String nom;
 private String pers;

 public MonteurGenerique(String n, IAireJeu a, IMoteurSpecifique i, String p) {
  nom = n;
  aire = a;
  ims = i;
  pers = p;
 }

 public void construireGui() {
  gui = new Gui(nom);
  presentation.gui.generique.PanneauJeu pjeu = new PanneauJeu(gui);
  gui.setPanneauJeu(pjeu);
  pjeu.setAireJeu(aire);
  aire.setGui(gui);

 }

 public void construireMoteur() {
  moteur = new Moteur();
  gui.setMoteur(moteur);
  moteur.setMoteurSpecifique(ims);
  ims.setMoteur(moteur);
  aire.setMoteur(moteur);
 }

 public void ajouterSon() {
  Son son = new Son();
  son.initialiser();
  moteur.setSon(son);
 }

 public void ajouterPersistance() {
  MeilleurScore ms = new MeilleurScore(nom, pers);
  gui.setMeilleurScore(ms);
 }

 public void terminerConstruction() {
  gui.terminerGui();
 }
}
