package gui.user;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

public class EncryptDialog extends JDialog {
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EncryptDialog dialog = new EncryptDialog();
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
	public EncryptDialog() {
		getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 14));
		setTitle("Login");
		setModal(true);
		setFont(new Font("Dialog", Font.PLAIN, 14));
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new FormLayout(
				new ColumnSpec[] { FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"),
						FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"), FormSpecs.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("default:grow(5)"), FormSpecs.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("default:grow"), ColumnSpec.decode("4dlu:grow"), },
				new RowSpec[] { RowSpec.decode("4dlu:grow"), RowSpec.decode("fill:default"),
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode("default:grow"),
						RowSpec.decode("4dlu:grow"), }));

		JLabel lblPathFileEncrypt = new JLabel("Save as");
		getContentPane().add(lblPathFileEncrypt, "2, 2, right, default");

		textField = new JTextField();
		getContentPane().add(textField, "4, 2, 3, 1, fill, default");
		textField.setColumns(10);

		JButton btnNewButton = new JButton("...");
		getContentPane().add(btnNewButton, "8, 2");

		JLabel lblSendTo = new JLabel("Send To");
		getContentPane().add(lblSendTo, "2, 4, right, default");

		JComboBox comboBox_3 = new JComboBox();
		getContentPane().add(comboBox_3, "4, 4, 5, 1, fill, default");

		JLabel lblAlo = new JLabel("Algorithm");
		getContentPane().add(lblAlo, "2, 6, right, default");

		JComboBox comboBox = new JComboBox();
		getContentPane().add(comboBox, "4, 6, 5, 1, fill, default");

		JLabel lblPaddingMode = new JLabel("Padding Mode");
		getContentPane().add(lblPaddingMode, "2, 8, right, default");

		JComboBox comboBox_1 = new JComboBox();
		getContentPane().add(comboBox_1, "4, 8, 5, 1, fill, default");

		JLabel lblModeOfOperation = new JLabel("Mode of Operation");
		getContentPane().add(lblModeOfOperation, "2, 10, right, default");

		JComboBox comboBox_2 = new JComboBox();
		getContentPane().add(comboBox_2, "4, 10, 5, 1, fill, default");

		final JCheckBox chckbxNewCheckBox = new JCheckBox("Compress");
		chckbxNewCheckBox.setFocusable(false);
		getContentPane().add(chckbxNewCheckBox, "8, 12");

		JPanel panel = new JPanel();
		getContentPane().add(panel, "2, 14, 7, 1, fill, fill");
		panel.setLayout(new FormLayout(
				new ColumnSpec[] { ColumnSpec.decode("default:grow(10)"), FormSpecs.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("default:grow(2)"), FormSpecs.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("default:grow(2)"), },
				new RowSpec[] { FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, }));

		JProgressBar progressBar = new JProgressBar();
		panel.add(progressBar, "1, 2");

		JButton btnEncrypt = new JButton("Encrypt");
		panel.add(btnEncrypt, "3, 2");

		JButton btnCancel = new JButton("Cancel");
		panel.add(btnCancel, "5, 2");

	}

}
