
package imdb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for filmType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="filmType">
 *   &lt;complexContent>
 *     &lt;extension base="{}animatiefilmType">
 *       &lt;sequence>
 *         &lt;element name="filmlocatie" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "filmType", propOrder = {
    "filmlocatie"
})
public class FilmType
    extends AnimatiefilmType
{

    @XmlElement(required = true)
    protected String filmlocatie;

    /**
     * Gets the value of the filmlocatie property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFilmlocatie() {
        return filmlocatie;
    }

    /**
     * Sets the value of the filmlocatie property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFilmlocatie(String value) {
        this.filmlocatie = value;
    }

}
