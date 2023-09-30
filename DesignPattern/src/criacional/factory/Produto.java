package criacional.factory;

public class Produto {
    private String cod;
    private String nome;
    private double valor;

    public Produto() {

    }

    public Produto(String cod, String nome, double valor) {
        this.cod = cod;
        this.nome = nome;
        this.valor = valor;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

}
