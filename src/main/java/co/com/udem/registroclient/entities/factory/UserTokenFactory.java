package co.com.udem.registroclient.entities.factory;

import co.com.udem.registroclient.domain.AutenticationResponseDTO;
import co.com.udem.registroclient.entities.UserToken;

public final class UserTokenFactory {

	private UserTokenFactory() {

	}
	
	public static AutenticationResponseDTO toModel(UserToken entity) {
		return new AutenticationResponseDTO(entity.getUsername(),entity.getToken());	
	}
	
    public static UserToken toEntity(AutenticationResponseDTO autenticationResponseDTO) {
        return UserToken.builder()
        		.username(autenticationResponseDTO.getUsername())
        		.token(autenticationResponseDTO.getToken())
        		.build();
    }
	

}
