package org.example;

import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Main {
    public static int NIVEAU_DE_DIFFICULTE = 2;
    static ArrayList<Blocs> blockChain = new ArrayList<>();

    public static HashMap<String,TransactionOutput> UTXOs = new HashMap<>();

    public static void main(String[] args) {
        Blocs b = new Blocs("0");
        System.out.println("Début du minage");
        if (b.miner(NIVEAU_DE_DIFFICULTE)) {
            blockChain.add(b);
        }

        b = new Blocs(blockChain.get(blockChain.size() - 1).hash);
        if (b.miner(NIVEAU_DE_DIFFICULTE)) {
            blockChain.add(b);
        }

        b = new Blocs(blockChain.get(blockChain.size() - 1).hash);
        if (b.miner(NIVEAU_DE_DIFFICULTE)) {
            blockChain.add(b);
        }

        String json = new GsonBuilder().setPrettyPrinting().create().toJson(blockChain);
        System.out.println(json);

        System.out.println("Blocschain validity : " + verifyBlocschain());

//        System.out.println(StringUtil.getStringFromKey(portefeuille1.clePublique));
//        System.out.println(StringUtil.getStringFromKey(portefeuille1.clePrivee));
//
//        Transaction transaction = new Transaction(
//                portefeuille1.clePublique,
//                portefeuille2.clePublique,
//                50000,
//                new ArrayList<>());
//
//        transaction.generateSignature(portefeuille1.clePrivee);
//
//        System.out.println(Arrays.toString(transaction.signature));
//        System.out.println(transaction.verifySignature());

    }

   //On vérifie si la blockchaine est valide
    public static boolean verifyBlocschain() {
        Object previous;
        Object current;
        for (int i = 0; i < blockChain.size(); i++) {
            current = blockChain.get(i);
            if (i > 0) {
                previous = blockChain.get(i - 1);
                if (!Objects.equals(((Blocs) previous).hash, ((Blocs) current).previousHash)) {
                    return false;
                }
            }
            String calculatedHash = ((Blocs) current).calculateHash();
            if (!Objects.equals(calculatedHash, ((Blocs) current).hash)) {
                return false;
            }
        }
        return true;
    }
}