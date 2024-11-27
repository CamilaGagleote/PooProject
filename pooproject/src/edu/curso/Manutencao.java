package src.edu.curso;

// Int idMan, String tipoMan, String statusMan, String eqMan, double custoMan, Date dataManutencao  

import java.time.LocalDate;

public class Manutencao{

    private int idMan;
    private String tipoMan;
    private String statusMan;
    private String eqMan;
    private Float custoMan;
    private LocalDate dataManutencao = LocalDate.now();

    public Manutencao(){
    }

    public Manutencao(int idMan, String tipoMan, String statusMan,
     String eqMan, Float custoMan, LocalDate dataManutencao){

        this.idMan = idMan;
        this.tipoMan = tipoMan;
        this.statusMan = statusMan;
        this.eqMan = eqMan;
        this.custoMan = custoMan;
        this.dataManutencao = dataManutencao;
    }

    public int getIdMan() {
        return idMan;
    }

    public void setIdMan(int idMan) {
        this.idMan = idMan;
    }

    public String getTipoMan() {
        return tipoMan;
    }

    public void setTipoMan(String tipoMan) {
        this.tipoMan = tipoMan;
    }

    public String getStatusMan() {
        return statusMan;
    }

    public void setStatusMan(String statusMan) {
        this.statusMan = statusMan;
    }

    public String getEqMan() {
        return eqMan;
    }

    public void setEqMan(String eqMan) {
        this.eqMan = eqMan;
    }

    public Float getCustoMan() {
        return custoMan;
    }

    public void setCustoMan(Float custoMan) {
        this.custoMan = custoMan;
    }

    public LocalDate getDataManutencao() {
        return dataManutencao;
    }

    public void setDataManutencao(LocalDate dataManutencao) {
        this.dataManutencao = dataManutencao;
    }
}