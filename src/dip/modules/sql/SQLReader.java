package dip.modules.sql;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import dip.core.Metadata;
import dip.modules.AbstractReader;

public class SQLReader extends AbstractReader<ResultSet, Map<String, Object>> {
	ResultSetMetaData metaData;

	public SQLReader(ResultSet rs) throws SQLException {
		super(rs);
		this.metaData = rs.getMetaData();
	}

	@Override
	public Map<String, Object> read(ResultSet rs, Metadata metadata) throws SQLException {
		Map<String, Object> map = new HashMap<String, Object>();

		if (!rs.next())
			return null;

		for (int i = 0; i < metaData.getColumnCount(); i++)
			map.put(metaData.getColumnName(i), rs.getObject(i));

		return map;
	}
}
