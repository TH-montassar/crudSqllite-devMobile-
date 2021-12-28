package com.monta.tp6_gestion_etudent.Model;

public class Student {
    private int id;
    private String name ;
    private Double note;

    public Student() {
    }

    public Student(int id, String name, Double note) {
        this.id = id;
        this.name = name;
        this.note = note;
    }

    public Student(String name, Double note) {
        this.name = name;
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getNote() {
        return note;
    }

    public void setNote(Double note) {
        this.note = note;
    }
}
