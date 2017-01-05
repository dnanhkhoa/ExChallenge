package gui.user;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import core.file.FileHandler;
import core.handler.CoreHandler;
import core.utils.ProgressInfo;

public class DecryptDialog extends JDialog implements Observer {

	private static final long serialVersionUID = 1L;
	private JLabel lblSaveAs;
	private JTextField txtSaveAs;
	private JButton btnSaveAs;
	private JLabel lblPassword;
	private JPasswordField txtPassword;
	private JLabel lblProgress;
	private JProgressBar pbProgress;
	private JButton btnDecrypt;

	public File currentPath;
	public File file;

	/**
	 * Create the dialog.
	 */
	public DecryptDialog() {
		setTitle("Decrypt");
		setModal(true);
		setResizable(false);
		setFont(new Font("Dialog", Font.PLAIN, 14));
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new FormLayout(
				new ColumnSpec[] { FormSpecs.UNRELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"),
						FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow(2)"),
						FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow(2)"),
						FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, FormSpecs.UNRELATED_GAP_COLSPEC, },
				new RowSpec[] { FormSpecs.UNRELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, RowSpec.decode("7dlu:grow"),
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode("fill:35dlu"),
						FormSpecs.UNRELATED_GAP_ROWSPEC, }));
		getContentPane().add(getLblSaveAs(), "2, 2, right, default");
		getContentPane().add(getTxtSaveAs(), "4, 2, 3, 1, fill, default");
		getContentPane().add(getBtnSaveAs(), "8, 2");
		getContentPane().add(getLblPassword(), "2, 4, right, default");
		getContentPane().add(getTxtPassword(), "4, 4, 5, 1, fill, default");
		getContentPane().add(getLblProgress(), "2, 6, 3, 1");
		getContentPane().add(getPbProgress(), "2, 8, 3, 1");
		getContentPane().add(getBtnDecrypt(), "6, 8, 3, 1");

	}

	private JLabel getLblSaveAs() {
		if (lblSaveAs == null) {
			lblSaveAs = new JLabel("Save as");
		}
		return lblSaveAs;
	}

	private JTextField getTxtSaveAs() {
		if (txtSaveAs == null) {
			txtSaveAs = new JTextField();
			txtSaveAs.setFocusable(false);
			txtSaveAs.setEditable(false);
			txtSaveAs.setColumns(10);
		}
		return txtSaveAs;
	}

	private JButton getBtnSaveAs() {
		if (btnSaveAs == null) {
			btnSaveAs = new JButton("...");
			btnSaveAs.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					do_btnSaveAs_actionPerformed(e);
				}
			});
		}
		return btnSaveAs;
	}

	private JLabel getLblPassword() {
		if (lblPassword == null) {
			lblPassword = new JLabel("Password");
		}
		return lblPassword;
	}

	private JPasswordField getTxtPassword() {
		if (txtPassword == null) {
			txtPassword = new JPasswordField();
		}
		return txtPassword;
	}

	private JLabel getLblProgress() {
		if (lblProgress == null) {
			lblProgress = new JLabel("Progress: ...");
		}
		return lblProgress;
	}

	private JProgressBar getPbProgress() {
		if (pbProgress == null) {
			pbProgress = new JProgressBar();
			pbProgress.setStringPainted(true);
		}
		return pbProgress;
	}

	private JButton getBtnDecrypt() {
		if (btnDecrypt == null) {
			btnDecrypt = new JButton("Decrypt");
			btnDecrypt.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					do_btnDecrypt_actionPerformed(e);
				}
			});
		}
		return btnDecrypt;
	}

	protected void do_btnSaveAs_actionPerformed(ActionEvent e) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Choose folder");
		fileChooser.setCurrentDirectory(currentPath);
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
			if (!fileChooser.getSelectedFile().exists()) {
				JOptionPane.showMessageDialog(this, "The folder does not exist!");
				return;
			}
			txtSaveAs.setText(fileChooser.getSelectedFile().toString());
		}
	}

	protected void do_btnDecrypt_actionPerformed(ActionEvent e) {
		if (txtSaveAs.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Please choose the destination path!");
			return;
		}

		if (txtPassword.getPassword().length == 0) {
			JOptionPane.showMessageDialog(this, "Please enter your password!");
			return;
		}

		List<File> files = new ArrayList<>();
		files.add(file);

		FileHandler fileHandler = new FileHandler(files);

		fileHandler.registerObserver(this);

		btnDecrypt.setEnabled(false);

		final JDialog dialog = this;
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					fileHandler.decrypt(new File(txtSaveAs.getText()), CoreHandler.getInstance().currentUser,
							new String(txtPassword.getPassword()));
					JOptionPane.showMessageDialog(dialog, "Done!");
				} catch (Exception e) {
					JOptionPane.showMessageDialog(dialog, e.getMessage());
				}

				dialog.dispose();
			}
		});

		thread.start();
	}

	@Override
	public void update(Observable o, Object arg) {
		if (arg instanceof ProgressInfo) {
			ProgressInfo processInfo = (ProgressInfo) arg;
			lblProgress.setText(processInfo.getName());
			pbProgress.setValue(processInfo.getProgressValue());
		}
	}
}
