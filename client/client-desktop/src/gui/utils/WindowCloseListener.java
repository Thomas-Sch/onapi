package gui.utils;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * Définit un écouteur de l'action de fermeture d'une fenêtre. Il s'agit d'un
 * masque ne définissant que le comportement de fermeture.
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 *
 */
public abstract class WindowCloseListener implements WindowListener {

   @Override
   public final void windowActivated(WindowEvent arg0) { }

   @Override
   public final void windowClosed(WindowEvent arg0) { }

   @Override
   public final void windowDeactivated(WindowEvent arg0) { }

   @Override
   public final void windowDeiconified(WindowEvent arg0) { }

   @Override
   public final void windowIconified(WindowEvent arg0) { }

   @Override
   public void windowOpened(WindowEvent arg0) { }

}
