import java.util.Arrays;

public class TestCallBack implements CallBack {
	static int count;
	static boolean test = false;
	
	public void call(int index, int[] limit, LinearArithExpression exp) {
		count++;
		
		
		System.out.print(Arrays.toString(exp.getExpression()));
		System.out.println(" " + index 
					+ " " + Arrays.toString(limit) 
					+ " " + exp.evaluate(index));
		
	}
}