package core.model;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

// Done

public class JLabelRenderer extends DefaultTableCellRenderer {

	private static final long serialVersionUID = 1L;

	public void fillColor(JTable table, JLabel label, boolean isSelected) {
		if (isSelected) {
			label.setBackground(table.getSelectionBackground());
			label.setForeground(table.getSelectionForeground());
		} else {
			label.setBackground(table.getBackground());
			label.setForeground(table.getForeground());
		}

	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {

		if (value instanceof FileModel) {
			FileModel fileModel = (FileModel) value;
			JLabel label = new JLabel(fileModel.getName());
			label.setIcon(fileModel.getIcon());
			label.setOpaque(true);
			fillColor(table, label, isSelected);
			return label;

		}
		return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	}

}
