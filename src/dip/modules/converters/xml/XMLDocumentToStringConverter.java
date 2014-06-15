package dip.modules.converters.xml;

import java.io.StringWriter;

import javax.xml.transform.Transformer;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

import dip.modules.AbstractConverter;

public class XMLDocumentToStringConverter extends AbstractConverter<Document, String> {

	protected Transformer transformer;

	public XMLDocumentToStringConverter(Transformer transformer) throws Exception {
		this.transformer = transformer;
	}

	@Override
	public String convert(Document doc) throws Exception {
		StringWriter writer = new StringWriter();
		transformer.transform(new DOMSource(doc), new StreamResult(writer));
		return writer.getBuffer().toString();
	}

}
