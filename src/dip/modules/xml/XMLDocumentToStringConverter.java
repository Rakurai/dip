package dip.modules.xml;

import java.io.StringWriter;

import javax.xml.transform.Transformer;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

import dip.core.Metadata;
import dip.modules.AbstractConverter;

public class XMLDocumentToStringConverter extends AbstractConverter<Document, String> {

	protected Transformer transformer;

	public XMLDocumentToStringConverter(Transformer transformer) throws Exception {
		this.transformer = transformer;
	}

	@Override
	public String convert(Document doc, Metadata metadata) throws Exception {
		StringWriter writer = new StringWriter();
		transformer.transform(new DOMSource(doc), new StreamResult(writer));
		String str = writer.getBuffer().toString();
		writer.close();
		return str;
	}

}
