package ch.catnip.catbreeder.converter;

import java.time.LocalDate;
import java.util.Date;

import ch.catnip.catbreeder.model.Breed;

import com.vaadin.data.util.converter.Converter;
import com.vaadin.data.util.converter.DefaultConverterFactory;

@SuppressWarnings("serial")
public class BreederConverterFactory extends DefaultConverterFactory {

	@SuppressWarnings("unchecked")
	@Override
	protected <PRESENTATION, MODEL> Converter<PRESENTATION, MODEL> findConverter(Class<PRESENTATION> presentationType, Class<MODEL> modelType) {
		
		// Handle String <-> LocalDate
		if (presentationType == String.class && modelType == LocalDate.class) {
			return (Converter<PRESENTATION, MODEL>) new StringToLocalDateConverter();
		}
		
		// Handle Date <-> LocalDate
		if (presentationType == Date.class && modelType == LocalDate.class) {
			return (Converter<PRESENTATION, MODEL>) new DateToLocalDateConverter();
		}
		
		// Handle Integer <-> Breed
		if (presentationType == Integer.class && modelType == Breed.class) {
			return (Converter<PRESENTATION, MODEL>) new IntegerToBreedConverter();
		}
		
		// Let default factory handle the rest
		return super.findConverter(presentationType, modelType);
	}

}
