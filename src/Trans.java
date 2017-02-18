import Utils.Utils;

public class Trans {

  public String date;
  public boolean isBuy;
  public String company;
  public int qty;
  public double price;
  public double amount;
  public boolean isForNextYear =false;

  public Trans(String date, boolean isBuy, String company, int quantity, double price, double amount) {
    super();
    this.date = date;
    this.isBuy = isBuy;
    this.company = company;
    this.qty = quantity;
    this.price = price;
    this.amount = amount;
  }

  public Trans(String line) {
    try{
     
    String[] fields = line.split("\\s+");
    this.date = fields[0];
    this.isBuy = fields[1].equalsIgnoreCase("buy") ? true : false;
    this.company = fields[2];
    this.qty = Integer.parseInt(fields[3]);
    String tmp = fields[4].replace("$", "");
    this.price = Double.parseDouble(fields[4].replace("$", "").replace(",", ""));
    
    this.amount = Double.parseDouble(fields[5].replace("$", "").replace(",", ""));
    if(fields.length > 6){
      this.isForNextYear = fields[6].contains("2016-use") ? true : false;  
    }
    
    
  } catch (Exception e) {
    Utils.println("error:" + Utils.eToStr(e));
  }
  }

  public boolean isSell() {
    return !isBuy;
  }

  public String toString() {
    String type = isBuy ? "buy" : "sell";
   // String str =  
//        "trans: " +date 
 //       + " " +type 
  //      + " "+ company 
  //      + " " + qty
  //      + " " + price
  //      + " " + amount
  //      ;
    String nextYear = isForNextYear ? "Next-Year-use" : "";
    String str = String.format(" %11s, %5s, %10s, %6d, %7.2f, %10.2f, %8s",date , type ,company,qty,price, amount,nextYear );
    return str;
  }

  public static String getHeaders(){
    return String.format(" %13s, %5s, %10s, %6s, %7s, %10s, %8s","date" , "type" ,"company","qty","price", "amount","Next-Year-use");
  }
}
