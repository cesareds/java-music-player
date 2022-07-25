package dados;

public class Artista {
    public Artista() {
    }
    public Artista(String nome) {
        this.nome = nome;
    }

    private String nome;


    public String getNome() {
        return this.nome;
    }
    public void setNome(String value) {
        this.nome = value;
    }

    @Override
    public String toString() {
        return "Artista{" +
                "nome='" + nome + '\'' +
                '}';
    }
}