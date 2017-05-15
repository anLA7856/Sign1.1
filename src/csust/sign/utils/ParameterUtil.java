package csust.sign.utils;

/**
 * 用于验证参数。
 * 
 * @author anLA7856
 *
 */
public class ParameterUtil {

	public static boolean parameterTest(String... params) {
		// 首先验证是否为空，如果有一个为null就返回false,具有短路功能。
		for (int i = 0; i < params.length; i++) {
			if (params[i] == null || params.equals("")) {
				return false;
			}
		}

		return true;
	}
}
