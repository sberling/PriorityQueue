import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Sportsball {

	private static Scanner s;
	private static final short HEAP = 0;
	private static final short SECONDARY = 1;
	
	/*
	 * Set equal to 0 (HEAP) to run Sportsball using a BinaryHeap method
	 * Set equal to 1 (SECONDARY) to run Sportsball using my weird way of doing it (I don't know why it works in approx. the same amount of  time)
	 */
	private static short ALGORITHM = HEAP; 
	
	public static void main(String[] args){
		getFile();
		long timeStart = System.currentTimeMillis(); //start time
		PQ pq = (ALGORITHM == SECONDARY) ? new SecondaryVersion() : new PriorityQueue();
		String next = s.nextLine();
		while(true){
			if(next.equalsIgnoreCase("go!")){//player enters game
				if(pq.getSize() > 0){
				String nextPlayer = pq.remove().toString();
				System.out.println(nextPlayer + " enters the game. There are " + pq.getSize() + " players left.");
				}
				else{
					System.out.println("No one is ready!");
				}
			}
			else{//add player to queue
				String[] playerInfo = next.split("/");
				pq.insert(playerInfo[0], new Integer(playerInfo[1]));
			}
			//check if done
			if(s.hasNextLine()){
				next = s.nextLine();
			}
			else{
				break;
			}
		}
		System.out.println("At the end, there were " + pq.getSize() + " players left.");
		long timeEnd = System.currentTimeMillis();
		System.out.println(timeEnd-timeStart + "ms");
	}

	private static void getFile() {
		s = new Scanner(System.in);
		System.out.print("Please input the file name for Sportsball:");
		String fileName = s.next();
		s.close();
		File file = new File(fileName);
		try {
			s = new Scanner(file);
		} catch (FileNotFoundException e) {
			System.err.println("I could not find that file, thanks for playing (or trying to)!");
			System.exit(0);
		}
	}
}
