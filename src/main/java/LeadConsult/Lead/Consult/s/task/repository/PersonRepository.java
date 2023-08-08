package LeadConsult.Lead.Consult.s.task.repository;

import LeadConsult.Lead.Consult.s.task.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {
    @Query("SELECT COALESCE(COUNT(s), 0) FROM Person s WHERE s.typeId.type = 'Student'")
    int findStudentTotalCount();

    @Query("SELECT COALESCE(COUNT(t), 0) FROM Person t WHERE t.typeId.type = 'Teacher'")
    int findTeacherTotalCount();

    @Query("SELECT DISTINCT p FROM Person p " +
            "JOIN Class c ON p IN ELEMENTS(c.people) " +
            "WHERE c.name = :className AND p.typeId.type = 'Student'")
    List<Person> findStudentsByClassName(String className);

    @Query("SELECT DISTINCT p FROM Person p " +
            "JOIN Class c ON p IN ELEMENTS(c.people) " +
            "JOIN Course co ON p IN ELEMENTS(co.people) " +
            "WHERE c.name = :className AND co.type = :course")
    List<Person> findAllStudentsAndTeachersForSpecificClassAndCourse(String course, String className);

    @Query("SELECT DISTINCT p FROM Person p " +
            "JOIN Course c ON p IN ELEMENTS (c.people) " +
            "WHERE c.type = :courseType AND p.typeId.type = 'Student'")
    List<Person> findStudentsByCourseType(String courseType);


    @Query("SELECT DISTINCT p FROM Person p " +
            "JOIN Course c ON p IN ELEMENTS (c.people) " +
            "WHERE c.type = :courseType AND p.typeId.type = 'Student' AND p.age > :age")
    List<Person> findStudentsOlderBySpecificAgeAndParticipateInSpecificCourse(String courseType , int age);
}
