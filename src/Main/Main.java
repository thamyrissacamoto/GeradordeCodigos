package Main;

import java.util.ArrayList;
import java.util.List;
import tools.ManipulaArquivo;
import tools.StringTools;

/**
 *
 * @author thamyris
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        String nomeDaClasse = "Veiculo";

        List<String> atributo = new ArrayList();

        atributo.add("int; Placa;10");
        atributo.add("String; Cor;10");
        atributo.add("int; Ano;10");
        atributo.add("double; Km;50");

        GerarClasseEntidade gerarClasseEntidade = new GerarClasseEntidade(nomeDaClasse, atributo);
        GerarClasseControle gerarClasseControle = new GerarClasseControle(nomeDaClasse, atributo);
        GerarClasseGUI gerarClasseGUI = new GerarClasseGUI(nomeDaClasse, atributo);
    }
}
