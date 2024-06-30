package entity;

public class Hotel {
    private String local;
    private String nome;
    private Integer vagas;
    private Double preco;
    private Integer estrelas;

    public Hotel() {
        super();
    }

    public Hotel(String local, String nome, Integer vagas, Double preco, Integer estrelas) {
        this.local = local;
        this.nome = nome;
        this.vagas = vagas;
        this.preco = preco;
        this.estrelas = estrelas;
    }

    public String getLocal() {
        return local;
    }

    public String getNome() {
        return nome;
    }

    public int getVagas() {
        return vagas;
    }

    public void setVagas(int vagas) {
        this.vagas = vagas;
    }

    public Double getPreco() {
        return preco;
    }

    public int getEstrelas() {
        return estrelas;
    }

}
