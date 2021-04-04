package ru.geekbrains.chat;

import ru.geekbrains.chat.client.ChatClient;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClientOne {
    public static void main(String[] args) {
        new ChatClient();
    }

}
