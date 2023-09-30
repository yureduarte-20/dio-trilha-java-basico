package estrutural.bridge.implementacao;

public class CirculoVerde implements Desenhador {
    @Override
    public void desenharCirculo(int raio, int x, int y) {
        System.out.println("Desenhando Círculo[ cor: verde, raio: " + raio + ", x: " + x + ", " + y + "]");
    }
}
