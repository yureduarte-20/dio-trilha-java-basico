package interfaces;

import models.Contato;

public interface AparelhoTelefonico {
    void ligar(Contato c);
    void encerrarChamada();
    void atender();
    void recusarChamada();
    void bloquear(Contato c);
    void iniciarCorreioDeVoz();
}
