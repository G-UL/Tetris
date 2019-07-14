package ui;


// Packages
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.RenderingHints;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.util.Random;


// Project
import tetris.Grid;
import tetris.TetrisConstants;
import tetris.Tetromino;
import tetris.TetrominoFactory;
import ui.UIConstants;


@SuppressWarnings("serial")
public
class
Tetris extends JPanel
	   implements Runnable
{
	// Random int generator from 0 to 6 (inclusive).
	private
	static
	final
	Random
	random = new Random();


	// States of this application.
	private
	boolean
	isGameOver,
	// Used as a lock.
	isPaused,
	isStarted;


	private
	int
	// State of current Tetromino.
	currentRow,
	currentColumn,
	// State of this application.
	level,
	lines,
	score;


	// Single thread for this application
	Thread
	thread;


	// Tetrominoes used in this application.
	Tetromino
	currentTetromino,
	nextTetromino;


	// Grid/Board for this application.
	private
	Grid
	grid;


	// Shows client's status in this application.
	private
	String
	labelLevel = "Level:          ",
	labelLine = "Lines:          ",
	labelScore = "Score:          ";
	

	// Labels that hold client's status.
	private
	JLabel
	lblStats,
	lblLevel,
	lblLine,
	lblScore;


	public
	Tetris()
	{
		setPreferredSize(UIConstants.TETRIS_DIMENSION);
		setBackground(UIConstants.BLACK);
		setFocusable(true);

		// Initializes the Grid.
		this.grid = new Grid(TetrisConstants.ROWS,
							 TetrisConstants.COLUMNS);
		getNextTetromino();
		setLayout(null);

		// Client's Stats
		this.lblStats = new JLabel ("Stats");
		this.lblStats.setBounds(400,
								350,
								45,
								25);
		this.lblStats.setFont(UIConstants.MEDIUM_FONT);
		this.lblStats.setForeground(UIConstants.WHITE);
		add(this.lblStats);
		
		this.lblLevel = new JLabel (this.labelLevel + this.level);
		this.lblLevel.setBounds(400,
								400,
								200,
								20);
		this.lblLevel.setFont(UIConstants.SMALL_FONT);
		this.lblLevel.setForeground(UIConstants.WHITE);
		add(this.lblLevel);

		this.lblLine = new JLabel (this.labelLine + this.lines);
		this.lblLine.setBounds(400,
							   450,
							   200,
							   20);
		this.lblLine.setFont(UIConstants.SMALL_FONT);
		this.lblLine.setForeground(UIConstants.WHITE);
		add(this.lblLine);

		this.lblScore = new JLabel (this.labelScore + this.score);
		this.lblScore.setBounds(400,
								500,
								200,
								20);
		this.lblScore.setFont(UIConstants.SMALL_FONT);
		this.lblScore.setForeground(UIConstants.WHITE);
		add(this.lblScore);

		// Update Speed that refers to map Level to Speed.
		UIConstants.updateSpeed();

		// Adds custom KeyListner called KeyboardHandler.
		addKeyListener(new KeyboardHandler ());
	}


	/**
	 * Selects the next Tetromino and
	 * updates current Tetromino, resets currentRow,
	 * and currentColumn.
	 */
	public
	void
	getNextTetromino()
	{
		this.currentRow = TetrisConstants.INITIAL_ROW;
		this.currentColumn = TetrisConstants.INITIAL_COLUMN;
		this.currentTetromino = this.nextTetromino;
		// Each tetromino is connected to its own unique reference.
		int reference = random.nextInt(TetrisConstants.TETROMINOES.length);
		this.nextTetromino = TetrominoFactory.createTetromino(reference);
	}


	/**
	 * Launches this application.
	 */
	public
	void
	launch()
	{
		// Makes sure that thread is interrupted.
		stop();
		// Updates Tetrominoes.
		getNextTetromino();
		// Resets Stats.
		resetStats();
		// Initializes the Grid.
		this.grid = new Grid(TetrisConstants.ROWS,
							 TetrisConstants.COLUMNS);
		// Toggles isStarted state.
		this.setStarted(!this.isStarted);
		// Initializes thread.
		this.thread = new Thread(this);
		this.thread.start();
	}


	/**
	 * Sets thread's interrupt flag to be true
	 * and sets thread to be null.
	 */
	public
	void
	stop()
	{
		if (this.thread != null)
		{
			Thread holder = this.thread;
			this.thread = null;
			holder.interrupt();
		}
	}


	/**
	 * Resets Stats for client.
	 */
	public
	void
	resetStats()
	{
		this.level = this.lines = this.score = 0;
		this.isGameOver = this.isPaused = this.isStarted = false;
	}


	@Override
	public
	void
	run() 
	{
		while (Thread.currentThread() == this.thread)
		{
			// Usage for pausing this application.
			synchronized (this.thread)
			{
				// If client hits paused buttom,
				// waits for next paused buttom
				// to resume application.
				while (this.isPaused)
				{
					try
					{
						this.thread.wait();
					}
					catch (InterruptedException ex)
					{
						ex.printStackTrace();
						return ;
					}
				}
			}

			try
			{
				// Determines the speed of current Tetromino's drop down.
				int speed = UIConstants.SPEED.get(this.level);
				Thread.sleep(speed);
			}
			catch (InterruptedException ex)
			{
				ex.printStackTrace();
				return ;
			}

			if (!this.isGameOver)
			{
				if (this.grid.isMovable(this.currentTetromino,
										this.currentRow + TetrisConstants.DOWNWARD,
										this.currentColumn))
				{
					updateCurrentPosition(TetrisConstants.DOWNWARD,
										  TetrisConstants.NO_MOVEMENT);
				}
				else
				{
					hitsBottom();
				}
				repaint();
			}
		}
	}


	public
	void
	drawStartScreen(Graphics2D graphics)
	{
		graphics.setFont(UIConstants.HUGE_FONT);
		graphics.setColor(UIConstants.RED);
		graphics.fill(UIConstants.TITLE_RECTANGLE);
		graphics.fill(UIConstants.INIT_RECTANGLE);
		graphics.setColor(UIConstants.WHITE);
		graphics.drawString("Tetris",
							UIConstants.TITLE_TEXT_X,
							UIConstants.TITLE_TEXT_Y);
		graphics.setFont(UIConstants.MEDIUM_FONT);
		graphics.drawString("Press Enter to Start",
							UIConstants.INIT_TEXT_X,
							UIConstants.INIT_TEXT_Y);
	}


	public
	void
	drawPauseScreen(Graphics2D graphics)
	{
		graphics.setFont(UIConstants.HUGE_FONT);
		graphics.setColor(UIConstants.RED);
		graphics.fill(UIConstants.TITLE_RECTANGLE);
		graphics.setColor(UIConstants.WHITE);
		graphics.drawString("Paused",
							UIConstants.PAUSE_TEXT_X,
							UIConstants.PAUSE_TEXT_Y);
	}


	public
	void
	drawBlock(Graphics2D graphics,
			  int color,
			  int row,
			  int column)
	{
		if (color == TetrisConstants.EMPTY)
		{
			graphics.setColor(UIConstants.BLACK);
		}
		else
		{
			graphics.setColor(UIConstants.TETROMINO_COLOR[color]);
		}

		int
		xCoordinate = column * UIConstants.BLOCK_SIZE + UIConstants.MARGIN_LEFT,
		yCoordinate = row * UIConstants.BLOCK_SIZE + UIConstants.MARGIN_TOP;
		graphics.fillRect(xCoordinate,
						  yCoordinate,
						  UIConstants.BLOCK_SIZE,
						  UIConstants.BLOCK_SIZE);
		graphics.setColor(UIConstants.WHITE);
		graphics.drawRect(xCoordinate,
						  yCoordinate,
						  UIConstants.BLOCK_SIZE,
						  UIConstants.BLOCK_SIZE);
	}


	void
	drawPlayScreent(Graphics2D graphics)
	{
		// grid background
		graphics.setColor(UIConstants.BLACK);
		graphics.fill(UIConstants.GRID_RECTANGLE);

		// the blocks dropped in the grid
		for (int row = 0; row < TetrisConstants.ROWS; row++)
		{
			for (int column = 0; column < TetrisConstants.COLUMNS; column++)
			{
				int color = this.grid.getColor(row,
											   column);
				if (color >= TetrisConstants.EMPTY)
				{
					drawBlock(graphics,
							  color,
							  row,
							  column);
				}
			}
		}

		// the borders of grid and preview panel
		graphics.setColor(UIConstants.WHITE);
		graphics.draw(UIConstants.GRID_RECTANGLE);
		graphics.draw(UIConstants.PREVIEW_RECTANGLE);

		/* Reference
		 * https://docs.oracle.com/javase/8/docs/api/java/awt/Graphics.html#translate-int-int-
		 * 
		 * Translates the origin of the graphics context to the point (x, y)
		 * in the current coordinate system.
		 * 
		 * Modifies this graphics context so that its new origin corresponds to
		 * the point (x, y) in this graphics context's original coordinate system.
		 * 
		 * All coordinates used in subsequent rendering operations on this graphics
		 * context will be relative to this new origin.
		 */

		// Preview
		int
		newX = UIConstants.PREVIEW_NEW_X,
		newY = UIConstants.PREVIEW_NEW_Y;

		graphics.translate(newX,
						   newY);
		for (int[] row : this.nextTetromino.getShape())
		{
			drawBlock(graphics,
					  this.nextTetromino.getReference(),
					  row[1],
					  row[0]);
		}
		graphics.translate(-newX,
						   -newY);
	}


	public
	void
	drawCurrentTetromino(Graphics2D graphics)
	{
		for (int[] row : this.currentTetromino.getPosition())
		{
			drawBlock(graphics,
					  this.currentTetromino.getReference(),
					  this.currentRow + row[1],
					  this.currentColumn + row[0]);
		}
	}


	@Override
	public
	void
	paintComponent(Graphics graphic)
	{
		super.paintComponent(graphic);
		Graphics2D graphics = (Graphics2D) graphic;
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
								 RenderingHints.VALUE_ANTIALIAS_ON);

		drawPlayScreent(graphics);
		// Draws Initial Screen.
		if (!this.isStarted || this.isGameOver)
		{
			drawStartScreen(graphics);
		}
		// Draws Paused Screen.
		else if (this.isPaused)
		{
			drawPauseScreen(graphics);
		}
		// Draws Current Tetromino on the Grid.
		else
		{
			drawCurrentTetromino(graphics);
		}
	}


	/**
	 * Updates the number of lines removed and
	 * scores based on the line count.
	 * 
	 * @param line removed from Grid.
	 */
	public
	void
	updateLines(int line)
	{
		setLines(line);
		setScore(line * 10);
	}


	/**
	 * Updates current position of current Tetromino.
	 * 
	 * @param row of current position.
	 * @param column of current position.
	 */
	public
	void
	updateCurrentPosition(int row,
						  int column)
	{
		this.currentRow += row;
		this.currentColumn += column;
	}


	/**
	 * Free fall of current Tetromino when client hits space bar.
	 */
	public
	void
	freeFall()
	{
		while (this.grid.isMovable(this.currentTetromino,
								   this.currentRow + TetrisConstants.DOWNWARD,
								   this.currentColumn))
		{
			updateCurrentPosition(TetrisConstants.DOWNWARD,
								  TetrisConstants.NO_MOVEMENT);
		}
		hitsBottom();
	}


	/**
	 * Manages speed of this application when client's
	 * presses corresponding keyboard.
	 * 
	 * @param flag if it is true, increases the speed.
	 * 		  Otherwise, decreases the speed.
	 * 
	 * @return speed of this appilcation.
	 */
	public
	int
	manageSpeed(boolean flag)
	{
		if (flag)
		{
			// Maximum Speed
			if (this.level == UIConstants.MAX_SPEED)
			{
				return UIConstants.SPEED.get(this.level);
			}
			else
			{
				setLevel(getLevel() + 1);
				return UIConstants.SPEED.get(this.level);
			}
		}
		else
		{
			// Minimum Speed
			if (this.level == UIConstants.MIN_SPEED)
			{
				return UIConstants.SPEED.get(this.level);
			}
			else
			{
				setLevel(getLevel() - 1);
				return UIConstants.SPEED.get(this.level);
			}
		}
	}


	/**
	 * Since Tetromino hits the bottom of the grid,
	 * sets the Tetromino in the Grid and
	 * updates stats, and current/next Tetrominoes.
	 */
	public
	void
	hitsBottom()
	{
		this.grid.setTetromino(this.currentTetromino,
							   this.currentRow,
							   this.currentColumn);

		// Since current Tetromino is not movable,
		// it refers to the game over.
		if (this.currentRow < 2)
		{
			this.setGameOver(!this.isGameOver);
			stop();
		}
		// Otherwise, updates stats.
		else
		{
			int line = this.grid.removeRows();
			updateLines(line);
		}
		// Then, updates current/next Tetrominoes.
		getNextTetromino();
	}


	/**
	 * Pauses this application and toggles
	 * the isPaused flag.
	 */
	public
	void
	pause()
	{
		if (this.isGameOver || !isStarted)
		{
			return ;
		}

		setPaused(!this.isPaused);
	}


	/**
	 * Custom class that extends from KeyAdapter.
	 * This class manages all the keyboard input from client.
	 */
	public
	class
	KeyboardHandler extends KeyAdapter
	{
		/**
		 * Manages the keyboard input from client.
		 * This is the keyboard event driven application.
		 * 
		 * @param keyEvent keyboard input from client.
		 */
		@Override
		public
		void
		keyPressed(KeyEvent keyEvent)
		{
			switch (keyEvent.getKeyCode())
			{
				// Starts the application.
				case UIConstants.ENTER:

					if (!isStarted || isGameOver)
					{
						launch();
					}
					break;

				// Pauses the game.
				case UIConstants.PAUSE:

					pause();
					if (!isPaused)
					{
						synchronized (thread)
						{
							thread.notify();
						}
					}
					break;

				// Rotates current Tetromino.
				case UIConstants.UP:

					if (isGameOver) return ;

					if (grid.isRotatable(currentTetromino,
										 currentRow,
										 currentColumn))
					{
						currentTetromino.rotate();
					}
					break;

				// Single line down.
				case UIConstants.DOWN:

					if (isGameOver) return ;

					if (grid.isMovable(currentTetromino,
									   currentRow + TetrisConstants.DOWNWARD,
									   currentColumn))
					{
						updateCurrentPosition(TetrisConstants.DOWNWARD,
											  TetrisConstants.NO_MOVEMENT);
					}
					break;

				// Free Fall of current Tetromino.
				case UIConstants.SPACEBAR:

					if (isGameOver) return ;

					freeFall();
					break;

				// Moves leftward of current Tetromino.
				case UIConstants.LEFT:

					if (isGameOver) return ;

					if (grid.isMovable(currentTetromino,
									   currentRow,
									   currentColumn + TetrisConstants.LEFTWARD))
					{
						updateCurrentPosition(TetrisConstants.NO_MOVEMENT,
											  TetrisConstants.LEFTWARD);
					}
					break;

				// Moves rightward of current Tetromino.
				case UIConstants.RIGHT:

					if (isGameOver) return ;

					if (grid.isMovable(currentTetromino,
									   currentRow,
									   currentColumn + TetrisConstants.RIGHTWARD))
					{
						updateCurrentPosition(TetrisConstants.NO_MOVEMENT,
											  TetrisConstants.RIGHTWARD);
					}
					break;

				// Speeds up the dropping speed.
				case UIConstants.SPEEDUP:

					if (isGameOver) return ;

					manageSpeed(true);
					break;

				// Slows down the dropping speed.
				case UIConstants.SLOWDOWN:

					if (isGameOver) return ;

					manageSpeed(false);
					break;
			}
			repaint();
		}
	}


/*****************************************************************************
 ****************************  Getters & Setters  ****************************
 **************************  Some of Unique Setters  *************************
 *****************************************************************************/


	/**
	 * @return the level
	 */
	public
	int
	getLevel()
	{
		return this.level;
	}


	/**
	 * @param level the level to set
	 */
	public
	void
	setLevel(int level)
	{
		this.level = level;
		// Updates JLabel.
		this.lblLevel.setText(this.labelLevel + this.level);
	}


	/**
	 * @return the lines
	 */
	public
	int
	getLines()
	{
		return this.lines;
	}


	/**
	 * @param lines the lines to set
	 */
	public
	void
	setLines(int lines)
	{
		this.lines += lines;
		// Updates JLabel.
		this.lblLine.setText(this.labelLine + this.lines);
	}


	/**
	 * @return the score
	 */
	public
	int
	getScore()
	{
		return this.score;
	}


	/**
	 * @param score the score to set
	 */
	public
	void
	setScore(int score)
	{
		this.score += score;
		// Updates JLabel.
		this.lblScore.setText(this.labelScore + this.score);
	}


	/**
	 * @return the isGameOver
	 */
	public
	boolean
	getGameOver()
	{
		return this.isGameOver;
	}


	/**
	 * @param isGameOver the isGameOver to set
	 */
	public
	void
	setGameOver(boolean isGameOver)
	{
		this.isGameOver = isGameOver;
	}


	/**
	 * @return the isPaused
	 */
	public
	boolean
	getPaused()
	{
		return this.isPaused;
	}


	/**
	 * @param isPaused the isPaused to set
	 */
	public
	void
	setPaused(boolean isPaused)
	{
		this.isPaused = isPaused;
	}


	/**
	 * @return the isStarted
	 */
	public
	boolean
	getStarted()
	{
		return this.isStarted;
	}


	/**
	 * @param isStarted the isStarted to set
	 */
	public
	void
	setStarted(boolean isStarted)
	{
		this.isStarted = isStarted;
	}
}
