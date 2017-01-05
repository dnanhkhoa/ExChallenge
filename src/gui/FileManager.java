package gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import core.handler.CoreHandler;
import core.model.TableModel;

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
		CoreHandler.getInstance();
		this.tableModel = new TableModel();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initializeGUI() {
		this.frmMain = new JFrame();
		this.frmMain.setTitle("ExChallenge");
		this.frmMain.setBounds(100, 100, 850, 600);
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
			btnSignature = new JButton("Signature");
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

	protected static void do_btnUp_actionPerformed(ActionEvent arg0) {
		System.out.println("Fire up");
	}

	protected static void do_txtPath_actionPerformed(ActionEvent e) {
	}

	private void browseFiles(String path) {

	}

	protected static void do_btnEncrypt_actionPerformed(ActionEvent e) {
	}

	protected static void do_btnDecrypt_actionPerformed(ActionEvent e) {
	}

	protected static void do_btnSignature_actionPerformed(ActionEvent e) {
	}

	protected static void do_btnVerify_actionPerformed(ActionEvent e) {
	}

	protected static void do_btnExport_actionPerformed(ActionEvent e) {
	}

	protected static void do_btnEditProfile_actionPerformed(ActionEvent e) {
	}

	protected static void do_btnLogOut_actionPerformed(ActionEvent e) {
	}
}
