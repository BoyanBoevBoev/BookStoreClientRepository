package org.bosch.intern.client.impl;

import java.io.IOException;
import java.util.Scanner;

public class Core {
    private final Client client;

    public Core(Client client) {
        this.client = client;
    }

    public void run() throws IOException {
        client.startClient("localHost",6999);
        String command;
        do {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please enter command");
            command = scanner.nextLine();
            client.sendToServerMessage(command);
            System.out.println("Please enter entity");
            String entityOption = scanner.nextLine();
            client.sendToServerMessage(entityOption);
            if (command.equalsIgnoreCase("read") || command.equalsIgnoreCase("add")){
                String parameter;
                if (command.equalsIgnoreCase("read")){
                    System.out.println("Please enter id");
                }else {
                    System.out.println("Please enter parameters");
                }
                parameter = scanner.nextLine();
                client.sendToServerMessage(parameter);
            }
            int resultAmount = Integer.parseInt(client.readServerMessage());
            System.out.printf("Amount of results: %d%n",resultAmount);
            if (resultAmount > 0){
                for (int i = 1; i <= resultAmount; i++) {
                    String[] currentResultLine = client.readServerMessage().split(",");
                    for (String s : currentResultLine) {
                        System.out.print(s + " ");
                    }
                    System.out.println();
                }
            }else {
                System.out.println(client.readServerMessage());
            }
        }
        while (!command.equalsIgnoreCase("exit"));
        System.out.println("End connection");
    }


}
