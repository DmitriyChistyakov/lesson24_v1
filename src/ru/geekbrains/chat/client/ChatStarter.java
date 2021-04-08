package ru.geekbrains.chat.client;

import ru.geekbrains.chat.client.gui.ChatFrame;

public class ChatStarter {
    public static void run(){
        new Chat("localhost", 8080);

    }
    public static void run(String host, int port){
        new Chat(host, port);
    }


}
