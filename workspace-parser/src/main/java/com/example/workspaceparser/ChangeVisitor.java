/**
 * 
 */
package com.example.workspaceparser;

import org.springframework.util.StringUtils;

import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.expr.LambdaExpr;
import com.github.javaparser.ast.expr.MarkerAnnotationExpr;
import com.github.javaparser.ast.expr.MethodReferenceExpr;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.expr.NormalAnnotationExpr;
import com.github.javaparser.ast.expr.TypeExpr;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.visitor.ModifierVisitorAdapter;


public class ChangeVisitor extends ModifierVisitorAdapter<RefactorConfig> {
	
	@Override
	public Node visit(ImportDeclaration importDeclaration, RefactorConfig config) {
		String importName = importDeclaration.getName().toString();
		if(importName.startsWith(config.getOriginalPackage())) {
			importName = importName.replace(config.getOriginalPackage(), config.getRefactoredPackage());
			String[] imports = importName.split("\\.");
			String className = imports[imports.length-1];
			String refactoredClass = config.getRefactoredClass(className);
			StringBuilder importString = new StringBuilder();
			if(StringUtils.isEmpty(refactoredClass)) {
				importString .append(importName);
			}else {
				for(int i= 0 ; i< (imports.length-1); i++) {
					if(i != 0)
						importString.append(".");
					importString.append(imports[i]);
				}
				importString.append(".").append(refactoredClass);			
			}
			importDeclaration.setName(new NameExpr(importString.toString()));
		}
		return super.visit(importDeclaration, config);
	}
	
	@Override
	public Node visit(MarkerAnnotationExpr annotationExpr, RefactorConfig config) {
		processAnnotation(annotationExpr, config);
		return super.visit(annotationExpr, config);
	}
	
	@Override
	public Node visit(NormalAnnotationExpr annotationExpr, RefactorConfig config) {
		processAnnotation(annotationExpr, config);
		return super.visit(annotationExpr, config);
	}

	@Override
	public Node visit(PackageDeclaration pd, RefactorConfig config) {
		String packageName = pd.getName().toString();
		packageName = packageName.replace(config.getOriginalPackage(), config.getRefactoredPackage());
		pd.setName(new NameExpr(packageName));		
		return super.visit(pd,config);
	}
	
	
	private void processAnnotation(AnnotationExpr annotationExpr, RefactorConfig config) {
		String annotationClass = annotationExpr.getName().toString();
		String refatoredAnnotationClass = config.getRefactoredClass(annotationClass);
		if(!StringUtils.isEmpty(refatoredAnnotationClass)) {
			annotationExpr.setName(new NameExpr(refatoredAnnotationClass));
		}
	}

	@Override
	public Node visit(ClassOrInterfaceType classType, RefactorConfig config) {
		String className = classType.getName();
		String refactoredClassName = config.getRefactoredClass(className);
		if(!StringUtils.isEmpty(refactoredClassName))
			classType.setName(refactoredClassName);
		return super.visit(classType, config);
	}
	
	@Override
	public Node visit(ClassOrInterfaceDeclaration classDeclaration, RefactorConfig config) {
		String className = classDeclaration.getName();
		String refactoredClassName = config.getRefactoredClass(className);
		if(!StringUtils.isEmpty(refactoredClassName))
			classDeclaration.setName(refactoredClassName);		
		return super.visit(classDeclaration, config);
	}

	@Override
	public Node visit(LambdaExpr n, RefactorConfig arg) {
		return null;
	}

	@Override
	public Node visit(MethodReferenceExpr n, RefactorConfig arg) {
		return null;
	}

	@Override
	public Node visit(TypeExpr n, RefactorConfig arg) {
		return null;
	}

}
