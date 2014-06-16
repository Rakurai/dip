package dip.modules;

public abstract class AbstractConverter<INPUT, OUTPUT> extends AbstractModule implements Converter<INPUT, OUTPUT> {

	@Override
	public void cleanup() { }

}
