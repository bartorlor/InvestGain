import java.util.ArrayList;

public class Invest {
  public int buyQty;
  public int sellQty;
  // public double buyAmount;
  // public double sellAmount;
  public double gainByPrice;
  public double gainByAmount;
  public String company;
  public Remainder remained = new Remainder();
  public double buyAmount;
  public double soldAmount;
  public ArrayList<Double> prices = new ArrayList<Double>();

  public Invest(String company) {
    this.company = company;
    this.buyQty = 0;
    sellQty = 0;
    gainByPrice = 0;
    gainByAmount = 0 ;
    buyAmount = 0;
    soldAmount = 0;

  }

  public void processAmount(double amount){
    gainByAmount += amount;
    if(amount < 0){
      buyAmount += amount; 
    }else{
      soldAmount += amount; 
    }
  
  }
  public void process(boolean isNewBuy, int newQty, double newPrice) {
    if (newQty > 0) {
      buyQty += newQty;
    } else {
      sellQty += newQty;
    }
    prices.add(newPrice);
    
    if (remained.isBuy() && !isNewBuy || !remained.isBuy() && isNewBuy) {// sell and buy
      int minQty = 0;
       if (Math.abs(remained.qty) >= Math.abs(newQty)) {
       minQty = newQty;
       } else {
       minQty = remained.qty;
       }
      //minQty = Math.min(Math.abs(remained.qty), Math.abs(newQty));
      if (isNewBuy) {
        gainByPrice += -minQty * (remained.price - newPrice);
      } else {
        gainByPrice += -minQty * (newPrice - remained.price);
      }

      int i = 44;

    }

    if (remained.qty + newQty != 0 && remained.isBuy() && isNewBuy) {
      remained.price = (remained.qty * remained.price + newQty * newPrice) / (remained.qty + newQty);
    } 
    remained.qty = remained.qty + newQty;
  }

  public String toString() {
    String str = "" + company;
    str += ": ";
    str +=  gainByPrice >= 0 ? "gain" : "loss";
    str += ": " + String.format("%.2f", gainByPrice);
    str += "," + remained.toString();
    str += " buyQty: " + buyQty;
    str += " sellQty: " + sellQty;
    str += " prices: " + prices.toString();

    return str;

  }
  public static String getReportTitle(){
    return String.format("%8s,%10s,%10s,%8s,%12s,%6s,%6s,%6s","Company","BuyAmount","SoldAmount","Gained","GainedAmount","Remain","Bought","Sold");
  }  
  public String getReport(){
    return String.format("%8s,%10.2f,%10.2f,%8.2f,%12.2f,%6d,%6d,%6d",company,buyAmount,soldAmount,gainByPrice,gainByAmount,remained.qty,buyQty,sellQty);
  }

  class Remainder {
    int qty;
    double price;

    public boolean isBuy() {
      return (qty >= 0);
    }

    public String toString() {

      String str = "";
      str += isBuy() ? "remained: " : " selled this year ";
      str += String.format("qty:%d , price:%.2f", qty, price);
      return str;
    }
  }
}
