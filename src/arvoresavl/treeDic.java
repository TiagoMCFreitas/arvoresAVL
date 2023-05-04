package arvoresavl;

import arvoresavl.ArvoresAVL;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class treeDic {
    
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        try {
            File arquivo = new File("./src/dicTXT/dicPort.txt");
            File dicSaida = new File("./src/dicTXT/newDic.txt");
            Path arquivoo = Paths.get("./src/dicTXT/newDic.txt");
            File saidinha = new File("./src/dicTXT/dicTXT/newDic.txt");
            byte[] dicionario = Files.readAllBytes(arquivoo);
            String dicLer = new String(dicionario);
            
            FileReader fr = new FileReader(arquivo);
            BufferedReader bf = new BufferedReader(fr);
            FileWriter fw = new FileWriter(dicSaida);
            
            String linha;
            ArrayList<String> dicioo = new ArrayList<String>();
            while ((linha = bf.readLine()) != null) {
                if (linha.contains("/")) {
                    int indexL = linha.indexOf("/");
                    String novaLinha = linha.substring(0, indexL);
                    fw.write(novaLinha + "\n");
                    dicioo.add(novaLinha);
                } else if (linha.contains(".")) {
                    int indexL = linha.indexOf(".");
                    String novaLinha = linha.substring(0, indexL);
                    fw.write(novaLinha + "\n");
                    dicioo.add(novaLinha);
                } else {
                    fw.write(linha + "\n");
                    dicioo.add(linha);
                }
            }
            fr.close();
            bf.close();
            fw.close();
            
            int pog = 0;
            String[] letras = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
                "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
            ArrayList<String> palavrasTemp = new ArrayList<>();
            ArrayList<String> PalavrasPos = new ArrayList<>();
            ArvoresAVL arvore = new ArvoresAVL();
            do {
                for (String stri : dicioo) {
                    if (stri.indexOf(letras[pog]) == 0 || stri.indexOf(letras[pog].toLowerCase()) == 0) {
                        arvore.insert(letras[pog]);
                        arvore.addPalavra(stri);
                    }
                    
                }
                PalavrasPos.add(pog, palavrasTemp.toString());
                palavrasTemp.clear();
                pog++;
                
            } while (pog < letras.length);
            
            ArrayList<String> buscador = new ArrayList<>();
            int skr = 0;
            int opc = 0;
            String leterOpc;
            while (skr == 0) {
                System.out.println("De Acordo com a árvore abaixo, Escolha: ");
                arvore.printAVLTree();
                System.out.println("1 - Buscar palavras");
                System.out.println("2 - Exibir palavras");
                System.out.println("3 - Excluir Letras");
                System.out.println("4 - Sair");
                opc = entrada.nextInt();
                
                if (opc == 1) {
                    int qnt;
                    String palavra;
                    System.out.println("Digite a letra que deseja buscar");
                    leterOpc = entrada.next();
                    System.out.println("Quantas palavras deseja buscar?");
                    qnt = entrada.nextInt();
                    qnt += 1;
                    for (int cont = 0; cont < letras.length; cont++) {
                        if (arvore.contains(letras[cont]) == true && letras[cont].equalsIgnoreCase(leterOpc)) {
                            for (int i = 1; i < qnt; i++) {
                                System.out.println("Digite a " + i + "° palavra");
                                palavra = entrada.next();
                                buscador.add(palavra);
                            }
                            for (int i = 0; i < buscador.size(); i++) {
                                
                                if (PalavrasPos.get(cont).contains(buscador.get(i))) {
                                    System.out.println("A palavra: " + buscador.get(i) + ", existe!");
                                } else {
                                    System.out.println("A palavra: " + buscador.get(i) + ", não existe! ");
                                }
                            }
                            buscador.clear();
                            System.out.println("\n");
                            try {
                                Thread.sleep(3);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(treeDic.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                } else if (opc == 2) {
                    String opcL;
                    System.out.println("De qual letra quer que as palavras sejam exibidas?");
                    opcL = entrada.next();
                    for (int cont = 0; cont < letras.length; cont++) {
                        if (arvore.contains(letras[cont]) == true && letras[cont].equalsIgnoreCase(opcL)) {
                            System.out.println(PalavrasPos.get(cont));
                            }
                    }
                }
                        else if(opc == 3){
                            System.out.println("Informe a letra a ser removida: ");
                            String letra = entrada.next();
                            for (int i = 0; i < letras.length; i++) {
                             if (arvore.contains(letras[i]) == true && letras[i].equalsIgnoreCase(letra)) {
                                 PalavrasPos.remove(i);
                                 arvore.delete(letra.toUpperCase());
                                 arvore.printAVLTree();
                             }
                            
                            }
                            System.out.println("Letra Removida!");
                        }else if(opc == 4){
                    System.out.println("Saiu :(");
                    System.exit(0);
            }
            }
            }catch (Exception err) {
            System.out.println(err);
        
            }
         }
    }
