package caso7;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JOptionPane;
import static org.apache.commons.codec.binary.Base64.decodeBase64;
import static org.apache.commons.codec.binary.Base64.encodeBase64;
  
public class Main 
{
	public static void main(String[] args) throws Exception
    {
		String iv = "0123456789abcdef";
        String encriptado = "xZwM7BWIpSjYyGFr9rhpEa+cYVtACW7yQKmyN6OYSCv0ZEg9jWbc6lKzzCxRSSIvOvlimQZBMZOYnOwiA9yy3YU8zk4abFSItoW6Wj0ufQ0="; 
        String key = "29dh120_dk1_3";        
        
        String[] lista_abc = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
        String[] lista_num = {"0","1","2","3","4","5","6","7","8","9"};
        
        int largo_lista_abc = lista_abc.length;
        int largo_lista_num = lista_num.length;
        
        int pIndice1 = 7;
        int pIndice2 = 11;
        
        JOptionPane.showInputDialog(null, "Programa de Analisis, En que estas pensando?");
        
        for(int cont = 0; cont < largo_lista_abc; cont++)
        {
            
            for(int cont_num = 0; cont_num < largo_lista_num; cont_num++)
            {
            	String pKey1 = new StringBuilder(key).replace(pIndice1, pIndice1+1, lista_abc[cont]).toString();
            	key = new StringBuilder(pKey1).replace(pIndice2, pIndice2+1, lista_num[cont_num]).toString();
            	
            	System.out.println(key);
            	
            	//System.out.println("Texto desencriptado: "+caso7.Decrypt.decrypt(key, iv, encriptado));
            	
            	
            	NoSuchAlgorithmException e = new NoSuchAlgorithmException();
            	System.out.println(e.getMessage());
            	
            	IllegalBlockSizeException e1 =new IllegalBlockSizeException();
            	System.out.println(e.getMessage());

            	NoSuchPaddingException e2 = new NoSuchPaddingException();
            	System.out.println(e2.getMessage());
            	
            	InvalidKeyException e3 = new InvalidKeyException();
            	System.out.println(e3.getMessage());
            	
            	
            	
            	/*
				 * try {
				 * 
				 * 
				 * } catch (NoSuchAlgorithmException e) { // TODO: handle exception
				 * System.out.println(e.getMessage()); } catch (IllegalBlockSizeException e) {
				 * // TODO: handle exception System.out.println(e.getMessage()); } catch (
				 * NoSuchPaddingException e) { // TODO: handle exception
				 * System.out.println(e.getMessage()); } catch (InvalidKeyException e) { //
				 * TODO: handle exception System.out.println(e.getMessage()); }
				 */
            }
        }
    }
}