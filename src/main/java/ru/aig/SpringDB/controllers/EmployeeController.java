package ru.aig.SpringDB.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.aig.SpringDB.entities.Employee;
import ru.aig.SpringDB.services.EmployeeService;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//@Controller
//@RequestMapping("/users")  - если повесить данную аннтацию на целый класс, то end-point "/users" появится у всех методов, объявленных в рамках данного класса
@RestController
public class EmployeeController {

    @Autowired
    protected EmployeeService employeeService;


/*
    @GetMapping("/")
    @ResponseBody // данная аннтация возвращает значение, прописанное в return в теле http ответа
    public String home() {
        return "Home page";
    }

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "num1", required = false) Integer num1,
                        @RequestParam(value = "num2", required = false) Integer num2) {
        if(num1 != null && num2 != null) return String.valueOf(num1+num2);
        return "Just for fun!";
    }

    @GetMapping("/getUser")
    @ResponseBody // данная аннтация возвращает значение, прописанное в return в теле http ответа
    public Employee getUser(@RequestParam(value = "id", required = false) Long id) {
        return employeeRepository.getEmployeeById(id).get();
    }
*/
    @PostMapping("/users")
    public ResponseEntity<?> save(@RequestBody Employee employee) {
        try {
            return ResponseEntity.ok(employeeService.save(employee));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
//    @PostMapping(name = "/uploadoc", headers = "Content-Type= multipart/form-data")

    @PostMapping("/uploadoc2")
    public ResponseEntity<?> uploadDoc1(@RequestParam("file") MultipartFile myFile) {
        try {

            byte[] bytes = myFile.getBytes();

//            modelMap.addAttribute("file", myFile);

            FileOutputStream fileOutputStream = new FileOutputStream("d:/Works/IT/file.txt");
            fileOutputStream.write(bytes);
            fileOutputStream.close();
            return ResponseEntity.ok("Test text");
        } catch (IOException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping(value = "/uploadoc")
    public ResponseEntity<?> uploadDoc(@RequestParam("file") MultipartFile file) throws IOException {

        Map<String, String> map = new HashMap<>();

        // Populate the map with file details
        map.put("Look at this! FileName", file.getOriginalFilename());
        map.put("fileSize", String.valueOf(file.getSize()));
        map.put("fileContentType", file.getContentType());

        // File upload is successful
        map.put("message", "File upload done");

        byte[] bytes = file.getBytes();
        FileOutputStream fileOutputStream = new FileOutputStream("d:/Works/IT/uploadedFile.txt");
        fileOutputStream.write(bytes);
        fileOutputStream.close();

        return ResponseEntity.ok(map);
    }


    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUserAlternative(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(employeeService.getById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        try {
            return ResponseEntity.ok(employeeService.findAll());
        } catch(RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PutMapping("/users")
    public ResponseEntity<?> editById(@RequestBody Employee employee) {
        try {
            return ResponseEntity.ok(employeeService.save(employee));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/users/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        try {
            employeeService.deleteById(id);
            ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            ResponseEntity.badRequest().body(e.getMessage());
        }

    }

}
