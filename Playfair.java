package playfair;

import java.awt.Point;  //untuk proses inputan dalam sebuah program java yang telah dijalankan
import java.util.Scanner;  //untuk menginput data yang sudah dijalankan

public abstract class Playfair {
    
//panjang array digraf  
private int length = 0;  
//membuat matriks untuk sandi Playfair 
private String [][] table;  
//main() method untuk menguji metode Playfair 
public static void main(String args[])  {  
Playfair pf = new Playfair() {};  
}  

//constructor kelas 
private Playfair()  {  
    
//meminta user untuk memasukkan playfair ciphher yang akan digunakan untuk penyandian & membuat tabel
System.out.print("Enter the key for playfair cipher: ");  
Scanner sc = new Scanner(System.in);  
String key = parseString(sc);  
while(key.equals(""))  
key = parseString(sc);  
table = this.cipherTable(key);  
//meminta user untuk memasukkan plaintext yang akan di encipher
System.out.print("Enter the plaintext to be encipher: ");  
//System.out.println("using the previously given keyword");  
String input = parseString(sc);  
while(input.equals(""))  
input = parseString(sc);  
//mengenkripsi pesan  
String output = cipher(input);  
String decodedOutput = decode(output);  
//hasil output  
this.keyTable(table);  
this.printResults(output,decodedOutput);  
}  

//membaca nilai dari satu objek untuk mengubahnya ke tipe lain  
//mengganti huruf J dengan I dan membuat string menjadi huruf besar semua  
private String parseString(Scanner sc)  {  
String parse = sc.nextLine();  
//mengubah semua huruf menjadi huruf besar  
parse = parse.toUpperCase();  
//string akan diganti dengan spasi untuk setiap kecocokan (A hingga Z) 
parse = parse.replaceAll("[^A-Z]", "");  
//mengganti huruf J menjadi I  
parse = parse.replace("J", "I");  
return parse;  }  

//membuat tabel cipher berdasarkan beberapa string input  
private String[][] cipherTable(String key)  {  
//membuat tabel huruf matriks 5x5    
String[][] playfairTable = new String[5][5];  
String keyString = key + "ABCDEFGHIKLMNOPQRSTUVWXYZ";  
//mengisi array string dengan string kosong  
for(int i = 0; i < 5; i++)  
for(int j = 0; j < 5; j++)  
playfairTable[i][j] = "";  
for(int k = 0; k < keyString.length(); k++)  
{  
boolean repeat = false;  
boolean used = false;  
for(int i = 0; i < 5; i++)  
{  
for(int j = 0; j < 5; j++)  
{  
if(playfairTable[i][j].equals("" + keyString.charAt(k)))  
{  
repeat = true;  
}  
else if(playfairTable[i][j].equals("") && !repeat && !used)  
{  
playfairTable[i][j] = "" + keyString.charAt(k);  
used = true;  
}  
}  
}  
}  
return playfairTable;  }  

//cipher: mengambil input, mengkodekannya, mengembalikan output  
private String cipher(String in)  {  
length = (int) in.length() / 2 + in.length() % 2;  
//menyisipkan x di antara digraf huruf ganda & definisikan ulang  
  
for(int i = 0; i < (length - 1); i++)  
{  
if(in.charAt(2 * i) == in.charAt(2 * i + 1))  
{  
in = new StringBuffer(in).insert(2 * i + 1, 'X').toString();  
length = (int) in.length() / 2 + in.length() % 2;  
}  
}  
//membuat array digraf
String[] digraph = new String[length];  
//loop berulang di atas plaintext 
for(int j = 0; j < length ; j++)  
{  
//memeriksa plaintext panjangnya genap atau tidak  
if(j == (length - 1) && in.length() / 2 == (length - 1))  
//jika tidak menambahkan X di akhir plaintext  
in = in + "X";  
digraph[j] = in.charAt(2 * j) +""+ in.charAt(2 * j + 1);  }  
//mengkodekan graf dan mengembalikan output  
String out = "";  
String[] encDigraphs = new String[length];  
encDigraphs = encodeDigraph(digraph);  
for(int k = 0; k < length; k++)  
out = out + encDigraphs[k];  
return out;  }  

//---------------encryption-----------------  
//mengkodekan input graph dengan spesifikasi cipher  
private String[] encodeDigraph(String di[])  {  
String[] encipher = new String[length];  
for(int i = 0; i < length; i++)  
{  
char a = di[i].charAt(0);  
char b = di[i].charAt(1);  
int r1 = (int) getPoint(a).getX();  
int r2 = (int) getPoint(b).getX();  
int c1 = (int) getPoint(a).getY();  
int c2 = (int) getPoint(b).getY();  
//dijalankan jika huruf digraf muncul pada baris yang sama
//dalam kasus seperti itu geser kolom ke kanan 
if(r1 == r2)  
{  
c1 = (c1 + 1) % 5;  
c2 = (c2 + 1) % 5;  
}  
//dieksekusi jika huruf digraf muncul pada kolom yang sama
//dalam kasus seperti itu menggeser baris ke bawah
else if(c1 == c2)  
{  
r1 = (r1 + 1) % 5;  
r2 = (r2 + 1) % 5;  
}  
//dijalankan jika huruf digraf muncul pada baris dan kolom yang berbeda
//dalam kasus seperti itu tukar kolom pertama dengan kolom kedua
else  
{  
int temp = c1;  
c1 = c2;  
c2 = temp;  
}  
//melakukan pencarian tabel dan memasukkan nilai-nilai itu ke dalam array yang disandikan  
encipher[i] = table[r1][c1] + "" + table[r2][c2];  }  
return encipher;  }  

//-----------------------decryption---------------------  
// men-decode output yang diberikan dari metode cipher dan decode (berlawanan dengan proses encoding) 
private String decode(String out)  
{  
String decoded = "";  
for(int i = 0; i < out.length() / 2; i++)  
{  
char a = out.charAt(2*i);  
char b = out.charAt(2*i+1);  
int r1 = (int) getPoint(a).getX();  
int r2 = (int) getPoint(b).getX();  
int c1 = (int) getPoint(a).getY();  
int c2 = (int) getPoint(b).getY();  
if(r1 == r2)  
{  
c1 = (c1 + 4) % 5;  
c2 = (c2 + 4) % 5;  
}  
else if(c1 == c2)  
{  
r1 = (r1 + 4) % 5;  
r2 = (r2 + 4) % 5;  
}  
else  
{  
//bertukar logika     
int temp = c1;  
c1 = c2;  
c2 = temp;  }  
decoded = decoded + table[r1][c1] + table[r2][c2];  }  
//mengembalikan pesan yang didekodekan 
return decoded;  }  

// mengembalikan titik yang berisi baris dan kolom surat 
private Point getPoint(char c)  {  
Point pt = new Point(0,0);  
for(int i = 0; i < 5; i++)  
for(int j = 0; j < 5; j++)  
if(c == table[i][j].charAt(0))  
pt = new Point(i,j);  
return pt;  }  

//function mencetak tabel kunci dalam bentuk matriks untuk sandi playfair 
private void keyTable(String[][] printTable)  {  
System.out.println("Playfair Cipher Key Matrix: ");  
System.out.println();  
// perulangan iterasi untuk baris 
for(int i = 0; i < 5; i++)  
{  
// perulangan iterasi untuk kolom    
for(int j = 0; j < 5; j++)  
{  
//mencetak tabel kunci dalam bentuk matriks    
System.out.print(printTable[i][j]+" ");  }  
System.out.println();  }  
System.out.println();  }    

//method yang mencetak semua hasil 
private void printResults(String encipher, String dec)  {  
System.out.print("Encrypted Message: ");  
//mencetak pesan terenkripsi
System.out.println(encipher);  
System.out.println();  
System.out.print("Decrypted Message: ");  
//mencetak pesan yang didekripsi
System.out.println(dec);  
}  
}  