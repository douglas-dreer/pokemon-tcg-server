package br.com.collecion.pokemontcg.services;

import br.com.collecion.pokemontcg.enities.User;
import br.com.collecion.pokemontcg.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserServiceTest {
    @InjectMocks
    private UserService service;

    @Mock
    private UserRepository repository;

    private static final UUID ID = UUID.fromString("37ca882d-8550-43b3-9a12-597d17885b64");
    private User user = new User();
    private List<User> userList = new ArrayList<>();

    @BeforeEach
    public void setup() {
        user = new User(
                ID, "Tabitha Rowe", "Simeon65@hotmail.com",
                "Richard.Swaniawski53",
                "1673:029a03f504394a121c12f96c8f823c2e:b5a4bc18cd058f301c64149de93364bef1b9c6f337dfbb9d1caf9055334bcff0c40f0cad239023456066ef81d8fe78d0ccda7f8db858f23e9f244f594a7802e157467890a1c0f9465ffcba322ac1ceeb796cd4f774aa0453a86957b459d00e0d1f9fa95b1fd8e8e73cb977d8a3ccc73d1f4bf1c634e0266c5508c7912dd8b0a9f1b8d79b07ec31bd23cc79f9c54cb819b6fdf9ec5baa808de2ee48a328dc31a5c2858f031051dc064bef6870fdeb2aee3ff62869d35d4f240c21ffda89be1f562420116e492a2d1c74c6561af1019dd0cf07ffad20551500df59da68a0e1ed9802999a5016ab5030b00fb7cd53daefad4d890be80e7a97dc87eab0cfe1edbfb096e0f94d6920f9325a25702aa921ca0c93a57f4d5a430b423c7dc844c1c4e00ea861e418df74c42f1d518351c7b2156eda153b50784992ee0881ca75e95ae0b6dd0461f98893649a46e319c3c20c2b7b192b4e6943067ec51ab3b31e74676a466a9171a2a42c439ebb954ee6059885a509049de5a87de356e877453481e4fc8263b4441f4925663871c8b0c9ecbe6fb1e66aa525fc882a47de24acaeacbac130b812c4c204a72ff272f082f444f8834d6744371543a5097cb3e09f967769d5bfeb54ddc5dcd284be8f786784649415e354018dcb83266e9eebe6ce11baed1b1a009d902e63f115107598d39e40fa8e8180b600e95afd7905bfc40ffd59a850ed2fe77edb9adc95c087750b2542ebea4bfd7878b90d4bdee96248b100c4269ae1240b1f32e868b37439a2ff02804a18c71da98697cf5ec8e6e61cfa7891afb90a108330b0f69c3a917ee29f8c285c07a07e5fbf57f75a974b58f00bb2501ac105ed4c6098360671d97dc32ffc2309ea9b152c689e736c764d24a6cb51e434aac72c63cf0f989f989cfebcc073688598d113048fd14dc55e37c819178850c1b736f504e219178fb48df9814e09457618bf91530bf8f40a7ba28ede8eb50fe961048df904a9aa0c95a5ecb801c033f66b1e868c1314707e20fffa6d57f2546151c5dd134da32df8ea8af00b301a0df4acf8e2aa575fbf06bb56a7bb45ff8e563aa5718b443e3106ca57d5a6733a2523d64cf0e0ab38f82db8cccf0d7437fb994f781fcd5565dda1bd1dbe7a23865e432a6525d0ba9c79a542bcc156906da6cc8665b4014681c370f24b4243c91a0618d3ce657677eacd187bf5b2cbf4bc463f78ecc8fbd1994f54891a0192c7346f4e83340095339f3aa69e35aadfaedadae2b8bf52ad23116d472cb446c5c0947a52312cebb550c51ee5669ae6f2e0df9e24386b72d2e0c387101a0d0ed7cc55fda8b0146cee50319b538e7cbd3152ea758d8a59d6ede305a52c38f708e05bdddb2bce28c4aa7b96b48fdccea9fa672cf8fe7f021eee0cb40f57adf33bcf3d65a1f2e775647fdca7c8b159bcf9ff86fc2825c264",
                new Date(), null, true
        );
        userList = Collections.singletonList(user);
    }

    @Test
    void mustReturnSuccess_WhenFindByUUID() {
        Optional<User> userOptional = Optional.of(user);
        when(repository.findById(ID)).thenReturn(userOptional);

        User result = service.findByUUID(ID);

        assertNotNull(result);
        assertEquals(ID, user.getId());
        verify(repository, atLeastOnce()).findById(any());
    }

    @Test
    void mustReturnSuccess_WhenFindAll() {
        when(repository.findAll()).thenReturn(userList);
        List<User> result = service.findAll();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        verify(repository, atLeastOnce()).findAll();
    }

    @Test
    public void mustReturnSuccess_WhenSave() throws Exception {
        when(repository.save(any())).thenReturn(user);

        User result = service.save(user);

        assertNotNull(result);
        verify(repository, atLeastOnce()).save(any());
    }

    @Test
    void mustReturnSuccess_WhenEdit() {
        user.setUpdateAt(new Date());
        when(repository.existsById(any())).thenReturn(true);
        when(repository.save(any())).thenReturn(user);

        User result = service.edit(user);

        assertNotNull(result);
        verify(repository, atLeastOnce()).save(any());
        verify(repository, atLeastOnce()).existsById(any());
    }

    @Test
    void mustReturnError_WhenEdit_NotFound() {
        user.setUpdateAt(new Date());
        when(repository.existsById(any())).thenReturn(false);
        when(repository.save(any())).thenReturn(user);

        User result = service.edit(user);

        assertNull(result);
        verify(repository, atLeastOnce()).existsById(any());
    }

    @Test
    void mustReturnSuccess_WhenDelete() {
        doNothing().when(repository).deleteById(any());
        when(repository.existsById(any())).thenReturn(false);

        boolean result = service.delete(ID);

        assertTrue(result);
        verify(repository, atLeastOnce()).deleteById(any());
        verify(repository, atLeastOnce()).existsById(any());
    }
}
