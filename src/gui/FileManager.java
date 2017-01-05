package gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.security.Key;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import core.file.FileDigitalCertificate;
import core.handler.CoreHandler;
import core.model.FileModel;
import core.model.JLabelRenderer;
import core.model.TableModel;
import core.user.User;
import gui.user.EditDialog;
import gui.user.LoginDialog;

public class FileManager {

	private JFrame frmMain;
	private JToolBar tlbHeader;

	private JScrollPane scrollPane;
	private JTable tbFiles;

	private JToolBar tlbFooter;
	private JLabel lblUser;

	private JButton btnEncrypt;
	private JButton btnDecrypt;
	private JButton btnSignature;
	private JButton btnVerify;
	private JButton btnExport;
	private JButton btnEditProfile;
	private JButton btnLogOut;

	private JButton btnUp;
	private JTextField txtPath;

	private JPopupMenu popupMenu;
	private JMenuItem mnuCut;
	private JMenuItem mnuCopy;
	private JMenuItem mnuPaste;
	private JMenuItem mnuRename;
	private JMenuItem mnuDelete;

	private JDialog loginDialog;

	private TableModel tableModel;
	private File currentPath;

	private FileAlterationObserver observer;
	private FileAlterationMonitor monitor;
	private FileAlterationListener listener;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FileManager window = new FileManager();
					window.frmMain.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FileManager() {
		initializeVariables();
		initializeGUI();
	}

	private void initializeVariables() {
		CoreHandler.getInstance();

		this.currentPath = new File("").getAbsoluteFile();

		this.monitor = new FileAlterationMonitor(1000);
		this.listener = new FileAlterationListenerAdaptor() {
			@Override
			public void onFileCreate(File file) {
				browseFiles(currentPath);
			}

			@Override
			public void onFileDelete(File file) {
				browseFiles(currentPath);
			}

			@Override
			public void onFileChange(File file) {
				browseFiles(currentPath);
			}

			@Override
			public void onDirectoryChange(File directory) {
				browseFiles(currentPath);
			}

			@Override
			public void onDirectoryCreate(File directory) {
				browseFiles(currentPath);
			}

			@Override
			public void onDirectoryDelete(File directory) {
				browseFiles(currentPath);
			}
		};

		this.tableModel = new TableModel();
		this.browseFiles(this.currentPath);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initializeGUI() {
		this.frmMain = new JFrame();
		this.frmMain.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				do_frmMain_windowClosing(arg0);
			}
		});
		this.frmMain.setLocationRelativeTo(null);
		this.frmMain.setTitle("ExChallenge");
		this.frmMain.setBounds(100, 100, 850, 600);
		this.frmMain.setMinimumSize(new Dimension(850, 600));
		this.frmMain.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.frmMain.getContentPane()
				.setLayout(new FormLayout(new ColumnSpec[] {
						FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("25dlu"), FormSpecs.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("default:grow"), FormSpecs.RELATED_GAP_COLSPEC, },
						new RowSpec[] { FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode("fill:max(70dlu;default)"),
								FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode("fill:default"),
								FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode("fill:default:grow"),
								FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
								FormSpecs.RELATED_GAP_ROWSPEC, }));
		this.frmMain.getContentPane().add(getTlbHeader(), "2, 2, 3, 1");
		this.frmMain.getContentPane().add(getBtnUp(), "2, 4");
		this.frmMain.getContentPane().add(getTxtPath(), "4, 4, fill, default");
		this.frmMain.getContentPane().add(getScrollPane(), "2, 6, 3, 1, fill, fill");
		this.frmMain.getContentPane().add(getTlbFooter(), "2, 8, 3, 1");

		this.loginDialog = new LoginDialog();
		this.loginDialog.setLocationRelativeTo(this.frmMain);
		this.loginDialog.setVisible(false);

		this.isLogged();
	}

	private JToolBar getTlbHeader() {
		if (tlbHeader == null) {
			tlbHeader = new JToolBar();
			tlbHeader.setFloatable(false);
			tlbHeader.add(getBtnEncrypt());
			tlbHeader.add(getBtnDecrypt());
			tlbHeader.addSeparator();
			tlbHeader.add(getBtnSignature());
			tlbHeader.add(getBtnVerify());
			tlbHeader.addSeparator();
			tlbHeader.add(getBtnExport());
			tlbHeader.add(getBtnEditProfile());
			tlbHeader.addSeparator();
			tlbHeader.add(getBtnLogOut());
		}
		return tlbHeader;
	}

	private JButton getBtnUp() {
		if (btnUp == null) {
			btnUp = new JButton("");
			btnUp.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					do_btnUp_actionPerformed(arg0);
				}
			});
			btnUp.setContentAreaFilled(false);
			btnUp.setFocusable(false);

			try {
				Image icon = ImageIO.read(getClass().getResource("/icons/24/up.png"));
				btnUp.setIcon(new ImageIcon(icon));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return btnUp;
	}

	private JTextField getTxtPath() {
		if (txtPath == null) {
			txtPath = new JTextField();
			txtPath.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					do_txtPath_actionPerformed(e);
				}
			});
			txtPath.setFont(new Font("Tahoma", Font.PLAIN, 14));
			txtPath.setColumns(10);
		}
		return txtPath;
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getTbFiles());
		}
		return scrollPane;
	}

	private JTable getTbFiles() {
		if (tbFiles == null) {
			tbFiles = new JTable();
			tbFiles.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					do_tbFiles_mouseClicked(arg0);
				}
			});
			tbFiles.setShowGrid(false);
			tbFiles.setFocusable(false);
			tbFiles.setFillsViewportHeight(true);
			tbFiles.setDefaultRenderer(JLabel.class, new JLabelRenderer());
			tbFiles.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			tbFiles.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			tbFiles.setModel(tableModel);
			addPopup(tbFiles, getPopupMenu());
		}
		return tbFiles;
	}

	private JPopupMenu getPopupMenu() {
		if (popupMenu == null) {
			popupMenu = new JPopupMenu();
			popupMenu.add(getMnuCut());
			popupMenu.add(getMnuCopy());
			popupMenu.add(getMnuPaste());
			popupMenu.addSeparator();
			popupMenu.add(getMnuRename());
			popupMenu.add(getMnuDelete());
		}
		return popupMenu;
	}

	private void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					checkItemMenu();
					showMenu(e);
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					checkItemMenu();
					showMenu(e);
				}
			}

			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}

	private JMenuItem getMnuCut() {
		if (mnuCut == null) {
			mnuCut = new JMenuItem("Cut");
			mnuCut.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					do_mnuCut_actionPerformed(arg0);
				}
			});
		}
		return mnuCut;
	}

	private JMenuItem getMnuCopy() {
		if (mnuCopy == null) {
			mnuCopy = new JMenuItem("Copy");
			mnuCopy.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					do_mnuCopy_actionPerformed(e);
				}
			});
		}
		return mnuCopy;
	}

	private JMenuItem getMnuPaste() {
		if (mnuPaste == null) {
			mnuPaste = new JMenuItem("Paste");
			mnuPaste.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					do_mnuPaste_actionPerformed(e);
				}
			});
		}
		return mnuPaste;
	}

	private JMenuItem getMnuRename() {
		if (mnuRename == null) {
			mnuRename = new JMenuItem("Rename");
			mnuRename.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					do_mnuRename_actionPerformed(e);
				}
			});
		}
		return mnuRename;
	}

	private JMenuItem getMnuDelete() {
		if (mnuDelete == null) {
			mnuDelete = new JMenuItem("Delete");
			mnuDelete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					do_mnuDelete_actionPerformed(e);
				}
			});
		}
		return mnuDelete;
	}

	private JToolBar getTlbFooter() {
		if (tlbFooter == null) {
			tlbFooter = new JToolBar();
			tlbFooter.setFloatable(false);
			tlbFooter.add(getLblUser());
		}
		return tlbFooter;
	}

	private JLabel getLblUser() {
		if (lblUser == null) {
			lblUser = new JLabel("User");
		}
		return lblUser;
	}

	private JButton getBtnEncrypt() {
		if (btnEncrypt == null) {
			btnEncrypt = new JButton("Encrypt");
			btnEncrypt.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					do_btnEncrypt_actionPerformed(e);
				}
			});
			btnEncrypt.setHorizontalTextPosition(SwingConstants.CENTER);
			btnEncrypt.setVerticalTextPosition(SwingConstants.BOTTOM);
			btnEncrypt.setFocusable(false);

			try {
				Image icon = ImageIO.read(getClass().getResource("/icons/64/encrypt.png"));
				btnEncrypt.setIcon(new ImageIcon(icon));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return btnEncrypt;
	}

	private JButton getBtnDecrypt() {
		if (btnDecrypt == null) {
			btnDecrypt = new JButton("Decrypt");
			btnDecrypt.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					do_btnDecrypt_actionPerformed(e);
				}
			});
			btnDecrypt.setHorizontalTextPosition(SwingConstants.CENTER);
			btnDecrypt.setVerticalTextPosition(SwingConstants.BOTTOM);
			btnDecrypt.setFocusable(false);

			try {
				Image icon = ImageIO.read(getClass().getResource("/icons/64/decrypt.png"));
				btnDecrypt.setIcon(new ImageIcon(icon));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return btnDecrypt;
	}

	private JButton getBtnSignature() {
		if (btnSignature == null) {
			btnSignature = new JButton("Digital signature");
			btnSignature.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					do_btnSignature_actionPerformed(e);
				}
			});
			btnSignature.setVerticalTextPosition(SwingConstants.BOTTOM);
			btnSignature.setHorizontalTextPosition(SwingConstants.CENTER);
			btnSignature.setFocusable(false);

			try {
				Image icon = ImageIO.read(getClass().getResource("/icons/64/signature.png"));
				btnSignature.setIcon(new ImageIcon(icon));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return btnSignature;
	}

	private JButton getBtnVerify() {
		if (btnVerify == null) {
			btnVerify = new JButton("Verify");
			btnVerify.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					do_btnVerify_actionPerformed(e);
				}
			});
			btnVerify.setVerticalTextPosition(SwingConstants.BOTTOM);
			btnVerify.setHorizontalTextPosition(SwingConstants.CENTER);
			btnVerify.setFocusable(false);

			try {
				Image icon = ImageIO.read(getClass().getResource("/icons/64/verify.png"));
				btnVerify.setIcon(new ImageIcon(icon));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return btnVerify;
	}

	private JButton getBtnExport() {
		if (btnExport == null) {
			btnExport = new JButton("Export");
			btnExport.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					do_btnExport_actionPerformed(e);
				}
			});
			btnExport.setVerticalTextPosition(SwingConstants.BOTTOM);
			btnExport.setHorizontalTextPosition(SwingConstants.CENTER);
			btnExport.setFocusable(false);

			try {
				Image icon = ImageIO.read(getClass().getResource("/icons/64/export.png"));
				btnExport.setIcon(new ImageIcon(icon));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return btnExport;
	}

	private JButton getBtnEditProfile() {
		if (btnEditProfile == null) {
			btnEditProfile = new JButton("Edit profile");
			btnEditProfile.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					do_btnEditProfile_actionPerformed(e);
				}
			});
			btnEditProfile.setVerticalTextPosition(SwingConstants.BOTTOM);
			btnEditProfile.setHorizontalTextPosition(SwingConstants.CENTER);
			btnEditProfile.setFocusable(false);

			try {
				Image icon = ImageIO.read(getClass().getResource("/icons/64/edit.png"));
				btnEditProfile.setIcon(new ImageIcon(icon));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return btnEditProfile;
	}

	private JButton getBtnLogOut() {
		if (btnLogOut == null) {
			btnLogOut = new JButton("Log out");
			btnLogOut.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					do_btnLogOut_actionPerformed(e);
				}
			});
			btnLogOut.setFocusable(false);
			btnLogOut.setVerticalTextPosition(SwingConstants.BOTTOM);
			btnLogOut.setHorizontalTextPosition(SwingConstants.CENTER);
			try {
				Image icon = ImageIO.read(getClass().getResource("/icons/64/logout.png"));
				btnLogOut.setIcon(new ImageIcon(icon));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return btnLogOut;
	}

	protected void do_btnUp_actionPerformed(ActionEvent arg0) {
		this.browserBack();
	}

	protected void do_txtPath_actionPerformed(ActionEvent e) {
		this.browseFiles(new File(this.getTxtPath().getText()));
	}

	protected void do_btnEncrypt_actionPerformed(ActionEvent e) {
	}

	protected void do_btnDecrypt_actionPerformed(ActionEvent e) {
	}

	protected void do_btnSignature_actionPerformed(ActionEvent e) {
		if (this.tbFiles.getSelectedRowCount() != 1) {
			JOptionPane.showMessageDialog(this.frmMain, "Please select a file!");
			return;
		}

		FileModel fileModel = this.tableModel.fileModels.get(this.tbFiles.getSelectedRow());
		File file = new File(fileModel.getPath());

		if (file.isFile()) {

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
			if (!CoreHandler.getInstance().currentUser.confirmPassword(password)) {
				JOptionPane.showMessageDialog(this.frmMain, "Password is wrong!");
				return;
			}

			Key privateKey = CoreHandler.getInstance().currentUser.getPrivateKey(password);
			try {
				FileDigitalCertificate.sign(file, privateKey, CoreHandler.getInstance().currentUser.getKeySize(),
						new File(file.getPath() + ".cer"));
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this.frmMain, ex.getMessage());
			}
		} else {
			JOptionPane.showMessageDialog(this.frmMain, "This is not file!");
		}
	}

	protected void do_btnVerify_actionPerformed(ActionEvent e) {
		if (this.tbFiles.getSelectedRowCount() != 2) {
			JOptionPane.showMessageDialog(this.frmMain, "Please select a file pair!");
			return;
		}

		int[] files = this.tbFiles.getSelectedRows();

		File cerFile = null;

		FileModel fileModel = this.tableModel.fileModels.get(files[0]);
		File file = new File(fileModel.getPath());

		if (FileDigitalCertificate.isCerFile(file)) {
			cerFile = file;
		}

		if (cerFile == null) {
			fileModel = this.tableModel.fileModels.get(files[1]);
			cerFile = new File(fileModel.getPath());

			if (!FileDigitalCertificate.isCerFile(cerFile)) {
				JOptionPane.showMessageDialog(this.frmMain, "Please select a certificate file!");
				return;
			}
		} else {
			fileModel = this.tableModel.fileModels.get(files[1]);
			file = new File(fileModel.getPath());
		}

		for (User user : CoreHandler.getInstance().userManager.getUsers()) {
			try {
				if (FileDigitalCertificate.verify(file, user.getPublicKey(), user.getKeySize(), cerFile)) {
					JOptionPane.showMessageDialog(this.frmMain,
							"Digital certificate has been registered by user " + user.getEmail(),
							"Digital certificate is valid", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		JOptionPane.showMessageDialog(this.frmMain, "Digital certificate is invalid!");
	}

	protected void do_btnExport_actionPerformed(ActionEvent e) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Export profile");
		fileChooser.setCurrentDirectory(new java.io.File("."));
		fileChooser.setSelectedFile(new File("profile.bin"));
		if (fileChooser.showSaveDialog(this.frmMain) == JFileChooser.APPROVE_OPTION) {
			File outFile = fileChooser.getSelectedFile();
			try {
				CoreHandler.getInstance().currentUser.save(outFile);
			} catch (IOException ex) {
				JOptionPane.showMessageDialog(this.frmMain, ex.getMessage());
			}
		}
	}

	protected void do_btnEditProfile_actionPerformed(ActionEvent e) {
		if (CoreHandler.getInstance().currentUser != null) {
			this.frmMain.setVisible(false);

			JDialog dialog = new EditDialog();
			dialog.setLocationRelativeTo(this.frmMain);
			dialog.setVisible(true);

			this.frmMain.setVisible(true);
			this.isLogged();
		}
	}

	protected void do_btnLogOut_actionPerformed(ActionEvent e) {
		CoreHandler.getInstance().currentUser = null;
		this.isLogged();
	}

	protected void do_tbFiles_mouseClicked(MouseEvent me) {
		if (me.getClickCount() == 2) {
			FileModel fileModel = this.tableModel.fileModels.get(this.tbFiles.getSelectedRow());
			if (fileModel.isBack()) {
				this.browserBack();
				return;
			}
			File file = new File(fileModel.getPath());
			if (file.isDirectory()) {
				this.browseFiles(file);
			}
		}
	}

	protected void browseFiles(File file) {
		if (!this.currentPath.exists()) {
			this.currentPath = new File("").getAbsoluteFile();
		}
		if (!file.isDirectory()) {
			this.getTxtPath().setText(this.currentPath.getPath());
			return;
		}
		this.tableModel.browse(file);
		this.currentPath = file;

		try {
			this.monitor.stop();
		} catch (Exception e) {
			e.printStackTrace();
		}

		this.monitor.removeObserver(this.observer);

		this.observer = new FileAlterationObserver(this.currentPath);
		this.observer.addListener(this.listener);

		this.monitor.addObserver(this.observer);

		try {
			this.monitor.start();
		} catch (Exception e) {
			e.printStackTrace();
		}

		this.getTxtPath().setText(this.currentPath.getPath());
	}

	protected void browserBack() {
		if (this.currentPath.getParentFile() != null) {
			this.browseFiles(this.currentPath.getParentFile());
		}
	}

	protected void isLogged() {
		if (CoreHandler.getInstance().currentUser == null) {
			this.frmMain.setVisible(false);
			this.loginDialog.requestFocus();
			this.loginDialog.setVisible(true);
			this.frmMain.setVisible(true);
		}
		if (CoreHandler.getInstance().currentUser != null) {
			this.lblUser.setText(CoreHandler.getInstance().currentUser.toString());
		}
	}

	protected void do_frmMain_windowClosing(WindowEvent arg0) {
		do_btnLogOut_actionPerformed(null);
	}

	protected void do_mnuCut_actionPerformed(ActionEvent arg0) {
		if (this.tbFiles.getSelectedRow() == -1)
			return;

		CoreHandler.getInstance().fileBuffer.clear();

		int[] indices = this.tbFiles.getSelectedRows();
		for (int i : indices) {
			if (this.tableModel.fileModels.get(i).isBack())
				continue;
			CoreHandler.getInstance().fileBuffer.add(new File(this.tableModel.fileModels.get(i).getPath()));
		}
		CoreHandler.getInstance().isMove = true;
	}

	protected void do_mnuCopy_actionPerformed(ActionEvent e) {
		CoreHandler.getInstance().fileBuffer.clear();

		int[] indices = this.tbFiles.getSelectedRows();
		for (int i : indices) {
			if (this.tableModel.fileModels.get(i).isBack())
				continue;
			CoreHandler.getInstance().fileBuffer.add(new File(this.tableModel.fileModels.get(i).getPath()));
		}
		CoreHandler.getInstance().isMove = false;
	}

	protected void do_mnuPaste_actionPerformed(ActionEvent e) {
		try {
			this.monitor.stop();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		for (File file : CoreHandler.getInstance().fileBuffer) {
			if (CoreHandler.getInstance().isMove) {

				try {
					if (file.isDirectory()) {
						FileUtils.moveDirectoryToDirectory(file, this.currentPath, true);
					} else if (file.isFile()) {
						FileUtils.moveFileToDirectory(file, this.currentPath, true);
					}
				} catch (IOException ex) {
					JOptionPane.showMessageDialog(this.frmMain, ex.getMessage());
				}

			} else {

				try {
					if (file.isDirectory()) {
						FileUtils.copyDirectoryToDirectory(file, this.currentPath);
					} else if (file.isFile()) {
						FileUtils.copyFileToDirectory(file, this.currentPath);
					}
				} catch (IOException ex) {
					JOptionPane.showMessageDialog(this.frmMain, ex.getMessage());
				}

			}
		}

		this.browseFiles(this.currentPath);

		CoreHandler.getInstance().fileBuffer.clear();
	}

	protected void do_mnuRename_actionPerformed(ActionEvent e) {
		if (this.tbFiles.getSelectedRow() == -1)
			return;

		FileModel fileModel = this.tableModel.fileModels.get(this.tbFiles.getSelectedRow());
		File file = new File(fileModel.getPath());
		if (file.exists()) {
			String newName = JOptionPane.showInputDialog(this.frmMain, "Enter a new name", file.getName());
			if (newName == null)
				return;
			File newFile = new File(file.getParent(), newName);
			if (newFile.exists()) {
				JOptionPane.showMessageDialog(this.frmMain,
						"There is already a file with the same name in this location!");
				return;
			}
			try {
				try {
					this.monitor.stop();
				} catch (Exception ex) {
					ex.printStackTrace();
				}

				if (file.isDirectory()) {
					FileUtils.moveDirectory(file, newFile);
				} else if (file.isFile()) {
					FileUtils.moveFile(file, newFile);
				}

				this.browseFiles(this.currentPath);
			} catch (IOException ex) {
				JOptionPane.showMessageDialog(this.frmMain, ex.getMessage());
			}
		}
	}

	protected void do_mnuDelete_actionPerformed(ActionEvent e) {
		if (this.tbFiles.getSelectedRow() == -1)
			return;

		FileModel fileModel = this.tableModel.fileModels.get(this.tbFiles.getSelectedRow());
		File file = new File(fileModel.getPath());
		if (file.exists()) {
			if (JOptionPane.showConfirmDialog(this.frmMain, "Are you sure?", "Confirm",
					JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
				try {
					this.monitor.stop();
				} catch (Exception ex) {
					ex.printStackTrace();
				}

				try {
					FileUtils.forceDelete(file);
				} catch (IOException ex) {
					JOptionPane.showMessageDialog(this.frmMain, ex.getMessage());
				}

				this.browseFiles(this.currentPath);
			}
		}
	}

	protected void checkItemMenu() {
		if (this.tbFiles.getSelectedRow() == -1) {
			this.mnuCut.setEnabled(false);
			this.mnuCopy.setEnabled(false);
			this.mnuRename.setEnabled(false);
			this.mnuDelete.setEnabled(false);
		} else {
			this.mnuCut.setEnabled(true);
			this.mnuCopy.setEnabled(true);
			this.mnuRename.setEnabled(true);
			this.mnuDelete.setEnabled(true);
		}
		this.mnuPaste.setEnabled(!CoreHandler.getInstance().fileBuffer.isEmpty());
	}
}
