package org.example;

public class EmpleadoEntity {
    private String dni;
    private String nomEmp;
    private int idDepto;
    private DepartamentoEntity listaDepartamentos;

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNomEmp() {
        return nomEmp;
    }

    public void setNomEmp(String nomEmp) {
        this.nomEmp = nomEmp;
    }

    public int getIdDepto() {
        return idDepto;
    }

    public void setIdDepto(int idDepto) {
        this.idDepto = idDepto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmpleadoEntity that = (EmpleadoEntity) o;

        if (idDepto != that.idDepto) return false;
        if (dni != null ? !dni.equals(that.dni) : that.dni != null) return false;
        if (nomEmp != null ? !nomEmp.equals(that.nomEmp) : that.nomEmp != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = dni != null ? dni.hashCode() : 0;
        result = 31 * result + (nomEmp != null ? nomEmp.hashCode() : 0);
        result = 31 * result + idDepto;
        return result;
    }

    public DepartamentoEntity getListaDepartamentos() {
        return listaDepartamentos;
    }

    public void setListaDepartamentos(DepartamentoEntity listaDepartamentos) {
        this.listaDepartamentos = listaDepartamentos;
    }
}
