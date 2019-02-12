package com.antheminc.oss.nimbus.tet;

import java.util.List;

import com.antheminc.oss.nimbus.domain.defn.Domain;
import com.antheminc.oss.nimbus.domain.defn.Execution.Config;
import com.antheminc.oss.nimbus.domain.defn.Model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Domain(value="test")
@Model
public class Test1 {
	
	@Config(url="test")
	private String test1;
	
	@Deprecated
	public List<String> getString(String test, Model m){
		Domain d = null;
		Domain domain = org.springframework.core.annotation.AnnotationUtils.findAnnotation(Test1.class, Domain.class);
		return null;
	}
	
	@Model
	public class Test3{
		@Config(url="test")
		private String test1;
		
	}
	

}
