package dip.modules.readers.xml;

import java.io.File;
import java.util.concurrent.BlockingQueue;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

import dip.modules.readers.AbstractReader;
import dip.modules.writers.file.FileFactory;

public class XMLFileReader extends AbstractReader<Document> {

	private FileFactory factory;
	private DocumentBuilder builder;

	public XMLFileReader(BlockingQueue<Document> q, FileFactory factory) throws Exception {
		super(q);
		this.factory = factory;
		this.builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
	}

	@Override
	protected Document read() throws Exception {
		File file = factory.getFile();
		if (file == null)
			return null;
		
		Document doc = builder.parse(file);

		return doc;
	}

}
