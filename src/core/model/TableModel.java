package core.model;

import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;

import javax.swing.table.AbstractTableModel;

public final class TableModel extends AbstractTableModel implements Serializable, Observer {

	private static final long serialVersionUID = 1L;

	private static final String[] columnNames = { "Name", "Size", "Type", "Modified" };
	
	@SuppressWarnings("rawtypes")
	private static final Class[] columnClasses = { String.class, String.class, String.class, String.class};
	
	@Override
	public void update(Observable arg0, Object arg1) {

	}

	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}
	
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return columnClasses[columnIndex];
	}
	
	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
	}

}
