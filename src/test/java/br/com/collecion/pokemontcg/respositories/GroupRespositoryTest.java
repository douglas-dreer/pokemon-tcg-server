package br.com.collecion.pokemontcg.respositories;

import br.com.collecion.pokemontcg.enities.Group;
import br.com.collecion.pokemontcg.repositories.GroupRepository;
import org.junit.Before;
import org.junit.Test;
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

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class GroupRespositoryTest {

    private static final Logger logger = LoggerFactory.getLogger(GroupRespositoryTest.class);

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private GroupRepository repository;

    private static final Group group = new Group();

    @Before
    public void setup() {
        group.setName("Administrator");
        group.setCreateAt(new Date());
    }

    @Test
    public void mustReturnSuccess_WhenFindAll() {
        repository.save(group);
        List<Group> results = (List<Group>) repository.findAll();

        assertFalse(results.isEmpty());
        assertEquals(1, results.size());
    }

    @Test
    public void mustReturnSuccess_WhenFindById() {
        UUID uuid = repository.save(group).getId();
        Group result = null;

        Optional<Group> groupOptional = repository.findById(uuid);

        if(groupOptional.isPresent()) {
            result = groupOptional.get();
        }

        assertNotNull(result);
        assertEquals(uuid, result.getId());
    }

    @Test
    public void mustReturnSuccess_WhenSave() {
        Group result = repository.save(group);
        assertNotNull(result);
    }

    @Test
    public void mustReturnSuccess_WhenEdit() {
        group.setName("System");

        Group result = repository.save(group);

        assertNotNull(result);
    }

    @Test
    public void mustReturnSuccess_WhenDelete() {
        Group groupSaved = repository.save(group);

        repository.delete(groupSaved);

        assertFalse(repository.existsById(groupSaved.getId()));
    }
}
