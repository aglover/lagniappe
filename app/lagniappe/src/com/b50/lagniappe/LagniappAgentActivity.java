package com.b50.lagniappe;

public class LagniappAgentActivity extends org.ruboto.RubotoActivity {
	public void onCreate(android.os.Bundle arg0) {
		try {
			setSplash(Class.forName("com.b50.lagniappe.R$layout").getField("splash")
					.getInt(null));
		} catch (Exception e) {
		}

		setScriptName("lagniapp_activity.rb");
		super.onCreate(arg0);
	}
}
