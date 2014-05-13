package motortrap;

import java.awt.*;

class Wall extends Bike
{
	protected Point		beg_pt;
	protected Point		end_pt;
	protected boolean	is_on;
	protected boolean	is_hologram;
	protected boolean	is_invisible;
	
	
	public Wall()
	{
		beg_pt = new Point(super.xpos,super.ypos);
		//beg_pt = null;
		end_pt = null;
		is_on = true;
		is_hologram = false;
		is_invisible = false;
	}
	
	
	public Wall(int x, int y)
	{
		beg_pt = new Point(x,y);
		end_pt = null;
		is_on = true;
		is_hologram = false;
		is_invisible = false;
	}
	
	
	public Wall(int x1, int y1, int x2, int y2)
	{
		beg_pt = new Point(x1,y1);
		end_pt = new Point(x2,y2);
		is_on = true;
		is_hologram = false;
		is_invisible = false;
	}
	
	
	public Wall(Point b, Point e)
	{
		beg_pt = b;
		end_pt = e;
		is_on = true;
		is_hologram = false;
		is_invisible = false;
	}
	
	
	public void set_end_pt(int x,int y)
	{
		end_pt = new Point(x,y);
	}
	
	
	@Override
	public void draw(Graphics g)
	{
		if(beg_pt != null && end_pt != null)
		{
			if((beg_pt.x < end_pt.x) || (beg_pt.y < end_pt.y))
			{
				g.fillRect(beg_pt.x - 1,beg_pt.y - 1,(end_pt.x - beg_pt.x + 3),(end_pt.y - beg_pt.y + 3));//E & S
			}
			else if((beg_pt.x > end_pt.x) || (beg_pt.y > end_pt.y))
			{
				g.fillRect(end_pt.x - 1,end_pt.y - 1,(beg_pt.x - end_pt.x + 3),(beg_pt.y - end_pt.y + 3));//W & N
			}
		}
	}
	
	
	public boolean is_horizontal()
	{
		if(beg_pt.y == end_pt.y)
		{
			return true;
		}
		return false;
	}
	
	
	public boolean is_vertical()
	{
		if(beg_pt.x == end_pt.x)
		{
			return true;
		}
		return false;
	}
	
	
	public int get_left()
	{
		if(is_horizontal() == true)
		{
			if(beg_pt.x < end_pt.x)
			{
				return beg_pt.x;
			}
			else
			{
				return end_pt.x;
			}
		}
		return -1;
	}
	
	
	public int get_right()
	{
		if(is_horizontal() == true)
		{
			if(beg_pt.x > end_pt.x)
			{
				return beg_pt.x;
			}
			else
			{
				return end_pt.x;
			}
		}
		return -1;
	}
	
	
	public int get_top()
	{
		if(is_vertical() == true)
		{
			if(beg_pt.y < end_pt.y)
			{
				return beg_pt.y;
			}
			else
			{
				return end_pt.y;
			}
		}
		return -1;
	}
	
	
	public int get_bottom()
	{
		if(is_vertical() == true)
		{
			if(beg_pt.y > end_pt.y)
			{
				return beg_pt.y;
			}
			else
			{
				return end_pt.y;
			}
		}
		return -1;
	}
}
