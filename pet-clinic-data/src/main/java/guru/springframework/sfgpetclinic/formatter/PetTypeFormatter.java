package guru.springframework.sfgpetclinic.formatter;

import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.service.PetTypeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;
import java.util.Set;

/**
 * Default behaviour was trying to generate PetType using the String and putting to Id property
 * so automatically Spring parsing was failing, because this component was not implemented
 *
 * @author Krzysztof Kukla
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class PetTypeFormatter implements Formatter<PetType> {

    private final PetTypeService petTypeService;

    @Override
    public String print(PetType petType, Locale locale) {
        return petType.getName();
    }

    @Override
    public PetType parse(String text, Locale locale) throws ParseException {
        Set<PetType> petTypes = petTypeService.findAll();
        return petTypes.stream()
            .filter(p -> p.getName().equals(text))
            .findFirst()
            .orElseThrow(() -> new ParseException("type not found " + text, 0));
//        for (PetType petType : petTypes) {
//            if (petType.getName().equals(text)) {
//                return petType;
//            }
//        }
//        throw new ParseException("type not found " + text, 0);
    }

}
