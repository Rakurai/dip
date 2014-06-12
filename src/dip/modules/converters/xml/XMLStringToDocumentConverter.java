package dip.modules.converters;

import java.io.StringReader;
import java.util.concurrent.BlockingQueue;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

public class XMLStringToDocumentConverter extends AbstractConverter<String, Document> {
	private DocumentBuilder builder;

	public XMLStringToDocumentConverter(BlockingQueue<String> input, BlockingQueue<Document> output) throws Exception {
		super(input, output);

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		builder = factory.newDocumentBuilder();
	}

	@Override
	protected Document convert(String str) throws Exception {
        InputSource source = new InputSource(new StringReader(str));
        return builder.parse(source);
	}

}
