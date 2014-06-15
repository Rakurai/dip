package dip.modules.converters.xml;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import dip.modules.AbstractConverter;

public class XMLStringToDocumentConverter extends AbstractConverter<String, Document> {
	private DocumentBuilder builder;

	public XMLStringToDocumentConverter() throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		builder = factory.newDocumentBuilder();
	}

	@Override
	public Document convert(String str) throws Exception {
        InputSource source = new InputSource(new StringReader(str));
        return builder.parse(source);
	}

}
