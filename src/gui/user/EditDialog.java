package gui.user;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JDialog;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class EditDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTextField txtEmail;
	private JTextField txtPassword;
	private JTextField txtName;
	private JTextField txtBirthday;
	private JTextField textField;
	private JTextField textField_1;
	private JLabel lblEmail;
	private JLabel lblPassword;
	private JLabel lblName;
	private JLabel lblBirthday;
	private JLabel lblPhone;
	private JLabel lblAddress;
	private JLabel lblKeyLength;
	private JButton btnSignUp;
	private JComboBox comboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterDialog dialog = new RegisterDialog();
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
	public EditDialog() {
		getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 14));
		setTitle("Login");
		setModal(true);
		setFont(new Font("Dialog", Font.PLAIN, 14));
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("4dlu:grow"),
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				ColumnSpec.decode("4dlu:grow"),},
			new RowSpec[] {
				RowSpec.decode("7dlu:grow"),
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				RowSpec.decode("7dlu:grow"),}));
		getContentPane().add(getLblEmail(), "2, 2, right, default");
		getContentPane().add(getTxtEmail(), "4, 2, fill, default");
		getContentPane().add(getLblPassword(), "2, 4, right, default");
		getContentPane().add(getTxtPassword(), "4, 4, fill, default");
		getContentPane().add(getLblName(), "2, 6, right, default");
		getContentPane().add(getTxtName(), "4, 6, fill, default");
		getContentPane().add(getLblBirthday(), "2, 8, right, default");
		getContentPane().add(getTxtBirthday(), "4, 8, fill, default");
		getContentPane().add(getLblPhone(), "2, 10, right, default");
		getContentPane().add(getTextField(), "4, 10, fill, default");
		getContentPane().add(getLblAddress(), "2, 12, right, default");
		getContentPane().add(getTextField_1(), "4, 12, fill, default");
		getContentPane().add(getLblKeyLength(), "2, 14, right, default");
		getContentPane().add(getComboBox(), "4, 14, fill, default");
		getContentPane().add(getBtnSignUp(), "4, 16");

	}

	private JTextField getTxtEmail() {
		if (txtEmail == null) {
			txtEmail = new JTextField();
			txtEmail.setColumns(10);
		}
		return txtEmail;
	}
	private JTextField getTxtPassword() {
		if (txtPassword == null) {
			txtPassword = new JTextField();
			txtPassword.setColumns(10);
		}
		return txtPassword;
	}
	private JTextField getTxtName() {
		if (txtName == null) {
			txtName = new JTextField();
			txtName.setColumns(10);
		}
		return txtName;
	}
	private JTextField getTxtBirthday() {
		if (txtBirthday == null) {
			txtBirthday = new JTextField();
			txtBirthday.setColumns(10);
		}
		return txtBirthday;
	}
	private JTextField getTextField() {
		if (textField == null) {
			textField = new JTextField();
			textField.setColumns(10);
		}
		return textField;
	}
	private JTextField getTextField_1() {
		if (textField_1 == null) {
			textField_1 = new JTextField();
			textField_1.setColumns(10);
		}
		return textField_1;
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
	private JLabel getLblName() {
		if (lblName == null) {
			lblName = new JLabel("Name");
		}
		return lblName;
	}
	private JLabel getLblBirthday() {
		if (lblBirthday == null) {
			lblBirthday = new JLabel("Birthday");
		}
		return lblBirthday;
	}
	private JLabel getLblPhone() {
		if (lblPhone == null) {
			lblPhone = new JLabel("Phone");
		}
		return lblPhone;
	}
	private JLabel getLblAddress() {
		if (lblAddress == null) {
			lblAddress = new JLabel("Address");
		}
		return lblAddress;
	}
	private JLabel getLblKeyLength() {
		if (lblKeyLength == null) {
			lblKeyLength = new JLabel("Key length");
		}
		return lblKeyLength;
	}
	private JButton getBtnSignUp() {
		if (btnSignUp == null) {
			btnSignUp = new JButton("Update");
		}
		return btnSignUp;
	}
	private JComboBox getComboBox() {
		if (comboBox == null) {
			comboBox = new JComboBox();
		}
		return comboBox;
	}
}
