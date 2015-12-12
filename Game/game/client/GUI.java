package game.client;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.rmi.RemoteException;
public class GUI extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	JPanel panel;
	JButton[] button;
	int count = 0;
	int sign = 0;
	int[][] Board = new int[3][3];
	JFrame frame = new JFrame("TicTacToe");
	
	JButton[][] buttons = new JButton[3][3];
	JButton start = new JButton("Start");              //Create start/reset buttons for game
	JButton reset = new JButton("Reset");
	Hello stub;
	public GUI(Hello stub_in){
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
	}
	public void actionPerformed(ActionEvent e){
		count++;
		if(start == e.getSource() ){
			System.out.println("Start");
			String response="";
			response = "Nothing";
			JOptionPane.showMessageDialog(null, response);
			
		}
		else if(reset == e.getSource()){
			System.out.println("Reset");
			try {
				if(stub.reset()){
					reset_game();
				}
				else{
					JOptionPane.showMessageDialog(null, "Error while reseting");
				}
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		else{
			for(int i =0; i <3; i++){
				for(int j =0; j <3; j++){
					if(buttons[i][j] == e.getSource()){
						if(sign%2 == 0){
							buttons[i][j].setText("X");	
							if(checkWinner(sign%2, i, j))
							{
								show_winner(sign%2);
								reset_game();
							}
						}
						else{
							buttons[i][j].setText("O");
							if(checkWinner(sign%2, i, j))
							{
								show_winner(sign%2);
								reset_game();
							}
						}
						Board[i/3][i%3] = sign%2;
						buttons[i][j].setEnabled(false);
					}			
				}		
			}
			sign++;
			if(count >= 9){
				JOptionPane.showMessageDialog(null, "Cat's Game!");
				reset_game();
			}
			move_IA(sign%2);
			sign++;
			count++;
		}
	}
	public void show_winner(int player){
		
	}
	
	public void move_IA(int player){
		int i, j;
		//calculate i, j, check the move doesn't exist
		i=0;
		j=0;
		
		
		
		if(checkWinner(player, i, j))
		{
			show_winner(player);
			reset_game();
		}
	}
	
	public boolean checkWinner(int player, int i, int j){	
		
		return false;
	}
	
	public void reset_game(){
		for(int i =0; i <3; i++){
			for(int j =0; j <3; j++){
				buttons[i][j].setText("");
				buttons[i][j].setEnabled(true);
			}
		}
		count = 0;
		sign = 0;
	}
}
