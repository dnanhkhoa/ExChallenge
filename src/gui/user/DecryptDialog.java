package gui.user;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JDialog;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JButton;

public class DecryptDialog extends JDialog {
	private JTextField textField_1;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DecryptDialog dialog = new DecryptDialog();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public DecryptDialog() {
		getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 14));
		setTitle("Login");
		setModal(true);
		setFont(new Font("Dialog", Font.PLAIN, 14));
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,},
			new RowSpec[] {
				RowSpec.decode("4dlu:grow"),
				RowSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				RowSpec.decode("4dlu:grow"),
				RowSpec.decode("default:grow"),
				RowSpec.decode("4dlu:grow"),}));
		
		JLabel lblPathFile = new JLabel("Path file");
		getContentPane().add(lblPathFile, "2, 2, right, default");
		
		textField = new JTextField();
		getContentPane().add(textField, "4, 2");
		textField.setColumns(10);
		
		JButton button = new JButton("...");
		getContentPane().add(button, "6, 2");
		
		JLabel lblPassword = new JLabel("Password");
		getContentPane().add(lblPassword, "2, 4, right, default");
		
		textField_1 = new JTextField();
		getContentPane().add(textField_1, "4, 4, 3, 1, fill, default");
		textField_1.setColumns(10);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, "2, 6, 5, 1, fill, fill");
		panel.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("default:grow(10)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow(2)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow(2)"),},
			new RowSpec[] {
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JProgressBar progressBar = new JProgressBar();
		panel.add(progressBar, "1, 1");
		
		JButton btnNewButton = new JButton("New button");
		panel.add(btnNewButton, "3, 1");
		
		JButton btnNewButton_1 = new JButton("New button");
		panel.add(btnNewButton_1, "5, 1");

	}

}
