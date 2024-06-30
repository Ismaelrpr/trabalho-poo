package entity;

public class Voos {
    private String origem;
    private String Destino;
    private String data;
    private String horario;
    private Integer qtd_assentos;
    private Double preco;

    public Voos() {
        super();
    }

    public Voos(String origem, String destino, String data, String horario, Integer qtd_assentos, Double preco) {
        this.origem = origem;
        Destino = destino;
        this.data = data;
        this.horario = horario;
        this.qtd_assentos = qtd_assentos;
        this.preco = preco;
    }

    public String getOrigem() {
        return origem;
    }

    public String getDestino() {
        return Destino;
    }

    public String getData() {
        return data;
    }

    public String getHorario() {
        return horario;
    }

    public int getQtd_assentos() {
        return qtd_assentos;
    }

    public void setQtd_assentos(int qtd_assentos) {
        this.qtd_assentos = qtd_assentos;
    }

    public Double getPreco() {
        return preco;
    }

}

