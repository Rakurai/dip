package dip.modules;

public interface CompositeConverter<INPUT extends Object, OUTPUT extends Object> extends Converter<INPUT, OUTPUT> {
	public void addConverter(Converter<? extends Object, ? extends Object> converter);
}
