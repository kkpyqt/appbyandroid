package org.jeecg.modules.healthmanage.measure.utils;

import java.util.UUID;

public class CommonUtil {
	public static String getUUID() {
		UUID uuid = UUID.randomUUID();
		String str = uuid.toString();
		String uuidStr = str.replace("-", "");
		return uuidStr;
	}
	public static int getSixBitRandomNumbe() {
		return (int) ((Math.random() * 9 + 1) * 100000);
	}
}
