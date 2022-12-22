package com.aries.jc.lch.modules.repository.dao;

import com.aries.jc.lch.modules.repository.entity.Department;
import com.aries.jc.lch.modules.repository.entity.Employee;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lichanghao6
 * 员工dao
 */
@Repository
public class EmployeeDao {

    /**
     * 模拟数据库中的数据
     */
    private static Map<Integer, Employee> employees = null;

    /**
     * 员工有所属的部门
     */
    @Resource
    private DepartmentDao departmentDao;

    //static优先加载，然后再去初始化DepartmentDao，所以拿不到departmentDao
    //且静态代码块中，不能加载非静态变量departmentDao
    static {

        //创建一个员工表
        employees = new HashMap<Integer, Employee>();

        employees.put(1001, new Employee(1001, "AA", "A846293740@qq.com", 0, new Department(101, "教学部")));
        employees.put(1002, new Employee(1002, "BB", "B846293740@qq.com", 1, new Department(102, "市场部")));
        employees.put(1003, new Employee(1003, "CC", "C846293740@qq.com", 0, new Department(103, "教研部")));
        employees.put(1004, new Employee(1004, "DD", "D846293740@qq.com", 1, new Department(104, "运营部")));
        employees.put(1005, new Employee(1005, "EE", "E846293740@qq.com", 0, new Department(105, "后勤部")));
    }

    /**
     * 主键自增
     */
    private static Integer initId = 1006;

    /**
     * 增加一个员工
     */
    public void add(Employee employee) {
        if (employee.getId() == null) {
            employee.setId(initId++);
        }

        employee.setDepartment(departmentDao.getDepartmentById(employee.getDepartment().getId()));

        employees.put(employee.getId(), employee);
    }

    /**
     * 查询全部员工信息
     *
     * @return 全部员工信息
     */
    public Collection<Employee> getAll() {
        return employees.values();
    }

    /**
     * 通过id查询员工
     *
     * @return Employee 员工信息
     */
    public Employee getEmployeeById(Integer id) {
        return employees.get(id);
    }

    /**
     * 通过id删除员工
     */
    public void deleteEmployeeById(Integer id) {
        employees.remove(id);
    }


}
