package dip.modules.xml;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import dip.core.Metadata;
import dip.modules.AbstractConverter;

public class XMLStringToDocumentConverter extends AbstractConverter<String, Document> {
	private DocumentBuilder builder;

	public XMLStringToDocumentConverter() throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		builder = factory.newDocumentBuilder();
	}

	@Override
	public Document convert(String str, Metadata metadata) throws Exception {
		StringReader stringReader = new StringReader(str);
		InputSource source = new InputSource(stringReader);
        Document doc = builder.parse(source);
        stringReader.close();
        return doc;
	}

}
