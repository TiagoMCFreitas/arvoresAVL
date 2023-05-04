/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arvoresavl;
import arvoresavl.ArvoresAVL;
/**
 *
 * @author aluno
 */
public class ClassTest {
    public static void main(String[] args) {
          String[] letras = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
                "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        ArvoresAVL arvore = new ArvoresAVL();
        
        for(int i = 0; i < letras.length; i++){
            arvore.insert(letras[i]);
            arvore.printAVLTree();
            arvore.addPalavra("Arvore");
            arvore.addPalavra("Agua");
        }
        for(int cont = 0; cont < letras.length;cont++){
            arvore.addPalavra("Busca");
            arvore.addPalavra("Balão");
            
        }
         System.out.println(arvore.getLista("A"));
         
         
         
         
    }
  
}
