package dip.modules.xml;

import java.util.concurrent.BlockingQueue;

import javax.xml.transform.Transformer;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

import dip.modules.stream.AbstractStreamWriter;
import dip.modules.stream.OutputStreamFactory;

public class XMLStreamWriter extends AbstractStreamWriter<Document> {

	private Transformer transformer;

	public XMLStreamWriter(BlockingQueue<Document> q, Transformer transformer, OutputStreamFactory factory) {
		super(q, factory);
		this.transformer = transformer;
	}

	@Override
	public void write(Document doc) throws Exception {
		transformer.transform(new DOMSource(doc.getDocumentElement()), new StreamResult(factory.getStream()));
	}
}
