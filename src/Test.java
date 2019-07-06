public class Test {
	
	public static void main(String[] args) {
		LinearArithExpression exp = new LinearArithExpression(
				new int[] {1, 2}, new int[] { -1, -2 }) ;
		exp.generate(2);
	}
}