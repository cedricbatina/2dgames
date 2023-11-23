package jeu.persist;

import java.util.ArrayList;

public interface IPersist {
 static final String PATH_SCORES = "Highscores/";

 ArrayList<Score> lireListe(String nom);

 void ecrireListe(String nom, ArrayList<Score> liste);
}
