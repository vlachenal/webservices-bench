//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.11 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2019.11.22 à 07:11:29 PM CET 
//


package com.github.vlachenal.webservice.bench.soap.api;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour anonymous complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="test" type="{http://github.com/vlachenal/webservices-bench}test-suite"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "test"
})
@XmlRootElement(name = "consolidateRequest")
public class ConsolidateRequest {

    @XmlElement(required = true)
    protected TestSuite test;

    /**
     * Obtient la valeur de la propriété test.
     * 
     * @return
     *     possible object is
     *     {@link TestSuite }
     *     
     */
    public TestSuite getTest() {
        return test;
    }

    /**
     * Définit la valeur de la propriété test.
     * 
     * @param value
     *     allowed object is
     *     {@link TestSuite }
     *     
     */
    public void setTest(TestSuite value) {
        this.test = value;
    }

}
