package rolit;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
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
	private JTextField nameField;
	private JComboBox<String> strategyChooser;
	private JButton startButton;
	private JComboBox<String> numberChooser;
	private JFrame startFrame;
	private JTextField tfPort;
	private JButton okButton;
	private JFrame errorFrame;
	private Color kleur;
	Game game = new Game();
	
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
//		startFrame.add(namePanel(), BorderLayout.EAST);
		startFrame.add(strategyPanel(), BorderLayout.EAST);
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
	
	public JPanel humanComputerPlayerPanel(){
		JPanel humanComputerPlayerPanel = new JPanel();
		humanComputerPlayerPanel.setLayout(new GridLayout(2,1));
		
		humanButton = new JRadioButton("Human player");
		humanButton.setActionCommand("human");
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
			JTextField tfAddress = new JTextField(getHostAddress(), 9);
			tfAddress.setEditable(false);

			JLabel lbPort = new JLabel("Port:");
			tfPort = new JTextField("2727", 5);

			pp.add(lbAddress);
			pp.add(tfAddress);
			pp.add(lbPort);
			pp.add(tfPort);
			
			pp.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "IP adres en poort nr"));
			
			return pp;
	}
	
	private String getHostAddress() {
		try {
			InetAddress iaddr = InetAddress.getLocalHost();
			return iaddr.getHostAddress();
		} catch (UnknownHostException e) {
			return "?unknown?";
		}
	}
	
	public JPanel startPanel(){
		startButton = new JButton("Start Game");
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
			kleur = Color.red;
			
		}
		else if(ae.getActionCommand().equals("computer")){
			kleur = Color.yellow;
		}

		if(ae.getSource() == startButton){
			int port = 0;
			int max = 0;

			try {
				port = Integer.parseInt(tfPort.getText());
			} catch (NumberFormatException e) {
				errorFrame();
				
				return;
			}
			
			tfPort.setEditable(false);
			GUI gui = new GUI(game);
			startFrame.dispose();
			
		}
		if(ae.getSource() == okButton){
			errorFrame.dispose();
		}
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
