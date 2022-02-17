package GUI;
import Entidades.Veiculo;
import static com.sun.java.accessibility.util.AWTEventMonitor.addWindowListener;
import Controle.VeiculoControle;import java.awt.BorderLayout;
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
 * @author thamyris
29/09/2020 - 00:52:58 */
public class VeiculoGUI extends JDialog {

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


JLabel lbPlaca = new JLabel("Placa");
    JTextField tfPlaca = new JTextField(10);



JLabel lbCor = new JLabel("Cor");
    JTextField tfCor = new JTextField(10);



JLabel lbAno = new JLabel("Ano");
    JTextField tfAno = new JTextField(10);



JLabel lbKm = new JLabel("Km");
    JTextField tfKm = new JTextField(50);

VeiculoControle controle = new VeiculoControle();
    Veiculo veiculo = new Veiculo();
    String acao = "";
String[] colunas = new String[]{" Placa"," Cor"," Ano"," Km"};

String[][] dados = new String[0][colunas.length];

    DefaultTableModel model = new DefaultTableModel(dados, colunas);
    JTable tabela = new JTable(model);
public VeiculoGUI () {

setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        setTitle("CRUD - Veiculo");

        cp.add(pnNorte, BorderLayout.NORTH);
        cp.add(pnCentro, BorderLayout.CENTER);
        cp.add(pnSul, BorderLayout.SOUTH);

        pnNorte.setBackground(Color.cyan);
        pnCentro.setBorder(BorderFactory.createLineBorder(Color.black));

        pnNorte.setLayout(new FlowLayout(FlowLayout.LEFT));
pnNorte.add(lbPlaca);
        pnNorte.add(tfPlaca);
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
pnCentro.setLayout(new GridLayout(1, colunas.length-1));
 pnCentro.add(lbCor);
pnCentro.add(tfCor);
 pnCentro.add(lbAno);
pnCentro.add(tfAno);
 pnCentro.add(lbKm);
pnCentro.add(tfKm);
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
tfCor.setText(""); 
                    tfCor.setEditable(false); 
tfAno.setText(""); 
                    tfAno.setEditable(false); 
tfKm.setText(""); 
                    tfKm.setEditable(false); 
String caminho = "Veiculo.csv";
        //carregar dados do HD para memória RAM
        controle.carregarDados(caminho);

//listener buscar

btBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(pnSul, "avisos");
veiculo = controle.buscar(Integer.valueOf(tfPlaca.getText()));
if (veiculo != null) {//achou a veiculo na lista
                    //mostrar
                    btAdicionar.setVisible(false);
                    btAlterar.setVisible(true);
                    btCancelar.setVisible(true);
                    btExcluir.setVisible(true);
tfPlaca.setText(String.valueOf(veiculo.getPlaca()));
                    tfPlaca.setEditable(false);

tfCor.setText(veiculo.getCor());
                    tfCor.setEditable(false);

tfAno.setText(String.valueOf(veiculo.getAno()));
                    tfAno.setEditable(false);

tfKm.setText(String.valueOf(veiculo.getKm()));
                    tfKm.setEditable(false);

} else {//não achou na lista
                    //mostrar botão incluir
                    btAdicionar.setVisible(true);
                    btAlterar.setVisible(false);
                    btExcluir.setVisible(false);
tfCor.setText(""); 
                    tfCor.setEditable(false); 
tfAno.setText(""); 
                    tfAno.setEditable(false); 
tfKm.setText(""); 
                    tfKm.setEditable(false); 
}
            }
        });
//fim do listener buscar


//listener adicionar

 btAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
tfPlaca.setEnabled(false);
tfCor.requestFocus();
 tfCor.setEditable(true);
 tfAno.setEditable(true);
 tfKm.setEditable(true);
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
                    veiculo = new Veiculo();
                }
                Veiculo veiculoAntigo = veiculo;
veiculo.setPlaca(Integer.valueOf(tfPlaca.getText()));
veiculo.setCor(tfCor.getText());
veiculo.setAno(Integer.valueOf(tfAno.getText()));
veiculo.setKm(Double.valueOf(tfKm.getText()));
if (acao.equals("adicionar")) {
                    controle.adicionar(veiculo);
                } else {
                    controle.alterar(veiculo, veiculoAntigo);
                }
                btSalvar.setVisible(false);
                btCancelar.setVisible(false);
                btBuscar.setVisible(true);
                btListar.setVisible(true);
tfPlaca.setEnabled(true);
                tfPlaca.setEditable(true);
                tfPlaca.setText("");
                tfPlaca.requestFocus();
tfCor.setText(""); 
                    tfCor.setEditable(false); 
tfAno.setText(""); 
                    tfAno.setEditable(false); 
tfKm.setText(""); 
                    tfKm.setEditable(false); 
}
        });
//fim do listener salvar

//listener alterar
btAlterar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btBuscar.setVisible(false);
                btAlterar.setVisible(false);
tfPlaca.setEditable(false);

tfCor.requestFocus();
tfCor.setEditable(true);

tfAno.setEditable(true);

tfKm.setEditable(true);

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
tfPlaca.setEnabled(true);

tfPlaca.setEditable(true);

tfPlaca.requestFocus();

tfPlaca.setText("");
tfCor.setText("");
tfAno.setText("");
tfKm.setText("");
btBuscar.setVisible(true);
btAlterar.setVisible(false);
                if (response == JOptionPane.YES_OPTION) {
                    controle.excluir(veiculo);
                }
            }
        });
//fim do listener excluir


//listener listar
btListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Veiculo> listaVeiculo = controle.listar();
String[] colunas = new String[]{" Placa"," Cor"," Ano"," Km"};

String[][] dados = new String[listaVeiculo.size()][colunas.length];

String aux[];
                for (int i = 0; i < listaVeiculo.size(); i++) {
                    aux = listaVeiculo.get(i).toString().split(";");
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
tfPlaca.requestFocus();
                tfPlaca.setEnabled(true);
                tfPlaca.setEditable(true);
                tfPlaca.setText("");
tfCor.setText("");
tfCor.setEditable(false);
tfAno.setText("");
tfAno.setEditable(false);
tfKm.setText("");
tfKm.setEditable(false);

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
        setSize(700,250);
        //pack();
        setLocationRelativeTo(null);//centraliza na tela
        setVisible(true);

    }

}


//fim do construtor de GUI

//fim da classe
