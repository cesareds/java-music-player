package dados;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Usuario {

    public Usuario(String login, String pwd) {
        this.login = login;
        this.pwd = pwd;
    }
    private String login;
    private String pwd;
    private List<Musica> favoritas = new ArrayList<>();
    private List<Playlist> playlists = new ArrayList<>();

    public List<Playlist> getPlaylists() {
        return playlists;
    }
    public void setPlaylists(List<Playlist> playlists) {
        this.playlists = playlists;
    }
    public List<Musica> getFavoritas() {
        return favoritas;
    }
    public void setFavoritas(List<Musica> favoritas) {
        this.favoritas = favoritas;
    }
    public String getLogin() {
        return this.login;
    }
    public void setLogin(String value) {
        this.login = value;
    }
    public String getPwd() {
        return this.pwd;
    }
    public void setPwd(String value) {
        this.pwd = value;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                ", login='" + login + '\'' +
                ", favoritas=" + favoritas +
                ", playlists=" + playlists +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(getLogin(), usuario.getLogin());
    }
}