import com.google.gson.Gson;
import com.revature.controllers.EmployeeController;
import com.revature.models.Employee;
import com.revature.services.EmployeeService;
import io.javalin.Javalin;
import io.javalin.http.Context;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.net.http.HttpClient;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class) //this lets us use Mockito!
public class EmployeeControllerTests {

    @Mock
    EmployeeService eService;
    Context ctx;

    @InjectMocks
    EmployeeController empController = new EmployeeController();
    Gson gson = new Gson();
    Javalin app;
    CloseableHttpClient httpClient;


    @Before
    public void setUp() {
        app = Javalin.create();

        // Configure your Javalin routes and handlers here
        app.post("/employees", empController.insertEmployeeHandler);

        app.start(9090); // Start the Javalin server
        httpClient = HttpClients.createDefault();
    }

    @After
    public void tearDown() {
        app.stop();
    }



    @Test
    public void testInsertValidEmployee() throws Exception {

        Employee testEmployee = new Employee("Guy", "Mansson", 1);

        String jsonEmployee = gson.toJson(testEmployee);

        System.out.println(jsonEmployee);

        HttpPost request = new HttpPost("http://localhost:9090/employees");
        request.setEntity(new StringEntity(jsonEmployee));

        //stubbing! When the insertEmployee() method gets called, return the testEmployee
        when(eService.insertEmployee(any(Employee.class))).thenReturn(testEmployee);

        HttpResponse response = httpClient.execute(request);

        //Assert the response status code is 201 (ACCEPTED)
        assertEquals(201, response.getStatusLine().getStatusCode());

        //Assert the response content matches your expectations
        String responseBody = EntityUtils.toString(response.getEntity());
        assertEquals(jsonEmployee, responseBody);
    }




//    @Test
//    public void testExampleHandler() throws Exception {
//        // Simulate an HTTP GET request to "/example"
//        HttpGet request = new HttpGet("http://localhost:" + app.port() + "/example");
//        HttpResponse response = httpClient.execute(request);
//
//        // Assert the response status code is 200 (OK)
//        assertEquals(200, response.getStatusLine().getStatusCode());
//
//        // Assert the response content matches your expectations
//        String responseBody = EntityUtils.toString(response.getEntity());
//        assertEquals("Hello, Javalin!", responseBody);
//    }


}
