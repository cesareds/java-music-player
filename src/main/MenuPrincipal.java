package main;

import dados.Artista;
import dados.Musica;
import dados.Playlist;
import org.apache.commons.lang3.ObjectUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static main.Main.app;

public class MenuPrincipal extends JFrame{
    private JButton logoutButton;
    private JPanel panel1;
    private JButton adicionarAsFavoritasButton;
    private JButton fazerUploadButton;
    private JButton criarPlaylistButton;
    private JButton addMscPlButton;
    private JButton rmMscPlButton;
    private JButton listarArtistasButton;
    private JButton listarPlaylistsButton;
    private JButton listarMusicasButton;
    private JButton tocarPreviaButton;
    private JTextField fazerUploadTituloTextField;
    private JTextField fazerUploadArtistaTextField;
    private JTextField fazerUploadPathTextField;
    private JLabel fzrUpldArtistaLabel;
    private JLabel fzrUpldPathLabel;
    private JLabel fzrUpldTituloLabel;
    private JList addAsFavoritasJList;
    private JTextField textField1;
    private JLabel addAsFavoritasLabel;
    private JTextField criarPlaylistTextField;
    private JLabel criarPlaylistLabel;
    private JTextField addMscAPlPlaylistTextField;
    private JLabel addMscAPlPlaylistLabel;
    private JTextField addMscAPlMusicaTextField;
    private JLabel addMscAPlMusicaLabel;
    private JTextField textField2;
    private JTextField rmMscMusicaTextField;
    private JLabel rmMscMusicaLabel;
    private JTextField rmMscPlaylistTextField;
    private JLabel rmMscPlaylistLabel;
    private JList addMscAPlPlaylistJList;
    private JList addMscAPlMusicaJList;

    public MenuPrincipal(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(panel1);
        if(app.getUsuarioAtual()!=null) {
            addMscAPlPlaylistJList = new JList(app.getUsuarioAtual().getPlaylists().toArray());
        }
        if(app.getMusicas()!=null){
            addMscAPlMusicaJList = new JList(app.getMusicas().toArray());
            addAsFavoritasJList = new JList(app.getMusicas().toArray());
        }
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.logout();
                panel1.setVisible(false);
            }
        });
        fazerUploadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Artista artista = new Artista(fazerUploadArtistaTextField.getText());
                app.fazerUploadDeMusicas(fazerUploadPathTextField.getText(), artista, fazerUploadTituloTextField.getText());
                panel1.repaint();
            }
        });
        adicionarAsFavoritasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, app.getMusicas().toString());
                app.getMusicas().forEach(musica -> {
                    if(musica.getTitulo()==textField1.getText()){
                        app.adicionarAsFavoritas(musica);
                    }
                });
            }
        });

        criarPlaylistButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.criarPlaylist(criarPlaylistTextField.getText());
            }
        });
        addMscPlButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Playlist playlist = new Playlist(addMscAPlPlaylistTextField.getText());
                Musica newMusica = new Musica();
                for(int i = 0; i<app.getMusicas().size(); i++){
                    if(app.getMusicas().get(i).getTitulo()==addMscAPlMusicaTextField.getText()){
                        newMusica = app.getMusicas().get(i);
                    }
                }
                if(!app.adicionarMusica(playlist, newMusica)){
                    app.criarPlaylist(addMscAPlPlaylistTextField.getText());
                    app.adicionarMusica(playlist, newMusica);
                }
            }
        });
        listarArtistasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, app.listarArtistas().toString());
            }
        });
        listarPlaylistsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, app.listarPlaylists().toString());
            }
        });

        listarMusicasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, app.listarMusicas().toString());
            }
        });
        tocarPreviaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Escolha indice:\n" + app.listarMusicas().toString());
                Musica musica = null;
                for(int i = 0; i<app.getMusicas().size(); i++){
                    JOptionPane.showMessageDialog(null, "roda: " + textField2.getText());
                    JOptionPane.showMessageDialog(null, "sera que " + app.getMusicas().get(i).getTitulo() + " eh igual a " + textField2.getText());
                    if(app.getMusicas().get(i).getTitulo().equals(textField2.getText())){
                        JOptionPane.showMessageDialog(null, "titulo igual");
                        musica = app.getMusicas().get(i);
                    }
                }
                if(musica!=null){
                    app.tocarPrevia(musica);
                }else {
                    JOptionPane.showMessageDialog(null, "Nenhuma musica encontrada");
                }
            }
        });
        rmMscPlButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Playlist playlist = null;
                Musica musica = null;
                for(int i = 0; i<app.getUsuarioAtual().getPlaylists().size(); i++){
                    if(app.getUsuarioAtual().getPlaylists().get(i).getNome().equals(rmMscPlaylistTextField.getText())){
                        playlist = app.getUsuarioAtual().getPlaylists().get(i);
                    }
                }
                for(int i = 0; i<app.getMusicas().size(); i++){
                    if(app.getMusicas().get(i).getTitulo().equals(rmMscMusicaTextField.getText())){
                        musica = app.getMusicas().get(i);
                    }
                }
                if(musica!=null&&playlist!=null){
                    app.removerMusica(playlist, musica);
                }else{
                    JOptionPane.showMessageDialog(null, "Não encontrado");
                }
            }
        });
    }
}
