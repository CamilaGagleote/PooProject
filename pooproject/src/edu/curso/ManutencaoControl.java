package src.edu.curso;

import java.time.LocalDate;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ManutencaoControl {


    private ObservableList<Manutencao> lista = FXCollections.observableArrayList();
    private int contador = 1;

    private IntegerProperty idMan = new SimpleIntegerProperty(0);
    private StringProperty tipoMan = new SimpleStringProperty("");
    private StringProperty statusMan = new SimpleStringProperty("");
    private StringProperty eqMan = new SimpleStringProperty("");
    private FloatProperty custoMan = new SimpleFloatProperty(0);
    private ObjectProperty<LocalDate> dataManutencao = 
            new SimpleObjectProperty<>(LocalDate.now());
    
    private ManutencaoDAO manutencaoDAO = null;

    public ManutencaoControl() throws TechException{
        manutencaoDAO = new ManutencaoDAOImpl();
    }

    public Manutencao paraEntidade(){
        Manutencao m = new Manutencao();
        m.setIdMan(idMan.get());
        m.setTipoMan(tipoMan.get());
        m.setStatusMan(statusMan.get());
        m.setEqMan(eqMan.get());
        m.setCustoMan(custoMan.get());
        m.setDataManutencao(dataManutencao.get());

        return m;
    }

    public void paraTela(Manutencao m){
        if(m != null){
            idMan.set(m.getIdMan());
            tipoMan.set(m.getTipoMan());
            statusMan.set(m.getStatusMan());
            eqMan.set(m.getEqMan());
            custoMan.set(m.getCustoMan());
            dataManutencao.set(m.getDataManutencao());
        }
    }

    public void excluir (Manutencao m) throws TechException{
        manutencaoDAO.remover(m);
        pesquisarTodos();
    }

    public void limparTudo(){
        idMan.set(0);
        tipoMan.set("");
        statusMan.set("");
        eqMan.set("");
        custoMan.set(0);
        dataManutencao.set(LocalDate.now());
    }

    public void gravar() throws TechException{
        Manutencao m = paraEntidade();
        if(m.getIdMan() == 0){
            this.contador += 1;
            m.setIdMan(this.contador);
            manutencaoDAO.inserir(m);
        }else{
            manutencaoDAO.atualizar(m);
        }
        pesquisarTodos();
    }


    public void pesquisar() throws TechException{
        lista.clear();
        lista.addAll(manutencaoDAO.pesquisarPorTipo(tipoMan.get()));
    }

    public void pesquisarTodos() throws TechException{
        lista.clear();
        lista.addAll(manutencaoDAO.pesquisarPorTipo(""));
    }

    public IntegerProperty idManProperty(){
        return this.idMan;
    }
    public StringProperty tipoManProperty(){
        return this.tipoMan;
    }
    public StringProperty statusManProperty(){
        return this.statusMan;
    }
    public StringProperty eqManProperty(){
        return this.eqMan;
    }
    public FloatProperty custoManProperty(){
        return this.custoMan;
    }
    public ObjectProperty<LocalDate> dataManutencaProperty(){
        return this.dataManutencao;
    }
    public ObservableList<Manutencao> getLista(){
        return this.lista;
    }



    
}
