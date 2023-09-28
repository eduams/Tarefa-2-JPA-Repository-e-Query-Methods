package com.example.aula3.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 200, nullable = false)
    private String nome;
    @Column(nullable = false)
    private double preco;
    @ManyToOne
    @JoinColumn(name = "categoriaProduto_id")
    private CategoriaProduto categoriaProduto;

    public Produto(Long id, String nome, int preco) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
    }

    public Produto() {
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

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return "Produto [id=" + id + ", nome=" + nome + ", preco=" + preco + "]";
    }

    public CategoriaProduto getCategoriaProduto() {
        return categoriaProduto;
    }

    public void setCategoriaProduto(CategoriaProduto categoriaProduto) {
        this.categoriaProduto = categoriaProduto;

}
}