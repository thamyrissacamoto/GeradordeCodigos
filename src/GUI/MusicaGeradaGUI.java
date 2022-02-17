package GUI;

import Entidades.MusicaGerada;
import static com.sun.java.accessibility.util.AWTEventMonitor.addWindowListener;
import Controle.MusicaGeradaControle;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author thamyris 29/09/2020 - 00:49:27
 */
public class MusicaGeradaGUI extends JDialog {

    Container cp;
    JPanel pnNorte = new JPanel();
    JPanel pnCentro = new JPanel();
    JPanel pnSul = new JPanel();
    JButton btBuscar = new JButton("Buscar");
    JButton btAdicionar = new JButton("Adicionar");
    JButton btSalvar = new JButton("Salvar");
    JButton btAlterar = new JButton("Alterar");
    JButton btExcluir = new JButton("Excluir");
    JButton btListar = new JButton("Listar");
    JButton btCancelar = new JButton("Cancelar");
    private JScrollPane scrollTabela = new JScrollPane();

    private JPanel pnAvisos = new JPanel(new GridLayout(1, 1));
    private JPanel pnListagem = new JPanel(new GridLayout(1, 1));
    private JPanel pnVazio = new JPanel(new GridLayout(6, 1));

    private CardLayout cardLayout;

    /////////////////////// - mutável - ///////////////////
    JLabel lbIdMusica = new JLabel("IdMusica");
    JTextField tfIdMusica = new JTextField(20);

    JLabel lbNomeMusica = new JLabel("NomeMusica");
    JTextField tfNomeMusica = new JTextField(20);

    JLabel lbDuracao = new JLabel("Duracao");
    JTextField tfDuracao = new JTextField(5);

    JLabel lbGravadora = new JLabel("Gravadora");
    JTextField tfGravadora = new JTextField(50);

    MusicaGeradaControle controle = new MusicaGeradaControle();
    MusicaGerada musicaGerada = new MusicaGerada();
    String acao = "";
    String[] colunas = new String[]{" idMusica", " nomeMusica", " duracao", " gravadora"};

    String[][] dados = new String[0][colunas.length];

    DefaultTableModel model = new DefaultTableModel(dados, colunas);
    JTable tabela = new JTable(model);

    public MusicaGeradaGUI() {

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        setTitle("CRUD - MusicaGerada");

        cp.add(pnNorte, BorderLayout.NORTH);
        cp.add(pnCentro, BorderLayout.CENTER);
        cp.add(pnSul, BorderLayout.SOUTH);

        pnNorte.setBackground(Color.cyan);
        pnCentro.setBorder(BorderFactory.createLineBorder(Color.black));

        pnNorte.setLayout(new FlowLayout(FlowLayout.LEFT));
        pnNorte.add(lbIdMusica);
        pnNorte.add(tfIdMusica);
        pnNorte.add(btBuscar);
        pnNorte.add(btAdicionar);
        pnNorte.add(btAlterar);
        pnNorte.add(btExcluir);
        pnNorte.add(btListar);
        pnNorte.add(btSalvar);
        pnNorte.add(btCancelar);

        btSalvar.setVisible(false);
        btAdicionar.setVisible(false);
        btAlterar.setVisible(false);
        btExcluir.setVisible(false);
        btCancelar.setVisible(false);
        pnCentro.setLayout(new GridLayout(1, colunas.length - 1));
        pnCentro.add(lbNomeMusica);
        pnCentro.add(tfNomeMusica);
        pnCentro.add(lbDuracao);
        pnCentro.add(tfDuracao);
        pnCentro.add(lbGravadora);
        pnCentro.add(tfGravadora);
        cardLayout = new CardLayout();
        pnSul.setLayout(cardLayout);

        for (int i = 0; i < 5; i++) {
            pnVazio.add(new JLabel(" "));
        }
        pnSul.add(pnVazio, "vazio");
        pnSul.add(pnAvisos, "avisos");
        pnSul.add(pnListagem, "listagem");
        tabela.setEnabled(false);

        pnAvisos.add(new JLabel("Avisos"));
        tfNomeMusica.setText("");
        tfNomeMusica.setEditable(false);
        tfDuracao.setText("");
        tfDuracao.setEditable(false);
        tfGravadora.setText("");
        tfGravadora.setEditable(false);
        String caminho = "MusicaGerada.csv";
        //carregar dados do HD para memória RAM
        controle.carregarDados(caminho);

//listener buscar
        btBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(pnSul, "avisos");
                musicaGerada = controle.buscar(Integer.valueOf(tfIdMusica.getText()));
                if (musicaGerada != null) {//achou a musicaGerada na lista
                    //mostrar
                    btAdicionar.setVisible(false);
                    btAlterar.setVisible(true);
                    btCancelar.setVisible(true);
                    btExcluir.setVisible(true);
                    tfIdMusica.setText(String.valueOf(musicaGerada.getIdMusica()));
                    tfIdMusica.setEditable(false);

                    tfNomeMusica.setText(musicaGerada.getNomeMusica());
                    tfNomeMusica.setEditable(false);

                    tfDuracao.setText(String.valueOf(musicaGerada.getDuracao()));
                    tfDuracao.setEditable(false);

                    tfGravadora.setText(musicaGerada.getGravadora());
                    tfGravadora.setEditable(false);

                } else {//não achou na lista
                    //mostrar botão incluir
                    btAdicionar.setVisible(true);
                    btAlterar.setVisible(false);
                    btExcluir.setVisible(false);
                    tfNomeMusica.setText("");
                    tfNomeMusica.setEditable(false);
                    tfDuracao.setText("");
                    tfDuracao.setEditable(false);
                    tfGravadora.setText("");
                    tfGravadora.setEditable(false);
                }
            }
        });
//fim do listener buscar

//listener adicionar
        btAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tfIdMusica.setEnabled(false);
                tfNomeMusica.requestFocus();
                tfNomeMusica.setEditable(true);
                tfDuracao.setEditable(true);
                tfGravadora.setEditable(true);
                btAdicionar.setVisible(false);
                btSalvar.setVisible(true);
                btCancelar.setVisible(true);
                btBuscar.setVisible(false);
                btListar.setVisible(false);
                acao = "adicionar";
            }
        });
//fim do listener adicionar

//listener salvar
        btSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (acao.equals("adicionar")) {
                    musicaGerada = new MusicaGerada();
                }
                MusicaGerada musicaGeradaAntigo = musicaGerada;
                musicaGerada.setIdMusica(Integer.valueOf(tfIdMusica.getText()));
                musicaGerada.setNomeMusica(tfNomeMusica.getText());
                musicaGerada.setDuracao(Double.valueOf(tfDuracao.getText()));
                musicaGerada.setGravadora(tfGravadora.getText());
                if (acao.equals("adicionar")) {
                    controle.adicionar(musicaGerada);
                } else {
                    controle.alterar(musicaGerada, musicaGeradaAntigo);
                }
                btSalvar.setVisible(false);
                btCancelar.setVisible(false);
                btBuscar.setVisible(true);
                btListar.setVisible(true);
                tfIdMusica.setEnabled(true);
                tfIdMusica.setEditable(true);
                tfIdMusica.setText("");
                tfIdMusica.requestFocus();
                tfNomeMusica.setText("");
                tfNomeMusica.setEditable(false);
                tfDuracao.setText("");
                tfDuracao.setEditable(false);
                tfGravadora.setText("");
                tfGravadora.setEditable(false);
            }
        });
//fim do listener salvar

//listener alterar
        btAlterar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btBuscar.setVisible(false);
                btAlterar.setVisible(false);
                tfIdMusica.setEditable(false);

                tfNomeMusica.requestFocus();
                tfNomeMusica.setEditable(true);

                tfDuracao.setEditable(true);

                tfGravadora.setEditable(true);

                btSalvar.setVisible(true);
                btCancelar.setVisible(true);
                btListar.setVisible(false);
                btExcluir.setVisible(false);
                acao = "alterar";

            }
        });
//fim do listener alterar

//listener excluir
        btExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int response = JOptionPane.showConfirmDialog(cp, "Confirme a exclusão?", "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                btExcluir.setVisible(false);
                tfIdMusica.setEnabled(true);

                tfIdMusica.setEditable(true);

                tfIdMusica.requestFocus();

                tfIdMusica.setText("");
                tfNomeMusica.setText("");
                tfDuracao.setText("");
                tfGravadora.setText("");
                btBuscar.setVisible(true);
                btAlterar.setVisible(false);
                if (response == JOptionPane.YES_OPTION) {
                    controle.excluir(musicaGerada);
                }
            }
        });
//fim do listener excluir

//listener listar
        btListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<MusicaGerada> listaMusicaGerada = controle.listar();
                String[] colunas = new String[]{" idMusica", " nomeMusica", " duracao", " gravadora"};

                String[][] dados = new String[listaMusicaGerada.size()][colunas.length];

                String aux[];
                for (int i = 0; i < listaMusicaGerada.size(); i++) {
                    aux = listaMusicaGerada.get(i).toString().split(";");
                    for (int j = 0; j < colunas.length; j++) {
                        dados[i][j] = aux[j];
                    }
                }
                cardLayout.show(pnSul, "listagem");
                scrollTabela.setPreferredSize(tabela.getPreferredSize());
                pnListagem.add(scrollTabela);
                scrollTabela.setViewportView(tabela);
                model.setDataVector(dados, colunas);

                btAlterar.setVisible(false);
                btExcluir.setVisible(false);
                btAdicionar.setVisible(false);

            }
        });
//fim do listener listar

//listener cancelar
        btCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btCancelar.setVisible(false);
                btExcluir.setVisible(false);
                tfIdMusica.requestFocus();
                tfIdMusica.setEnabled(true);
                tfIdMusica.setEditable(true);
                tfIdMusica.setText("");
                tfNomeMusica.setText("");
                tfNomeMusica.setEditable(false);
                tfDuracao.setText("");
                tfDuracao.setEditable(false);
                tfGravadora.setText("");
                tfGravadora.setEditable(false);

                btBuscar.setVisible(true);
                btListar.setVisible(true);
                btSalvar.setVisible(false);
                btCancelar.setVisible(false);

            }
        });
//fim do listener cancelar

//listener ao fechar o programa
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                //antes de sair, salvar a lista em disco
                controle.gravarLista(caminho);
                // Sai da classe
                dispose();
            }
        });

        setModal(true);
        setSize(700, 250);
        //pack();
        setLocationRelativeTo(null);//centraliza na tela
        setVisible(true);

    }

}

//fim do construtor de GUI

//fim da classe
