package cn.iamding.drpc.client;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Bootstrap.class)
@WebAppConfiguration
//@SpringApplicationConfiguration(classes = BootStrap.class)
public class BaseTest {
    protected Logger logger = LoggerFactory.getLogger(getClass());
}

