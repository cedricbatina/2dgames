
import java.awt.Graphics;

public interface IGUI {
 void terminerGui();

 void setMeilleurScore(IMeilleurScore m);

 void setMoteur(IMoteur m);

 void resetPanneauControle();

 void repaintPanneauJeu();

 void focusPanneauJeu();

 void dessinGameover(Graphics g);

 public void setImageFondPanneauJeu(String s, Graphics g);

 boolean containsPanneauJeu(int x, int y);

 IMeilleurScore getMeilleurScore();

 IMoteur getMoteur();
}
