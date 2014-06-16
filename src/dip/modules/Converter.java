package dip.modules;

public interface Converter<INPUT, OUTPUT> {
	public OUTPUT convert(INPUT in) throws Exception;
}
