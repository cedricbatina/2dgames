package moteur.generique;

import java.awt.Graphics;
import java.beans.PropertyChangeListener;

public interface IMoteur {
 void demmarrerJeu();

 void terminerJeu();

 void setMoteurSpecifique(IFabriqueObjets spe);

 IFabriqueObjets getMoteurSpecifique();

setGui(IGui g);

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
}
