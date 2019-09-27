package analisis;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import com.sun.org.apache.xml.internal.security.utils.Base64;

public class Decrypt {
 
    private final String AES_ALGORITHM = "AES";
    private final String cI = "AES/ECB/PKCS5Padding";
    private final String initVector = "encryptionIntVec";
    private final String TEXT_FORMAT = "UTF-8";
    private final String SECURITY_FORMAT = "SHA-1";
    
    public SecretKeySpec setKey(String myKey)
    {
        byte[] key;
        SecretKeySpec secretKey;
        MessageDigest sha = null;
        try {
            key = myKey.getBytes(TEXT_FORMAT);
            sha = MessageDigest.getInstance(SECURITY_FORMAT);
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            secretKey = new SecretKeySpec(key, AES_ALGORITHM);
            return secretKey;
            
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
 
    }
 
    /**
     * Función de tipo String que recibe una llave (key), un vector de inicialización (iv)
     * y el texto que se desea descifrar
     * @param key la llave en tipo String a utilizar
     * @param encrypted el texto cifrado en modo String
     * @return el texto desencriptado en modo String
     * @throws Exception puede devolver excepciones de los siguientes tipos: NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException
     */
    public String decrypt(String key, String encrypted) throws Exception {
            
            
            //SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(TEXT_FORMAT), AES_ALGORITHM);
            
            SecretKeySpec skeySpec = setKey(key);
            Cipher cipher = Cipher.getInstance(cI);
            
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes(TEXT_FORMAT));
            
           
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            
            byte[] original = cipher.doFinal(Base64.decode(encrypted));
            
            return new String(original);
            
    }
}

