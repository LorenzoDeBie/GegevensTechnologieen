
package straten;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the straten package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Naam_QNAME = new QName("", "naam");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: straten
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Stadsdelen }
     * 
     */
    public Stadsdelen createStadsdelen() {
        return new Stadsdelen();
    }

    /**
     * Create an instance of {@link Stadsdeel }
     * 
     */
    public Stadsdeel createStadsdeel() {
        return new Stadsdeel();
    }

    /**
     * Create an instance of {@link Wijk }
     * 
     */
    public Wijk createWijk() {
        return new Wijk();
    }

    /**
     * Create an instance of {@link Straat }
     * 
     */
    public Straat createStraat() {
        return new Straat();
    }

    /**
     * Create an instance of {@link Nummers }
     * 
     */
    public Nummers createNummers() {
        return new Nummers();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "naam")
    public JAXBElement<String> createNaam(String value) {
        return new JAXBElement<String>(_Naam_QNAME, String.class, null, value);
    }

}
