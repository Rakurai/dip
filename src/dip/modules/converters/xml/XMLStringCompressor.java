package dip.modules.converters.xml;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.concurrent.BlockingQueue;

import org.openexi.sax.Transmogrifier;
import org.xml.sax.InputSource;

import dip.modules.converters.AbstractConverter;

public class XMLStringCompressor extends AbstractConverter<String, byte[]> {

	private Transmogrifier transmogrifier;

	public XMLStringCompressor(BlockingQueue<String> input, BlockingQueue<byte[]> output, Transmogrifier transmogrifier) {
		super(input, output);
		this.transmogrifier = transmogrifier;
	}

	@Override
	protected byte[] convert(String str) throws Exception {
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		transmogrifier.setOutputStream(byteStream);
		transmogrifier.encode(new InputSource(new ByteArrayInputStream(str.getBytes("utf-8"))));
		return byteStream.toByteArray();
	}

}
