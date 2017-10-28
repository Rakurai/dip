package dip.core;

import org.apache.commons.lang3.builder.HashCodeBuilder;

public class MapWrapper extends Object {
	private Object o;

	public MapWrapper (Object o) {
		set(o);
	}

	public Object get() {
		return o;
	}

	public void set(Object o) {
		this.o = o;
	}

	@Override
	public int hashCode() {
		return o.hashCode();
		/*		return new HashCodeBuilder(17, 31). // two randomly chosen prime numbers
				// if deriving: appendSuper(super.hashCode()).
				append(o).
				toHashCode();
*/
	}

	public boolean equals(Object obj) {
		if (obj == o)
			return true;
		if (!(obj instanceof MapWrapper))
			return false;
		if (((MapWrapper)obj).get() == o)
			return true;
		return false;
	}
}