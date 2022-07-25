package negocio;

import dados.Artista;
import dados.Musica;
import dados.Playlist;
import dados.Usuario;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import javax.swing.*;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AplicativoDeMusica {
    public AplicativoDeMusica() {
    }
    private boolean usuarioConectado = false;
    private Usuario usuarioAtual;
    private List<Usuario> usuarios = new ArrayList<>();
    private List<Musica> musicas = new ArrayList<>();
    private Player player;
    private FileInputStream FIS;
    private BufferedInputStream BIS;
    private boolean podeContinuar;
    private String path;
    private int total;
    private int parada;
    private boolean tocando;

    public boolean cadastrarUsuario(String login, String pwd) {
        Usuario novo = new Usuario(login, pwd);
        if(!usuarios.contains(novo)){
            usuarios.add(novo);
            return true;
        }
        return false;
    }
    public boolean login(Usuario usuario) {
        for(int i = 0; i<usuarios.size(); i++){
            if(usuarios.get(i).equals(usuario)){
                this.usuarioAtual = usuario;
                usuarioConectado = true;
                return true;
            }
        }
        return false;
    }
    public void logout() {
        this.usuarioAtual = null;
        usuarioConectado = false;
    }
    public void adicionarAsFavoritas(Musica musica) {
        List<Musica> list = usuarioAtual.getFavoritas();
        list.add(musica);
        usuarioAtual.setFavoritas(list);
    }
    public void fazerUploadDeMusicas(String titulo, Artista artista, String path) {
        Musica musica = new Musica(path, artista, titulo);
        musicas.add(musica);
    }
    public boolean adicionarMusica(Playlist playlist, Musica musica) {
        if(usuarioConectado) {
            if(usuarioAtual.getPlaylists().size()>0) {
                for(int i = 0; i<usuarioAtual.getPlaylists().size(); i++) {
                    if(usuarioAtual.getPlaylists().get(i).getNome() == playlist.getNome()){
                        List<Playlist> todasAsPlaylists = usuarioAtual.getPlaylists();
                        Playlist pL = todasAsPlaylists.get(i);
                        List<Musica> playlistDoUsuario = pL.getPlaylist();
                        playlistDoUsuario.add(musica);
                        pL.setPlaylist(playlistDoUsuario);
                        todasAsPlaylists.remove(i);
                        todasAsPlaylists.add(pL);
                        usuarioAtual.setPlaylists(todasAsPlaylists);
                        return true;
                    }
                }
            }
            return false;
        }
        return false;
    }
    public boolean removerMusica(Playlist playlist, Musica musica) {
        for(int i = 0; i<usuarioAtual.getPlaylists().size(); i++){
            if(usuarioAtual.getPlaylists().get(i).getNome() == playlist.getNome()){
                List<Playlist> todasAsPlaylists = usuarioAtual.getPlaylists();
                Playlist pL = todasAsPlaylists.get(i);
                List<Musica> playlistDoUsuario = pL.getPlaylist();
                playlistDoUsuario.remove(musica);
                pL.setPlaylist(playlistDoUsuario);
                todasAsPlaylists.remove(i);
                todasAsPlaylists.add(pL);
                usuarioAtual.setPlaylists(todasAsPlaylists);
                return true;
            }
        }
        return false;
    }
    public boolean criarPlaylist(String nome){
        Playlist playlist = new Playlist(nome);
        List<Playlist> playlistList = this.usuarioAtual.getPlaylists();
        playlistList.add(playlist);
        this.usuarioAtual.setPlaylists(playlistList);
        return true;
    }
    public Map<Musica, Artista> listarMusicas() {
        Map<Musica, Artista> musicaArtistaMap = new HashMap<>();
        for(int i = 0; i< musicas.size(); i++){
            musicaArtistaMap.put(musicas.get(i), musicas.get(i).getArtista());
        }
        return musicaArtistaMap;
    }
    public Map<Artista, List<Musica>> listarArtistas() {
        Map<Artista, List<Musica>> artistaListMap = new HashMap<>();
        for(int j = 0; j<musicas.size(); j++) {
            List<Musica> musicasDoArtista = new ArrayList<>();
            Musica aux = musicas.get(j);
            for (int i = 0; i < musicas.size(); i++) {
                if (musicas.get(i).getArtista().equals(aux.getArtista())) {
                    musicasDoArtista.add(musicas.get(i));
                }
            }
            artistaListMap.put(musicas.get(j).getArtista(), musicasDoArtista);
        }
        return artistaListMap;
    }
    public Map<String, List<Musica>> listarPlaylists() {
        Map<String, List<Musica>> playlistListMap = new HashMap<>();
        usuarioAtual.getPlaylists().forEach(playlist -> {
            playlistListMap.put(playlist.getNome(), playlist.getPlaylist());
        }
        );
        return playlistListMap;
    }
    public void tocarPrevia(Musica musica) {
        try{
            Player mp3 = new Player(musica.getArquivo());
            System.out.println("Song_is_playing...");
            mp3.play(1000);

        }catch(JavaLayerException e){
            e.printStackTrace();
        }
    }
    public List<Musica> getMusicas() {
        return musicas;
    }
    public Usuario getUsuarioAtual() {
        return usuarioAtual;
    }
}