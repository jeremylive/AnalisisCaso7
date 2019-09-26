package caso7;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import com.sun.org.apache.xml.internal.security.utils.Base64;

public class Decrypt {
 
    private final String AES_ALGORITHM = "AES";
    private final String cI = "AES/CBC/PKCS5Padding";
    private final String initVector = "encryptionIntVec";
    private final String TEXT_FORMAT = "UTF-8";
 
    /**
     * Función de tipo String que recibe una llave (key), un vector de inicialización (iv)
     * y el texto que se desea descifrar
     * @param key la llave en tipo String a utilizar
     * @param encrypted el texto cifrado en modo String
     * @return el texto desencriptado en modo String
     * @throws Exception puede devolver excepciones de los siguientes tipos: NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException
     */
    public String decrypt(String key, String encrypted) throws Exception {
            
            
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(TEXT_FORMAT), AES_ALGORITHM);
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes(TEXT_FORMAT));
            
            Cipher cipher = Cipher.getInstance(cI);
            
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            
            byte[] original = cipher.doFinal(Base64.decode(encrypted));
   
            return new String(original);
            
    }
}

