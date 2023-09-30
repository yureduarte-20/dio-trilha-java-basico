import comportamental.strategy.Correios;
import comportamental.strategy.vias.AereaStrategy;
import comportamental.strategy.vias.FluvialStrategy;
import comportamental.strategy.vias.RodoviariaStrategy;
import criacional.factory.CarrosFactory;
import criacional.factory.ComputadorFactory;
import criacional.factory.Produto;
import criacional.factory.ProdutosFactory;
import estrutural.bridge.abstracao.Formato;
import estrutural.bridge.implementacao.Circulo;
import estrutural.bridge.implementacao.CirculoVerde;
import estrutural.bridge.implementacao.CirculoVermelho;

public class App {
    public static void main(String[] args) throws Exception {
        // Strategy
        Correios correios = new Correios();
        correios.setDespachador(new AereaStrategy());
        correios.despachar("São Paulo - SP", "Cuiabá - MT", "Ferro fundido");
        correios.setDespachador(new RodoviariaStrategy());
        correios.despachar("Cuiabá - MT", "Santarém - PA", "Ferro fundido");
        correios.setDespachador(new FluvialStrategy());
        correios.despachar("Santarém - PA", "Belém - PA", "Ferro fundido");
        System.out.println();
        // Bridge
        Formato redCircle = new Circulo(100, 100, 10, new CirculoVermelho());
        Formato greenCircle = new Circulo(100, 100, 10, new CirculoVerde());

        redCircle.desenhar();
        greenCircle.desenhar();
        System.out.println();
        // Factory
        ProdutosFactory fabricaCarros = new CarrosFactory();
        ProdutosFactory  fabricaComputadores = new ComputadorFactory();
        
        Produto carro = fabricaCarros.criarProduto();
        Produto computador = fabricaComputadores.criarProduto();

    }
}
