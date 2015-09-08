package com.shaowei.custom.generate;

import com.shaowei.custom.generate.GenerateSettings.SettingsType;
import com.shaowei.custom.generate.db.DataSource;
import com.shaowei.custom.generate.db.DbFactory;
import com.shaowei.custom.generate.util.ConvertHandler;
import com.shaowei.custom.generate.util.FileType;
import com.shaowei.custom.generate.util.Resources;
import com.shaowei.custom.generate.vo.Table;

/** 
 * @author 	ShaoWei Duan
 * @since   2015年09月08日 下午3:53:35 
 */
public class GenerateFactory {
	private Table table;
	private String tableName;
	
	public GenerateFactory() {
		this.tableName = Resources.TPL_TABLE_NAME;
	}
	
	/**
	 * 
	 */
	public GenerateFactory(String tableName) {
		this.tableName = tableName;
	}
	
	public GenerateFactory(Table table) {
		this.table = table;
	}
	
	private void init () {
		Table table = null;
		try {
			DataSource db = DbFactory.create();
			table = db.getTable(tableName);
			ConvertHandler.tableHandle(table);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
		this.table = table;
	}
	
	public void genJavaTemplate() {
		try {
			if (table == null) {
				init();
			}
			new GenerateCode(FileType.ENTITY).generate(table);
			new GenerateCode(FileType.ENTITY_VO).generate(table);
			new GenerateCode(FileType.DAO).generate(table);
			new GenerateCode(FileType.SERVICE).generate(table);
			new GenerateCode(FileType.SERVICE_IMPL).generate(table);
			new GenerateCode(FileType.CONTROLLER).generate(table);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void genJspTemplate() {
		try {
			if (table == null) {
				init();
			}
			new GenerateCode(FileType.JSP_CREATE).generate(table);
			new GenerateCode(FileType.JSP_LIST).generate(table);
			new GenerateCode(FileType.JSP_UPDATE).generate(table);
			new GenerateCode(FileType.JSP_VIEW).generate(table);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void genSettings() {
		try {
			new GenerateSettings().generate(SettingsType.APPLICATION_CONTEXT);
			new GenerateSettings().generate(SettingsType.JDBC);
			new GenerateSettings().generate(SettingsType.LOGBACK);
			new GenerateSettings().generate(SettingsType.SPRING_MVC);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void copyCommons() {
		try {
			new CopyCommonFile().copy();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void genServer() {
		try {
			new GenerateServer().generate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
