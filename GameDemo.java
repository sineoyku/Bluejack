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
   
   //Starting the actual game
   Scanner sc = new Scanner(System.in);
   int[] playerboard = new int[9];
   int[] cpuboard = new int[9];
   int card ;
   String choice ;
   int playersum = 0;
   int cpusum = 0;
   int playerscore =0;
   int cpuscore = 0;
   int turn = 1;;
   boolean game = true;
   while(game){
  
   card = rd.nextInt(30) + 5;
   playerboard[turn-1] = card; 
   
   System.out.print("Your board: ");
    for(i=0;i<(turn); i++){
       System.out.print(x.getCardcolor(playerboard[i]) + " ");
       System.out.print(x.getCardnum(playerboard[i]) + "-");
       
   }
   System.out.println("\nEnd or Stand?");
   choice = sc.nextLine();
   if(choice.equals("End")){
       System.out.println("You chose to end your turn, computer's turn!");	   
       card = rd.nextInt(30) + 5;

       //checking if this card has been drawn before
       for(int j= 0; j<playerboard.length; j++){
          if(card == playerboard[j] || card == cpuboard[j]) card = rd.nextInt(30) + 5;
          else continue;	  
       }

       cpuboard[turn-1] = card;
       System.out.print("Computer board: ");

       for(i= 0; i<(turn); i++){
          System.out.print(x.getCardcolor(cpuboard[i]) + " ");
	  System.out.print(x.getCardnum(cpuboard[i]) + "-");
       }
       int chance = rd.nextInt(2);
       if(chance == 0) {
	       System.out.println("\nYour turn!");
                
       }
       else {
	       System.out.println("\nComputer chose to stand. Your turn!");
             
       }
   }
   else{
    	   int w = 1;	
       while(true){	      
       System.out.println("You chose to stand, computer's turn!");	   
       for(i= 0; i<w; i++){	   
       card = rd.nextInt(30) + 5;

       //checking if this card has been drawn before
       for(int j= 0; j<playerboard.length; j++){
          if(card == playerboard[j] || card == cpuboard[j]) card = rd.nextInt(30) + 5;
          else continue;	  
       }

       cpuboard[turn-1] = card;
       System.out.print("Computer board: ");

       for(int q = 0; q<i; q++){
          System.out.print(x.getCardcolor(cpuboard[i]) + " ");
	  System.out.print(x.getCardnum(cpuboard[i]) + "-");
       }
       for(i = 0; i<turn; i++){
       cpusum += x.getCardnum(cpuboard[i]);
       }
       if(cpusum>= 20 || (cpusum+=5)==20){ // both of the players chose to stand, end of this turn
          System.out.println("Computer chose to stand.");
          for(i = 0; i<turn; i++){
             playersum += x.getCardnum(playerboard[i]);
         }
	 System.out.println("Your score: " +playersum+ "Computer's Score: " +cpusum);
	 if(playersum>cpusum){
		 System.out.println("You won this turn!");
	         playerscore++;
			 }
	 if(cpusum>playersum){
		 System.out.println("Computer won this turn!");
	         cpuscore++;
		 	 }
	 else {
		 System.out.println("Tie!");
	         playerscore++;
		 cpuscore++;

	 }
	 break;
       }
       else w++;
              
    }   
    }
   }   
  }


   

    
  }
}
