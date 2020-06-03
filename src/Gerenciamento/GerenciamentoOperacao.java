package Gerenciamento;

import Objetos.FeedBack;
import Objetos.Operacao;
import Objetos.Usuario;
import api.root;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class GerenciamentoOperacao {
    
    List<Unidade> listOperacao = new ArrayList<Unidade>();
    
    public void inserirUsuario (Operacao operacao) {
        
        //listOperacao.add(Unidade);
        
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
            unid.setAvaliacao(result.getInt("avaliacao"));
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

    public static String verificaDistancia (String cepUni, String cepUser){
        String distancia = "-1";
        	 try {
	            URL url = new URL("https://maps.googleapis.com/maps/api/distancematrix/json?origins="+cepUser+"&destinations="+cepUni+"&mode=walking&language=PT-FR&key=AIzaSyDg1SDu-K9k1Kxtz8WFEr_ZallAOAXrFok");//your url i.e fetch data from .
	            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	            conn.setRequestMethod("GET");
	            conn.setRequestProperty("Accept", "application/json");
	            if (conn.getResponseCode() != 200) {
	                throw new RuntimeException("Failed : HTTP Error code : "
	                        + conn.getResponseCode());
	            }
	            InputStreamReader in = new InputStreamReader(conn.getInputStream());
	            BufferedReader br = new BufferedReader(in);

	            String output = "";
	            String line;
	            while ((line = br.readLine()) != null) {
	                output += line;
	            }
	            System.out.println(output);

                    Gson gson = new Gson();
	            
	            root n = gson.fromJson(new String(output.getBytes()), root.class);
	            	
	            System.out.println("ENTROU NA API "+n.getRows().get(0).getElements().get(0).getDistance().getValue());
                    distancia = n.getRows().get(0).getElements().get(0).getDistance().getText();
                    
                    
	            conn.disconnect();
	            	return distancia;
                        
                        
	        } catch (Exception e) {
	            System.out.println("Exception in NetClientGet:- " + e);
	        }

              return distancia; 
    }
        public static void AdicionaAluguel (Operacao op) throws Exception{
        
            try {
                
           
            
         String sql = "INSERT INTO Alugueis (ID_EQUIPAMENTO, ID_USUARIO, DIA, situacao)"
                + " VALUES (?, ?, ?, ?)";

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
       
                System.out.println("ID DO USER" + op.iduser);
        preparedStatement.setInt(1, op.idEquip);
        preparedStatement.setInt(2, op.iduser);
        String data = (op.data).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        preparedStatement.setString(3, data);
        preparedStatement.setString(4, "0");
   
        //Executa   preparedStatement.setString(18,usuario.propCidade);o comando no banco de dados
        preparedStatement.execute();
                System.out.println("ALUGADO");
        
        //Fecha o statement
        preparedStatement.close();

        //Fecha a conexão
        connection.close();
        
        } catch (Exception e) {
                System.out.println();
               System.out.println("Exception in NetClientGet:- " + e);
        }
       
        }
        
        
        public static void mandaAvaliacao(int idEquip, int avaliacao) throws Exception {
    //Monta a string de inserção de um cliente no BD,
        //utilizando os dados do clientes passados como parâmetro
      
        try{
        String sql = "INSERT INTO LISTAS_EQUIPAMENTOS (avaliacao) where id = ?"
                + " VALUES (?)";

        //Conexão para abertura e fechamento
        Connection connection = null;
        //Statement para obtenção através da conexão e execução de
        //comandos SQL
        PreparedStatement preparedStatement = null;
        //Abre uma conexão com o banco de dados
        connection = ConnectionUtils.getConnection();
        preparedStatement = connection.prepareStatement(sql);
       
        
        preparedStatement.setInt(1, idEquip);
        preparedStatement.setInt(2, avaliacao);
        preparedStatement.execute();
        
        //Fecha o statement
        preparedStatement.close();

        //Fecha a conexão
        connection.close();
    
        }catch (Exception e) {
                System.out.println();
               System.out.println("Exception in NetClientGet:- " + e);
        }

        
        }
        
          public static int verificaFeedback (int idUser) throws Exception{
        
              //METODO PARA VERIFICAR SE USUARIO TEM ALUGUEIS PARA AVALIAR
        int idEquipRetorna = -1;
       
            try {

         String sql = "SELECT ID_EQUIPAMENTO, DIA FROM ALUGUEIS WHERE ID_USUARIO = ?";

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
       
        preparedStatement.setInt(1, idUser);
        ResultSet result = null;
        
         result = preparedStatement.executeQuery();
        
         while (result.next()) {
          
             idEquipRetorna = result.getInt("id_equipamento");
             
         }
         
           preparedStatement.close();
           
        connection.close();
         System.out.println("retornou este equip" + idEquipRetorna);
        return idEquipRetorna;
                
        
            } catch (Exception e) {
                System.out.println();
               System.out.println("Exception in NetClientGet:- " + e);
               return idEquipRetorna;
            }
          }
          
          
        
        
        
        
          public static String trazModalidade (int id) throws Exception{
        
              //METODO PARA VERIFICAR SE USUARIO TEM ALUGUEIS PARA AVALIAR
        String retornoModalidade = "";
        //String retornoDia = "";
            try {

         String sql = "SELECT *, FROM LISTAS_EQUIPAMENTOS WHERE ID = ?";

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
       
        preparedStatement.setInt(1, id);
        ResultSet result = null;
         result = preparedStatement.executeQuery();
        
         while (result.next()) {
             retornoModalidade = result.getString("EQUIPAMENTOS");
             
         }
         
           preparedStatement.close();
           
        connection.close();
      
        return retornoModalidade;
        
            } catch (Exception e) {
                System.out.println();
               System.out.println("Exception in NetClientGet:- " + e);
               
               return retornoModalidade;
            }
          }
          
          
           public static List<Unidade> listarAluguel(String cep, String modalidade)
            throws Exception {

        //Monta a string de listagem de clientes no banco, considerando
        //apenas a coluna de ativação de clientes ("enabled")
        String sql = "SELECT * FROM alugueis where EQUIPAMENTOS = ?";

        //Lista de clientes de resultado
        List<Unidade> listaUnidade = null;

        //Conexão para abertura e fechamento
        Connection connection = null;

        PreparedStatement preparedStatement = null;

        ResultSet result = null;

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
            unid.setAvaliacao(result.getInt("avaliacao"));
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
