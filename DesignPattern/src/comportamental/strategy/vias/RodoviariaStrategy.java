package comportamental.strategy.vias;

import comportamental.strategy.Despachador;

public class RodoviariaStrategy implements Despachador{
    @Override
    public void despachar(String produto, String origem, String destino) {
        System.out.println("O produto " + produto + " foi despacho de " + origem + " para " + destino + " de caminhão.");
        
    }
}
