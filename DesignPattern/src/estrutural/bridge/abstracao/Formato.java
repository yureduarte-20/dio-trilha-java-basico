package estrutural.bridge.abstracao;

import estrutural.bridge.implementacao.Desenhador;

public abstract class Formato {
    protected Desenhador desenhador;

    protected Formato(Desenhador desenhador) {
        this.desenhador = desenhador;
    }

    public abstract void desenhar();
}
