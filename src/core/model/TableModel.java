package core.model;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.table.AbstractTableModel;

// Done

public final class TableModel extends AbstractTableModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final String[] columnNames = { "Name", "Size", "Type", "Modified" };

	@SuppressWarnings("rawtypes")
	private static final Class[] columnClasses = { JLabel.class, String.class, String.class, String.class };

	public List<FileModel> fileModels;

	public TableModel() {
		this.fileModels = new ArrayList<>();
	}

	public void browse(File dir) {
		this.fileModels.clear();

		try {
			this.fileModels.add(new FileModel(dir.getPath(), true));
		} catch (Exception e) {
		}

		File[] files = dir.listFiles();
		for (File file : files) {
			try {
				this.fileModels.add(new FileModel(file.getPath()));
			} catch (Exception e) {
			}
		}
		fireTableDataChanged();
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
		return this.fileModels.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		FileModel fileModel = this.fileModels.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return fileModel;
		case 1:
			return fileModel.getSize();
		case 2:
			return fileModel.getType();
		case 3:
			return fileModel.getModified();
		}
		return null;
	}
}
