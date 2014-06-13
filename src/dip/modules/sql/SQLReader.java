package dip.modules.sql;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

import dip.modules.AbstractReader;

public class SQLReader extends AbstractReader<Map<String, Object>> {

	private ResultSet resultSet;
	ResultSetMetaData metaData;

	public SQLReader(BlockingQueue<Map<String, Object>> queue, ResultSet results) throws SQLException {
		super(queue);
		this.resultSet = results;
		this.metaData = results.getMetaData();
	}

	@Override
	protected Map<String, Object> read() throws SQLException {
		Map<String, Object> map = new HashMap<String, Object>();

		if (!resultSet.next())
			return null;

		for (int i = 0; i < metaData.getColumnCount(); i++)
			map.put(metaData.getColumnName(i), resultSet.getObject(i));

		return map;
	}
}
