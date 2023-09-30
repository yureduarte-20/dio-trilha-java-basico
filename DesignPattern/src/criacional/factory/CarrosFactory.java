package criacional.factory;

public class CarrosFactory implements ProdutosFactory {
    @Override
    public Produto criarProduto() {
        return new Carro();
    }
}
