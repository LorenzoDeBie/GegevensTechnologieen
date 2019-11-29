
package imdb;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for animatiefilmType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="animatiefilmType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="titel" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cast">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="personage" maxOccurs="unbounded">
 *                     &lt;complexType>
 *                       &lt;simpleContent>
 *                         &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                           &lt;attribute name="acteur" type="{http://www.w3.org/2001/XMLSchema}IDREF" />
 *                         &lt;/extension>
 *                       &lt;/simpleContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="regisseur" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;attribute name="naam" type="{http://www.w3.org/2001/XMLSchema}IDREF" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="speelduur" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/>
 *         &lt;element name="maatschappijen">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence maxOccurs="unbounded">
 *                   &lt;element name="maatschappij">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;attribute name="naam" type="{http://www.w3.org/2001/XMLSchema}IDREF" />
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *       &lt;attribute name="beoordeling">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;pattern value="(KT)|(KNT)"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "animatiefilmType", propOrder = {
    "titel",
    "cast",
    "regisseur",
    "speelduur",
    "maatschappijen"
})
@XmlSeeAlso({
    FilmType.class
})
public class AnimatiefilmType {

    @XmlElement(required = true)
    protected String titel;
    @XmlElement(required = true)
    protected AnimatiefilmType.Cast cast;
    @XmlElement(required = true)
    protected List<AnimatiefilmType.Regisseur> regisseur;
    @XmlElement(required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger speelduur;
    @XmlElement(required = true)
    protected AnimatiefilmType.Maatschappijen maatschappijen;
    @XmlAttribute(name = "id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;
    @XmlAttribute(name = "beoordeling")
    protected String beoordeling;

    /**
     * Gets the value of the titel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitel() {
        return titel;
    }

    /**
     * Sets the value of the titel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitel(String value) {
        this.titel = value;
    }

    /**
     * Gets the value of the cast property.
     * 
     * @return
     *     possible object is
     *     {@link AnimatiefilmType.Cast }
     *     
     */
    public AnimatiefilmType.Cast getCast() {
        return cast;
    }

    /**
     * Sets the value of the cast property.
     * 
     * @param value
     *     allowed object is
     *     {@link AnimatiefilmType.Cast }
     *     
     */
    public void setCast(AnimatiefilmType.Cast value) {
        this.cast = value;
    }

    /**
     * Gets the value of the regisseur property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the regisseur property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRegisseur().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AnimatiefilmType.Regisseur }
     * 
     * 
     */
    public List<AnimatiefilmType.Regisseur> getRegisseur() {
        if (regisseur == null) {
            regisseur = new ArrayList<AnimatiefilmType.Regisseur>();
        }
        return this.regisseur;
    }

    /**
     * Gets the value of the speelduur property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getSpeelduur() {
        return speelduur;
    }

    /**
     * Sets the value of the speelduur property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setSpeelduur(BigInteger value) {
        this.speelduur = value;
    }

    /**
     * Gets the value of the maatschappijen property.
     * 
     * @return
     *     possible object is
     *     {@link AnimatiefilmType.Maatschappijen }
     *     
     */
    public AnimatiefilmType.Maatschappijen getMaatschappijen() {
        return maatschappijen;
    }

    /**
     * Sets the value of the maatschappijen property.
     * 
     * @param value
     *     allowed object is
     *     {@link AnimatiefilmType.Maatschappijen }
     *     
     */
    public void setMaatschappijen(AnimatiefilmType.Maatschappijen value) {
        this.maatschappijen = value;
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

    /**
     * Gets the value of the beoordeling property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBeoordeling() {
        return beoordeling;
    }

    /**
     * Sets the value of the beoordeling property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBeoordeling(String value) {
        this.beoordeling = value;
    }


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
     *         &lt;element name="personage" maxOccurs="unbounded">
     *           &lt;complexType>
     *             &lt;simpleContent>
     *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
     *                 &lt;attribute name="acteur" type="{http://www.w3.org/2001/XMLSchema}IDREF" />
     *               &lt;/extension>
     *             &lt;/simpleContent>
     *           &lt;/complexType>
     *         &lt;/element>
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
        "personage"
    })
    public static class Cast {

        @XmlElement(required = true)
        protected List<AnimatiefilmType.Cast.Personage> personage;

        /**
         * Gets the value of the personage property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the personage property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getPersonage().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link AnimatiefilmType.Cast.Personage }
         * 
         * 
         */
        public List<AnimatiefilmType.Cast.Personage> getPersonage() {
            if (personage == null) {
                personage = new ArrayList<AnimatiefilmType.Cast.Personage>();
            }
            return this.personage;
        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;simpleContent>
         *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
         *       &lt;attribute name="acteur" type="{http://www.w3.org/2001/XMLSchema}IDREF" />
         *     &lt;/extension>
         *   &lt;/simpleContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "value"
        })
        public static class Personage {

            @XmlValue
            protected String value;
            @XmlAttribute(name = "acteur")
            @XmlIDREF
            @XmlSchemaType(name = "IDREF")
            protected Object acteur;

            /**
             * Gets the value of the value property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getValue() {
                return value;
            }

            /**
             * Sets the value of the value property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setValue(String value) {
                this.value = value;
            }

            /**
             * Gets the value of the acteur property.
             * 
             * @return
             *     possible object is
             *     {@link Object }
             *     
             */
            public Object getActeur() {
                return acteur;
            }

            /**
             * Sets the value of the acteur property.
             * 
             * @param value
             *     allowed object is
             *     {@link Object }
             *     
             */
            public void setActeur(Object value) {
                this.acteur = value;
            }

        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence maxOccurs="unbounded">
     *         &lt;element name="maatschappij">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;attribute name="naam" type="{http://www.w3.org/2001/XMLSchema}IDREF" />
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
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
        "maatschappij"
    })
    public static class Maatschappijen {

        @XmlElement(required = true)
        protected List<AnimatiefilmType.Maatschappijen.Maatschappij> maatschappij;

        /**
         * Gets the value of the maatschappij property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the maatschappij property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getMaatschappij().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link AnimatiefilmType.Maatschappijen.Maatschappij }
         * 
         * 
         */
        public List<AnimatiefilmType.Maatschappijen.Maatschappij> getMaatschappij() {
            if (maatschappij == null) {
                maatschappij = new ArrayList<AnimatiefilmType.Maatschappijen.Maatschappij>();
            }
            return this.maatschappij;
        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;attribute name="naam" type="{http://www.w3.org/2001/XMLSchema}IDREF" />
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "")
        public static class Maatschappij {

            @XmlAttribute(name = "naam")
            @XmlIDREF
            @XmlSchemaType(name = "IDREF")
            protected Object naam;

            /**
             * Gets the value of the naam property.
             * 
             * @return
             *     possible object is
             *     {@link Object }
             *     
             */
            public Object getNaam() {
                return naam;
            }

            /**
             * Sets the value of the naam property.
             * 
             * @param value
             *     allowed object is
             *     {@link Object }
             *     
             */
            public void setNaam(Object value) {
                this.naam = value;
            }

        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;attribute name="naam" type="{http://www.w3.org/2001/XMLSchema}IDREF" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class Regisseur {

        @XmlAttribute(name = "naam")
        @XmlIDREF
        @XmlSchemaType(name = "IDREF")
        protected Object naam;

        /**
         * Gets the value of the naam property.
         * 
         * @return
         *     possible object is
         *     {@link Object }
         *     
         */
        public Object getNaam() {
            return naam;
        }

        /**
         * Sets the value of the naam property.
         * 
         * @param value
         *     allowed object is
         *     {@link Object }
         *     
         */
        public void setNaam(Object value) {
            this.naam = value;
        }

    }

}
