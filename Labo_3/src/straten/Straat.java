
package straten;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for straat complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="straat">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{}naam"/>
 *         &lt;element name="onpaar" type="{}nummers" minOccurs="0"/>
 *         &lt;element name="paar" type="{}nummers" minOccurs="0"/>
 *         &lt;element name="sector" type="{http://www.w3.org/2001/XMLSchema}token"/>
 *       &lt;/sequence>
 *       &lt;attribute name="code" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" />
 *       &lt;attribute name="postcode" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "straat", propOrder = {
    "naam",
    "onpaar",
    "paar",
    "sector"
})
public class Straat {

    @XmlElement(required = true)
    protected String naam;
    protected Nummers onpaar;
    protected Nummers paar;
    @XmlElement(required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String sector;
    @XmlAttribute(name = "code")
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger code;
    @XmlAttribute(name = "postcode")
    protected BigInteger postcode;

    /**
     * Gets the value of the naam property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNaam() {
        return naam;
    }

    /**
     * Sets the value of the naam property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNaam(String value) {
        this.naam = value;
    }

    /**
     * Gets the value of the onpaar property.
     * 
     * @return
     *     possible object is
     *     {@link Nummers }
     *     
     */
    public Nummers getOnpaar() {
        return onpaar;
    }

    /**
     * Sets the value of the onpaar property.
     * 
     * @param value
     *     allowed object is
     *     {@link Nummers }
     *     
     */
    public void setOnpaar(Nummers value) {
        this.onpaar = value;
    }

    /**
     * Gets the value of the paar property.
     * 
     * @return
     *     possible object is
     *     {@link Nummers }
     *     
     */
    public Nummers getPaar() {
        return paar;
    }

    /**
     * Sets the value of the paar property.
     * 
     * @param value
     *     allowed object is
     *     {@link Nummers }
     *     
     */
    public void setPaar(Nummers value) {
        this.paar = value;
    }

    /**
     * Gets the value of the sector property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSector() {
        return sector;
    }

    /**
     * Sets the value of the sector property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSector(String value) {
        this.sector = value;
    }

    /**
     * Gets the value of the code property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getCode() {
        return code;
    }

    /**
     * Sets the value of the code property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setCode(BigInteger value) {
        this.code = value;
    }

    /**
     * Gets the value of the postcode property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getPostcode() {
        return postcode;
    }

    /**
     * Sets the value of the postcode property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setPostcode(BigInteger value) {
        this.postcode = value;
    }

}
