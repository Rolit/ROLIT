package rolit;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Start implements ActionListener {
	
	private JRadioButton humanButton;
	private JRadioButton computerButton;
	public JTextField nameField;
	private JComboBox<String> strategyChooser;
	private JButton startButton;
	private JComboBox<String> numberChooser;
	private JFrame startFrame;
	private JButton okButton;
	private JFrame errorFrame;
	private Color kleur;
	private Socket socket;
	private JTextField tfPort;
	private JTextField tfAddress;
	private JPanel card1;
	private JPanel card2;
	private JPanel switchPanel;
	private JFrame errorFrameStart;
	private JButton okStartErrorButton;
	Game game = new Game();
	
	// -- Constructors -----------------------------------------------
    
	public Start(){
		startFrame();
	}
	
	public void startFrame(){
		startFrame = new JFrame();
		startFrame.setLayout(new BorderLayout());
		startFrame.add(numberPlayers(), BorderLayout.NORTH);
		startFrame.add(startPanel(), BorderLayout.SOUTH);
		startFrame.add(ipAdresServerPoortPanel(), BorderLayout.WEST);
		startFrame.add(humanComputerPlayerPanel(), BorderLayout.CENTER);
		startFrame.add(switchingPanel(), BorderLayout.EAST);
		startFrame.setSize(600,220);
		startFrame.setVisible(true);
	}

	public JPanel strategyPanel(){
		strategyChooser = new JComboBox<String>();
		strategyChooser.addItem("Naive");
		strategyChooser.addActionListener(this);
		JPanel strategyPanel = new JPanel();
		JPanel innerPanel = new JPanel();
		
		innerPanel.add(strategyChooser);
		innerPanel.setBorder(BorderFactory.createEmptyBorder(0,55,0,55));
		strategyPanel.add(innerPanel);
		kleur = Color.black;
		strategyPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(kleur), "Strategy"));
		return strategyPanel;
	}
	
	public JPanel namePanel(){
		JLabel namePrompt = new JLabel("Name: ");
		nameField = new JTextField(10);
		nameField.addActionListener(this);
		JPanel innerPanel = new JPanel();
		innerPanel.add(namePrompt);
		innerPanel.add(nameField);
		innerPanel.setBorder(BorderFactory.createRaisedBevelBorder());
		JPanel namePanel = new JPanel();
		namePanel.add(innerPanel);
		namePanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		return namePanel;
	}
	
	private JPanel switchingPanel(){
		switchPanel = new JPanel(new CardLayout());
		card2 = strategyPanel();
		card1 = namePanel();
		switchPanel.add(card1, "NamePanel");
		switchPanel.add(card2, "StrategyPanel");
		return switchPanel;
	}
	
	public JPanel humanComputerPlayerPanel(){
		JPanel humanComputerPlayerPanel = new JPanel();
		humanComputerPlayerPanel.setLayout(new GridLayout(2,1));
		
		humanButton = new JRadioButton("Human player");
		humanButton.setActionCommand("human");
		humanButton.setSelected(true);
		humanButton.addActionListener(this);
		
		computerButton = new JRadioButton("Computer player");
		computerButton.setActionCommand("computer");
		computerButton.addActionListener(this);
		
		ButtonGroup playerGroup = new ButtonGroup();
		playerGroup.add(humanButton);
		playerGroup.add(computerButton);
		
		humanComputerPlayerPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		
		humanComputerPlayerPanel.add(humanButton);
		humanComputerPlayerPanel.add(computerButton);
		return humanComputerPlayerPanel;
	}
	
	public JPanel ipAdresServerPoortPanel(){
			JPanel pp = new JPanel(new GridLayout(2, 2));

			JLabel lbAddress = new JLabel("Address: ");
			tfAddress = new JTextField("localhost", 9);
			tfAddress.setEditable(true);

			JLabel lbPort = new JLabel("Port:");
			tfPort = new JTextField("5555", 5);

			pp.add(lbAddress);
			pp.add(tfAddress);
			pp.add(lbPort);
			pp.add(tfPort);
			
			pp.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "IP adres en poort nr"));
			
			return pp;
	}
	
	public JPanel startPanel(){
		startButton = new JButton("Connect");
		JPanel startPanel = new JPanel();
		startPanel.add(startButton);
		startButton.addActionListener(this);
		
		return startPanel;
	}
	
	public JPanel numberPlayers(){
		numberChooser = new JComboBox<String>();
		
		numberChooser.addItem("2 spelers");
		numberChooser.addItem("3 spelers");
		numberChooser.addItem("4 spelers");
		
		numberChooser.addActionListener(this);
		JPanel numberPlayers = new JPanel();
		numberPlayers.add(numberChooser);
		numberPlayers.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Choose number of Players"));
		return numberPlayers;
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		CardLayout cl = (CardLayout)(switchPanel.getLayout());
		if(numberChooser.getSelectedItem() == "2 spelers"){
			Game.NUMBER_PLAYERS = 2;
		}
		else if(numberChooser.getSelectedItem() == "3 spelers"){
			Game.NUMBER_PLAYERS = 3;
		}
		else if(numberChooser.getSelectedItem() == "4 spelers"){
			Game.NUMBER_PLAYERS = 4;
		}
		if(ae.getActionCommand().equals("human")){
			nameField.setText("");
			cl.show(switchPanel, "NamePanel");
		}
		else if(ae.getActionCommand().equals("computer")){
			nameField.setText("Computer");
			cl.show(switchPanel, "StrategyPanel");
		}

		if(ae.getSource() == startButton){
			String hostName = tfAddress.getText();
			int portNumber = 0;
			if(nameField.getText().equals("")){
					errorFrameStart();
			}
			else{
				try{
					portNumber = Integer.parseInt(tfPort.getText());
					
					try {
						socket = new Socket(hostName, portNumber);
						System.out.println("ik open nu een socket!");
						PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
					    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					    
					    String line;
					    
					    int opties = 0;
					    int versie = 35;
						out.println("hello " + nameField.getText() + " " + opties + " " + versie);
					    while ((line = in.readLine()) != null) {
					        System.out.println("Server: " + line);
					        
					        if (line.equals("Bye.")){
					            break;
					        }
					    }
					    tfPort.setEditable(false);
					    startFrame.dispose();
					    
					} catch (UnknownHostException e) {
						e.printStackTrace();
					} catch (IOException eio) {
						eio.printStackTrace();
					}
				
				} catch (NumberFormatException e) {
					errorFrame();
					startFrame.setEnabled(false);
				}
			}
		}
		if(ae.getSource() == okButton){
			errorFrame.dispose();
			startFrame.setEnabled(true);
		}
		if(ae.getSource() == okStartErrorButton){
			errorFrameStart.dispose();
		}
	}
	
	public JFrame errorFrameStart(){
		errorFrameStart = new JFrame();
		errorFrameStart.setLayout(new FlowLayout());
		JLabel errorStartLabel = new JLabel("ERROR: no name entered");
		errorFrameStart.add(errorStartLabel);
		okStartErrorButton = new JButton("OK");
		okStartErrorButton.addActionListener(this);
		errorFrameStart.add(okStartErrorButton);
		errorFrameStart.setVisible(true);
		errorFrameStart.setSize(200, 100);
		return errorFrameStart;
	}
	
	public JFrame errorFrame(){
		errorFrame = new JFrame();
		errorFrame.setLayout(new FlowLayout());
		JLabel errorLabel = new JLabel("ERROR: not a valid portnumber!");
		errorFrame.add(errorLabel);
		okButton = new JButton("OK");
		okButton.addActionListener(this);
		errorFrame.add(okButton);
		errorFrame.setVisible(true);
		errorFrame.setSize(250, 120);
		return errorFrame;
	}
	
	static public void main(String[] args) {
	    new Start();
	}
}
