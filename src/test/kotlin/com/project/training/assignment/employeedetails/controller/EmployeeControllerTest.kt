package com.project.training.assignment.employeedetails.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.project.training.assignment.employeedetails.model.employee.Employee
import com.project.training.assignment.employeedetails.model.owner.Owner
import com.project.training.assignment.employeedetails.repository.employee.EmployeeRepository
import com.project.training.assignment.employeedetails.repository.owner.OwnerRepository
import com.project.training.assignment.employeedetails.service.EmployeeServiceImpl
import org.junit.Rule
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnit
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import java.lang.Exception
import java.lang.RuntimeException
import java.util.*
@SpringBootTest
@AutoConfigureMockMvc
class EmployeeControllerTest {
    @InjectMocks
    private val employeeController: EmployeeController? = null

    @Mock
    private val employeeRepository: EmployeeRepository? = null

    @Mock
    private val ownerRepository: OwnerRepository? = null

    @Mock
    private val employeeService: EmployeeServiceImpl? = null

    @Rule
    var rule = MockitoJUnit.rule()

    @Autowired
    private var mockMvc: MockMvc? = null

    @Autowired
    private val context: WebApplicationContext? = null
    private var employee: Employee? = null
    private val owner: Owner? = null

    //private ObjectMapper objectMapper;
    private val mapper = ObjectMapper()
    @BeforeEach
    fun setUp() {
        mockMvc = context?.let {
            MockMvcBuilders.webAppContextSetup(it)
                .apply<DefaultMockMvcBuilder>(SecurityMockMvcConfigurers.springSecurity()).build()
        }
        employee = Employee(1, "Ram", "Ind")
    }

//    @Test
//    @WithMockUser(username = "user1", password = "pwd", roles = ["admin"])
//    @Throws(Exception::class)
//    fun whenCreateEmployee() {
//        val employee1 = Employee(1, "Ram", "Ind")
//        Mockito.`when`(employeeService!!.createEmployee(ArgumentMatchers.any())).thenReturn(employee1)
//        mockMvc?.perform(
//            MockMvcRequestBuilders.post("/addemployee")
//                .content(mapper.writeValueAsString(employee1))
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON)
//        )
//            ?.andExpect(MockMvcResultMatchers.status().isOk)
//    }

    @Test
    @WithMockUser(username = "user1", password = "pwd", roles = ["admin"])
    @Throws(Exception::class)
    fun whenCreateOwner() {
        val owner = Owner(1, "Juli", "Axxx")
        Mockito.`when`<Any>(ownerRepository!!.save(ArgumentMatchers.any())).thenReturn(owner)
        mockMvc!!.perform(
            MockMvcRequestBuilders.post("/addowner")
                .content(mapper.writeValueAsString(owner))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
    }

    @Test
    @WithMockUser(username = "user1", password = "pwd", roles = ["client"])
    @Throws(
        Exception::class
    )
    fun whenGetEmployeeList() {
        val customerList = Arrays.asList(
            Employee(1, "Ram", "Ind"),
            Employee(2, "Ravan", "USA")
        )
        Mockito.`when`(employeeService!!.getAllEmployee()).thenReturn(customerList)
        mockMvc!!.perform(
            MockMvcRequestBuilders.get("/employeelist")
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
        //.andExpect(content().json("[{}, {}]"));
    }

    @Test
    @WithMockUser(username = "user1", password = "pwd", roles = ["admin"])
    @Throws(Exception::class)
    fun whenGetOwnerList() {
        val customerList = Arrays.asList(
            Owner(1, "Ram", "Axx"),
            Owner(2, "Ravan", "Wxx")
        )
        Mockito.`when`(
            ownerRepository!!.findAll()
        ).thenReturn(customerList)
        mockMvc!!.perform(
            MockMvcRequestBuilders.get("/ownerlist")
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
        //.andExpect(content().json("[{}, {}]"));
    }

//    @Test
//    @WithMockUser(username = "user1", password = "pwd", roles = ["admin"])
//    @Throws(Exception::class)
//    fun whenUpdateEmployee() {
//        val employee1 = Employee(1, "Ram", "Ind")
//        Mockito.`when`<Any>(employeeRepository!!.save(ArgumentMatchers.any())).thenReturn(employee1)
//        mockMvc?.perform(
//            MockMvcRequestBuilders.put("/updateemployee/1")
//                .content(mapper.writeValueAsString(employee1))
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON)
//        )
//            ?.andExpect(MockMvcResultMatchers.status().isOk)
//    }

//    @Test
//    @WithMockUser(username = "user1", password = "pwd", roles = ["admin"])
//    @Throws(Exception::class)
//    fun whenUpdateOwner() {
//        val owner = Owner(1, "Juli", "Axxx")
//        Mockito.`when`<Any>(ownerRepository!!.save(ArgumentMatchers.any())).thenReturn(owner)
//        mockMvc!!.perform(
//            MockMvcRequestBuilders.put("/updateowner/1")
//                .content(mapper.writeValueAsString(owner))
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON)
//        )
//            .andExpect(MockMvcResultMatchers.status().isOk)
//    }

    @Test
    @WithMockUser(username = "user1", password = "pwd", roles = ["admin"])
    @Throws(Exception::class)
    fun whenGetEmployeeById_Not_Found() {
        val empId = 1
        Mockito.`when`(employeeService!!.getEmpById(empId)).thenThrow(RuntimeException("No Data found"))
        mockMvc!!.perform(
            MockMvcRequestBuilders.get("/getbyid/1") //.content(mapper.writeValueAsString(employee1))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isNotFound)
    }

    @Test //@WithMockUser(username = "user1", password = "pwd", roles = "admin")
    @Throws(Exception::class)
    fun whenDeleteEmployee() {
        val employee1 = Employee(1, "Ram", "Ind")
        Mockito.`when`(employeeRepository!!.findById(employee!!.empId)).thenReturn(Optional.of(employee!!))
        mockMvc!!.perform(
            MockMvcRequestBuilders.delete("/deleteemployee/1") //.content(mapper.writeValueAsString(employee1))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().`is`(401))
        employeeController!!.deleteEmployee(employee!!.empId)
    }
}