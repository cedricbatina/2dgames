package presentation.gui.generique;

import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public interface IPanneauJeu {
 boolean contains(int x, int y);

 void dessineGameOver(Graphics g);

 void remplirImageFond(String imageFile, Graphics g);

 IAireJeu getAireJeu();

 int getLargeur();

 int getHauteur();

 void getAireJeu(IAireJeu spe);

 JPanel getPanneau();
}
