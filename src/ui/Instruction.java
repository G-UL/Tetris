package ui;


// Packages
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Color;


/**
 * Instructional Panel for this application.
 */
@SuppressWarnings("serial")
public
class
Instruction extends JPanel
{
	private
	JLabel
	lblControl,
	lblLeft,
	lblRight,
	lblTop,
	lblDown,
	lblSpace,
	lblPause,
	lblSpeedUp,
	lblSlowDown;


	// 260 * 640
	public
	Instruction()
	{
		setBackground(Color.BLACK);
		// Sets layout as absolute.
		setLayout(null);
		setBounds(0,
				  0,
				  260,
				  640);

		// Initializes Control Label.
		this.lblControl = new JLabel ("Control");
		this.lblControl.setFont(UIConstants.MEDIUM_FONT);
		this.lblControl.setForeground(UIConstants.WHITE);
		this.lblControl.setBounds(25,
								  150,
								  75,
								  30);
		this.add(lblControl);

		// Initializes Left Label.
		this.lblLeft = new JLabel ("← - Moves Left");
		this.lblLeft.setFont(UIConstants.SMALL_FONT);
		this.lblLeft.setForeground(UIConstants.WHITE);
		this.lblLeft.setBounds(50,
							   200,
							   150,
							   30);
		this.add(lblLeft);

		// Initializes Right Label.
		this.lblRight = new JLabel ("➝ - Moves Right");
		this.lblRight.setFont(UIConstants.SMALL_FONT);
		this.lblRight.setForeground(UIConstants.WHITE);
		this.lblRight.setBounds(50,
								250,
								150,
								30);
		this.add(lblRight);

		// Initializes Top Label.
		this.lblTop = new JLabel ("↑ - Rotates Tetromino");
		this.lblTop.setFont(UIConstants.SMALL_FONT);
		this.lblTop.setForeground(UIConstants.WHITE);
		this.lblTop.setBounds(50,
							  300,
							  200,
							  30);
		this.add(lblTop);

		// Initializes Down Label.
		this.lblDown = new JLabel ("↓ - Slow Fall");
		this.lblDown.setFont(UIConstants.SMALL_FONT);
		this.lblDown.setForeground(UIConstants.WHITE);
		this.lblDown.setBounds(50,
							   350,
							   150,
							   30);
		this.add(lblDown);

		// Initializes Space Label.
		this.lblSpace = new JLabel ("Spacebar - Free Fall");
		this.lblSpace.setFont(UIConstants.SMALL_FONT);
		this.lblSpace.setForeground(UIConstants.WHITE);
		this.lblSpace.setBounds(50,
								400,
								150,
								30);
		this.add(lblSpace);

		// Initializes Pause Label.
		this.lblPause = new JLabel ("P - Pauses the Game");
		this.lblPause.setFont(UIConstants.SMALL_FONT);
		this.lblPause.setForeground(UIConstants.WHITE);
		this.lblPause.setBounds(50,
								450,
								200,
								30);
		this.add(lblPause);

		// Initializes Speed Up Label.
		this.lblSpeedUp = new JLabel ("E - Speed Up");
		this.lblSpeedUp.setFont(UIConstants.SMALL_FONT);
		this.lblSpeedUp.setForeground(UIConstants.WHITE);
		this.lblSpeedUp.setBounds(50,
								  500,
								  150,
								  30);
		this.add(lblSpeedUp);

		// Initializes Slow Down Label.
		this.lblSlowDown = new JLabel ("W - Slow Down");
		this.lblSlowDown.setFont(UIConstants.SMALL_FONT);
		this.lblSlowDown.setForeground(UIConstants.WHITE);
		this.lblSlowDown.setBounds(50,
								   550,
								   150,
								   30);
		this.add(lblSlowDown);
	}
}
