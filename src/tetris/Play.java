package tetris;


// Packages
import java.awt.BorderLayout;
import javax.swing.JFrame;


// Projects
import ui.Instruction;
import ui.Tetris;
import ui.UIConstants;


/**
 * Main executor for this application.
 */
public
class
Play
{
	public
	static
	void
	main(String[] args)
	{
		// Initializes the JFrame.
    	JFrame jFrame = new JFrame();
    	jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	jFrame.setTitle("Tetris");
    	jFrame.setResizable(false);
    	jFrame.setPreferredSize(UIConstants.FULL_DIMENSION);
    	// Initializes the Instructional Panel.
		Instruction instruction = new Instruction ();
		jFrame.add(instruction);
		// Initializes the Tetris Panel.
		Tetris tetris = new Tetris ();
		jFrame.add(tetris,
				   BorderLayout.EAST);
		jFrame.pack();
		jFrame.setLocationRelativeTo(null);
		jFrame.setVisible(true);
	}
}
