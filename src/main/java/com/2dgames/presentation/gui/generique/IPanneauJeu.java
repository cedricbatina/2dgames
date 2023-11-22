package com.2dgames.application.moteur.generique;

import java.awt.Graphics;
import javax.swing.JFrame;

public interface IPanneauJeu {
 boolean contains(int x, int y);

 void dessineGameOver(Graphics g);

 void remplirImageFond(String imageFile, Graphics g);

 IAireJeu getAireJeu();

 int getLargeur();

 int getHauteur();

 void getAireJeu(IAire spe);

 JPanel getPanneau();
}
