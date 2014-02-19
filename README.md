Cryptography
============

Created by Kenes Zhanserik. International Inforamtion Technology Uiniversity. Information Security. 

    List of ciphers:

        1. Ceaser cipher
        2. PolyAlphabetic cipher
        3. Playfair cipher
    	4. Hill cipher             [Not tested, please if you have any issues letme know. Completed version will be added soon]    
    
    Source code directory Cryptography/src/cryptography/
    
    
    All commentaries will be added soon.  
    
    Example usage:
        
        System.out.println("Ceaser cipher Encoded text: "+Cryptography.ceaserEncode(3,"My name is Zhanserik"));
        System.out.println("Ceaser cipher Decoded text: "+Cryptography.ceaserDecode(3,"Pb qdph lv Ckdqvhuln"));
        System.out.println();
        
        System.out.println("PolyAlphabetic cipher Encoded text: "+Cryptography.polyAlphabeticEncode("deceptive", "WE ARE discovered save yourself"));
        System.out.println("PolyAlphabetic cipher Decoded text: "+Cryptography.polyAlphabeticDecode("deceptive","ZI EGX ymvgqztkmy vexi rwpvvinj"));
        System.out.println();
        
        System.out.println("Playfair Encoded text: " + Cryptography.playfairEncode("MONAR","HELLO FROM HEAVEN") );
        System.out.println("Playfair Decoded text: " + Cryptography.playfairDecode("MONAR","KCIZHR LFNO KCMYDA") );
        System.out.println();
