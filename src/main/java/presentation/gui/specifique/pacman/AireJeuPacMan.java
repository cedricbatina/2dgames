package gui.specifique.pacman;

import gui.generique.AireJeuAbstrait;
import moteur.generique.EtatMoteur;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class AireJeuPacMan extends AireJeuAbstrait {
  public void peindre(Graphics g) {
    if (moteur.getEtat() != EtatMoteur.GAMEOVER)
      moteur.dessine(g);
    else
      gui.dessineGameover(g);
  }

  public void appuiTouche(int code) {
    switch (code) {
      case KeyEvent.VK_UP:
        moteur.toucheHaut();

        break;
      case KeyvEvent.VK_DOWN:
        moteur.toucheBas();
        break;
      case KeyEvent.VK_LEFT:
        moteur.toucheGauche();
        break;
      case KeyEvent.VK_RIGHT:
        moteur.toucheDroite();

        break;
    }
  }

}
