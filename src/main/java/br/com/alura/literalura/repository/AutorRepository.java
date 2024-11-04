package br.com.alura.literalura.repository;

import br.com.alura.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {
    Optional<Autor> findByNome(String name);

    Optional<Autor> findByNomeContainingIgnoreCase(String name);

    @Query("SELECT a FROM Autor a WHERE " +
            "(a.anoNascimento <= :ano AND (a.anoFalecimento = 0 OR a.anoFalecimento > :ano))")
    List<Autor> findAutoresVivosPorAno(@Param("ano") Integer ano);
}