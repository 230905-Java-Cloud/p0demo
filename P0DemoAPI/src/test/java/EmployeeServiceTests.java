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

@RunWith(MockitoJUnitRunner.class) //this lets us use Mockito!
public class EmployeeServiceTests {

    @Mock
    EmployeeDAO eDAO;

    @InjectMocks
    EmployeeService eService;

    @Test
    public void testInsertValidEmployee(){

        //First, define a valid (enough) Employee object
        //the actual data doesn't matter, since the Service is mocked
        Employee e = new Employee(1, "john", "doe", new Role());

        //Stubbing - when save() is called in the DAO with employee e, return it back
        when(eDAO.insertEmployee(e)).thenReturn(e);

        //call the controller method, which should return an Employee
        //...because the method from the Service returns an Employee
        Employee returnedEmployee = eService.insertEmployee(e);

        //verify that the save() method from the DAO was called
        //verify is just a way to write more robust tests. we can confirm a method was called
        verify(eDAO).insertEmployee(e);

        //Basic assert - make sure the employee object is actually returned
        assertNotNull(returnedEmployee);
        //More meaningful assert - the returned Person has values we would expect.
        assertEquals(returnedEmployee.getFirst_name(), "john");

    }

    @Test
    public void testInsertInvalidEmployee(){

        //Define an invalid Employee object
        Employee e = new Employee(1, null, "doe", new Role());

        //call the controller method, which should return null
        //...because the method from the Service returns null if first_name is null
        Employee returnedEmployee = eService.insertEmployee(e);

        //verify that the DAO was never interacted with
        verifyNoInteractions(eDAO);

        //Basic assert - make sure null is actually returned
        assertNull(returnedEmployee);
        //More meaningful assert - the returned Employee has values we would expect.
        assertEquals(returnedEmployee, null);

        //TODO: refactor service to throw IllegalArgumentException

    }

}
