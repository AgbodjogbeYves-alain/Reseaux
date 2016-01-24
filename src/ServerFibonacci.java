import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Le serveur va servir a creerr les clients de calcul et renvoyer le resultat au client qui a demandé le calcul
 * Une des consigne etaient de creer 2 serveurs
 */

public class ServerFibonacci {
	private ServerSocket serverSocket1;
	private ServerSocket serverSocket2;

	/**
	 * Constructeur de la classe serveurFibonacci
	 * 
	 * @param serverSocket
	 *   
	 */
	ServerFibonacci(ServerSocket serverSocket) {
		this.serverSocket1 = serverSocket;
		this.serverSocket2 = serverSocket;
	}

	
	public class ClientThread extends Thread {
		private Socket socket;
		private InputStream inputStream;
		private OutputStream outputStream;
		private int[] cache;

		/**
		 * Constructeur de la classe clientThread
		 * 
		 * @param socket
		 *            le socket du serveur, recupere pour aller le retourner.
		 * @param cache1
		 *            Le tableau qui contient les valeurs deja calculée et qui contiendras les futures valeurs
		 */
		ClientThread(Socket socket, int[] cache1) {
			try {
				this.socket = socket;
				outputStream = socket.getOutputStream();
				inputStream = socket.getInputStream();
				cache = cache1;
			} catch (Exception e) {
			}
		}

		/**
		 * Dans le run on va verifier si le resultat est dans le cache, si le chiffre entré est superieur a 0 
		 * Si il n'est pas dans le cache on creer 2 nouveaux clients pour le calcul recursif.
		 * Si il est dans le cache on renvoie la valeur indexée a l'indice [compteur].
		 */
		@Override
		public void run() {
			Scanner sc = new Scanner(inputStream);
			String text = "";
			if (sc.hasNext()) {
				text = sc.nextLine(); 
			}
			int compteur = Integer.parseInt(text);
			PrintWriter printWrite = new PrintWriter(outputStream);
			if (compteur>=0)
			{
				if (compteur == 0 || compteur == 1) { 
					printWrite.println(compteur);
				} else {
					if (cache[compteur] == 0) {
						ClientFibonacci client1 = new ClientFibonacci(compteur - 1, serverSocket1.getLocalPort());
						ClientFibonacci client2 = new ClientFibonacci(compteur - 2, serverSocket2.getLocalPort());
						client1.clientRun();
						client2.clientRun();
						int result1 = client1.getResult();
						int result2 = client2.getResult();
						client1.setResult(result1);
						client2.setResult(result2);
						cache[compteur - 1] = result1;
						cache[compteur - 2] = result2;
					
						printWrite.println(result1 + result2); // On additionne les 2 resultats
					
					} else {
						printWrite.println(cache[compteur]);
					}
				}
				}
				else{
					printWrite.println("Entier negatif : entrez un entier valide");
				}

				
				printWrite.flush();// Sert a vider
				try {
					this.socket.close();
					sc.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}

	// Le port devra etre entré en parametre dans la console
	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(Integer.parseInt(args[0]));
			ServerFibonacci server = new ServerFibonacci(serverSocket);
			int[] cache = new int[99999999];
			while (true) {
				Socket socketClient = serverSocket.accept();
				ClientThread clientThread = server.new ClientThread(socketClient, cache);
				clientThread.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}