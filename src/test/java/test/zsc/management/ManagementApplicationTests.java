package test.zsc.management;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import test.zsc.management.mapper.EmpMapper;
import test.zsc.management.pojo.Emp;

import java.util.List;

@SpringBootTest
class ManagementApplicationTests {

    @Test
    void contextLoads() {
    }

        @Autowired
        private EmpMapper empMapper;

        @Test
        public void testList(){
            List<Emp> empList = empMapper.list2();
            empList.forEach(System.out::println);
        }


}
