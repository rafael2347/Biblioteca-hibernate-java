package org.example;

import java.util.Collection;

public class SedeEntity {
    private int idSede;
    private String nomSede;
    private Collection<DepartamentoEntity> listaDepartamentos;

    public int getIdSede() {
        return idSede;
    }

    public void setIdSede(int idSede) {
        this.idSede = idSede;
    }

    public String getNomSede() {
        return nomSede;
    }

    public void setNomSede(String nomSede) {
        this.nomSede = nomSede;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SedeEntity that = (SedeEntity) o;

        if (idSede != that.idSede) return false;
        if (nomSede != null ? !nomSede.equals(that.nomSede) : that.nomSede != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idSede;
        result = 31 * result + (nomSede != null ? nomSede.hashCode() : 0);
        return result;
    }

    public Collection<DepartamentoEntity> getListaDepartamentos() {
        return listaDepartamentos;
    }

    public void setListaDepartamentos(Collection<DepartamentoEntity> listaDepartamentos) {
        this.listaDepartamentos = listaDepartamentos;
    }
}
