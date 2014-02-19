/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptography;

import java.awt.Point;
import java.util.StringTokenizer;
import java.math.*;
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
        
        System.out.println("Playfair Encoded text: " + playfairEncode("MONAR","HELLO, FROM, HEAVEN") );
        System.out.println("Playfair Decoded text: " + playfairDecode("MONAR","KCIZHR, LFNO, KCMYDA") );
        System.out.println();
        
        System.out.println("Hill Encoded text: " + hillEncode("GYBNQKURP","COOOLBR COOOLAAA") );
        System.out.println("Hill Decoded text: " + hillDecode("GYBNQKURP","YAULEOR YAUKUZAA") );
        System.out.println();
     
        
        
    }
    
    /**
     *  Ceaser Cipher
     */
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
    /**
     *  Poly Alphabetic Cipher
     */
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
    private static char[] polyAlphabeticKeyCheck(String key, String text){
        char texts[] = text.toCharArray();
        char keys[] = new char[text.length()];
        if(key.length() < texts.length){
            char temporary[] = key.toUpperCase().toCharArray();
            for(int i=0; i<texts.length; i++){
                keys[i] = temporary[i%key.length()]; 
            }
        }
        
        else{
            keys = key.toUpperCase().toCharArray();
        }
        return keys;
    }
    public static String polyAlphabeticEncode(String key, String text){
        
        String encodedText = "";
        char keys[] = polyAlphabeticKeyCheck(key, text);
        char texts[] = text.toCharArray();
        
        
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
    public static String polyAlphabeticDecode(String key, String text){
        String decodedText = "";
        
        char keys[] = polyAlphabeticKeyCheck(key, text);
        char texts[] = text.toCharArray();
        
        
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
    /**
     *  PlayFair Cipher
     */
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
        StringTokenizer st = new StringTokenizer(text, ".,!? ", true);
        
        while (st.hasMoreTokens()){
           String component = st.nextToken();
 
           if(component.charAt(0) > 'A' && component.charAt(0) < 'Z')
               encodedText = encodedText + playfairCipher(component, key) ;
           
           else
            encodedText = encodedText + component;
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

        StringTokenizer st = new StringTokenizer(text,".,!? ", true);
        
        while (st.hasMoreTokens()){
            String component = st.nextToken();
           if(component.charAt(0) > 'A' && component.charAt(0) < 'Z')
               decodedText = decodedText + playfairDecipher(component, key) ;
           
           else
            decodedText = decodedText + component; 
        }
        return decodedText;
    }
     
    /**
     *  Hill Cipher
     */
    public static void printHillMap(int map[][], boolean isInversed){
        if(!isInversed)
            System.out.println("Hill Cipher Key For Encrypting");
        else
            System.out.println("Hill Cipher Key For Decrypting");
        
        for(int i=0; i<map.length; i++){
            System.out.print("|");
            for(int j=0; j<map.length; j++){
                System.out.print(map[i][j] + (((Math.log10(map[i][j]))>1)?" ":"  "));
            }
            System.out.print("|\n");
        }
        System.out.println();
    }   
    private static boolean checkHillMap(int map[][]){
        boolean isAvailable = false;
        
        Matrix keyMap = new Matrix(map);
        
        int determin = Matrix.determinant(keyMap);
        int b = 26;
        while(determin!=0 && b!=0){
            int c = b;
            b = determin%b;
            determin = c;
        }
        int gcd = determin+b;
        
        if(Matrix.determinant(keyMap) > 0 && gcd == 1)
            isAvailable = true;
        
        
        return isAvailable;
    }
    private static int[][] hillMap(String key){
        
        int size = (int)Math.sqrt((double)key.length());
        int map[][] = new int[size][size];
       
        for(int i=0; i<key.length(); i++)
            map[i/size][i%size] = (int)key.charAt(i)%65;
        
        //batyahan@gmail.com
    
        return map;
    }
    
    private static int[][] hillInverseMap(int map[][]){
        Matrix keyMap = Matrix.inverse(new Matrix(map));
        int inversedKey[][] = keyMap.getValues();
        
        for(int i=0; i<inversedKey.length; i++)
            for(int j=0; j<inversedKey[0].length; j++)
                inversedKey[i][j] = inversedKey[i][j]>0?(inversedKey[i][j]%26):(inversedKey[i][j]%26 + 26);
                
        return inversedKey;
    }
    private static int[] hillWord2Vector(String word){
        int result[] = new int[word.length()];
        
        for(int i=0; i < word.length(); i++)
            result[i] = (int)word.charAt(i)%65;
        
        return result;
    }
    private static String hillVector2Word(int vector[]){
        String word = "";
        
        for(int i=0; i<vector.length; i++)
            word = word + "" + (char)(vector[i]+65);
            
        return word;
    }
    private static String[] hillWords(int index, String text){
        int size = text.length()/index + (((text.length()%index)>0)?1:0);
        String components[] = new String[size];
        
        int cell = 0;
        String conatainer = "";
        for(int i=0; i<text.length(); i++){
            if(conatainer.length() == index){
                components[cell] = conatainer;
                cell ++;
                conatainer = "";
                
            }
            conatainer = conatainer + "" + text.charAt(i);
        }
        
        if(conatainer.length() > 0)
            components[cell] = conatainer;
        
        return components;
    }
    private static int[] hillMultiply(int first[][], int second[]){
        int firstRows = first.length;
        int firstCols = first[0].length;
        
        if(second.length != firstCols)
            return second;
            
        //int result[][] = new int[firstRows][secondCols];
        int result[] = new int[firstRows];
        for(int i=0; i < firstRows; i++)
            for(int j=0; j < firstCols; j++)
                result[i] = (result[i] + first[i][j] * second[j])%26;
                
        return result;
    }
    private static String hillCipher(String key, String text){
        String encodedWord = "";
        
        int size = (int)Math.sqrt((double)key.length());
        if(size > text.length())
            return text;
        int map[][] = hillMap(key);
        String words[] = hillWords(size, text);
        
        for(int i=0; i<words.length; i++){
            int vector[] = hillWord2Vector(words[i]);
            int encodedVector[] = hillMultiply(map, vector);
            encodedWord = encodedWord + "" + hillVector2Word(encodedVector);
            
        }
        return encodedWord;
    }
    public static String hillEncode(String key, String text){
        String encodedText = "";
        
        text = text.toUpperCase();
        key = key.toUpperCase();
        
        printHillMap(hillMap(key), false);
        
        StringTokenizer st = new StringTokenizer(text, ".,!? ", true);
        
        while (st.hasMoreTokens()){
           String component = st.nextToken();
           
           if(component.charAt(0) > 'A' && component.charAt(0) < 'Z')
               encodedText = encodedText + hillCipher(key, component) ;
           
           else
               encodedText = encodedText + component;
        }
        return encodedText;
    }  
    private static String hillDecipher(String key, String text){
        String decodedWord = "";
        
        int map[][] = hillMap(key);
        int inversedMap[][] = hillInverseMap(map);
        
        int size = (int)Math.sqrt((double)key.length());
        if(size > text.length())
            return text;
        String words[] = hillWords(size, text);
        
        for(int i=0; i<words.length; i++){
            int vector[] = hillWord2Vector(words[i]);
            int encodedVector[] = hillMultiply(inversedMap, vector);
            
            decodedWord = decodedWord + "" + hillVector2Word(encodedVector);
            
        }
       
        
        return decodedWord;
    } 
    public static String hillDecode(String key, String text){
        String decodedText = "";
        
        text = text.toUpperCase();
        key = key.toUpperCase();
        
        if(!checkHillMap(hillMap(key)))
            return "[WARNING: Your key is not invertable]";
        
        printHillMap(hillInverseMap(hillMap(key)), true);
        
        StringTokenizer st = new StringTokenizer(text, ".,!? ", true);
        
        while (st.hasMoreTokens()){
           String component = st.nextToken();
           
           if(component.charAt(0) > 'A' && component.charAt(0) < 'Z')
               decodedText = decodedText + hillDecipher(key, component) ;
           
           else
            decodedText = decodedText + component;
        }
        
        return decodedText;
    }
}


class Matrix{
   private int rows;
   private int cols;
   private int[][] matrix;
   
   public Matrix(int[][] dat){
       this.matrix = dat;
       this.rows = dat.length;
       this.cols = dat[0].length;
   }
   public Matrix(int nrow, int ncols){
       this.rows = nrow;
       this.cols = ncols;
       this.matrix = new int[nrow][ncols];
   }
   public void setRows(int row){
       this.rows = row;
   }
   public int getRows(){
       return this.rows;
   }
   public void setCols(int col){
       this.cols = col;
   }
   public int getCols(){
       return this.cols;
   }
   public void setValues(int[][] values){
       this.matrix = values;
   }
   public int[][] getValues(){
       return this.matrix;
   }
   public void setValueAt(int i, int j, int value){
       this.matrix[i][j] = value;
   }
   public int getValueAt(int i, int j){
       return this.matrix[i][j];
   }
   public boolean isSquare(){
       return this.rows == this.cols;
   }
   public Matrix multiplyByConst(int constant){
       Matrix mat = new Matrix(this.rows, this.cols);
       for(int i=0; i < this.rows; i++){
           for(int j=0; j < this.cols; j++){
               mat.setValueAt(i, j, matrix[i][j] * constant);
           }
       }
       return mat;
   }
   public static Matrix transpose(Matrix matrix){
       Matrix transposed = new Matrix(matrix.getCols(), matrix.getRows());
       for(int i=0; i<matrix.getRows(); i++){
           for(int j=0; j<matrix.getCols(); j++){
               transposed.setValueAt(j, i, matrix.getValueAt(i, j));
           }
       }
       return transposed;
   }
   public static Matrix multiply(Matrix first, Matrix second){
       Matrix result = new Matrix(first.getRows(), second.getCols());
       
       for(int i=0; i < result.getRows(); i++){
           for(int j=0; j < result.getCols(); j++){
               int sum = 0;
               for(int k=0; k < first.getCols(); k++){
                   sum += first.getValueAt(i, k) * second.getValueAt(k, j);
               }
               result.setValueAt(i, j, sum);
           }
       }
       return result;
   }
   public static Matrix add(Matrix first, Matrix second){
       if(first.getCols() != second.getCols() || first.getRows() != second.getRows())
           return null;
       Matrix result = new Matrix(first.getRows(), first.getCols());
       
       for(int i=0; i<first.getRows(); i++)
           for(int j=0; j<first.getCols(); j++)
               result.setValueAt(i, j, first.getValueAt(i, j) + second.getValueAt(i, j));
       
       return result;
   } 
   public static Matrix substruct(Matrix first, Matrix second){
       return add(first, second.multiplyByConst(-1));
   }
   public static Matrix createSubMatrix(Matrix matrix, int row, int col){
       Matrix mat = new Matrix(matrix.getRows() - 1, matrix.getCols() - 1);
       int r = -1;
       for(int i=0; i < matrix.getRows(); i++){
           if(i == row)
               continue;
           r++;
           int c= -1;
           for(int j=0; j<matrix.getCols(); j++){
               if(j == col)
                   continue;
               mat.setValueAt(r, ++c, matrix.getValueAt(i, j));
           }
       }
       return mat;
   } 
   public static int determinant(Matrix matrix){
       if(!matrix.isSquare())
           return -1;
       if(matrix.getCols() == 1)
           return matrix.getValueAt(0, 0);
       if(matrix.getCols() == 2)
           return (matrix.getValueAt(0, 0) * matrix.getValueAt(1, 1)) - (matrix.getValueAt(0, 1) * matrix.getValueAt(1, 0));
       int sum = 0;
       
       for(int i=0; i < matrix.getCols(); i++){
           sum += (((i%2)==0)?1:-1) * matrix.getValueAt(0, i) * determinant(createSubMatrix(matrix,0,i));
       }
       return sum;
   }
   public static Matrix cofactor(Matrix matrix){
       Matrix mat = new Matrix(matrix.getRows(), matrix.getCols());
       
       for(int i=0; i<matrix.getRows(); i++)
           for(int j=0; j<matrix.getCols(); j++)
               mat.setValueAt(i, j, (((i%2)==0)?1:-1) * (((j%2)==0)?1:-1) * determinant(createSubMatrix(matrix,i ,j)));
       
       return mat;   
   }
   public static Matrix inverse(Matrix matrix){
       return (transpose(cofactor(matrix)).multiplyByConst(determinant(matrix)));
   }
}