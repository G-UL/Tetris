package tetris;


/**
 * Reference
 * https://en.wikipedia.org/wiki/Tetromino
 */
public
abstract
class
Tetromino
{
	// Shape of this Tetromino.
	private
	int[][]
	position,
	shape;


	// Int that represents this Tetromino
	private
	int
	reference;


	/**
	 * Abstract constructor for 7 Tetrominoes.
	 * This abstract accepts shape of 2d array and
	 * color of this Tetromino.
	 * 
	 * Reference
	 * https://en.wikipedia.org/wiki/Tetromino
	 * 
	 * Checks out TetrisConstants file.
	 * 
	 * @param shape 2d array of this Tetromino.
	 * @param color of this Tetromino.
	 */
	public
	Tetromino(int[][] shape,
			  int reference)
	{
		this.shape = shape;
		this.reference = reference;
		this.position = cloneShape();
	}


	/**
	 * Returns the cloned shape from this.shape.
	 * 
	 * @return the cloned shape from this.shape.
	 */
	public
	int[][]
	cloneShape()
	{
		int[][] result = new int[4][2];
		for (int i = 0; i < this.shape.length; i++)
		{
			result[i] = this.shape[i].clone();
		}
		return result;
	}


	public
	int[][]
	clonePosition()
	{
		int[][] result = new int[4][2];
		for (int i = 0; i < this.position.length; i++)
		{
			result[i] = this.position[i].clone();
		}
		return result;		
	}


	/**
	 * The Counter-Clockwise Rotation
	 * 
	 * R(𝛳) =  cos(𝛳)  sin(𝛳)
	 *        -sin(𝛳)  cos(𝛳)
	 *
	 * R(-90) =  cos(-90) sin(-90)
	 *          -sin(-90) cos(-90)
	 * 
	 * For 90 degree counter clockwise rotation
	 * 
	 * x'    cos(-90)  sin(-90) | x
	 * y' = -sin(-90)  cos(-90) | y
	 *
	 * x'   xcos(-90) - ysin(-90)
	 * y' = xsin(-90) - ycos(-90)
	 * 
	 *       0   1 | x
	 *    = -1   0 | y
	 *
	 * x' =  y
	 * y' = -x 
	 *  
	 * Reference
	 * https://en.wikipedia.org/wiki/Rotation_matrix
	 * 
	 * Rotates counter-clockwise of this Tetromino.
	 * 
	 * @return the rotated shape of matrix
	 */
	public
	int[][]
	rotatedPosition()
	{
		 int[][] result = clonePosition();
		for (int[] row : result)
		{
			int buffer = row[0];
			row[0] = row[1];
			row[1] = -buffer;
		}
		return result;
	}


	public
	void
	rotate()
	{
		for (int[] row : this.position)
		{
			int buffer = row[0];
			row[0] = row[1];
			row[1] = -buffer;
		}
	}


	public
	void
	printPosition()
	{
		for (int i = 0; i < this.position.length; i++)
		{
			System.out.printf("(%d, %d) ",
							  this.position[i][0],
							  this.position[i][1]);
		}
		System.out.printf("\n");
	}


/*****************************************************************************
 ****************************  Getters & Setters  ****************************
 *****************************************************************************/


	/**
	 * @return the position
	 */
	public
	int[][]
	getPosition()
	{
		return this.position;
	}


	/**
	 * @param position the position to set
	 */
	public
	void
	setPosition(int[][] position)
	{
		this.position = position;
	}


	/**
	 * @return the shape represented in the matrix.
	 */
	public
	int[][]
	getShape()
	{
		return this.shape;
	}


	/**
	 * @param shape the shape to set.
	 */
	public
	void
	setShape(int[][] shape)
	{
		this.shape = shape;
	}


	/**
	 * @return the reference
	 */
	public
	int
	getReference()
	{
		return this.reference;
	}


	/**
	 * @param reference the reference to set
	 */
	public
	void
	setReference(int reference)
	{
		this.reference = reference;
	}
}
