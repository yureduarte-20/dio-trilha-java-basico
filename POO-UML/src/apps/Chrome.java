package apps;

import interfaces.Browser;
import models.Webpage;

public class Chrome implements Browser{

    @Override
    public void abrirNovaAba() {
        System.out.println("Abrindo nova aba Web no chrome");
    }

    @Override
    public void abrirPagina(Webpage w) {
       System.out.println("Abrindo a página " + w.getNome() + " no chrome");
    }

    @Override
    public void atualizarPagina(Webpage w) {
        System.out.println("Atualizando a página " + w.getNome()+ " no chrome");
    }

    @Override
    public void fecharPagina(Webpage w) {
        System.out.println("Fechando a página " + w.getNome() + " no chrome");
    }
    
}
    

