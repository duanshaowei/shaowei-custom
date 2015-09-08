package ${pknEntity};

<#if hasDate == true>
import java.util.Date;
</#if>
<#if hasBigDecimal == true>
import java.math.BigDecimal;
</#if>
import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;
/**
  *   
  *   @author Shaowei Duan
  *   @since  ${createDate?string("yyyy年MM月dd日 HH:mm:ss")}
  *
  */
public class ${className}Vo { 
    private Long id ;

    <#list columns as column>
	/**
	 * ${column.comment}
	 */
    <#if column.nullable == false>
       <#if column.javaType == "String">
    @NotBlank(message = "${column.comment}不能为空")
    @Length(max=${column.size},message="${column.fieldName}长度不能超过${column.size}个字符")
       <#else>
    @NotNull(message = "${column.comment}不能为空")
       </#if>
    </#if>
    private ${column.javaType} ${column.fieldName}; 
    
    </#list>

    /**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	<#list columns as column>
	
	/**
	 * @param ${column.fieldName} the ${column.fieldName} to set
	 */
    public void ${column.setMethod}(${column.javaType} ${column.fieldName}){
       this.${column.fieldName} = ${column.fieldName};
    }
    
    /**
     * @return the ${column.fieldName} 
     */
    public ${column.javaType} ${column.getMethod}(){
       return this.${column.fieldName};
    }
	</#list>
}