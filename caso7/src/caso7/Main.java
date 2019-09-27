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
    
    public static int[][] obtenerRangos(int tamanoLista,double[]razon){
      int[][] espectro = new int[tamanoLista][2];
      int sumatoria = 0;
      for (int indice=0;indice<razon.length;indice++) {
        //razon[indice] = 1;
        sumatoria += razon[indice];
        int rango = (int)Math.floor(REPRESENTACION_CROMO/razon.length*sumatoria);
        if (indice != 0) {
          espectro[indice][0] = espectro[indice-1][1] + 1;
        }
        espectro[indice][1] = rango;
        //System.out.println("" + indice + " " + "Rango: [" + espectro[indice][0] + "," + espectro[indice][1] + "]");
      }
      return espectro;
      
    }
    public static double[] cambiarRazones(double[]razones,int[]indices,boolean isSuccessful) {
        //System.out.println("Cambio");
        //System.out.println(isSuccessful);
        for (int indice = 0;indice<indices.length;indice++) {
          //System.out.print("Antes = " + indices[indice]);
          //System.out.println(" = " + razones[indices[indice]]);
          razones[indices[indice]] = isSuccessful? razones[indices[indice]]+0.1:razones[indices[indice]]-0.1;
          //System.out.print("Despues = " +indices[indice]);
          //System.out.println(" = " + razones[indices[indice]]);
        }
      return razones;
    }
    
    public static int encontrarPar(int idCromosoma,int[][] rangos) {
      return (int)Math.floor(idCromosoma*rangos.length/REPRESENTACION_CROMO);
    }
    
    public static void sort(ArrayList<String[]> arrAOrdenar,double arrayPrio[]) 
    { 
        int n = arrayPrio.length; 
  
        // One by one move boundary of unsorted subarray 
        for (int i = 0; i < n-1; i++) 
        { 
            // Find the minimum element in unsorted array 
            int min_idx = i; 
            for (int j = i+1; j < n; j++) 
                if (arrayPrio[j] < arrayPrio[min_idx]) 
                    min_idx = j; 
  
            // Swap the found minimum element with the first 
            // element 
            double temp = arrayPrio[min_idx]; 
            arrayPrio[min_idx] = arrayPrio[i]; 
            arrayPrio[i] = temp; 
            
            
            String[] temp2 = arrAOrdenar.get(min_idx); 
            arrAOrdenar.set(min_idx,arrAOrdenar.get(i));  
            arrAOrdenar.set(min_idx, temp2); 
        } 
    }
    public static void main(String[] args) throws Exception
    {
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
        
          
        double[] razon = new double[listaTotal.size()];
        for (int indice=0;indice<razon.length;indice++) {
          razon[indice]  = 1;
        }
        
        int[][] espectro = obtenerRangos(listaTotal.size(),razon);
        
        
        //Corre 40 veces para que descubra la respuesta
        for (int index = 0;index<40;index++) {
          
          // Sacar 20 elementos aleatoriamente
          int[] indexEscogidos = new int [10];
          for (int index1 = 0;index1<indexEscogidos.length;index1++) {
            int numero = (int) (Math.random() * REPRESENTACION_CROMO);
            
            indexEscogidos[index1] =  encontrarPar(numero,espectro);
            //System.out.println(numero);
            //System.out.println(indexEscogidos[index1]);
          }
          int pIndice1 = 7;
          int pIndice2 = 11;
          boolean isDescartable = false;
          for (int indicePrueba = 0;indicePrueba<indexEscogidos.length;indicePrueba++) {
              try {
                String pKey1 = new StringBuilder(key).replace(pIndice1, pIndice1+1, listaTotal.get(indexEscogidos[indicePrueba])[0]).toString();
                key = new StringBuilder(pKey1).replace(pIndice2, pIndice2+1, listaTotal.get(indexEscogidos[indicePrueba])[1]).toString();
              
                //System.out.println(key);
                
                System.out.println("Texto desencriptado: "+ decryptor.decrypt(key, encriptado));
                
                //System.out.println("Llegoooooo");
                razon = cambiarRazones(razon,indexEscogidos,true);
                espectro = obtenerRangos(espectro.length,razon);
                isDescartable = false;
                break;
                
              } catch (IllegalBlockSizeException exception) {
                //System.out.println(exception.getMessage()); 
                isDescartable = true;
              }
              catch (InvalidKeyException exception) { //
                //System.out.println(exception.getMessage());
                isDescartable = true; 
              }
              catch (NoSuchAlgorithmException exception) { // TODO: handle exception
                //System.out.println(exception.getMessage()); 
                isDescartable = true;
              } catch (NoSuchPaddingException exception) { // TODO: handle exception
                //System.out.println(exception.getMessage()); 
                isDescartable = true;
              } catch (BadPaddingException exception) {
                //System.out.println(exception.getMessage()); 
                isDescartable = true;
                
              }  
          }
          if(isDescartable) {
            System.out.println("Mensaje no se pudo desencriptar!!");
            razon = cambiarRazones(razon,indexEscogidos,false);
            espectro = obtenerRangos(espectro.length,razon);
          }
          
        }
        
        //sort(listaTotal,razon);
        
        
        for (int index = 0;index<listaTotal.size();index++) {
          if (razon[index] > 1.0) {
            System.out.print("{" + listaTotal.get(index)[0]+ "," + listaTotal.get(index)[1] + "}");
            System.out.println(" {" +razon[index] + "}");
          }
        }
        
        /**
        
        int largo_lista_abc = lista_abc.length;
        int largo_lista_num = lista_num.length;
        
        int pIndice1 = 7;
        int pIndice2 = 11;
        
        //JOptionPane.showInputDialog(null, "Programa de Analisis, En que estas pensando?");
        
        for(int cont = 0; cont < largo_lista_abc; cont++)
        {
            
            for(int cont_num = 0; cont_num < largo_lista_num; cont_num++)
            {
                try {
                  String pKey1 = new StringBuilder(key).replace(pIndice1, pIndice1+1, lista_abc[cont]).toString();
                  key = new StringBuilder(pKey1).replace(pIndice2, pIndice2+1, lista_num[cont_num]).toString();
                
                  System.out.println(key);
                  
                  System.out.println("Texto desencriptado: "+ decryptor.decrypt(key, encriptado));
                
                  
                } catch (IllegalBlockSizeException exception) {
                  System.out.println(exception.getMessage()); 
                }
                catch (InvalidKeyException exception) { //
                  System.out.println(exception.getMessage()); 
                }
                catch (NoSuchAlgorithmException exception) { // TODO: handle exception
                  System.out.println(exception.getMessage()); 
                } catch (NoSuchPaddingException exception) { // TODO: handle exception
                  System.out.println(exception.getMessage()); 
                } catch (BadPaddingException exception) {
                  System.out.println(exception.getMessage()); 
                }
                 
            }
        }
        */
    }
}
