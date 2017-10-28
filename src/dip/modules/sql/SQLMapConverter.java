package dip.modules.sql;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import dip.core.Metadata;
import dip.modules.AbstractConverter;
import dip.modules.Converter;

// just converts any kind of Map<String, ?> to a map suitable for passing to an SQLWriter
public class SQLMapConverter extends AbstractConverter<Map<String, String>, Map<String, Object>> {

	@Override
	public Map<String, Object> convert(Map<String, String> in, Metadata metadata)
			throws Exception {
		Map<String, Object> out = new HashMap<String, Object>();
		for (Entry<String, ? extends Object> entry: in.entrySet()) {
			out.put(entry.getKey(), entry.getValue());
		}

		return out;
	}

}

