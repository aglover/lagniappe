package com.b50.lagniappe;

import com.app47.embeddedagent.EmbeddedAgent;

// 08-27 22:14:24.836: WARN/EA(3155): 1.0
// 08-27 22:14:24.836: WARN/EA(3155): 10
// 08-27 22:14:24.846: INFO/EA(3155): Agent run for the first time, making POST request for update
// 08-27 22:14:25.146: WARN/System.err(3155): {"status":"CONFIG-2","message":"device_id  cannot be nil"}
// 08-27 22:14:25.146: DEBUG/EA(3155): Problem with config connection, no response
// 08-27 22:26:50.656: WARN/System.err(3213): {"status":"CONFIG-1","message":"Invalid app_id 4e599b34d731dc000100000d"}
//08-27 22:33:35.506: WARN/EA(3260):   "agent_id": "4e59a9754f5a500001000002",


public class LagniappActivity extends LagniappAgentActivity {
	public void onCreate(android.os.Bundle arg0) {	
		super.onCreate(arg0);		
		EmbeddedAgent.configureAgentWithAppID(this, "4e599b34d731dc000100000d");
	}
}
