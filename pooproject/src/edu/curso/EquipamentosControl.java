package src.edu.curso;


import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class EquipamentosControl {
    
    private ObservableList<Equipamentos> lista = FXCollections.observableArrayList();
    private int contador = 1;

    private IntegerProperty idEq =  new SimpleIntegerProperty(0);
    private StringProperty nomeEq = new SimpleStringProperty("");
    private StringProperty tipoEq = new SimpleStringProperty("");
    private StringProperty statusEq = new SimpleStringProperty("");
    private StringProperty modeloEq = new SimpleStringProperty("");
    private IntegerProperty qntEq =  new SimpleIntegerProperty(0);
    private ObjectProperty<LocalDate> dataAquisicao = new SimpleObjectProperty<>(LocalDate.now());

    private EquipamentosDAO eqDao = null;

    public EquipamentosControl() throws TechException{
        eqDao = new EquipamentosDAOImpl();
    }

    public Equipamentos paraEntidade(){
        Equipamentos eq = new Equipamentos();
        eq.setIdEq(idEq.get());
        eq.setNomeEq(nomeEq.get());
        eq.setTipoEq(tipoEq.get());
        eq.setStatusEq(statusEq.get());
        eq.setModeloEq(modeloEq.get());
        eq.setQntEq(qntEq.get());
        eq.setDataAquisicao(dataAquisicao.get());

        return eq;
    }

    public void paraTela(Equipamentos eq){
        if(eq != null){
            idEq.set(eq.getIdEq());
            nomeEq.set(eq.getNomeEq());
            tipoEq.set(eq.getTipoEq());
            statusEq.set(eq.getStatusEq());
            modeloEq.set(eq.getModeloEq());
            qntEq.set(eq.getQntEq());
            dataAquisicao.set(eq.getDataAquisicao());
        }
    }

    public void excluir(Equipamentos eq) throws TechException{
        eqDao.remover(eq);
        pesquisarTodos();
    }

    public void limparTudo(){
        idEq.set(0);
        nomeEq.set("");
        tipoEq.set("");
        statusEq.set("");
        modeloEq.set("");
        qntEq.set(0);
        dataAquisicao.set(LocalDate.now());
    }

    public void gravar() throws TechException{
        Equipamentos eq = paraEntidade();
        if(eq.getIdEq() == 0){
            this.contador += 1;
            eq.setIdEq(this.contador);
            eqDao.inserir(eq);
        }else{
            eqDao.atualizar(eq);
        }
        pesquisarTodos();
    }

    public void pesquisar() throws TechException{
        lista.clear();
        lista.addAll(eqDao.pesquisarPorNome(nomeEq.get()));
    }
    public void pesquisarTodos() throws TechException{
        lista.clear();
        lista.addAll(eqDao.pesquisarPorNome(""));
    }

    public IntegerProperty idEqProperty(){
        return this.idEq;
    }
    public StringProperty nomeEqProperty(){
        return this.nomeEq;
    }
    public StringProperty tipoEqProperty(){
        return this.tipoEq;
    }
    public StringProperty statusEqProperty(){
        return this.statusEq;
    }
    public StringProperty modeloEqProperty(){
        return this.modeloEq;
    }
    public IntegerProperty qntEqProperty(){
        return this.qntEq;
    }
    public ObjectProperty<LocalDate> dataAquisicaoProperty(){
        return this.dataAquisicao;
    }

    public ObservableList<Equipamentos> getLista(){
        return this.lista;
    }


}
