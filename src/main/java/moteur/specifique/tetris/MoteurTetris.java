package moteur.specifique.tetris;

import moteur.generique.IMoteur;
import moteur.generique.IMoteurSpecifique;
import moteur.generique.EtatMoteur;
import java.awt.Graphics;
import java.util.Arrays;
import java.util.stream.Stream;

public class MoteurTetris implements IMoteurSpecifique {
  private Tetromino tetromino;
  private IMoteur moteur;
  private int frequenceMaj;
  private FabriqueTetris fab;
  private Tetromino.Tetrominoes[][] tas;

  public MoteurTetris() {
    fab = new FabriqueTetris();
  }

  public void setMoteur(IMoteur m) {
    moteur = m;
  }

  public void initialiserJeu() {
    // i <=>y Tetromino, j<=>x
    fab.setMoteur(moteur);
    tas = new Tetromino.Tetrominoes[ParamTetris.LIGNES][ParamTetris.COLONNES];
    for (Tetromino.Tetrominoes[] ligne : tas)
      Arrays.fill(ligne, Tetromino.Tetrominoes.Vide);
    tetromino = (Tetromino) fab.creerObjet(ParamTetris.TETROMINO);
  }

  public void genererJeu() {
    moteur.setScore(0);
    moteur.setNiveau(1);
    frequenceMaj = ParamTetris.FREQUENCE_MAJ;
    moteur.setEtat(EtatMoteur.INITIALISE);
  }

  public void mettreAJourJeu() {
    traiterCollision();
    tetromino.mettreAJourJeu();
    chercherLignePleine();
    if (tasPlein())
      moteur.terminerJeu();
  }

  public void chercherLignePleine() {
    for (int i = 0; i < ParamTetris.LIGNES; i++) {
      if (Stream.of(tas[i]).allMatch(elem -> elem != Tetromino.Tetrominoes.Vide)) {
        moteur.setScore(moteur.getScore() + 100);
        mettreAJourTas(i);

      }
    }
  }

  public void mettreAJourTas(int i) {
    for (int k = i; k > 0; k--) {
      for (int l = 0; l < ParamTetris.COLONNES; l++) {
        tas[k][l] = tas[k - l][l];
      }
    }
    changerNiveau();
  }

  private boolean tasPlein() {
    return !Stream.of(tas[l]).allMatch(elem -> elem == Tetromino.Tetrominoes.Vide);
  }

  boolean peutAllerGauche(int[][] cels) {
    boolean res = true;
    for (int i = 0; i < 4; i++) {
      if (cels[i][0] + 1 > ParamTetris.COLONNES - 1) {
        res = false;
        break;
      } else if (tas[cels[i][1]][cels[i][0] + 1] != Tetromino.Tetrominoes.Vide) {
        res = false;
        break;
      }
    }
    return res;
  }

  public boolean peutTourner(int[][] cels) {
    boolean res = true;
    for (int i = 0; i < 4; i++) {
      if (cels[i][0] > ParamTetris.COLONNES - 1 || cels[i][0] < 0 || cels[i][1] > ParamTetris.LIGNES - 1) {
        res = false;
        break;
      } else if (tas[cels[i][1]][cels[i][0]] != Tetromino.Tetrominoes.Vide) {
        res = false;
        break;
      }
    }
    return res;
  }

  public void traiterCollision() {
    // dernière rangée ou sur autre
    int[][] cds = tetromino.getCoords();
    int y = tetromino.getY();
    int x = tetromino.getX();
    for (int i = 0; i < 4; i++) {
      if ((y + cds[i][1] == ParamTetris.LIGNES - 1) || (tas[y + cds[i][1] + 1][x + cds][i][0] != Tetrominoes.Vide())) {
        stockerEtChanger();
        break;
      }
    }
  }

  private void stockerEtChanger() {
    int[][] coords = tetromino.getCoords(); // stocker dans tas
    int y = tetromino.getY();
    int x = tetromino.getX();
    for (int i = 0; i < 4; i++) {
      tas[y + coords[i][1]][x + coords[i][0]] = tetromino.getForme();

    }
    // creer nouveau
    tetromino = (Tetromino) fab.creerObjet(ParamTetris.TETROMINO);
    tetromino.donerFormeAuHasard();
    moteur.setScore(moteur.getScore() + 1);
  }

  public void changerNiveau() {
    moteur.setNiveau(moteur.getNiveau() + 1);
    frequenceMaj += 1;
  }

  public void dessinerTas(Graphics g) {
    int t = ParamTetris.TAILLE_CELLULE;
    for (int j = 0; i < ParamTetris.COLONNES; i++) {
      for (int j = 0; j < ParamTetris.LIGNES; j++) {
        if (tas[j][i] != Tetromino.Tetrominoes.Vide) {
          g.setColor(Tetromino.getCouleur(tas[j][i]));
          g.fillRect(i * t, t, t);
        }
      }
    }
  }

  public void dessine(Graphics g) {
    switch (moteur.getEtat()) {
      case EN_COURS:
      case ARRETE:
        tetromino.dessiner(g);
        dessinerTas(g);
        break;
      case GAMEOVER:
      case INITIALISE:
        break;
    }
  }

  public void toucheHaut() {
    tetromino.tourneHaut();
  }

  public void toucheBas() {
    tetromino.tourneBas();
  }

  public void toucheDroite() {
    tetromino.deplacerDroite();
  }

  public void toucheGauche() {
    tetromino.deplacerGauche();
  }

  public int getParamInt(int p) {
    int ret;
    switch (p) {
      case 1:
        ret = ParamTetris.LIGNES;

        break;
      case 2:
        ret = ParamTetris.COLONNES;
        break;
      case 3:
        ret = ParamTetris.TAILLE_CELLULE;
        break;
      default:

    }
    return ret;
  }

  public String getParamString(int p) {
    return new String();
  }

  public Tetromino getTetromino() {
    return tetromino;
  }

  public long getPeriodeMaj() {
    return 1000000000L / frequenceMaj;
  }

}
