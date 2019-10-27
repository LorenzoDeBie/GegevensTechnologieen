import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import straten.Stadsdelen;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            String filename = "res/stratenInGent.xml";
            SAXStratenDefaultHandler handler = new SAXStratenDefaultHandler();
            parser.parse(filename,handler);
            Stadsdelen result = handler.getStadsdelen();
            JAXBContext ctx = JAXBContext.newInstance(Stadsdelen.class);
            Marshaller m = ctx.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            m.marshal(result, System.out);
            m.marshal(result, new File("stratenPerStadsdeel.xml"));
        }
        catch (ParserConfigurationException | SAXException | IOException | JAXBException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
