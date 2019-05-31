package br.edu.ceub.escola.negocio;

import br.edu.ceub.escola.entity.Aluno;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class AlunoBO {

  private static final EntityManagerFactory emf =
                        Persistence.createEntityManagerFactory("escolaPU");

  public void incluirAluno(String nome, String idade, String matricula) {

    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();

    Aluno aluno = new Aluno(nome, Integer.valueOf(idade), matricula);
    em.persist(aluno);

    em.getTransaction().commit();
    em.close();
  }

  public List<Aluno> getAlunos() {
    List<Aluno> alunos;
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();

    alunos = em.createQuery("from Aluno").getResultList();

    em.getTransaction().commit();
    em.close();

    return alunos;
  }

  public Boolean excluirAluno(String idAluno){
    try{
      EntityManager em = emf.createEntityManager();
      em.getTransaction().begin();

      Aluno aluno = em.getReference(Aluno.class, Integer.valueOf(idAluno));
      em.remove(aluno);

      em.getTransaction().commit();
      em.close();

      return true;
    }
    catch(Exception e){
      System.err.println("Erro ao excluir aluno de id " + idAluno + ": " + e.getMessage());
      return false;
    }
  }

  public Aluno getAluno(String idAluno){
    Aluno aluno;
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();

    aluno = em.find(Aluno.class, Integer.valueOf(idAluno));

    em.getTransaction().commit();
    em.close();

    return aluno;
  }

  public void alterarAluno(String id, String nome, String idade, String matricula){
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();

    Aluno aluno = em.find(Aluno.class, Integer.valueOf(id));
    aluno.setNome(nome);
    aluno.setIdade(Integer.valueOf(idade));
    aluno.setMatricula(matricula);
    em.merge(aluno);

    em.getTransaction().commit();
    em.close();
  }
}
