package dip.modules;

import dip.core.Metadata;

public interface Converter<INPUT, OUTPUT> {
	public OUTPUT convert(INPUT in, Metadata metadata) throws Exception;

	public Class<INPUT> getInputType();
}
