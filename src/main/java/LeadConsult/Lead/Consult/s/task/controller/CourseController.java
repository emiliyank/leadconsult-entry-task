package LeadConsult.Lead.Consult.s.task.controller;

import LeadConsult.Lead.Consult.s.task.entity.Person;
import LeadConsult.Lead.Consult.s.task.repository.CourseRepository;
import LeadConsult.Lead.Consult.s.task.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/course")
public class CourseController {
    private final CourseRepository courseRepository;
    private final PersonService personService;
    @GetMapping("/total-count")
    @ResponseStatus(HttpStatus.OK)
    public String getTotalCountOfCourseByType(){
        return "Total courses by type: "+courseRepository.countCoursesByType();
    }

    @GetMapping("/students")
    @ResponseStatus(HttpStatus.FOUND)
    public List<Person> getStudentsBySpecificCourse(@RequestParam("courseType")String courseType){
        return personService.getStudentsByCourseType(courseType);
    }

    @GetMapping("/students/older-than-and-by-course")
    @ResponseStatus(HttpStatus.FOUND)
    public List<Person> getStudentsOlderThanSpecificAgeAndParticipateInSpecificCourse(@RequestParam("age")int age ,@RequestParam("course")String course){
        return  personService.getStudentsOlderThanSpecificAgeAndParticipateInSpecificCourse(age , course);
    }

}
