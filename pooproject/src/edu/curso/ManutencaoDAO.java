package src.edu.curso;

import java.util.List;

public interface ManutencaoDAO {

    void inserir( Manutencao m) throws TechException;
    void atualizar(Manutencao m) throws TechException;
    void remover(Manutencao m) throws TechException;
    List<Manutencao> pesquisarPorTipo(String tipoMan) throws TechException;
    

    
}
