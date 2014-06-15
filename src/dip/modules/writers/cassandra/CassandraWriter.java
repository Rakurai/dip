package dip.modules.writers.cassandra;

import dip.modules.AbstractWriter;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Metadata;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.Statement;

public class CassandraWriter extends AbstractWriter<Statement, Session> {
/*	private static Cluster cluster = null;
	private Session session;
*/
	public CassandraWriter(Session session) {
		super(session);
	}
	
/*	public synchronized void init(String node, String host, int port, String table_name) throws Exception {
		if (cluster == null) {
			this.cluster = Cluster.builder().addContactPoint(node).build();
			Metadata metadata = cluster.getMetadata();
			System.out.printf("Connected to cluster: %s\n", metadata.getClusterName());
		}
		session = cluster.connect();
	}
*/
	@Override
	public void write(Statement obj, Session session) {
		while (true) {
			try {
				session.execute(obj);
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
	}

	@Override
	public synchronized void cleanup() {
		super.cleanup();
/*		if (cluster != null) {
			cluster.close();
		}
*/	}
}

