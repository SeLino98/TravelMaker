package com.ssafy.gumibom;


import com.ssafy.gumibom.domain.user.entity.Gender;
import com.ssafy.gumibom.domain.user.entity.User;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class InitDB {

    private final InitDBService initDBService;

    @PostConstruct
    public void init() {
        initDBService.initDB();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitDBService {

        private final EntityManager em;

        public void initDB() {
            User user = createUser("중원", "secret", "jw", "010-0000-0000");
            em.persist(user);
        }
    }

    private static User createUser(String username, String password, String nickname, String phone) {
        User user = new User(username, password, nickname, phone);
        return user;
    }
}
