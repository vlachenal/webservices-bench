//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.11 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2019.11.22 à 07:11:29 PM CET 
//


package com.github.vlachenal.webservice.bench.soap.api;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour mapper.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="mapper"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="MANUAL"/&gt;
 *     &lt;enumeration value="DOZER"/&gt;
 *     &lt;enumeration value="MAPSTRUCT"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "mapper")
@XmlEnum
public enum Mapper {

    MANUAL,
    DOZER,
    MAPSTRUCT;

    public String value() {
        return name();
    }

    public static Mapper fromValue(String v) {
        return valueOf(v);
    }

}
