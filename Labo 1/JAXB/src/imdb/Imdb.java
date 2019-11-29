
package imdb;

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
 *         &lt;element name="films" type="{}filmsType"/>
 *         &lt;element name="personen" type="{}personenType"/>
 *         &lt;element name="filmmaatschappijen" type="{}filmmaatschappijenType"/>
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
    "films",
    "personen",
    "filmmaatschappijen"
})
@XmlRootElement(name = "imdb")
public class Imdb {

    @XmlElement(required = true)
    protected FilmsType films;
    @XmlElement(required = true)
    protected PersonenType personen;
    @XmlElement(required = true)
    protected FilmmaatschappijenType filmmaatschappijen;

    /**
     * Gets the value of the films property.
     * 
     * @return
     *     possible object is
     *     {@link FilmsType }
     *     
     */
    public FilmsType getFilms() {
        return films;
    }

    /**
     * Sets the value of the films property.
     * 
     * @param value
     *     allowed object is
     *     {@link FilmsType }
     *     
     */
    public void setFilms(FilmsType value) {
        this.films = value;
    }

    /**
     * Gets the value of the personen property.
     * 
     * @return
     *     possible object is
     *     {@link PersonenType }
     *     
     */
    public PersonenType getPersonen() {
        return personen;
    }

    /**
     * Sets the value of the personen property.
     * 
     * @param value
     *     allowed object is
     *     {@link PersonenType }
     *     
     */
    public void setPersonen(PersonenType value) {
        this.personen = value;
    }

    /**
     * Gets the value of the filmmaatschappijen property.
     * 
     * @return
     *     possible object is
     *     {@link FilmmaatschappijenType }
     *     
     */
    public FilmmaatschappijenType getFilmmaatschappijen() {
        return filmmaatschappijen;
    }

    /**
     * Sets the value of the filmmaatschappijen property.
     * 
     * @param value
     *     allowed object is
     *     {@link FilmmaatschappijenType }
     *     
     */
    public void setFilmmaatschappijen(FilmmaatschappijenType value) {
        this.filmmaatschappijen = value;
    }

}
