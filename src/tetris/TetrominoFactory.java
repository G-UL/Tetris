package tetris;


public
class
TetrominoFactory
{
	/**
	 * Returns Tetromino based on the given input reference.
	 * 
	 * @param reference is a given input from random int generator that:
	 * 		  	0 <=> IShape,
	 *			1 <=> JShape,
	 *			2 <=> LShape,
	 *			3 <=> OShape,
	 *			4 <=> SShape,
	 *			5 <=> TShape,
	 *			6 <=> ZShape.
	 * @return Tetromino based on the given input reference.
	 * @pre reference must be in between 0 (inclusive) and 6 (inclusive).
	 */
	public
	static
	Tetromino
	createTetromino(int reference)
	{
		Tetromino result = null;
		if (reference == TetrisConstants.IReference)
		{
			result = new IShape (TetrisConstants.TETROMINOES[reference],
								 reference);
		}
		else if (reference == TetrisConstants.JReference)
		{
			result = new JShape (TetrisConstants.TETROMINOES[reference],
								 reference);
		}
		else if (reference == TetrisConstants.LReference)
		{
			result = new LShape (TetrisConstants.TETROMINOES[reference],
								 reference);
		}
		else if (reference == TetrisConstants.OReference)
		{
			result = new OShape (TetrisConstants.TETROMINOES[reference],
								 reference);
		}
		else if (reference == TetrisConstants.SReference)
		{
			result = new SShape (TetrisConstants.TETROMINOES[reference],
								 reference);
		}
		else if (reference == TetrisConstants.TReference)
		{
			result = new TShape (TetrisConstants.TETROMINOES[reference],
								 reference);
		}
		else
		{
			// reference == TetrisConstants.ZReference
			result = new ZShape (TetrisConstants.TETROMINOES[reference],
								 reference);
		}
		return result;
	}
}
