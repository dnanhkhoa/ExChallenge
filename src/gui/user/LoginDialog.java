package gui.user;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

public class LoginDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private JTextField txtUserName;
	private JPasswordField txtPassword;
	private JButton btnSignIn;
	private JButton btnSignUp;
	private JButton btnImport;

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
		
		getContentPane().setLayout(new FormLayout(
				new ColumnSpec[] { FormSpecs.UNRELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"),
						FormSpecs.UNRELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"),
						FormSpecs.UNRELATED_GAP_COLSPEC, },
				new RowSpec[] { RowSpec.decode("30dlu"), RowSpec.decode("default:grow"),
						FormSpecs.UNRELATED_GAP_ROWSPEC, RowSpec.decode("default:grow"),
						FormSpecs.UNRELATED_GAP_ROWSPEC, RowSpec.decode("default:grow"),
						FormSpecs.UNRELATED_GAP_ROWSPEC, RowSpec.decode("default:grow"),
						RowSpec.decode("20dlu:grow"), }));
		getContentPane().add(getTxtUserName(), "2, 2, 3, 1, fill, default");
		getContentPane().add(getTxtPassword(), "2, 4, 3, 1");
		getContentPane().add(getBtnSignIn(), "2, 6, 3, 1");
		getContentPane().add(getBtnSignUp(), "2, 8");
		getContentPane().add(getBtnImport(), "4, 8");

	}

	private JTextField getTxtUserName() {
		if (txtUserName == null) {
			txtUserName = new JTextField();

			txtUserName.setColumns(10);
		}
		return txtUserName;
	}

	private JPasswordField getTxtPassword() {
		if (txtPassword == null) {
			txtPassword = new JPasswordField();

		}
		return txtPassword;
	}

	private JButton getBtnSignIn() {
		if (btnSignIn == null) {
			btnSignIn = new JButton("Sign in");
			btnSignIn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					do_btnNewButton_actionPerformed(arg0);
				}
			});
		}
		return btnSignIn;
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
