import java.util.ArrayList;
import java.util.HashMap;

public class Transaction {
  private String transactionID;
  private int inputSize;
  private int outputSize;
  private ArrayList<TxnIdIndexPair> inputTransactions;
  private ArrayList<UTXO> outputTransactions;
  private String transactionString;
  public String userName;
  public String signature;
  public int isGood;
  public int isVerified;

  public String getTransactionString() {
    return transactionString;
  }

  public String getTransactionID() {
    return transactionID;
  }

  public int getInputSize() {
    return inputSize;
  }

  public int getOutputSize() {
    return outputSize;
  }

  public ArrayList<TxnIdIndexPair> getInputTransactions() {
    return inputTransactions;
  }

  public ArrayList<UTXO> getOutputTransactions() {
    return outputTransactions;
  }
  
  public String getName(HashMap<String, Transaction> txnChain, ArrayList<TxnIdIndexPair> inputs) {
	 String username = null;
	 	 
	 for(int i = 0; i < inputs.size(); i++) {
		 String txnId =  inputs.get(i).getTxnId();
		 int idx = inputs.get(i).getIndex();
		 
		 if(txnChain.containsKey(txnId)){
			Transaction t = txnChain.get(txnId);
			UTXO utx  = t.outputTransactions.get(idx);
			
			
			if(utx.isSpent() == true) {
				this.isGood = 1;
			}
			String user_i = utx.getUser();
			
			if(username == null) username = user_i;
			else {
				if(!username.equals(user_i)) {
					this.isGood = 1;
					break;
				}
			}
		 }
		 else {
			 this.isGood = 1;
		 }	 
	 }
	 
	 return username;
  }
  
  public String getUserName() {
	    return userName;
	  }

 
  public Transaction(String transactionString, String txnId, int inputSize, int outputSize,
      ArrayList<TxnIdIndexPair> inputs, ArrayList<UTXO> outputs, String signature, HashMap<String, Transaction> txnChain) {
    this.transactionString = transactionString;
    this.transactionID = txnId;
    this.inputSize = inputSize;
    this.outputSize = outputSize;
    this.inputTransactions = inputs;
    this.outputTransactions = outputs;
    this.signature = signature;
    this.isGood = 0;
    this.userName = getName(txnChain, inputs);
    this.isVerified = 0;
    System.out.println("Transaction signed by " + this.userName);
  }

}
