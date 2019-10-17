import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import straten.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Converter {

    public static void main(String[] args) {
        // Hulpmap om gemeentes op postcode op te zoeken
        final Map<BigInteger, Postcode> mapPostcodes = new HashMap<>();
        // hulpmap om stadsdelen op naam te zoeken
        final Map<String, Stadsdeel> mapStadsdelen = new HashMap<>();
        // hulpmap om wijken op nr te zoeken
        final Map<BigInteger, Wijk> mapWijken = new HashMap<>();
        // hulpmap om straten op naam te zoeken
        final Map<String, Straat> mapStraten = new HashMap<>();

        //get filename to read
        String bestandsnaam = "Labo_2/Opdracht_2/stratenInGent.xml";

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = null;
            document = builder.parse(new File(bestandsnaam));

            Postcodes root = new Postcodes();

            NodeList straatKnopen = document.getElementsByTagName("element");
            for (int i = 0; i < straatKnopen.getLength(); i++) {
                Node straatKnoop = straatKnopen.item(i);
                NodeList kinderen = straatKnoop.getChildNodes();

                String postcodeString = null;
                String straatNaam = null;
                String naamStadsdeel = null;
                String onpaarVan = null;
                String wijkNaam = null;
                String wijkNr = null;
                String sectorNaam = null;
                String onpaarTot = null;
                String paarVan = null;
                String paarTot = null;
                String straatCode = null;



                // VUL HIER AAN:
                // overloop alle kinderen, en vul - afhankelijk van hun nodename -
                // de informatie die hierboven staat aan.
                // ...
                for(int n = 0; n < kinderen.getLength(); n++) {
                    Node kind = kinderen.item(n);
                    if(!kind.hasChildNodes()) continue;
                    switch (kind.getNodeName()) {
                        case "postcode":
                            postcodeString = kind.getFirstChild().getNodeValue();
                            break;
                        case "straatnaam":
                            straatNaam = kind.getFirstChild().getNodeValue();
                            break;
                        case "stadsdeel":
                            naamStadsdeel = kind.getFirstChild().getNodeValue();
                            break;
                        case "onpaar_van":
                            onpaarVan = kind.getFirstChild().getNodeValue();
                            break;
                        case "wijknaam":
                            wijkNaam = kind.getFirstChild().getNodeValue();
                            break;
                        case "wijkNr":
                            wijkNr = kind.getFirstChild().getNodeValue();
                            break;
                        case "sector":
                            sectorNaam = kind.getFirstChild().getNodeValue();
                            break;
                        case "onpaar_tot":
                            onpaarTot = kind.getFirstChild().getNodeValue();
                            break;
                        case "paar_van":
                            paarVan = kind.getFirstChild().getNodeValue();
                            break;
                        case "paar_tot":
                            paarTot = kind.getFirstChild().getNodeValue();
                            break;
                        case "straatcode":
                            straatCode = kind.getFirstChild().getNodeValue();
                            break;
                    }
                }

                ObjectFactory objectFactory = new ObjectFactory();

                //bepaal postcode
                Postcode postcode;
                if(mapPostcodes.containsKey(new BigInteger(postcodeString))) {
                    postcode = mapPostcodes.get(new BigInteger(postcodeString));
                }
                else {
                    postcode = new Postcode();
                    postcode.setCode(new BigInteger(postcodeString));
                    mapPostcodes.put(postcode.getCode(), postcode);
                }

                //stadsdeel
                Stadsdeel stadsdeel;
                if(mapStadsdelen.containsKey(naamStadsdeel)) {
                    stadsdeel = mapStadsdelen.get(naamStadsdeel);
                }
                else {
                    stadsdeel = new Stadsdeel();
                    stadsdeel.setNaam(naamStadsdeel);
                    mapStadsdelen.put(naamStadsdeel, stadsdeel);
                }

                //wijken
                Wijk wijk;
                if(mapWijken.containsKey(new BigInteger(wijkNr))) {
                    wijk = mapWijken.get(new BigInteger(wijkNr));
                }
                else {
                    wijk = new Wijk();
                    wijk.setNaam(wijkNaam);
                    wijk.setNr(new BigInteger(wijkNr));
                    mapWijken.put(new BigInteger(wijkNr), wijk);
                }

                //straat
                Straat straat;
                if(mapStraten.containsKey(straatNaam)) {
                    straat = mapStraten.get(straatNaam);
                }
                else {
                    straat = new Straat();
                    straat.setNaam(straatNaam);
                    straat.setSector(sectorNaam);
                    if(straatCode != null) {
                        straat.setCode(new BigInteger(straatCode));
                    }
                    if(onpaarVan != null) {
                        Nummers onpaar = new Nummers();
                        onpaar.setVan(onpaarVan);
                        onpaar.setTot(onpaarTot);
                        straat.setOnpaar(onpaar);
                    }
                    if(paarVan != null) {
                        Nummers paar = new Nummers();
                        paar.setVan(paarVan);
                        paar.setTot(paarTot);
                        straat.setPaar(paar);
                    }
                    mapStraten.put(straatNaam, straat);
                }

                //add to appropriate object if needed
                if(!root.getPostcode().contains(postcode)) {
                    root.getPostcode().add(postcode);
                }
                if(!postcode.getStadsdeel().contains(stadsdeel)) {
                    postcode.getStadsdeel().add(stadsdeel);
                }
                if(!stadsdeel.getWijk().contains(wijk)) {
                    stadsdeel.getWijk().add(wijk);
                }
                if(!wijk.getStraat().contains(straat)) {
                    wijk.getStraat().add(straat);
                }
            }

            JAXBContext jctx = JAXBContext.newInstance("straten");
            Marshaller m = jctx.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            m.marshal(root, System.out);
            m.marshal(root, new File("Labo_2/Opdracht_2/stratenPerPostcode.xml"));
        }
        catch (SAXException | IOException | ParserConfigurationException | JAXBException ex) {
            Logger.getLogger(Converter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
