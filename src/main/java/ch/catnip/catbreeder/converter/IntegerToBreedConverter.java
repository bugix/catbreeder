package ch.catnip.catbreeder.converter;

import java.util.Locale;

import ch.catnip.catbreeder.model.Breed;

import com.vaadin.data.util.converter.Converter;

public class IntegerToBreedConverter implements Converter<Integer, Breed> {

    private static final long serialVersionUID = 1L;

    @Override
    public Breed convertToModel(Integer value, Class<? extends Breed> targetType, Locale locale) throws Converter.ConversionException {
        if (value == null) {
            return null;
        }

        return Breed.values()[value];
    }

    @Override
    public Integer convertToPresentation(Breed value, Class<? extends Integer> targetType, Locale locale) throws Converter.ConversionException {
        if (value == null) {
            return null;
        }
               
        return value.ordinal();
    }

    @Override
    public Class<Breed> getModelType() {
        return Breed.class;
    }

    @Override
    public Class<Integer> getPresentationType() {
        return Integer.class;
    }

}
