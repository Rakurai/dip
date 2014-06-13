package dip.modules.converters.xml;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.concurrent.BlockingQueue;

import org.openexi.sax.Transmogrifier;
import org.xml.sax.InputSource;

import dip.modules.converters.AbstractConverter;

public class XMLStringCompressor extends AbstractConverter<String, String> {

	private Transmogrifier transmogrifier;

	public XMLStringCompressor(BlockingQueue<String> input, BlockingQueue<String> output, Transmogrifier transmogrifier) {
		super(input, output);
		this.transmogrifier = transmogrifier;
	}

	@Override
	protected String convert(String str) throws Exception {
		OutputStream ostream = new ByteArrayOutputStream();
		transmogrifier.setOutputStream(ostream);
//		transmogrifier.encode(new InputSource(str));
		transmogrifier.encode(new InputSource(new ByteArrayInputStream(str.getBytes("utf-8"))));
		return ostream.toString();
	}

}
