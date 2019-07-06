public class Test {
	
	static int[] arr = new int[] {1, 2, 3, 1, 3};
	
	public static void main(String[] args) {
		
		LinearArithExpression exp = new LinearArithExpression(
				new int[] {1, 2}, new int[] { -1, -2 }) ;
		exp.generate(3);
		
	}
}