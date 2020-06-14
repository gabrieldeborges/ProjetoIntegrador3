/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gerenciamento;

import static Gerenciamento.GerenciamentoOperacao.verificaDistancia;
import Objetos.Usuario;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Lucas Filipe
 */

public class GerenciamentoUsuario {
     List<Usuario> listUsuario = new ArrayList<Usuario>();
    
    public void inserirUsuario (Usuario usuario) {
        
        listUsuario.add(usuario);
        
  
    }
    
    public static void inserir(Usuario usuario) throws Exception {
    //Monta a string de inserção de um cliente no BD,
        //utilizando os dados do clientes passados como parâmetro
      
       
        String sql = "INSERT INTO usuarios (NOME,SOBRENOME, EMAIL, SENHA, SEXO, CIDADE_NASC , "
                + "UF_NASC, DATA_NASC, CPF, NOME_MAE, ESPORTE_FAVORITO, UNIDADE_FAVORITA, CEP, ENDERECO, COMPLEMENTO,"
                + "NUMERO_ENDE, BAIRRO, CIDADE, SITUACAO_CADASTRO, UF) "
                + " VALUES (?, ?, ?, ?, ?, ?,?,?,?,?, ?, ?, ?, ?, ?,?,?,?, ?, ?)";

        //Conexão para abertura e fechamento
        Connection connection = null;
        //Statement para obtenção através da conexão e execução de
        //comandos SQL
        PreparedStatement preparedStatement = null;
        //Abre uma conexão com o banco de dados
        connection = ConnectionUtils.getConnection();
        //Cria um statement para execução de instruções SQL
        preparedStatement = connection.prepareStatement(sql);
        //Configura os parâmetros do "PreparedStatement"
       
        
        preparedStatement.setString(1, usuario.propNome);
        preparedStatement.setString(2, usuario.propSobrenome);
        preparedStatement.setString(3, usuario.propEmail);
        preparedStatement.setString(4, usuario.propSenha);
        if(usuario.Sexo == "Masculino"){
        preparedStatement.setString(5, "M");
        }else{
        preparedStatement.setString(5, "F");
        }
        preparedStatement.setString(6, usuario.propCidadeRes);
        preparedStatement.setString(7, usuario.propPais);
        
        System.out.println(usuario.dtNascimento);
        
        String data = (usuario.dtNascimento).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        preparedStatement.setString(8, data);
        preparedStatement.setString(9,usuario.propCpf);
        preparedStatement.setString(10,usuario.propNomeMae);
        preparedStatement.setString(11,usuario.esportePref);
        preparedStatement.setString(12, usuario.unidadePref);
        preparedStatement.setString(13,usuario.propCep);
        preparedStatement.setString(14,usuario.propEndereco);
        preparedStatement.setString(15,usuario.propComplemento);
       
        preparedStatement.setString(16,usuario.propNumero);
        preparedStatement.setString(17,usuario.propBairro);
        preparedStatement.setString(18,usuario.propCidade);
        preparedStatement.setString(19,"1");
        preparedStatement.setString(20,"BR");
        //Executa   preparedStatement.setString(18,usuario.propCidade);o comando no banco de dados
        preparedStatement.execute();
            
        
        //Fecha o statement
        preparedStatement.close();

        //Fecha a conexão
        connection.close();
       

    }

    public static int verificar_login(String user, String senha) throws Exception{
    
         String sql = "SELECT ID_USUARIO FROM usuarios where EMAIL = ? and SENHA = ?";
         
        //Conexão para abertura e fechamento
        Connection connection = null;
        //Statement para obtenção através da conexão e execução de
        //comandos SQL
        PreparedStatement preparedStatement = null;
        
        //Armazenarã os resultados do banco de dados
        ResultSet result = null;

        //Abre uma conexão com o banco de dados
        connection = ConnectionUtils.getConnection();

        //Cria um statement para execução de instruçães SQL
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, user);
        preparedStatement.setString(2, senha);

        int id= -1;
        //Executa a consulta SQL no banco de dados
       ResultSet rs = preparedStatement.executeQuery();
       
        while (rs.next()) {
	id = rs.getInt("ID_USUARIO");
}     //Fecha o statement
        
        
        
        preparedStatement.close();
        System.out.println(id);
    return id;
    }
    
    public static String RetornaUser(int id) throws Exception{
        
    String sql = "SELECT * FROM usuarios where ID_USUARIO = ?";
        System.out.println("a variavel recebida" + id);
        //Conexão para abertura e fechamento
        Connection connection = null;
        //Statement para obtenção através da conexão e execução de
        //comandos SQL
        PreparedStatement preparedStatement = null;
        
        //Armazenarã os resultados do banco de dados
        ResultSet result = null;

        //Abre uma conexão com o banco de dados
        connection = ConnectionUtils.getConnection();

        //Cria um statement para execução de instruçães SQL
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
       
        //Executa a consulta SQL no banco de dados
       ResultSet rs = preparedStatement.executeQuery();
       
     
        rs.next();
        
        String retorna = rs.getString("Nome");
      
        System.out.println(rs.getString("Nome"));
    preparedStatement.close();
   rs.close();
        return retorna;
        
    }
    
    public static List<Unidade> listar(String cep, String modalidade)
            throws Exception {

        //Monta a string de listagem de clientes no banco, considerando
        //apenas a coluna de ativação de clientes ("enabled")
        String sql = "SELECT * FROM LISTAS_EQUIPAMENTOS where EQUIPAMENTOS = ?";

        //Lista de clientes de resultado
        List<Unidade> listaUnidade = null;

        //Conexão para abertura e fechamento
        Connection connection = null;
        //Statement para obtenção através da conexão e execução de
        //comandos SQL
        PreparedStatement preparedStatement = null;
        //Armazenarã os resultados do banco de dados
        ResultSet result = null;

        //Abre uma conexão com o banco de dados
        connection = ConnectionUtils.getConnection();

        //Cria um statement para execução de instruçães SQL
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, modalidade);

        
        //Executa a consulta SQL no banco de dados
        result = preparedStatement.executeQuery();

        //Itera por cada item do resultado
        while (result.next()) {

            //Se a lista não foi inicializada, a inicializa
            if (listaUnidade == null) {
                listaUnidade = new ArrayList<Unidade>();
            }

            //Cria uma instância de Cliente e popula com os valores do BD
            Unidade unid = new Unidade();
            
            unid.setUnidade(result.getString("UNIDADE_ESPORTIVA"));
            unid.setBairro(result.getString("DISTRITO"));
            unid.setAvaliacao(result.getDouble("avaliacao"));
            unid.setId(result.getInt("ID"));
            String dist = verificaDistancia(result.getString("CEP"), cep);
            
            unid.setDistancia(dist);
                        listaUnidade.add(unid);

        }

        //Fecha o result        
        result.close();

        //Fecha o statement
        preparedStatement.close();

        //Fecha a conexão
        connection.close();

        //Retorna a lista de clientes do banco de dados
        return listaUnidade;
    }

    
    
}
