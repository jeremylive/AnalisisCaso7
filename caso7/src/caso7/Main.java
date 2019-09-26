package caso7;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.JOptionPane;
  
public class Main 
{
    public static void main(String[] args) throws Exception
    {
        Decrypt decryptor = new Decrypt();
        String encriptado = "xZwM7BWIpSjYyGFr9rhpEa+cYVtACW7yQKmyN6OYSCv0ZEg9jWbc6lKzzCxRSSIvOvlimQZBMZOYnOwiA9yy3YU8zk4abFSItoW6Wj0ufQ0="; 
        String key = "29dh120_dk1_3";   
        
        
        String[] lista_abc = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","ñ","o","p","q","r","s","t","u","v","w","x","y","z"};
        String[] lista_num = {"0","1","2","3","4","5","6","7","8","9"};
        
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
    }
}
