package dip.modules.sql;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import dip.core.Metadata;
import dip.modules.AbstractWriter;
import dip.modules.IOMapper;

public class SQLWriter extends AbstractWriter<List<Object>, PreparedStatement> {

	private PreparedStatement statement;
	private int batch_size = 1000;
	private int n = 0;

	public SQLWriter(final PreparedStatement statement) {
		super(new IOMapper<PreparedStatement>() {

			@Override
			public PreparedStatement acquire(Metadata metadata) {
				// TODO Auto-generated method stub
				return statement;
			}

			@Override
			public void release(PreparedStatement val) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void cleanup() throws Exception {
				statement.executeBatch();
				statement.close();
			}
			
		});
	}

	public SQLWriter(IOMapper<PreparedStatement> mapper) {
		super(mapper);
	}
	
	@Override
	public void write(List<Object> vals, PreparedStatement statement, Metadata metadata) throws Exception {
		for (int i = 0; i < vals.size(); i++)
			statement.setObject(i+1, vals.get(i));

		statement.addBatch();
		if (++n % batch_size == 0)
			statement.executeBatch();
	}
	
	@Override
	public void cleanup() {
		try {
			statement.executeBatch();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
