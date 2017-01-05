package gui.user;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import core.handler.CoreHandler;
import core.user.User;

public class LoginDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private JTextField txtUserName;
	private JPasswordField txtPassword;
	private JButton btnSignIn;
	private JButton btnSignUp;
	private JButton btnImport;
	private JLabel lblEmail;
	private JLabel lblPassword;

	/**
	 * Create the dialog.
	 */
	public LoginDialog() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				do_this_windowClosing(arg0);
			}
		});
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setResizable(false);
		getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 14));
		setTitle("Login");
		setModal(true);
		setFont(new Font("Dialog", Font.PLAIN, 14));
		setBounds(100, 100, 350, 300);

		getContentPane().setLayout(new FormLayout(
				new ColumnSpec[] { FormSpecs.UNRELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"),
						FormSpecs.UNRELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"),
						FormSpecs.UNRELATED_GAP_COLSPEC, },
				new RowSpec[] { RowSpec.decode("30dlu"), FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						RowSpec.decode("default:grow"), FormSpecs.UNRELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode("default:grow"), FormSpecs.UNRELATED_GAP_ROWSPEC,
						RowSpec.decode("default:grow"), FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						RowSpec.decode("20dlu:grow"), }));
		getContentPane().add(getLblEmail(), "2, 2");
		getContentPane().add(getTxtUserName(), "2, 4, 3, 1, fill, default");
		getContentPane().add(getLblPassword(), "2, 6");
		getContentPane().add(getTxtPassword(), "2, 8, 3, 1");
		getContentPane().add(getBtnSignIn(), "2, 10, 3, 1");
		getContentPane().add(getBtnSignUp(), "2, 12");
		getContentPane().add(getBtnImport(), "4, 12");

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
				public void actionPerformed(ActionEvent e) {
					do_btnSignIn_actionPerformed(e);
				}
			});
		}
		return btnSignIn;
	}

	private JButton getBtnSignUp() {
		if (btnSignUp == null) {
			btnSignUp = new JButton("Sign up");
			btnSignUp.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					do_btnSignUp_actionPerformed(e);
				}
			});
		}
		return btnSignUp;
	}

	private JButton getBtnImport() {
		if (btnImport == null) {
			btnImport = new JButton("Import");
			btnImport.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					do_btnImport_actionPerformed(e);
				}
			});
		}
		return btnImport;
	}

	private JLabel getLblEmail() {
		if (lblEmail == null) {
			lblEmail = new JLabel("Email");
		}
		return lblEmail;
	}

	private JLabel getLblPassword() {
		if (lblPassword == null) {
			lblPassword = new JLabel("Password");
		}
		return lblPassword;
	}

	protected void do_btnSignIn_actionPerformed(ActionEvent e) {
		if (this.getTxtUserName().getText().isEmpty() || this.getTxtPassword().getPassword().length == 0) {
			JOptionPane.showMessageDialog(this, "You must fill above field!");
			return;
		}
		User user = CoreHandler.getInstance().userManager.login(this.getTxtUserName().getText(),
				new String(this.getTxtPassword().getPassword()));
		if (user == null) {
			JOptionPane.showMessageDialog(this, "Email or password is wrong!");
			return;
		}
		CoreHandler.getInstance().currentUser = user;
		this.setVisible(false);
		this.getTxtUserName().setText("");
		this.getTxtPassword().setText("");
	}

	protected void do_btnSignUp_actionPerformed(ActionEvent e) {
		this.setVisible(false);

		JDialog dialog = new RegisterDialog();
		dialog.setLocationRelativeTo(this);
		dialog.setVisible(true);

		this.getTxtUserName().setText("");
		this.getTxtPassword().setText("");
		this.setVisible(true);
	}

	protected void do_btnImport_actionPerformed(ActionEvent e) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Import profile");
		fileChooser.setCurrentDirectory(new java.io.File("."));
		if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			File inFile = fileChooser.getSelectedFile();
			if (!inFile.isFile()) {
				JOptionPane.showMessageDialog(this, "Profile file does not exist!");
				return;
			}
			User user = User.load(inFile);
			if (user == null) {
				JOptionPane.showMessageDialog(this, "Profile file is corrupt!");
			} else {
				User otherUser = CoreHandler.getInstance().userManager.findUserByEmail(user.getEmail());
				if (otherUser != null) {

					JPanel panel = new JPanel();
					JLabel label = new JLabel("Enter a password to confirm:");
					JPasswordField pass = new JPasswordField(10);
					panel.add(label);
					panel.add(pass);
					String[] options = new String[] { "OK", "Cancel" };
					int option = JOptionPane.showOptionDialog(null, panel, "Input password", JOptionPane.NO_OPTION,
							JOptionPane.PLAIN_MESSAGE, null, options, options[1]);

					if (option != 0)
						return;

					String password = new String(pass.getPassword());
					if (!otherUser.confirmPassword(password)) {
						JOptionPane.showMessageDialog(this, "Password is wrong!");
						return;
					}
					CoreHandler.getInstance().userManager.remove(otherUser);
				}
				CoreHandler.getInstance().userManager.add(user);
				JOptionPane.showMessageDialog(this, "Done!");
			}
		}
	}

	protected void do_this_windowClosing(WindowEvent arg0) {
		CoreHandler.getInstance().close();
		System.exit(0);
	}
}
