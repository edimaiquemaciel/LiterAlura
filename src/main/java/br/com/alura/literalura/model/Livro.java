package br.com.alura.literalura.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private List<String> idioma = new ArrayList<>();
    private Integer numeroDownload;

    @ManyToOne(fetch = FetchType.EAGER)
    private Autor autor;
    public Livro(){}

    public Livro(DadosLivros dadosLivros) {
        this.titulo = dadosLivros.getTitle();
        this.idioma = dadosLivros.getLanguages();
        this.numeroDownload = dadosLivros.getDownload_count();
    }


    public List<String> getIdioma() {
        return idioma;
    }

    public void setIdioma(List<String> idioma) {
        this.idioma = idioma;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Integer getNumeroDownload() {
        return numeroDownload;
    }

    public void setNumeroDownload(Integer numeroDownload) {
        this.numeroDownload = numeroDownload;
    }

    @Override
    public String toString() {
        return "\n----- LIVRO -----\n"+
                "Título: "+ titulo+ "\n"+
                "Autor: "+ autor.getNome()+ "\n"+
                "Idioma: "+ idioma.getFirst() + "\n"+
                "Número de Downloads: "+ numeroDownload+ "\n";
    }
}
