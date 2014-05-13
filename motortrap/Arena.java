package motortrap;

import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
import static java.lang.System.*;
import static java.lang.Character.*;
import java.awt.event.*;
import javax.swing.Timer;

public class Arena extends JPanel implements KeyListener// ,MouseListener
{
	// private static int SLEEP = 20;
	
	protected Player	p1;
	protected Player	p2;
	protected Bike		bike1;
	protected Bike		bike2;
	protected boolean[]	keys1_action;
	protected boolean[]	keys2_action;
	
	protected boolean	setup_new_game;
	protected boolean	is_paused;
	protected int		countdown_timer;
	protected boolean	show_info;
	protected Timer		timer;
	protected int		game_time;
	protected Graphics	g;
	
	
	public Arena()
	{
		this.setSize(1200, 800);
		this.setVisible(true);
		this.setBackground(Color.WHITE);
		
//		timer = new Timer(1000,);
		
		p1 = new Player(1);
		p2 = new Player(2);
		bike1 = p1.bike;
		bike2 = p2.bike;
		keys1_action = new boolean[6];
		keys2_action = new boolean[6];
		Arrays.fill(keys1_action, false);
		Arrays.fill(keys2_action, false);
		
		setup_new_game = true;
		
		setFocusable(true);
		this.addKeyListener(this);
	}
	
	
	public Arena(Player one)
	{
		p1 = one;
		p2 = new Player(2);
		bike1 = p1.bike;
		bike2 = p2.bike;
		keys1_action = new boolean[6];
		keys2_action = new boolean[6];
		Arrays.fill(keys1_action, false);
		Arrays.fill(keys2_action, false);
		
		setup_new_game = true;
		
		setFocusable(true);
		this.addKeyListener(this);
	}
	
	
	public Arena(Player one, Player two)
	{
		p1 = one;
		p2 = two;
		bike1 = p1.bike;
		bike2 = p2.bike;
		keys1_action = new boolean[6];
		keys2_action = new boolean[6];
		Arrays.fill(keys1_action, false);
		Arrays.fill(keys2_action, false);
		
		setup_new_game = true;
		
		setFocusable(true);
		this.addKeyListener(this);
	}
	
	
	/*
	 * sets up all of the variables for each player to start a new game
	 */
	public void setup_game()
	{
		bike1.setup(1);
		bike2.setup(2);
		bike1.current_wall = new Wall(bike1.xpos, bike1.ypos);
		bike2.current_wall = new Wall(bike2.xpos, bike2.ypos);
		show_info = true;
		countdown_timer = 0;
		game_time = 0;
		
		ActionListener counter = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event)
			{
				game_time++;
				if(countdown_timer > 0)
				{
					countdown_timer--;
				}
			}
		};
		timer = new Timer(1000, counter);// call every second (1000 milliseconds)
		timer.start();
		
		
		pause_game(true);
		setup_new_game = false;
	}
	
	
	@Override
	// public void paint(Graphics g)
	public void paintComponent(Graphics window)
	{
		super.paintComponent(window);
		g = window;
		draw_background();
		draw_game();
	}
	
	
	public void draw_background()
	{
		// draws the outer-edge walls
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 50, 800); // left wall
		g.fillRect(0, 0, 1200, 25); // top wall
		g.fillRect(0, 750, 1200, 50); // bottom wall
		g.fillRect(1000, 0, 200, 800); // right wall
		
		
		if(setup_new_game == true)
		{
			setup_game();
		}
		
		
		if(show_info == true)
		{
			g.setColor(bike1.color);
			g.drawString("PLAYER " + p1.player_number + ":", 1025, 50);
			g.drawString(p1.username + "/" + p1.password, 1005, 75);
			g.drawString(bike1.direction + " at (" + bike1.xpos + ", " + bike1.ypos + ")", 1005, 100);
			g.drawString("$" + p1.money, 1020, 125);
			g.drawString("lives: " + p1.lives + "+" + p1.bonus_lives, 1010, 150);
			g.drawString("k1(F.W.): " + p1.keys[0], 1010, 175);
			g.drawString("k2(Spec): " + p1.keys[1], 1010, 200);
			g.drawString("k3(B.W.): " + p1.keys[2], 1010, 225);
			g.drawString("k4(Left): " + p1.keys[3], 1010, 250);
			g.drawString("k5(Func): " + p1.keys[4], 1010, 275);
			g.drawString("k6(Rght): " + p1.keys[5], 1010, 300);
			
			g.drawString("BIKE:", 1025, 375);
			g.drawString("Engn: " + bike1.engine.name, 1010, 400);
			g.drawString("Body: " + bike1.body.name, 1010, 425);
			g.drawString("Tire: " + bike1.tires.name, 1010, 450);
			g.drawString("F.W.: " + bike1.front_weapon.name, 1010, 475);
			g.drawString("B.W.: " + bike1.back_weapon.name, 1010, 500);
			g.drawString("Spec: " + bike1.special.name, 1010, 525);
			g.drawString("Func: " + bike1.bike_function.name, 1010, 550);
			
			g.setColor(bike2.color);
			g.drawString("PLAYER " + p2.player_number + ":", 1125, 50);
			g.drawString(p1.username + "/" + p2.password, 1105, 75);
			g.drawString(bike2.direction + " at (" + bike2.xpos + ", " + bike2.ypos + ")", 1105, 100);
			g.drawString("$" + p2.money, 1120, 125);
			g.drawString("lives:" + p2.lives + " + " + p2.bonus_lives, 1110, 150);
			g.drawString("k1(F.W.): " + p2.keys[0], 1110, 175);
			g.drawString("k2(Spec): " + p2.keys[1], 1110, 200);
			g.drawString("k3(B.W.): " + p2.keys[2], 1110, 225);
			g.drawString("k4(Left): " + p2.keys[3], 1110, 250);
			g.drawString("k5(Func): " + p2.keys[4], 1110, 275);
			g.drawString("k6(Rght): " + p2.keys[5], 1110, 300);
			
			g.drawString("BIKE:", 1125, 375);
			g.drawString("Engn: " + bike2.engine.name, 1110, 400);
			g.drawString("Body: " + bike2.body.name, 1110, 425);
			g.drawString("Tire: " + bike2.tires.name, 1110, 450);
			g.drawString("F.W.: " + bike2.front_weapon.name, 1110, 475);
			g.drawString("B.W.: " + bike2.back_weapon.name, 1110, 500);
			g.drawString("Spec: " + bike2.special.name, 1110, 525);
			g.drawString("Func: " + bike2.bike_function.name, 1110, 550);
			
			g.setColor(Color.YELLOW);
			g.drawString("Time: " + game_time, 1075, 600);
		}
		if((is_paused == true) || (countdown_timer > 0))
		{
			Font original_font = g.getFont();
			Font f = g.getFont().deriveFont(48.0f);
			g.setFont(f);
			g.setColor(Color.ORANGE);
			g.drawString("Game Paused", 300, 300);
			
			g.setFont(original_font);
		}
		
		if(countdown_timer > 0)
		{
			Font original_font = g.getFont();
			Font f = g.getFont().deriveFont(48.0f);
			g.setFont(f);
			g.setColor(Color.ORANGE);
			g.drawString("Starting in: " + countdown_timer, 300, 400);
			
			g.setFont(original_font);
		}
	}
	
	
	public void draw_game()
	{
		if((crash_status() == 0) && (is_paused == false) && (countdown_timer <= 0))// game is going
		{
			bike1.draw_walls(g, true);
			bike2.draw_walls(g, true);
			player_actions_perform();
			bike1.move_and_draw(g);
			bike2.move_and_draw(g);
		}
		else if((crash_status() == 0) && ((is_paused == true) || (countdown_timer > 0)))// game is paused
		{
			bike1.draw_walls(g, true);
			bike2.draw_walls(g, true);
			bike1.draw(g);
			bike2.draw(g);
		}
		else if(crash_status() != 0) // if someone has crashed
		{
			bike1.draw_walls(g, true);
			bike2.draw_walls(g, true);
			bike1.draw(g);
			bike2.draw(g);
			// pause_game(true);
			// setup_new_game = true;
			player_has_crashed();
		}
	}
	
	
	public void player_actions_perform()
	{
		if(keys1_action[0] == true)// p1 front weapon
		{
			// do something
			keys1_action[0] = false;
		}
		else if(keys1_action[1] == true)// p1 special
		{
			// do something
			keys1_action[1] = false;
		}
		else if(keys1_action[2] == true)// p1 back weapon
		{
			// do something
			keys1_action[2] = false;
		}
		
		else if(keys1_action[3] == true)// p1 turn left
		{
			bike1.turn_left();
			keys1_action[3] = false;
		}
		else if(keys1_action[4] == true)// p1 bike function
		{
			// do something
			keys1_action[4] = false;
		}
		else if(keys1_action[5] == true)// p1 turn right
		{
			bike1.turn_right();
			keys1_action[5] = false;
		}
		
		
		if(keys2_action[0] == true)// p2 front weapon
		{
			// do something
			keys2_action[0] = false;
		}
		else if(keys2_action[1] == true)// p2 special
		{
			// do something
			keys2_action[1] = false;
		}
		else if(keys2_action[2] == true)// p2 back weapon
		{
			// do something
			keys2_action[2] = false;
		}
		else if(keys2_action[3] == true)// p2 turn left
		{
			bike2.turn_left();
			keys2_action[3] = false;
		}
		else if(keys2_action[4] == true)// p2 bike function
		{
			// do something
			keys2_action[4] = false;
		}
		else if(keys2_action[5] == true)// p2 turn right
		{
			bike2.turn_right();
			keys2_action[5] = false;
		}
		
	}
	
	
	public int crash_status()
	// returns either 0 if all good or
	// returns the player number that has crashed (1 if p1 crashed, 2 if p2 crashed, etc)
	{
		// crashed into outer wall boundaries
		if((bike1.xpos < 50) || (bike1.xpos > 1000) || (bike1.ypos < 25) || (bike1.ypos > 750))
		{// (hit left wall) OR (hit right wall) OR (hit top wall) OR (hit bottom wall)
			return 1;
		}
		if((bike2.xpos < 50) || (bike2.xpos > 1000) || (bike2.ypos < 25) || (bike2.ypos > 750))
		{// (hit left wall) OR (hit right wall) OR (hit top wall) OR (hit bottom wall)
			return 2;
		}
		
		ArrayList<Wall> all_walls = new ArrayList<Wall>();
		all_walls.addAll(bike1.walls);
		all_walls.addAll(bike2.walls);
		Wall w1 = null;
		Wall w2 = null;
		if(bike1.current_wall.beg_pt != null)
		{
			w1 = new Wall(bike1.current_wall.beg_pt, new Point(bike1.xpos, bike1.ypos));
			all_walls.add(w1);
		}
		if(bike2.current_wall.beg_pt != null)
		{
			w2 = new Wall(bike2.current_wall.beg_pt, new Point(bike2.xpos, bike2.ypos));
			all_walls.add(w2);
		}
		
		for(Wall wall: all_walls)
		{
			if(wall.is_horizontal() == true)
			{
				if(((wall.get_left() <= bike1.xpos) && (wall.get_right() >= bike1.xpos)) &&
						((bike1.ypos - wall.beg_pt.y) <= 2) && ((bike1.ypos - wall.beg_pt.y) >= -2))
				{
					if(wall != w1)// if its not the current wall
					{
						return 1;// bike 1 crashed into a horizontal wall
					}
				}
				if(((wall.get_left() <= bike2.xpos) && (wall.get_right() >= bike2.xpos)) &&
						((bike2.ypos - wall.beg_pt.y) <= 2) && ((bike2.ypos - wall.beg_pt.y) >= -2))
				{
					if(wall != w2)
					{
						return 2;// bike 2 crashed into a horizontal wall
					}
				}
			}
			else if(wall.is_vertical() == true)
			{
				if(((wall.get_top() <= bike1.ypos) && (wall.get_bottom() >= bike1.ypos)) &&
						((bike1.xpos - wall.beg_pt.x) <= 2) && ((bike1.xpos - wall.beg_pt.x) >= -2))
				{
					if(wall != w1)
					{
						return 1;// bike1 crashed into a vertical wall
					}
				}
				if(((wall.get_top() <= bike2.ypos) && (wall.get_bottom() >= bike2.ypos)) &&
						((bike2.xpos - wall.beg_pt.x) <= 2) && ((bike2.xpos - wall.beg_pt.x) >= -2))
				{
					if(wall != w2)
					{
						return 2;// bike1 crashed into a vertical wall
					}
				}
			}
		}
		
		
		
		return 0;
	}
	
	
	public void pause_game(boolean p)
	{
		is_paused = p;
		if(p == true)
		{
			timer.stop();
			countdown_timer = 0;
		}
		else
		{
			timer.restart();
			countdown_timer = 3;
		}
	}
	
	
	/*
	 * counts down 3 seconds to the start of the game (draws this graphically too)
	 * occurs every time the game is setup or paused
	 */
	public void countdown()
	{
		int g_time = game_time;
		game_time = -3;
		timer.restart();
		
		Font original_font = g.getFont();
		Font f = g.getFont().deriveFont(48.0f);
		g.setFont(f);
		g.setColor(Color.RED);
		
		
		
		while(game_time < 0)
		{
			g.drawString("Starts in: " + (-1 * game_time), 300, 400);
		}
		
		g.setFont(original_font);
		game_time = g_time;
//		do_countdown = false;
		pause_game(false);
	}
	
	
	public void player_has_crashed()
	{
		// setup_new_game = true;
		pause_game(true);
	}
	
	
	@Override
	public void keyPressed(KeyEvent e)
	{
		char typed = toUpperCase(e.getKeyChar());
		
		
		if(typed == p1.keys[0])// default is 'Q' = weapon2 (back weapon)
		{
			keys1_action[0] = true;
		}
		else if(typed == p1.keys[1])// default is 'W' = special
		{
			keys1_action[1] = true;
		}
		else if(typed == p1.keys[2])// default is 'E' = weapon1 (front weapon)
		{
			keys1_action[2] = true;
		}
		else if(typed == p1.keys[3])// default is 'A' = p1 turn left
		{
			keys1_action[3] = true;
		}
		else if(typed == p1.keys[4])// default is 'S' = break/boost
		{
			keys1_action[4] = true;
		}
		else if(typed == p1.keys[5])// default is 'D' = p1 turn right
		{
			keys1_action[5] = true;
		}
		
		
		if(typed == p2.keys[0])// default is 'I' = weapon1 (front weapon)
		{
			keys2_action[0] = true;
		}
		else if(typed == p2.keys[1])// default is 'O' = special
		{
			keys2_action[1] = true;
		}
		else if(typed == p2.keys[2])// default is 'P' = weapon2 (back weapon)
		{
			keys2_action[2] = true;
		}
		else if(typed == p2.keys[3])// default is 'K' = p2 turn left
		{
			keys2_action[3] = true;
		}
		else if(typed == p2.keys[4])// default is 'L' = break/boost
		{
			keys2_action[4] = true;
		}
		else if(typed == p2.keys[5])// default is ';' = p2 turn right
		{
			keys2_action[5] = true;
		}
	}
	
	
	@Override
	public void keyReleased(KeyEvent e)
	{
		char typed = toUpperCase(e.getKeyChar());
		
		
		if(typed == p1.keys[0])// default is 'Q' = weapon2 (back weapon)
		{
			keys1_action[0] = false;
		}
		else if(typed == p1.keys[1])// default is 'W' = special
		{
			keys1_action[1] = false;
		}
		else if(typed == p1.keys[2])// default is 'E' = weapon1 (front weapon)
		{
			keys1_action[2] = false;
		}
		else if(typed == p1.keys[3])// default is 'A' = p1 turn left
		{
			keys1_action[3] = false;
		}
		else if(typed == p1.keys[4])// default is 'S' = break/boost
		{
			keys1_action[4] = false;
		}
		else if(typed == p1.keys[5])// default is 'D' = p1 turn right
		{
			keys1_action[5] = false;
		}
		
		
		if(typed == p2.keys[0])// default is 'I' = weapon1 (front weapon)
		{
			keys2_action[0] = false;
		}
		else if(typed == p2.keys[1])// default is 'O' = special
		{
			keys2_action[1] = false;
		}
		else if(typed == p2.keys[2])// default is 'P' = weapon2 (back weapon)
		{
			keys2_action[2] = false;
		}
		else if(typed == p2.keys[3])// default is 'K' = p2 turn left
		{
			keys2_action[3] = false;
		}
		else if(typed == p2.keys[4])// default is 'L' = break/boost
		{
			keys2_action[4] = false;
		}
		else if(typed == p2.keys[5])// default is ';' = p2 turn right
		{
			keys2_action[5] = false;
		}
	}
	
	
	
	@Override
	public void keyTyped(KeyEvent e)
	{
		char typed = toUpperCase(e.getKeyChar());
		if(typed == ' ')
		{
			if(crash_status() != 0)
			{
				setup_game();
				// countdown_timer = 3;
			}
			else
			{
				pause_game(!(is_paused));
			}
		}
		if(typed == '\n')
		{
			// out.println("ENTER");
			setup_game();
		}
		if(typed == '0')
		{
			show_info = !(show_info);
		}
	}
	
	
//	@Override
//	public void mouseClicked(MouseEvent e)
//	{	
//		
//	}
//	
//	
//	@Override
//	public void mousePressed(MouseEvent e)
//	{	
//		
//	}
//	
//	
//	@Override
//	public void mouseReleased(MouseEvent e)
//	{	
//		
//	}
//	
//	
//	@Override
//	public void mouseEntered(MouseEvent e)
//	{	
//		
//	}
//	
//	
//	@Override
//	public void mouseExited(MouseEvent e)
//	{	
//		
//	}
	
	
//	public static void main(String[] args) throws Exception
//	{
//		JFrame frame = new JFrame("MotorTrap - Arena");
//		Arena start = new Arena();
//		frame.add(start);
//		frame.setSize(1200,800);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setVisible(true);
//		frame.setResizable(false);
//		frame.setBackground(Color.WHITE);
//		
//		while(true)//while the programs running (infinent loop)
//		{
//			frame.repaint();
//			//start.paintComponent(start.getGraphics());
//			Thread.sleep(SLEEP);
//		}
//	}
}
