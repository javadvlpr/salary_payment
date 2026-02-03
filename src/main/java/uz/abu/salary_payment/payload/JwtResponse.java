package uz.abu.salary_payment.payload;

import lombok.Builder;
import lombok.Data;
import uz.abu.salary_payment.payload.userDtos.UserResponse;

@Data
@Builder
public class JwtResponse {
    private String accessToken;
    private String refreshToken;
    private UserResponse user;

    public static JwtResponse from(String accessToken, String refreshToken, UserResponse userResponse){
        return JwtResponse
                .builder()
                .user(userResponse)
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
