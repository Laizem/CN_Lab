import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
public class sort{
   public static void main(String[] args){
      List<int[]>frame = new ArrayList<>();
      System.out.println("Enter the number of frames>>");
      Scanner scanner = new Scanner(System.in);
      int n=scanner.nextInt();
      for(int i=0;i<n;i++){
         Random random = new Random();
         int seqNum=random.nextInt(10000)+1;
         System.out.printf("Enter the data for %dth frame >>\n",i+1);
         int data=scanner.nextInt();
         frame.add(new int[]{seqNum,data});
      }
      System.out.println("\n\nBefore Sorting>>\n");
      for(int[] i : frame){
         System.out.printf("Seq Num-> %d, Data->%d\n",i[0],i[1]);

}
      frame = sortFrame(frame);
      System.out.println("\n\nAfter Sorting>>");
      for(int[] i : frame){
         System.out.printf("Seq Num-> %d, Data->%d\n",i[0],i[1]);
      }
   }
   public static List<int[]> sortFrame(List<int[]> frame){
      frame.sort((a,b) -> Integer.compare(a[0],b[0]));
      return frame;
   }
}
