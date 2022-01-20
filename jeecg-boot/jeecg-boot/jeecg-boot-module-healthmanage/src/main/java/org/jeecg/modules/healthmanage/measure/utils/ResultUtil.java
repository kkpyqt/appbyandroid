package org.jeecg.modules.healthmanage.measure.utils;

public class ResultUtil {
	public static <T> Message<T> success() {
		return new Message<T>(200, "success");
	}

	public static <T> Message<T> success(T result, String msg) {
		return new Message<T>(200, result, msg);
	}

	public static <T> Message<T> success(T result) {
		return new Message<T>(200, result);
	}

	public static <T> Message<T> success(String msg) {
		return new Message<T>(200, msg);
	}

	////////////////////////////////////////////////////////////
	public static <T> Message<T> fail() {
		return new Message<T>(500, "fail");
	}

	public static <T> Message<T> fail(T result, String msg) {
		return new Message<T>(500, result, msg);
	}

	public static <T> Message<T> fail(T result) {
		return new Message<T>(500, result);
	}

	public static <T> Message<T> fail(String msg) {
		return new Message<T>(500, msg);
	}
}