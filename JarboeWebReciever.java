package jarboe;

import java.io.*;
import java.net.*;

public class JarboeWebReciever {
	private String history = "";
	public String getHistory() {return history;}
	private ServerSocket serverSocket = null;
    public boolean isActive = false;
    public void startServer(int port) {
        isActive=true;
        
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("JarboeWebSocket waiting on " + port);
            
            while (true) {
                Socket clientSocket = serverSocket.accept();
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                
                String line; String finalmsg = "";
                while ((line = in.readLine()) != null) {
                    System.out.println(line);
                    finalmsg+=line;
                    if (line.length() == 0) break; //check for end
                }
                in.close();
                
                history += finalmsg + "\n";
                clientSocket.close();
            }
        } catch (Exception e) {
        	isActive=false;
            e.printStackTrace();
            if (serverSocket != null)
            	try{
            		serverSocket.close();  System.err.print("CLOSING SERVER SOCKET " + port);
            	} catch (Exception f) {f.printStackTrace(); System.err.print("FAILED TO CLOSE SOCKET ON RECIEVER " + port);}
        }
    }
    
    public void stopServer()
    {
        try {
			serverSocket.close();
		} catch (IOException e) {
			System.err.print("FAILED TO CLOSE SOCKET");
			e.printStackTrace();
		}
        
        isActive=false;
    }
}