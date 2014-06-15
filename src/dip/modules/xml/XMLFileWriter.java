package dip.modules.xml;

import java.io.File;

import javax.xml.transform.Transformer;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

import dip.modules.AbstractWriter;
import dip.modules.IOMapper;

public class XMLFileWriter extends AbstractWriter<Document, File> {
	private Transformer transformer;

	public XMLFileWriter(Transformer transformer, IOMapper<File> mapper) throws Exception {
		super(mapper);
		this.transformer = transformer;
	}

	@Override
	public void write(Document doc, File file) throws Exception {
        transformer.transform(new DOMSource(doc.getDocumentElement()), new StreamResult(file));
	}

}
