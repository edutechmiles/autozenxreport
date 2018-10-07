package utility;

import java.util.HashMap;
import java.util.Map;

public class SystemProperties {

	private String releaseVersion;
	private String runOnMachine;
	private String browser;
	private String environment;
	private Map<String,String> systemInfoMap = new HashMap<String, String>();
	
	
	
	
	public Map<String, String> getSystemInfoMap() {
		return systemInfoMap;
	}

	public void addSystemInfo(String key, String value) {
		systemInfoMap.put(key, value);
		setSystemInfoMap(systemInfoMap);
	}

	public void setSystemInfoMap(Map<String, String> systemInfoMap) {
		this.systemInfoMap = systemInfoMap;
	}
	
}
