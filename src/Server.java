
/******************************* CONVENTIONS: *********************************
 * 																			  *
 *  Placer un // AJOUT - NOM à coté des lignes ajoutées par la personne NOM   *
 *  Placer un // MODIF - NOM à coté des lignes modifiées par la personne NOM  *
 *  																		  *
 ******************************************************************************/

import java.lang.*;
import java.io.*;
import java.net.*;


/* Important: pour lancer le programme il faut:
 * 1) Se mettre sur Serveur.java
 * 2) Lancer le programme avec le bouton vert
 * 3) Aller sur Client.java
 * 4) Lancer le programme avec le bouton vert
 * 5) Entrer le nombre à facto
 */


class Server {
   public static void main(String args[]) {
      String data = "Connexion établie!";
      
      try {
         ServerSocket srvr = new ServerSocket(5001);
         Socket skt = srvr.accept();
         System.out.print("Server has connected!\n");
         PrintWriter out = new PrintWriter(skt.getOutputStream(), true);
         System.out.print("Sending string: '" + data + "'\n");
         System.out.print("Sending string: '" + data + "'\n");
         if (skt.getInputStream().read()>0)
         {
        	 Client cli = new Client();
        	 
         }
         out.print(data);
         out.close();
         skt.close();
         srvr.close();
         
      }
      catch(Exception e) {
         System.out.print("Whoops! It didn't work!\n");
      }
   }
}
