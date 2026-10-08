package jarboe;

import java.io.*;
import java.net.*;

public class JarboeWebSender {
    public void sendMessage(String message, String ip, String port) {
        int portNum = Integer.parseInt(port);
        Socket socket = null;
        
        try {
            socket = new Socket(ip, portNum);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.print(message);
            out.flush();
            
            System.out.println("JarboeWebSender sent message successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (socket != null) socket.close(); } catch (IOException e) {System.err.println("FAILED TO CLOSE SOCKET ON SENDER " + ip +":"+ port);}
        }
    }
    
    public void sendHTTPMessage(String message, String ip, String port) {
    	String http = ("POST / HTTP/1.0\r\n") +
                ("Host: " + ip + "\r\n") +
                ("Content-Length: " + message.length() + "\r\n") +
                ("Content-Type: text/plain\r\n") +
                ("\r\n");
        sendMessage(http+message, ip, port);
    }
}