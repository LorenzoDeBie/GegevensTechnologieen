import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import straten.*;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class SAXStratenDefaultHandler extends DefaultHandler {
    private Map<String, Stadsdeel> mapStadsdelen = new HashMap<>();
    private Map<String, Wijk> mapWijken = new HashMap<>();
    private Map<String, Straat> mapStraten = new HashMap<>();

    //factory to make all the objects
    private ObjectFactory factory = new ObjectFactory();
    //current street we are parsing
    private Straat currentStraat;

    enum Types {
        postcode,
        straatcode,
        straatnaam,
        onpaar_van,
        onpaar_tot,
        paar_van,
        paar_tot,
        sector,
        stadsdeel,
        wijkNr,
        wijknaam,
        element,
        root;
    }

    private String postcodeString;
    private String straatcode;
    private String straatnaam;
    private String onpaar_van;
    private String onpaar_tot;
    private String paar_van;
    private String paar_tot;
    private String sector;
    private String stadsdeel;
    private String wijkNr;
    private String wijkNaam;

    private Types currentElementType;

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        currentElementType = Types.valueOf(qName);
        if(currentElementType == Types.element){
            //reset all variables if we have a new street in old
            postcodeString = "";
            straatcode = "";
            straatnaam = "";
            onpaar_van = onpaar_tot = paar_van = paar_tot = sector = stadsdeel = wijkNr = wijkNaam = "";
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        if(qName.equals(Types.element.name())) {
            //we now have all the information about this street
            currentStraat = bepaalStraat(straatnaam);
            //wijknr is not required
            if(!straatcode.isEmpty())
                currentStraat.setCode(BigInteger.valueOf(Integer.parseInt(straatcode)));

            //nummers
            if(!onpaar_van.isEmpty() && !onpaar_tot.isEmpty()) {
                Nummers onpaar = factory.createNummers();
                onpaar.setVan(onpaar_van);
                onpaar.setTot(onpaar_tot);
                currentStraat.setOnpaar(onpaar);
            }
            if(!paar_van.isEmpty() && ! paar_tot.isEmpty()) {
                Nummers paar = factory.createNummers();
                paar.setVan(paar_van);
                paar.setTot(paar_tot);
                currentStraat.setPaar(paar);
            }

            currentStraat.setSector(sector);
            //postcody is not required
            if(!postcodeString.isEmpty())
                currentStraat.setPostcode(BigInteger.valueOf(Integer.parseInt(postcodeString)));

            //wijk
            Wijk currentWijk = bepaalWijk(wijkNaam);
            if(!wijkNr.isEmpty())
                currentWijk.setNr(BigInteger.valueOf(Integer.parseInt(wijkNr)));
            //current street is original because file is ordened by streets
            currentWijk.getStraat().add(currentStraat);

            //stadsdeel
            Stadsdeel currentStadsdeel = bepaalStadsdeel(stadsdeel);
            //only add wijk to stadsdeel if it isn't in there already
            if(!currentStadsdeel.getWijk().contains(currentWijk)) {
                currentStadsdeel.getWijk().add(currentWijk);
            }

        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        String var = new String(ch, start, length).trim();
        if(var.isEmpty()) return;
        switch (currentElementType) {
            case postcode:
                postcodeString = var;
                break;
            case straatcode:
                straatcode = var;
                break;
            case straatnaam:
                straatnaam = var;
                break;
            case onpaar_van:
                onpaar_van = var;
                break;
            case onpaar_tot:
                onpaar_tot = var;
                break;
            case paar_van:
                paar_van = var;
                break;
            case paar_tot:
                paar_tot = var;
                break;
            case sector:
                sector = var;
                break;
            case stadsdeel:
                stadsdeel = var;
                break;
            case wijkNr:
                wijkNr = var;
                break;
            case wijknaam:
                wijkNaam = var;
                break;
        }
    }

    private Straat bepaalStraat(String straatNaam) {
        if(mapStraten.containsKey(straatNaam)) {
           currentStraat = mapStraten.get(straatNaam);
        }
        else {
            currentStraat = factory.createStraat();
            currentStraat.setNaam(straatNaam);
            mapStraten.put(straatNaam, currentStraat);
        }
        return currentStraat;
    }

    private Wijk bepaalWijk(String wijkNaam) {
        Wijk wijk;
        if(mapWijken.containsKey(wijkNaam)) {
            wijk = mapWijken.get(wijkNaam);
        }
        else {
            wijk = factory.createWijk();
            wijk.setNaam(wijkNaam);
            mapWijken.put(wijkNaam, wijk);
        }
        return wijk;
    }

    private Stadsdeel bepaalStadsdeel(String stadsdeelNaam) {
        Stadsdeel stadsdeel;
        if(mapStadsdelen.containsKey(stadsdeelNaam)) {
            stadsdeel = mapStadsdelen.get(stadsdeelNaam);
        }
        else {
            stadsdeel = factory.createStadsdeel();
            stadsdeel.setNaam(stadsdeelNaam);
            mapStadsdelen.put(stadsdeelNaam, stadsdeel);
        }
        return stadsdeel;
    }

    public Stadsdelen getStadsdelen() {
        Stadsdelen stadsdelen = factory.createStadsdelen();
        stadsdelen.getStadsdeel().addAll(mapStadsdelen.values());
        return stadsdelen;
    }

}
