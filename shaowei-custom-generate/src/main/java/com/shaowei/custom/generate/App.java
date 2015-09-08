package com.shaowei.custom.generate;

/**
 * @author ShaoWei Duan
 * @since 2015年09月08日 下午4:49:35
 */
public class App {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GenerateFactory factory = new GenerateFactory("UC_T_STAFF_ROLE_GROUP");

		factory.genJavaTemplate();
		//factory.genJspTemplate();
		// factory.genSettings();
		// factory.genServer();
		//factory.copyCommons();
	}

}
