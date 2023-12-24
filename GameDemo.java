import java.util.Random;
import java.util.Scanner;
import java.util.Formatter;
import java.io.FileWriter;
import java.nio.file.Paths;
import java.io.IOException;

public class GameDemo{
public static void writetofile(String name, int plscore,int cpuscore,String date){
        Scanner reader = null;
        Formatter formatter = null;
        FileWriter fileWriter = null;

        try {
            reader = new Scanner(Paths.get("Scores.txt"));
            int lineCount = 0;
            String[] history = new String[10];

            while (reader.hasNextLine() && lineCount < 10) {
                history[lineCount] = reader.nextLine();
                lineCount++;
            }

            String newGame = "-----" + name + ": " + plscore + ", Computer: " + cpuscore + ", Date: " + date ;
            	    
            if (lineCount == 10) {
                // Shift elements to remove the oldest game
                System.arraycopy(history, 0, history, 0, 9);
		history[9] = newGame;

            }
	    

            // Write the updated history back to the file
            fileWriter = new FileWriter("Scores.txt");
            formatter = new Formatter(fileWriter);
            for (String game : history) {
                if (game != null) {
                    formatter.format("%s", game+"\n");
                }
            }

        } 
      catch (IOException e) {
            System.err.println("Error reading/writing file.");
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (formatter != null) {
                formatter.close();
            }
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    System.err.println("Error closing FileWriter.");
                }
            }
        }	
}
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
	player.setPlNum(9, 2); 
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
	computer.setPlNum(9, 2);  
    } 
 }

  public static int choosecard(int sum, int[] board ,int turn, GameDeck x, PlayerDeck y){
    String str;  
    int card = -1;
    
    if(sum>20){
       String a;	    
       for(int i = 0; i<4; i++){
         str = y.getPlColor(i);
         if(str.equals("flip")){
	      a = "flip";
	      card = i;
	 }
	 else if(y.getPlNum(i)<0){
              card = i;
	  }	 
       }
     }

    else if(sum<20){
      for(int i = 0; i<4; i++){
	      String a = y.getPlColor(i); 
         if(sum+y.getPlNum(i)==20){
		 card = i;
	 }
	 else if(sum+(2*x.getCardnum(board[turn]))==20 && a.equals("double")){
                card = i;
           }	 
      }
     }
    return card;
  }

  public static void main(String[] args){
    Random rd = new Random();	
    Scanner reader =null;
    Scanner s = new Scanner(System.in);
    GameDeck x = new GameDeck();

    /* test gamedeck
    System.out.println("Game Deck: ");	
    for(int i = 0; i<40; i++){
      System.out.println(x.getCardcolor(i) + x.getCardnum(i)); 
      } 
     */
    
   PlayerDeck player = new PlayerDeck(10);
   PlayerDeck computer = new PlayerDeck(10);
/*
    test player decks
   System.out.println("PLAYER HAND");
   for(int i = 0; i<10; i++){
      System.out.println(player.getPlColor(i) +" "+ player.getPlNum(i));
   }
   System.out.println("COMPUTER HAND");
   for(int i = 0; i<10; i++){
     System.out.println(computer.getPlColor(i) +" "+ computer.getPlNum(i));
   }
   */

   PlayerDeck plhand = new PlayerDeck(4);
   PlayerDeck cpuhand = new PlayerDeck(4);


    /*testing the player hands
   System.out.print("YOUR HAND: ");
   for(int j = 0; j<4; j++){
     System.out.print(plhand.getPlNum(j) +" "+plhand.getPlColor(j)+" ");
   }
   System.out.print("\n");

   
   System.out.print("\nCOMPUTER'S HAND: ");
   for(int j = 0; j<4; j++){
     System.out.print(cpuhand.getPlNum(j) +" "+cpuhand.getPlColor(j)+" ");
   }
   player shouldnt know computer's hand lol
   */
   
   

  //starting the actual game 
  int[] playerboard = new int[9]; //playerboard arrays do not show the cards	
  int[] cpuboard = new int[9];  // they are just place holders that point to the gamedeck
  int drawncard = 0;
  int turn;
  String input = "";
  String outpl = "";
  String outcpu = "";
  String choice = "";
  int sumpl = 0;
  int sumcpu = 0;
  int plscore=0;
  int cpuscore =0;
  boolean loop2 = false;
  boolean loop3= false;
  int turncpu = 0;
  int turnpl = 0;
  String date = "";
  String name = "";

  try{ 
	  System.out.println("Enter the date: ");
          date = s.nextLine();    }
  catch(Exception e){
          System.out.println("invalid date");
  }

  try{
	  System.out.println("Enter your name: ");
          name = s.nextLine();    }
  catch(Exception e){
          System.out.println("invalid name");
  }
  
  for(int round = 1; round<20; round++){ 
   Shuffle(x, 50);
   Deal(player, computer, x);

   int i = 0;
   int num;
   while(i<4){
      num = rd.nextInt(10);
      for(int q = 0; q<4; q++){ 
       boolean bool = player.getPlNum(num) == plhand.getPlNum(q) &&  player.getPlColor(num) == plhand.getPlColor(q);
       if(bool){
             num = rd.nextInt(10); 
         } }
      
      plhand.setPlNum(i, player.getPlNum(num));
      plhand.setPlColor(i, player.getPlColor(num));
      i++;
      if(i==4) break;

   }
   i=0;
    while(i<4){
      num = rd.nextInt(10);
      for(int q = 0; q<4; q++){ 
	boolean bool = computer.getPlNum(num) == cpuhand.getPlNum(q) && computer.getPlColor(num) == cpuhand.getPlColor(q);
        if(bool){
             num = rd.nextInt(10); 
         }
      
      cpuhand.setPlNum(i, computer.getPlNum(num));
      cpuhand.setPlColor(i, computer.getPlColor(num));
      i++;
      if(i==4) break;
   }}
	  
    System.out.println("\n                 -ROUND " +round+ "-");
    System.out.println("************************************************\n");
    System.out.print(name.toUpperCase() +" HAND: "); 
    for(int j = 0; j<4; j++){
      System.out.print(plhand.getPlNum(j) +" "+plhand.getPlColor(j)+" ");
    }
    System.out.print("\n");
    
  for(turn = 0; turn<9; turn++){ // loop 1
      drawncard = rd.nextInt(30)+5; //since the first and last 5 was given to the players
				   
      for(int q = 0; q<9; q++){ //checking if this card has been drawn before
         if(drawncard == playerboard[q] || drawncard == cpuboard[q]){
             drawncard = rd.nextInt(30)+5; 
         }
      }
      playerboard[turn]= drawncard;
      sumpl += x.getCardnum(drawncard);

      for(int q = turn; q<(turn+1); q++){
        outpl += x.getCardcolor(playerboard[q]) + " ";
	outpl += String.valueOf(x.getCardnum(playerboard[q])) + " - ";
      }

      System.out.print(name.toUpperCase() + " BOARD: ");
      System.out.println(outpl.substring(0, outpl.length()-2));
      System.out.println(name.toUpperCase() + " SCORE: " +sumpl);

      System.out.print("                    Use Card: ");

      try{
	   reader = new Scanner(System.in);  
	   boolean bool = true; 
	   while(bool){   
	   int lol = reader.nextInt();  
           if((lol-1)>=0 && (lol-1)<4){
	     	 if(plhand.getPlColor(lol-1).equals("double card")){
	             sumpl+= x.getCardnum(playerboard[turn]);
	         }
	         else if(plhand.getPlColor(lol-1).equals("flip card")){
	             sumpl -= 2*x.getCardnum(playerboard[turn]);
	          }
	   else sumpl += plhand.getPlNum(lol-1);

	      outpl+= plhand.getPlColor(lol-1) + " ";
	      outpl+= plhand.getPlNum(lol-1) + " - ";
              System.out.print(name.toUpperCase() + " BOARD: ");
              System.out.println(outpl.substring(0, outpl.length()-2)); 
	      System.out.println(name.toUpperCase() + " SCORE: " + sumpl); 
	      bool = false;
           }
           else if(lol==0) bool = false;
  	   else{
	       System.out.print("                    This card does not exist."); 
	       System.out.println("\n                    Try again.");
	       System.out.print("                    ");

	   }	       	   }}
      catch(Exception e){
          System.out.println("                    Invalid input.");
	  
      }
      finally{
         if(reader!=null) reader = null;;
      }
      
      System.out.print("                    End or Stand? ");
      try{
	 boolean bool = true;     
         while(bool){	 
         choice = s.nextLine();
	 int a = choice.compareToIgnoreCase("stand");
	 int b = choice.compareToIgnoreCase("end");
	 if(a!=0 && b!=0){ 
		 System.out.println("                    Invalid input. Try again");
	         System.out.print("                    ");
	 }
	 else bool = false;
	 }}
      catch(Exception e){
         System.out.println("\n                    Invalid input");
      }
      
      int compare = choice.compareToIgnoreCase("stand");
      if(compare==0){
	  System.out.println("                    You chose to stand.");  
	  System.out.println("                    Wait for the computer to stand!");
	   turncpu += turn +1;
	    loop2=true;
	     break; // go to loop 2 where only cpu draws cards
	   }
      else{
	  System.out.println("                    Computer's turn!");
          drawncard = rd.nextInt(30)+5;

	  for(int q = 0; q<9; q++){ 
              if(drawncard == playerboard[q] || drawncard == cpuboard[q]){
               drawncard = rd.nextInt(30)+5; 
             }
           }

          cpuboard[turn] = drawncard;
          sumcpu += x.getCardnum(cpuboard[turn]); 

          outcpu += x.getCardcolor(cpuboard[turn]) + " ";
     	  outcpu += String.valueOf(x.getCardnum(cpuboard[turn])) + " - ";    
          System.out.print("COMPUTER'S BOARD: ");
          System.out.println(outcpu.substring(0, outcpu.length()-2));	
	  System.out.println("COMPUTER'S SCORE: " +sumcpu);  

	  int card = choosecard(sumcpu, cpuboard, turn, x, cpuhand);
	  if(card>0){
	      System.out.println("                    Computer used a card from it's hand.");
              String str = cpuhand.getPlColor(card);
	      if(str.equals("double")){
	         sumcpu+= x.getCardnum(cpuboard[turn]);
	      }
	      else if(str.equals("flip")){
	         sumcpu -= 2*x.getCardnum(cpuboard[turn]);
	      } 
	      else sumcpu += cpuhand.getPlNum(card); 

	      outcpu+= cpuhand.getPlColor(card) + " ";
	      outcpu+= cpuhand.getPlNum(card) + " - ";
              System.out.print("COMPUTER'S BOARD: ");
              System.out.println(outcpu.substring(0, outcpu.length()-2));
	      System.out.println("COMPUTER'S SCORE: " +sumcpu);  

              cpuhand.setPlColor(card, "null");
	      cpuhand.setPlNum(card, 0);	 
	  } 
	  
	  
	  if(sumcpu>15){
	    System.out.println("                    Computer chose to stand. Your turn!");
	     loop3=true;
	       turnpl += turn+1;
	         break; // go to loop 3 where only player draws cards
	  }
	  else{
	     System.out.println("                    Your turn!");
	      	    }

          }
       
      if(turn==9){ //Round over
	 if(sumpl<=20&& sumcpu<=20){ 
	      if(sumpl>sumcpu){
		  System.out.println("         You won this round!");
	          plscore++;  
	          break;   }
	      else if(sumpl<sumcpu){
		  System.out.println("         Computer won this round!");
	          cpuscore++;
                  break;   }
	       else{ 
		  System.out.println("         Tie!");
	          cpuscore++;
	          plscore++;
                  break; }
	  }

	  else if(sumpl<=20 && sumcpu>20){
	     System.out.println("         You won this round!");
	     plscore++;
	     break;  }
	  else if(sumpl>20 && sumcpu<=20){
	     System.out.println("         Computer won this round!");
	     cpuscore++;		  
	     break; }
	  else if(sumcpu>20 && sumpl>20){
              System.out.println("         No one wins :c ");
	      break; 	  }



      }
      }


      while(loop2){
          drawncard = rd.nextInt(30)+5;

	  for(int w = 0; w<9; w++){ 
              if(drawncard == playerboard[w] || drawncard == cpuboard[w]){
               drawncard = rd.nextInt(30)+5; 
             }
           }

	  cpuboard[turncpu] = drawncard;
	  sumcpu += x.getCardnum(cpuboard[turncpu]);
          outcpu += x.getCardcolor(drawncard) + " ";
	  outcpu += String.valueOf(x.getCardnum(drawncard)) + " - ";
              System.out.print("COMPUTER'S BOARD: ");
              System.out.println(outcpu.substring(0, outcpu.length()-2));
	      System.out.println("COMPUTER'S SCORE: " +sumcpu);  

	  int card = choosecard(sumcpu, cpuboard, turncpu, x, cpuhand);
      	  if(card>0){
	      System.out.println("                    Computer used a card from it's hand.");	  
	      cpuboard[turn+1] = -1*card;
	      if(cpuhand.getPlColor(card).equals("double")){
	         sumcpu+= x.getCardnum(cpuboard[turncpu]);
	      }
	      else if(cpuhand.getPlColor(card).equals("flip")){
	         sumcpu -= 2*x.getCardnum(cpuboard[turncpu]);
	      }
	      else sumcpu += cpuhand.getPlNum(card);

	      outcpu+= cpuhand.getPlColor(card) + " ";
	      outcpu+= cpuhand.getPlNum(card) + " - ";
              System.out.print("COMPUTER'S BOARD: ");
              System.out.println(outcpu.substring(0, outcpu.length()-2));
              System.out.println("COMPUTER'S SCORE: " +sumcpu);  
	 
	  }
 
	  if(turncpu==8){ 
		  System.out.println("                    Computer chose to stand!");
  	          for(int w = turncpu; w<(turncpu+1); w++){
           	     outcpu += x.getCardcolor(w) + " ";
     	    	     outcpu += String.valueOf(x.getCardnum(w)) + " - ";
         	  }
       		  System.out.print("COMPUTER'S BOARD: ");
      		  System.out.println(outcpu.substring(0, outcpu.length()-2));
		  System.out.println("COMPUTER'S SCORE: " +sumcpu);  
      		  System.out.print(name.toUpperCase() + " BOARD: ");
       		  System.out.println(outpl.substring(0, outpl.length()-2));
		  System.out.println(name.toUpperCase() + " SCORE: " +sumpl);  


	 if(sumpl<=20 && sumcpu<=20){ 
	      if(sumpl>sumcpu){
		  System.out.println("         You won this round!");
	          plscore++;  
	          break;   }
	      else if(sumpl<sumcpu){
		  System.out.println("         Computer won this round!");
	          cpuscore++;
                  break;   }
	       else{ 
		  System.out.println("         Tie!");
	          cpuscore++;
	          plscore++;
                  break; }
	  }

	  else if(sumpl<=20 && sumcpu>20){
	     System.out.println("         You won this round!");
	     plscore++;
	     break;  }
	  else if(sumpl>20 && sumcpu<=20){
	     System.out.println("         Computer won this round!");
	     cpuscore++;		  
	     break; }
	  else if(sumcpu>20 && sumpl>20){
              System.out.println("         No one wins :c ");
	      break; 	  }
    
	  }

          else{
	  if(sumcpu>15){
		  System.out.println("                    Computer chose to stand!");
       		  System.out.print("COMPUTER'S BOARD: ");
      		  System.out.println(outcpu.substring(0, outcpu.length()-2));
		  System.out.println("COMPUTER'S SCORE: " +sumcpu);  
      		  System.out.print(name.toUpperCase() + " BOARD: ");
       		  System.out.println(outpl.substring(0, outpl.length()-2));
		  System.out.println(name.toUpperCase() + " SCORE: " +sumpl); 
	
	 if(sumpl<=20 && sumcpu<=20){ 
	      if(sumpl>sumcpu){
		  System.out.println("         You won this round!");
	          plscore++;  
	          break;   }
	      else if(sumpl<sumcpu){
		  System.out.println("         Computer won this round!");
	          cpuscore++;
                  break;   }
	       else{ 
		  System.out.println("         Tie!");
	          cpuscore++;
	          plscore++;
                  break; }
	  }

	  else if(sumpl<=20 && sumcpu>20){
	     System.out.println("         You won this round!");
	     plscore++;
	     break;  }
	  else if(sumpl>20 && sumcpu<=20){
	     System.out.println("         Computer won this round!");
	     cpuscore++;		  
	     break; }
	  else if(sumcpu>20 && sumpl>20){
              System.out.println("         No one wins :c ");
	      break; 	  }
	 }
	  }
      }
            
      while(loop3){   
          drawncard = rd.nextInt(30)+5;

	  for(int w = 0; w<9; w++){ 
              if(drawncard == playerboard[w] || drawncard == cpuboard[w]){
               drawncard = rd.nextInt(30)+5; 
             }
           }

	  playerboard[turnpl]=drawncard;
	  sumpl += x.getCardnum(drawncard);

          for(int w = turnpl; w<(turnpl+1); w++){
             outpl += x.getCardcolor(playerboard[w]) + " ";
       	     outpl += String.valueOf(x.getCardnum(playerboard[w])) + "-";
          }

          
          System.out.print(name.toUpperCase() + " BOARD: ");
          System.out.println(outpl.substring(0, outpl.length()-1));
	  System.out.println(name.toUpperCase() + " SCORE: " +sumpl); 

          System.out.print("                    Use Card: ");
      try{ 
	   reader = new Scanner(System.in);   
	   boolean bool = true;   
	   while(bool){   
	   int lol = reader.nextInt();  
           if((lol-1)>=0 && (lol-1)<4){
	      playerboard[turn+1]= -1*(lol-1); // - means its a card from players hand
	      if(plhand.getPlColor(lol-1).equals("double card")){
	         sumpl+= x.getCardnum(playerboard[turn]);
	      }
	      else if(plhand.getPlColor(lol-1).equals("flip card")){
	         sumpl -= 2*x.getCardnum(playerboard[turn]);
	      }
	      else sumpl += plhand.getPlNum(lol-1);

	      outpl+= plhand.getPlColor(lol-1) + " ";
	      outpl+= plhand.getPlNum(lol-1) + " - ";
              System.out.print(name.toUpperCase() + " BOARD: ");
              System.out.println(outpl.substring(0, outpl.length()-2));  
	      System.out.println(name.toUpperCase() + " SCORE: " +sumpl); 
	      bool = false;
           }
           else if(lol==0) bool = false;
  	   else{
	        System.out.println("                    This card does not exist.");
	        System.out.println("                    Try again.");
		System.out.print("                    ");
	   }
	  
	   }}
      catch(Exception e){
          System.out.println("                    Invalid input.");
      }
      finally{
          if(reader!=null) reader = null;
      }
       
	  System.out.println("                    Draw or Stand?");
          System.out.print("                    ");

      try{
	 boolean bool = true;
         while(bool){	 
         choice = s.nextLine();
	 int a = choice.compareToIgnoreCase("stand");
	 int b = choice.compareToIgnoreCase("draw");
	 if(a!=0 && b!=0){
		 System.out.println("                    Invalid input. Try again");
	         System.out.print("                    ");
         }
	 else bool = false;
	 }}
      catch(Exception e){
         System.out.println("\n                    Invalid input");
      }
      
      int compare = choice.compareToIgnoreCase("stand");
      if(compare==0){
                  System.out.println("                    You chose to stand. Round over");
	 if(sumpl<=20&& sumcpu<=20){ 
	      if(sumpl>sumcpu){
		  System.out.println("         You won this round!");
	          plscore++;  
	          break;   }
	      else if(sumpl<sumcpu){
		  System.out.println("         Computer won this round!");
	          cpuscore++;
                  break;   }
	       else{ 
		  System.out.println("         Tie!");
	          cpuscore++;
	          plscore++;
                  break; }
	  }

	  else if(sumpl<=20 && sumcpu>20){
	     System.out.println("         You won this round!");
	     plscore++;
	     break;  }
	  else if(sumpl>20 && sumcpu<=20){
	     System.out.println("         Computer won this round!");
	     cpuscore++;		  
	     break; }
	  else if(sumcpu>20 && sumpl>20){
              System.out.println("         No one wins :c ");
	      break; 	  }

 
         }
     	 
        else turnpl++;	
      }
  System.out.println("\n************************************************"); 
  System.out.print("\n" +name+ " score: " + plscore);
  System.out.println("       Cpu score: " + cpuscore);

  if(plscore==3){ 
	  System.out.println("            You won!");
          break;
  }
  else if(cpuscore==3){ 
	  System.out.println("             Computer won!");
          break;
  }
 
  outpl = "";
  outcpu = "";
  turn = 0;
  turncpu = 0;
  turnpl = 0;
  sumcpu = 0;
  sumpl = 0;
  loop3 = false;
  loop2 = false;

  }

  writetofile(name, plscore, cpuscore, date);

    
  }
   }
