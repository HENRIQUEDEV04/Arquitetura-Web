package com.example.ProdutoRepository;

import com.example.ProdutoRepository.Models.Categoria;
import com.example.ProdutoRepository.Models.Produto;
import com.example.ProdutoRepository.Repositories.CategoriaRepository;
import com.example.ProdutoRepository.Repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        
        Categoria categoria1 = new Categoria();
        categoria1.setNome("Eletrônicos");

        Categoria categoria2 = new Categoria();
        categoria2.setNome("Vestuário");

        categoriaRepository.saveAll(List.of(categoria1, categoria2));

        
        Produto produto1 = new Produto();
        produto1.setNome("TV");
        produto1.setPreco(2000.0);
        produto1.setCategoria(categoria1);

        Produto produto2 = new Produto();
        produto2.setNome("Notebook");
        produto2.setPreco(3000.0);
        produto2.setCategoria(categoria1);

        Produto produto3 = new Produto();
        produto3.setNome("Camisa");
        produto3.setPreco(50.0);
        produto3.setCategoria(categoria2);

        produtoRepository.saveAll(List.of(produto1, produto2, produto3));

        
        System.out.println("Produtos com valor maior que 1000:");
        produtoRepository.findByValorGreaterThan(1000.0).forEach(p -> System.out.println(p.getNome()));

        System.out.println("Produtos com valor menor ou igual a 50:");
        produtoRepository.findByValorLessThanEqual(50.0).forEach(p -> System.out.println(p.getNome()));

        System.out.println("Produtos que começam com 'N':");
        produtoRepository.findByNomeStartingWith("N").forEach(p -> System.out.println(p.getNome()));

        System.out.println("Categorias que começam com 'E':");
        categoriaRepository.findByNomeStartingWith("E").forEach(c -> System.out.println(c.getNome()));

        System.out.println("Categoria com ID 1 e produtos relacionados:");
        Categoria categoriaComProdutos = categoriaRepository.findCategoriaByIdWithProdutos(1L);
        if (categoriaComProdutos != null) {
            System.out.println("Categoria: " + categoriaComProdutos.getNome());
            categoriaComProdutos.getProdutos().forEach(p -> System.out.println("Produto: " + p.getNome()));
        } else {
            System.out.println("Categoria não encontrada.");
        }
    }
}
