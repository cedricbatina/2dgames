package jeu.persist;

import java.io.Serializable;

public class Score implements Comparable<Object>, Serializable {
 private static final long SerialVersionUID = -665460468231969484L;
 private String nomJoueur;
 private int score;

 public Score(String n, int s) {
  nomJoueur = n;
  score = s;
 }

 public int compareTo(Object o) {
  Score = o;
  if (this.score < s.getScore()) return -1;
  else if (this.score == s.getScore()) return 0;
  else return 1;
 }

 public String getNom() {
  return nom;
 }

 public int getScore() {
  return score;
 }

 public String toString() {
  return nomJoueur + " " + score;
 }
}
