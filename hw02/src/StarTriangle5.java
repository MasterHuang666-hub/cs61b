public class StarTriangle5 {
   /**
     * Prints a right-aligned triangle of stars ('*') with 5 lines.
     * The first row contains 1 star, the second 2 stars, and so on. 
     */
   public static void starTriangle5() {
      for (int i=1;i<=5;i++){
         for (int j=5;j>i;j--){
           IO.print(' ');
           }
         for (int l=0;l<i;l++){
            IO.print('*');
         }
         IO.println();
      }
   }
   
   public static void main(String[] args) {
      starTriangle5();
   }
}