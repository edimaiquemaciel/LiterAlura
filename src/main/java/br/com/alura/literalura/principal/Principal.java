package br.com.alura.literalura.principal;

import br.com.alura.literalura.model.*;
import br.com.alura.literalura.repository.AutorRepository;
import br.com.alura.literalura.repository.LivroRepository;
import br.com.alura.literalura.service.BookApiResponse;
import br.com.alura.literalura.service.ConsumoApi;
import br.com.alura.literalura.service.ConverteDados;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

@Component
public class Principal {
    private LivroRepository repository;
    private AutorRepository autorRepository;
    private final Scanner leitura = new Scanner(System.in);
    ConsumoApi consumo = new ConsumoApi();
    ConverteDados converter =  new ConverteDados();
    private final String ENDERECO = "https://gutendex.com/books/?search=";

    @Autowired
    public Principal(LivroRepository repository, AutorRepository autorRepository) {
        this.repository = repository;
        this.autorRepository = autorRepository;
    }

    public void exibeMenu(){
        var opcao = -1;
        while (opcao != 0){
            var menu = """
                ***** LiterAlura *****

                Escolha o número de sua opção:

                1- Buscar livro pelo título
                2- Listar livros registrados
                3- Listar autores registrados
                4- Listar autores vivos em um determinado ano
                5- Listar livros em um determinado idioma
                6- Listar Top10 livros mais baixados
                7- Buscar Autor por nome
                8- Mostrar estatísticas de Livros
                0- Sair

                """;
            System.out.println(menu);

            if (leitura.hasNextInt()){
                opcao = leitura.nextInt();
                leitura.nextLine();

                switch (opcao){
                    case 1:
                        buscarLivroPeloTitulo();
                        break;
                    case 2:
                        listarLivrosRegistrados();
                        break;
                    case 3:
                        listarAutoresRegistrados();
                        break;
                    case 4:
                        listarAutoresVivosPorAno();
                        break;
                    case 5:
                        listarLivrosPorIdioma();
                        break;
                    case 6:
                        listaTop10();
                        break;
                    case 7:
                        buscarAutorPorNome();
                        break;
                    case 8:
                        mostrarEstatisticas();
                        break;
                    case 0:
                        System.out.println("Encerrando aplicação!");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Opção inválida!");
                }
            }else {
                System.out.println("entrada inválida!");
                leitura.next();
            }


        }

    }

    private List<DadosLivros> getLivroResult(){
        System.out.println("Insira o nome do livro que você deseja procurar:");
        var nomeLivro = leitura.nextLine();
            var json = consumo.obterDados(ENDERECO + nomeLivro.replace(" ", "+"));
            BookApiResponse result = converter.obterDados(json, BookApiResponse.class);
            List<DadosLivros> dadosLivros = result.getResults();

            return dadosLivros;
    }


    private boolean verifiedBookExistence( DadosLivros result) {
        Livro livro = new Livro(result);

        return repository.verificarExistenciaNoBanco(livro.getTitulo());
    }

    private void listarLivrosPorIdioma() {
        System.out.println("Insira o idioma para realizar a busca");
        var menuIdioma = """
                es - espanhol
                en - inglês
                fr - francês
                pt - português
                
                """;
        System.out.println(menuIdioma);
        if(leitura.hasNext()){
            var idioma = leitura.nextLine();
            List<Livro> livros = repository.findLivrosByIdioma(idioma);
            if (!livros.isEmpty()){
                switch (idioma){
                    case "es":
                        livros.forEach(System.out::println);
                        break;
                    case "en":
                        livros.forEach(System.out::println);
                        break;
                    case "fr":
                        livros.forEach(System.out::println);
                        break;
                    case "pt":
                        livros.forEach(System.out::println);
                        break;
                    default:
                        System.out.println("Opção inválida!");
                }
            }else {
                System.out.println("\nNão exsiem lvros desse idioma no banco de dados");
            }
        }else {
            System.out.println("Escolha uma opção válida!");
        }


    }

    private void listarAutoresVivosPorAno() {
        System.out.println("Insira o ano que deseja pesquisar:");

        if (leitura.hasNextInt()) {
            int ano = leitura.nextInt();
            leitura.nextLine();

            List<Autor> autores = autorRepository.findAutoresVivosPorAno(ano);

            if (!autores.isEmpty()) {
                System.out.println("----- Autores registrados vivos em " + ano + " -----");
                autores.forEach(System.out::println);
            } else {
                System.out.println("\nSem resultados, digite outro ano.");
            }
        } else {
            System.out.println("\nEntrada inválida. Por favor, digite um número inteiro.");
        }
    }


    private void listarAutoresRegistrados() {
        List<Autor> autores = autorRepository.findAll();

        if(!autores.isEmpty()) {
            System.out.println("\n----- Autores Registrados -----");
            autores.forEach(System.out::println);
        } else {
            System.out.println("\nNenhum livro registrado!");
        }
    }

    private void listarLivrosRegistrados() {
        List<Livro> livros = repository.findAll();

        if(!livros.isEmpty()) {
            System.out.println("\n----- Livros Registrados -----");
            livros.forEach(System.out::println);
        } else {
            System.out.println("\nNenhum livro registrado!");
        }
    }

    private void buscarLivroPeloTitulo() {
        List<DadosLivros> listaDadosLivros = getLivroResult();

        if (listaDadosLivros.isEmpty()) {
            System.out.println("Nenhum livro encontrado na API para o título fornecido.");
            return;
        }

        if (!verifiedBookExistence(listaDadosLivros.get(0))) {
            DadosLivros dadosLivros = listaDadosLivros.get(0);
            DadosAutor dadosAutor = new DadosAutor(dadosLivros);
            Livro livro = new Livro(dadosLivros);
            Optional<Autor> optionalAuthor = autorRepository.findByNome(dadosAutor.getName());
            if (optionalAuthor.isPresent()) {
                Autor existingAuthor = optionalAuthor.get();
                livro.setAutor(existingAuthor);
                existingAuthor.getLivros().add(livro);
                autorRepository.save(existingAuthor);
            } else {
                Autor newAuthor = new Autor(dadosAutor);
                livro.setAutor(newAuthor);
                newAuthor.getLivros().add(livro);
                autorRepository.save(newAuthor);
            }

            System.out.println(
                    "\n----- LIVRO -----\n" +
                            "Título: " + dadosLivros.getTitle() + "\n" +
                            "Autor: " + dadosLivros.getAuthors().getFirst().getName() + "\n" +
                            "Idioma: " + dadosLivros.getLanguages().getFirst() + "\n" +
                            "Número de Downloads: " + dadosLivros.getDownload_count() + "\n"
            );
        } else {
            System.out.println("O livro já existe");
        }
    }
    private void listaTop10() {
        List<Livro> livros = repository.findTop10();

        if (!livros.isEmpty()) {
            System.out.println("\n----- Top 10 Livros Baixados -----");
            livros.forEach(l -> System.out.println(l.getTitulo()));
        } else {
            System.out.println("\nNão foram encontrados livros no banco de dados");
        }

    }

    private void buscarAutorPorNome(){
        System.out.println("Informe o nome do autor para busca");
        var nome = leitura.nextLine();
        Optional<Autor> autor = autorRepository.findByNomeContainingIgnoreCase(nome);
        if (autor.isPresent()){
            System.out.println(autor.get());
        }else {
            System.out.println("Não existe esse autor no Banco de Dados");
        }

    }

    private void mostrarEstatisticas() {
        List<Livro> livros = repository.findAll();

        if (!livros.isEmpty()) {
            IntSummaryStatistics sta = livros.stream()
                    .filter(l -> l.getNumeroDownload() > 0)
                    .collect(Collectors.summarizingInt(Livro::getNumeroDownload));

            System.out.println("\n----- Estatísticas de Banco de Dados -----");
            System.out.println("Mádia de downloads: " + sta.getAverage());
            System.out.println("Max downloads: " + sta.getMax());
            System.out.println("Min downloads: " + sta.getMin());
            System.out.println("Livros Registrados: " + sta.getCount());
        } else {
            System.out.println("\nSem Estatísticas de Banco de Dados");
        }

    }

}
