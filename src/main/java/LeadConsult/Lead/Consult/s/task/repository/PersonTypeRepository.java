package LeadConsult.Lead.Consult.s.task.repository;

import LeadConsult.Lead.Consult.s.task.entity.PersonType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonTypeRepository extends JpaRepository<PersonType , Long> {
    PersonType findByType(String type);
}
