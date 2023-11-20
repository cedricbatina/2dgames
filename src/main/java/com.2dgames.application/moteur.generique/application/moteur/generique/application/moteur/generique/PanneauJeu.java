package application.moteur.generique;

import gui.generique;
import moteur.generique.IGui;
import moteur.generique.EtatMoteur;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Font;
import java.awt.TexturePaint;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import javax.imageio.ImageIO;
import java.io.File;

@SuppressWarnings("serial")
public class PanneauJeu implements IPanneauJeu {
 private IAire aire;
 private IGui gui;
 private JPanel panneau;

 private class ToucheListener extends KeyAdapter {
  public void KeyPressed(KeyEvent e) {
   if (gui.getMoteur().getEtat() == EtatMoteur.ARRETE)
    return;
   aire.appuiTouche(e.getKeyCode());
  }
 }

 public PanneauJeu (IGUI g) {
  this.gui = g;
  panneau = new JPanel () {
   public void paintComponent(Graphics g) {
    super.paintComponent(g);
    aire.peindre(g);
   }
  }
 }
}
