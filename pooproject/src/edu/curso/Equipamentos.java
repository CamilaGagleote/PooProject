//ID do equipamento, nome, tipo (computador, impressora, etc.), modelo, status, data de aquisição, quantidade de Equipamentos.
package src.edu.curso;

import java.time.LocalDate;

public class Equipamentos {
   
    private int idEq = 0;
    private String nomeEq = "";
    private String tipoEq = "";
    private String statusEq = "";
    private String modeloEq = "";
    private int qntEq = 0;
    private LocalDate dataAquisicao = LocalDate.now();

    public Equipamentos(){
    }

    public Equipamentos(int idEq, String nomeEq, String tipoEq,
     String statusEq, String modeloEq, int qntEq, LocalDate dataAquisicao){
        this.idEq = idEq;
        this.nomeEq = nomeEq;
        this.tipoEq = tipoEq;
        this.statusEq = statusEq;
        this.modeloEq = modeloEq;
        this.qntEq = qntEq;
        this.dataAquisicao = dataAquisicao;
     }

    public int getIdEq() {
        return idEq;
    }

    public void setIdEq(int idEq) {
        this.idEq = idEq;
    }

    public String getNomeEq() {
        return nomeEq;
    }

    public void setNomeEq(String nomeEq) {
        this.nomeEq = nomeEq;
    }

    public String getTipoEq() {
        return tipoEq;
    }

    public void setTipoEq(String tipoEq) {
        this.tipoEq = tipoEq;
    }

    public String getStatusEq() {
        return statusEq;
    }

    public void setStatusEq(String statusEq) {
        this.statusEq = statusEq;
    }

    public String getModeloEq() {
        return modeloEq;
    }

    public void setModeloEq(String modeloEq) {
        this.modeloEq = modeloEq;
    }

    public int getQntEq() {
        return qntEq;
    }

    public void setQntEq(int qntEq) {
        this.qntEq = qntEq;
    }

    public LocalDate getDataAquisicao() {
        return dataAquisicao;
    }

    public void setDataAquisicao(LocalDate dataAquisicao) {
        this.dataAquisicao = dataAquisicao;
    }
     

}
