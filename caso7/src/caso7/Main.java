package caso7;

import javax.swing.JOptionPane;

  
public class Main 
{
	public static void main(String[] args) throws Exception
    {
        
        String[] lista_abc = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
        String[] lista_num = {"0","1","2","3","4","5","6","7","8","9"};
        
        int largo_lista_abc = lista_abc.length;
        int largo_lista_num = lista_num.length;
        
        String key = "29dh120_dk1*3";
        
        for(int cont = 0; cont < largo_lista_abc; cont++)
        {
            
            for(int cont_num = 0; cont_num < largo_lista_abc; cont_num++)
            {
                //key.replace("_", );
                //key.replace("*", );
                
            }
        }
        
        
        
        String iv = "0123456789abcdef";
        String encriptado = "xZwM7BWIpSjYyGFr9rhpEa+cYVtACW7yQKmyN6OYSCv0ZEg9jWbc6lKzzCxRSSIvOvlimQZBMZOYnOwiA9yy3YU8zk4abFSItoW6Wj0ufQ0="; // vector de inicialización
        String cleartext = "hola";
        
        JOptionPane.showInputDialog(null, "Programa de Analisis, En que estas pensando?");
        
        //System.out.println("Texto desencriptado: "+programaanalisis.StringEncrypt.decrypt(key, iv,programaanalisis.StringEncrypt.encrypt(key, iv,encriptado)));

        
    }
    
}
