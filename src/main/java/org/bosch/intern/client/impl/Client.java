package org.bosch.intern.client.impl;

import org.bosch.intern.client.Exception.ExceptionClient;

import java.io.*;
import java.net.Socket;

public class Client implements Closeable {
    private Socket socket;
    private BufferedReader bufferedReader;
    private PrintWriter printWriter;

    public void startClient(String address, int portNumber) throws RuntimeException {
        try {
            System.out.println("Starting Client");
            socket = new Socket(address, portNumber);
            printWriter = new PrintWriter(socket.getOutputStream());
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println("Server connected");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String readServerMessage() throws IOException {
        return bufferedReader.readLine();
    }

    public void sendToServerMessage(String inputCommand) {
        printWriter.println(inputCommand);
        printWriter.flush();
    }

    @Override
    public void close() {
        try {
            if (socket != null) {
                socket.close();
                System.out.println("Socket closed");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (printWriter != null) {
            printWriter.close();
            System.out.println("PrintWriter closed");
        }
        try {
            if (bufferedReader != null) {
                bufferedReader.close();
                System.out.println("BufferedReader closed");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
