package presentation.gui.generique;

import moteur.generique.IMoteur;
import moteur.generique.ISon;
import moteur.generique.IGui;
import moteur.generique.EtatMoteur;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.ImageSon;

public class PanneauControle implements IPanneauControle {
  private IGui gui;
  private IMoteur moteur;
  private IMoteur demarrer_pause;

  private JButton arret;
  private JButton son_on_off;
  private JPanel panneau;
  static ImageIcon ipause = new ImageIcon("Images/pause.png");
  static ImageIcon iarret = new ImageIcon("Images/stop.png");
  static ImageIcon ison_on = new ImageIcon("Images/audio-high.png");
  static ImageIcon ison_off = new ImageIcon("Images/audio-muted.png");
  static ImageIcon idemmarrer = new ImageIcon("Images/start.png");

  private void activer(ActionEvent e) {
    EtatMoteur etat = moteur.getEtat();
    switch (etat) {
      case INITIALISE:
      case GAMEOVER:
        demarrer_pause.setIcon(ipause);
        moteur.demarrerJeu();
        break;
      case EN_COURS:
        moteur.setEtat(EtatMoteur.EN_COURS);
        demarrer_pause.setIcon(ipause);
        break;
      default:
        break;
    }
    arret.setEnabled(true);
    gui.focusPanneauJeu();
  }

  private void arreter(ActionEvent e) {
    moteur.setEtat(EtatMoteur.GAMEOVER);
    demarrer_pause.setIcon(idemmarrer);
    demarrer_pause.setEnabled(true);
    arret.setEnabled(false);
    gui.repaintPanneauJeu();
  }

  private void son(ActionEvent e) {
    ISon son = moteur.getSon();
    if (son.isOn()) {
      son.setOff();
      son_on_off.setIcon(ison_off);
    } else {
      son.setOn();
      son_on_off.setIcon(ison_on);
    }
    gui.focusPanneauJeu();
  }

  public PanneauControle(final IGui gui) {
    this.gui = gui;
    moteur = gui.getMoteur();
    panneau = new JPanel();
    panneau.setLayout(new FlowLayout(FlowLayout.CENTER));
    demarrer_pause = new JButton(idemmarrer);
    demarrer_pause.setEnabled(true);
    panneau.add(demarrer_pause);
    arret = new JButton(iarret);
    arret.setEnabled(false);
    paaneau.add(arret);
    son_on_off = new JButton(ison_off);
    son_on_off.setEnabled(true);
    panneau.add(son_on_off);
    demarrer_pause.addActionListener(this::activer);
    arret.addActionListener(this::arreter);
    son_on_off.addActionListener(this::son);

  }

  public void reset() {
    demarrer_pause.setIcon(idemmarrer);
    demarrer_pause.setEnabled(true);
    arret.setEnabled(false);
  }

  public JPanel getPanneau() {
    return panneau;
  }
}
