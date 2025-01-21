package nam.ecom.ecomweb.test.Dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import nam.ecom.ecomweb.test.Entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
    
    Optional<User> findByEmail(String email);
}
