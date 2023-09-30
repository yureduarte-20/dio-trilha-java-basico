package comportamental.strategy;
public class Correios {
    private Despachador depachador;    
    public void setDespachador(Despachador depachador) {
        this.depachador = depachador;
    }
    public void despachar(String origem, String destino, String produto){
        this.depachador.despachar(produto, origem, destino);
    }
}
