package gestion;

import java.io.Serializable;

public class Persona implements Serializable {
    private static final long serialVersionUID = 1L;

    private int codigo;
    private String apellido;
    private String nombre;
    private char sexo;
    private String fechaNacimiento;
    private String ciudad;
    private String telefono;
    private String correo;
    private String profesion;
    private double salario;

    public Persona() {}

    public Persona(int codigo, String apellido, String nombre, char sexo,
                   String fechaNacimiento, String ciudad, String telefono,
                   String correo, String profesion, double salario) {
        this.codigo = codigo;
        this.apellido = apellido;
        this.nombre = nombre;
        this.sexo = sexo;
        this.fechaNacimiento = fechaNacimiento;
        this.ciudad = ciudad;
        this.telefono = telefono;
        this.correo = correo;
        this.profesion = profesion;
        this.salario = salario;
    }

    // Getters y Setters
    public int getCodigo() { return codigo; }
    public void setCodigo(int codigo) { this.codigo = codigo; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public char getSexo() { return sexo; }
    public void setSexo(char sexo) { this.sexo = sexo; }

    public String getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(String fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

    public String getCiudad() { return ciudad; }
    public void setCiudad(String ciudad) { this.ciudad = ciudad; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getProfesion() { return profesion; }
    public void setProfesion(String profesion) { this.profesion = profesion; }

    public double getSalario() { return salario; }
    public void setSalario(double salario) { this.salario = salario; }

    @Override
    public String toString() {
        return String.format("| %-4d | %-15s | %-15s | %-5c | %-12s | %-15s | %-12s | %-25s | %-18s | %10.2f |",
                codigo, apellido, nombre, sexo, fechaNacimiento, ciudad,
                telefono, correo, profesion, salario);
    }
}