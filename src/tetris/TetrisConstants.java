package tetris;


public
final
class
TetrisConstants
{
	// For Grid
	public
	static
	final
	int
	BORDER = -2,
	COLUMNS = 12,
	DOWNWARD = 1,
	EMPTY = -1,
	INITIAL_COLUMN = 6,
    INITIAL_ROW = 1,
    LEFTWARD = -1,
    NO_MOVEMENT = 0,
    RIGHTWARD = 1,
	ROWS = 18;


	// Tetrominoes
	public
	static
	int[][]
	ISHAPE = {{-1, -1},
			  {-1, 0},
			  {-1, 1},
			  {-1, 2}},
	JSHAPE = {{-1, -1},
			  {-1, 0},
			  {-1, 1},
			  {0, 1}},
	LSHAPE = {{0, -1},
			  {-1, -1},
			  {-1, 0},
			  {-1, 1}},
	OSHAPE = {{0, 0},
			  {0, -1},
			  {-1, 0},
			  {-1, -1}},
	SSHAPE = {{-1, 0},
			  {0, 0},
			  {0, 1},
			  {-1, -1}},
	TSHAPE = {{-1, -1},
			  {-1, 0},
			  {-1, 1},
			  {0, 0}},
	ZSHAPE = {{0, -1},
			  {0, 0},
			  {-1, 0},
			  {-1, 1}};


	// List of Tetrominoes
	public
	final
	static
	int[][][]
	TETROMINOES = {ISHAPE,
				   JSHAPE,
				   LSHAPE,
				   OSHAPE,
				   SSHAPE,
				   TSHAPE,
				   ZSHAPE};


	// Unique Reference for each Tetromino
	public
	final
	static
	int
	IReference = 0,
	JReference = 1,
	LReference = 2,
	OReference = 3,
	SReference = 4,
	TReference = 5,
	ZReference = 6;
}
