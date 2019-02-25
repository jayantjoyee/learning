package com.example.workspaceparser;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RefactorConfig {
	
	private Properties classNameMapping;
	private String originalPackage = "com.antheminc.oss.nimbus";
	private String refactoredPackage = "com.ts.flip";
	private Map<String,String> folderMapping = new HashMap<String,String>();
	
	public RefactorConfig() {
		folderMapping.put("nimbus-core/src", "converted/ts-runtime/src");
		folderMapping.put("nimbus-test/src", "converted/ts-test/src");
	}
	
	public String getRefactoredClass(String originalClass) {
		return classNameMapping.getProperty(originalClass);
	}

}
