
package imdb;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for filmmaatschappijenType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="filmmaatschappijenType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence maxOccurs="unbounded">
 *         &lt;element name="filmmaatschappij" type="{}filmmaatschappijType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "filmmaatschappijenType", propOrder = {
    "filmmaatschappij"
})
public class FilmmaatschappijenType {

    @XmlElement(required = true)
    protected List<FilmmaatschappijType> filmmaatschappij;

    /**
     * Gets the value of the filmmaatschappij property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the filmmaatschappij property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFilmmaatschappij().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FilmmaatschappijType }
     * 
     * 
     */
    public List<FilmmaatschappijType> getFilmmaatschappij() {
        if (filmmaatschappij == null) {
            filmmaatschappij = new ArrayList<FilmmaatschappijType>();
        }
        return this.filmmaatschappij;
    }

}
