package co.com.udem.registroclient.repositories;



import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import co.com.udem.registroclient.entities.UserToken;

public interface UserTokenRepository extends CrudRepository<UserToken, Long> {
	@Query("SELECT u FROM UserToken u WHERE u.username = ?1")
    public UserToken obtenerToken(String username);
}
