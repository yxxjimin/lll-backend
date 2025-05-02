package lll.backend.domain.auth.service;

import lll.backend.domain.auth.dto.request.LoginRequest;
import lll.backend.domain.auth.dto.request.SignupRequest;
import lll.backend.domain.auth.dto.response.LoginResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
public class AuthServiceTest {

    @Autowired
    AuthService authService;

    @Test
    void signup() {
        SignupRequest request = new SignupRequest("test-user", "password");

        LoginResponse response = authService.signup(request);

        assertThat(response.username()).isEqualTo(request.username());
    }

    @Test
    void loginSuccess() {
        SignupRequest signup = new SignupRequest("test-user", "password");
        authService.signup(signup);

        LoginRequest reqSuccess = new LoginRequest(signup.username(), signup.password());
        LoginResponse resSuccess = authService.login(reqSuccess);

        assertThat(resSuccess.username()).isEqualTo(signup.username());
    }

    @Test
    void loginNonExistingMember() {
        LoginRequest req = new LoginRequest("non-existing", "password");

        assertThatThrownBy(() -> authService.login(req)).isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void loginInvalidPassword() {
        SignupRequest signup = new SignupRequest("test-user", "password");
        authService.signup(signup);

        LoginRequest reqFail2 = new LoginRequest(signup.username(), "invalid");

        assertThatThrownBy(() -> authService.login(reqFail2)).isInstanceOf(RuntimeException.class);
    }
}
