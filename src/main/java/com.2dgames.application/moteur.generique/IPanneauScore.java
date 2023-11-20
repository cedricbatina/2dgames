package com.2dgames.application.moteur.generique;

import javax.swing.JPanel;
import java.beans.PropertyChangeListener;

public interface IPanneauScore extends PropertyChangeListener {
 JPanel getPanneau();

}
