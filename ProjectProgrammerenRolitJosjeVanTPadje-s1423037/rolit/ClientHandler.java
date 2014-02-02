package rolit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import protocollen.ClientProtocol;
import protocollen.ServerProtocol;

public class ClientHandler extends Thread {
	
	private Socket clientSocket;
	private Server server;
	private PrintWriter out;
	private BufferedReader in;
	private String name;
	private String outputLine;
	private String opties;
	private String versie;
	@SuppressWarnings("unused")
	private int state = 0;
	public ClientHandler(Server server, Socket clientSocket){
		this.server = server;
		this.clientSocket = clientSocket;
		
	}

	public void run() {
		int count = 0;
		String inputLine;
		try {
			

			out = new PrintWriter(clientSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); 
			
		    out.println(outputLine);
			while ((inputLine = in.readLine()) != null) {
				
				if(inputLine.startsWith("hello")){
					String[] helloList = inputLine.split(" ");
					name = helloList[1];
					server.clientNames.add(name);
					opties = helloList[2];
					versie = helloList[3];
				}
				
				outputLine = processInput(inputLine);
				if(outputLine != null){
			        out.println(outputLine);
					if (inputLine.equals("Bye.")){
				        break;
				    }
				}
				
				if(count == 0){
					server.broadcast("Online " + getClientName() + " true");
					count++;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			server.broadcast("online " + getClientName() + " false");
			server.clientHandlers.remove(this);
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
	
	public String processInput(String inputLine) {
		String theOutput = null;
		
		if (inputLine.startsWith(ClientProtocol.HANDSHAKE)){
			theOutput = ServerProtocol.HANDSHAKE + " " + opties + " " + versie;
			state = 1;
		}
		
		else if (inputLine.startsWith("message")){
			theOutput = inputLine;
			server.broadcast(theOutput);
		}
		
		return theOutput;
	}
}







