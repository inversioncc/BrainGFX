package cc.inversion.braingfx.util;


public class Pointer
{
	public int x;
	public int y;

	private int mode;

	private final int WIDTH;
	private final int HEIGHT;


	public Pointer(final int WIDTH, final int HEIGHT)
	{
		this.WIDTH = WIDTH;
		this.HEIGHT = HEIGHT;

    mode = 0;
	}

	public void incrementMode()
	{
		mode++;
		if(mode == 4)
    {
      mode = 0;
    }
	}

	public void decrementMode()
	{
		mode--;
		if(mode == -1)
    {
      mode = 3;
    }
	}

	public void actionOne()
	{
		switch(mode)
		{
			case 0:
				moveLeft();
				break;
			case 1:
				moveUp();
				break;
			case 2:
				moveRight();
				break;
			case 3:
				moveDown();
				break;
		}
	}

	public void actionTwo()
	{
		switch(mode)
		{
			case 0:
				moveRight();
				break;
			case 1:
				moveDown();
				break;
			case 2:
				moveLeft();
				break;
			case 3:
				moveUp();
				break;
		}
	}

	private void moveLeft()
	{
		x -= 1;
		if(x == -1)
		{
			x = WIDTH - 1;
		}
	}

	private void moveRight()
	{
		x += 1;
		if(x == WIDTH)
		{
			x = 0;
		}
	}

	private void moveUp()
	{
		y -= 1;
		if(y == -1)
		{
			y = HEIGHT - 1;
		}
	}

	private void moveDown()
	{
		y += 1;
		if(y == HEIGHT)
		{
			y = 0;
		}
	}
}