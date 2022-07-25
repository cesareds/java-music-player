package main;

import dados.Artista;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private JList addAsFavoritasJList = new JList(Main.app.getMusicas().toArray());

    public MenuPrincipal(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(panel1);
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
                Main.app.fazerUploadDeMusicas(fazerUploadPathTextField.getText(), artista, fazerUploadTituloTextField.getText());
            }
        });
        adicionarAsFavoritasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
