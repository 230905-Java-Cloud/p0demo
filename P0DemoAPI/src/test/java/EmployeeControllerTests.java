import com.google.gson.Gson;
import com.revature.controllers.EmployeeController;
import com.revature.models.Employee;
import com.revature.models.Role;
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

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeControllerTests {

    @Mock
    EmployeeService eService;
    Context ctx;

    @InjectMocks
    EmployeeController eController = new EmployeeController();
    Gson gson = new Gson();
    Javalin app;
    CloseableHttpClient httpClient;

    @Before
    public void setUp(){

        app = Javalin.create().start(9090); //open our javalin resource

        //configure the necessary endpoint handlers
        app.post("/employees", eController.insertEmployeeHandler);

        //open our httpClient (get a default client)
        httpClient = HttpClients.createDefault();

    }

    @After
    public void tearDown() throws Exception{
        app.stop(); //close our javalin resource
        httpClient.close(); //close httpClient
    }


    //GREEN TEST - Test that a valid HTTP Request with a valid user works as intended
    @Test
    public void testInsertValidEmployee() throws Exception{

        Employee testEmp = new Employee("Jessie", "Pinkman", 4);

        //let's prepare our POST request, starting with the stringification of our testEmp
        String JSONEmp = gson.toJson(testEmp);

        //creating the POST request, and setting the request body
        HttpPost request = new HttpPost("http://localhost:9090/employees");
        request.setEntity(new StringEntity(JSONEmp));

        //stubbing! (thanks to mockito)
        when(eService.insertEmployee(any(Employee.class))).thenReturn(testEmp);

        //send the request! Store it in an HttpResponse object (to run assertions on)
        HttpResponse response = httpClient.execute(request);

        //now we can write assertions on the response (as well as verify for the service)
        verify(eService).insertEmployee(any(Employee.class));

        //Assert the response status code is 201 ACCEPTED
        assertEquals(201, response.getStatusLine().getStatusCode());

        //Assert the response body matches our expectations
        //getEntity returns the body of the response, and we need to turn it into a string
        assertEquals(JSONEmp, EntityUtils.toString(response.getEntity()));

    }

    //RED TEST - Testing that invalid employee returns the appropriate status code
    @Test
    public void testInsertInvalidEmployee() throws Exception{

        //invalid employee
        Employee testEmp = new Employee("Javascript", "Johnson", 3);

        String JSONEmp = gson.toJson(testEmp);

        HttpPost request = new HttpPost("http://localhost:9090/employees");
        request.setEntity(new StringEntity(JSONEmp));

        //stubbing - set the service method to throw an exception when called w/ Javascript Johnson
        when(eService.insertEmployee(any(Employee.class))).thenThrow(new IllegalArgumentException("dude...."));

        HttpResponse response = httpClient.execute(request);

        //assert that we get the appropriate error status code (406)
        assertEquals(406, response.getStatusLine().getStatusCode());

    }



}
