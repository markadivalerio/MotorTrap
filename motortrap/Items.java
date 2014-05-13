package motortrap;

import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
import static java.lang.System.*;
import static java.lang.Character.*;
import java.awt.event.*;
import javax.swing.Timer;


class Items
{
	protected String		name;
	protected int			buying_price;
	protected int			selling_price;
	protected int			weight;
	
	protected static Items	DEFAULT	= new Items("Default",0,0,0);
	
	
	public Items()
	{
		name = "Item";
		buying_price = 150;
		selling_price = 150;
		weight = 0;
	}
	
	
	public Items(String n, int b, int s, int w)
	{
		name = n;
		buying_price = b;
		selling_price = s;
		weight = w;
	}
}


class Usable_Items extends Items
{
	protected int	uses;
	//uses means the # of times this item can be used in a game
	
	protected int	duration;
	//duration means # of seconds that this item lasts
	
	protected int	cooldown;
	//cooldown means the # of seconds it takes until this item can be used again
	
	protected Items	item;
	
	
	public Usable_Items()
	{
		item = Items.DEFAULT;
		uses = 0;
		duration = 0;
		cooldown = 0;
	}
	
	
	public Usable_Items(Items i)
	{
		item = i;
		uses = 0;
		duration = 0;
		cooldown = 0;
	}
	
	
	public Usable_Items(Items i, int u, int d, int c)
	{
		item = i;
		uses = u;
		duration = d;
		cooldown = c;
	}
}


class Engine extends Items
{
	protected static Items	FUEL_INJECTED	= new Items("Fuel Injected",500,500,0);
	protected static Items	LIGHT_WEIGHT	= new Items("Light Weight",1000,1000,0);
	protected static Items	TURBO			= new Items("Turbo",5000,5000,0);
}


class Body extends Items
{
	protected static Items	TANK			= new Items("Tank",500,500,0);
	protected static Items	LIGHT_WEIGHT	= new Items("Light Weight",1000,1000,0);
	protected static Items	BATTLE_READY	= new Items("Battle Ready",5000,5000,0);
}


class Tires extends Items
{
	protected static Items	THICK	= new Items("Thick",500,500,0);
	protected static Items	RACING	= new Items("Racing",1000,1000,0);
	protected static Items	ARMORED	= new Items("Armored",5000,5000,0);
}


class Front_Weapon extends Usable_Items
{
	protected static Usable_Items	LASER			= new Usable_Items(new Items("Laser",500,500,0),3,3,5);
	protected static Usable_Items	WALL_BLASTER	= new Usable_Items(new Items("Wall Blaster",1000,1000,0),3,3,5);
	protected static Usable_Items	DRILL			= new Usable_Items(new Items("Drill",5000,5000,0),3,3,5);
	protected static Usable_Items	SUPER_BEAM		= new Usable_Items(new Items("Super Beam",10000,10000,0),3,3,5);
}


class Back_Weapon extends Usable_Items
{
	protected static Usable_Items	SPIKES			= new Usable_Items(new Items("Spikes",500,500,0),3,3,5);
	protected static Usable_Items	OIL_SLICK		= new Usable_Items(new Items("Oil Slick",1000,1000,0),3,3,5);
	protected static Usable_Items	SMOKE_SCREEN	= new Usable_Items(new Items("Smoke Screen",5000,5000,0),3,3,5);
}


class Special extends Usable_Items//wall related or anything else
{
	protected static Usable_Items	AUTO_PILOT		= new Usable_Items(new Items("Auto Pilot",500,500,0),3,3,5);
	protected static Usable_Items	SIDE_LASERS		= new Usable_Items(new Items("Side Lasers",5000,5000,0),3,3,5);
	protected static Usable_Items	INVISIBLE_WALLS	= new Usable_Items(new Items("Invisible Walls",5000,5000,0),3,3,5);
	protected static Usable_Items	HOLOGRAM_WALLS	= new Usable_Items(new Items("Hologram Walls",10000,10000,0),3,3,5);
}


class Bike_Function extends Usable_Items//actions that the bike can take
{
	protected static Usable_Items	BREAKS				= new Usable_Items(new Items("Breaks",500,500,0),3,3,5);
	protected static Usable_Items	BOOSTERS			= new Usable_Items(new Items("Boosters",1000,1000,0),3,3,5);
	protected static Usable_Items	JUMPER_HYDROLICS	= new Usable_Items(new Items("Jumper Hydrolics",5000,5000,0),3,3,5);
}


class Misc extends Usable_Items//One-time purchases like changing paint color
{
	protected static Usable_Items	BONUS_LIFE	= new Usable_Items(new Items("Bonus Life",500,500,0),0,0,0);
	protected static Usable_Items	PAINT_JOB	= new Usable_Items(new Items("Paint Job",500,500,0),0,0,0);
}
