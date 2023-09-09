package models;

public class Webpage {
    private String url;
    private String nome;
    
    public Webpage(String url, String nome) {
        this.url = url;
        this.nome = nome;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    
}
