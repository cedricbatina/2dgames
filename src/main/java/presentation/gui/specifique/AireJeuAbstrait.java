package presentation.gui.specifique;

import moteur.generique.IGui;
import moteur.generique.IMoteur;
import presentation.gui.generique.IAireJeu;

public abstract class AireJeuAbstrait implements IAireJeu {
 protected IGui gui;
 protected IMoteur moteur;

 public void setGui(IGui g) {
  gui = g;
 }

 public void setMoteur(IMoteur m) {
  moteur = m;
 }

 public int getNbLignes() {
  return moteur.getParamInt(1);

 }

 public int getNbColonnes() {
  return moteur.getParamInt(2);
 }

 public int getTailleCellule() {
  return moteur.getParamInt(3);
 }

 public String getImageFond() {
  return moteur.getParamString(1);
 }

}
