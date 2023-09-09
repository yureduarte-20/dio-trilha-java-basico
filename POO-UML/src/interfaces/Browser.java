package interfaces;

import models.Webpage;

public interface Browser {
    void abrirPagina(Webpage w);
    void abrirNovaAba();
    void atualizarPagina(Webpage w);
    void fecharPagina(Webpage w);
}
