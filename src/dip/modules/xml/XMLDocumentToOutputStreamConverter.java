package dip.modules.xml;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.StringWriter;

import javax.xml.transform.Transformer;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

import dip.core.Metadata;
import dip.modules.AbstractConverter;

public class XMLDocumentToOutputStreamConverter extends AbstractConverter<Document, OutputStream> {

	protected Transformer transformer;

	public XMLDocumentToOutputStreamConverter(Transformer transformer) throws Exception {
		this.transformer = transformer;
	}

	@Override
	public OutputStream convert(Document doc, Metadata metadata) throws Exception {
		OutputStream stream = new ByteArrayOutputStream();
		transformer.transform(new DOMSource(doc), new StreamResult(stream));
		return stream;
	}

}
