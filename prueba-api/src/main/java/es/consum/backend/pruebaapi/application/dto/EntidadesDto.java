package es.consum.backend.pruebaapi.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.validation.annotation.Validated;

import es.consum.backend.application.applicationutils.dto.ApplicationDto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Validated
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Data
public class EntidadesDto extends ApplicationDto {

    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "id")
    private Long id;
    @JsonProperty(value = "nroent")
    private Integer nroent;
    @JsonProperty(value = "noment")
    private String noment;
    @JsonProperty(value = "codagrup")
    private String codagrup;
    @JsonProperty(value = "tpopro")
    private Integer tpopro;
    @JsonProperty(value = "disabled")
    private Boolean disabled;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNroent() {
        return nroent;
    }

    public void setNroent(Integer nroent) {
        this.nroent = nroent;
    }

    public String getNoment() {
        return noment;
    }

    public void setNoment(String noment) {
        this.noment = noment;
    }

    public String getCodagrup() {
        return codagrup;
    }

    public void setCodagrup(String codagrup) {
        this.codagrup = codagrup;
    }

    public Integer getTpopro() {
        return tpopro;
    }

    public void setTpopro(Integer tpopro) {
        this.tpopro = tpopro;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

}