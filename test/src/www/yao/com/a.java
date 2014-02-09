package www.yao.com;

import java.io.IOException;
import java.util.Random;

public class a {

	public static void main(String[] args) {
		Random ran = new Random(1000000);
		StringBuilder sb = new StringBuilder();
		for(int i=0; ;i++){
			int a = ran.nextInt(27);
			if(a==0)
				break;
			sb.append(String.valueOf(a));
		}
		System.out.println(sb.toString());
	}

}
