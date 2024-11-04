package br.com.alura.literalura.repository;

import br.com.alura.literalura.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
    @Query("SELECT COUNT(b) > 0 FROM Livro b WHERE b.titulo LIKE %:title%")
    Boolean verificarExistenciaNoBanco(String title);

    @Query(value = "SELECT * FROM Livro WHERE :language = ANY (Livro.idioma)", nativeQuery = true)
    List<Livro> findLivrosByIdioma(String language);


    @Query("SELECT l FROM Livro l ORDER BY l.numeroDownload DESC LIMIT 10")
    List<Livro> findTop10();
}
