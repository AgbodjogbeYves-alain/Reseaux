import java.io.IOException;
import java.util.Scanner;

public class Recurence {
	CLASSE A SUPRIMMER
	
	public static void main(int N) throws IOException
	/* DOC: calcul le factoriel de N;
	 * DONNEE: entier N dont on veut calculer la factorielle;
	 * RESULTAT: entier factN = N!;
	 * PRE-CONDITION: 0<=N;
	 * POST-CONDITION: 0<=factN
	 */
	{	
		if N=0{
			factN = 1;
			return factN
		}
		else{
			factN = N*Recurence(N-1);
		}
	}
}
