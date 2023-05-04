package arvoresavl;

import javax.swing.SortOrder;

import arvoresavl.ArvoresAVL;

public class ClassTest {

    public static void main(String[] args) {
        String[] letras = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
            "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        ArvoresAVL arvore = new ArvoresAVL();

        for (int i = 0; i < letras.length; i++) {
            arvore.insert(letras[i]);

        }
        String pog = "a";
        arvore.addPalavra("Arvore");
        arvore.addPalavra("Agua");
        arvore.addPalavra("Bruninho");
        System.out.println(arvore.getLista("A"));
        arvore.removerPalavra("Arvore");
        System.out.println(arvore.getLista(pog.toUpperCase()));
        System.out.println(arvore.getLista("B"));
        System.out.println(arvore.getLista("P"));
        if(arvore.acharPalavras("Arvore") == true){
            System.out.println("Achou");
        }else{
            System.out.println("Achou n ");
        }
    }

}
