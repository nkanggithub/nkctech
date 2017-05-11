package com.nkang.kxmoment.util;

import java.io.InputStream;
import java.io.Writer;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;





import com.nkang.kxmoment.response.Article;
import com.nkang.kxmoment.response.NewsMessage;
import com.nkang.kxmoment.response.TextMessage;
import com.nkang.kxmoment.service.CoreService;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;

@SuppressWarnings("unchecked")
public class MessageUtil {
	private static Logger log = Logger.getLogger(MessageUtil.class);
	
	public static final String REQ_MESSAGE_TYPE_TEXT = "text";
	public static final String REQ_MESSAGE_TYPE_IMAGE = "image";
	public static final String REQ_MESSAGE_TYPE_LINK = "link";
	public static final String REQ_MESSAGE_TYPE_LOCATION = "location";
	public static final String REQ_MESSAGE_TYPE_VOICE = "voice";
	public static final String REQ_MESSAGE_TYPE_VIDEO = "video";
	
	public static final String REQ_MESSAGE_TYPE_EVENT = "event";
	public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";
	public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";
	public static final String EVENT_TYPE_CLICK = "CLICK";
	public static final String EVENT_TYPE_SCAN = "SCAN";
	public static final String EVENT_TYPE_LOCATION = "LOCATION";
	public static final String EVENT_TYPE_LOCATION_SELECT = "location_select";
	public static final String EVENT_TYPE_VIEW = "VIEW";
	public static final String EVENT_TYPE_SCAN_TEXT = "scancode_waitmsg";
	public static final String EVENT_TYPE_SCAN_URL = "scancode_push";

	public static final String RESP_MESSAGE_TYPE_TEXT = "text";
	public static final String RESP_MESSAGE_TYPE_MUSIC = "music";
	public static final String RESP_MESSAGE_TYPE_NEWS = "news";
	public static final String RESP_MESSAGE_TYPE_IMAGE = "image";
	public static final String RESP_MESSAGE_TYPE_VOICE = "voice";
	public static final String RESP_MESSAGE_TYPE_VIDEO = "video";
	public static final String RESP_MESSAGE_TYPE_CUSTOMER_SERVICE = "transfer_customer_service";

	public static Element parseXml(HttpServletRequest request)
			throws Exception {
		InputStream inputStream = request.getInputStream();
		SAXReader reader = new SAXReader();
		Document doc = reader.read(inputStream);
		Element root = doc.getRootElement();
		
		List<Element> list = root.elements();
/*		for (Element e : list) {
			log.info(e.getName() + "-:- " + e.getText());
		}*/
		
		return root;

	}

	private static XStream xstream = new XStream(new XppDriver() {
		public HierarchicalStreamWriter createWriter(Writer out) {
			return new PrettyPrintWriter(out) {
				boolean cdata = true;

				public void startNode(String name, Class clazz) {
					super.startNode(name, clazz);
				}

				protected void writeText(QuickWriter writer, String text) {
					if (cdata) {
						writer.write("<![CDATA[");
						writer.write(text);
						writer.write("]]>");
					} else {
						writer.write(text);
					}
				}
			};
		}
	});

	public static String textMessageToXml(TextMessage textMessage) {
		xstream.alias("xml", textMessage.getClass());
		return xstream.toXML(textMessage);
	}

	public static String newsMessageToXml(NewsMessage newsMessage) {  
	    xstream.alias("xml", newsMessage.getClass());  
	    xstream.alias("item", new Article().getClass());  
	    return xstream.toXML(newsMessage);  
	}  
}
