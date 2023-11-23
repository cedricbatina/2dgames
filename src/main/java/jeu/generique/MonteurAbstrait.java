package jeu.generique;

public abstract class MonteurAbstrait {
 public void construire() {
  construireGui();
  ajouterSon();
  ajouterPersistance();
  terminerConstruction();
  construireMoteur();
 }

 public abstract void construireGui();

 public abstract void ajouterSon();

 public abstract void construireMoteur();

 public abstract void terminerConstruction();

 public abstract void ajouterPersistance();
}
