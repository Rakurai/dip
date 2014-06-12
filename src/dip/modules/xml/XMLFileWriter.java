package dip.modules.xml;

import java.util.concurrent.BlockingQueue;

import javax.xml.transform.Transformer;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

import dip.modules.file.FileFactory;
import dip.modules.AbstractWriter;

public class XMLFileWriter extends AbstractWriter<Document> {
	private Transformer transformer;
	private FileFactory factory;

	public XMLFileWriter(BlockingQueue<Document> q, Transformer transformer, FileFactory factory) throws Exception {
		super(q);
		this.transformer = transformer;
		this.factory = factory;
	}

	@Override
	protected void write(Document doc) throws Exception {
        transformer.transform(new DOMSource(doc.getDocumentElement()), new StreamResult(factory.getFile()));
	}

}
