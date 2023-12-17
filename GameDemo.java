import java.util.Random;

public class GameDemo{

 public static void Shuffle(GameDeck k, int x){
   Random rd = new Random();

   int z = 0;
   int y = 0;   
   int tempnum ;
   String tempcolor ;

   for(int i = 0; i<x; i++){
       z = rd.nextInt(40);
       y = rd.nextInt(40);

      tempcolor = k.getCardcolor(z);
      k.setCardcolor(z, k.getCardcolor(y));
      k.setCardcolor(y, tempcolor);


      tempnum = k.getCardnum(z);
      k.setCardnum(z, k.getCardnum(y));
      k.setCardnum(y, tempnum);
   }
 }
  public static void main(String[] args){
    Random rd = new Random();	  
    GameDeck x = new GameDeck();
    Shuffle(x, 50);

    // test gamedeck
    System.out.println("Game Deck: ");	
    for(int i = 0; i<40; i++){
      System.out.println(x.getCardcolor(i) + x.getCardnum(i)); 
      } 

    int[] plNum = new int[10];
          //player's card deck's numbers
    String[] plColor = new String[10];
         //player's card deck's colors
    for(int i = 0; i<5; i++){
      plNum[i] = x.getCardnum(i);
      plColor[i] = x.getCardcolor(i);
      }

    int temp;
    String color;   
    int sign;

    for(int i = 0; i<3; i++){
        temp = rd.nextInt(40);
        color = x.getCardcolor(temp);
        plColor[i+5] = color;
        temp = rd.nextInt(6) + 1;
	sign = rd.nextInt(2);
	if(sign == 0)  plNum[i+5] = temp;
	else plNum[i+5] = (-1)*temp;
    }
    int chance = rd.nextInt(10);
    if(chance<=8){
      for(int i = 0; i<2; i++){
        temp = rd.nextInt(40);
        color = x.getCardcolor(temp);
        plColor[i+8] = color;
        temp = rd.nextInt(6) + 1;
	sign = rd.nextInt(2);
	if(sign == 0)  plNum[i+8] = temp;
	else plNum[i+8] = (-1)*temp;
      }
    }
    else{
        plColor[8] = "flip card";
	plNum[8] = -1 ;
	plColor[9] = "double card";
	plNum[9] = -2; 
    }
    

    int[] cpNum = new int[10];
    String[] cpColor = new String[10];
    int j = 0;
    for(int i = 39; i>34; i--){	    
       cpNum[j]= x.getCardnum(i);
       cpColor[j]= x.getCardcolor(i);
       j++;
       }
    for(int i = 0; i<3; i++){
        temp = rd.nextInt(40);
        color = x.getCardcolor(temp);
        cpColor[i+5] = color;
        temp = rd.nextInt(6) + 1;
	sign = rd.nextInt(1);
	if(sign == 0)  cpNum[i+5] = temp;
	else cpNum[i+5] = (-1)*temp;
   }
    chance = rd.nextInt(10);
    if(chance<=8){
      for(int i = 0; i<2; i++){
        temp = rd.nextInt(40);
        color = x.getCardcolor(temp);
        cpColor[i+8] = color;
        temp = rd.nextInt(6) + 1;
	sign = rd.nextInt(2);
	if(sign == 0)  cpNum[i+8] = temp;
	else cpNum[i+8] = (-1)*temp;
      }
    }
    else{
        cpColor[8] = "flip card";
	cpNum[8] = -1 ;
	cpColor[9] = "double card";
	cpNum[9] = -2; 
    }

    //test player decks
   System.out.println("PLAYER HAND");
   for(int i = 0; i<10; i++){
      System.out.println(plColor[i] +" "+ plNum[i]);
   }
   System.out.println("COMPUTER HAND");
   for(int i = 0; i<10; i++){
     System.out.println(cpColor[i] +" "+ cpNum[i]);
   }


    

    
  }
}
