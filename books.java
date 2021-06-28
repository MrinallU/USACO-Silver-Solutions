import java.util.*; 
public class books{

    public static void main(String[] args) {
      Scanner sc = new Scanner(System.in); 
      int N = sc.nextInt(); int t = sc.nextInt(); 
      int [] arr = new int [N]; 
      Arrays.sort(arr); int currTime = 0; int ans = 0; 
      for (int i = 0; i < arr.length; i++) {
      	currTime += arr[i]; 
	if(currTime > t){
         break;  
      }else{
        ans++; 
      }	      

    }
    	System.out.println(ans);
	sc.close();
  }
}
