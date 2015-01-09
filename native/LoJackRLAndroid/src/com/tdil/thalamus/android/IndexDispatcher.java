package com.tdil.thalamus.android;

public class IndexDispatcher {

	public static Class getIndexClass() {
		System.out.println("version" + android.os.Build.VERSION.SDK_INT);
		if (android.os.Build.VERSION.SDK_INT < 11) {
			return IndexActivityPreAndroid3.class;
		} else {
			return IndexActivity.class;
		}
	}
}
