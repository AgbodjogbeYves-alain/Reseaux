import java.lang.*;
import java.io.*;
import java.net.*;
import java.util.Scanner;

class Client {
	
	
	private int result;
	private int localport;
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
		this.localport = localport;
		
		}

	public void setResult(int x) {
	// TODO Auto-generated method stub
		this.result = x;
	}

	public int getResult() {
		// TODO Auto-generated method stub
		return this.result;
	}

	public void clientRun() {
		// TODO Auto-generated method stub
			try {
				Socket clientSocket = new Socket(InetAddress.getLocalHost(), this.localport);
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