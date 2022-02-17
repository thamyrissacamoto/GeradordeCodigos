package Controle;
import Entidades.Veiculo;
import java.util.ArrayList;
import java.util.List;
import tools.ManipulaArquivo;
/**
 *
 * @author thamyris
29/09/2020 - 00:52:58 */
public class VeiculoControle {

    private List<Veiculo> lista = new ArrayList<>();
public VeiculoControle() {
    }
public void limparLista() {
        lista.clear();
    }
public void adicionar(Veiculo veiculo) {
        lista.add(veiculo);
    }
public List<Veiculo> listar() {
        return lista;
    }
public Veiculo buscar(int  Placa) {
        for (int i = 0; i < lista.size(); i++) {

if (lista.get(i).getPlaca() ==  Placa) {

   return lista.get(i);
            }
        }
        return null;
    }
public void alterar(Veiculo veiculo, Veiculo PlacaAntigo) {
        lista.set(lista.indexOf(PlacaAntigo), veiculo);

    }
public void excluir(Veiculo veiculo) {
        lista.remove(veiculo);
    }
public void gravarLista(String caminho) {
        ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
        List<String> listaDeString = new ArrayList<>();
        for (Veiculo veiculo : lista) {
            listaDeString.add(veiculo.toString());
        }
        manipulaArquivo.salvarArquivo(caminho, listaDeString);
    }
public void carregarDados(String caminho) {
        ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
        if (!manipulaArquivo.existeOArquivo(caminho)) {
            manipulaArquivo.criarArquivoVazio(caminho);
        }

        List<String> listaDeString = manipulaArquivo.abrirArquivo(caminho);
        //converter de CSV para Veiculo
        Veiculo veiculo;
        for (String string : listaDeString) {
            String aux[] = string.split(";");
            veiculo = new Veiculo(Integer.valueOf(aux[0]),aux[1],Integer.valueOf(aux[2]),Double.valueOf(aux[3]));
            lista.add(veiculo);
        }
    }


} //fim da classe
