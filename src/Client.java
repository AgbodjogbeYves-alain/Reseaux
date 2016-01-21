import java.lang.*;
import java.io.*;
import java.net.*;
import java.util.Scanner;

class Client {
	
	
	private int result;
	private int localport = 50000;
	@SuppressWarnings("unused")
	private int compteur;
	/**
	 * Constructeur du client.
	 * 
	 * @param i
	 *            l'increment, pour savoir ou on en est et de quel nombre on
	 *            veut la factorielle.
	 * @param port
	 *            le port ou on veut envoyer les informations.
	 */
   
	public Client(int x, int localPort) {
		this.compteur = x;
		this.result = 1;
		this.localport = localPort;
		
		}

	public void setResult(int x) {
	// TODO Auto-generated method stub
		this.result = x;
	}

	public int getResult() {
		// TODO Auto-generated method stub
		return this.result;
	}

	public static void main(String[] args) throws IOException {// On recupere les arguments de la
		// console pour creer le client, on
		// le lance puis on affiche le
		// resultat.

		Client client = new Client(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
		client.clientRun();
		System.out.println(client.getResult());
	}
	public void clientRun() {
		// TODO Auto-generated method stub
			try {
				 InetAddress address = InetAddress.getLocalHost();
				Socket clientSocket = new Socket(address, this.localport);
				PrintStream output = new PrintStream(clientSocket.getOutputStream());
				String text = Integer.toString(this.result);
				Scanner sc = new Scanner(clientSocket.getInputStream());
				PrintWriter printWrite= new PrintWriter(output);
				printWrite.println(text);
				printWrite.flush();// Nécessaire pour le bon fonctionnement.
				if (sc.hasNext()) {
					String texte2 = sc.nextLine();
					this.result = Integer.parseInt(texte2);
				}
				clientSocket.close();
				sc.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
   	}
//}