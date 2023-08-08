package LeadConsult.Lead.Consult.s.task.controller;

import LeadConsult.Lead.Consult.s.task.entity.Person;
import LeadConsult.Lead.Consult.s.task.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/class")
public class ClassController {
    private final PersonService personService;

    @GetMapping("/students")
    @ResponseStatus(HttpStatus.FOUND)
    public List<Person> getStudentsBySpecificClass(@RequestParam("class")String className){
        return personService.getStudentsByClassName(className);
    }
}
