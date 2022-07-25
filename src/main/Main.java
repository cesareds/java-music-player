package main;

import dados.Artista;
import dados.Playlist;
import dados.Usuario;
import negocio.AplicativoDeMusica;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Bem vindo ao reprodutor de musica");
        int opcao = 0;
        do{
            menu();
            opcao = scannerDigit.nextInt();
            switch (opcao){
                case 0:
                    System.out.println("Encerrando o aplicativo");
                    break;
                case 1:
                    System.out.println(cadastrarUsuario());
                    break;
                case 2:
                    System.out.println(login());
                    break;
                case 3:
                    logout();
                    break;
                case 4:
                    fazerUploadDeMusica();
                    break;
                case 5:
                    criarPlaylist();
                    break;
                case 6:
                    adicionarMusica();
                    break;
                case 7:
                    removerMusica();
                    break;
                case 8:
                    adicionarAsFavoritas();
                    break;
                case 9:
                    listarArtistas();
                    break;
                case 10:
                    listarPlaylists();
                    break;
                case 11:
                    listarMusicas();
                    break;
                case 12:
                    tocarPrevia();
                    break;
                default:
                    System.out.println("opcao inválida");

            }
        }while(opcao!=0);
    }

    public static Scanner scannerString = new Scanner(System.in);
    public static Scanner scannerDigit = new Scanner(System.in);
    public static AplicativoDeMusica app = new AplicativoDeMusica();
    public static boolean cadastrarUsuario(){
        System.out.println("Login: ");
        String login = scannerString.next();
        System.out.println("Senha: ");
        String pwd = scannerString.next();
        return app.cadastrarUsuario(login, pwd);
    }
    public static boolean login(){
        System.out.println("Login: ");
        String login = scannerString.next();
        System.out.println("Senha: ");
        String senha = scannerString.next();
        return app.login(new Usuario(login, senha));
    }
    public static void logout(){
        app.logout();
    }
    public static void adicionarAsFavoritas(){
        System.out.println("Escolha sua favorita: ");
        listarMusicas();
        System.out.println("Titulo: ");
        String titulo = scannerString.next();
        app.getMusicas().forEach(musica -> {
            if(musica.getTitulo()==titulo){
                app.adicionarAsFavoritas(musica);
            }
        });
    }
    public static void fazerUploadDeMusica(){
        System.out.println("Nome do artista: ");
        String nome = scannerString.next();
        Artista artista = new Artista(nome);
        System.out.println("Caminho para arquivo: ");
        String path = scannerString.next();
        System.out.println("Titulo: ");
        String titulo = scannerString.next();
        app.fazerUploadDeMusicas(path, artista, titulo);
    }
    public static void criarPlaylist(){
        System.out.println("Nome: ");
        String nome = scannerString.next();
        app.criarPlaylist(nome);
    }
    public static void adicionarMusica(){
        listarPlaylists();
        System.out.println("Inserindo a playlist (será cirada caso não exista)");
        System.out.println("Nome: ");
        String nome = scannerString.next();
        Playlist playlist = new Playlist(nome);

        todasAsMusicas();
        System.out.println("Qual musica deseja adicionar? (index)");
        int index = scannerDigit.nextInt();
        if(!app.adicionarMusica(playlist, app.getMusicas().get(index))){
            app.criarPlaylist(nome);
            app.adicionarMusica(playlist, app.getMusicas().get(index));
        }
    }
    public static void removerMusica(){
        listarPlaylists();
        System.out.println("Insira a playlist (indice):");
        int indicePlaylist = scannerDigit.nextInt();
        todasAsMusicas();
        System.out.println("Qual musica deseja remover? (index)");
        int indiceMusica = scannerDigit.nextInt();
        app.removerMusica(app.getUsuarioAtual().getPlaylists().get(indicePlaylist), app.getMusicas().get(indiceMusica));
    }
    public static void listarArtistas(){
        System.out.println(app.listarArtistas());
    }
    public static void listarPlaylists(){
        System.out.println(app.listarPlaylists());
    }
    public static void listarMusicas(){
        System.out.println(app.listarMusicas());
    }
    public static void todasAsMusicas(){
        for(int i = 0; i<app.getMusicas().size(); i++){
            System.out.println(i+ ": " + app.getMusicas().get(i));
        }
    }
    public static void tocarPrevia(){
        todasAsMusicas();
        System.out.println("Qual musica deseja reproduzir? (indice)");
        int index = scannerDigit.nextInt();
        app.tocarPrevia(app.getMusicas().get(index));
    }
    public static void menu(){
        System.out.println("0-Sair\n" +
                "01-Cadastrar usuario\n" +
                "02-login\n" +
                "03-logout\n" +
                "04-fazer upload de musica\n" +
                "05-criar playlist\n" +
                "06-adicionar musica à playlist\n" +
                "07-remover musica da playlist\n" +
                "08-adicionar musica às favoritas\n" +
                "09-listar artistas\n" +
                "10-listar playlists\n" +
                "11-listar musicas\n" +
                "12-tocar previa");
    }
}
