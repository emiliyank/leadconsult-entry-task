package LeadConsult.Lead.Consult.s.task.repository;

import LeadConsult.Lead.Consult.s.task.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CourseRepository extends JpaRepository<Course , Long> {
    @Query("SELECT COALESCE(COUNT(c), 0) FROM Course c")
    int countCoursesByType();
}
