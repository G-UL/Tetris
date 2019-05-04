package ui;


//Packages
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.Font;
import java.awt.Rectangle;
import java.util.HashMap;
import java.util.Map;


public
final
class
UIConstants
{
	// Color for the Application
	public
    final
    static
    Color
    // For IShape
    RED = Color.red,
    // For JShape
    BLUE = Color.blue,
    // For LShape
    ORANGE = Color.orange,
    // For OShape
    YELLOW = Color.yellow,
    // For SShape
    MAGENTA = Color.magenta,
    // For TShape
    CYAN = Color.cyan,
    // For ZShape
    GREEN = Color.green,
    // For Board
    BLACK = Color.black,
    // For Strings
    WHITE = Color.white;


	// List of Colors for Tetrominoes
	public
	static
	final
	Color[]
	TETROMINO_COLOR = {RED,
			      	   BLUE,
			      	   ORANGE,
			      	   YELLOW,
			      	   MAGENTA,
			      	   CYAN,
			      	   GREEN};


    // For Client KeyBoard Input
	public
    static
    final
    int
    UP = KeyEvent.VK_UP,
    DOWN = KeyEvent.VK_DOWN,
    LEFT = KeyEvent.VK_LEFT,
    RIGHT = KeyEvent.VK_RIGHT,
    SPACEBAR = KeyEvent.VK_SPACE,
    ENTER = KeyEvent.VK_ENTER,
    PAUSE = KeyEvent.VK_P,
    SPEEDUP = KeyEvent.VK_E,
    SLOWDOWN = KeyEvent.VK_W;


	// Fonts for this Application
	public
    static
    final
    Font
    HUGE_FONT = new Font("Calibri",
    				     Font.BOLD,
    				     48),
    BIG_FONT = HUGE_FONT.deriveFont(Font.BOLD,
		   	 					    24),
    MEDIUM_FONT = HUGE_FONT.deriveFont(Font.BOLD,
    								   18),
    SMALL_FONT = HUGE_FONT.deriveFont(Font.BOLD,
    								  14);

 
	public
    static
    final
    Dimension
    // Total size of this application.
    FULL_DIMENSION = new Dimension(880,
    							   640),
    // Size of Tetris board.
    TETRIS_DIMENSION = new Dimension(640,
						   	  		 640);
 
	public
    static
    final
    Rectangle
    GRID_RECTANGLE = new Rectangle(50,
    						 	   50,
    						 	   300,
    						 	   510),
    INIT_RECTANGLE = new Rectangle(50,
    							   450,
    							   300,
    							   50),
    PREVIEW_RECTANGLE = new Rectangle(400,
    								  50,
    								  200,
    								  200),
    TITLE_RECTANGLE = new Rectangle(50,
    								150,
    								300,
    								100);

    public
    static
    final
    int
    BLOCK_SIZE = 30,
    INIT_TEXT_X = 110,
    INIT_TEXT_Y = 480,
    MARGIN_LEFT = 20,
    MARGIN_TOP = 50,
    MAX_SPEED = 8,
    MIN_SPEED = 0,
    PAUSE_TEXT_X = 115,
    PAUSE_TEXT_Y = 220,
    PREVIEW_NEW_X = 470,
    PREVIEW_NEW_Y = 80,
    PREVIEW_X = 450,
    PREVIEW_Y = 100,
	TITLE_TEXT_X = 125,
	TITLE_TEXT_Y = 220;


    /**
     * Key := Level
     * Value := Sleep interval for Thread
     */
    public
    static
    final
    Map<Integer, Integer>
    SPEED = new HashMap<Integer, Integer> ();


    // Initializes the above map.
    public
    static
    void
    updateSpeed()
    {
    	int minSpeed = 900;
    	for (int i = 0; i <= MAX_SPEED; i++)
    	{
    		SPEED.put(i,
    				  minSpeed);
    		minSpeed -= 100;
    	}
    }
}
