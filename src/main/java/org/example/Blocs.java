package org.example;

import java.util.ArrayList;
import java.util.Date;

public class Blocs {
    public String hash;
    public String previousHash;
    public long timestamp;
    public int nonce;
    public ArrayList<Transaction> transactions = new ArrayList<>();

    public Blocs(String previousHash) {
        this.previousHash = previousHash;
        this.timestamp = new Date().getTime();
        this.hash = calculateHash();
    }

    public String calculateHash(){
        String input = previousHash + Long.toString(timestamp)+Integer.toString(nonce);
        return StringUtil.applySha256(input);
    }//Le nonce permet de calculer le hash

    public boolean miner(int difficulty){
        String target = new String(new char[difficulty]).replace('\0','0');
        while(!hash.substring(0,difficulty).equals(target)){
            nonce ++;
           hash = calculateHash();
            System.out.println("Minage en cours : nonce = " + nonce + "...");
        }
        System.out.println("Block miné : HASH = " + hash);
        return true;
    }// C'est le mineur qui traite les transaction
}
