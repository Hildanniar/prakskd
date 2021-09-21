/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vigenerecipher;

/**
 *
 * @author USER
 */
public class Vigenerecipher {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // menampung kumpulan karakter
    String plaintext = "HILDANNIARFAUZI";
    String keyword = "MADIUN";
    encryptDecrypt(plaintext,keyword);
    }

    
    public static void encryptDecrypt(String plaintext, String keyword)
    {
 //Mengonversi plaintext ke array char
 char msg[] = plaintext.toCharArray();
 int msgLen = msg.length;
 int i,j;
  
 // Membuat array char baru
 char key[] = new char[msgLen];
 char encryptedMsg[] = new char[msgLen];
 char decryptedMsg[] = new char[msgLen];
  
 /* menghasilkan kunci, menggunakan kata kunci dalam siklus
 cara sama dengan panjang
 pesan asli yaitu plaintext */
 for(i = 0, j = 0; i < msgLen; ++i, ++j)
 {
  if(j == keyword.length())
  {
   j = 0;
  }
  key[i] = keyword.charAt(j);
 }
  
 //kode enkripsi 
 for(i = 0; i < msgLen; ++i)
  encryptedMsg[i] = (char) (((msg[i] + key[i]) % 26) + 'A');
 
 //kode dekripsi
 for(i = 0; i < msgLen; ++i)
  decryptedMsg[i] = (char)((((encryptedMsg[i] - key[i]) + 26) % 26) + 'A');
  
 System.out.println("Original Message: " + plaintext);  
 System.out.println("Keyword: " + keyword);
 /* String.valueOf() metode mengkonversi
    char[] ke String */
 System.out.println("Key: " + String.valueOf(key));
 System.out.println();
 System.out.println("Encrypted Message: " + String.valueOf(encryptedMsg));
 System.out.println();        
 System.out.println("Decrypted Message: " + String.valueOf(decryptedMsg));
   }
}
   
