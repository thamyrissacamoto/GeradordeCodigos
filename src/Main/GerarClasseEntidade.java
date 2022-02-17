package Main;

import java.util.ArrayList;
import java.util.List;
import tools.ManipulaArquivo;
import tools.StringTools;

/**
 *
 * @author thamyris
 */
public class GerarClasseEntidade {

    GerarClasseEntidade(String nomeDaClasse, List<String> atributo) {
        StringTools st = new StringTools();
        List<String> cg = new ArrayList();

        cg.add("package Entidades;");
        cg.add("/**\n"
                + " *\n"
                + " * @author thamyris\n"
                + " */");

        cg.add("public class " + nomeDaClasse + " {\n");

        //atributos
        String[] aux;
        for (int i = 0; i < atributo.size(); i++) {
            aux = atributo.get(i).split(";");
            cg.add("private " + aux[0] + " " + aux[1] + ";");
        }

        //construtor vazio
        cg.add("public " + nomeDaClasse + "() {\n"
                + "    }");

        //this
        String s = "";
        for (int i = 0; i < atributo.size(); i++) {
            aux = atributo.get(i).split(";");
            s = s + aux[0] + " " + aux[1] + ",";
        }

        s = s.substring(0, s.length() - 1); //tirar a ultima virgula
        cg.add("public " + nomeDaClasse + "(" + s + ") {\n");

        for (int i = 0; i < atributo.size(); i++) {
            aux = atributo.get(i).split(";");
            cg.add("this." + aux[1] + "=" + aux[1] + ";\n");
        }
        cg.add("}");

        //gets
        cg.add("\n\n //gets e sets\n");
        for (int i = 0; i < atributo.size(); i++) {
            aux = atributo.get(i).split(";");
            cg.add("public " + aux[0] + " get" + st.plMaiusc(aux[1]) + "() {\n"
                    + "     return " + aux[1] + ";\n"
                    + "    }\n");
        }

        //sets
        for (int i = 0; i < atributo.size(); i++) {
            aux = atributo.get(i).split(";");
            cg.add("public void set" + st.plMaiusc(aux[1])
                    + "(" + aux[0] + " " + aux[1] + ") {\n"
                    + "this." + aux[1] + " = " + aux[1] + ";\n"
                    + "    }\n");
        }

        //to string
        cg.add("@Override\n"
                + "    public String toString() {\n return");
        s = "";
        for (int i = 0; i < atributo.size(); i++) {
            aux = atributo.get(i).split(";");
            s = s + " " + aux[1] + "+ \";\" +";
        }
        s = s.substring(0, s.length() - 7); //tirar as ultimas 7 letras
        cg.add(s + ";");
        cg.add("}");

        cg.add("}//fim da classe\n\n");

        for (String linha : cg) {
            System.out.println(linha);
        }

        ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
        manipulaArquivo.salvarArquivo("src/Entidades/" + nomeDaClasse + ".java", cg);
    }
}
