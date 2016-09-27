package com.agreeya.chhs.util;

/**
 * Utility cast to separate unsafe type cast from other code that requires warning-free compiles.
 * @author AgreeYa Solutions
 */
public final class TypeCast {

	private TypeCast() {
		// prevent instantiation
	}

	@SuppressWarnings("unchecked")
	public static <T> T cast(Object obj) {
		return (T) obj; // unchecked cast: OK
	}
}
