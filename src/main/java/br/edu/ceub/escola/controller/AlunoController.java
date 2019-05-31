package br.edu.ceub.escola.controller;

import br.edu.ceub.escola.negocio.AlunoBO;
import java.io.IOException;
import java.util.Objects;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "AlunoController", urlPatterns = {"/alunocontroller"})
public class AlunoController extends HttpServlet {

  private AlunoBO alunoBO = new AlunoBO();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    //Pegando dados dos alunos
    try {
      req.setAttribute("alunos", alunoBO.getAlunos());
    } catch (Exception e) {
      req.setAttribute("mensagemErro", "Erro ao obter lista de alunos: " + e.getMessage());
    }

    req.getRequestDispatcher("jsp/cadastroAlunos.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String mensagemErro = "";

    if(Objects.nonNull(req.getParameter("incluir"))){
      //Tratando inclusão
      try {
        alunoBO.incluirAluno( req.getParameter("nome"),
                              req.getParameter("idade"),
                              req.getParameter("matricula"));

        req.setAttribute("mensagemSucesso", "Registro de " + req.getParameter("nome") + " incluído com sucesso!");
      } catch (Exception e) {
        mensagemErro = "Erro ao incluir aluno: " + e.getMessage();
      }
    }
    else if(Objects.nonNull(req.getParameter("excluir"))){
      //Tratando exclusão
      if(alunoBO.excluirAluno(req.getParameter("idAluno"))){
        req.setAttribute("mensagemSucesso", "Registro excluído com sucesso!");
      }
      else{
        mensagemErro = "Não foi possível excluir o registro!";
      }
    }
    else if(Objects.nonNull(req.getParameter("editar"))){
      //Tratando edição
      req.setAttribute("alunoEditando", alunoBO.getAluno(req.getParameter("idAluno")));
    }
    else if(Objects.nonNull(req.getParameter("alterar"))){
      //Tratando atualização
      try{
        alunoBO.alterarAluno( req.getParameter("id"),
                              req.getParameter("nome"),
                              req.getParameter("idade"),
                              req.getParameter("matricula"));
        req.setAttribute("mensagemSucesso", "Registro alterado com sucesso!");
      }
      catch(Exception e){
        mensagemErro = "Não foi possível alterar o registro!";
      }
    }

    //Pegando dados dos alunos
    try {
      req.setAttribute("alunos", alunoBO.getAlunos());
    } catch (Exception e) {
      if (!mensagemErro.isEmpty()) {
        mensagemErro += "<br/>";
      }
      mensagemErro += "Erro ao obter lista de alunos: " + e.getMessage();
    }

    if (!mensagemErro.isEmpty()) {
      req.setAttribute("mensagemErro", mensagemErro);
    }

    req.getRequestDispatcher("jsp/cadastroAlunos.jsp").forward(req, resp);
  }

  @Override
  protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    alunoBO.excluirAluno(req.getParameter("idAluno"));
  }



}
