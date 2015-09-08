package ${pknDAO};

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import ${pknEntity}.${className};
/**
  *   
  *   @author Shaowei Duan
  *   @since  ${createDate?string("yyyy年MM月dd日 HH:mm:ss")}
  *
  */
@Repository
public interface ${className}DAO extends JpaRepository<${className}, Long>, JpaSpecificationExecutor<${className}> {

}