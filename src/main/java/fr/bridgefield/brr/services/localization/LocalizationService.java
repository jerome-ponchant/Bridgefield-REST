package fr.bridgefield.brr.services.localization;

import java.util.Locale;

public interface LocalizationService {

	String getMessage(String key, Locale locale, Object... args);

}