import java.lang.Math;
import java.util.Arrays;

public class LinearArithExpression {
	
	/*
	 * limit = [term, unit, op_rm, value]
	 */
	
	public int[] arr;
	private int[] consts;
	private int[] vars;
	private int cSize;
	private int vSize;
	private int M;
	
	public LinearArithExpression(int[] consts, int[] vars) {
		this.consts = consts;
		this.vars = vars;
		cSize = consts.length;
		vSize = vars.length;
		M = cSize + vSize + 1;
	}
	
	public void generate(int op_max) {
		if (op_max == 0) { return; }
		arr = new int[15];
		int[] limit = new int[4];
		limit[0] = Integer.MAX_VALUE;
		limit[1] = M - 2;
		limit[2] = op_max;
		limit[3] = 0;
		TestCallBack tcb = new TestCallBack();
		gen_sum(0, limit, tcb);
		System.out.println(tcb.count);
	}
	
	public void gen(int index, int[] limit, CallBack cb) {
		gen_sum(index, limit, cb);
	}
	
	public void gen_minus(int index, int[] limit, CallBack cb) {
		
	}
	
	public void gen_sum(int index, int[] limit, CallBack cb) {
		//int[] lim_cpy = copy(limit);
		gen_term(index, limit, cb);
		if (limit[2] <= 0) { return; }
		gen_term(index, limit, new SumCallBack(cb));
	}
	
	public void gen_term(int index, int[] limit, CallBack cb) {
		//int[] lim_cpy = copy(limit);
		gen_unit(index, limit, cb);
		if (limit[2] <= 0) { return; }
		gen_unit(index, limit, new TermCallBack(cb));
	}
	
	public void gen_unit(int index, int[] limit, CallBack cb) {
		for (int i = 0; i <= Math.min(limit[1], cSize - 1); i++) {
			int[] lim_cpy = copy(limit);
			lim_cpy[3] = lim_cpy[3] * M + i + 1;
			if (lim_cpy[3] >= lim_cpy[0]) { return; }
			arr[index] = 1;
			arr[index + 1] = consts[i];
			lim_cpy[1] = i;
			cb.call(index + 2, lim_cpy, this);
		}
		
		for (int i = cSize; i <= limit[1]; i++) {
			int[] lim_cpy = copy(limit);
			lim_cpy[3] = lim_cpy[3] * M + i + 1;
			if (lim_cpy[3] >= lim_cpy[0]) { return; }
			arr[index] = 2;
			arr[index + 1] = vars[i - cSize];
			lim_cpy[1] = i;
			cb.call(index + 2, lim_cpy, this);
		}
	}
	
	public int[] getExpression() {
		return arr;
	}
	
	public int[] copy(int[] limit) {
		int[] lim_cpy = new int[limit.length];
		System.arraycopy( limit, 0, lim_cpy, 0, limit.length );
		return lim_cpy;
	}
	
	public int getM() {
		return M;
	}
	
}