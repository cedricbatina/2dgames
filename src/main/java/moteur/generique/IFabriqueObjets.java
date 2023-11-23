package moteur.generique;

public interface IFabriqueObjets {
 ObjetJeu creerObjet(int type);

 void setMoteur(IMoteur m);

}
