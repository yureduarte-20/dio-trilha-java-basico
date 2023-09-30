package estrutural.bridge.implementacao;

public class CirculoVermelho implements Desenhador {
    @Override
    public void desenharCirculo(int raio, int x, int y) {
        System.out.println("Desenhando Círculo[ cor: vermelho, raio: " + raio + ", x: " + x + ", " + y + "]");
    }
}
