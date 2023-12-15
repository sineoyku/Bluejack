import java.util.Random;

public class GameDeck{
  private int[] cardnum = new int[40];
  private String[] cardcolor = new String[40];

 public GameDeck(){
   for(int i = 0; i<10; i++){
     cardnum[i] = i+1;   }
   for(int i = 10; i<40; i++){
     if(i%10==0) cardnum[i]= 1;	   
       else cardnum[i] = i%10+1;  }

   for(int i = 0; i<10; i++){ cardcolor[i]= "blue";}
   for(int i = 10; i<20; i++){ cardcolor[i] = "yellow";}
   for(int i = 20; i<30; i++){ cardcolor[i] = "red";}
   for(int i = 30; i<40; i++){ cardcolor[i] = "green";} 
 }
 public void setCardnum(){
   for(int i = 0; i<10; i++){
     cardnum[i] = i+1;   }
   for(int i = 10; i<40; i++){
     if(i%10==0) cardnum[i]= 1;	   
       else cardnum[i] = i%10+1;  }
 }
 public void setCardnum(int a, int b){cardnum[a] = cardnum[b]; }
 public void setCardcolor(){
   for(int i = 0; i<10; i++){ cardcolor[i]= "blue";}
   for(int i = 10; i<20; i++){ cardcolor[i] = "yellow";}
   for(int i = 20; i<30; i++){ cardcolor[i] = "red";}
   for(int i = 30; i<40; i++){ cardcolor[i] = "green";}
  }
 public void setCardcolor(int a, String b){ cardcolor[a]=b; }
 public int[] getCardnumarr(){return cardnum;}
 public String[] getCardcolorarr(){return cardcolor;}
 public int getCardnum(int x){return cardnum[x];}
 public String getCardcolor(int x){return cardcolor[x];}
 
 
}
