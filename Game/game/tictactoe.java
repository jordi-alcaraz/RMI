package game;

public class tictactoe {
	private String my_string;
	public tictactoe(String str) {
		this.my_string = str;
	}
	
    private int get_random(int max, int min){
        int randomNum = min+ (int) (Math.random() * ( max - min ));
        return randomNum;
    }
	
	public String getString()
	{
		this.my_string = this.my_string +" "+get_random(9, 0);
		return this.my_string;
	}

}
