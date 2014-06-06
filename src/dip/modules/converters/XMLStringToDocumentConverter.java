package dip.modules.converters;

import java.io.StringReader;
import java.util.concurrent.BlockingQueue;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

public class XMLStringToDocumentConverter extends AbstractConverter<String, Document> {
	private Transformer transformer;
	private DocumentBuilder builder;

	protected XMLStringToDocumentConverter(BlockingQueue<String> input, BlockingQueue<Document> output) throws Exception {
		super(input, output);

		transformer = TransformerFactory.newInstance().newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
		transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		builder = factory.newDocumentBuilder();
	}

	@Override
	protected Document convert(String str) throws Exception {
        InputSource source = new InputSource(new StringReader(str));
        return builder.parse(source);
	}

}
