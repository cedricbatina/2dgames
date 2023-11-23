package presentation.gui.generique;

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

import org.w3c.dom.css.Rect;

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

 public PanneauJeu(IGui g) {
  this.gui = g;
  panneau = new JPanel() {
   public void paintComponent(Graphics g) {
    super.paintComponent(g);
    aire.peindre(g);
   }
  };
  panneau.setFocusable(true);
  panneau.requestFocus();
  panneau.addKeyListener(new ToucheListener());

 }

 public boolean contains(int x, int y) {
  if ((x < 0) || (x >= aire.getNbLignes()))
   return false;
  if ((y < 0) || (y >= aire.getColonnes()))
   return false;
  return true;
 }

 public void setAireJeu(IAireJeu spe) {
  aire = spe;
 }

 public void dessineGameover(Graphics g) {
  int h = getHauteur() / 5;
  g.setColor(Color.YELLOW);
  g.fillRect(0, 0, getLargeur(), getHauteur());
  g.setFont(new Font("verdana", Font.BOLD, 20));
  g.drawString("GAME OVER", position(g, "GAME OVER"), h);
  h += h;
  String s = gui.getMeilleurScore().afficherTop5();
  g.setColor(Color.GRAY);
  g.drawString("MEILLEURS SCORES: ", position(g, line), h);
  for (String line : s.split("\n")) {
   h += g.getFontMetrics().getHeight();
   g.drawString(line, position(g, line), h);
  }
 }

 private int position(Graphics g, String s) {
  return getLargeur() / 2 - (g.getFontMetrics().stringWidth(s)) / 2;
 }

 public void remplirImageFond(String imagePath, Graphics g) {
  try {
   BufferedImage bimg = ImageIO.read(new File(imagePath));
   Graphics2D g2 = (Graphics2D) g;
   Rectangle r = new Rectangle(0, 0, 32, 32);
   g2.setPaint((new TexturePaint(bimg, r)));
   Rectangle rect = new Rectangle(0, 0, getLargeur(), getHauteur());
   g2.fill(rect);

  } catch (Exception e) {

  }
 }

 public IAireJeu getAireJeu() {
  return aire.getNbLignes() * aire.getTailleCellule();
 }

 public int getLargeur() {
  return aire.getNbLignes() * aire.getTailleCellule();

 }

 public JPanel getPanneau() {
  return panneau;
 }

 /*
  * @Override
  * public void dessineGameOver(Graphics g) {
  * // TODO Auto-generated method stub
  * throw new
  * UnsupportedOperationException("Unimplemented method 'dessineGameOver'");
  * }
  * 
  * @Override
  * public int getHauteur() {
  * // TODO Auto-generated method stub
  * throw new UnsupportedOperationException("Unimplemented method 'getHauteur'");
  * }
  * 
  * @Override
  * public void getAireJeu(IAire spe) {
  * // TODO Auto-generated method stub
  * throw new UnsupportedOperationException("Unimplemented method 'getAireJeu'");
  * }
  */
}
