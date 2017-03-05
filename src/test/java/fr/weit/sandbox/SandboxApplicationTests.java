package fr.weit.sandbox;

import fr.weit.sandbox.models.UserModel;
import fr.weit.sandbox.repositories.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SandboxApplicationTests {

    @Autowired
    UserRepository userRepository;

    @Test

    public void go(){
        crud();
    }

    @Transactional
    public void crud() {
        UserModel user = UserModel.builder().dateOfBirth(LocalDateTime.parse("2017-03-07T18:00:00")).name("name").build();
        Assert.assertThat(user.getName(), is("name"));


        Assert.assertThat(userRepository.findByName("name").size(),is(0));
        userRepository.save(user);

        Assert.assertThat(userRepository.findByName("name").size(),is(1));
        UserModel fromDb = userRepository.getByName("name");
        Assert.assertThat(fromDb.getName(), is("name"));
        Assert.assertThat(fromDb, is(user));
        Assert.assertNotNull(fromDb.getId());

        Long errors = userRepository.errors();
        Assert.assertThat(errors,is(1L));

        List<UserModel> weird = userRepository.weird();
        Assert.assertTrue(( (Object) weird.get(0))  instanceof Long); // OMG!!

        userRepository.delete(fromDb.getId());
        Assert.assertThat(userRepository.findByName("name").size(),is(0));
        UserModel one = userRepository.findOne(fromDb.getId());
        Assert.assertNull(one);
    }

}
