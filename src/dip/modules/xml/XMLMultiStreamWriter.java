package dip.modules.xml;

import java.io.OutputStream;
import java.util.concurrent.BlockingQueue;

import javax.xml.transform.Transformer;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

import dip.modules.AbstractMultiWriter;
import dip.modules.IOMapper;

public class XMLMultiStreamWriter extends AbstractMultiWriter<Document, OutputStream> {

	private Transformer transformer;

	public XMLMultiStreamWriter(BlockingQueue<Document> q, Transformer transformer, IOMapper<OutputStream> mapper) {
		super(q, mapper);
		this.transformer = transformer;
	}

	@Override
	public void write(Document doc, OutputStream stream) throws Exception {
		transformer.transform(new DOMSource(doc.getDocumentElement()), new StreamResult(stream));
	}
}
