package com.example.aula3.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.aula3.models.Produto;



public interface ProdutoRepository extends JpaRepository<Produto,Long>{
    List<Produto> findByNomeLike(String nome);
    List<Produto> findByPrecoGreaterThan(Double valor);
    List<Produto> findByPrecoLessThanEqual(Double valor);
}