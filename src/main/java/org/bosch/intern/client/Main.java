package org.bosch.intern.client;

import org.bosch.intern.client.Exception.ExceptionClient;
import org.bosch.intern.client.impl.Client;
import org.bosch.intern.client.impl.Core;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Client client = null;
        try {
            client = new Client();
            Core core = new Core(client);
            core.run();
        } catch (IOException e) {
            throw new ExceptionClient("Client Error");
        } finally {
            if (client != null) {
                client.close();
            }
        }
    }
}

