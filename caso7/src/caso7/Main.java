package caso7;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
  
public class Main 
{    
    public static void main(String[] args) throws Exception
    {
    	AlgoritmoProbatorioProbabilistico algoritmoPro = new AlgoritmoProbatorioProbabilistico();
        Decrypt metodo_desencriptar = new Decrypt();
        ArrayList<String[]> lista_vectores_posibles = new ArrayList<String[]>(); 
        String[] lista_abcdario = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","ñ","o","p","q","r","s","t","u","v","w","x","y","z"};
        String[] lista_numeros = {"0","1","2","3","4","5","6","7","8","9"};
        String texto_encriptado = "xZwM7BWIpSjYyGFr9rhpEa+cYVtACW7yQKmyN6OYSCv0ZEg9jWbc6lKzzCxRSSIvOvlimQZBMZOYnOwiA9yy3YU8zk4abFSItoW6Wj0ufQ0="; 
        String key= "29dh120_dk1_3";  
        int[] indices_escogidos;
        final int total_corridas = 40;
        final int total_grupo = 10;
        int indice_primer_undercore = 7;
        int indice_segundo_undercore = 11;
        int numero_random;
        int largo_lista_abcdario = lista_abcdario.length;
        int largo_lista_numeros = lista_numeros.length;
        boolean descarto_caso = false;
        
        System.out.println("Bienvenido a la solucion del CASO #7. Analisis de Algoritmos");
        
        for (int indice_abc=0; indice_abc < largo_lista_abcdario; indice_abc++) 
        {
          for (int indice_num=0; indice_num < largo_lista_numeros; indice_num++) 
          {
        	  String[] vector_a_insertar = {lista_abcdario[indice_abc], lista_numeros[indice_num]};
        	  lista_vectores_posibles.add(vector_a_insertar);
          }
        }
        
        //Inicializa las razones 
        algoritmoPro.createListaPorcentajeRazon(lista_vectores_posibles.size()); 
        for (int indice_porcentaje_razon = 0; indice_porcentaje_razon < algoritmoPro.getLargoListaPorcentajeRazon(); indice_porcentaje_razon++) 
        {
        	algoritmoPro.setListaPorcenatajeRazon(indice_porcentaje_razon, 1);
        }
        
        algoritmoPro.createListaIntervaloEspectro(lista_vectores_posibles.size());
        
        //Corre CORRIDAS cantidad de  veces para que descubra la respuesta
        for (int indice_corrida = 0; indice_corrida < total_corridas; indice_corrida++) 
        {  
          // Sacar GRUPOS elementos aleatorios
          indices_escogidos = new int [total_grupo];
          
          for (int indice_escogido = 0; indice_escogido < total_grupo; indice_escogido++) 
          {
        	  algoritmoPro.calcularNumeroRandom(); 
              indices_escogidos[indice_escogido] = algoritmoPro.encontrarPar(algoritmoPro.getNumeroRandom());
          }
          
          for (int indicePrueba = 0; indicePrueba < total_grupo; indicePrueba++) 
          {
        	  try 
        	  {
        		  	String pKey1 = new StringBuilder(key).replace(indice_primer_undercore, indice_primer_undercore+1, lista_vectores_posibles.get(indices_escogidos[indicePrueba])[0]).toString();
					key = new StringBuilder(pKey1).replace(indice_segundo_undercore, indice_segundo_undercore+1, lista_vectores_posibles.get(indices_escogidos[indicePrueba])[1]).toString();
					
					System.out.println("Texto desencriptado: "+ metodo_desencriptar.decrypt(key, texto_encriptado));
					
					algoritmoPro.actualizarRazon(indices_escogidos, true);
					algoritmoPro.actualizarEspectro();
					descarto_caso = false;
					break;
              } 
              catch (IllegalBlockSizeException exception) 
              {
            	  	descarto_caso = true;
              } 
              catch (InvalidKeyException exception) 
              { 
            	  	descarto_caso = true; 
              }
              catch (NoSuchAlgorithmException exception) 
              { 
            	  	descarto_caso = true;
              } 
              catch (NoSuchPaddingException exception) 
              { 
            	  	descarto_caso = true;
              } 
              catch (BadPaddingException exception) 
              {
            	  	descarto_caso = true;  
              }  
          }
          if(descarto_caso) 
          {
            System.out.println("Mensaje no se pudo desencriptar!!");
            algoritmoPro.actualizarRazon(indices_escogidos, false);
            algoritmoPro.actualizarEspectro();
          }
        }
        
        System.out.println("Posibles respuestas : ");
        for (int indice_posible_respuesta = 0; indice_posible_respuesta < lista_vectores_posibles.size(); indice_posible_respuesta++) 
        {
          if (algoritmoPro.getListaRazon()[indice_posible_respuesta] > 1.0) 
          {
            System.out.print("{" + lista_vectores_posibles.get(indice_posible_respuesta)[0]+ "," + lista_vectores_posibles.get(indice_posible_respuesta)[1] + "}");
            System.out.println(" {" +algoritmoPro.getListaRazon()[indice_posible_respuesta] + "}");
          }
        }
    }
/**
 * Fin del programa CASO #7. Analisis de Algoritmo
 */
}
