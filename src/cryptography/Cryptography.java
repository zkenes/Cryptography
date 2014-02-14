/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptography;

import java.awt.Point;
import java.util.StringTokenizer;

/**
 *
 * @author zhanserikkenes
 */
public class Cryptography {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
      
        /**
         *  Example usage
         */
        System.out.println("Ceaser cipher Encoded text: "+ceaserEncode(3,"My name is Zhanserik"));
        System.out.println("Ceaser cipher Decoded text: "+ceaserDecode(3,"Pb qdph lv Ckdqvhuln"));
        System.out.println();
        
        System.out.println("PolyAlphabetic cipher Encoded text: "+polyAlphabeticEncode("deceptive", "WE ARE discovered save yourself"));
        System.out.println("PolyAlphabetic cipher Decoded text: "+polyAlphabeticDecode("deceptive","ZI EGX ymvgqztkmy vexi rwpvvinj"));
        System.out.println();
        
        System.out.println("Playfair Encoded text: " + playfairEncode("MONAR","HELLO FROM HEAVEN") );
        System.out.println("Playfair Decoded text: " + playfairDecode("MONAR","KCIZHR LFNO KCMYDA") );
        System.out.println();
        
    }
    
    
    public static String ceaserEncode(int shift, String text){
        String encodedText="";
        shift = shift%26;                                
        
         for (int i=0;i<text.length();i++){
             boolean isUpperCase = false;
             int letter = text.charAt(i);
             
             if(letter >= 'A' && letter <= 'Z')          
                 isUpperCase = true;
             
             if(letter == (int)' '){
                 encodedText = encodedText + (char)letter;
                 continue;
             }
             
             letter += shift;
             if(isUpperCase){
                 if (letter > (int)'Z') 
                    letter = (letter - 26);
             }else{
                 if(letter > 'z')
                     letter = (letter - 26);
             }
                   
             encodedText = encodedText + (char)letter;
         }
         
         return encodedText;
    }
    
    public static String ceaserDecode(int shift, String text){
        String decodedText = "";
        
        shift = shift%26;
        
        for (int i=0; i<text.length(); i++){
            boolean isUpperCase = false;
            int letter = text.charAt(i);
            
            if(letter >= 'A' && letter <= 'Z')
                 isUpperCase = true;
            
            if(letter == (int)' '){
                 decodedText = decodedText + (char)letter;
                 continue;
             }
            
            letter -= shift;
           
            if(isUpperCase){
                 if (letter < (int)'A') 
                    letter = (letter + 26);
             }else{
                 if(letter < 'a')
                     letter = (letter + 26);
             }
            
           
            
            decodedText = decodedText + (char)letter; 
        }
        
        return decodedText;
    }
    
    
    private static char[][] polyAlphabeticMap(boolean isUppercase){
        
        char map[][] = new char[26][26];
        
        if(isUppercase){
            for(int i = 0; i < 26; i++){
                for(int j = 0; j < 26; j++){
                    map[i][j] = (char)((i+j)%26+65);
                }
            }
        }else{
            for(int i = 0; i < 26; i++){
                for(int j = 0; j < 26; j++){
                    map[i][j] = (char)((i+j)%26+97);
                }
            }
        }
        
        return map;
    }
    public static String polyAlphabeticEncode(String key, String text){
        
        String encodedText = "";
        char keys[] = new char[text.toUpperCase().toCharArray().length];
        char texts[] = text.toCharArray();
        
        if(key.toUpperCase().toCharArray().length < texts.length){
            char temporary[] = key.toUpperCase().toCharArray();
            for(int i=0; i<texts.length; i++){
                keys[i] = temporary[i%key.toUpperCase().toCharArray().length]; 
            }
        }
        
        else{
            keys = key.toUpperCase().toCharArray();
        }
        
        char mapUppercase[][] = polyAlphabeticMap(true);
        char mapLowercase[][] = polyAlphabeticMap(false);
        
        
       for(int i=0; i<texts.length; i++){
           boolean isUppercase = false;
           if(texts[i] == ' '){
               encodedText = encodedText + " ";
               continue;
           }
           if(texts[i] >= 'A' && texts[i] <= 'Z')
               isUppercase = true;
           
           if(isUppercase)
               encodedText = encodedText + "" + mapUppercase[(int)keys[i] - 65][(int)texts[i] - 65];
           else
               encodedText = encodedText + "" + mapLowercase[(int)keys[i] - 65][(int)texts[i] - 97];
       }
        
        
        return encodedText;
    }
    
    
    /**
     *  Poly Alphabetic Cipher
     */
    public static String polyAlphabeticDecode(String key, String text){
        String decodedText = "";
        
        char keys[] = new char[text.toUpperCase().toCharArray().length];
        char texts[] = text.toCharArray();
        
        if(key.toUpperCase().toCharArray().length < texts.length){
            char temporary[] = key.toUpperCase().toCharArray();
            for(int i=0; i<texts.length; i++){
                keys[i] = temporary[i%key.toUpperCase().toCharArray().length]; 
            }
        }
        else{
            keys = key.toUpperCase().toCharArray();
        }
        
        char mapUppercase[][] = polyAlphabeticMap(true);
        char mapLowercase[][] = polyAlphabeticMap(false);
        
        
        
        for(int i=0; i<texts.length; i++){
            boolean isUppercase = false;
            if(texts[i] == ' '){
               decodedText = decodedText + " ";
               continue;
            }
            if(texts[i] >= 'A' && texts[i] <= 'Z')
               isUppercase = true;
            
            for(int j=0; j<26; j++){
                if(isUppercase){
                    if(mapUppercase[(int)keys[i]-65][j] == texts[i]){
                        decodedText = decodedText + "" + (char)(j+65);
                    }
                }else{
                    if(mapLowercase[(int)keys[i]-65][j] == texts[i]){
                        decodedText = decodedText + "" + (char)(j+97);
                    }
                }
            }
        }
        
        return decodedText;
    }
    
    private static char[][] playfairMap(String key){
        char keyMap[][] = new char[5][5];
        
        key = key + "ABCDEFGHIKLMNOPQRSTUVWXYZ"; 
        
        int index = 0;
        for(int i=0; i < key.length(); i++){
            boolean flag = false;
            for(int j=i-1; j >= 0; j--){
                if(key.charAt(i) == key.charAt(j))
                    flag = true;
            }
            if(!flag){
                keyMap[index/5][index%5] = key.charAt(i);
                index++;
            }
        }
        return keyMap;
    }
    
    private static Point playfairGetLocation(char c, char[][] map){
        Point p = new Point();
       
        for(int i=0; i<5; i++){
            for(int j=0; j<5; j++){
                if(c == map[i][j])
                    p = new Point(i,j);
            }
        }
        
        return p;
    } 
    
    private static String playfairCipher(String text, String key){
        String encodedWord = "";
        int length = text.length()/2 + text.length()%2;
        text = text.toUpperCase();
        for(int i = 0; i < length -1; i++){
            if(text.charAt(2*i) == text.charAt(2*i + 1)){
                text = new StringBuffer(text).insert(2*i + 1, 'X').toString();
            }
        }
        
        char keyMap[][] = playfairMap(key);
        
        
        String[] couples = new String[length]; 
        
        for(int i=0; i<length; i++){
            if(i == (length - 1) && text.length()/2 == (length - 1))
                text = text + 'X';
            couples[i] = text.charAt(2*i)+""+text.charAt(2*i+1);
        }
        
        String[] encodedCouples = new String[length];
        
        for(int i=0; i<length; i++){
            char first = couples[i].charAt(0);
            char second = couples[i].charAt(1);
            
            int firstX = (int) playfairGetLocation(first, keyMap).x;
            int firstY = (int) playfairGetLocation(first, keyMap).y;
            
            int secondX = (int) playfairGetLocation(second, keyMap).x;
            int secondY = (int) playfairGetLocation(second, keyMap).y;
            
            
            if(firstX == secondX){
                
                firstY = (firstY + 1)%5;
                secondY = (secondY + 1)%5; 
                
            }
            else if(firstY == secondY){
                
                firstX = (firstX + 1)%5;
                secondX = (secondX + 1)%5;
            }
            else{
                int t = firstY;
                firstY = secondY;
                secondY = t;
            }
            
            
            encodedCouples[i] = keyMap[firstX][firstY] + "" + keyMap[secondX][secondY];
        }
        
        for(int i = 0; i < encodedCouples.length; i++){
            encodedWord = encodedWord + "" + encodedCouples[i];
        }
        
        return encodedWord;
        //return text;
    }
    
    
    public static String playfairEncode(String key, String text){
        String encodedText = "";
        text = text.toUpperCase();
        key = key.toUpperCase();
        key = key.replace('J', 'I');
        StringTokenizer st = new StringTokenizer(text);
        
        while (st.hasMoreTokens()){
           encodedText = encodedText + playfairCipher(st.nextToken(), key) + " "; 
        }
        
        
        return encodedText;
    }
    
    private static String playfairDecipher(String text, String key){
        String decodedWord = "";
        
        char keyMap[][] = playfairMap(key);
        
        for(int i=0; i < text.length()/2 ; i++){
            char first = text.charAt(2*i);
            char second = text.charAt(2*i + 1);
            
            int firstX = (int) playfairGetLocation(first, keyMap).x;
            int firstY = (int) playfairGetLocation(first, keyMap).y;
            
            int secondX = (int) playfairGetLocation(second, keyMap).x;
            int secondY = (int) playfairGetLocation(second, keyMap).y;
            
            if(firstX == secondX){
                
                firstY = (firstY + 4)%5;
                secondY = (secondY + 4)%5; 
                
            }
            else if(firstY == secondY){
                
                firstX = (firstX + 4)%5;
                secondX = (secondX + 4)%5;
            }
            else{
                int t = firstY;
                firstY = secondY;
                secondY = t;
            }
            
            decodedWord = decodedWord + "" + keyMap[firstX][firstY] + keyMap[secondX][secondY];
        }
        
        return decodedWord;
    } 
    
    public static String playfairDecode(String key ,String text){
        String decodedText = "";
        text = text.toUpperCase();
        key = key.toUpperCase();
        key = key.replace('J', 'I');

        StringTokenizer st = new StringTokenizer(text);
        
        while (st.hasMoreTokens()){
           decodedText = decodedText + playfairDecipher(st.nextToken(), key) + " "; 
        }
        return decodedText;
    }
}
