package dip.modules.xml;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

import dip.core.Metadata;
import dip.modules.AbstractReader;
import dip.modules.IOMapper;

public class XMLFileReader extends AbstractReader<File, Document> {
	private DocumentBuilder builder;

	public XMLFileReader(IOMapper<File> mapper) throws Exception {
		super(mapper);
		this.builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
	}

	@Override
	public Document read(File file, Metadata metadata) throws Exception {
		return builder.parse(file);
	}

}
