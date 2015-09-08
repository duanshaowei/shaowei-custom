package ${pknEntity};

<#if hasDate == true>
import java.util.Date;
</#if>
<#if hasBigDecimal == true>
import java.math.BigDecimal;
</#if>

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
<#if hasDate == true>
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
</#if>

import com.shaowei.custom.common.entity.IdEntity;
/**
  *   
  *   @author Shaowei Duan
  *   @since  ${createDate?string("yyyy年MM月dd日 HH:mm:ss")}
  *
  */
@Entity
@Table(name="${tableName}")
public class ${className} implements IdEntity<Long>{
	
	<#if driveType == "MYSQL">
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    <#elseif driveType == "ORACLE">
	@Id
	@SequenceGenerator(name = "generator", sequenceName = "SEQ_${tableName}")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
	</#if>
    private Long id;
    
    <#list columns as column>
	/**
	 * ${column.comment}
	 */
    @Column(<#if column.nullable == false>nullable=false, </#if>length=${column.size})
    <#if column.type == "DATE">
	@Temporal(TemporalType.DATE)
	<#elseif column.type == "DATETIME">
	@Temporal(TemporalType.TIMESTAMP)
	<#elseif column.type == "TIME">
	@Temporal(TemporalType.TIME)
	<#elseif column.type == "TIMESTAMP">
	@Temporal(TemporalType.TIMESTAMP)
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
