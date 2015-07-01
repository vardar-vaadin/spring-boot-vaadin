package tr.biz.vardar.repository;

import org.springframework.data.repository.CrudRepository;

import tr.biz.vardar.domain.User;

public interface UserRepository extends CrudRepository<User, Long>{

	
	User findUserByUsernameAndPassword(String username,String password);
	
}
