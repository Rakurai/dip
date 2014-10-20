package dip.modules.xml;

import java.io.ByteArrayInputStream;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXParseException;

import dip.core.Metadata;
import dip.modules.AbstractConverter;

public class XMLStringToDocumentConverter extends AbstractConverter<String, Document> {
	private DocumentBuilder builder;

	public XMLStringToDocumentConverter() throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		builder = factory.newDocumentBuilder();
	}

	@Override
	public Document convert(String str, Metadata metadata) throws Exception {
		StringReader stringReader = new StringReader(str);
		InputSource source = new InputSource(stringReader);
		try {
			Document doc = builder.parse(source);
	        return doc;
		}
		finally {
			stringReader.close();
		}
	}

}
