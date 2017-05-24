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

    default PlainSetting getPlain() {
        return null;
    }

    default void setNode(Element element) {
    }

    default void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
    }

    default void endElement(String uri, String localName, String qName) throws SAXException {
    }

    default void characters(char ch[], int start, int length) throws SAXException {
    }
}
