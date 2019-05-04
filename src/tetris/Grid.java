package tetris;


// Package
import java.util.Arrays;


public
class
Grid
{
	// Grid for Tetris Board
	public
	int[][]
	grid;


	/**
	 * Constructor for Tetris BoardPanel Grid.
	 * 
	 * @param rows of Tetris BoardPanel.
	 * @param columns of Tetris BoardPanel.
	 */
	public
	Grid(int rows,
		 int columns)
	{
		// In my application, new int[20][10]
		this.grid = new int[rows][columns];
		initializeGrid(rows,
					   columns);
	}


	/**
	 * Initializes the Grid. The first rows and
	 * last rows and columns are filled with BORDER constant.
	 * Otherwise, rest are filled with EMPTY constant.
	 * 
	 * @param rows total number of row for this Grid.
	 * @param columns total number of column for this Grid.
	 */
	public
	void
	initializeGrid(int rows,
				   int columns)
	{
		for (int row = 0; row < rows; row++)
		{
            for (int column = 0; column < TetrisConstants.COLUMNS; column++)
            {
            	// Sets Border for this Board
                if (column == 0 ||
                	column == TetrisConstants.COLUMNS - 1 ||
                	row == TetrisConstants.ROWS - 1)
                {
                    this.grid[row][column] = TetrisConstants.BORDER;
                }
                // Sets Empty position of this Grid.
                else
                {
                	this.grid[row][column] = TetrisConstants.EMPTY;
                }
            }
		}
	}


	/**
	 * Sets the entire row of this Grid as EMPTY.
	 * 
	 * @param row of this Grid.
	 */
	public
	void
	setLineEmpty(int row)
	{
		Arrays.fill(this.grid[row],
					TetrisConstants.EMPTY);
	}


	/**
	 * Returns true if this position is EMPTY.
	 * Otherwise, returns false.
	 * 
	 * @param row of Tetromino
	 * @param column of Tetromino
	 * @return
	 */
	public
	boolean
	isEmpty(int row,
			int column)
	{
		if (row < TetrisConstants.NO_MOVEMENT ||
			row >= TetrisConstants.ROWS ||
       	    column == TetrisConstants.NO_MOVEMENT ||
       	    column > TetrisConstants.COLUMNS)
		{
			return false;
		}

		if (this.grid[row][column] == TetrisConstants.BORDER)
		{
			return false;
		}
		return this.grid[row][column] == TetrisConstants.EMPTY;
	}


	/**
	 * Returns true if this Tetromino is rotatable.
	 * Otherwise, returns false.
	 * 
	 * @param tetromino of current shape.
	 * @param currentRow of this application.
	 * @param currentColumn of this application.
	 * @return
	 */
	public
	boolean
	isRotatable(Tetromino tetromino,
				int currentRow,
				int currentColumn)
	{
		// OShape is not rotatable.
		if (tetromino.getReference() == TetrisConstants.OReference)
		{
			return false;
		}

		int[][] locations = tetromino.rotatedPosition();
		for (int[] location : locations)
		{
			int
			newColumn = location[0] + currentColumn,
			newRow = location[1] + currentRow;
			if (!isEmpty(newRow,
						 newColumn))
			{
				return false;
			}
		}
		return true;
	}


	/**
	 * Returns true if all this locations are filled with EMPTY.
	 * Otherwise, returns false.
	 * 
	 * @param tetromino of current shape.
	 * @param currentRow of this application.
	 * @param currentColumn of this application.
	 * @return true if all this locations are filled with EMPTY.
	 *         Otherwise, returns false.
	 */
	public
	boolean
	isMovable(Tetromino tetromino,
			  int currentRow,
			  int currentColumn)
	{
		for (int[] location : tetromino.getPosition())
		{
			int
			newColumn = currentColumn + location[0],
			newRow = currentRow + location[1];
			if (!isEmpty(newRow,
						 newColumn))
			{
				return false;
			}
		}
		return true;
	}


	/**
	 * Sets input Tetromino into this Grid.
	 * 
	 * @param tetromino that will be updated in this Grid.
	 * @param currentRow of this Tetromino.
	 * @param currentColumn of this Tetromino.
	 */
	public
	void
	setTetromino(Tetromino tetromino,
				 int currentRow,
				 int currentColumn)
	{
		for (int[] row : tetromino.getPosition())
	    {
	      	int
	      	newRow = currentRow + row[1],
	      	newColumn = currentColumn + row[0];
	      	setColor(newRow,
	      			 newColumn,
	      			 tetromino.getReference());
	    }
	}


	public
	void
	setColor(int row,
			 int column,
			 int color)
	{
		if (this.grid[row][column] == TetrisConstants.EMPTY)
		{
			this.grid[row][column] = color;
		}
	}


	public
	int
	removeRows()
	{
		int count = 0;
		for (int row = 0; row < TetrisConstants.ROWS - 1; row++)
		{
			if (isRemovable(row))
			{
				count++;
				removeRow(row);				
			}
		}
		return count;
	}


	public
	void
	removeRow(int row)
	{
		setLineEmpty(row);
		for (int column = 0; column < TetrisConstants.COLUMNS; column++)
		{
			int buffer = row;
			while (buffer > 0)
			{
				this.grid[buffer][column] = this.grid[buffer - 1][column];
				buffer--;
			}
		}
	}


	public
	boolean
	isRemovable(int row)
	{
		for (int column = 1; column < TetrisConstants.COLUMNS - 1; column++)
		{
			if (this.grid[row][column] == TetrisConstants.EMPTY)
			{
				return false;
			}
		}
		return true;
	}


 	public
	int
	getColor(int row,
			 int column)
	{
		return this.grid[row][column];
	}


 	/**
 	 * Prints grid.
 	 * The purpose of this function is for debugging.
 	 */
	public
	void
	printGrid()
	{
		System.out.printf("this.grid.length = %d\n", this.grid.length);
		System.out.printf("this.grid[0].length = %d\n", this.grid[0].length);
		for (int i = 0; i < this.grid.length; i++)
		{
			for (int j = 0; j < this.grid[i].length; j++)
			{
				System.out.printf("%d ", this.grid[i][j]);
			}
			System.out.printf("\n");
		}
	}
}
