package com.example.ProdutoRepository.Repositories;

import com.example.ProdutoRepository.Models.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    List<Produto> findByValorGreaterThan(Double valor);

    List<Produto> findByValorLessThanEqual(Double valor);

    List<Produto> findByNomeStartingWith(String nome);
}
