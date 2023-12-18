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
 public static void Deal(PlayerDeck player, PlayerDeck computer, GameDeck x){
  Random rd = new Random();	 
  for(int i = 0; i<5; i++){
      player.setPlNum(i, x.getCardnum(i));
      player.setPlColor(i, x.getCardcolor(i));
      }

    int temp;
    String color;   
    int sign;

    for(int i = 0; i<3; i++){
        temp = rd.nextInt(40);
        color = x.getCardcolor(temp);
        player.setPlColor(i+5,color);
        temp = rd.nextInt(6) + 1;
	sign = rd.nextInt(2);
	if(sign == 0)  player.setPlNum(i+5 , temp);
	else player.setPlNum(i+5, (-1)*temp);
    }
    int chance = rd.nextInt(10);
    if(chance<=8){
      for(int i = 0; i<2; i++){
        temp = rd.nextInt(40);
        color = x.getCardcolor(temp);
        player.setPlColor(i+8, color);
        temp = rd.nextInt(6) + 1;
	sign = rd.nextInt(2);
	if(sign == 0)  player.setPlNum(i+8, temp);
	else player.setPlNum(i+8, (-1)*temp);
      }
    }
    else{
        player.setPlColor(8, "flip card");
	player.setPlNum(8, -1) ;
	player.setPlColor(9, "double card");
	player.setPlNum(9, -2); 
    }
    
 
    int j = 0;
    for(int i = 39; i>34; i--){	    
       computer.setPlNum(j, x.getCardnum(i));
       computer.setPlColor(j, x.getCardcolor(i));
       j++;
       }
    for(int i = 0; i<3; i++){
        temp = rd.nextInt(40);
        color = x.getCardcolor(temp);
        computer.setPlColor(i+5, color);
        temp = rd.nextInt(6) + 1;
	sign = rd.nextInt(1);
	if(sign == 0)  computer.setPlNum(i+5, temp);
	else computer.setPlNum(i+5, (-1)*temp);
   }
    chance = rd.nextInt(10);
    if(chance<=8){
      for(int i = 0; i<2; i++){
        temp = rd.nextInt(40);
        color = x.getCardcolor(temp);
        computer.setPlColor(i+8, color);
        temp = rd.nextInt(6) + 1;
	sign = rd.nextInt(2);
	if(sign == 0)  computer.setPlNum(i+8, temp);
	else computer.setPlNum(i+8, (-1)*temp);
      }
    }
    else{
        computer.setPlColor(8, "flip card");
	computer.setPlNum(8, -1) ;
	computer.setPlColor(9, "double card");
	computer.setPlNum(9, -2);  
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
      
   PlayerDeck player = new PlayerDeck();
   PlayerDeck computer = new PlayerDeck();
   Deal(player, computer, x);

    //test player decks
   System.out.println("PLAYER HAND");
   for(int i = 0; i<10; i++){
      System.out.println(player.getPlColor(i) +" "+ player.getPlNum(i));
   }
   System.out.println("COMPUTER HAND");
   for(int i = 0; i<10; i++){
     System.out.println(computer.getPlColor(i) +" "+ computer.getPlNum(i));
   }


    

    
  }
}
