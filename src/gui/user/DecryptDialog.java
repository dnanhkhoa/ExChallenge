package gui.user;

import javax.swing.JDialog;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

public class DecryptDialog extends JDialog {

	/**
	 * Create the dialog.
	 */
	public DecryptDialog() {
		setTitle("Decrypt");
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new FormLayout(new ColumnSpec[] {},
			new RowSpec[] {}));

	}

}
