package game.client;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;
public class GUI extends JFrame implements ActionListener{
	//Create the basic elements for our GUI
	private static final long serialVersionUID = 1L;
	JPanel panel;
	JButton[] button;
	int player = 1;
	int[][] Board = new int[3][3];
	JFrame frame = new JFrame("TicTacToe");
	//Create buttons for game
	JButton[][] buttons = new JButton[3][3];
	JButton start = new JButton("Start");              
	JButton reset = new JButton("Reset");
	//Create a stub that will be initialized by the client
	Hello stub;
	//Creates the icons for the GUI
	URL iconX_URL = GUI.class.getResource("X-icon.png");
	ImageIcon iconX ;
	URL iconO_URL = GUI.class.getResource("O-icon.png");
	ImageIcon iconO ;
	
	//Initializer of the class
	public GUI(Hello stub_in) throws IOException{
	   //Initialize the stub for the GUI
	   stub = stub_in;
	   //Initialize GUI containers
	   JPanel mainPanel = new JPanel(new BorderLayout());        
	   JPanel menu = new JPanel(new BorderLayout());
	   JPanel game = new JPanel(new GridLayout(3,3));
	   mainPanel.setPreferredSize(new Dimension(300,360));
	   menu.setPreferredSize(new Dimension(300,30));                     
	   game.setPreferredSize(new Dimension(300,300));
	   //Add both start/reset buttons to menu container panel
	   menu.add(start, BorderLayout.WEST);                
	   menu.add(reset, BorderLayout.EAST);
	   //Add response event to buttons
	   start.addActionListener(this);
	   reset.addActionListener(this);
	   //Create grid of buttons for the game
	   for(int i = 0; i < 3; i++){                      
		  for(int j = 0; j < 3; j++){ 		
		     buttons[i][j] = new JButton();                
			 buttons[i][j].setText("");
			 buttons[i][j].setVisible(true);
			 game.add(buttons[i][j]); 
			 buttons[i][j].addActionListener(this);        
		   }
		}
	    //Add two panels to the main container panel
	    mainPanel.add(menu, BorderLayout.NORTH);                                
	    mainPanel.add(game, BorderLayout.SOUTH);
	    //Initialize the frame
	    frame.add(mainPanel);                                         
	    frame.setSize(350, 360);
	    frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
	    frame.setVisible(true);
	    frame.setResizable(false);
	    int width = buttons[0][0].getSize().width;
	    int height = buttons[0][0].getSize().height;
	    //Initialize the icons for the game
	    Image imageX = ImageIO.read(iconX_URL);
	    imageX = imageX.getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconX = new javax.swing.ImageIcon(imageX);	
		Image imageO = ImageIO.read(iconO_URL);
	    imageO = imageO.getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconO = new javax.swing.ImageIcon(imageO);
	}
	
	//Actions performed by the GUI buttons
	public void actionPerformed(ActionEvent e){
		int x=0, y=0;
		//Check if the button pressed is the start
		//Loads the board of the game and shows message 
		if(start == e.getSource() ){
			System.out.println("Start");
			String response="";
			response = "Board Loaded";
			try {
				int[][] response_board = stub.get_Board_server();
				for(int i =0; i <3; i++){
					for(int j =0; j <3; j++){
						if(response_board[i][j] == 1)
							buttons[i][j].setIcon(iconX);
						else if(response_board[i][j] == 2)
							buttons[i][j].setIcon(iconO);
					}		
				}
				JOptionPane.showMessageDialog(null, response);
			} catch (RemoteException | ServerNotActiveException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, "Error loading board");
			}
			
		}
		//Check if the button pressed is the reset
		else if(reset == e.getSource()){
			System.out.println("Reset");
			reset_game();
		}
		//Check which board button was pressed
		else{
			for(int i =0; i <3; i++){
				for(int j =0; j <3; j++){
					if(buttons[i][j] == e.getSource()){
						y= i;
						x= j;
					}			
				}		
			}
			try {
				//Try if the new move is valid
				if(stub.client_move(y, x, 1)){
					buttons[y][x].setIcon(iconX);
					//Look if the move make the player win
					if(stub.winner(y, x, 1)){
						//Show winner and reset game
						show_winner(1);
						reset_game();
					}
					//Receive move done by the server and
					//check winner
					int move = stub.server_move();
					x= move/10;
					y= (move-(x*10))%3;
					System.out.println(move + " "+x +" "+y);
					buttons[y][x].setIcon(iconO);
					if(stub.winner(y, x, 2)){
						//Show winner and reset game
						show_winner(2);
						reset_game();
					}					
				}	
			} catch (RemoteException | ServerNotActiveException e1) {
				e1.printStackTrace();
			}
		}
	}
	//Shows the winner
	public void show_winner(int player){
		String wins="";
		if(player == 1)
			wins = "X";
		else
			wins = "O";
		JOptionPane.showMessageDialog(null, wins+" Wins");
	}
	//Resets the game interface and 
	//the server part of the game
	public void reset_game(){
		for(int i =0; i <3; i++){
			for(int j =0; j <3; j++){
				buttons[i][j].setIcon(null);
				buttons[i][j].setEnabled(true);
			}
		}
		try {
			stub.reset();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
