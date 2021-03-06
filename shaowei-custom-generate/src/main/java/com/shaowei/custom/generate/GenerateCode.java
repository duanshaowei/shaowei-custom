package com.shaowei.custom.generate;

import java.util.List;

import com.shaowei.custom.generate.util.FileType;
import com.shaowei.custom.generate.util.FileUtils;
import com.shaowei.custom.generate.util.FreeMarkers;
import com.shaowei.custom.generate.util.Resources;
import com.shaowei.custom.generate.vo.Column;
import com.shaowei.custom.generate.vo.Table;

import freemarker.template.Template;


/** 
 * @author 	ShaoWei Duan
 * @since   2015年09月08日 下午3:02:33 
 */
public class GenerateCode extends AbstractGenerate implements Generate {
	FileType fileType = null;

	public GenerateCode(FileType javaFileType) {
		super();
		this.fileType = javaFileType;
	}

	/* (non-Javadoc)
	 * @see com.shaowei.custom.generate.Generate#generate(com.shaowei.custom.generate.vo.Table)
	 */
	@Override
	public void generate(Table table) throws Exception {
		model.put("tableName", table.getTableName());
		model.put("columns", table.getColumns());
		model.put("createDate", table.getCreateDate());
		model.put("driveType", table.getDriveType());
		model.put("className", table.getClazzName());
		// 特殊类型处理
		handleSpecial(table.getColumns());

		Template template = cfg.getTemplate("code" + separator + fileType.getTemplate());
		String content = FreeMarkers.renderTemplate(template, model);
		// 生成java
		if (fileType.getFileNameExtension().endsWith(".java")) {
			String filePath = javaPath + fileType.getJavaStorePath() + separator + table.getClazzName() + fileType.getFileNameExtension();
			FileUtils.writeFile(content, filePath);
			
			logger.info(fileType.getType() + ": {}", filePath);
			
		//生成jsp
		} else if (fileType.getFileNameExtension().endsWith(".jsp")) {
			String filePath = viewPath + Resources.TPL_REQUEST_MAPPING + separator + fileType.getFileNameExtension();
			FileUtils.writeFile(content, filePath);
			
			logger.info(fileType.getType() + ": {}", filePath);
		}
	}

	/* (non-Javadoc)
	 * @see com.shaowei.custom.generate.Generate#generate(java.util.List)
	 */
	@Override
	public void generate(List<Table> tables) throws Exception {
		// TODO Auto-generated method stub
	}

	/**
	 * 特殊类型处理
	 * @param columns
	 */
	private void handleSpecial(List<Column> columns) {
		boolean hasDate = false;
		boolean hasBigDecimal = false; 
		for (Column column : columns) {
			if (column.getJavaType().equals("Date")) {
				hasDate = true;
			} else if (column.getJavaType().equals("BigDecimal")) {
				hasBigDecimal = true;
			}
		}
		
		model.put("hasDate", hasDate);
		model.put("hasBigDecimal", hasBigDecimal);
	}
}
