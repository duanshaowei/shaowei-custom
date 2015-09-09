
package ${pknService};

import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import ${pknEntity}.${className};
import com.ketayao.ketacustom.util.dwz.Page;
/**
  *   
  *   @author Shaowei Duan
  *   @since  ${createDate?string("yyyy年MM月dd日 HH:mm:ss")}
  *
  */
public interface ${className}Service {
	${className} get(Long id);

	void saveOrUpdate(${className} ${instanceName});

    void deleteInBatch(Iterable<${className}> paramIterable);

	void delete(Long id);
	
	List<${className}> findAll(Page page);
	
	List<${className}> findByExample(Specification<${className}> specification, Page page);
}
