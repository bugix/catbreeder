package ch.catnip.catbreeder.converter;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

import com.vaadin.data.util.converter.Converter;

public class DateToLocalDateConverter implements Converter<Date, LocalDate> {

    private static final long serialVersionUID = 1L;

    @Override
    public LocalDate convertToModel(Date value, Class<? extends LocalDate> targetType, Locale locale) throws Converter.ConversionException {
        if (value == null) {
            return null;
        }
        
        Instant instant = Instant.ofEpochMilli(value.getTime());
        
        return LocalDate.from(instant);
    }

    @Override
    public Date convertToPresentation(LocalDate value, Class<? extends Date> targetType, Locale locale) throws Converter.ConversionException {
        if (value == null) {
            return null;
        }
        
        Instant instant = null;
        value.adjustInto(instant);
        
        return Date.from(instant);
    }

    @Override
    public Class<LocalDate> getModelType() {
        return LocalDate.class;
    }

    @Override
    public Class<Date> getPresentationType() {
        return Date.class;
    }

}
