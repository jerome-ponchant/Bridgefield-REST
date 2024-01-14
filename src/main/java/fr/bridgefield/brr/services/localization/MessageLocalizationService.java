package fr.bridgefield.brr.services.localization;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

public class MessageLocalizationService extends AbstractLocalizationService implements LocalizationService {

	private static final String BASE_NAME = "messages";
	Map<Locale,ResourceBundle> resourceMap = new HashMap<Locale, ResourceBundle>();;
	
	protected ResourceBundle getBundle(Locale locale) {
		ResourceBundle bundle = resourceMap.get(locale);
		if (bundle == null) {
			bundle = ResourceBundle.getBundle(BASE_NAME,locale);
			resourceMap.put(locale, bundle);
		}
		return bundle;
	}
}
