package motortrap;

import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
import static java.lang.System.*;
import static java.lang.Character.*;
import java.awt.event.*;
import javax.swing.Timer;

public class Bike
{
	protected Items				engine;
	protected Items				body;
	protected Items				tires;
	protected Items				front_weapon;
	protected Items				back_weapon;
	protected Items				special;
	protected Items				bike_function;
	protected Color				color;
	
	protected int				speed;
	protected int				xpos;
	protected int				ypos;
	protected char				direction;
	protected int				weight;
	
	protected Wall				current_wall;
	protected ArrayList<Wall>	walls;
	protected Point				last_wall_pt;
	
	
	
	public Bike()
	{
		engine = Engine.DEFAULT;
		body = Body.DEFAULT;
		tires = Tires.DEFAULT;
		front_weapon = Front_Weapon.DEFAULT;
		back_weapon = Back_Weapon.DEFAULT;
		special = Special.DEFAULT;
		bike_function = Bike_Function.DEFAULT;
		color = Color.BLUE;
		
		speed = 5;
		xpos = 150;
		ypos = 100;
		direction = 'E';
		weight = 0;
		
		walls = new ArrayList<Wall>(0);
		last_wall_pt = new Point(xpos, ypos);
	}
	
	
	public Bike(int player_number)
	{
		engine = Engine.DEFAULT;
		body = Body.DEFAULT;
		tires = Tires.DEFAULT;
		front_weapon = Front_Weapon.DEFAULT;
		back_weapon = Back_Weapon.DEFAULT;
		special = Special.DEFAULT;
		bike_function = Bike_Function.DEFAULT;
		
		if(player_number == 1)
		{
			color = Color.BLUE;
			speed = 5;
			xpos = 150;
			ypos = 100;
			direction = 'E';
		}
		else if(player_number == 2)
		{
			color = Color.RED;
			speed = 5;
			xpos = 900;
			ypos = 650;
			direction = 'W';
		}
		else
		{
			color = Color.GREEN;
			speed = 5;
			xpos = 800;
			ypos = 75;
			direction = 'S';
		}
		weight = 0;
		
		walls = new ArrayList<Wall>(0);
		last_wall_pt = new Point(xpos, ypos);
	}
	
	
	public Bike(ArrayList<String> file_bike_info)
	{
		String e = file_bike_info.get(0).toUpperCase();
		String b = file_bike_info.get(1).toUpperCase();
		String t = file_bike_info.get(2).toUpperCase();
		String fw = file_bike_info.get(3).toUpperCase();
		String bw = file_bike_info.get(4).toUpperCase();
		String s = file_bike_info.get(5).toUpperCase();
		String f = file_bike_info.get(6).toUpperCase();
		String c = file_bike_info.get(7).toUpperCase();
		
		if(e.equals("DEFAULT"))
		{
			engine = Engine.DEFAULT;
		}
		else
		{
//			protected static Items	FUEL_INJECTED	= new Items("Fuel Injected",500,500,0);
//			protected static Items	LIGHT_WEIGHT	= new Items("Light Weight",1000,1000,0);
//			protected static Items	TURBO			= new Items("Turbo",5000,5000,0);
		}
		
		if(b.equals("DEFAULT"))
		{
			body = Body.DEFAULT;
		}
		else
		{
//			protected static Items	TANK			= new Items("Tank",500,500,0);
//			protected static Items	LIGHT_WEIGHT	= new Items("Light Weight",1000,1000,0);
//			protected static Items	BATTLE_READY	= new Items("Battle Ready",5000,5000,0);
		}
		
		if(t.equals("DEFAULT"))
		{
			tires = Tires.DEFAULT;
		}
		else
		{
//			protected static Items	THICK	= new Items("Thick",500,500,0);
//			protected static Items	RACING	= new Items("Racing",1000,1000,0);
//			protected static Items	ARMORED	= new Items("Armored",5000,5000,0);
		}
		
		if(fw.equals("DEFAULT"))
		{
			front_weapon = Front_Weapon.DEFAULT;
		}
		else
		{
//			protected static Usable_Items	LASER			= new Usable_Items(new Items("Laser",500,500,0),3,3,5);
//			protected static Usable_Items	WALL_BLASTER	= new Usable_Items(new Items("Wall Blaster",1000,1000,0),3,3,5);
//			protected static Usable_Items	DRILL			= new Usable_Items(new Items("Drill",5000,5000,0),3,3,5);
//			protected static Usable_Items	SUPER_BEAM		= new Usable_Items(new Items("Super Beam",10000,10000,0),3,3,5);
		}
		
		if(bw.equals("DEFAULT"))
		{
			back_weapon = Back_Weapon.DEFAULT;
		}
		else
		{
//			protected static Usable_Items	SPIKES			= new Usable_Items(new Items("Spikes",500,500,0),3,3,5);
//			protected static Usable_Items	OIL_SLICK		= new Usable_Items(new Items("Oil Slick",1000,1000,0),3,3,5);
//			protected static Usable_Items	SMOKE_SCREEN	= new Usable_Items(new Items("Smoke Screen",5000,5000,0),3,3,5);
		}
		
		if(s.equals("DEFAULT"))
		{
			special = Special.DEFAULT;
		}
		else
		{
//			protected static Usable_Items	AUTO_PILOT		= new Usable_Items(new Items("Auto Pilot",500,500,0),3,3,5);
//			protected static Usable_Items	SIDE_LASERS		= new Usable_Items(new Items("Side Lasers",5000,5000,0),3,3,5);
//			protected static Usable_Items	INVISIBLE_WALLS	= new Usable_Items(new Items("Invisible Walls",5000,5000,0),3,3,5);
//			protected static Usable_Items	HOLOGRAM_WALLS	= new Usable_Items(new Items("Hologram Walls",10000,10000,0),3,3,5);
		}
		
		if(f.equals("DEFAULT"))
		{
			bike_function = Bike_Function.DEFAULT;
		}
		else
		{
//			protected static Usable_Items	BREAKS				= new Usable_Items(new Items("Breaks",500,500,0),3,3,5);
//			protected static Usable_Items	BOOSTERS			= new Usable_Items(new Items("Boosters",1000,1000,0),3,3,5);
//			protected static Usable_Items	JUMPER_HYDROLICS	= new Usable_Items(new Items("Jumper Hydrolics",5000,5000,0),3,3,5);
		}
		color = Color.getColor(c);
		speed = 5;
		xpos = 150;
		ypos = 100;
		direction = 'E';
		weight = 0;
		
		walls = new ArrayList<Wall>(0);
		last_wall_pt = new Point(xpos, ypos);
	}
	
	
	public void setup(int player_position_num)
	{
		if(player_position_num == 1)
		{
			xpos = 150;
			ypos = 100;
			direction = 'E';
		}
		if(player_position_num == 2)
		{
			xpos = 900;
			ypos = 650;
			direction = 'W';
		}
		walls = new ArrayList<Wall>(0);
		last_wall_pt = new Point(xpos, ypos);
	}
	
	
	public int calc_speed()
	{
		// this will figure out how fast the bike will go due to all of the bike's individual components
		return speed;
	}
	
	
	public void turn_right()
	{
		set_wall();
		if(direction == 'N')
		{
			direction = 'E';
		}
		else if(direction == 'E')
		{
			direction = 'S';
		}
		else if(direction == 'S')
		{
			direction = 'W';
		}
		else if(direction == 'W')
		{
			direction = 'N';
		}
	}
	
	
	public void turn_left()
	{
		set_wall();
		if(direction == 'N')
		{
			direction = 'W';
		}
		else if(direction == 'W')
		{
			direction = 'S';
		}
		else if(direction == 'S')
		{
			direction = 'E';
		}
		else if(direction == 'E')
		{
			direction = 'N';
		}
	}
	
	
	// sets the current wall as a permanent wall in the bike's array list
	public void set_wall()
	{
		current_wall.set_end_pt(xpos, ypos);
		walls.add(current_wall);
		current_wall = new Wall(xpos, ypos);
		last_wall_pt = new Point(xpos, ypos);
	}
	
	
	public void draw(Graphics g)
	{
		this.draw(g, false, false);
	}
	
	
	public void draw(Graphics g, boolean clear, boolean outlined)
	{
		if(clear == true)
		{
			g.setColor(Color.WHITE);
		}
		else
		{
			g.setColor(color);
		}
		
		
		if(direction == 'N')
		{
			if(outlined == false)
			{
				g.fillRect(xpos - 5, ypos, 10, 25);
			}
			else
			{
				g.drawRect(xpos - 5, ypos, 10, 25);
			}
		}
		if(direction == 'E')
		{
			if(outlined == false)
			{
				g.fillRect(xpos - 25, ypos - 5, 25, 10);
			}
			else
			{
				g.drawRect(xpos - 25, ypos - 5, 25, 10);
			}
		}
		if(direction == 'S')
		{
			if(outlined == false)
			{
				g.fillRect(xpos - 5, ypos - 25, 10, 25);
			}
			else
			{
				g.drawRect(xpos - 5, ypos - 25, 10, 25);
			}
		}
		if(direction == 'W')
		{
			if(outlined == false)
			{
				g.fillRect(xpos, ypos - 5, 25, 10);
			}
			else
			{
				g.drawRect(xpos, ypos - 5, 25, 10);
			}
		}
	}
	
	
	public void move_and_draw(Graphics g)
	{
		this.move_and_draw(g, false);
	}
	
	
	public void move_and_draw(Graphics g, boolean outlined)
	{
		current_wall = new Wall(current_wall.beg_pt, new Point(xpos, ypos));// updates the current wall
		
		if(direction == 'N')
		{
			this.draw(g, true, false);// clears the previous painting
			ypos = ypos - calc_speed();
			this.draw(g, false, outlined);
		}
		if(direction == 'E')
		{
			this.draw(g, true, false);// clears the previous painting
			xpos = xpos + calc_speed();
			this.draw(g, false, outlined);
		}
		if(direction == 'S')
		{
			this.draw(g, true, false);// clears the previous painting
			ypos = ypos + calc_speed();
			this.draw(g, false, outlined);
		}
		if(direction == 'W')
		{
			this.draw(g, true, false);// clears the previous painting
			xpos = xpos - calc_speed();
			this.draw(g, false, outlined);
		}
	}
	
	
	public void draw_walls(Graphics g, boolean draw_current_wall)
	{
		g.setColor(color);
		if(draw_current_wall == true)
		{
			current_wall.draw(g);
		}
		for(Wall w: walls)
		{
			w.draw(g);
		}
	}
}
