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
	private static final long serialVersionUID = 1L;
	JPanel panel;
	JButton[] button;
	int player = 1;
	int[][] Board = new int[3][3];
	JFrame frame = new JFrame("TicTacToe");
	
	JButton[][] buttons = new JButton[3][3];
	JButton start = new JButton("Start");              //Create start/reset buttons for game
	JButton reset = new JButton("Reset");
	Hello stub;
	URL iconX_URL = GUI.class.getResource("X-icon.png");
	ImageIcon iconX ;
	URL iconO_URL = GUI.class.getResource("O-icon.png");
	ImageIcon iconO ;
	public GUI(Hello stub_in) throws IOException{
		//super();
		stub = stub_in;
	    JPanel mainPanel = new JPanel(new BorderLayout());         //create main panel container to put layer others on top
	    JPanel menu = new JPanel(new BorderLayout());
	    JPanel game = new JPanel(new GridLayout(3,3));                     //Create two more panels with layouts for buttons

	    mainPanel.setPreferredSize(new Dimension(300,360));
	    menu.setPreferredSize(new Dimension(300,30));                     //Setting dimensions of panels
	    game.setPreferredSize(new Dimension(300,300));

	    menu.add(start, BorderLayout.WEST);                //Add both start/reset buttons to menu container panel
	    menu.add(reset, BorderLayout.EAST);

	    start.addActionListener(this);
	    reset.addActionListener(this);

	   for(int i = 0; i < 3; i++)                      //Create grid of buttons for tic tac toe game
		 {
		  for(int j = 0; j < 3; j++) 
		    {
		
		     buttons[i][j] = new JButton();                //Instantiating buttons 
			 buttons[i][j].setText("");
			 buttons[i][j].setVisible(true);
			 game.add(buttons[i][j]); 
			 buttons[i][j].addActionListener(this);        //Adding response event to buttons
		    }
		 }
	    mainPanel.add(menu, BorderLayout.NORTH);                   //Add two panels to the main container panel             
	    mainPanel.add(game, BorderLayout.SOUTH);
	    frame.add(mainPanel);                                         //add main container panel to frame
	    frame.setSize(350, 360);
	    frame.setDefaultCloseOperation(EXIT_ON_CLOSE);        //Setting dimension of Jframe and setting parameters
	    frame.setVisible(true);
	    frame.setResizable(false);
	    int width = buttons[0][0].getSize().width;
	    int height = buttons[0][0].getSize().height;
	    //Resize icon X
	    Image imageX = ImageIO.read(iconX_URL);
	    imageX = imageX.getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconX = new javax.swing.ImageIcon(imageX);

	    //Resize icon X		
		Image imageO = ImageIO.read(iconO_URL);
	    imageO = imageO.getScaledInstance(width, height, Image.SCALE_DEFAULT);
		iconO = new javax.swing.ImageIcon(imageO);
	}
	public void actionPerformed(ActionEvent e){
		int x=0, y=0;
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
			} catch (RemoteException | ServerNotActiveException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			JOptionPane.showMessageDialog(null, response);
			
		}
		else if(reset == e.getSource()){
			System.out.println("Reset");
			reset_game();
		}
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
				if(stub.client_move(y, x, 1))
				{
					buttons[y][x].setIcon(iconX);
					//buttons[y][x].setEnabled(false);
					if(stub.winner(y, x, 1))
					{
						show_winner(1);
						reset_game();
					}
					int move = stub.server_move();
					x= move/10;
					y= (move-(x*10))%3;
					System.out.println(move + " "+x +" "+y);
					buttons[y][x].setIcon(iconO);
					//buttons[y][x].setEnabled(false);
					if(stub.winner(y, x, 2))
					{
						show_winner(2);
						reset_game();
					}					
				}	
			} catch (RemoteException | ServerNotActiveException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	public void show_winner(int player){
		String wins="";
		if(player == 1)
			wins = "X";
		else
			wins = "O";
		JOptionPane.showMessageDialog(null, wins+" Wins");
	}
	
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
