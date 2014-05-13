package motortrap;

import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
import static java.lang.System.*;
import static java.lang.Character.*;
import java.awt.event.*;
import javax.swing.Timer;

public class Profiles extends JPanel
{
	protected String						file_name	= "profiles.txt";
	protected File							file;
	protected ArrayList<ArrayList<String>>	content;
	
	protected Player						example;
	
	
	public Profiles()
	{
		this.setSize(1200, 800);
		this.setVisible(true);
		this.setBackground(Color.GRAY);
		
		file = new File(file_name);
		content = new ArrayList<ArrayList<String>>();
		getFileContents();
		example = getExamplePlayer();
		
		
	}
	
	
	public Player getExamplePlayer()
	{
		Player mark = new Player();
		
		mark.username = "Mark";
		mark.password = "password";
		mark.player_number = 1;
		mark.money = 9999;
		mark.lives = 3;
		mark.bonus_lives = 1;
		mark.score = new int[] {2,1};
		mark.setKeys("WERSDF");
		
		
		Bike cycle = new Bike();
		cycle.color = Color.MAGENTA;
		mark.bike = cycle;
		
		return mark;
	}
	
	
	public void getFileContents()
	{
		try
		{
			Scanner scan = new Scanner(file_name);
			content = new ArrayList<ArrayList<String>>();
			while(scan.hasNextLine() == true)
			{
				String line = scan.nextLine();
				if((line.length() > 0) && (line.charAt(0) == '>'))
				{
					ArrayList<String> info = new ArrayList<String>();
					info.add(scan.nextLine());// username & password
					info.add(scan.nextLine());// profile info
					info.add(scan.nextLine());// bike info
					content.add(info);
				}
			}
			
		}
		catch(Exception e)
		{
			out.println("File Not Found: " + file_name);
			out.println("\n" + e.toString());
		}
	}
	
	
	public void save(Player p)
	{	
		
	}
	
	
	public int getUserIndex(String user)
	{
		for(int index = 0; index < content.size(); index++)
		{
			ArrayList<String> line1 = (ArrayList<String>)Arrays.asList((content.get(index).get(0).split(",")));
			// line1 is an arraylist of the 1st line of the profile data (gathered from the file)
			// line1 at 0 = username, and at 1 = password
			if(line1.get(0).equalsIgnoreCase(user) == true)
			{
				return index;
			}
		}
		return -1;// user could not be found
	}
	
	
	public Player getProfile(String user)
	{
		Player p = new Player();
		int i = getUserIndex(user);
		
		if(i != -1)
		{
			p = new Player(i);
		}
		
		return p;
	}
	
}
