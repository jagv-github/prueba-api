package es.consum.backend.pruebaapi.domain.model;

public  class EntidadesBuilder {
    public Long id;
    public Integer nroent;
    private String noment;
    private String codagrup;
    private Integer tpopro;
    private Boolean disabled;

    EntidadesBuilder() {

    }

    public Entidades build() {
        Entidades entidad = new Entidades(this);
        return entidad;
    }

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
