
package imdb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for persoonType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="persoonType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="naam" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="geboortedatum">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;pattern value="\d\d\/\d\d\/\d\d\d\d"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="geboorteplaats" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="geslacht">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;pattern value="[MV]"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="foto">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;pattern value=".+\.jpg"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "persoonType", propOrder = {
    "naam",
    "geboortedatum",
    "geboorteplaats",
    "geslacht",
    "foto"
})
public class PersoonType {

    @XmlElement(required = true)
    protected String naam;
    @XmlElement(required = true)
    protected String geboortedatum;
    @XmlElement(required = true)
    protected String geboorteplaats;
    @XmlElement(required = true)
    protected String geslacht;
    @XmlElement(required = true)
    protected String foto;
    @XmlAttribute(name = "id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;

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
     * Gets the value of the geboortedatum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGeboortedatum() {
        return geboortedatum;
    }

    /**
     * Sets the value of the geboortedatum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGeboortedatum(String value) {
        this.geboortedatum = value;
    }

    /**
     * Gets the value of the geboorteplaats property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGeboorteplaats() {
        return geboorteplaats;
    }

    /**
     * Sets the value of the geboorteplaats property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGeboorteplaats(String value) {
        this.geboorteplaats = value;
    }

    /**
     * Gets the value of the geslacht property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGeslacht() {
        return geslacht;
    }

    /**
     * Sets the value of the geslacht property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGeslacht(String value) {
        this.geslacht = value;
    }

    /**
     * Gets the value of the foto property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFoto() {
        return foto;
    }

    /**
     * Sets the value of the foto property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFoto(String value) {
        this.foto = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

}
