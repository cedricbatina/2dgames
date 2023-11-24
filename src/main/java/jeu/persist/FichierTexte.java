package jeu.persist;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferdWriter;
import java.io.FileWriter;

public class FichierTexte implements IPersist {
 public ArrayList<Score> lireListe(String nom) {
  File f = new File(PATH_SCORES + nom + ".sco.txt");
  ArrayList<Score> res = new ArrayList<Score>();
  if (f.exists()) {
   try {
    String ligne = null;
    String[] mots = new String[2];
    FileReader fr = new FileReader(PATH_SCORES + nom + "sco.txt");
    BufferedReader br = new BufferedReader(fr);
    while ((ligne = br.readLine()) != null) {
     mots = ligne.split("\t");
     s = new Score(mots[0], Integer.parseInt(mots[1]));
     res.add(s);
    }
    br.close();
   } catch (Exception e) {
    System.out.println("write error : " + e);
   }
  }
 }
}
