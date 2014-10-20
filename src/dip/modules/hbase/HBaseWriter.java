package dip.modules.hbase;

import dip.core.Metadata;
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
	public HBaseWriter(HTable table) {
		super(table);
	}

	@Override
	public void write(Put obj, HTable table, Metadata metadata) {
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
	}
}

