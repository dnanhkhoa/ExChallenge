package gui;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.JComboBox;
import javax.swing.JLabel;

public class FileManager {

	private JFrame frmMain;
	private JToolBar toolBar;
	private JButton btnNewButton;
	private JScrollPane scrollPane;
	private JTable table;
	private JToolBar toolBar_1;
	private JComboBox comboBox;
	private JLabel lblNewLabel;
	private JButton btnNewButton_1;
	private JButton btnDecrypt;
	private JButton btnSignature;
	private JButton btnVerify;
	private JButton btnExport;
	private JButton btnEdit;
	private JButton btnLogOut;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.frmMain = new JFrame();
		this.frmMain.setTitle("ExChallenge");
		this.frmMain.setBounds(100, 100, 800, 500);
		this.frmMain.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.frmMain.getContentPane()
				.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("fill:default"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,}));
		this.frmMain.getContentPane().add(getToolBar(), "2, 2, 3, 1");
		this.frmMain.getContentPane().add(getBtnNewButton(), "2, 4");
		this.frmMain.getContentPane().add(getComboBox(), "4, 4, fill, default");
		this.frmMain.getContentPane().add(getScrollPane(), "2, 6, 3, 1, fill, fill");
		this.frmMain.getContentPane().add(getToolBar_1(), "2, 8, 3, 1");
	}

	private JToolBar getToolBar() {
		if (toolBar == null) {
			toolBar = new JToolBar();
			toolBar.add(getBtnNewButton_1());
			toolBar.add(getBtnDecrypt());
			toolBar.add(getBtnSignature());
			toolBar.add(getBtnVerify());
			toolBar.add(getBtnExport());
			toolBar.add(getBtnEdit());
			toolBar.add(getBtnLogOut());
		}
		return toolBar;
	}

	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("");
		}
		return btnNewButton;
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getTable());
		}
		return scrollPane;
	}

	private JTable getTable() {
		if (table == null) {
			table = new JTable();
		}
		return table;
	}

	private JToolBar getToolBar_1() {
		if (toolBar_1 == null) {
			toolBar_1 = new JToolBar();
			toolBar_1.add(getLblNewLabel());
		}
		return toolBar_1;
	}
	private JComboBox getComboBox() {
		if (comboBox == null) {
			comboBox = new JComboBox();
			comboBox.setEditable(true);
		}
		return comboBox;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("New label");
		}
		return lblNewLabel;
	}
	private JButton getBtnNewButton_1() {
		if (btnNewButton_1 == null) {
			btnNewButton_1 = new JButton("Encrypt");
		}
		return btnNewButton_1;
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
	private JButton getBtnEdit() {
		if (btnEdit == null) {
			btnEdit = new JButton("Edit");
		}
		return btnEdit;
	}
	private JButton getBtnLogOut() {
		if (btnLogOut == null) {
			btnLogOut = new JButton("Log out");
		}
		return btnLogOut;
	}
}