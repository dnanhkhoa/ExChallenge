package gui;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import core.model.TableModel;

public class FileManager {

	private JFrame frmMain;
	private JToolBar tlbHeader;
	private JButton btnUp;
	private JScrollPane scrollPane;
	private JTable tbFiles;
	private JToolBar tlbFooter;
	private JLabel lblNewLabel;
	private JButton btnEncrypt;
	private JButton btnDecrypt;
	private JButton btnSignature;
	private JButton btnVerify;
	private JButton btnExport;
	private JButton btnEditProfile;
	private JButton btnLogOut;
	private JTextField txtPath;

	private TableModel tableModel;

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
		this.tableModel = new TableModel();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initializeGUI() {
		this.frmMain = new JFrame();
		this.frmMain.setTitle("ExChallenge");
		this.frmMain.setBounds(100, 100, 800, 500);
		this.frmMain.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.frmMain.getContentPane()
				.setLayout(new FormLayout(new ColumnSpec[] {
						FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("default:grow"), FormSpecs.RELATED_GAP_COLSPEC, },
						new RowSpec[] { FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
								FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode("fill:default"),
								FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode("default:grow"),
								FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
								FormSpecs.RELATED_GAP_ROWSPEC, }));
		this.frmMain.getContentPane().add(getTlbHeader(), "2, 2, 3, 1");
		this.frmMain.getContentPane().add(getBtnUp(), "2, 4");
		this.frmMain.getContentPane().add(getTxtPath(), "4, 4, fill, default");
		this.frmMain.getContentPane().add(getScrollPane(), "2, 6, 3, 1, fill, fill");
		this.frmMain.getContentPane().add(getTlbFooter(), "2, 8, 3, 1");
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
		}
		return btnUp;
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
			tbFiles.setRowHeight(18);
			tbFiles.setFillsViewportHeight(true);

			tbFiles.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			tbFiles.setFont(new Font("Tahoma", Font.PLAIN, 18));
			tbFiles.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			tbFiles.setModel(tableModel); // Add data model
		}
		return tbFiles;
	}

	private JToolBar getTlbFooter() {
		if (tlbFooter == null) {
			tlbFooter = new JToolBar();
			tlbFooter.setFloatable(false);
			tlbFooter.add(getLblNewLabel());
		}
		return tlbFooter;
	}

	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("New label");
		}
		return lblNewLabel;
	}

	private JButton getBtnEncrypt() {
		if (btnEncrypt == null) {
			btnEncrypt = new JButton("Encrypt");
		}
		return btnEncrypt;
	}

	private JButton getBtnDecrypt() {
		if (btnDecrypt == null) {
			btnDecrypt = new JButton("Decrypt");
		}
		return btnDecrypt;
	}

	private JButton getBtnSignature() {
		if (btnSignature == null) {
			btnSignature = new JButton("Signature");
		}
		return btnSignature;
	}

	private JButton getBtnVerify() {
		if (btnVerify == null) {
			btnVerify = new JButton("Verify");
		}
		return btnVerify;
	}

	private JButton getBtnExport() {
		if (btnExport == null) {
			btnExport = new JButton("Export");
		}
		return btnExport;
	}

	private JButton getBtnEditProfile() {
		if (btnEditProfile == null) {
			btnEditProfile = new JButton("Edit profile");
		}
		return btnEditProfile;
	}

	private JButton getBtnLogOut() {
		if (btnLogOut == null) {
			btnLogOut = new JButton("Log out");
		}
		return btnLogOut;
	}

	private JTextField getTxtPath() {
		if (txtPath == null) {
			txtPath = new JTextField();
			txtPath.setColumns(10);
		}
		return txtPath;
	}
}
