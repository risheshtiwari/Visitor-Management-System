package io.bootify.minor_project.model;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;

import io.bootify.minor_project.service.VisitorService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;
import org.springframework.web.servlet.HandlerMapping;


/**
 * Validate that the phone value isn't taken yet.
 */
@Target({ FIELD, METHOD, ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(
        validatedBy = VisitorPhoneUnique.VisitorPhoneUniqueValidator.class
)
public @interface VisitorPhoneUnique {

    String message() default "{Exists.visitor.phone}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class VisitorPhoneUniqueValidator implements ConstraintValidator<VisitorPhoneUnique, String> {

        private final VisitorService visitorService;
        private final HttpServletRequest request;

        public VisitorPhoneUniqueValidator(final VisitorService visitorService,
                final HttpServletRequest request) {
            this.visitorService = visitorService;
            this.request = request;
        }

        @Override
        public boolean isValid(final String value, final ConstraintValidatorContext cvContext) {
            if (value == null) {
                // no value present
                return true;
            }
            @SuppressWarnings("unchecked") final Map<String, String> pathVariables = 
                    ((Map<String, String>)request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE));
            final String currentId = pathVariables.get("id");
            if (currentId != null && value.equalsIgnoreCase(visitorService.get(Long.parseLong(currentId)).getPhone())) {
                // value hasn't changed
                return true;
            }
            return !visitorService.phoneExists(value);
        }

    }

}
