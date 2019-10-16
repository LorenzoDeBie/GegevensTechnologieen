package dom;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ...
 */
public class DOMBoom {
    private Document document;
    private String waarde;

    public DOMBoom(String bestandsnaam) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            document = null;
            document = builder.parse(new File(bestandsnaam));
        }
        catch (SAXException | IOException | ParserConfigurationException ex) {
            Logger.getLogger(DOMBoom.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public String toString()
    {
        return writeNode(document.getFirstChild(), 0);
    }

    private String writeNode(Node node, int level) {
        if(node == null) return "";
        StringBuilder result = new StringBuilder();
        result.append("--".repeat(Math.max(0, level)));

        result.append(node.getNodeName());
        result.append(":*");
        String nodeValue = node.getNodeValue();
        if(nodeValue != null && !nodeValue.isEmpty()) {
            result.append(node.getNodeValue().trim());
        }
        result.append("*\n");
        NodeList children = node.getChildNodes();
        for(int i = 0; i < children.getLength(); i++) {
            result.append(writeNode(children.item(i), level + 1));
        }
        return result.toString();
    }


    public void schrijfNaarBestand(String bestandsnaam) throws IOException {
        System.out.println("\nHet resultaat is te vinden in het bestand "+bestandsnaam+"\n");
        FileWriter fileWriter = new FileWriter(bestandsnaam, false);  //true = append
        BufferedWriter buffer = new BufferedWriter(fileWriter);
        buffer.write(this.toString());
        buffer.close();
    }
    // to do
    
}
