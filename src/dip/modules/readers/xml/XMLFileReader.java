package dip.modules.readers.xml;

import java.io.File;
import java.util.concurrent.BlockingQueue;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

import dip.modules.readers.AbstractReader;
import dip.modules.writers.file.FileNamer;

public class XMLFileReader extends AbstractReader<Document> {

	private FileNamer namer;
	private DocumentBuilder builder;

	public XMLFileReader(BlockingQueue<Document> q, FileNamer namer) throws Exception {
		super(q);
		this.namer = namer;
		this.builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
	}

	@Override
	protected Document read() throws Exception {
		String filename = namer.getName();
		if (filename == null)
			return null;
		
		Document doc = builder.parse(new File(filename));

		return doc;
	}

}
