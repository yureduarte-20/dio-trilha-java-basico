package criacional.factory;

public class Computador extends Produto{
    private String marca;
    private String processador;
    public String getMarca() {
        return marca;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }
    public String getProcessador() {
        return processador;
    }
    public void setProcessador(String processador) {
        this.processador = processador;
    }
    public Computador (){

    }
    public Computador(String marca, String processador) {
        this.marca = marca;
        this.processador = processador;
    }
    public Computador(String cod, String nome, double valor, String marca, String processador) {
        super(cod, nome, valor);
        this.marca = marca;
        this.processador = processador;
    }
    
}
