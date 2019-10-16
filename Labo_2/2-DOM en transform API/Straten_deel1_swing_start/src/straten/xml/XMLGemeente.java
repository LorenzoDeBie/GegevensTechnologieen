package straten.xml;

import java.io.File;
import java.io.IOException;
import java.util.*;
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
public class XMLGemeente implements Gemeente {

    // hulpmap om stadsdelen op naam te zoeken
    private final Map<String, Stadsdeel> mapStadsdelen;
    // hulpmap om wijken op nr te zoeken
    private final Map<Integer, Wijk> mapWijken;
    // hulpmap om straten op naam te zoeken
    private final Map<String, Straat> mapStraten;

    public XMLGemeente(String bestand) {
        mapStraten = new HashMap<>();
        mapWijken = new HashMap<>();
        mapStadsdelen = new HashMap<>();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(bestand));
            NodeList straatKnopen = document.getElementsByTagName("element");
            for (int i = 0; i < straatKnopen.getLength(); i++) {
                Node straatKnoop = straatKnopen.item(i);
                NodeList kinderen = straatKnoop.getChildNodes();

                String postcodeString = null;
                String straatNaam = null;
                String naamStadsdeel = null;
                String onpaarVan = "*";
                String wijkNaam = null;
                String wijkNr = null;
                String sectorNaam = null;
                String onpaarTot = "*";
                String paarVan = "*";
                String paarTot = "*";



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
                            paarTot = kind.getFirstChild().getNodeValue();
                            break;
                        case "paar_tot":
                            paarVan = kind.getFirstChild().getNodeValue();
                            break;
                    }
                }
                // VUL HIER AAN:
                // bewaar alle informatie die je in de kinderen vond;
                // bepaal daarvoor sector, wijk, straat, stadsdeel,... 
                // ...


                //create straat && put it in map
                Straat currentStraat = bepaalStraat(straatNaam, postcodeString);
                //create wijk && put it in map
                Wijk currentWijk = bepaalWijk(wijkNr, wijkNaam);
                //add straat to wijk
                currentWijk.getStraten().add(currentStraat);
                //create stadsdeel && put it in map
                Stadsdeel currentStadsdeel = bepaalStadsdeel(naamStadsdeel);
                //add wijk to stadsdeel
                currentStadsdeel.getWijken().add(currentWijk);
                //create sector && straatdelen
                Sector currentSector = new Sector();
                currentSector.setNaam(sectorNaam);
                StraatDeel paar = new StraatDeel();
                paar.setVan(paarVan); paar.setTot(paarTot);
                StraatDeel onpaar = new StraatDeel();
                onpaar.setVan(onpaarVan); onpaar.setTot(onpaarTot);
                //add straatdelen to sector && sector to straat
                currentSector.setPaar(paar);
                currentSector.setOnpaar(onpaar);
                currentStraat.getSectoren().add(currentSector);
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
            stadsdeel.setWijken(new TreeSet<>());
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
        Integer nr = Integer.valueOf(wijkNr);
        if (mapWijken.containsKey(nr)) {
            wijk = mapWijken.get(nr);
        } else {
            wijk = new Wijk();
            wijk.setNaam(naam);
            wijk.setId(nr);
            wijk.setStraten(new TreeSet<>());
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