package caso7;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.JOptionPane;
  
public class Main 
{
    private static final double REPRESENTACION_CROMO = Math.pow(2, 12);
    private static final int CORRIDAS = 40;
    private static final int GRUPOS = 10;
    
    public static int[][] obtenerRangos(int tamanoLista,double[]razon){
      int[][] espectro = new int[tamanoLista][2];
      int sumatoria = 0;
      for (int indice=0;indice<razon.length;indice++) {
        sumatoria += razon[indice];
        int rango = (int)Math.floor(REPRESENTACION_CROMO/razon.length*sumatoria);
        if (indice != 0) {
          espectro[indice][0] = espectro[indice-1][1] + 1;
        }
        espectro[indice][1] = rango;
      }
      return espectro;
      
    }
    
    public static double[] cambiarRazones(double[]razones,int[]indices,boolean isSuccessful) {
        for (int indice = 0;indice<indices.length;indice++) {
          razones[indices[indice]] = isSuccessful? razones[indices[indice]]+0.1:razones[indices[indice]]-0.1;
        }
      return razones;
    }
    
    public static int encontrarPar(int idCromosoma,int[][] rangos) {
      return (int)Math.floor(idCromosoma*rangos.length/REPRESENTACION_CROMO);
    }
    
    public static void main(String[] args) throws Exception{
        Decrypt decryptor = new Decrypt();
        String encriptado = "xZwM7BWIpSjYyGFr9rhpEa+cYVtACW7yQKmyN6OYSCv0ZEg9jWbc6lKzzCxRSSIvOvlimQZBMZOYnOwiA9yy3YU8zk4abFSItoW6Wj0ufQ0="; 
        String key = "29dh120_dk1_3";   
        
        
        String[] lista_abc = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","ñ","o","p","q","r","s","t","u","v","w","x","y","z"};
        String[] lista_num = {"0","1","2","3","4","5","6","7","8","9"};
        
        ArrayList<String[]> listaTotal = new ArrayList<String[]>();
        
        for (int indiceI=0;indiceI<lista_abc.length;indiceI++) {
          for (int indiceJ=0;indiceJ<lista_num.length;indiceJ++) {
            String[] par = {lista_abc[indiceI],lista_num[indiceJ]};
            listaTotal.add(par);
          }
        }
        
        //Inicializa las razones 
        double[] razon = new double[listaTotal.size()];
        for (int indice=0;indice<razon.length;indice++) {
          razon[indice]  = 1;
        }
        
        
        int[][] espectro = obtenerRangos(listaTotal.size(),razon);
        
        //Corre CORRIDAS cantidad de  veces para que descubra la respuesta
        for (int index = 0;index<CORRIDAS;index++) {
          
          // Sacar GRUPOS elementos aleatorios
          int[] indexEscogidos = new int [GRUPOS];
          for (int index1 = 0;index1<indexEscogidos.length;index1++) {
            int numero = (int) (Math.random() * REPRESENTACION_CROMO);
            indexEscogidos[index1] =  encontrarPar(numero,espectro);
          }
          
          
          int pIndice1 = 7;
          int pIndice2 = 11;
          boolean isDescartable = false;
          for (int indicePrueba = 0;indicePrueba<indexEscogidos.length;indicePrueba++) {
              try {
                String pKey1 = new StringBuilder(key).replace(pIndice1, pIndice1+1, listaTotal.get(indexEscogidos[indicePrueba])[0]).toString();
                key = new StringBuilder(pKey1).replace(pIndice2, pIndice2+1, listaTotal.get(indexEscogidos[indicePrueba])[1]).toString();
                
                System.out.println("Texto desencriptado: "+ decryptor.decrypt(key, encriptado));
                
                razon = cambiarRazones(razon,indexEscogidos,true);
                espectro = obtenerRangos(espectro.length,razon);
                isDescartable = false;
                break;
                
              } catch (IllegalBlockSizeException exception) {
                isDescartable = true;
              }
              catch (InvalidKeyException exception) { //
                isDescartable = true; 
              }
              catch (NoSuchAlgorithmException exception) { // TODO: handle exception
                isDescartable = true;
              } catch (NoSuchPaddingException exception) { // TODO: handle exception
                isDescartable = true;
              } catch (BadPaddingException exception) {
                isDescartable = true;
                
              }  
          }
          if(isDescartable) {
            System.out.println("Mensaje no se pudo desencriptar!!");
            razon = cambiarRazones(razon,indexEscogidos,false);
            espectro = obtenerRangos(espectro.length,razon);
          }
          
        }
        
        
        System.out.println("Posibles respuestas : ");
        for (int index = 0;index<listaTotal.size();index++) {
          if (razon[index] > 1.0) {
            System.out.print("{" + listaTotal.get(index)[0]+ "," + listaTotal.get(index)[1] + "}");
            System.out.println(" {" +razon[index] + "}");
          }
        }
    }
}
