package src.edu.curso;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EquipamentosDAOImpl  implements EquipamentosDAO{

    private final static String DB_class = "org.mariadb.jdbc.Driver";
    private final static String DB_URL = "jdbc:mariadb://localhost:3306/trabalho?allowPublicKeyRetrieval=true";
    private final static String DB_USER = "root";
    private final static String DB_PASS = "gbd*2745";

    private Connection con = null;

    public EquipamentosDAOImpl() throws TechException{
        try {
            Class.forName(DB_class);
            con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        } catch (ClassNotFoundException | SQLException e) {
            throw new TechException(e);
        }
    }

    @Override
    public void inserir(Equipamentos eq) throws TechException {
        try{
            String SQL = """
                    INSERT INTO equipamentos (idEq, nomeEq, tipoEq, statusEq, modeloEq, qntEq, dataAquisicao)
                     VALUES (?, ?, ?, ?, ?, ?, ?)
                    """;
            PreparedStatement stm = con.prepareStatement(SQL);
            stm.setInt(1, eq.getIdEq());
            stm.setString(2, eq.getNomeEq());
            stm.setString(3, eq.getTipoEq());
            stm.setString(4, eq.getStatusEq());
            stm.setString(5, eq.getModeloEq());
            stm.setInt(6, eq.getQntEq());
            java.sql.Date dt = java.sql.Date.valueOf(eq.getDataAquisicao());
            stm.setDate(7, dt);
            int i = stm.executeUpdate();
        }catch(SQLException e){
            throw new TechException(e);
        }        
       
    }

    @Override
    public void atualizar(Equipamentos eq) throws TechException {
        try{
            String SQL = """
                    UPDATE equipamentos SET nomeEq = ?, tipoEq = ?, statusEq = ?, modeloEq = ?, qntEq = ?, dataAquisicao = ?
                    WHERE idEq = ?
                    """;
            PreparedStatement stm = con.prepareStatement(SQL);
            stm.setString(1,eq.getNomeEq());
            stm.setString(2,eq.getTipoEq());
            stm.setString(3,eq.getStatusEq());
            stm.setString(4,eq.getModeloEq());
            stm.setInt(5, eq.getQntEq());
            java.sql.Date dt = java.sql.Date.valueOf(eq.getDataAquisicao());
            stm.setDate(6, dt);
            int i = stm.executeUpdate();

        }catch(SQLException e){
            throw new TechException(e);
        }
    }

    @Override
    public void remover(Equipamentos eq) throws TechException {
        try{
            String SQL = """
                    DELETE FROM equipamentos WHERE idEq = ?
                    """;
            PreparedStatement stm = con.prepareStatement(SQL);
            stm.setInt(1, eq.getIdEq());
            int i = stm.executeUpdate();

        }catch(SQLException e){
            throw new TechException(e);

        }
     
    }

    @Override
    public List<Equipamentos> pesquisarPorNome(String nomeEq) throws TechException {
        List<Equipamentos> lista = new ArrayList<>();
        try{
            String SQL = """
                    SELECT * FROM equipamentos WHERE nomeEq LIKE ?
                    """;
            PreparedStatement stm = con.prepareStatement(SQL);
            stm.setString(1,"%" + nomeEq + "%");
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Equipamentos eq = new Equipamentos();
                eq.setIdEq(rs.getInt("idEq"));
                eq.setNomeEq(rs.getString("nomeEq"));
                eq.setTipoEq(rs.getString("tipoEq"));
                eq.setStatusEq(rs.getString("statusEq"));
                eq.setModeloEq(rs.getString("modeloEq"));
                eq.setQntEq(rs.getInt("qntEq"));
                eq.setDataAquisicao(rs.getDate("dataAquisicao").toLocalDate());

                lista.add(eq);

            }
        }catch(SQLException e){
                throw new TechException(e);
        }
        return lista;   
    }
}
