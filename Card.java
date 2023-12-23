public class Card{
  private String color;
  private int value;

  public Card(String a, int x){
    color = a;
    value = x;
  }

  public void setColor(String a){color=a;} 
  public void setValue(int x){value=x;}
  public String getColor(){return color;}
  public int getValue(){return value;}

}
