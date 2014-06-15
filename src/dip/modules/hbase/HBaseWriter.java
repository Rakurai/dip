package dip.modules.hbase;

import dip.modules.AbstractWriter;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor; 
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;

public class HBaseWriter extends AbstractWriter<Put, HTable> {
/*	private static Configuration conf = null;
	private HBaseAdmin hbase;
	private HTable table;
*/	
	public HBaseWriter(HTable table) {
		super(table);
	}
/*	
	public synchronized void init(String hbaseSitePath, String host, int port, String table_name) throws Exception {
		if (conf == null) {
			this.conf = HBaseConfiguration.create();
			conf.addResource(hbaseSitePath);
			conf.set("hbase.master", host + ":" + port);
		}
		hbase = new HBaseAdmin(conf);
		
		if (!hbase.tableExists(table_name)){
			HTableDescriptor new_table = new HTableDescriptor(TableName.valueOf("table_name"));
			new_table.addFamily(new HColumnDescriptor("content"));
			hbase.createTable(new_table);
		}
		
		table = new HTable(conf, table_name);
	}
*/
	@Override
	public void write(Put obj, HTable table) {
		while (true) {
			try {
				table.put(obj);
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
	}

	@Override
	public synchronized void cleanup() {
		super.cleanup();
/*		if (conf != null) {
			try {
				hbase.close();
				conf.clear();
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
*/
	}
}

