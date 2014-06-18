package dip.modules.xml;

import javax.xml.transform.Transformer;

import org.openexi.sax.Transmogrifier;
import org.w3c.dom.Document;

import dip.modules.CompositeConverter;

public class XMLDocumentCompressor extends CompositeConverter<Document, byte[]> {
	public XMLDocumentCompressor(Transformer transformer, Transmogrifier transmogrifier) throws Exception {
		super();
		addConverter(new XMLDocumentToStringConverter(transformer));
		addConverter(new XMLStringCompressor(transmogrifier));
	}
}
