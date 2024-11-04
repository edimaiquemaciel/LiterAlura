package br.com.alura.literalura.model;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Integer anoNascimento;
    private Integer anoFalecimento;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Livro> livros = new ArrayList<>();

    public Autor(){}
    public Autor(DadosAutor dadosAutor){
        this.nome = dadosAutor.getName();
        this.anoNascimento = dadosAutor.getBirthYear();
        this.anoFalecimento = dadosAutor.getDeathYear();
    };


    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = new ArrayList<>();
        livros.forEach(b -> {
            b.setAutor(this);
            this.livros.add(b);
        });
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getAnoNascimento() {
        return anoNascimento;
    }

    public void setAnoNascimento(Integer anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    public Integer getAnoFalecimento() {
        return anoFalecimento;
    }

    public void setAnoFalecimento(Integer anoFalecimento) {
        this.anoFalecimento = anoFalecimento;
    }


    @Override
    public String toString() {
        List<String> livros = this.getLivros().stream().map(Livro::getTitulo).toList();
        StringBuilder livrosComIndice = new StringBuilder();
        for (int i = 0; i < livros.size(); i++) {
            livrosComIndice.append(i + 1).append(" - ").append(livros.get(i));
            if (i < livros.size() - 1) {
                livrosComIndice.append(", "); // Add a comma between books, except for the last one
            }
        }
        return  "\nAutor: " + nome + "\n" +
                "Ano de nascimento: " + anoNascimento + "\n" +
                "Ano de falecimento: " + anoFalecimento + "\n" +
                "Livros: " + livrosComIndice + "\n";
    }
}
