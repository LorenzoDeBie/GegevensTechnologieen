import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import straten.ObjectFactory;
import straten.Stadsdeel;
import straten.Straat;
import straten.Wijk;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class SAXStratenDefaultHandler extends DefaultHandler {
    private Map<String, Stadsdeel> mapStadsdelen = new HashMap<>();
    private Map<Integer, Wijk> mapWijken = new HashMap<>();
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
        element;
    }

    private String postcodeString;
    private String straatcode;
    private String straatnaam;
    String onpaar_van;
    String onpaar_tot;
    String paar_van;
    String paar_tot;
    String sector;
    String stadsdeel;
    String wijkNr;
    String wijkNaam;

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
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        if(qName.equals(Types.element.name())) {
            currentStraat = bepaalStraat(straatnaam);
            currentStraat.setCode(BigInteger.valueOf(Integer.parseInt(wijkNr)));

        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        switch (currentElementType) {
            case postcode:
                postcodeString = new String(ch, start, length);
                break;
            case straatcode:
                straatcode = new String(ch, start, length);
                break;
            case straatnaam:
                straatnaam = new String(ch, start, length);
                break;
            case onpaar_van:
                onpaar_van = new String(ch, start, length);
                break;
            case onpaar_tot:
                onpaar_tot = new String(ch, start, length);
                break;
            case paar_van:
                paar_van = new String(ch, start, length);
                break;
            case paar_tot:
                paar_tot = new String(ch, start, length);
                break;
            case sector:
                sector = new String(ch, start, length);
                break;
            case stadsdeel:
                stadsdeel = new String(ch, start, length);
                break;
            case wijkNr:
                wijkNr = new String(ch, start, length);
                break;
            case wijknaam:
                wijkNaam = new String(ch, start, length);
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

    private Wijk bepaalWijk(int wijkNr) {
        Wijk wijk;
        if(mapWijken.containsKey(wijkNr)) {
            wijk = mapWijken.get(wijkNr);
        }
        else {
            wijk = factory.createWijk();
            wijk.setNr(BigInteger.valueOf(wijkNr));
            mapWijken.put(wijkNr, wijk);
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

}
