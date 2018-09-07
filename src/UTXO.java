public class UTXO {
  private String user;
  private long amount;
  private boolean spent;

  @Override
  public String toString() {
    return "[userName=" + user + ", amount=" + amount + "]";
  }

  public UTXO(String user, long amount) {
    this.user = user;
    this.amount = amount;
    this.spent = false;
  }

  public String getUser() {
    return user;
  }

  public long getAmount() {
    return amount;
  }

  public boolean isSpent() {
    return spent;
  }
  
  public void setSpent(boolean isSpent) {
    this.spent = isSpent;
  }
}

