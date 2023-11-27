package com.tcc.moradiaestudantil.utils;

import java.text.MessageFormat;
import java.util.ResourceBundle;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MessagesPropertiesUtil {
	private static final ResourceBundle MESSAGES_BUNDLE = ResourceBundle.getBundle("messages");
	
	
	public static String getMessage(String key) {
		return MESSAGES_BUNDLE.getString(key);
	}
	
	public static String getMessage(String key, @NonNull Object... params) {
		return MessageFormat.format(getMessage(key), params);
	}
}
