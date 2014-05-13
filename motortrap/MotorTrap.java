package motortrap;

import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
import static java.lang.System.*;
import static java.lang.Character.*;
import java.awt.event.*;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class MotorTrap extends JFrame //implements ChangeListener
{
	private final static int	SLEEP				= 20;
	
	private final static String	PROFILES_BUTTON		= "      Profiles     ";
	private final static String	ARENA_BUTTON		= "       Arena       ";
	private final static String	SHOP_BUTTON			= "       Shop        ";
	private final static String	GARAGE_BUTTON		= "       Garage      ";
	private final static String	INSTRUCTIONS_BUTTON	= "     Instructions   ";
	
	
	public MotorTrap()
	{	
		
	}
	
	
	public void addComponentToPane(Container pane)
	{
		JTabbedPane tabbedPane = new JTabbedPane();
		
		Profiles profiles = new Profiles();
		Arena arena = new Arena();
		Shop shop = new Shop();
		Garage garage = new Garage();
		Instructions instructions = new Instructions();
		
		tabbedPane.addTab(PROFILES_BUTTON,profiles);
		tabbedPane.addTab(ARENA_BUTTON,arena);
		tabbedPane.addTab(SHOP_BUTTON,shop);
		tabbedPane.addTab(GARAGE_BUTTON,garage);
		tabbedPane.addTab(INSTRUCTIONS_BUTTON,instructions);
		
		ChangeListener changeListener = new ChangeListener()
		{
			@Override
			public void stateChanged(ChangeEvent changeEvent)
			{
				JTabbedPane sourceTabbedPane = (JTabbedPane)changeEvent.getSource();
				int index = sourceTabbedPane.getSelectedIndex();
				System.out.println("Tab changed to: " + sourceTabbedPane.getTitleAt(index));
				(sourceTabbedPane.getComponent(index)).setFocusable(true);
				(sourceTabbedPane.getComponent(index)).requestFocusInWindow();
				
				if(sourceTabbedPane.getTitleAt(index).equals(ARENA_BUTTON) == true)
				{
					Arena temp = (Arena)sourceTabbedPane.getComponent(index);
					temp.pause_game(true);
				}
			}
		};
		tabbedPane.addChangeListener(changeListener);
		
		int i = tabbedPane.getSelectedIndex();
		pane.add(tabbedPane,BorderLayout.CENTER);
		i=i+1;
		
	}
	
	
	public static void main(String[] args) throws Exception
	{
		/* Use an appropriate Look and Feel */
		try
		{
			//UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		}
		catch(UnsupportedLookAndFeelException ex)
		{
			ex.printStackTrace();
		}
		catch(IllegalAccessException ex)
		{
			ex.printStackTrace();
		}
		catch(InstantiationException ex)
		{
			ex.printStackTrace();
		}
		catch(ClassNotFoundException ex)
		{
			ex.printStackTrace();
		}
		/* Turn off metal's use of bold fonts */
		UIManager.put("swing.boldMetal",Boolean.FALSE);
		
		
		JFrame frame = new JFrame("MotorTrap");
		MotorTrap game = new MotorTrap();
		game.addComponentToPane(frame.getContentPane());
		//Arena start = new Arena();
		//frame.add(start);
		frame.setSize(1200,800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setBackground(Color.WHITE);
		
		JTabbedPane tabs = (JTabbedPane)frame.getContentPane().getComponent(0);
		int index = tabs.getSelectedIndex();
		tabs.getComponent(index).setFocusable(true);
		tabs.getComponent(index).requestFocusInWindow();
		
		while(true)//while the programs running (infinent loop)
		{
			frame.repaint();
			//start.paintComponent(start.getGraphics());
			Thread.sleep(SLEEP);
		}
	}
}
