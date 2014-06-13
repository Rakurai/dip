package dip.modules.sql;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.BlockingQueue;

import dip.modules.AbstractWriter;

public class SQLWriter extends AbstractWriter<Map<String, Object>> {

	Statement statement;
	private String table_name;

	public SQLWriter(BlockingQueue<Map<String, Object>> queue, Connection connection, String table_name) throws SQLException {
		super(queue);
		this.statement = connection.createStatement();
		this.table_name = table_name;
	}

	@Override
	protected void write(Map<String, Object> pairs) throws Exception {
		String sql = "INSERT INTO " + table_name + " VALUES(" + generateValues(pairs) + ")";
		statement.executeUpdate(sql);
	}

	private String generateValues(Map<String, Object> map) {
		StringBuilder sb = new StringBuilder();
		for (Entry<String, Object> entry: map.entrySet()) {
			sb.append(entry.getKey());
			sb.append('=');
			sb.append(entry.getValue());
			sb.append(',');
		}
		return sb.toString();
	}
}
