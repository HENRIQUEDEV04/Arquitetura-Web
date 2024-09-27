package com.example.ProdutoRepository.Repositories;

import com.example.ProdutoRepository.Models.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    List<Categoria> findByNomeStartingWith(String nome);

    @Query("SELECT c FROM Categoria c LEFT JOIN FETCH c.produtos WHERE c.id = :id")
    Categoria findCategoriaByIdWithProdutos(@Param("id") Long id);
}
