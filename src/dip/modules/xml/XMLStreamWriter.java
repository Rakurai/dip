package dip.modules.xml;

import java.io.OutputStream;

import javax.xml.transform.Transformer;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

import dip.core.Metadata;
import dip.modules.AbstractWriter;
import dip.modules.IOMapper;

public class XMLStreamWriter extends AbstractWriter<Document, OutputStream> {

	private Transformer transformer;

	public XMLStreamWriter(Transformer transformer, IOMapper<OutputStream> mapper) {
		super(mapper);
		this.transformer = transformer;
	}

	@Override
	public void write(Document doc, OutputStream stream, Metadata metadata) throws Exception {
		transformer.transform(new DOMSource(doc.getDocumentElement()), new StreamResult(stream));
	}
}
