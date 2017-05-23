package loaders;

import model.SettingListWrapper;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import utils.Util;

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
                    storage.getSetting().add(DOMSwitcher.parceNode(element.getAttribute("xsi:type"), element));
                }
            }
            return "Successfully loaded data from file:\n" + file.getAbsolutePath();
        } catch (Exception e) {
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
}
