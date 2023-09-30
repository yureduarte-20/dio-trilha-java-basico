package criacional.factory;

public class ComputadorFactory implements ProdutosFactory {

    @Override
    public Produto criarProduto() {
       return new Computador();
    }
    
}
