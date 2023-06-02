package org.example;

public class TransactionInput {
    public String transactionOutputId;
    public TransactionOutput UTXO;

    public TransactionInput(String idTransactionOutput) {this.transactionOutputId = transactionOutputId;}
}
