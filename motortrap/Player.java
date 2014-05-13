package motortrap;

import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
import static java.lang.System.*;
import static java.lang.Character.*;
import java.awt.event.*;
import javax.swing.Timer;

public class Player
{
	protected String	username;
	protected String	password;
	protected int		player_number;
	protected int		money;
	protected int		lives;
	protected int		bonus_lives;
	protected char[]	keys;
	protected int[]		score	= {0,0};	// total player's wins vs losses
	protected Bike		bike;
	
	
	public Player()
	{
		username = "J. Smith";
		password = "nothing";
		player_number = 1;
		money = 0;
		lives = 3;
		bonus_lives = 0;
		keys = new char[6];
		char[] temp = {'Q','W','E','A','S','D'};
		setKeys(temp);
		score = new int[] {0,0};
		bike = new Bike();
	}
	
	
	public Player(int player_num)
	{
		username = "J. Smith";
		password = "nothing";
		player_number = player_num;
		money = 0;
		lives = 3;
		bonus_lives = 0;
		score = new int[] {0,0};
		keys = new char[6];
		char[] temp1 = {'Q','W','E','A','S','D'};
		char[] temp2 = {'I','O','P','K','L',';'};
		if(player_num == 2)
		{
			setKeys(temp2);
		}
		else
		{
			setKeys(temp1);
		}
		bike = new Bike(player_num);
	}
	
	
	public Player(ArrayList<String> file_info)
	{
		// FILE FORMAT:
		// line1 = [username],[password]
		// line2 = [player number],[money],[lives],[bonus lives],[# games won],[# games lost],[6 keys string]
		// line3 = [engine],[body],[tires],[front_weapon],[back_weapon],[special],[bike_function],[color]
		
		ArrayList<String> line1 = (ArrayList<String>)Arrays.asList((file_info.get(0).split(",")));
		ArrayList<String> line2 = (ArrayList<String>)Arrays.asList((file_info.get(1).split(",")));
		ArrayList<String> line3 = (ArrayList<String>)Arrays.asList((file_info.get(1).split(",")));
		
		username = line1.get(0);
		password = line1.get(1);
		
		player_number = Integer.parseInt(line2.get(0));
		money = Integer.parseInt(line2.get(1));
		lives = Integer.parseInt(line2.get(2));
		bonus_lives = Integer.parseInt(line2.get(3));
		score[0] = Integer.parseInt(line2.get(4));
		score[1] = Integer.parseInt(line2.get(5));
		setKeys(line2.get(6));
		bike = new Bike(line3);
	}
	
	
	public void setKeys(char[] temp)
	{
		if(temp.length == keys.length)
		{
			keys = temp;
		}
	}
	
	
	public void setKeys(String k)
	{
		k = k.toUpperCase();
		if(k.length() == keys.length)
		{
			for(int i = 0; i < k.length(); i++)
			{
				keys[i] = k.charAt(i);
			}
		}
	}
}
