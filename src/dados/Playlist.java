package dados;

import java.util.ArrayList;
import java.util.List;

public class Playlist {
    public Playlist(String nome) {
        this.nome = nome;
    }

    private String nome;
    private Usuario usuario;
    private List<Musica> playlist = new ArrayList<>();

    public Usuario getUsuario(){
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public List<Musica> getPlaylist() {
        return playlist;
    }
    public void setPlaylist(List<Musica> playlist) {
        this.playlist = playlist;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Playlist{" +
                "nome='" + nome + '\'' +
                ", playlist=" + playlist +
                '}';
    }
}