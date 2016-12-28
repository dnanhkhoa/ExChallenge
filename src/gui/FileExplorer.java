package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JToolBar;
import javax.swing.JComboBox;
import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.JTable;
import javax.swing.JMenuBar;

public class FileExplorer {

	private JFrame frmExchallenge;
	private JToolBar toolBar;
	private JToolBar statusBar;
	private JComboBox cbbAddressBar;
	private JSplitPane mainSplitPane;
	private JScrollPane scrollLeftPane;
	private JScrollPane scrollRightPane;
	private JTree fileTreeView;
	private JTable fileListView;
	private JMenuBar menuBar;

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
					FileExplorer window = new FileExplorer();
					window.frmExchallenge.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FileExplorer() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmExchallenge = new JFrame();
		frmExchallenge.setTitle("ExChallenge");
		frmExchallenge.setBounds(100, 100, 900, 600);
		frmExchallenge.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmExchallenge.getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,},
			new RowSpec[] {
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		this.frmExchallenge.getContentPane().add(getToolBar(), "1, 1, 5, 1");
		this.frmExchallenge.getContentPane().add(getCbbAddressBar(), "4, 3, fill, default");
		this.frmExchallenge.getContentPane().add(getMainSplitPane(), "2, 5, 3, 1, fill, fill");
		this.frmExchallenge.getContentPane().add(getStatusBar(), "1, 7, 5, 1");
		this.frmExchallenge.setJMenuBar(getMenuBar());
	}

	private JToolBar getToolBar() {
		if (toolBar == null) {
			toolBar = new JToolBar();
		}
		return toolBar;
	}
	private JToolBar getStatusBar() {
		if (statusBar == null) {
			statusBar = new JToolBar();
			statusBar.setFloatable(false);
		}
		return statusBar;
	}
	private JComboBox getCbbAddressBar() {
		if (cbbAddressBar == null) {
			cbbAddressBar = new JComboBox();
			cbbAddressBar.setEditable(true);
		}
		return cbbAddressBar;
	}
	private JSplitPane getMainSplitPane() {
		if (mainSplitPane == null) {
			mainSplitPane = new JSplitPane();
			mainSplitPane.setResizeWeight(0.4);
			mainSplitPane.setLeftComponent(getScrollLeftPane());
			mainSplitPane.setRightComponent(getScrollRightPane());
		}
		return mainSplitPane;
	}
	private JScrollPane getScrollLeftPane() {
		if (scrollLeftPane == null) {
			scrollLeftPane = new JScrollPane();
			scrollLeftPane.setViewportView(getFileTreeView());
		}
		return scrollLeftPane;
	}
	private JScrollPane getScrollRightPane() {
		if (scrollRightPane == null) {
			scrollRightPane = new JScrollPane();
			scrollRightPane.setViewportView(getFileListView());
		}
		return scrollRightPane;
	}
	private JTree getFileTreeView() {
		if (fileTreeView == null) {
			fileTreeView = new JTree();
		}
		return fileTreeView;
	}
	private JTable getFileListView() {
		if (fileListView == null) {
			fileListView = new JTable();
			fileListView.setShowGrid(false);
		}
		return fileListView;
	}
	private JMenuBar getMenuBar() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
		}
		return menuBar;
	}
}
