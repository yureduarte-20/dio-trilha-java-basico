package apps;

import interfaces.ReprodutorMusical;
import models.Musica;

public class Itunes implements ReprodutorMusical {

    @Override
    public void pausar() {
        System.out.println("Pausando...");
    }

    @Override
    public void resumir() {
        System.out.println("Retomando a música.");
        
    }

    @Override
    public void tocarMusica(Musica m) {
        System.out.println("Tocando a música " + m.getNome());
    }
    
}
