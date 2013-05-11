/* ============================================================================
 * Nom du fichier   : Login.java
 * ============================================================================
 * Date de création : 8 mai 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Grégoire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */
package gui.controller;

import gui.view.JLogin;

import java.awt.Component;

/**
 * TODO
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 *
 */
public class Login extends Controller {
   
   JLogin view;

   @Override
   protected void initComponents() {
      view = new JLogin();
   }

   @Override
   protected void initListeners() {
   }

   @Override
   public Component getGraphicalComponent() {
      return view;
   }

}
