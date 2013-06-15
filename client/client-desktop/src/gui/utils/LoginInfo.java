package gui.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Contient les informations saisies dans une connection.
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 *
 */
public class LoginInfo {
   private String ip;
   private String port;
   private String username;
   private String password;
   
   
   /**
    * Construit les informations de connection.
    */
   public LoginInfo(String ip, String port, String username, String password) {
      this.ip = ip;
      this.port = port;
      this.username = username;
      this.password = password;
   }
   
   public boolean isValid() {
      // Fournie par http://www.mkyong.com/regular-expressions/how-to-validate-ip-address-with-regular-expression/
      String ipAdressePattern = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                                "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                                "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                                "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
      Pattern regIp = Pattern.compile(ipAdressePattern);
      Matcher match = regIp.matcher(ip);
      
      // On regarde si on peut parse le port.
      try {
         Integer.valueOf(port);
      } catch (NumberFormatException e) {
         return false;
      }      
      return match.find() && username.length() > 0 && password.length() > 0;
   }
   
   public String getServerAdress() {
      return ip;
   }
   
   public String getServerPort() {
      return port;
   }
   
   public String getLogin() {
      return username;
   }
   
   public String getPassword() {
      return password;
   }

}
