package src.edu.curso;


import java.util.List;

public interface EquipamentosDAO {
    void inserir (Equipamentos eq) throws TechException;
    void atualizar( Equipamentos eq) throws TechException;
    void remover(Equipamentos eq) throws TechException;
    List<Equipamentos> pesquisarPorNome(String nomeEq) throws TechException;
}
