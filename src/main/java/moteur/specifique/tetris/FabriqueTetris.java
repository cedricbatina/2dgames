package moteur.specifique.tetris;

import moteur.generique.IFabriqueObjets;
import moteur.generique.IMoteur;
import moteur.generique.ObjetJeu;

public class FabriqueTetris implements IFabriqueObjets {
 private IMoteur moteur;

 public ObjetJeu creerObjet(int type) {
  ObjetJeu result = null;
  if (type == ParamTetris.TETROMINO)
   result = new Tetronimo();
  result.setMoteurSpecifique(moteur.getMoteurSpecifique());
  return result;
 }

 public void setMoteur(IMoteur m) {
  moteur = m;
 }
}
