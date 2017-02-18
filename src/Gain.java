import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import Utils.Utils;
//TVJ7MT9G2FC49B7H3BVB8PYYR
public class Gain {

  public ArrayList<Trans> transactions = new ArrayList<Trans>();
  public ArrayList<Invest> invests = new ArrayList<Invest>();
  public ArrayList<String> companys = new ArrayList<String>();

  public void buildList() {
    try {
      System.out.println("2015 tax year Ren's margin (380081422) Acount Stock records:");
      //System.out.println("2015 tax year 380130432 Acount Stock records:");
      System.out.println(Trans.getHeaders());
      // find path //C:\work\TAX\2015Tax\VB477\vbRen.txt
      BufferedReader reader = new BufferedReader(new FileReader(
          //"C:\\work\\TAX\\2015Tax\\VB477\\vbRen.txt"));
          "C:\\work\\TAX\\2015Tax\\vb636268\\ShaMarginAccount.txt"));
      int index =0;
      while (true) {
        index++;
        String line = reader.readLine();
        if (line == null) {
          break;
        }
        if(line.trim().length() ==0 ){
          Utils.println("line "+ index+ "");
          continue;
        }
        //Utils.println("line "+ index+ ":" + line);
        Trans trans = new Trans(line);
        Utils.println(""+ index+ ":" + trans.toString());
        transactions.add(trans);
      }
      reader.close();
      Utils.println("read done ........................... ");
//      System.out.println("Lines: " + transactions.size());
//      for (Trans line : transactions) {
//        System.out.println(line);
//      }

    } catch (Exception e) {
      Utils.println("error:" ,e);
    }
  }

  public void process() {

    for (Trans trans : transactions) {
      if(!companys.contains(trans.company)){
        companys.add(trans.company); 
        invests.add(new Invest(trans.company));
      }
      //Utils.println(trans.toString());
      Invest invest = getInvestByCompany(trans.company);
      if(trans.isForNextYear){
        continue;
      }
      invest.process(trans.isBuy,trans.qty,trans.price);
      invest.processAmount(trans.amount);
    }
    Utils.println("process done........................");
  }
  public void showResult(){
    int index = 0;
    System.out.println(index++ + ":" +Invest.getReportTitle());
   for(Invest invest: invests) {
     System.out.println(index++ + ":" +invest.getReport());
   }
   System.out.println(String.format("%10s %31.2f, %11.2f","Total:",getGain(),getGainAmount()));
   
  }
  public double getGain(){
    double amount = 0;
    for(Invest invest:invests){
      amount += invest.gainByPrice;
    }
    return amount;
  }
  public double getGainAmount(){
    double amount = 0;
    for(Invest invest:invests){
      amount += invest.gainByAmount;
    }
    return amount;
  }  
public Invest getInvestByCompany(String company){
     for(Invest invest : invests) {
       if(company.equalsIgnoreCase(invest.company)){
          return invest; 
       } 
     
     }
     return null;
  }
  public static void main(String[] args) {
    Gain gain = new Gain();
    gain.buildList();
    gain.process();
    gain.showResult();
    

  }
}
