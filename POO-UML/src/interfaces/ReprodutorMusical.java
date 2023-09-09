package interfaces;
import models.Musica;
public interface ReprodutorMusical {
    void tocarMusica(Musica m);
    void pausar();
    void resumir();
}