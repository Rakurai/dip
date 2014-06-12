package dip.modules.xml;

import java.io.File;
import java.util.concurrent.BlockingQueue;

import javax.xml.transform.Transformer;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

import dip.modules.AbstractMultiWriter;
import dip.modules.IOMapper;

public class XMLFileWriter extends AbstractMultiWriter<Document, File> {
	private Transformer transformer;

	public XMLFileWriter(BlockingQueue<Document> queue, Transformer transformer, IOMapper<File> mapper) throws Exception {
		super(queue, mapper);
		this.transformer = transformer;
	}

	@Override
	protected void write(Document doc, File file) throws Exception {
        transformer.transform(new DOMSource(doc.getDocumentElement()), new StreamResult(file));
	}

}
