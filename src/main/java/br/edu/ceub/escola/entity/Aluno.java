package br.edu.ceub.escola.entity;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_aluno")
public class Aluno {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_aluno")
  private Integer id;

  @Column(name = "ds_nome", length = 150, nullable = false)
  private String nome;

  @Column(name = "nu_idade", nullable = false)
  private Integer idade;

  @Column(name = "ds_matricula", nullable = false, length = 10)
  private String matricula;

  public Aluno() {}

  public Aluno(String nome, Integer idade, String matricula) {
    this.nome = nome;
    this.idade = idade;
    this.matricula = matricula;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public Integer getIdade() {
    return idade;
  }

  public void setIdade(Integer idade) {
    this.idade = idade;
  }

  public String getMatricula() {
    return matricula;
  }

  public void setMatricula(String matricula) {
    this.matricula = matricula;
  }

  @Override
  public int hashCode() {
    int hash = 31;
    hash = 31 * hash + Objects.hashCode(this.id);
    return hash;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final Aluno other = (Aluno) obj;
    if (!Objects.equals(this.id, other.id)) {
      return false;
    }
    return true;
  }
}
