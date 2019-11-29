
package imdb;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for filmsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="filmsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice maxOccurs="unbounded">
 *         &lt;element name="film" type="{}filmType"/>
 *         &lt;element name="animatiefilm" type="{}animatiefilmType"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "filmsType", propOrder = {
    "filmOrAnimatiefilm"
})
public class FilmsType {

    @XmlElements({
        @XmlElement(name = "film", type = FilmType.class),
        @XmlElement(name = "animatiefilm")
    })
    protected List<AnimatiefilmType> filmOrAnimatiefilm;

    /**
     * Gets the value of the filmOrAnimatiefilm property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the filmOrAnimatiefilm property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFilmOrAnimatiefilm().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FilmType }
     * {@link AnimatiefilmType }
     * 
     * 
     */
    public List<AnimatiefilmType> getFilmOrAnimatiefilm() {
        if (filmOrAnimatiefilm == null) {
            filmOrAnimatiefilm = new ArrayList<AnimatiefilmType>();
        }
        return this.filmOrAnimatiefilm;
    }

}
