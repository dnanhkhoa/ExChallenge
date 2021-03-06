package gui.user;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import core.handler.CoreHandler;

public class EditDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private JTextField txtEmail;
	private JTextField txtName;
	private JTextField txtBirthday;
	private JTextField txtPhone;
	private JTextField txtAddress;
	private JComboBox<Integer> cbbKeyLength;
	private JPasswordField txtPassword;
	private JLabel lblEmail;
	private JLabel lblPassword;
	private JLabel lblName;
	private JLabel lblBirthday;
	private JLabel lblPhone;
	private JLabel lblAddress;
	private JLabel lblKeyLength;
	private JButton btnSignUp;

	/**
	 * Create the dialog.
	 */
	public EditDialog() {
		setResizable(false);
		setModal(true);
		getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 14));
		setTitle("Update");
		setFont(new Font("Dialog", Font.PLAIN, 14));
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		getContentPane().setLayout(new FormLayout(
				new ColumnSpec[] { FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"),
						FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow(2)"),
						FormSpecs.RELATED_GAP_COLSPEC, },
				new RowSpec[] { FormSpecs.UNRELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						RowSpec.decode("7dlu:grow"), FormSpecs.DEFAULT_ROWSPEC, FormSpecs.UNRELATED_GAP_ROWSPEC, }));
		
		getContentPane().add(getLblEmail(), "2, 2, right, default");
		getContentPane().add(getTxtEmail(), "4, 2, fill, default");
		getContentPane().add(getLblPassword(), "2, 4, right, default");
		getContentPane().add(getTxtPassword(), "4, 4, fill, default");
		getContentPane().add(getLblName(), "2, 6, right, default");
		getContentPane().add(getTxtName(), "4, 6, fill, default");
		getContentPane().add(getLblBirthday(), "2, 8, right, default");
		getContentPane().add(getTxtBirthday(), "4, 8, fill, default");
		getContentPane().add(getLblPhone(), "2, 10, right, default");
		getContentPane().add(getTxtPhone(), "4, 10, fill, default");
		getContentPane().add(getLblAddress(), "2, 12, right, default");
		getContentPane().add(getTxtAddress(), "4, 12, fill, default");
		getContentPane().add(getLblKeyLength(), "2, 14, right, default");
		getContentPane().add(getCbbKeyLength(), "4, 14, fill, default");
		getContentPane().add(getBtnSignUp(), "4, 16");

		this.getTxtEmail().setText(CoreHandler.getInstance().currentUser.getEmail());
		this.getTxtName().setText(CoreHandler.getInstance().currentUser.getName());
		this.getTxtBirthday().setText(CoreHandler.getInstance().currentUser.getBirthday());
		this.getTxtPhone().setText(CoreHandler.getInstance().currentUser.getPhone());
		this.getTxtAddress().setText(CoreHandler.getInstance().currentUser.getAddress());
		this.getTxtEmail().setText(CoreHandler.getInstance().currentUser.getEmail());
		this.getCbbKeyLength().setSelectedIndex(CoreHandler.getInstance().currentUser.getKeySizeIndex());
	}

	private JTextField getTxtEmail() {
		if (txtEmail == null) {
			txtEmail = new JTextField();
			txtEmail.setEditable(false);
			txtEmail.setColumns(10);
		}
		return txtEmail;
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

	private JTextField getTxtPhone() {
		if (txtPhone == null) {
			txtPhone = new JTextField();
			txtPhone.setColumns(10);
		}
		return txtPhone;
	}

	private JTextField getTxtAddress() {
		if (txtAddress == null) {
			txtAddress = new JTextField();
			txtAddress.setColumns(10);
		}
		return txtAddress;
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
			btnSignUp.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					do_btnSignUp_actionPerformed(e);
				}
			});
			btnSignUp.setFocusable(false);
		}
		return btnSignUp;
	}

	private JComboBox<Integer> getCbbKeyLength() {
		if (cbbKeyLength == null) {
			cbbKeyLength = new JComboBox<>();
			cbbKeyLength.setFocusable(false);
			cbbKeyLength.setModel(
					new DefaultComboBoxModel<Integer>(new Integer[] { 512, 576, 640, 704, 768, 832, 896, 960, 1024 }));
			cbbKeyLength.setSelectedIndex(8);
		}
		return cbbKeyLength;
	}

	private JPasswordField getTxtPassword() {
		if (txtPassword == null) {
			txtPassword = new JPasswordField();
		}
		return txtPassword;
	}

	protected void do_btnSignUp_actionPerformed(ActionEvent e) {
		try {
			System.out.println(new String(this.getTxtPassword().getPassword()).length());
			CoreHandler.getInstance().currentUser.update(new String(this.getTxtPassword().getPassword()),
					this.getTxtName().getText(), this.getTxtBirthday().getText(), this.getTxtPhone().getText(),
					this.getTxtAddress().getText(), (int) this.getCbbKeyLength().getSelectedItem(),
					this.getCbbKeyLength().getSelectedIndex());
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage());
		}
		this.dispose();
	}
}
