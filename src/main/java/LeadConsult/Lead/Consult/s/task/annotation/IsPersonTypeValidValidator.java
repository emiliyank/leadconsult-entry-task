package LeadConsult.Lead.Consult.s.task.annotation;

import LeadConsult.Lead.Consult.s.task.repository.PersonTypeRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class IsPersonTypeValidValidator implements ConstraintValidator<IsPersonTypeValid ,String> {

    private final PersonTypeRepository personTypeRepository;
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return personTypeRepository.findByType(s) != null;
    }
}
