package dip.modules.converters;

import java.io.StringWriter;
import java.util.concurrent.BlockingQueue;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

public class XMLDocumentToStringConverter extends AbstractConverter<Document, String> {

	protected Transformer transformer;

	public XMLDocumentToStringConverter(BlockingQueue<Document> input, BlockingQueue<String> output, Transformer transformer) throws Exception {
		super(input, output);
		this.transformer = transformer;
	}

	@Override
	protected String convert(Document doc) throws Exception {
		StringWriter writer = new StringWriter();
		transformer.transform(new DOMSource(doc), new StreamResult(writer));
		return writer.getBuffer().toString();
	}

}
