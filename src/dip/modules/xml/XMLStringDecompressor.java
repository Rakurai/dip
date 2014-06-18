package dip.modules.xml;

import java.io.ByteArrayInputStream;
import java.io.StringWriter;

import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;

import org.openexi.sax.EXIReader;
import org.xml.sax.InputSource;

import dip.core.Metadata;
import dip.modules.AbstractConverter;

public class XMLStringDecompressor extends AbstractConverter<byte[], String> {

	private TransformerHandler transformerHandler;
	private EXIReader reader;

	public XMLStringDecompressor(EXIReader reader)  {
		SAXTransformerFactory saxTransformerFactory = (SAXTransformerFactory)SAXTransformerFactory.newInstance();
		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
		saxParserFactory.setNamespaceAware(true);
		try {
			this.transformerHandler = saxTransformerFactory.newTransformerHandler();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		reader.setContentHandler(transformerHandler);
		this.reader = reader;
	}

	@Override
	public String convert(byte[] bytes, Metadata metadata) throws Exception {
		StringWriter stringWriter = new StringWriter();
		transformerHandler.setResult(new StreamResult(stringWriter));
		reader.parse(new InputSource(new ByteArrayInputStream(bytes)));
		return stringWriter.getBuffer().toString();
	}

}
