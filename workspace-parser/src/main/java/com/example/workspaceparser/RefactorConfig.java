package com.example.workspaceparser;

import java.util.Properties;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RefactorConfig {
	
	private Properties classNameMapping;
	private String originalPackage = "com.antheminc.oss.nimbus";
	private String refactoredPackage = "com.ts.flip";
	
	public String getRefactoredClass(String originalClass) {
		return classNameMapping.getProperty(originalClass);
	}

}
