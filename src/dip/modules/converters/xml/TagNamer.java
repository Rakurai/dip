package dip.modules.converters.xml;
import java.util.HashMap;
import java.util.Map;


public class TagNamer {
	Map<String, String> index = new HashMap<String, String>();
	char[] currentTag = {'a', 'a', 'a'};

	private char increment(char c) {
		return (char)(((((int)c-(int)'a')+1)%26)+(int)'a');
	}

	private synchronized String nextTag() {
		String tag = String.copyValueOf(currentTag);
		
		currentTag[2] = increment(currentTag[2]);
		
		if (currentTag[2] == 'a') {
			currentTag[1] = increment(currentTag[1]);

			if (currentTag[1] == 'a')
				currentTag[0] = increment(currentTag[0]);
		}
		return tag;
	}
	
	public String getTag(String str) {
		String tag = index.get(str);
		
		if (tag == null) {
			tag = nextTag();
			index.put(str, tag);
		}

		return tag;
	}
}
