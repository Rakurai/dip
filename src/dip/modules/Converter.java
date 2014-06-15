package dip.modules;

public interface Converter<INPUT, OUTPUT> extends Module {
	public OUTPUT convert(INPUT in) throws Exception;
}
