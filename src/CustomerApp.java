import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class CustomerApp {
    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        Socket clientSocket = new Socket("localhost", 1234);
        InputStreamReader isReader = new InputStreamReader(clientSocket.getInputStream());
        BufferedReader reader = new BufferedReader(isReader);
        PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
        Scanner scanner = new Scanner(System.in);
        while(true)
        {
            String send = scanner.nextLine();
            writer.println(send);
            System.out.println(reader.readLine());
//			String echo = reader.readLine();
//			System.out.println("Server echo: " + echo);
            if(send.equals("end")) break;
        }
        System.out.println("Client socket colsed !");
        clientSocket.close();
    }
}