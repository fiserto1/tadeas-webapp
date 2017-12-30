package tadeas;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import tadeas.data.SessionKeyI;
import tadeas.dto.TaskDTO;
import tadeas.service.TaskService;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableAutoConfiguration
public class TaskServiceTest {

    @MockBean
    private SessionKeyI token;

    @Autowired
    private TaskService service;


    @Before
    public void setup(){
//        student
        when(token.getToken()).thenReturn("0dc1f6e1-c7f1-41ac-8ce2-32b6b3e57aa3");
    }

    @Test
    public void getExistingTask() throws Exception {
        TaskDTO returnedTask = service.getTask(1);
        assertEquals(1, returnedTask.getId());
        assertEquals("Task1", returnedTask.getName());
    }


    @Test(expected = HttpClientErrorException.class)
    public void getNonexistentTask() throws Exception {
        TaskDTO returnedTask = service.getTask(40);
    }


}
