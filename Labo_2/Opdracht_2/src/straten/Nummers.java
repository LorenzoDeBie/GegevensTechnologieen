
package straten;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for nummers complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="nummers">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="van" type="{http://www.w3.org/2001/XMLSchema}token" />
 *       &lt;attribute name="tot" type="{http://www.w3.org/2001/XMLSchema}token" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "nummers")
public class Nummers {

    @XmlAttribute(name = "van")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String van;
    @XmlAttribute(name = "tot")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String tot;

    /**
     * Gets the value of the van property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVan() {
        return van;
    }

    /**
     * Sets the value of the van property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVan(String value) {
        this.van = value;
    }

    /**
     * Gets the value of the tot property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTot() {
        return tot;
    }

    /**
     * Sets the value of the tot property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTot(String value) {
        this.tot = value;
    }

}
