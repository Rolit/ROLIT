package rolit;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
	
	List<ClientHandler> clientHandlers = new ArrayList<ClientHandler>();
	List<String> clientNames = new ArrayList<String>();
	
	public void startListening(int portNumber) {
		ServerSocket serverSocket;
		try {
			//create new server
			serverSocket = new ServerSocket(portNumber);
			while (true) {
				//accept a connection;
				Socket clientSocket = serverSocket.accept();
				//create a thread to deal with the client;
				
				ClientHandler clientHandler = new ClientHandler(this, clientSocket);
				clientHandler.start();
				clientHandlers.add(clientHandler);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public static void main(String[] args) {
		int portNumber = Integer.parseInt(args[0]);
		new Server().startListening(portNumber);
	}

	public void broadcast(String inputLine) {
		for(ClientHandler clientHandler: clientHandlers){
			clientHandler.send(inputLine);
		}
	}
}
