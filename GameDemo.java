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
    Scanner sc = new Scanner(System.in);
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
  int drawncard = 0;
  int turn;
  String outpl = "";
  String outcpu = "";
  String choice = "";
  int sumpl = 0;
  int sumcpu = 0;
  int plscore=0;
  int cpuscore =0;
  boolean loop2 = false;
  boolean loop3= false;
  
  //for(int round = 1; round<20; round++) //loop 0
  	  
  for(turn = 0; turn<9; turn++){ // loop 1
      drawncard = rd.nextInt(30)+5; //since the first and last 5 was given to the players
				   
      for(int q = 0; q<9; q++){ //checking if this card has been drawn before
         if(drawncard == playerboard[q] || drawncard == cpuboard[q]){
             drawncard = rd.nextInt(30)+5; 
         }
      }
      playerboard[turn]= drawncard;

      for(int q = turn; q<(turn+1); q++){
        outpl += x.getCardcolor(q) + " ";
	outpl += String.valueOf(x.getCardnum(q)) + "-";
      }

      System.out.print("Your Board: ");
      System.out.println(outpl.substring(0, outpl.length()-1));

      System.out.print("End or Stand?");
      choice = sc.nextLine(); 

      if(choice.equals("Stand")){
	  System.out.println("You chose to stand. Wait for the computer to stand!");  
	    loop2=true;
	     break; // go to loop 2 where only cpu draws cards
	   }
      else{
	  System.out.println("Computer's turn!");
          drawncard = rd.nextInt(30)+5;

	  for(int q = 0; q<9; q++){ 
              if(drawncard == playerboard[q] || drawncard == cpuboard[q]){
               drawncard = rd.nextInt(30)+5; 
             }
           }

          cpuboard[turn] = drawncard;

	  for(int q = turn; q<(turn+1); q++){
             outcpu += x.getCardcolor(cpuboard[q]) + " ";
     	     outcpu += String.valueOf(x.getCardnum(cpuboard[q])) + "-";
          }

          System.out.print("Computer's Board: ");
          System.out.println(outcpu.substring(0, outcpu.length()-1));
          
	  for(int q = turn; q<9; q++){
             sumcpu += x.getCardnum(cpuboard[q]);  
	  }

	  if(sumcpu>14){
	    System.out.println("Computer chose to stand. Your turn!");
	     loop3=true;
	      break; // go to loop 3 where only player draws cards
	  }
	  else System.out.println("Your turn!");

          }
        
      if(turn==9){ //Round over
	  if(sumpl>sumcpu){ 
		  System.out.println("You won this round!");
	          plscore++;
	  }
	  else if(sumpl<sumcpu){
		  System.out.println("Computer won this round!");
	          cpuscore++;
	  }
	  else{ 
	        System.out.println("Tie!");
	        cpuscore++;
		plscore++;
	  }
      }
      }

      int turn2 = turn+1;

      while(loop2){
          drawncard = rd.nextInt(30)+5;

	  for(int w = 0; w<9; w++){ 
              if(drawncard == playerboard[w] || drawncard == cpuboard[w]){
               drawncard = rd.nextInt(30)+5; 
             }
           }

	  cpuboard[turn2] = drawncard;

	  for(int w = turn2; w<9; w++){
             sumcpu += x.getCardnum(cpuboard[w]);  
	  }

          turn2++; //keeping track of the turns


	  if(sumcpu>15){
		  System.out.println("Computer chose to stand!");
  	          for(int w = turn2; w<(turn2+1); w++){
           	     outcpu += x.getCardcolor(w) + " ";
     	    	     outcpu += String.valueOf(x.getCardnum(w)) + "-";
         	  }
       		  System.out.print("Computer's Board: ");
      		  System.out.println(outcpu.substring(0, outcpu.length()-1));
      		  System.out.print("Your Board: ");
       		  System.out.println(outpl.substring(0, outpl.length()-1));

		  for(int w = 0; w<9; w++){
         	    sumpl += x.getCardnum(playerboard[w]);  
	  	  }

		  if(sumpl>sumcpu){ 
		 	 System.out.println("You won this round!");
	         	 plscore++;
			 break;
		  }
		  else if(sumpl<sumcpu){
			  System.out.println("Computer won this round!");
	        	  cpuscore++;
			  break;
		  }
		  else{ 
	      		System.out.println("Tie!");
	     	        cpuscore++;
	               	plscore++;
			break;
	 	  }
	 }	  
         
      }
      
      int turn3 = turn+1;      
      while(loop3){   
          drawncard = rd.nextInt(30)+5;

	  for(int w = 0; w<9; w++){ 
              if(drawncard == playerboard[w] || drawncard == cpuboard[w]){
               drawncard = rd.nextInt(30)+5; 
             }
           }

	  playerboard[turn3]=drawncard;

          for(int w = turn3; w<(turn3+1); w++){
             outpl += x.getCardcolor(playerboard[w]) + " ";
       	     outpl += String.valueOf(x.getCardnum(playerboard[w])) + "-";
          }


          System.out.print("Your Board: ");
          System.out.println(outpl.substring(0, outpl.length()-1));
          
	  System.out.println("Draw or Stand?");
          choice = sc.nextLine();

	  if(choice.equals("Stand")){
		  System.out.println("You chose to stand!");
       		  System.out.print("Computer's Board: ");
      		  System.out.println(outcpu.substring(0, outcpu.length()-1));
      		  System.out.print("Your Board: ");
       		  System.out.println(outpl.substring(0, outpl.length()-1));

		  for(int w = 0; w<9; w++){
         	    sumpl += x.getCardnum(playerboard[w]);  
	  	  }

		  for(int w = turn3; w<9; w++){
         	    sumcpu += x.getCardnum(cpuboard[w]);  
	  	  }

		  turn3++;

		  if(sumpl>sumcpu){ 
		 	 System.out.println("You won this round!");
	         	 plscore++;
			 break;
		  }
		  else if(sumpl<sumcpu){
			  System.out.println("Computer won this round!");
	        	  cpuscore++;
			  break;
		  }
		  else{ 
	      		System.out.println("Tie!");
	     	        cpuscore++;
	               	plscore++;
			break;
	 	  }
	    
	  }
         }

    
   

    
  }
}
