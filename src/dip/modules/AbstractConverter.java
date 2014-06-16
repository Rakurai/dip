package dip.modules;

public abstract class AbstractConverter<INPUT, OUTPUT> extends AbstractModule implements Converter<INPUT, OUTPUT> {

	@Override
	public abstract OUTPUT convert(INPUT in) throws Exception;

	@Override
	public void cleanup() { }

}
