package ch.catnip.catbreeder.converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

import com.vaadin.data.util.converter.Converter;

public class StringToLocalDateConverter implements Converter<String, LocalDate> {

    private static final long serialVersionUID = 1L;

    @Override
    public LocalDate convertToModel(String value, Class<? extends LocalDate> targetType, Locale locale) throws Converter.ConversionException {
        if (value == null) {
            return null;
        }
        
        return LocalDate.parse(value, DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM).withLocale(locale));
    }

    @Override
    public String convertToPresentation(LocalDate value, Class<? extends String> targetType, Locale locale) throws Converter.ConversionException {
        if (value == null) {
            return null;
        }
        
        return value.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM).withLocale(locale));
    }

    @Override
    public Class<LocalDate> getModelType() {
        return LocalDate.class;
    }

    @Override
    public Class<String> getPresentationType() {
        return String.class;
    }

}
