import java.io.IOException;
import java.util.Scanner;

public class Factorielle {
	
	/*
     * Calcul de la factorielle par la m�thode r�cursive
     * @param nombre Le nombre dont on veut calculer la factorielle
     * @return nombre!
     */
     static int factorielleRecursive(int N) {
        if (N == 0) {
            return 1;
        } else {
            return (N * factorielleRecursive(N - 1));
        }
    }
}
