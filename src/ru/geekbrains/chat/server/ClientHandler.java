package ru.geekbrains.chat.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Optional;

public class ClientHandler {
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private ChatServer chatServer;
    private String name;

    public ClientHandler(Socket socket, ChatServer chatServer) {
        this.socket = socket;
        this.chatServer = chatServer;
        try {
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            throw new ChatServerException("Что-то пошло не так при попытке аутентификации...", e);

        }
        new Thread(() -> {
            doAuthentication(chatServer);
            listen();

        })
                .start();


    }

    public String getName() {
        return name;
    }

    private void listen() {
        receiveMessage();

    }

    private void doAuthentication(ChatServer chatServer) {
        sendMessage("Привет. Пожалуйста зарегистрируйтесь");
        while (true) {
            try {
                /**
                 * login pattern
                 * -auth li pi
                 */
                String message = in.readUTF();

                if (message.startsWith("-auth")) {
                    String[] credentialsStruct = message.split("\\s"); //разбиение строки по пробелам (\\s)
                    String login = credentialsStruct[1];
                    String password = credentialsStruct[2];

                    Optional<AuthenticationService.Entry> mayBeCredentials = chatServer.getAuthenticationService()
                            .findEntryByCredentials(login, password);
                    if (mayBeCredentials.isPresent()) {
                        AuthenticationService.Entry credentials = mayBeCredentials.get();
                        if (!chatServer.isLoggedIn(credentials.getName())) {
                            name = credentials.getName();
                            chatServer.broadcast(String.format("Пользователь [%s]  вошел в чат ", name));
                            chatServer.subscribe(this);
                            return;
                        } else {
                            sendMessage(String.format("Пользователь  с именем %s уже зарегистрирован", credentials.getName()));
                        }
                    } else {
                        sendMessage("Не корректные логин или пароль");
                    }

                } else {
                    sendMessage("Не корректные значения аутентификации, введите корректные значения..\n -auth your_login your_pass");
                }
            } catch (IOException e) {
//                throw new ChatServerException("Что-то пошло не так при попытке аутентификации...", e);
            }

        }
    }

    public void receiveMessage() { // получить сообщение
        /**код оставлен, чтобы его можно было оперативно вернуть, если ВСЁ СЛОМАЮ!!!!!*/
//        while (true) {
//            try {
//                String message = in.readUTF();
//                chatServer.broadcast(String.format("%s: %s,", name, message));
////                System.out.println(message);
//            } catch (IOException e) {
//                throw new ChatServerException("Что-то пошло не так при получении сообщения...", e);
//
//            }
//
//        }

        /** код написан в рамках 7 задания*/
        while (true) {

            try {
                String message = in.readUTF();
                if (message.startsWith("/w ")) {
                    String[] messageStructure = message.split("\\s"); // выделение ника из сообщения
                    String nick = messageStructure[1];
                    String privMes;
                    privMes = message.substring(4 + nick.length());

                    chatServer.sendMessageToClient(this, nick, privMes);

                } else {
                    chatServer.broadcast(String.format("%s: %s,", name, message));
                }

            } catch (IOException e) {
                throw new ChatServerException("Что-то пошло не так при получении сообщения...", e);

            }


        }

    }

    public void sendMessage(String message) { // передать сообщение

        /** Код оставлен, чтобы его можно было оперативно вернуть, если ВСЁ СЛОМАЮ!!!!! */
        try {
            out.writeUTF(message);
        } catch (IOException e) {
            throw new ChatServerException("Что-то пошло не так при отправке сообщения...", e);
        }

    }


}
