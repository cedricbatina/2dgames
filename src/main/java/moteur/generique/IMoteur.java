package moteur.generique;

//import moteur.generique.EtatMoteur;
//import presentation.gui.generique;
import java.awt.Graphics;
import java.beans.PropertyChangeListener;

public interface IMoteur {
 void demmarrerJeu();

 void terminerJeu();

 void setMoteurSpecifique(IMoteurSpecifique spe);

 IMoteurSpecifique getMoteurSpecifique();

 void setGui(IGui g);

 IGui getGui();

 void dessine(Graphics g);

 void toucheHaut();

 void toucheBas();

 void toucheGauche();

 void toucheDroite();

 int getParamInt(int p);

 EtatMoteur getEtat();

 void setEtat(EtatMoteur e);

 int getScore();

 int getNiveau();

 ISon getSon();

 void addPropertyChangeListener(PropertyChangeListener pcl);

 String getParamString(int i);
}
