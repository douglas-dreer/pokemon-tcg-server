package br.com.collecion.pokemontcg.respositories;

import br.com.collecion.pokemontcg.enities.User;
import br.com.collecion.pokemontcg.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRespositoryTest {

    private static final Logger logger = LoggerFactory.getLogger(UserRespositoryTest.class);

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private UserRepository repository;

    private static final User user = new User();

    @Before
    public void setup() {
        user.setName("Douglas Dreer");
        user.setUsername("douglasdreer");
        user.setPassword("admin@123");
        user.setEmail("douglasdreer@outlook.com.br");
        user.setCreateAt(new Date());
    }

    @Test
    public void mustReturnSuccess_WhenFindAll() {
        repository.save(user);
        List<User> results = (List<User>) repository.findAll();

        assertFalse(results.isEmpty());
        assertEquals(1, results.size());
    }

    @Test
    public void mustReturnSuccess_WhenFindById() {
        UUID uuid = repository.save(user).getId();
        User result = null;

        Optional<User> userOptional = repository.findById(uuid);

        if(userOptional.isPresent()) {
            result = userOptional.get();
        }

        assertNotNull(result);
        assertEquals(uuid, result.getId());
    }

    @Test
    public void mustReturnSuccess_WhenSave() {
        User result = repository.save(user);

        assertNotNull(result);
    }

    @Test
    public void mustReturnSuccess_WhenEdit() {
        user.setUsername("DouglasDreer");
        User result = repository.save(user);
        assertNotNull(result);
    }

    @Test
    public void mustReturnSuccess_WhenDelete() {
        User userSaved = repository.save(user);

        repository.delete(userSaved);

        assertFalse(repository.existsById(userSaved.getId()));
    }

}
