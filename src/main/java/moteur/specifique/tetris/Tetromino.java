package moteur.specifique.tetris;

import moteur.generique.ObjetJeu;
import java.awt.Graphics;
import java.awt.Color;

public class Tetromino extends ObjetJeu {
  public static enum Tetrominoes {
    Vide, z, s, Ligne, T, Carre, L, LRetourne
  }

  private Tetrominoes forme;
  private int coords[][];

  public Tetromino() {
    coords = new int[4][2];
    donnerFormeAuHasard();
    x = (ParamTetris.COLONNES / 2) - 1;
    y = 0;
  }

  public void donnerFormeAuHasard() {
    int x = Math.abs(rand.nextInt() % 7 + 1);
    Tetrominoes[] valeurs = Tetrominoes.values();
    donnerForme(valeurs[x]);
  }

  public void donnerForme(Tetrominoes s) {
    int[][][] coordsTable = new int[][][] {
        { { 0, 0 }, { 0, 0 }, { 0, 0 }, { 0, 0 }, }, // coords [i][0] <=>x
        { { 0, -1 }, { 0, 0 }, { -1, 0 }, { -1, 1 } }, // coords [i][1]
        { { 0, -1 }, { 0, 0 }, { 1, 0 }, { 1, 1 } },
        { { 0, -1 }, { 0, 0 }, { 0, 1 }, { 0, 2 } },
        { { -1, 0 }, { 0, 0 }, { 1, 0 }, { 0, 1 } },
        { { 0, 0 }, { 1, 0 }, { 0, 1 }, { 1, 1 } },
        { { 1, 1 }, { 0, -1 }, { 0, 0 }, { 0, 1 } }
    };
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 2; j++) {
        coords[i][j] = coordsTable[s.ordinal()[i][j]];
      }
    }
    forme = s;
  }

  public void generer() {
  }

  public void mettreAJour() {
    y++;
  }

  public void deplacerGauche() {
    if (((MoteurTetris) mots).peutAllerGauche(getCells(coords)))
      x--;
  }

  private int[][] getCells(int[][] c) {
    // coord. relatives en absolues
    int[][] cells = new int[4][2];
    for (int i = 0; i < 4; i++) {
      cells[i][0] = x + c[i][1];
    }
    return cells;
  }

  public void deplacerDroite() {
    if (((MoteurTetris).mots).peutAllerDroite(getCells(coords)))
      x++;
  }

  public void tournerGauche() {
    if (forme == Tetrominoes.Carre)
      return;
    int old;
    int[][] ncoords = new int[4][2];
    for (int i = 0; i < 4; i++) {
      // calcul nouvelles coord.pour test
      old = coords[i][0];
      ncoords[i][0] = coords[i][1];
      ncoords[i][1] = -old;
    }
    if (((MoteurTetris) mots).peutTourner(getCells(ncoords))) {
      for (int i = 0; i < 4; i++) {
        for (int j = 0; j < 2; j++) {
          coords[i][j] = ncoords[i][j];
        }
      }
    }

  }

  public void tournerDroite() {
    if (forme == Tetrominoes.Carre)
      return;
    int old;
    int[][] ncoords = new int[4][2];
    for (int i = 0; i < 4; i++) {
      // calcul nouvelles coord. pour test
      old = coords[i][0];
      ncoords[i][0] = -coords[i][1];
      ncoords[i][1] = old;
    }
    if (((MoteurTetris) mots).peutTourner(getCells(ncoords))) {
      for (int i = 0; i < 4; i++) {
        for (int j = 0; j < 2; j++) {
          coords[i][j] = ncoords[i][j];
        }
      }
    }
  }

  public void dessiner(Graphics g) {
    int t = ParamTetris.TAILLE_CELLULE;
    if (forme != Tetrominoes.Vide) {
      for (int i = 0; i < 4; i++) {
        dessineCarre(g, (x + coords[i][0]) * t, (y + coords[i][1]) * t);
      }
    }

  }

  public void dessineCarre(Graphics g, int i, int j) {
    int t = ParamTetris.TAILLE_CELLULE;
    g.setColor(Tetromino.getCouleur(getForme()));
    g.fillRect(i, j, t, t);
  }

  public static Color getCouleur(Tetrominoes forme) {
    Color couleurs[] = {
        new Color(0, 0, 0), new Color(204, 102, 120), new Color(102, 102, 204), new Color(204, 204, 102),
        new Color(204, 102, 204), new Color(102, 204, 204), new Color(218, 170, 0)

    };

    return couleurs[forme.ordinal()];
  }

  public Tetrominoes getForme() {
    return forme;
  }

  public int[][] getCoords() {
    return getCoords();
  }

  /*
   * @Override
   * public void metteAJour() {
   * // TODO Auto-generated method stub
   * throw new UnsupportedOperationException("Unimplemented method 'metteAJour'");
   * }
   */

}
