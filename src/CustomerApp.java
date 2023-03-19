import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Objects;
import java.util.Scanner;

public class CustomerApp {
    private static final int PORT_RESERVATION;
    private static final int PORT_EMPRUNT;
    private static final int PORT_RETOURS;
    private static final String HOST;

    static {
        PORT_RESERVATION = 3000;
        PORT_EMPRUNT = 4000;
        PORT_RETOURS = 5000;
        HOST = "localhost";
    }

    public static void main(String[] args) throws IOException {
//        BufferedReader clavier = new BufferedReader(new InputStreamReader(System.in));
        Scanner sc = new Scanner(System.in);
        Socket socket = null;
        try {
            socket = new Socket(HOST, PORT_EMPRUNT);
            BufferedReader in = new BufferedReader (new InputStreamReader(socket.getInputStream ( )));
            PrintWriter out = new PrintWriter (socket.getOutputStream ( ), true);

            System.out.println(in.readLine()); // retrieves the message of the service

            // demande du mun doc
//            System.out.println();

            String numDocument = "";
            String serverMsg = in.readLine();
            do {
                System.out.println(serverMsg);
                while (!sc.hasNextInt()) {
                    System.out.println("You need to enter a digit !");
                    numDocument = sc.nextLine();
                }
                numDocument = sc.nextLine();
                System.out.println("ton num est: " + numDocument); // ok
                out.println(numDocument);
                serverMsg = in.readLine();
            } while (!Objects.equals(serverMsg, "ok"));

            System.out.println("num doc ok");
            // fin de mande du num doc ========= OK ==========


            String numAbonne = "";
            serverMsg = in.readLine();
            do {
                System.out.println(serverMsg);
                while (!sc.hasNextInt()) {
                    System.out.println("You need to enter en digit !");
                    numAbonne = sc.nextLine();
                }
                numAbonne = sc.nextLine();

                out.println(Integer.parseInt(numAbonne));
                System.out.println("le lci est: " + numAbonne);
                serverMsg = in.readLine();
            } while (!Objects.equals(serverMsg, "ok"));
            System.out.println("love xingtong");
            System.out.println(in.readLine());

//            line = sin.readLine(); // 1ère question
//            System.out.println(line);
//            // prompt d'invite à la saisie
//            System.out.print("->");
//            line = clavier.readLine();
//            sout.println(line);
//            line = sin.readLine(); // 2ème question//ya pbm pour retourService qui a 1 seul qst
//            System.out.println(line);
//            // prompt d'invite à la saisie
//            System.out.print("->");
//            line = clavier.readLine();
//            sout.println(line);
//
//            System.out.println(sin.readLine()); // réponse finale

            socket.close();
        }
        catch (IOException e) { System.err.println("Fin du service"); }
        // Refermer dans tous les cas la socket
        try { if (socket != null) socket.close(); }
        catch (IOException e2) { ; }
    }

    private static boolean isNumeric(String string) {
        try {
            Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}