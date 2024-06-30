package entity;

public class Hotel {
    private String local;
    private String nome;
    private Integer vagas;
    private Double preço;
    private Integer estrelas;

    public Hotel() {
        super();
    }

    public Hotel(String local, String nome, Integer vagas, Double preço, Integer estrelas) {
        this.local = local;
        this.nome = nome;
        this.vagas = vagas;
        this.preço = preço;
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

    public Double getPreço() {
        return preço;
    }

    public int getEstrelas() {
        return estrelas;
    }

}
