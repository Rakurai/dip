package dip.modules.xml;

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import dip.core.Metadata;
import dip.modules.AbstractConverter;

public class XMLInputStreamToDocumentConverter extends AbstractConverter<InputStream, Document> {
	private DocumentBuilder builder;

	public XMLInputStreamToDocumentConverter() throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		builder = factory.newDocumentBuilder();
	}

	@Override
	public Document convert(InputStream stream, Metadata metadata) throws Exception {
		InputSource source = new InputSource(stream);
        Document doc = builder.parse(source);
        stream.close();
        return doc;
	}
}
