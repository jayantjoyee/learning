/**
 * 
 */
package com.example.workspaceparser;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Properties;

import org.apache.commons.io.FileUtils;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;

public class OriganizeSource {
	
	private void copyFolder(String srcFolder, String destinationFolder, RefactorConfig config) throws Exception{
		final Path srcdir = Paths.get(srcFolder);
		final Path dstdir = Paths.get(destinationFolder);
		File f = new File(destinationFolder);
		f.mkdirs();
		Files.walk(srcdir)
        .forEach(source -> copy(source, dstdir.resolve(srcdir.relativize(source)), config));
	}
	
	private void reorganizeSrcFolder(String srcFolder, RefactorConfig config) throws Exception{
		StringBuilder srcPath = new StringBuilder(srcFolder);
		srcPath.append("/com/antheminc/oss/nimbus");
		StringBuilder targetPath = new StringBuilder(srcFolder);
		targetPath.append("/com/ts/flip");
		copyFolder(srcPath.toString(),targetPath.toString(), config);
		FileUtils.deleteDirectory(new File(srcFolder+"/com/antheminc"));
		
		Path directory = Paths.get(srcFolder);
		Files.walkFileTree(directory, new SimpleFileVisitor<Path>() {
			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
				if(file.toString().endsWith(".java")) {
					try {
						File javaFile = new File(file.toString());
						CompilationUnit compilationUnit = JavaParser.parse(javaFile);
						compilationUnit.accept(new ChangeVisitor(), config);
						FileUtils.forceDelete(javaFile);
						BufferedWriter writer = new BufferedWriter(new FileWriter(javaFile));
					    writer.write(compilationUnit.toString());
					    writer.close();
					} catch (Exception e) {
						System.out.println(file.toString());
						e.printStackTrace();
					}
				}
				return FileVisitResult.CONTINUE;
			}
		});
		
	}
	
	private void copy(Path source, Path dest, RefactorConfig config) {
	    try {
	        Files.copy(source, dest, StandardCopyOption.REPLACE_EXISTING);
	    } catch (Exception e) {
	        throw new RuntimeException(e.getMessage(), e);
	    }
	}
	
	public void copyFolder(RefactorConfig config, String baseDirectory) throws Exception{
		FileUtils.deleteDirectory(new File(baseDirectory+"/converted"));
		config.getFolderMapping().entrySet().iterator().forEachRemaining(entry -> {
			StringBuilder srcDirectory = new StringBuilder(baseDirectory);
			srcDirectory.append("/").append(entry.getKey());
			StringBuilder targetDirectory = new StringBuilder(baseDirectory);
			targetDirectory.append("/").append(entry.getValue());
			try {
				copyFolder(srcDirectory.toString(),targetDirectory.toString(), config);
				reorganizeSrcFolder(targetDirectory.toString()+"/main/java", config);
				reorganizeSrcFolder(targetDirectory.toString()+"/test/java", config);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
	
	public static void main(String[] args) {
		try {
			RefactorConfig config = new RefactorConfig();
			Properties prop = new Properties();
			prop.load(OriganizeSource.class.getClassLoader().getResourceAsStream("classmapping.properties"));
			config.setClassNameMapping(prop);
			OriganizeSource fc = new OriganizeSource();
			fc.copyFolder(config, "/Users/AC80637/git_auth/nimbus-core");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
