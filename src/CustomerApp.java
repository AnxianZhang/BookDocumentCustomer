import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Objects;
import java.util.Scanner;

public class CustomerApp {
    private static final String HOST;

    static {
        HOST = "localhost";
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        Socket socket = null;
        try {
            int port = Integer.parseInt(args[0]);
            socket = new Socket(HOST, port);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            System.out.println(in.readLine()); // retrieves the message of the service which the customer is connected

            String num = "";
            String serverMsg = in.readLine();
            boolean start = true;

            do {
                System.out.print(serverMsg.replace("##", "\n").replace("||", " "));

                while (!sc.hasNextInt()) {
                    num = sc.nextLine();
                    if (Objects.equals(num, "quit")){
                        out.println("quit");
                        start = false;
                        break;
                    }
                    System.out.print("You need to enter a digit: ");
                }
                if (start){
                    num = sc.nextLine();
                    out.println(num);
                    serverMsg = in.readLine();
                    if (Objects.equals(serverMsg, "ok")){
                        System.out.println(in.readLine().replace("##", "\n").replace("||", " "));
                        serverMsg = in.readLine();
                    }
                }
            } while (start);
            System.out.println("\n++++++++++ You have left the service ++++++++++");
        } catch (IOException e) {
            System.err.println("The number of server is not correct.\nOr you have been disconnected.");
        }finally {
            if (socket != null)
                socket.close();
        }
    }
}