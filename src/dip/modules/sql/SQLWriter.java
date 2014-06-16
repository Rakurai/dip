package dip.modules.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

import dip.core.Metadata;
import dip.modules.AbstractWriter;

public class SQLWriter extends AbstractWriter<Map<String, Object>, Connection> {

	private String table_name;

	public SQLWriter(Connection connection, String table_name) throws SQLException {
		super(connection);
		this.table_name = table_name;
	}

	@Override
	public void write(Map<String, Object> map, Connection connection, Metadata metadata) throws Exception {
		ArrayList<Object> vals = new ArrayList<Object>();
		StringBuilder keyString = new StringBuilder();
		StringBuilder valString = new StringBuilder();
		boolean first = true;
		
		for (Entry<String, Object> entry: map.entrySet()) {
			if (!first) {
				keyString.append(',');
				valString.append(',');
			}
			else
				first = false;
			keyString.append(entry.getKey());
			valString.append('?');
			vals.add(entry.getValue());
		}

		// INSERT INTO table (key1,key2,...) VALUES (val1,val2,...)
		String sql = "INSERT INTO " + table_name + " (" + keyString.toString() + ") VALUES (" + valString.toString() + ")";
		PreparedStatement pst = connection.prepareStatement(sql);

		for (int i = 0; i < vals.size(); i++)
			pst.setObject(i+1, vals.get(i));

		pst.executeUpdate();
	}
}
