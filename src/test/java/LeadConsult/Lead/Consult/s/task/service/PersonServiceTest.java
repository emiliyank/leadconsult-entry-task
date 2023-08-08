package LeadConsult.Lead.Consult.s.task.service;

import LeadConsult.Lead.Consult.s.task.dto.PersonDto;
import LeadConsult.Lead.Consult.s.task.entity.Person;
import LeadConsult.Lead.Consult.s.task.entity.PersonType;
import LeadConsult.Lead.Consult.s.task.repository.PersonRepository;
import LeadConsult.Lead.Consult.s.task.repository.PersonTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {
    @Mock
    private PersonRepository personRepository;
    @Mock
    private PersonTypeRepository personTypeRepository;
    @InjectMocks
    private PersonService classUnderTest;

    private PersonType studentType;

    private PersonType teacherType;


    @BeforeEach
    void setUp(){
        studentType = new PersonType();
        studentType.setType("student");
        teacherType = new PersonType();
        teacherType.setType("teacher");

    }

    @Test
    public void testCreatePersonWhenValidFields() {
        PersonType studentType = new PersonType(1L, LeadConsult.Lead.Consult.s.task.enums.PersonType.STUDENT.toString());
        when(personTypeRepository.findByType(any())).thenReturn(studentType);

        PersonDto personDto = new PersonDto();
        personDto.setName("John");
        personDto.setAge(25);
        personDto.setType("student");

        classUnderTest.createPerson(personDto);

        verify(personTypeRepository, times(1)).findByType(any());
        verify(personRepository, times(1)).save(any());
    }

    @Test
    public void testEditPerson() {
        PersonType teacherType = new PersonType(2L, LeadConsult.Lead.Consult.s.task.enums.PersonType.TEACHER.toString());
        when(personTypeRepository.findByType(any())).thenReturn(teacherType);

        Person personToEdit = new Person();
        personToEdit.setId(1L);
        personToEdit.setName("Alice");
        personToEdit.setAge(30);
        personToEdit.setTypeId(teacherType);

        when(personRepository.findById(1L)).thenReturn(Optional.of(personToEdit));

        PersonDto personDto = new PersonDto();
        personDto.setName("Bob");
        personDto.setAge(28);
        personDto.setType("teacher");

        classUnderTest.editPerson(1L, personDto);

        verify(personTypeRepository, times(1)).findByType(any());
        verify(personRepository, times(1)).findById(any());
        verify(personRepository, times(1)).save(any());
    }

    @Test
    public void testRemovePerson() {
        Person personToDelete = new Person();
        personToDelete.setId(1L);
        personToDelete.setName("Eve");
        personToDelete.setAge(22);
        personToDelete.setTypeId(new PersonType(3L, LeadConsult.Lead.Consult.s.task.enums.PersonType.STUDENT.toString()));

        when(personRepository.findById(1L)).thenReturn(Optional.of(personToDelete));

        classUnderTest.removePerson(1L);

        verify(personRepository, times(1)).findById(any());
        verify(personRepository, times(1)).delete(any());
    }

    @Test
    public void testGetAllStudentsAndTeachersForSpecificClassAndCourse() {

        List<Person> mockPersons = Arrays.asList(
                new Person("Alice", 25, studentType),
                new Person("Bob", 30, teacherType)
        );

        when(personRepository.findAllStudentsAndTeachersForSpecificClassAndCourse("Math", "ClassA")).thenReturn(mockPersons);

        List<Person> result = classUnderTest.getAllStudentsAndTeachersForSpecificClassAndCourse("Math", "ClassA");

        assertEquals(2, result.size());
        assertEquals("Alice", result.get(0).getName());
        assertEquals("Bob", result.get(1).getName());

        verify(personRepository, times(1)).findAllStudentsAndTeachersForSpecificClassAndCourse(any(), any());
    }

    @Test
    public void testGetStudentsByClassName() {
        List<Person> mockStudents = Arrays.asList(
                new Person( "Alice", 25, studentType),
                new Person( "Charlie", 30, studentType)
        );

        when(personRepository.findStudentsByClassName("ClassA")).thenReturn(mockStudents);

        List<Person> result = classUnderTest.getStudentsByClassName("ClassA");

        assertEquals(2, result.size());
        assertEquals("Alice", result.get(0).getName());
        assertEquals("Charlie", result.get(1).getName());

        verify(personRepository, times(1)).findStudentsByClassName(any());
    }
    @Test
    public void testGetStudentsByCourseType() {
        List<Person> mockStudents = Arrays.asList(
                new Person( "David", 28, studentType),
                new Person( "Eve", 24, studentType)
        );

        when(personRepository.findStudentsByCourseType("Math")).thenReturn(mockStudents);

        List<Person> result = classUnderTest.getStudentsByCourseType("Math");

        assertEquals(2, result.size());
        assertEquals("David", result.get(0).getName());
        assertEquals("Eve", result.get(1).getName());

        verify(personRepository, times(1)).findStudentsByCourseType(any());
    }

    @Test
    public void testGetStudentsOlderThanSpecificAgeAndParticipateInSpecificCourse() {
        int age= 25;
        List<Person> mockStudents = Arrays.asList(
                new Person( "Frank", 28, studentType),
                new Person( "Grace", 30, studentType)
        );

        when(personRepository.findStudentsOlderBySpecificAgeAndParticipateInSpecificCourse("Math", age)).thenReturn(mockStudents);

        List<Person> result = classUnderTest.getStudentsOlderThanSpecificAgeAndParticipateInSpecificCourse(age, "Math");

        assertEquals(2, result.size());
        assertEquals("Frank", result.get(0).getName());
        assertEquals("Grace", result.get(1).getName());

        verify(personRepository, times(1)).findStudentsOlderBySpecificAgeAndParticipateInSpecificCourse("Math", age);
    }


}
