package loaders;

import model.Setting;
import model.SettingListWrapper;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import utils.Util;

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
            System.out.println(e.toString());
            return "Could not load data from file:\n" + file.getPath();
        }
    }

    @Override
    public String load(SettingListWrapper storage) {
        File file = Util.getSettingFilePath();
        if (file != null) {
            return load(file, storage);
        }
        return "Cannot load latest file";
    }

    private class SAXHandler extends DefaultHandler {
        Setting setting;

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            if (qName.equalsIgnoreCase("settings")) {
                System.out.println("Start parsing");
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
                System.out.println("End parsing");
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
