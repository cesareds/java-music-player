package main;

import dados.Artista;
import dados.Musica;
import dados.Playlist;
import dados.Usuario;
import negocio.AplicativoDeMusica;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class Main extends JFrame {
    private JPanel mainPanel;
    private JLabel titulo;
    private JTextField loginTextField;
    private JPasswordField passwordField1;
    private JButton signIn;
    private JTextField senhaCadastro;
    private JButton criarContaButton;
    private JLabel loginUserLabel;
    private JLabel loginPwdLabel;
    private JLabel cadastroUserLabel;
    private JLabel cadastroPwdLabel;
    private JTextField userCadastroTextField;

    private MenuPrincipal menuPrincipal = new MenuPrincipal();


    public Main(String title){
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        signIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String login = loginTextField.getText();
                String pwd = String.valueOf(passwordField1.getPassword());
                Usuario usuario = new Usuario(login, pwd);
                if(app.login(usuario)){
                    menuPrincipal.setVisible(true);
                }
            }
        });
        criarContaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String login = userCadastroTextField.getText();
                String pwd = senhaCadastro.getText();
                app.cadastrarUsuario(login, pwd);
            }
        });
    }

    public static void main(String[] args) {
        JFrame jFrame = new Main("🎧java-music-player🎧");
        jFrame.setVisible(true);

        System.out.println("Bem vindo ao reprodutor de musica");
        int opcao;
        do{
            menu();
            opcao = scannerDigit.nextInt();
            switch (opcao) {
                case 0 -> System.out.println("Encerrando o aplicativo");
                case 1 -> System.out.println(cadastrarUsuario());
                case 2 -> System.out.println(login());
                case 3 -> logout();
                case 4 -> fazerUploadDeMusica();
                case 5 -> criarPlaylist();
                case 6 -> adicionarMusica();
                case 7 -> removerMusica();
                case 8 -> adicionarAsFavoritas();
                case 9 -> listarArtistas();
                case 10 -> listarPlaylists();
                case 11 -> listarMusicas();
                case 12 -> tocarPrevia();
                default -> System.out.println("opcao inválida");
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
            if(musica.getTitulo().equals(titulo)){
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
        String musicaInserida = scannerString.next();
        final Musica[] newMusica = {new Musica()};

        app.getMusicas().forEach(musica -> {
            if(musica.getTitulo().equals(musicaInserida)){
                newMusica[0] = musica;
            }
        });
        if(!app.adicionarMusica(playlist, newMusica[0])){
            app.criarPlaylist(nome);
            app.adicionarMusica(playlist, newMusica[0]);
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
        System.out.println("""
                0-Sair
                01-Cadastrar usuario
                02-login
                03-logout
                04-fazer upload de musica
                05-criar playlist
                06-adicionar musica à playlist
                07-remover musica da playlist
                08-adicionar musica às favoritas
                09-listar artistas
                10-listar playlists
                11-listar musicas
                12-tocar previa""");
    }

}
