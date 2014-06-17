package dip.modules;

import java.lang.reflect.*;

public abstract class AbstractConverter<INPUT extends Object, OUTPUT> extends AbstractModule implements Converter<INPUT, OUTPUT> {

	@Override
	public void cleanup() { }

	public Class<INPUT> getInputType() {
		return (Class<INPUT>)((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	
}
