package src.edu.curso;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ManutencaoDAOImpl implements ManutencaoDAO{

    private final static String DB_class = "org.mariadb.jdbc.Driver";
    private final static String DB_URL = "jdbc:mariadb://localhost:3306/trabalho?allowPublicKeyRetrieval=true";
    private final static String DB_USER = "root";
    private final static String DB_PASS = "gbd*2745";

    private Connection con = null;

    public ManutencaoDAOImpl() throws TechException{
        try{
            Class.forName(DB_class);
            con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        }catch(ClassNotFoundException | SQLException e){
            throw new TechException( e );
        }
    }

    @Override
    public void inserir(Manutencao m) throws TechException {
        try{
            String sql = """
                    INSERT INTO manutencao(idMan, tipoMan, statusMan, 
                    eqMan, custoMan, dataManutencao) 
                    VALUES(?, ?, ?, ?, ?, ?)
                    """;
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1,m.getIdMan());
            stm.setString(2,m.getTipoMan());
            stm.setString(3, m.getStatusMan());
            stm.setString(4, m.getEqMan());
            stm.setFloat(5,m.getCustoMan());
            java.sql.Date dt = java.sql.Date.valueOf(m.getDataManutencao());
            stm.setDate(6, dt);
            int i = stm.executeUpdate(); 
        }catch(SQLException err){
            throw new TechException(err);
        }
    }

    @Override
    public void atualizar(Manutencao m) throws TechException {
       try {
            String sql = """
                    UPDATE manutencao SET tipoMan=?, statusMan=?, 
                    eqMan=?, custoMan=?, dataManutencao=? WHERE idMan = ?
                    """;
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1,m.getTipoMan());
            stm.setString(2, m.getStatusMan());
            stm.setString(3, m.getEqMan());
            stm.setFloat(4, m.getCustoMan());
            java.sql.Date dt = java.sql.Date.valueOf(m.getDataManutencao());
            stm.setDate(5, dt);
            stm.setInt(6,m.getIdMan());
            int i = stm.executeUpdate();
       } catch (SQLException e) {
            throw new TechException(e);
       }
    }

    @Override
    public void remover(Manutencao m) throws TechException {
        try{
            String sql = """
                    DELETE FROM manutencao WHERE idMan = ?
                    """;

            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, m.getIdMan());
            int i = stm.executeUpdate();
        }catch(SQLException e ){
            throw new TechException(e);
        }
        
    }

    @Override
    public List<Manutencao> pesquisarPorTipo(String tipoMan) throws TechException {
       List<Manutencao> lista = new ArrayList<>();
       try{
           String sql = """
                   SELECT * FROM manutencao WHERE tipoMan LIKE ?
                   """;

            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, "%" + tipoMan + "%");
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Manutencao m = new Manutencao();
                m.setIdMan(rs.getInt("idMan"));
                m.setTipoMan(rs.getString("tipoMan"));
                m.setStatusMan(rs.getString("statusMan"));
                m.setEqMan(rs.getString("eqMan"));
                m.setCustoMan(rs.getFloat("custoMan"));
                m.setDataManutencao(rs.getDate("dataManutencao").toLocalDate());

                lista.add(m);
            }
       }catch(SQLException e){
            throw new TechException(e);
       }
       return lista;
    }
    
    
}
