public class PlayerDeck{
  private int[] plNum;
  private String[] plColor;

  public PlayerDeck(){
     plNum = new int[10];
     plColor = new String[10];     }

  public void setPlNum(int a, int b){ plNum[a]=b; }
  public void setPlColor(int a, String b){ plColor[a] = b; }
  public int getPlNum(int a){ return plNum[a]; }
  public String getPlColor(int a){ return plColor[a]; }
}

