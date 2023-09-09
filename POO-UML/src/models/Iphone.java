package models;

import apps.Chrome;
import apps.Itunes;
import apps.Safari;
import apps.Telefone;
import interfaces.AparelhoTelefonico;
import interfaces.Browser;
import interfaces.ReprodutorMusical;

public class Iphone {
    private Browser chrome = new Chrome();
    private Browser safari = new Safari();
    private ReprodutorMusical itunes = new Itunes();
    private AparelhoTelefonico telefone = new Telefone();

    public void rotinaWeb(){
        Webpage w = new Webpage("google.com", "google");
        Webpage w2 = new Webpage("bing.com", "bing");
        this.chrome.abrirPagina(w);
        this.safari.abrirPagina(w2);

        this.chrome.abrirNovaAba();
        this.safari.abrirNovaAba();
        
        this.chrome.atualizarPagina(w);
        this.safari.atualizarPagina(w2);

        this.chrome.fecharPagina(w);
        this.safari.fecharPagina(w2);
    }
    
    public void rotinaTelefone(){
        Contato c = new Contato("99999999", "Joãzinho");
        this.telefone.atender();
        this.telefone.encerrarChamada();
        this.telefone.bloquear(c);
        this.telefone.iniciarCorreioDeVoz();
    }

    public void rotinaMusica(){
        var m = new Musica( 120, "Michel Teló", "Aí se eu te pego" );
        this.itunes.tocarMusica(m);
        this.itunes.pausar();
        this.itunes.resumir();
    }
}
