package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JToolBar;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class FileExplorer {

	private JFrame frmExchallenge;
	private final JToolBar toolBar = new JToolBar();
	private final JTextField textField = new JTextField();
	private final JScrollPane scrollPane = new JScrollPane();
	private final JTable table = new JTable();
	private final JToolBar toolBar_1 = new JToolBar();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
		this.frmExchallenge.setTitle("ExChallenge 1.0.0");
		frmExchallenge.setBounds(100, 100, 800, 600);
		frmExchallenge.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frmExchallenge.getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,}));
		
		this.frmExchallenge.getContentPane().add(this.toolBar, "2, 2, 3, 1");
		this.textField.setColumns(10);
		
		this.frmExchallenge.getContentPane().add(this.textField, "4, 4, fill, default");
		
		this.frmExchallenge.getContentPane().add(this.scrollPane, "2, 6, 3, 1, fill, fill");
		
		this.scrollPane.setViewportView(this.table);
		
		this.frmExchallenge.getContentPane().add(this.toolBar_1, "2, 8, 3, 1");
	}

}
