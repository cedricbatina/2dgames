package moteur.generique;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class GUI implements IGUI {
 private IPanneauControle p_controle;
 private IPanneauScore p_score;
 private IPanneauJeu p_jeu;
 private IMoteur moteur;
 private JFrame fenetre;
 private IMeilleurScore meilleur_score;

 public GUI(String nom) {
  fenetre = new JFrame(nom);
 }

 public void setMeilleurScore(IMeilleurScore m) {
  meilleur_score = m;
 }

 public void terminerGUI() {
  Container cp = fenetre.getContentPane();
  cp.setLayout(new BorderLayout());
  cp.add(p_jeu.getPanneau(), BorderLayout.CENTER);
  Dimension d = new Dimension(p_jeu.getLargeur(), p_jeu.getHauteur());
  p_jeu.getPanneau().setPreferredSize(d);
  JPanel bas = new JPanel();
  bas.setLayout(new BorderLayout());
  p_score = new PanneauScore();
  bas.add(p_score.getPanneau(), BorderLayout.CENTER);
  cp.add(bas, BorderLayout.SOUTH);
  fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  fenetre.pack();
  fenetre.setLocationRelativeTo(fenetre.getParent());
  fenetre.setVisible(true);
  moteur.addPropertyChangeListener(p_score);
 }

 public void setMoteur(IMoteur m) {
  moteur = m;
 }

 public IMoteur getMoteur() {
  return moteur;
 }

 public void setPanneauJeu(IPanneauJeu P) {
  p_jeu = pj;
 }

 public IPanneauJeu getPanneauJeu() {
  return p_jeu;
 }

 public IPanneauControle getPanneauControle() {
  return p_controle;
 }

 public IPanneauScore getPanneauScore() {
  return p_score;

 }

 public IMeilleurScore getMeilleurScore() {
  return meilleur_score;
 }

 public void setImageFondPanneauJeu(String s, Graphics g) {
  p_jeu.remplirImageFond(s, g);
 }

 public void resetPanneauControle() {
  p_controle.reset();
 }

 public void repaintPanneauJeu() {
  p_jeu.getPanneau().repaint();
 }

 public void focusPanneauJeu() {
  p_jeu.getPanneau().requestFocus();
 }

 public boolean containsPanneauJeu(int x, int y) {
  return p_jeu.contains(x, y);
 }

 public void dessinGameover(Graphics g) {
  p_jeu.dessinGameover();
 }
}
