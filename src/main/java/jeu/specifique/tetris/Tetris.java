package jeu.specifique.tetris;

import jeu.generique.MonteurGenerique;
import presentation.gui.specifique.tetris.AireJeuTetris;
import moteur.specifique.tetris.MoteurTetris;

public class Tetris {
 public static void main(String[] args) {
  new MonteurGenerique("Tetris", new AireJeuTetris(), new MoteurTetris(), "FICHIER_TEXTE").construire();
 }
}
