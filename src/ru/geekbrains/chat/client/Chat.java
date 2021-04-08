package ru.geekbrains.chat.client;

import ru.geekbrains.chat.client.gui.ChatFrame;
import ru.geekbrains.chat.client.gui.api.Receiver;


public class Chat {
    private final ChatFrame frame;
    private final ChatComunication communication;

    public Chat(String host, int port) {
        communication = new ChatComunication(host, port);
//        frame = new ChatFrame(new Sender() {
//            @Override
//            public void send(String data) {
//                communication.transmit(data);
//            }
//        }, null);

        frame = new ChatFrame(data -> communication.transmit(data));

        new Thread(() -> {
            Receiver receiver = frame.getReceiver();
            while (true) {
                String msg = communication.receive();
                if (!msg.isBlank()) {
                    receiver.receive(msg);

                }
            }
        })
                .start();
    }

}
