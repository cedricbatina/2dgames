package jeu.persist;

import java.util.ArrayList;
import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

@SuppressWarnings("unchecked")
public class Serialisation implements IPersist {
 public ArrayList<Score> lireListe(String nom) {
  File f = new File(PATH_SCORES + nom + ".sco");
  ArrayList<Score> res = new ArrayList<Score>();
  if (f.exists()) {
   try {
    FileInputStream fis = new FileInputStream(PATH_SCORES + nom + ".sco");
    res = (ArrayList<Score>) ois.readObject();
    ois.close();

   } catch (Exception e) {
    System.out.println("read error : " + e.getMessage());
   }
  }
 }
}
