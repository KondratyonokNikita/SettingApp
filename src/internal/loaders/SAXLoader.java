package internal.loaders;

import internal.model.Setting;
import internal.model.SettingListWrapper;
import loaders.Switcher;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;

/**
 * Created by Samsung on 23.05.2017.
 */
public class SAXLoader implements Loader {
    private SettingListWrapper storage;

    @Override
    public String load(File file, SettingListWrapper storage) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            SAXHandler handler = new SAXHandler();
            storage.clear();
            this.storage = storage;
            saxParser.parse(file, handler);
            return "Successfully loaded data from file:\n" + file.getAbsolutePath();
        } catch (Exception e) {
            return "Could not load data from file:\n" + file.getPath();
        }
    }

    private class SAXHandler extends DefaultHandler {
        Setting setting;

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            if (qName.equalsIgnoreCase("settings")) {
            } else if (qName.equalsIgnoreCase("setting")) {
                setting = Switcher.getObject(attributes.getValue("xsi:type"));
                setting.startElement(uri, localName, qName, attributes);
            } else {
                setting.startElement(uri, localName, qName, attributes);
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            if (qName.equalsIgnoreCase("settings")) {
            } else if (qName.equalsIgnoreCase("setting")) {
                storage.getSetting().add(setting);
                setting = null;
            } else {
                setting.endElement(uri, localName, qName);
            }
        }

        @Override
        public void characters(char ch[], int start, int length) throws SAXException {
            if (setting != null) {
                setting.characters(ch, start, length);
            }
        }
    }
}
