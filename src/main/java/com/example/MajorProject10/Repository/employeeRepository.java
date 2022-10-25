package com.example.MajorProject10.Repository;

import com.example.MajorProject10.DataAcess.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface employeeRepository  extends JpaRepository<Employee, Long> {
}
