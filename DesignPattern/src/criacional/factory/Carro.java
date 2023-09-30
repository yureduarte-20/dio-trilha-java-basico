package criacional.factory;

public class Carro extends Produto {
    private String placa;
    private String modelo;
    private String marca;

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Carro(String cod, String nome, double valor, String placa, String modelo, String marca) {
        super(cod, nome, valor);
        this.placa = placa;
        this.modelo = modelo;
        this.marca = marca;
    }

    public Carro() {

    }

    public Carro(String cod, String nome, double valor) {
        super(cod, nome, valor);
        // TODO Auto-generated constructor stub
    }

}
