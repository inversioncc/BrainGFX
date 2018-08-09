package cc.inversion.braingfx.image;

import javafx.scene.image.PixelWriter;


public class ImageHandler
{
	private final int ROW_COUNT;
	private final int COL_COUNT;

	private final int ZOOM;

	private final PixelWriter pixelWriter;


	public ImageHandler(final int ROW_COUNT, final int COL_COUNT, final int ZOOM, final PixelWriter pixelWriter)
	{
		this.ROW_COUNT = ROW_COUNT;
		this.COL_COUNT = COL_COUNT;

		this.ZOOM = ZOOM;

		this.pixelWriter = pixelWriter;

		clearScreen();
	}

	public void clearScreen()
	{
		for(int x = 0; x < ROW_COUNT*ZOOM; x++)
		{
			for(int y = 0; y < COL_COUNT*ZOOM; y++)
			{
				pixelWriter.setArgb(x, y, 0xFF000000);
			}
		}
	}

	public void colorPixel(final int x, final int y, final int pixel)				// decode the 3-bit color pixel
	{
		int pixelColor;

		switch(pixel)
		{
			case 0:
				pixelColor = 0xFF000000;
				break;
			case 1:
				pixelColor = 0xFF0000FF;
				break;
			case 2:
				pixelColor = 0xFF00FF00;
				break;
			case 3:
				pixelColor = 0xFF00FFFF;
				break;
			case 4:
				pixelColor = 0xFFFF0000;
				break;
			case 5:
				pixelColor = 0xFFFF00FF;
				break;
			case 6:
				pixelColor = 0xFFFFFF00;
				break;
      case 7:
        pixelColor = 0xFFFFFFFF;
        break;
			default:
				pixelColor = 0xFFFFFFFF;
				break;
		}

		for(int i = x*ZOOM; i < (x + 1)*ZOOM; i++)
		{
			for(int j = y*ZOOM; j < (y + 1)*ZOOM; j++)
			{
				pixelWriter.setArgb(i, j, pixelColor);
			}
		}
	}
}