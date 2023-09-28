package com.example.aula3;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.aula3.models.CategoriaProduto;
import com.example.aula3.models.Produto;
import com.example.aula3.repository.CategoriaProdutoRepository;
import com.example.aula3.repository.ProdutoRepository;

@SpringBootApplication
public class Aula3Application {

	@Bean
	public CommandLineRunner init(
			@Autowired ProdutoRepository produtoRepository,
			@Autowired CategoriaProdutoRepository categoriaProdutoRepository) {
		return args -> {
			produtoRepository.save(
					new Produto((long) 0, "tablet", 2000));
			produtoRepository.save(
					new Produto((long) 0, "notebook", 2050));
			produtoRepository.save(
					new Produto((long) 0, "geladeira", 1200));
			produtoRepository.save(
					new Produto((long) 0, "televisão", 5000));
					
			List<Produto> listaProdutos = produtoRepository.findAll();
			listaProdutos.forEach(System.out::println);

			//Retorna todos os produtos com preço maior do que um determinado valor. Nesse caso, faço uma consulta para achar
			//valores maiores que 2001
			System.out.println("** PRODUTOS COM VALOR MAIOR QUE 2001 **");
			listaProdutos = produtoRepository.findByPrecoGreaterThan(2001.0);
			listaProdutos.forEach(System.out::println);

			//Retorna todos os produtos com preço maior do que um determinado valor. Nesse caso, faço uma consulta para achar
			//valores menores e iguais a 2000
			System.out.println("** PRODUTOS COM VALOR MAIOR QUE 2001 **");
			listaProdutos = produtoRepository.findByPrecoLessThanEqual(2000.0);
			listaProdutos.forEach(System.out::println);

			//Retorna todos os produtos que começam com um determinado nome
			System.out.println("** ACHA PRODUTOS COM notebook NO NOME **");
			listaProdutos = produtoRepository.findByNomeLike("%notebook%");  
			listaProdutos.forEach(System.out::println);

			//Inserir categoria
			System.out.println("** INSERE CATEGORIA TI e Eletrodoméstico na tabela CategoriaProduto **");
			CategoriaProduto c1 = new CategoriaProduto(0, "TI");
			CategoriaProduto c2 = new CategoriaProduto(0, "Eletrodoméstico");
			categoriaProdutoRepository.save(c1);
			categoriaProdutoRepository.save(c2);

			//Atualizar a categoria
			System.out.println("** ATUALIZA A CATEGORIA **");
			listaProdutos = produtoRepository.findByNomeLike("%tablet%");  
			listaProdutos.get(0).setCategoriaProduto(c1);
			produtoRepository.save(listaProdutos.get(0));
			listaProdutos = produtoRepository.findByNomeLike("%notebook%");  
			listaProdutos.get(0).setCategoriaProduto(c1);
			produtoRepository.save(listaProdutos.get(0));
			listaProdutos = produtoRepository.findByNomeLike("%geladeira%");  
			listaProdutos.get(0).setCategoriaProduto(c2);
			produtoRepository.save(listaProdutos.get(0));
			listaProdutos = produtoRepository.findByNomeLike("%televisão%");  
			listaProdutos.get(0).setCategoriaProduto(c2);
			produtoRepository.save(listaProdutos.get(0));

			//Usa método no CategoriaProdutoRepository para retornar todas as categorias que começam com eletro
			System.out.println("** Todas as categorias que começam com um determinado nome **");
			List<CategoriaProduto>  cc1 = categoriaProdutoRepository.findByNomeStartingWith("Eletro");
			for (CategoriaProduto categoria : cc1) {
				System.out.println(categoria.getNome());
			}
			
			//Usa método no CategoriaProdutoRepository para retornar todas as categorias que começam com um
			System.out.println("** Retorna uma categoria pelo ID e todos os produtos relacionados **");
			CategoriaProduto cc2 = categoriaProdutoRepository.findCategoriaProdutoFetchProdutos((long) 1);
			System.out.println(cc2.getProdutos().size());
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(Aula3Application.class, args);
	}

}
