package dip.modules.converters.xml;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.openexi.sax.Transmogrifier;
import org.xml.sax.InputSource;

import dip.core.Metadata;
import dip.modules.AbstractConverter;

public class XMLStringCompressor extends AbstractConverter<String, byte[]> {

	private Transmogrifier transmogrifier;

	public XMLStringCompressor(Transmogrifier transmogrifier) {
		this.transmogrifier = transmogrifier;
	}

	@Override
	public byte[] convert(String str, Metadata metadata) throws Exception {
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		transmogrifier.setOutputStream(byteStream);
		transmogrifier.encode(new InputSource(new ByteArrayInputStream(str.getBytes("utf-8"))));
		return byteStream.toByteArray();
	}

}
