package tr.biz.vardar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tr.biz.vardar.domain.User;
import tr.biz.vardar.repository.UserRepository;


@Service
public class DBService {

	@Autowired
	private UserRepository userRepository;

	public UserRepository getUserRepository() {
		return userRepository;
	}

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public User createUser(User user){
		if(userRepository.findUserByUsernameAndPassword(user.getUsername(), user.getPassword())==null){
			return userRepository.save(user);
		}
		System.out.println("No need to save ");
		return user;
		
	}
}
