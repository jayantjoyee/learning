package com.antheminc.oss.nimbus.tet;

import java.io.File;
import java.util.Properties;

import org.junit.Test;

import com.example.workspaceparser.ChangeVisitor;
import com.example.workspaceparser.RefactorConfig;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;

public class RefactorImportsTest {

	@Test
	public void test() throws Exception{
		RefactorConfig config = new RefactorConfig();
		Properties prop = new Properties();
		prop.load(RefactorImportsTest.class.getClassLoader().getResourceAsStream("classmapping.properties"));
		config.setClassNameMapping(prop);
		CompilationUnit compilationUnit = JavaParser.parse(new File("/Users/AC80637/Downloads/workspace-parser/src/test/java/com/antheminc/oss/nimbus/tet/Test1.java"));
		compilationUnit.accept(new ChangeVisitor(), config);
		System.out.println(compilationUnit.toString());
	}
	
	
}
