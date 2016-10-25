package com.test.neo4j.test;

import com.test.neo4j.config.Neo4jConfig;
import com.test.neo4j.domain.Role;
import com.test.neo4j.domain.Unit;
import com.test.neo4j.domain.User;
import com.test.neo4j.model.UserQo;
import com.test.neo4j.repositories.RoleRepository;
import com.test.neo4j.repositories.UnitRepository;
import com.test.neo4j.repositories.UserRepository;
import com.test.neo4j.services.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Neo4jConfig.class})
public class UserTest {
    private static Logger logger = LoggerFactory.getLogger(UserTest.class);

    @Autowired
    UnitRepository unitRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;

    //@Before
    public void initData() {
        userRepository.deleteAll();
        roleRepository.deleteAll();
        unitRepository.deleteAll();

        Unit unit = new Unit();
        unit.setName("开发部");
        unit.setCreate(new Date());

        Role role = new Role();
        role.setName("admin");
        role.setCreate(new Date());

        User user = new User();
        user.setName("user");
        user.setSex(1);
        user.setEmail("user@email.com");
        user.setCreate(new Date());
        BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
        user.setPassword(bc.encode("user"));

        user.setUnit(unit);
        user.addRole(role);

        userRepository.save(user);
        Assert.notNull(user.getId());
    }

    //@Test
    public void getPage() throws Exception{
        UserQo userQo = new UserQo();
        userQo.setName("");
        userQo.setCreate(new SimpleDateFormat("yyyy-MM-dd").parse("2016-08-19"));
        logger.info("======="+userQo.getCreate().getTime());


        Page<User> users = userService.findPage(userQo);
        Assert.notNull(users);
        for(User user : users.getContent()){
            logger.info("==== user ==== name:{}, unit: {}, role: {}",
                    user.getName(), user.getUnit().getName(), user.getRoles().iterator().next().getName()
            );
        }
    }

    //@Test
    public void addSomeDate(){
        Unit unit = new Unit();
        unit.setName("市场部");
        unit.setCreate(new Date());
        unitRepository.save(unit);

        Role edit = new Role();
        edit.setName("editor");
        edit.setCreate(new Date());
        roleRepository.save(edit);

        Role manage = new Role();
        manage.setName("manage");
        manage.setCreate(new Date());
        roleRepository.save(manage);

        for(int i = 0; i<10; i++) {
            User user = new User();
            user.setName("用户"+i);
            user.setSex(0);
            user.setEmail("user"+i+"@email.com");
            user.setCreate(new Date());
            BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
            user.setPassword(bc.encode("123456"));

            user.setUnit(unit);
            user.addRole(edit);
            user.addRole(manage);

            userRepository.save(user);
        }
    }

    @Test
    public void getList() throws Exception{
        Pageable pageable = new PageRequest(0, 10, new Sort(Sort.Direction.ASC, "id"));
        List<User> users = userRepository.findByNameLike("");
        Assert.notNull(users);
        for(User user : users){
            logger.info("==== user ==== name:{}, unit: {}, role: {}",
                    user.getName(), user.getUnit().getName(), user.getRoles().iterator().next().getName()
            );
        }
    }
}
