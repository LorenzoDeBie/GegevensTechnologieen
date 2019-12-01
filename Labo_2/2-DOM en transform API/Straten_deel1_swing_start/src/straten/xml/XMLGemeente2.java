package straten.xml;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import straten.bo.*;

/**
 *
 * @author tiwi
 */
public class XMLGemeente2 implements Gemeente {

    // hulpmap om stadsdelen op naam te zoeken
    private final Map<String, Stadsdeel> mapStadsdelen;
    // hulpmap om wijken op nr te zoeken
    private final Map<Integer, Wijk> mapWijken;
    // hulpmap om straten op naam te zoeken
    private final Map<String, Straat> mapStraten;

    private enum properties {
        POSTCODE("postcode"),
        STRAATCODE("straatcode"),
        STRAATNAAM("straatnaam"),
        ONPAAR_VAN("onpaar_van"),
        ONPAAR_TOT("onpaar_tot"),
        PAAR_VAN("paar_van"),
        PAAR_TOT("paar_tot"),
        SECTOR("sector"),
        STADSDEEL("stadsdeel"),
        WIJKNR("wijkNr"),
        WIJKNAAM("wijknaam"),
        NONE("");

        private String property;

        properties(String property) {
            this.property = property;
        }

        public String getProperty() {
            return property;
        }

        public static properties fromString(String value) {
            for(properties prop: properties.values()) {
                if(prop.property.equalsIgnoreCase(value))
                    return prop;
            }
            return NONE;
        }
    }

    public XMLGemeente2(String bestand) {
        mapStraten = new HashMap<>();
        mapWijken = new HashMap<>();
        mapStadsdelen = new HashMap<>();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(bestand));
            NodeList straatKnopen = document.getDocumentElement().getElementsByTagName("element");
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

                // VUL HIER AAN:
                // overloop alle kinderen, en vul - afhankelijk van hun nodename -
                // de informatie die hierboven staat aan.
                // ...
                // VUL HIER AAN:
                // bewaar alle informatie die je in de kinderen vond;
                // bepaal daarvoor sector, wijk, straat, stadsdeel,... 
                // ...
                for(int n = 0; n < kinderen.getLength(); n++) {
                    Node kind = kinderen.item(n);
                    properties currentType = properties.fromString(kind.getNodeName());
                    String value = kind.getFirstChild() != null ? kind.getFirstChild().getNodeValue() : "";
                    switch (currentType) {
                        case POSTCODE:
                            postcodeString = value;
                            break;
                        case STRAATCODE:
                            break;
                        case STRAATNAAM:
                            straatNaam = value;
                            break;
                        case ONPAAR_VAN:
                            onpaarVan = value;
                            break;
                        case ONPAAR_TOT:
                            onpaarTot = value;
                            break;
                        case PAAR_VAN:
                            paarVan = value;
                            break;
                        case PAAR_TOT:
                            paarTot = value;
                            break;
                        case SECTOR:
                            sectorNaam = value;
                            break;
                        case STADSDEEL:
                            naamStadsdeel = value;
                            break;
                        case WIJKNR:
                            wijkNr = value;
                            break;
                        case WIJKNAAM:
                            wijkNaam = value;
                            break;
                    }
                }

                //straatdelen
                StraatDeel paar = new StraatDeel();
                paar.setVan(paarVan); paar.setTot(paarTot);
                StraatDeel onpaar = new StraatDeel();
                onpaar.setVan(onpaarVan); onpaar.setTot(onpaarTot);
                //sector
                Sector sector = new Sector();
                sector.setPaar(paar); sector.setOnpaar(onpaar);
                sector.setNaam(sectorNaam);
                //straat
                Straat current = bepaalStraat(straatNaam, postcodeString);
                current.getSectoren().add(sector);
                //wijk
                Wijk wijk = bepaalWijk(wijkNr, wijkNaam);
                wijk.getStraten().add(current);
                //stadsdeel
                Stadsdeel stadsdeel = bepaalStadsdeel(naamStadsdeel);
                stadsdeel.getWijken().add(wijk);
            }
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(XMLGemeente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Geeft alle stadsdelen terug. De stadsdelen bevatten informatie over
     * wijken, straten, sectoren en straatdelen.
     *
     * Deze methode wordt gebruikt door de GUI.     
     *
     * @return een verzameling van stadsdelen
     */
    @Override
    public Set<Stadsdeel> getStadsdelen() {
        Set<Stadsdeel> stadsdelen = new HashSet<>();
        stadsdelen.addAll(mapStadsdelen.values());
        return stadsdelen;
    }

    /**
     * Zoekt een stadsdeel in de map of maakt een nieuw aan. Een hulpmethode die
     * in de hashmap mapStadsdelen een stadsdeel met de opgegeven naam zoekt,
     * als er geen stadsdeel gevonden wordt, dan wordt er een nieuw aangemaakt
     * en toegevoegd aan de hashmap.
     *
     * @param naamStadsdeel de naam van het te zoeken stadsdeel
     * @return het gevonden of nieuw aangemaakte stadsdeel
     */
    private Stadsdeel bepaalStadsdeel(String naamStadsdeel) {
        Stadsdeel stadsdeel;
        if (mapStadsdelen.containsKey(naamStadsdeel)) {
            stadsdeel = mapStadsdelen.get(naamStadsdeel);
        } else {
            stadsdeel = new Stadsdeel();
            stadsdeel.setNaam(naamStadsdeel);
            stadsdeel.setWijken(new HashSet<>());
            mapStadsdelen.put(naamStadsdeel, stadsdeel);
        }
        return stadsdeel;
    }

    /**
     * Zoekt een wijk in de map of maakt een nieuwe aan. Een hulpmethode die in
     * de hashmap mapWijken een wijk met het opgegeven nummer zoekt, als er geen
     * wijk gevonden wordt, dan wordt er een nieuwe aangemaakt en toegevoegd aan
     * de hashmap.
     *
     * @param wijkNr de nummer van de te zoeken wijk
     * @param naam de naam van de te zoeken wijk
     * @return de gevonden of nieuw aangemaakte wijk
     */
    private Wijk bepaalWijk(String wijkNr, String naam) {
        Wijk wijk;
        Integer nr = new Integer(wijkNr);
        if (mapWijken.containsKey(nr)) {
            wijk = mapWijken.get(nr);
        } else {
            wijk = new Wijk();
            wijk.setNaam(naam);
            wijk.setId(nr);
            wijk.setStraten(new HashSet<>());
            mapWijken.put(nr, wijk);
        }
        return wijk;
    }

    /**
     * Zoekt een straat in de map of maakt een nieuwe aan. Een hulpmethode die
     * in de hashmap mapStraten een straat met de opgegeven naam zoekt, als er
     * geen straat gevonden wordt, dan wordt er een nieuwe aangemaakt en
     * toegevoegd aan de hashmap.
     *
     * @param straatNaam de naam van de te zoeken straat
     * @param postcode de postcode van de deelgemeente waarin de straat ligt
     * @return de gevonden of nieuw aangemaakte straat
     */
    private Straat bepaalStraat(String straatNaam, String postcode) {
        Straat straat;
        if (mapStraten.containsKey(straatNaam)) {
            straat = mapStraten.get(straatNaam);
        } else {
            straat = new Straat();
            straat.setNaam(straatNaam);
            straat.setPostcode(Integer.parseInt(postcode));
            straat.setSectoren(new HashSet<>());
            mapStraten.put(straatNaam, straat);
        }
        return straat;
    }
    
    /**
     * Maakt een nieuw straatdeel aan. Een hulpmethode die een nieuw straatdeel
     * aan.
     *
     * @param van het nummer of de nummercode waarmee het straatdeel begint
     * @param tot het nummer of de nummercode waarmee het straatdeel eindigt
     * @return het aangemaakte straatdeel
     */
    private StraatDeel maakStraatDeel(String van, String tot) {
        StraatDeel straatdeel = new StraatDeel();
        straatdeel.setVan(van);
        straatdeel.setTot(tot);
        return straatdeel;
    }

}
