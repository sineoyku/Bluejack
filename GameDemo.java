import java.util.Random;
import java.util.Scanner;

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
    chance = rd.nextInt(10) + 1;
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
      
   PlayerDeck player = new PlayerDeck(10);
   PlayerDeck computer = new PlayerDeck(10);
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
   
   PlayerDeck plhand = new PlayerDeck(4);
   PlayerDeck cpuhand = new PlayerDeck(4);

  
   int i = 0;
   int num;
   while(i<4){
      num = rd.nextInt(10);
      plhand.setPlNum(i, num);
      plhand.setPlColor(i, player.getPlColor(num));
      i++;
      if(i==4) break;

   }
    while(i<4){
      num = rd.nextInt(10);
      cpuhand.setPlNum(i, num);
      cpuhand.setPlColor(i, computer.getPlColor(num));
      i++;
      if(i==4) break;
   }
 
   /* testing the player hands
   for(int j = 0; j<4; j++){
     System.out.println(plhand.getPlNum(j) +" "+plhand.getPlColor(j));
   }
   */
   

  //starting the actual game 
  int[] playerboard = new int[9];
  int[] cpuboard = new int[9];

  int drawncard;
  int turn = 0;
  boolean plturn=true; //player starts the game
  boolean cputurn=false;  //since the cpu deals it
  String out = "";

  for(i= 0; i<9; i++){
     playerboard[i]=-1;
     cpuboard[i]=-1;
  }

  while(true){
	 try{
	     for(i=0; i<9; i++){
	       out += x.getCardcolor(playerboard[i]) +" ";
	       out += String.valueOf(x.getCardnum(playerboard[i])) + "-";
    	     }
	     System.out.print("Your Board: ");
	     System.out.print(out.substring(0, out.length() -1));
	  }
	  catch(Exception e){
	     System.out.println("Game over");
	  }
	  finally{
	    if(playerboard[0]==-1) System.out.println("Starting game");
	  }
     while(plturn){	  
	     
          drawncard = rd.nextInt(30)+5;
          playerboard[turn]=drawncard;
   
          for(i=0;i<(turn+1);i++){ //checking if this card has been drawn before
             if(playerboard[i]==playerboard[turn] || cpuboard[i]==playerboard[turn]){
	       drawncard = rd.nextInt(30)+5;
                 playerboard[turn]=drawncard;
       	    }    
          }
	  for(int j=0; j<turn+1; j++){
	       out += x.getCardcolor(playerboard[j]) +" ";
	       out += String.valueOf(x.getCardnum(playerboard[j])) + "-";
          }
	  System.out.print("Your Board: ");
	  System.out.print(out.substring(0, out.length() -1));
	  try{
	  for(int j=0; j<turn+1; j++){
	       out += x.getCardcolor(cpuboard[j]) +" ";
	       out += String.valueOf(x.getCardnum(cpuboard[j])) +"-";
    	  }
	  System.out.print("Computer Board: ");
	  System.out.print(out.substring(0, out.length() -1));
	  }
	  catch(Exception e){
	    System.out.println("Computer Board: -");
	  }
	  finally{
	    out="";
	  }
     plturn=false;
     cputurn=true;
    }
    while(cputurn){
          drawncard = rd.nextInt(30)+5;
          cpuboard[turn]=drawncard;
   
          for(i=0;i<(turn);i++){ //checking if this card has been drawn before
             if(playerboard[i]==playerboard[turn] || cpuboard[i]==playerboard[turn]){
	       drawncard = rd.nextInt(30)+5;
                 cpuboard[turn]=drawncard;
       	     }    
          }
	  for(i=0; i<turn+1; i++){
	       out += x.getCardcolor(playerboard[i]) + " ";
	       out += String.valueOf(x.getCardnum(playerboard[i])) + "-";
          }
	  System.out.print("Your Board: ");
	  System.out.print(out.substring(0, out.length() -1));
	  for(i=0; i<turn+1; i++){
	       out += x.getCardcolor(cpuboard[i]) + " ";
	       out += String.valueOf(x.getCardnum(cpuboard[i])) + "-";
    	  }
	  System.out.print("Computer Board: ");
	  System.out.print(out.substring(0, out.length() -1));
          cputurn=false;
          plturn=true;	  

   }
  }

   

    
  }
}
