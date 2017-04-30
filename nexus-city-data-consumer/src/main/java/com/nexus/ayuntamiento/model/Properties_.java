
package com.nexus.ayuntamiento.model;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "idtramo",
    "lectura",
    "des_tramo"
})
public class Properties_ {

    @JsonProperty("idtramo")
    private String idtramo;
    @JsonProperty("lectura")
    private String lectura;
    @JsonProperty("des_tramo")
    private String desTramo;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public Properties_() {
    }

    /**
     * 
     * @param idtramo
     * @param lectura
     * @param desTramo
     */
    public Properties_(String idtramo, String lectura, String desTramo) {
        super();
        this.idtramo = idtramo;
        this.lectura = lectura;
        this.desTramo = desTramo;
    }

    @JsonProperty("idtramo")
    public String getIdtramo() {
        return idtramo;
    }

    @JsonProperty("idtramo")
    public void setIdtramo(String idtramo) {
        this.idtramo = idtramo;
    }

    @JsonProperty("lectura")
    public String getLectura() {
        return lectura;
    }

    @JsonProperty("lectura")
    public void setLectura(String lectura) {
        this.lectura = lectura;
    }

    @JsonProperty("des_tramo")
    public String getDesTramo() {
        return desTramo;
    }

    @JsonProperty("des_tramo")
    public void setDesTramo(String desTramo) {
        this.desTramo = desTramo;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
