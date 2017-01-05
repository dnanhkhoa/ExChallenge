package gui.user;

import javax.swing.JDialog;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

public class EncryptDialog extends JDialog {

	/**
	 * Create the dialog.
	 */
	public EncryptDialog() {
		setTitle("Encrypt");
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new FormLayout(new ColumnSpec[] {},
			new RowSpec[] {}));

	}

}
