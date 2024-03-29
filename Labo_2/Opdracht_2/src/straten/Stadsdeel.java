
package straten;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{}naam"/>
 *         &lt;element name="wijk" type="{}wijk" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "naam",
    "wijk"
})
@XmlRootElement(name = "stadsdeel")
public class Stadsdeel {

    @XmlElement(required = true)
    protected String naam;
    @XmlElement(required = true)
    protected List<Wijk> wijk;

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
     * Gets the value of the wijk property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the wijk property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWijk().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Wijk }
     * 
     * 
     */
    public List<Wijk> getWijk() {
        if (wijk == null) {
            wijk = new ArrayList<Wijk>();
        }
        return this.wijk;
    }

}
