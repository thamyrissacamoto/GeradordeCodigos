package Controle;
import Entidades.MusicaGerada;
import java.util.ArrayList;
import java.util.List;
import tools.ManipulaArquivo;
/**
 *
 * @author thamyris
29/09/2020 - 00:49:26 */
public class MusicaGeradaControle {

    private List<MusicaGerada> lista = new ArrayList<>();
public MusicaGeradaControle() {
    }
public void limparLista() {
        lista.clear();
    }
public void adicionar(MusicaGerada musicaGerada) {
        lista.add(musicaGerada);
    }
public List<MusicaGerada> listar() {
        return lista;
    }
public MusicaGerada buscar(int  idMusica) {
        for (int i = 0; i < lista.size(); i++) {

if (lista.get(i).getIdMusica() ==  idMusica) {

   return lista.get(i);
            }
        }
        return null;
    }
public void alterar(MusicaGerada musicaGerada, MusicaGerada IdMusicaAntigo) {
        lista.set(lista.indexOf(IdMusicaAntigo), musicaGerada);

    }
public void excluir(MusicaGerada musicaGerada) {
        lista.remove(musicaGerada);
    }
public void gravarLista(String caminho) {
        ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
        List<String> listaDeString = new ArrayList<>();
        for (MusicaGerada musicaGerada : lista) {
            listaDeString.add(musicaGerada.toString());
        }
        manipulaArquivo.salvarArquivo(caminho, listaDeString);
    }
public void carregarDados(String caminho) {
        ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
        if (!manipulaArquivo.existeOArquivo(caminho)) {
            manipulaArquivo.criarArquivoVazio(caminho);
        }

        List<String> listaDeString = manipulaArquivo.abrirArquivo(caminho);
        //converter de CSV para MusicaGerada
        MusicaGerada musicaGerada;
        for (String string : listaDeString) {
            String aux[] = string.split(";");
            musicaGerada = new MusicaGerada(Integer.valueOf(aux[0]),aux[1],Double.valueOf(aux[2]),aux[3]);
            lista.add(musicaGerada);
        }
    }


} //fim da classe
