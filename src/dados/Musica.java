package dados;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Musica {
    public Musica(String path, Artista artista, String titulo) {
        try {
            this.arquivo = new FileInputStream(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        this.artista = artista;
        this.titulo = titulo;
    }

    private String path;
    private FileInputStream arquivo;
    private Artista artista;
    private String titulo;

    public Musica() {

    }

    public Artista getArtista() {
        return artista;
    }
    public void setArtista(Artista artista) {
        this.artista = artista;
    }
    public FileInputStream getArquivo() {
        return arquivo;
    }
    public void setArquivo(FileInputStream arquivo) {
        this.arquivo = arquivo;
    }
    public String getTitulo() {
        return this.titulo;
    }
    public void setTitulo(String value) {
        this.titulo = value;
    }
    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "Musica{" +
                ", titulo='" + titulo + '\'' +
                ", artista='" + artista.getNome() + '\'' +
                '}';
    }


}