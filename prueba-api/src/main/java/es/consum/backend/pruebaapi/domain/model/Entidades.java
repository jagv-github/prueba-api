package es.consum.backend.pruebaapi.domain.model;

import es.consum.backend.domain.domainutils.model.AggregateRoot;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
//import javax.persistence.Entity;
//import javax.persistence.Table;
//import javax.persistence.Column;
//import javax.persistence.Id;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ENTIDADES")
@EqualsAndHashCode(callSuper = false)
public class Entidades extends AggregateRoot {

    private static final long serialVersionUID = 1L;

    @Column(name = "ID")
    @Id
    private Long id;

    @Column(name = "NROENT")
    private Integer nroent;

    @Column(name = "NOMENT")
    private String noment;

    @Column(name = "CODAGRUP")
    private String codagrup;

    @Column(name = "TPOPRO")
    private Integer tpopro;

    @Column(name = "DISABLED")
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


    Entidades(EntidadesBuilder builder) {
        this.id = builder.getId();
        this.nroent = builder.getNroent();
        this.noment = builder.getNoment();
        this.codagrup = builder.getCodagrup();
        this.tpopro = builder.getTpopro();
        this.disabled = builder.getDisabled();
    }

    public static EntidadesBuilder builder() {
        return new EntidadesBuilder();
    }
}

