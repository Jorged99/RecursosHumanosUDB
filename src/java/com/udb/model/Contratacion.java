/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.udb.model;
import java.sql.Date;
/**
 *
 * @author jorge
 */
public class Contratacion {
  
    private int idContratacion;
    private Empleado empleado; // Relación con Empleado
    private Departamento departamento; // Relación con Departamento
    private Cargo cargo; // Relación con Cargo
    private TipoContratacion tipoContratacion; // Relación con TipoContratacion
    private Date fechaContracion;
    private double salario;
    private boolean estado;

    // ... Constructores, Getters y Setters ...

    public int getIdContratacion() {
        return idContratacion;
    }

    public void setIdContratacion(int idContratacion) {
        this.idContratacion = idContratacion;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public TipoContratacion getTipoContratacion() {
        return tipoContratacion;
    }

    public void setTipoContratacion(TipoContratacion tipoContratacion) {
        this.tipoContratacion = tipoContratacion;
    }

    public Date getFechaContracion() {
        return fechaContracion;
    }

    public void setFechaContracion(Date fechaContracion) {
        this.fechaContracion = fechaContracion;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}

