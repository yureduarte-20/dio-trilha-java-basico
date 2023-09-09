package apps;

import interfaces.AparelhoTelefonico;
import models.Contato;

public class Telefone implements AparelhoTelefonico {

    @Override
    public void atender() {
        System.out.println("Atendendo Telefone");
        
    }

    @Override
    public void bloquear(Contato c) {
        System.out.println("Bloqueando contato " + c.getNome());
    }

    @Override
    public void encerrarChamada() {
        System.out.println("Encerrando chamada...");
        
    }

    @Override
    public void iniciarCorreioDeVoz() {
        System.out.println("Iniciando correio de voz");
        
    }

    @Override
    public void ligar(Contato c) {
        System.out.println("Ligando para "+ c.getNome());
    }

    @Override
    public void recusarChamada() {
        System.out.println("Recusando chamada...I");
        
    }
    
}
