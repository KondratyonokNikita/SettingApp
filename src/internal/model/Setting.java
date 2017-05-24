package internal.model;

import javafx.beans.property.StringProperty;
import org.w3c.dom.Element;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import java.io.Serializable;
import java.net.URL;

/**
 * Created by Samsung on 22.05.2017.
 */
public interface Setting extends Serializable {
    StringProperty nameProperty();
    StringProperty infoProperty();

    URL getView();
    PlainSetting getPlain();
    void setNode(Element element);
    void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException;
    void endElement(String uri, String localName, String qName) throws SAXException;
    void characters(char ch[], int start, int length) throws SAXException;
}
