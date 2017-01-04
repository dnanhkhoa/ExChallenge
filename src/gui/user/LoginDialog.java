package gui.user;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JDialog;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginDialog extends JDialog {
	private JTextField txtUserName;
	private JPasswordField passwordField;
	private JButton btnNewButton;
	private JButton btnSignUp;
	private JButton btnImport;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginDialog dialog = new LoginDialog();
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
	public LoginDialog() {
		setResizable(false);
		getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 14));
		setTitle("Login");
		setModal(true);
		setFont(new Font("Dialog", Font.PLAIN, 14));
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 350, 300);
		getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.UNRELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.UNRELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.UNRELATED_GAP_COLSPEC,},
			new RowSpec[] {
				RowSpec.decode("30dlu"),
				RowSpec.decode("default:grow"),
				FormSpecs.UNRELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormSpecs.UNRELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormSpecs.UNRELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				RowSpec.decode("20dlu:grow"),}));
		getContentPane().add(getTxtUserName(), "2, 2, 3, 1, fill, default");
		getContentPane().add(getPasswordField(), "2, 4, 3, 1");
		getContentPane().add(getBtnNewButton(), "2, 6, 3, 1");
		getContentPane().add(getBtnSignUp(), "2, 8");
		getContentPane().add(getBtnImport(), "4, 8");
		
	}

	private JTextField getTxtUserName() {
		if (txtUserName == null) {
			txtUserName = new JTextField();
			txtUserName.setText("User name");
			
			txtUserName.setColumns(10);
		}
		return txtUserName;
	}
	private JPasswordField getPasswordField() {
		if (passwordField == null) {
			passwordField = new JPasswordField();
			
		}
		return passwordField;
	}
	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("Sign in");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					do_btnNewButton_actionPerformed(arg0);
				}
			});
		}
		return btnNewButton;
	}
	protected void do_btnNewButton_actionPerformed(ActionEvent arg0) {
	}
	private JButton getBtnSignUp() {
		if (btnSignUp == null) {
			btnSignUp = new JButton("Sign up");
		}
		return btnSignUp;
	}
	private JButton getBtnImport() {
		if (btnImport == null) {
			btnImport = new JButton("Import");
		}
		return btnImport;
	}
}
