package dip.modules;

import java.util.ArrayList;
import java.util.List;

import dip.core.Metadata;

public class CompositeConverter<INPUT extends Object, OUTPUT extends Object> extends AbstractConverter<INPUT, OUTPUT> {

	List<Converter<Object, Object>> chain = new ArrayList<Converter<Object, Object>>();
	
	public void addConverter(Converter<? extends Object, ? extends Object> converter) throws Exception {
		if (converter == null)
			throw new Exception("adding null converter");
		chain.add((Converter<Object, Object>) converter);
	}

	@Override
	public OUTPUT convert(INPUT in, Metadata metadata) throws Exception {
		Object obj = (Object) in;
		for (Converter<Object, Object> converter: chain) {
			if (obj == null) {
//				System.out.println("obj is null at converter stage " + chain.indexOf(converter));
				return null;
			}
			obj = converter.convert(obj, metadata);
		}
		return (OUTPUT)obj;
	}

}
