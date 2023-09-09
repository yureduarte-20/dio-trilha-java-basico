package models;
public class Musica {
    private int duracao;
    private String compositor;
    private String nome;
    
    public Musica(int duracao, String compositor, String nome) {
        this.duracao = duracao;
        this.compositor = compositor;
        this.nome = nome;
    }
    public int getDuracao() {
        return duracao;
    }
    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }
    public String getCompositor() {
        return compositor;
    }
    public void setCompositor(String compositor) {
        this.compositor = compositor;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
}