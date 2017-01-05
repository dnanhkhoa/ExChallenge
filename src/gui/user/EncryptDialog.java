package gui.user;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import core.file.FileHandler;
import core.file.algs.symmetric.enums.AlgoEnum;
import core.file.algs.symmetric.enums.ModeOfOperationEnum;
import core.file.algs.symmetric.enums.PaddingModeEnum;
import core.handler.CoreHandler;
import core.user.User;
import core.utils.ProgressInfo;

public class EncryptDialog extends JDialog implements Observer {

	private static final long serialVersionUID = 1L;

	private JLabel lblSave;
	private JTextField txtSaveAs;
	private JButton btnSaveAs;
	private JLabel lblSendTo;
	private JComboBox<User> cbbSendTo;
	private JLabel lblAlgorithm;
	private JComboBox<AlgoEnum> cbbAlgorithm;
	private JLabel lblPaddingMode;
	private JComboBox<PaddingModeEnum> cbbPaddingMode;
	private JLabel lblMOO;
	private JComboBox<ModeOfOperationEnum> cbbMOO;
	private JCheckBox chkCompress;
	private JButton btnEncrypt;
	private JProgressBar pbProgress;
	private JLabel lblProgress;

	public File currentPath;
	public List<File> files = new ArrayList<File>();

	/**
	 * Create the dialog.
	 */
	public EncryptDialog() {
		setTitle("Encrypt");
		setModal(true);
		setResizable(false);
		setFont(new Font("Dialog", Font.PLAIN, 14));
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		getContentPane().setLayout(new FormLayout(
				new ColumnSpec[] { FormSpecs.UNRELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"),
						FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"), FormSpecs.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("default:grow(2)"), FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
						FormSpecs.UNRELATED_GAP_COLSPEC, },
				new RowSpec[] { FormSpecs.UNRELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, RowSpec.decode("7dlu:grow"), FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode("fill:35dlu"),
						FormSpecs.UNRELATED_GAP_ROWSPEC, }));
		getContentPane().add(getLblSave(), "2, 2, right, default");
		getContentPane().add(getTxtSaveAs(), "4, 2, 3, 1, fill, default");
		getContentPane().add(getBtnSaveAs(), "8, 2");
		getContentPane().add(getLblSendTo(), "2, 4, right, default");
		getContentPane().add(getCbbSendTo(), "4, 4, 5, 1, fill, default");
		getContentPane().add(getLblAlgorithm(), "2, 6, right, default");
		getContentPane().add(getCbbAlgorithm(), "4, 6, 5, 1, fill, default");
		getContentPane().add(getLblPaddingMode(), "2, 8, right, default");
		getContentPane().add(getCbbPaddingMode(), "4, 8, 5, 1, fill, default");
		getContentPane().add(getLblMOO(), "2, 10, right, default");
		getContentPane().add(getCbbMOO(), "4, 10, 5, 1, fill, default");
		getContentPane().add(getChkCompress(), "4, 12");
		getContentPane().add(getLblProgress(), "2, 14, 3, 1");
		getContentPane().add(getPbProgress(), "2, 16, 3, 1");
		getContentPane().add(getBtnEncrypt(), "6, 16, 3, 1");
	}

	private JLabel getLblSave() {
		if (lblSave == null) {
			lblSave = new JLabel("Save as");
		}
		return lblSave;
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
			btnSaveAs.setFocusable(false);
		}
		return btnSaveAs;
	}

	private JLabel getLblSendTo() {
		if (lblSendTo == null) {
			lblSendTo = new JLabel("Send to");
		}
		return lblSendTo;
	}

	private JComboBox<User> getCbbSendTo() {
		if (cbbSendTo == null) {
			cbbSendTo = new JComboBox<User>();
			cbbSendTo.setFocusable(false);

			for (User user : CoreHandler.getInstance().userManager.getUsers()) {
				cbbSendTo.addItem(user);
			}
		}
		return cbbSendTo;
	}

	private JLabel getLblAlgorithm() {
		if (lblAlgorithm == null) {
			lblAlgorithm = new JLabel("Algorithm");
		}
		return lblAlgorithm;
	}

	private JComboBox<AlgoEnum> getCbbAlgorithm() {
		if (cbbAlgorithm == null) {
			cbbAlgorithm = new JComboBox<AlgoEnum>();
			cbbAlgorithm.setModel(new DefaultComboBoxModel<AlgoEnum>(AlgoEnum.values()));
			cbbAlgorithm.setFocusable(false);
		}
		return cbbAlgorithm;
	}

	private JLabel getLblPaddingMode() {
		if (lblPaddingMode == null) {
			lblPaddingMode = new JLabel("Padding mode");
		}
		return lblPaddingMode;
	}

	private JComboBox<PaddingModeEnum> getCbbPaddingMode() {
		if (cbbPaddingMode == null) {
			cbbPaddingMode = new JComboBox<PaddingModeEnum>();
			cbbPaddingMode.setModel(new DefaultComboBoxModel<PaddingModeEnum>(PaddingModeEnum.values()));
			cbbPaddingMode.setFocusable(false);
		}
		return cbbPaddingMode;
	}

	private JLabel getLblMOO() {
		if (lblMOO == null) {
			lblMOO = new JLabel("Mode of operation");
		}
		return lblMOO;
	}

	private JComboBox<ModeOfOperationEnum> getCbbMOO() {
		if (cbbMOO == null) {
			cbbMOO = new JComboBox<ModeOfOperationEnum>();
			cbbMOO.setModel(new DefaultComboBoxModel<ModeOfOperationEnum>(ModeOfOperationEnum.values()));
			cbbMOO.setFocusable(false);
		}
		return cbbMOO;
	}

	private JCheckBox getChkCompress() {
		if (chkCompress == null) {
			chkCompress = new JCheckBox("Compress");
			chkCompress.setFocusable(false);
		}
		return chkCompress;
	}

	private JButton getBtnEncrypt() {
		if (btnEncrypt == null) {
			btnEncrypt = new JButton("Encrypt");
			btnEncrypt.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					do_btnEncrypt_actionPerformed(arg0);
				}
			});
			btnEncrypt.setFocusable(false);
		}
		return btnEncrypt;
	}

	private JProgressBar getPbProgress() {
		if (pbProgress == null) {
			pbProgress = new JProgressBar();
			pbProgress.setStringPainted(true);
		}
		return pbProgress;
	}

	private JLabel getLblProgress() {
		if (lblProgress == null) {
			lblProgress = new JLabel("Progress: ...");
		}
		return lblProgress;
	}

	protected void do_btnSaveAs_actionPerformed(ActionEvent e) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Save file");
		fileChooser.setSelectedFile(new File(currentPath.getPath(), "encrypted.enc"));
		if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
			if (fileChooser.getSelectedFile().exists()) {
				JOptionPane.showMessageDialog(this, "The file exists!");
				return;
			}
			txtSaveAs.setText(fileChooser.getSelectedFile().toString());
		}
	}

	protected void do_btnEncrypt_actionPerformed(ActionEvent arg0) {
		if (txtSaveAs.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Please choose the destination path");
			return;
		}

		FileHandler fileHandler = new FileHandler(files, (AlgoEnum) cbbAlgorithm.getSelectedItem(),
				(ModeOfOperationEnum) cbbMOO.getSelectedItem(), (PaddingModeEnum) cbbPaddingMode.getSelectedItem(),
				chkCompress.isSelected());

		fileHandler.registerObserver(this);

		btnEncrypt.setEnabled(false);

		final JDialog dialog = this;
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					fileHandler.encrypt(new File(txtSaveAs.getText()), (User) cbbSendTo.getSelectedItem());
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
