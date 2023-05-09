package Busca;

import java.util.ArrayList;

public class Busca {
    public static int sequential(ArrayList<String> vetor, String word) throws Exception {
        for (int i = 0; i < vetor.size(); i++) {
            if (vetor.get(i).equalsIgnoreCase(word)) {
                return i;
            }
        }
        return -1;
    }

    public static int binary(ArrayList<String> palavra, String palavraBuscada, int inicio, int fim) throws Exception {
        int meio = (inicio + fim) / 2;
        if (palavraBuscada.length() == palavra.get(meio).length()) {
            for (int i = palavraBuscada.length(); i < i + 1 && i < palavra.size(); i++) {
                if (palavra.get(i).equalsIgnoreCase(palavraBuscada)) {
                    return i;
                }
            }
        }

        else if (palavraBuscada.length() < palavra.get(meio).length()) {
            return binary(palavra, palavraBuscada, inicio, (meio - 1));
        } else if (palavraBuscada.length() > palavra.get(meio).length()) {
            return binary(palavra, palavraBuscada, (meio + 1), fim);
        }
        return -1;
    }
}
