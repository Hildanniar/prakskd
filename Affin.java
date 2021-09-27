package affin;
public class Affin {
// input kunci
  static int a = 5; 
  static int b = 7; 
  static String Message(char[] msg) 
  { 
    String cipher = ""; 
    for (int i = 0; i < msg.length; i++) 
    { 
      if (msg[i] != ' ') 
      { 
          //enkripsi affine cipher
        cipher = cipher 
            + (char) ((((a * (msg[i] - 'A')) + b) % 26) + 'A'); 
      }
      else 
      { 
        cipher += msg[i]; 
      } 
    } 
    return cipher; 
  } 
  static String Cipher(String cipher) 
  { 
    String msg = ""; //tipe data untuk huruf
    int a_inv = 0; 
    int flag = 0; 
    for (int i = 0; i < 26; i++)  // pengulangan untuk mengenkripsi huruf per huruf
   
    { 
      flag = (a * i) % 26; //perhitungan kunci a
      if (flag == 1) 
      { 
        a_inv = i; 
      } 
    } 
    for (int i = 0; i < cipher.length(); i++) 
    { 
      if (cipher.charAt(i) != ' ') 
      { 
          //deskripsi affine cipher
        msg = msg + (char) (((a_inv * 
            ((cipher.charAt(i) + 'A' - b)) % 26)) + 'A'); 
      } 
      else 
      { 
        msg += cipher.charAt(i); 
      } 
    } 
    return msg; 
  } 
    public static void main(String[] args) {
       { 
           //input plaintext
    String msg = "HILDANNIARFAUZI"; 
    String cipherText = Message(msg.toCharArray()); 
    System.out.println("Plain Text is : HILDANNIARFAUZI" );
    System.out.println("Key a is : 5 " );
    System.out.println("Key b is : 7 " );
    System.out.println("Encrypted Message is : " + cipherText);  //menampilkan hasil enkripsi
    System.out.println("Decrypted Message is: " + Cipher(cipherText)); //menampilkan hasil deskripsi
  } 
} 
    }
    

