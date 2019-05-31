# escola_ceub
Desenvolvimento de aplicação Web de exemplo de uso de Maven + Hibernate + JSP/Servlet


DDL da base de dados:

```
CREATE DATABASE `dbescola` /*!40100 DEFAULT CHARACTER SET latin1 */;

CREATE TABLE `dbescola.tb_aluno` (
  `id_aluno` int(11) NOT NULL AUTO_INCREMENT,
  `ds_nome` varchar(150) NOT NULL,
  `nu_idade` int(11) NOT NULL,
  `ds_matricula` varchar(10) NOT NULL,
  PRIMARY KEY (`id_aluno`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=latin1;
```
