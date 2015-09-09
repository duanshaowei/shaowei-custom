package	${pknServiceImpl};

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.dozer.DozerBeanMapper;
import com.ketayao.ketacustom.util.dwz.Page;
import com.ketayao.ketacustom.util.dwz.PageUtils;
import ${pknEntity}.${className};
import ${pknDAO}.${className}DAO;
import ${pknService}.${className}Service;
/**
  *   
  *   @author Shaowei Duan
  *   @since  ${createDate?string("yyyy年MM月dd日 HH:mm:ss")}
  *
  */
@Service
@Transactional(readOnly=true)
public class ${className}ServiceImpl implements ${className}Service {
	
	@Autowired
	private ${className}DAO ${instanceName}DAO;

	/*
	 * (non-Javadoc)
	 * @see ${pknService}.${className}Service#get(java.lang.Long)  
	 */ 
	@Override
	public ${className} get(Long id) {
		return ${instanceName}DAO.findOne(id);
	}

	/*
	 * (non-Javadoc) 
	 * @see ${pknService}.${className}Service#saveOrUpdate(${pknEntity}.${className})  
	 */
	@Override
	@Transactional
	public void saveOrUpdate(${className} ${instanceName}) {
	   ${className} destinationClass = ${instanceName}.getId() != null ? 
	                                ${instanceName}DAO.findOne(${instanceName}.getId()):${instanceName};
	
	   new DozerBeanMapper().map(${instanceName} , destinationClass);
	   
	   ${instanceName}DAO.save(destinationClass);
	}

	/*
	 * (non-Javadoc)
	 * @see ${pknService}.${className}Service#delete(java.lang.Long)  
	 */
	@Override
	@Transactional
	public void delete(Long id) {
		${instanceName}DAO.delete(id);
	}
	
	/*
	 * (non-Javadoc)
	 * @see ${pknService}.${className}Service#deleteInBatch(java.lang.Iterable)  
	 */
	@Override	
	@Transactional
	public void deleteInBatch(Iterable<${className}> paramIterable) {
	    ${instanceName}DAO.deleteInBatch(paramIterable);
	}
	
	/*
	 * (non-Javadoc)
	 * @see ${pknService}.${className}Service#findAll(com.ketayao.ketacustom.util.dwz.Page)  
	 */
	@Override
	public List<${className}> findAll(Page page) {
		org.springframework.data.domain.Page<${className}> springDataPage = ${instanceName}DAO.findAll(PageUtils.createPageable(page));
		page.setTotalCount(springDataPage.getTotalElements());
		return springDataPage.getContent();
	}
	
	/*
	 * (non-Javadoc)
	 * @see ${pknService}.${className}Service#findByExample(org.springframework.data.jpa.domain.Specification, com.ketayao.ketacustom.util.dwz.Page)	
	 */
	@Override
	public List<${className}> findByExample(
			Specification<${className}> specification, Page page) {
		org.springframework.data.domain.Page<${className}> springDataPage = ${instanceName}DAO.findAll(specification, PageUtils.createPageable(page));
		page.setTotalCount(springDataPage.getTotalElements());
		return springDataPage.getContent();
	}
}
