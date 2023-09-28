package com.example.aula3.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.aula3.models.CategoriaProduto;


public interface CategoriaProdutoRepository extends JpaRepository<CategoriaProduto,Integer>{        

    List<CategoriaProduto> findByNomeStartingWith(@Param("nome") String nome);

    @Query("select cc from CategoriaProduto cc left join fetch cc.produtos c where cc.id = :id ")
    CategoriaProduto findCategoriaProdutoFetchProdutos(@Param("id") Long id);
}
