package com.2dgames.application.moteur.generique;

import moteur.generique.IMoteur;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.beans.PropertyChangeEvent;

public class PanneauScore implements IPanneauScore {
 private JTextField score;
 private JTextField niveau;
 private JPanel panneau;

 public PanneauScore() {
  panneau = new JPanel();
  JLabel labscore = new JLabel("Score");
  panneau.add(labscore);
  score.setEditable(false);
  panneau.add(labniv);
  niveau = new JTextField("1", 5);
  niveau.setEditable(false);
  panneau.add(niveau);
 }

 public void propertyChange(PropertyChangeEvent e) {
  String name = e.getPropertyName();
  int valeur = (int) e.getNewValue();
  if (name.equals("score"))
   score.setText("" + valeur);
 }

 public JPanel getPanneau() {
  return panneau;
 }

}
