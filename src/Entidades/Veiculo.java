package Entidades;
/**
 *
 * @author thamyris
 */
public class Veiculo {

private int  Placa;
private String  Cor;
private int  Ano;
private double  Km;
public Veiculo() {
    }
public Veiculo(int  Placa,String  Cor,int  Ano,double  Km) {

this. Placa= Placa;

this. Cor= Cor;

this. Ano= Ano;

this. Km= Km;

}


 //gets e sets

public int getPlaca() {
     return  Placa;
    }

public String getCor() {
     return  Cor;
    }

public int getAno() {
     return  Ano;
    }

public double getKm() {
     return  Km;
    }

public void setPlaca(int  Placa) {
this. Placa =  Placa;
    }

public void setCor(String  Cor) {
this. Cor =  Cor;
    }

public void setAno(int  Ano) {
this. Ano =  Ano;
    }

public void setKm(double  Km) {
this. Km =  Km;
    }

@Override
    public String toString() {
 return
  Placa+ ";" +  Cor+ ";" +  Ano+ ";" +  Km;
}
}//fim da classe


