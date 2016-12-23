package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class FileExplorer {

	private JFrame frmExchallenge;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FileExplorer window = new FileExplorer();
					window.frmExchallenge.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FileExplorer() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmExchallenge = new JFrame();
		frmExchallenge.setTitle("ExChallenge");
		frmExchallenge.setBounds(100, 100, 450, 300);
		frmExchallenge.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
