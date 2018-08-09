package cc.inversion.braingfx;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Priority;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.application.Application;

import cc.inversion.braingfx.lang.Parser;
import cc.inversion.braingfx.lang.CharCode;
import cc.inversion.braingfx.image.ImageHandler;
import cc.inversion.braingfx.util.Pointer;


public class BrainGFX extends Application
{
	private int ROW_COUNT = 32;
	private int COL_COUNT = 32;

	private int CELL_SIZE = 256;

	private int ZOOM = 12;

	private ImageHandler imageHandler;


	public static void main(final String[] args)
	{
		launch(args);
	}

	@Override
	public void start(final Stage primaryStage)
	{
		execCommandLineArgs();

		final WritableImage outputFrame = new WritableImage(ROW_COUNT*ZOOM, COL_COUNT*ZOOM);
		final PixelWriter pixelWriter = outputFrame.getPixelWriter();

		final ImageView imageView = new ImageView();
		imageView.setImage(outputFrame);

		final TextArea text_codeInput = new TextArea();
		text_codeInput.setWrapText(true);
		text_codeInput.setPrefSize(650, 650);

		final Button button_run = new Button("Run");
		button_run.setOnAction
			(action -> {
				executeCode(text_codeInput.getText());
			});

		final VBox root = new VBox(32);
		root.setPadding(new Insets(32, 32, 32, 32));
		root.setAlignment(Pos.BASELINE_CENTER);
		root.setFillWidth(false);
		root.getChildren().addAll(imageView, text_codeInput, button_run);

		final Scene scene = new Scene(root, 720, 640);
		primaryStage.setTitle("BrainGFX");
		primaryStage.setScene(scene);
		primaryStage.show();

		imageHandler = new ImageHandler(ROW_COUNT, COL_COUNT, ZOOM, pixelWriter);
	}

	private void execCommandLineArgs()
	{
		for(Map.Entry<String, String> entry : getParameters().getNamed().entrySet())
		{
			final int value = Integer.parseInt(entry.getValue());

			switch(entry.getKey())
			{
				case "W":
				case "width":
					ROW_COUNT = value;
					break;
				case "H":
				case "height":
					COL_COUNT = value;
					break;
				case "Z":
				case "zoom":
					ZOOM = value;
					break;
				case "S":
				case "cellsize":
					CELL_SIZE = value;
					break;
				default:
					throw new RuntimeException("Illegal argument");
			}
		}
	}

	private void executeCode(final String rawCode)
	{
		System.out.println("--------------");

		System.out.println("RAW CODE");
		System.out.println(rawCode);
		System.out.println();

		imageHandler.clearScreen();

		final List<CharCode> parsedCode = new Parser().parse(rawCode);
		final int grid[][] = new int[ROW_COUNT][COL_COUNT];

		final Pointer pointer = new Pointer(ROW_COUNT, COL_COUNT);

		int i = 0;
		while(i < parsedCode.size())
		{
			switch(parsedCode.get(i).getValue())
			{
				case '+':
					grid[pointer.x][pointer.y]++;
					if(grid[pointer.x][pointer.y] == CELL_SIZE)
					{
						grid[pointer.x][pointer.y] = 0;
					}
					break;
				case '-':
					grid[pointer.x][pointer.y]--;
					if(grid[pointer.x][pointer.y] == -1)
					{
						grid[pointer.x][pointer.y] = CELL_SIZE - 1;
					}
					break;
				case '.':
					imageHandler.colorPixel(pointer.x, pointer.y, grid[pointer.x][pointer.y]);
					break;
				case '<':
					pointer.actionOne();
					break;
				case '>':
					pointer.actionTwo();
					break;
				case '[':
					if(grid[pointer.x][pointer.y] == 0)
					{
						i = parsedCode.get(i).getPairedBracket();
					}
					break;
				case ']':
					if(grid[pointer.x][pointer.y] != 0)
					{
						i = parsedCode.get(i).getPairedBracket();
					}
					break;
				case '/':
					pointer.incrementMode();
					break;
				case '\\':
					pointer.decrementMode();
					break;
			}

			i++;
		}


		System.out.println("SUCCESS");
		System.out.println();

		System.out.println("CELL GRID");
		printCellMatrix(grid);
		System.out.println();

		System.out.println("POINTER LOCATION");
		System.out.println("ROW:" + pointer.y + ", COL:" + pointer.x);

		System.out.println("--------------");
	}

	private void printCellMatrix(final int[][] grid)
	{
		final int cellPadding = String.valueOf(CELL_SIZE - 1).length() + 1;

		for(int y = 0; y < COL_COUNT; y++)
		{
			for(int x = 0; x < ROW_COUNT; x++)
			{
				System.out.printf("%" + cellPadding + "d", grid[x][y]);
			}
			System.out.println("|");
		}
	}
}
