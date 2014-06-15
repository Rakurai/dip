package dip.modules.hbase;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.MasterNotRunningException;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.ZooKeeperConnectionException;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;

public class HBaseTableFactory {
	private HBaseAdmin hbase;
	private Configuration conf;

	public HBaseTableFactory(String hbaseSitePath, String host, int port) throws MasterNotRunningException, ZooKeeperConnectionException, IOException {
		conf = HBaseConfiguration.create();
		conf.addResource(hbaseSitePath);
		conf.set("hbase.master", host + ":" + port);
		hbase = new HBaseAdmin(conf);
	}
	
	public HTable getTable(String db_name, String table_name) throws IOException {
		if (!hbase.tableExists(table_name)){
			HTableDescriptor new_table = new HTableDescriptor(TableName.valueOf("table_name"));
			new_table.addFamily(new HColumnDescriptor("content"));
			hbase.createTable(new_table);
		}
		
		return new HTable(conf, table_name);
	}
	
	public void cleanup() {
		try {
			hbase.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		conf.clear();
	}

}
