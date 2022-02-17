package Main;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import tools.ManipulaArquivo;
import tools.StringTools;

/**
 *
 * @author thamyris
 */
public class GerarClasseControle {

    GerarClasseControle(String nomeDaClasse, List<String> atributo) {

        StringTools st = new StringTools();
        List<String> cg = new ArrayList();
        String[] aux;

        cg.add("package Controle;");

        //imports
        cg.add("import Entidades." + nomeDaClasse + ";\n"
                + "import java.util.ArrayList;\n"
                + "import java.util.List;\n"
                + "import tools.ManipulaArquivo;");

        cg.add("/**\n"
                + " *\n"
                + " * @author thamyris\n" + new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss").format(new Date())
                + " */");

        cg.add("public class " + nomeDaClasse + "Controle {\n");
        cg.add("    private List<" + nomeDaClasse + "> lista = new ArrayList<>();");

        //construtor vazio
        cg.add("public " + nomeDaClasse + "Controle() {\n"
                + "    }");

        //limpar lista
        cg.add("public void limparLista() {\n"
                + "        lista.clear();\n"
                + "    }");

        //adicionar
        cg.add("public void adicionar(" + nomeDaClasse + " " + st.plMinus(nomeDaClasse) + ") {\n"
                + "        lista.add(" + st.plMinus(nomeDaClasse) + ");\n"
                + "    }");

        //listar
        cg.add("public List<" + nomeDaClasse + "> listar() {\n"
                + "        return lista;\n"
                + "    }");

        //buscar
        aux = atributo.get(0).split(";");
        cg.add("public " + nomeDaClasse + " buscar(" + aux[0] + " " + aux[1] + ") {\n"
                + "        for (int i = 0; i < lista.size(); i++) {\n");

        String s = "";
        switch (aux[0]) {
            case "int":
                s = "if (lista.get(i).get" + st.plMaiusc(aux[1]) + "() == " + aux[1] + ") {\n";
                break;
            case "short":
                s = "if (lista.get(i).get" + st.plMaiusc(aux[1]) + "() == " + aux[1] + ") {\n";
                break;
            case "double":
                s = "if (lista.get(i).get" + st.plMaiusc(aux[1]) + "() == " + aux[1] + ") {\n";
                break;
            case "String":
                s = "if (lista.get(i).get" + st.plMaiusc(aux[1]) + "().equals(" + aux[1] + ")) {\n";
                break;
            default:
                throw new AssertionError();
        }
        cg.add(s);

        cg.add("   return lista.get(i);\n"
                + "            }\n"
                + "        }\n"
                + "        return null;\n"
                + "    }");

        //alterar
        cg.add("public void alterar(" + nomeDaClasse + " " + st.plMinus(nomeDaClasse) + ", " + nomeDaClasse + " " + st.plMaiusc(aux[1]) + "Antigo) {\n"
                + "        lista.set(lista.indexOf(" + st.plMaiusc(aux[1]) + "Antigo), " + st.plMinus(nomeDaClasse) + ");\n"
                + "\n"
                + "    }");

        //excluir
        cg.add("public void excluir(" + nomeDaClasse + " " + st.plMinus(nomeDaClasse) + ") {\n"
                + "        lista.remove(" + st.plMinus(nomeDaClasse) + ");\n"
                + "    }");

        //gravar lista
        cg.add("public void gravarLista(String caminho) {\n"
                + "        ManipulaArquivo manipulaArquivo = new ManipulaArquivo();\n"
                + "        List<String> listaDeString = new ArrayList<>();\n"
                + "        for (" + nomeDaClasse + " " + st.plMinus(nomeDaClasse) + " : lista) {\n"
                + "            listaDeString.add(" + st.plMinus(nomeDaClasse) + ".toString());\n"
                + "        }\n"
                + "        manipulaArquivo.salvarArquivo(caminho, listaDeString);\n"
                + "    }");

        //carregar dados
        s = "";
        for (int i = 0; i < atributo.size(); i++) {
            aux = atributo.get(i).split(";");
            // aux[i] = aux[i].trim();

            if (aux[0].equals("String")) {
                s = s + "aux[" + i + "],";
            } else if (aux[0].equals("int")) {
                s = s + "Integer.valueOf(aux[" + i + "]),";
            } else if (aux[0].equals("double")) {
                s = s + "Double.valueOf(aux[" + i + "]),";
            }
        }
        s = s.substring(0, s.length() - 1);

        cg.add("public void carregarDados(String caminho) {\n"
                + "        ManipulaArquivo manipulaArquivo = new ManipulaArquivo();\n"
                + "        if (!manipulaArquivo.existeOArquivo(caminho)) {\n"
                + "            manipulaArquivo.criarArquivoVazio(caminho);\n"
                + "        }\n"
                + "\n"
                + "        List<String> listaDeString = manipulaArquivo.abrirArquivo(caminho);\n"
                + "        //converter de CSV para " + nomeDaClasse + "\n"
                + "        " + nomeDaClasse + " " + st.plMinus(nomeDaClasse) + ";\n"
                + "        for (String string : listaDeString) {\n"
                + "            String aux[] = string.split(\";\");\n"
                + "            " + st.plMinus(nomeDaClasse) + " = new " + nomeDaClasse + "(" + s + ");\n"
                + "            lista.add(" + st.plMinus(nomeDaClasse) + ");\n"
                + "        }\n"
                + "    }\n"
                + "\n");

        cg.add("} //fim da classe");

        ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
        manipulaArquivo.salvarArquivo("src/Controle/" + nomeDaClasse + "Controle.java", cg);
    }
}
