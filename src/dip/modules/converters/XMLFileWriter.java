package dip.modules.converters;

import java.util.concurrent.BlockingQueue;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import dip.modules.writers.AbstractWriter;
import dip.modules.writers.file.FileNamer;

public class XMLFileWriter extends AbstractWriter<Document> {
	private Transformer transformer;
	private FileNamer namer;

	public XMLFileWriter(BlockingQueue<Document> q, FileNamer namer) throws Exception {
		super(q);
		this.namer = namer;

		transformer = TransformerFactory.newInstance().newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
		transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
	}

	@Override
	protected void write(Document doc) throws Exception {
        transformer.transform(new DOMSource(doc.getDocumentElement()), new StreamResult(namer.getName()));
	}

}
