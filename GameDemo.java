import java.util.Random;

public class GameDemo{
  public static boolean includes(int[] arr, int x){
    int k = arr.length;
    boolean bool = true;
    for(int i = 0; i<k; i++){
      if(arr[i] == x) bool = true;
      else bool= false;   }
    return bool;
  }

 public static boolean contains(String[] arr, String x){
    int k = arr.length;
    boolean bool = true;
    for(int i = 0; i<k; i++){
      if(arr[i] == x) bool = true;
      else bool= false;    }
    return bool;
  }

 public static void Shuffle(GameDeck k, int x){
   Random rd = new Random();
   int z = 0;
   int y = 0;   
   for(int i = 0; i<x; i++){
       z = rd.nextInt(40);
       y = rd.nextInt(40);
      
      int temp = k.getCardnum(z);
      k.setCardnum(z,y);
      k.setCardnum(y, temp);

      String temp2 = k.getCardcolor(z);
      k.setCardcolor(z, k.getCardcolor(y));
      k.setCardcolor(y, temp2);

   }
 }

/*
 public static void Shuffle(GameDeck k){
   Random rd = new Random();

   int[] number = new int[40];
   for(int i = 0; i<number.length; i++){
       number[i] = k.getCardnum(i);    }
   String[] color = new String[40];
   for(int i = 0; i<color.length; i++){
       color[i] = k.getCardcolor(i); }
 

   for(int i = 20; i>0; i--){

	 int x = 0; 
	 while(true){
	     x = rd.nextInt(number.length); 
	    if(includes(number, x) == true && contains(color, k.getCardcolor(x)) == true){ break;} }
	 
	 int y = 0;        
	 while(true){
	      y = rd.nextInt(number.length);
	     if(includes(number, y) == true && contains(color, k.getCardcolor(y)) == true){ break;}}

         if(x==y){
	 while(true){
	     x = rd.nextInt(number.length); 
	    if(includes(number, x) == true && contains(color, k.getCardcolor(x)) == true) {break; }}
	         
	 while(true){
	      y = rd.nextInt(number.length);
	   if(includes(number, y) == true && contains(color, k.getCardcolor(y)) == true){ break;} }}
	 

         int tempnum = k.getCardnum(x);
	 k.setCardnum(x,y);
       	 k.setCardnum(y, tempnum);

	 String tempcolor = k.getCardcolor(x);
         k.setCardcolor(x, k.getCardcolor(y));
	 k.setCardcolor(y, tempcolor);

         
         for(int j = 0; j<40; j++){
            if(j == x || j == y){
	       number[j]=-1;
	       color[j] = "null";}
            else number[j] = k.getCardnum(j);    } 

	 
   }
 }
*/	
  public static void main(String[] args){
    GameDeck x = new GameDeck();
    Shuffle(x, 16);
       for(int i = 0; i<40; i++){
      System.out.println(x.getCardcolor(i) + x.getCardnum(i)); 
    }
    
  }
}
