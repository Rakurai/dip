package dip.modules.xml;

import java.io.File;
import java.util.concurrent.BlockingQueue;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

import dip.modules.AbstractMultiReader;
import dip.modules.IOMapper;

public class XMLFileReader extends AbstractMultiReader<File, Document> {
	private DocumentBuilder builder;

	public XMLFileReader(BlockingQueue<Document> queue, IOMapper<File> mapper) throws Exception {
		super(queue, mapper);
		this.builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
	}

	@Override
	protected Document read(File file) throws Exception {
		return builder.parse(file);
	}

}
