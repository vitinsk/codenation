package com.codenation.demo.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Response implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("numero_casas")
    Integer numeroCasas;
    String token;
    String cifrado;
    String decifrado;
    @JsonProperty("resumo_criptografico")
    String resumoCriptografico;

}
