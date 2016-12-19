/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Login;

import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 *
 * @author Chris Chewa
 */
public class AESAlg {
    
    /**
     *
     */
    @SuppressWarnings("PublicField")
    public static String algo = "AES";

    /**
     *
     */
    @SuppressWarnings("PublicField")
    public byte[] keyValue;
    
    /**
     *
     * @param key
     */
    public AESAlg(byte key[])
    {
        keyValue = key;
    }
     
    /**
     *
     * @return
     * @throws Exception
     */
    public Key generateKey() throws Exception
    {
        Key key = new SecretKeySpec(keyValue, algo);
        return key;
    }
    
    /**
     *
     * @param msg
     * @return
     * @throws Exception
     */
    public String encrypt(String msg) throws Exception
    {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(algo);
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = c.doFinal(msg.getBytes());
        String encryptedValue = new BASE64Encoder().encode(encVal);
        return encryptedValue;
    }
    
    /**
     *
     * @param msg
     * @return
     * @throws Exception
     */
    public String decrypt(String msg) throws Exception
    {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(algo);
        c.init(Cipher.DECRYPT_MODE, key);
        byte[] decodedValue = new BASE64Decoder().decodeBuffer(msg);
        byte[] decValue = c.doFinal(decodedValue);
        String decryptedValue = new String (decValue);
        return decryptedValue; 
        
    }
    
}
