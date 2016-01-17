import java.util.Scanner;
class Factorielle {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
    	/*
        char recommencer = 'o';
        do {
            int n = demanderNombre();
            System.out.println("M�thode it�rative :");
            System.out.println(n + "! = " + factorielleIterative(n));
            System.out.println("M�thode recursive :");
            System.out.println(n + "! = " + factorielleRecursive(n));
            System.out.println("Voulez-vous recommencer [o/n] ?");
            recommencer = scanner.next().charAt(0);
        } while (recommencer == 'o');
        */
    }

    /*
     * Demande un entier � l'utilisateur
     * @return L'entier entr� par l'utilisateur
     */
    static int demanderNombre() {
        int n;
        do {
            System.out.println("Donnez un nombre entier compris entre 0 et 12 (compris)\n");
            n = scanner.nextInt();
        } while ((n > 12) || (n < 0));
        return n;
    }

    /*
     * Calcul de la factorielle par la m�thode it�rative
     * @param nombre Le nombre dont on veut calculer la factorielle
     * @return nombre!
     
    static int factorielleIterative(int nombre) {
        int fact = 1;
        for (int i = 2; i <= nombre; i++) {
            fact *= i;
        }
        return fact;
    }*/

    /*
     * Calcul de la factorielle par la m�thode r�cursive
     * @param nombre Le nombre dont on veut calculer la factorielle
     * @return nombre!
     */
     static int factorielleRecursive(int nombre) {
        if (nombre == 0) {
            return 1;
        } else {
            return (nombre * factorielleRecursive(nombre - 1));
        }
    }
}
