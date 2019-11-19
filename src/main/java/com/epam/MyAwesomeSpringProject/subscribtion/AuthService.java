package com.epam.MyAwesomeSpringProject.subscribtion;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
@Service
public class AuthService {
        private Long userId;

        public Long getUserId() {
            if(userId == null) {
                throw new MyAuthException("No user is logged in");
            }
            return userId;
        }

        public void checkAuthentication() {
            getUserId();
        }
}
