import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class DataGenerator {
		
	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		if (args.length != 3) {
			inputs();
		}
		
		String fileNameAdd;
		System.out.println("Enter filename:");
		fileNameAdd = sc.nextLine();
		
		
		ArrayList<String> rand = new ArrayList<String>();
		
		int numOfStr = Integer.parseInt(args[0]);
		double numOfScen = Double.parseDouble(args[1]) /100;
		int indexes = (int)Math.floor(numOfScen * numOfStr);
		
		
		randStringArr(rand, numOfStr);
		
		try {
			PrintWriter writer = new PrintWriter(fileNameAdd);
			printAdd(rand, writer);
			Collections.shuffle(rand);
			printScenario(rand, writer, args[2], indexes);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		

	}
	
	public static void inputs()	{
		System.out.println("Arguments: <num of strings to gen> <percentage of removals> <scenario>");
		System.out.println("i.e. 100 75 S");
		System.out.println("Possible scenarios: S, RO, RA");
		System.exit(0);
	}
	
	public static String randomStringGen()	{
		String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 18) {
            int index = (int) (rnd.nextFloat() * chars.length());
            salt.append(chars.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
	}
	
	public static void randStringArr(ArrayList<String> list, int numOfStr)	{
		String randString;
		for(int i = 0; i < numOfStr; i++)	{
			randString = randomStringGen();
			list.add(randString);
		}
	}
	
	public static void printAdd(ArrayList<String> list, PrintWriter writer)	{
		for(String i : list)	{
	    	writer.print("A ");
	    	writer.println(i);
		}
	}
	
	public static void printScenario(ArrayList<String> list, PrintWriter writer, String scenario, int index)	{
		//List must be randomised first
		for(int i = 0; i < index; i++)	{
			writer.print(scenario + " ");
			writer.println(list.get(i));
		}
	}	
}
