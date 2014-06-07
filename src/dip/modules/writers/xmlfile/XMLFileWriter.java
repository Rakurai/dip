package dip.modules.writers.xmlfile;

import java.util.concurrent.BlockingQueue;

import javax.xml.transform.Transformer;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

import dip.modules.writers.AbstractWriter;
import dip.modules.writers.file.FileNamer;

public class XMLFileWriter extends AbstractWriter<Document> {
	private Transformer transformer;
	private FileNamer namer;

	public XMLFileWriter(BlockingQueue<Document> q, Transformer transformer, FileNamer namer) throws Exception {
		super(q);
		this.transformer = transformer;
		this.namer = namer;
	}

	@Override
	protected void write(Document doc) throws Exception {
        transformer.transform(new DOMSource(doc.getDocumentElement()), new StreamResult(namer.getName()));
	}

}
