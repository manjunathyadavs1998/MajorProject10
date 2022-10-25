package com.example.MajorProject10.Controller;

import com.example.MajorProject10.DataAcess.Employee;
import com.example.MajorProject10.Exceptions.ResourceNotFoundException;
import com.example.MajorProject10.Repository.employeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1")
public class employeeController {

    @Autowired
    private employeeRepository employeerepository;

    @GetMapping("/hi")
    public String sayhi()
    {
        return "Good Morning User";
    }

    @GetMapping("/getAllEmployees")
    public List<Employee> getAllEmployees()
    {
        return employeerepository.findAll();
    }

    @PostMapping("/addEmployee")
    public Employee createEmployee(@RequestBody Employee employee)
    {
        return  employeerepository.save(employee);
    }

    @GetMapping("{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable  long id)
    {
        Employee employee=employeerepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id:" + id));

        return ResponseEntity.ok(employee);
    }
    @PutMapping("updateEmployee/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable long id,@RequestBody Employee employeeupdatefield)
    {
        Employee employeeupdate=employeerepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id: " + id));

        employeeupdate.setFirstName(employeeupdatefield.getFirstName());
        employeeupdate.setLastName(employeeupdatefield.getLastName());
        employeeupdate.setEmailId(employeeupdatefield.getEmailId());

        employeerepository.save(employeeupdate);

        return ResponseEntity.ok(employeeupdate);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable long id){

        Employee employee=employeerepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id: " + id));

        employeerepository.delete(employee);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

}
