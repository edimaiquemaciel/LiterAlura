package br.com.alura.literalura.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public class DadosLivros {
    private int id;
    private String title;
    private List<String> languages;
    private Integer download_count;
    private List<DadosAutor> dadosAutors;

    // Getters e Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<DadosAutor> getAuthors() {
        return dadosAutors;
    }

    public void setAuthors(List<DadosAutor> dadosAutors) {
        this.dadosAutors = dadosAutors;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public Integer getDownload_count() {
        return download_count;
    }

    public void setDownload_count(Integer download_count) {
        this.download_count = download_count;
    }
}
