package fr.bridgefield.brr.services.localization;

import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

public abstract class AbstractLocalizationService implements LocalizationService {

	protected abstract  ResourceBundle getBundle(Locale locale);
	
	@Override
	public String getMessage(String key, Locale locale, Object... args ) {
		return getBundle(locale).getString(key).formatted(args);
	}
	

}
