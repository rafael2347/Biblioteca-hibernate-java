package org.example;

import java.util.Collection;

public class DepartamentoEntity {
    private int idDepto;
    private String nomDepto;
    private int idSede;
    private SedeEntity sede;
    private Collection<EmpleadoEntity> empleadosByIdDepto;

    public int getIdDepto() {
        return idDepto;
    }

    public void setIdDepto(int idDepto) {
        this.idDepto = idDepto;
    }

    public String getNomDepto() {
        return nomDepto;
    }

    public void setNomDepto(String nomDepto) {
        this.nomDepto = nomDepto;
    }

    public int getIdSede() {
        return idSede;
    }

    public void setIdSede(int idSede) {
        this.idSede = idSede;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DepartamentoEntity that = (DepartamentoEntity) o;

        if (idDepto != that.idDepto) return false;
        if (idSede != that.idSede) return false;
        if (nomDepto != null ? !nomDepto.equals(that.nomDepto) : that.nomDepto != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idDepto;
        result = 31 * result + (nomDepto != null ? nomDepto.hashCode() : 0);
        result = 31 * result + idSede;
        return result;
    }

    public SedeEntity getSede() {
        return sede;
    }

    public void setSede(SedeEntity sede) {
        this.sede = sede;
    }

    public Collection<EmpleadoEntity> getEmpleadosByIdDepto() {
        return empleadosByIdDepto;
    }

    public void setEmpleadosByIdDepto(Collection<EmpleadoEntity> empleadosByIdDepto) {
        this.empleadosByIdDepto = empleadosByIdDepto;
    }
}
