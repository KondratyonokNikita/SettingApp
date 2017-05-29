package internal.loaders;

import internal.model.Setting;
import internal.model.SettingListWrapper;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

/**
 * Created by Samsung on 23.05.2017.
 */
public class DOMLoader implements Loader {
    @Override
    public String load(File file, SettingListWrapper storage) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("setting");
            storage.getSetting().clear();
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) nNode;
                    Setting setting = Switcher.getObject(element.getAttribute("xsi:type"));
                    setting.setNode(element);
                    storage.getSetting().add(setting);
                }
            }
            return "Successfully loaded data from file:\n" + file.getAbsolutePath();
        } catch (Exception e) {
            return "Could not load data from file:\n" + file.getPath();
        }
    }
}
