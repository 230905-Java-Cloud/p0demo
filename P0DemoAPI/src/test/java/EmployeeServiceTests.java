import com.revature.daos.EmployeeDAO;
import com.revature.models.Employee;
import com.revature.models.Role;
import com.revature.services.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class) //Tell the test class that we want to use Mockito
public class EmployeeServiceTests {

    @Mock //Mocking the DAO (which is a dependency of the service)
    EmployeeDAO eDAO; //WHY?? We don't want to make ACTUAL calls to the DB. can be bad!

    @InjectMocks //attach the mocked dependency (DAO) into our (real) Service
    EmployeeService eService;

    //GREEN TEST - test that insert works as expected when given VALID input
    @Test
    public void testInsertValidEmployee(){

        //First, define an employee with data that's valid enough to test on
        //the actual data doesn't matter since the DAO is mocked
        Employee testEmp = new Employee(25, "james", "tree", new Role());

        //Stubbing - when insertEmployee() from the DAO is called, return our user
        when(eDAO.insertEmployee(testEmp)).thenReturn(testEmp);
        //"when eDAO is called with user testEmp, return testEmp" - seems pointless right?

        //Call the service method, which should return an employee
        Employee returnedEmp = eService.insertEmployee(testEmp);

        //verify that the DAO method was called, and that it was called ONLY ONCE
        verify(eDAO, times(1)).insertEmployee(testEmp);

        //Basic assert - make sure a not null object was actually returned
        assertNotNull(returnedEmp);

        //More meaningful assert - make sure the returned person has the values we expect
        assertEquals(returnedEmp.getFirst_name(), "james");
        assertEquals(returnedEmp.getLast_name(), "tree");

    }

    @Test(expected = IllegalArgumentException.class)
    public void testInsertInvalidEmployee(){

        //Instantiate an invalid Employee object
        Employee testEmp = new Employee(1, null, null, new Role());

        //call the service method, which should return null
        Employee returnedEmp = eService.insertEmployee(testEmp);

        //verify that the DAO was never interacted with
        verifyNoInteractions(eDAO);

        //Basic assert - make sure null is actually returned
        //assertNull(returnedEmp);

        //TODO: refactor for IllegalArgumentException

    }

}
