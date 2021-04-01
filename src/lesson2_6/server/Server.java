package lesson2_6.server;

import javax.imageio.IIOException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public Server() {
        try {
            ServerSocket serverSocket = new ServerSocket(8080);
            System.out.println("Сервер запущен...");
            System.out.println("Сервер ожидает подключения....");
            Socket accept = serverSocket.accept();
            System.out.println("Соединение установлено: " + accept);
            DataInputStream in = new DataInputStream(accept.getInputStream());
            DataOutputStream out = new DataOutputStream(accept.getOutputStream());
            Scanner serverscanner = new Scanner(System.in);


            new Thread(() -> {
                while (true) {
                    try {
                        out.writeUTF(serverscanner.nextLine());
                    } catch (IOException e) {
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
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
