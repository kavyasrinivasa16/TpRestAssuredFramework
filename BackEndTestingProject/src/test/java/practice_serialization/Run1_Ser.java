package practice_serialization;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

class NFSGame implements  Serializable
{
	String name;
	int level;
	long score;
	int life;
	public NFSGame(String name, int level, long score, int life) {
		super();
		this.name = name;
		this.level = level;
		this.score = score;
		this.life = life;
	}
}

public class Run1_Ser {
	
	public static void main(String[] args) throws Throwable {
		NFSGame userObj = new NFSGame("kavya", 15, 60000, 1);
		FileOutputStream fileOut  = new FileOutputStream("./f.txt");
		
		ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
		objOut.writeObject(userObj);
		System.out.println("=========end========");
	}

}
