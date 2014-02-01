package rolit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import protocollen.ClientProtocol;
import protocollen.ServerProtocol;
import protocollen.StandardProtocol;

public class ClientHandler extends Thread {
	
	private Socket clientSocket;
	private Server server;
	private PrintWriter out;
	private BufferedReader in;
	private String name;
	private String outputLine;
	private String opties;
	private String versie;
	public ClientHandler(Server server, Socket clientSocket){
		this.server = server;
		this.clientSocket = clientSocket;
		
	}

	public void run() {
		String inputLine;
		try {
			out = new PrintWriter(clientSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); 
			
			StandardProtocol standardProtocol = new StandardProtocol();
			outputLine = standardProtocol.processInput(null);
		    out.println(outputLine);
			while ((inputLine = in.readLine()) != null) {
				
				outputLine = server.processInput(inputLine);
		        out.println(outputLine);
		        System.out.println("Client: " + outputLine);
				if (inputLine.equals("Bye.")){
			        break;
			    }
				String opties;
				String versie;
				
				if(inputLine.startsWith("hello")){
					String[] helloList = inputLine.split(" ");
					name = helloList[1];
					server.clientNames.add(name);
					opties = helloList[2];
					versie = helloList[3];
				}
				else if (inputLine.startsWith("message")){
					// lieve, lieve server, broadcast 'inputLine' alsjeblieft!
					this.server.broadcast(inputLine);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void send(String inputLine) {
		try {
			out = new PrintWriter(clientSocket.getOutputStream(), true);
			out.println(inputLine);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getClientName(){
		return name;
	}
}
