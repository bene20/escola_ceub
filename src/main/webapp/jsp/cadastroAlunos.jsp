<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Cadastro de alunos</title>
  </head>
  <body>
    <h1>Cadastro de alunos</h1>

    <div style="color: green">${mensagemSucesso}</div>
    <div style="color: red">${mensagemErro}</div>
    
    <c:if test="${empty alunoEditando}">
      <fieldset>
        <legend>Inclusão de aluno</legend>
        <form action="${pageContext.request.contextPath}/alunocontroller" method="post">
          Nome: <input type="text" name="nome" placeholder="Nome do aluno"/><br/>
          Idade: <input type="number" min="0" name="idade" placeholder="Idade do aluno"/><br/>
          Matrícula: <input type="text" maxlength="10" name="matricula" placeholder="Matrícula do aluno"/><br/>
          <input type="submit" value="Incluir" name="incluir"/>
        </form>
      </fieldset>
    </c:if>
    
    <c:if test="${not empty alunoEditando}">
      <fieldset>
        <legend>Edição de aluno</legend>
        <form action="${pageContext.request.contextPath}/alunocontroller" method="post">
          <input type="hidden" name="id" value="${alunoEditando.id}"/>
          Nome: <input type="text" name="nome" placeholder="Nome do aluno" value="${alunoEditando.nome}"/><br/>
          Idade: <input type="number" min="0" name="idade" placeholder="Idade do aluno" value="${alunoEditando.idade}"/><br/>
          Matrícula: <input type="text" maxlength="10" name="matricula" placeholder="Matrícula do aluno" value="${alunoEditando.matricula}"/><br/>
          <input type="submit" value="Alterar" name="alterar"/>
        </form>
      </fieldset>
    </c:if>
    

    <table style="border: 1px solid black; border-collapse: collapse; margin-top: 20px; width: 98%" border="1">
      <thead>
        <tr>
          <th>ID</th>
          <th>Nome</th>
          <th>Idade</th>
          <th>Matrícula</th>
          <th>Ação</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach var="aluno" items="${alunos}">
          <tr>
            <td>${aluno.id}</td>
            <td>${aluno.nome}</td>
            <td>${aluno.idade}</td>
            <td>${aluno.matricula}</td>
            <td>
              <form method="post" action="${pageContext.request.contextPath}/alunocontroller"
                    style="float: left">
                <input type="hidden" name="idAluno" value="${aluno.id}"/>
                <input type="submit" value="Excluir" name="excluir"/>
              </form>
              <form method="post" action="${pageContext.request.contextPath}/alunocontroller"
                    style="float: left; margin-left: 10px">
                <input type="hidden" name="idAluno" value="${aluno.id}"/>
                <input type="submit" value="Editar" name="editar"/>
              </form>
            </td>
          </tr>
        </c:forEach>
      </tbody>
    </table>

    <hr/>
    <a href="${pageContext.request.contextPath}">Sair</a>
  </body>
</html>
