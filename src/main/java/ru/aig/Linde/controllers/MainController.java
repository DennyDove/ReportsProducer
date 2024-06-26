package ru.aig.Linde.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.aig.Linde.services.UpdatePricesService;
import ru.aig.Linde.services.MainService;

import java.io.IOException;

//@Controller
//@RequestMapping("/users")  - если повесить данную аннтацию на целый класс, то end-point "/users" появится у всех методов, объявленных в рамках данного класса
@RestController
public class MainController {

    @Autowired
    protected MainService mainService;

    @Autowired
    protected UpdatePricesService updatePricesService;

    @PostMapping(value = "/updprices")
    public ResponseEntity<?> updPrices(@RequestParam("file") MultipartFile[] file) throws IOException {
        updatePricesService.persistPricesDB(file);
        return ResponseEntity.ok("Prices successfully updated in the database");
    }

    @PostMapping(value = "/uploadoc")
    public HttpEntity<byte[]> uploadDoc(@RequestParam("file") MultipartFile[] file) throws IOException {

        byte[] result = mainService.documentHandler(file);

        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        header.setContentDisposition(ContentDisposition.attachment()
                .filename("test.docx").build());
        header.setContentLength(result.length);

        return new HttpEntity<byte[]>(result, header);
    }
    /*
    public ResponseEntity<?> uploadDoc(@RequestParam("file") MultipartFile[] file) throws IOException {

        mainService.documentHandler(file);
        return ResponseEntity.ok("Great!");
//        return ResponseEntity.ok().contentType(MediaType.MULTIPART_FORM_DATA)...
/*
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        InputStreamReader i = new InputStreamReader(new FileInputStream(xlsx));
        System.out.println("The length of the file is : "+file2Upload.length());

        return ResponseEntity.ok().headers(headers).contentLength(file2Upload.length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(i);
     }
     */
/*
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
*/

}
