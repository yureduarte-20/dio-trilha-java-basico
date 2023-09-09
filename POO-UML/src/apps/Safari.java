package apps;

import interfaces.Browser;
import models.Webpage;

public class Safari implements Browser{
    
    @Override
    public void abrirNovaAba() {
        System.out.println("Abrindo nova aba Web no safari");
    }

    @Override
    public void abrirPagina(Webpage w) {
       System.out.println("Abrindo a página " + w.getNome() + " no safari");
    }

    @Override
    public void atualizarPagina(Webpage w) {
        System.out.println("Atualizando a página " + w.getNome()+ " no safari");
    }

    @Override
    public void fecharPagina(Webpage w) {
        System.out.println("Fechando a página " + w.getNome() + " no safari");
    }
}
