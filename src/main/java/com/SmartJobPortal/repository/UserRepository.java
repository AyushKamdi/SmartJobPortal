package com.SmartJobPortal.repository;

import com.SmartJobPortal.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>{

	// We can define custom search methods simply by naming them correctly
    Optional<User> findByUsername(String username);
    
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
	
}
