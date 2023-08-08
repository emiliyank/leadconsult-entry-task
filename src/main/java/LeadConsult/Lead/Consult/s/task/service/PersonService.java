package LeadConsult.Lead.Consult.s.task.service;

import LeadConsult.Lead.Consult.s.task.dto.PersonDto;
import LeadConsult.Lead.Consult.s.task.entity.Person;
import LeadConsult.Lead.Consult.s.task.entity.PersonType;
import LeadConsult.Lead.Consult.s.task.repository.PersonRepository;
import LeadConsult.Lead.Consult.s.task.repository.PersonTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonService {
    private final PersonRepository personRepository;
    private final PersonTypeRepository personTypeRepository;

    public List<Person> getAllStudentsAndTeachersForSpecificClassAndCourse(String course, String className) {

        return personRepository.findAllStudentsAndTeachersForSpecificClassAndCourse(course, className);
    }

    public List<Person> getStudentsByClassName(String className) {
        return personRepository.findStudentsByClassName(className);

    }

    public List<Person> getStudentsByCourseType(String courseType) {
        return personRepository.findStudentsByCourseType(courseType);
    }

    public List<Person> getStudentsOlderThanSpecificAgeAndParticipateInSpecificCourse(int age, String courseType) {
        return personRepository.findStudentsOlderBySpecificAgeAndParticipateInSpecificCourse(courseType, age);
    }

    public void createPerson(PersonDto personDto) {
        Person personToSave = new Person();
        personToSave.setName(personDto.getName());
        personToSave.setAge(personDto.getAge());
        PersonType type = personTypeRepository.findByType(personDto.getType());
        personToSave.setTypeId(type);
        personRepository.save(personToSave);
    }

    public void editPerson(Long personId, PersonDto personDto) {
        Person personToEdit = getPerson(personId);
        personToEdit.setName(personDto.getName());
        personToEdit.setAge(personDto.getAge());
        PersonType type = personTypeRepository.findByType(personDto.getType());
        personToEdit.setTypeId(type);
        personRepository.save(personToEdit);
    }

    public void removePerson(Long personId) {
        Person personToDelete = getPerson(personId);

        personRepository.delete(personToDelete);
    }

    private Person getPerson(Long personId) {
        return personRepository.findById(personId)
                               .orElseThrow(() -> new RuntimeException("There isn't person with id= " + personId + " in our data base"));
    }
}
