package estrutural.bridge.implementacao;

import estrutural.bridge.abstracao.Formato;

public class Circulo extends Formato {

    private int x, y, radius;

    public Circulo(int x, int y, int radius, Desenhador drawAPI) {
       super(drawAPI);
       this.x = x;  
       this.y = y;  
       this.radius = radius;
    }

    public void desenhar() {
       desenhador.desenharCirculo(radius,x,y);
    }
}
