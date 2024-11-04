package br.com.alura.literalura.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DadosAutor {
    private String name;
    @JsonProperty("birth_year")
    private int birthYear;

    @JsonProperty("death_year")
    private int deathYear;

    public DadosAutor(){}
    public DadosAutor(DadosLivros dadosLivros) {
        this.name = dadosLivros.getAuthors().get(0).getName();
        this.birthYear = dadosLivros.getAuthors().get(0).getBirthYear();
        this.deathYear = dadosLivros.getAuthors().get(0).getDeathYear();
    }


    // Getters e Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public int getDeathYear() {
        return deathYear;
    }

    public void setDeathYear(int deathYear) {
        this.deathYear = deathYear;
    }
}
