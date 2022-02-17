package Entidades;
/**
 *
 * @author thamyris
 */
public class MusicaGerada {

private int  idMusica;
private String  nomeMusica;
private double  duracao;
private String  gravadora;
public MusicaGerada() {
    }
public MusicaGerada(int  idMusica,String  nomeMusica,double  duracao,String  gravadora) {

this. idMusica= idMusica;

this. nomeMusica= nomeMusica;

this. duracao= duracao;

this. gravadora= gravadora;

}


 //gets e sets

public int getIdMusica() {
     return  idMusica;
    }

public String getNomeMusica() {
     return  nomeMusica;
    }

public double getDuracao() {
     return  duracao;
    }

public String getGravadora() {
     return  gravadora;
    }

public void setIdMusica(int  idMusica) {
this. idMusica =  idMusica;
    }

public void setNomeMusica(String  nomeMusica) {
this. nomeMusica =  nomeMusica;
    }

public void setDuracao(double  duracao) {
this. duracao =  duracao;
    }

public void setGravadora(String  gravadora) {
this. gravadora =  gravadora;
    }

@Override
    public String toString() {
 return
  idMusica+ ";" +  nomeMusica+ ";" +  duracao+ ";" +  gravadora;
}
}//fim da classe


