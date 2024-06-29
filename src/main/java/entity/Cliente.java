package entity;

public class Cliente {
    private String nome;
    private String origem;
    private String destino;
    private int qtd_dias;
    private int estrelas;
    private Double dinheiro;

    public Cliente() {
        super();
    }
    public Cliente(String nome, String origem, String destino, int qtd_dias, int estrelas, Double dinheiro) {
        this.nome = nome;
        this.origem = origem;
        this.destino = destino;
        this.qtd_dias = qtd_dias;
        this.estrelas = estrelas;
        this.dinheiro = dinheiro;
    }

    public String getNome() {
        return nome;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public int getQtd_dias() {
        return qtd_dias;
    }

    public void setQtd_dias(int qtd_dias) {
        this.qtd_dias = qtd_dias;
    }

    public int getEstrelas() {
        return estrelas;
    }

    public void setEstrelas(int estrelas) {
        this.estrelas = estrelas;
    }

    public Double getDinheiro() {
        return dinheiro;
    }

    public void setDinheiro(Double dinheiro) {
        this.dinheiro = dinheiro;
    }


}
