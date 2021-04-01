package lesson2_6.client;

import javax.imageio.IIOException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public Client() {
        try {
            Socket socket = new Socket("127.0.0.1", 8080);
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            Scanner scanner = new Scanner(System.in);


            new Thread(() -> {
                while (true) {
                    try {
                        out.writeUTF(scanner.nextLine());
                    } catch (IOException e) {
                        System.out.println("Сервер отключился...");
                        System.out.println("Соединение прервано...");
                        break;
                    }
                }
            })
                    .start();

            while (true) {
                try {

                    String message = in.readUTF();
                    System.out.println(message);

                } catch (IOException e) {
                    System.out.println("Канал исходящих сообщений закрыт.");
                    System.out.println("Нажмите ввод");
                    break;

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
