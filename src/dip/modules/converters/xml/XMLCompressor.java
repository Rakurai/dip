package dip.modules.converters.xml;
import java.util.concurrent.BlockingQueue;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import dip.modules.converters.AbstractConverter;


public class XMLCompressor extends AbstractConverter<Document, Document> {

	private TagNamer namer;

	public XMLCompressor(BlockingQueue<Document> input, BlockingQueue<Document> output, TagNamer namer) {
		super(input, output);
		this.namer = namer;
		// TODO Auto-generated constructor stub
	}

	private void renameNode(Node node, Document doc) {
		doc.renameNode(node, null, namer.getTag(node.getNodeName()));
		
		// attributes
		NamedNodeMap attrList = node.getAttributes();
		for (int i = 0; i < attrList.getLength(); i++) {
			Node attr = attrList.item(i);
			doc.renameNode(attr, null, namer.getTag(attr.getNodeName()));
		}

		// recurse
	    NodeList nodeList = node.getChildNodes();
	    for (int i = 0; i < nodeList.getLength(); i++) {
	    	Node currentNode = nodeList.item(i);
	        if (currentNode.getNodeType() == Node.ELEMENT_NODE) {
	            //calls this method for all the children which is Element
	            renameNode(currentNode, doc);
	        }
	    }
		
	}
	
	@Override
	public Document convert(Document doc) throws Exception {
		renameNode(doc.getDocumentElement(), doc);

		return doc;
	}

}
