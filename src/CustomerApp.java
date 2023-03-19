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
        Scanner sc = new Scanner(System.in);
        Socket socket = null;
        try {
            socket = new Socket(HOST, PORT_EMPRUNT);
            BufferedReader in = new BufferedReader (new InputStreamReader(socket.getInputStream ( )));
            PrintWriter out = new PrintWriter (socket.getOutputStream ( ), true);

            System.out.println(in.readLine()); // retrieves the message of the service which the customer is connected

            String numDocument = "";
            String serverMsg = in.readLine();
            do {
                System.out.println(serverMsg);
                while (!sc.hasNextInt()) {
                    System.out.println("You need to enter a digit !");
                    numDocument = sc.nextLine();
                }

                numDocument = sc.nextLine();
                out.println(numDocument);
                serverMsg = in.readLine();
            } while (!Objects.equals(serverMsg, "ok"));

            String numAbonne = "";
            serverMsg = in.readLine();
            if (!Objects.equals(serverMsg, "ok")){
                do {
                    System.out.println(serverMsg);
                    while (!sc.hasNextInt()) {
                        System.out.println("You need to enter en digit !");
                        numAbonne = sc.nextLine();
                    }

                    numAbonne = sc.nextLine();
                    out.println(Integer.parseInt(numAbonne));
                    serverMsg = in.readLine();
                } while (!Objects.equals(serverMsg, "ok"));
            }
            System.out.println(in.readLine().replace("##", "\n")); // finale msg of the server

            socket.close();
        }
        catch (IOException e) {
            System.err.println("Problem in customer app");
        }
    }
}