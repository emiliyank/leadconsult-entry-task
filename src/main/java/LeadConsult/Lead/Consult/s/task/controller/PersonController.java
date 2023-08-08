package LeadConsult.Lead.Consult.s.task.controller;

import LeadConsult.Lead.Consult.s.task.dto.PersonDto;
import LeadConsult.Lead.Consult.s.task.entity.Person;
import LeadConsult.Lead.Consult.s.task.repository.PersonRepository;
import LeadConsult.Lead.Consult.s.task.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/person")
public class PersonController {
    private final PersonRepository personRepository;

    private final PersonService personService;
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createPerson(@Validated @RequestBody PersonDto personDto){
        personService.createPerson(personDto);
    }

    @PutMapping("/edit/{personId}")
    @ResponseStatus(HttpStatus.OK)
    public void editPerson(@PathVariable("personId")Long personId ,@Validated @RequestBody PersonDto personDto){
        personService.editPerson(personId , personDto);
    }

    @DeleteMapping("/delete/{personId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePersonById(@PathVariable("personId")Long personId){
        personService.removePerson(personId);
    }
    @GetMapping("/students/total-count")
    @ResponseStatus(HttpStatus.OK)
    public String getStudentCount(){
        return "Total count of students: "+ personRepository.findStudentTotalCount();
    }

    @GetMapping("/teachers/total-count")
    @ResponseStatus(HttpStatus.OK)
    public String  getTeacherCount(){
        return "Total count of teachers: " + personRepository.findTeacherTotalCount();
    }

    @GetMapping("/all/persons/for/course/class")
    @ResponseStatus(HttpStatus.FOUND)
    public List<Person> getAllStudentsAndTeachersInSpecificCourseAndGroup(@RequestParam("course")String course ,@RequestParam("class")String className){
    return personService.getAllStudentsAndTeachersForSpecificClassAndCourse(course , className);
    }
}
