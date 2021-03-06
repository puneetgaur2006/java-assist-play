package com.acc.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.util.StringUtils;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.ClassFile;
import javassist.bytecode.ConstPool;
import javassist.bytecode.annotation.Annotation;

public class StrutsActionConverter {

	private static final String controllerAnnotation = "org.springframework.stereotype.Controller";
	private static final String methodName = "execute";
	private static final String modelPackage = "org.springframework.ui";

	public static void main(String[] args) throws Exception, Exception {
		BufferedReader br = null;
		try {
			int numberOfActionFiles = new File("src/main/java/com/acc/action/").listFiles().length;
			File[] files = new File("src/main/java/com/acc/action/").listFiles();
			// Reading class as a file
			List<String> imports = new ArrayList<String>();
			for (int iCount = 0; iCount < numberOfActionFiles; iCount++) {
				br = new BufferedReader(new FileReader(files[iCount]));
				String st;

				while ((st = br.readLine()) != null) {
					if (st.startsWith("import")) {
						// Capture the string
						imports.add(st);
					}
				}
			}
			// Reading and storing class as string
			for (int i = 0; i < numberOfActionFiles; i++) {
				String legacyCode = readClass(files[i].getName());

				// Check if source class extends Action
				boolean needsRefactoring = legacyCode.contains("extends Action")
						&& !"StrutsActionConverter.java".equals(files[i].getName());

				if (needsRefactoring) {
					// Copy the contents between {}
					int methodIndex = legacyCode.indexOf("{", legacyCode.indexOf("{") + 1);
					String methodLines = legacyCode.substring(methodIndex + 1);
					methodLines = methodLines.substring(0, methodLines.length() - 2);
					System.out.println("Attempting to generate controller for " + files[i].getName());
					Class<?> clazz = generateClass(legacyCode, files[i].getName(), methodName, methodLines, imports);
					System.out.println("Class generated but will require manual fixes: " + clazz.getName());
					System.out.println("------------------------------------");
				} else {
					System.out.println("No need to refactor... " + files[i].getName());
				}
			}
		} finally {
			br.close();
		}
	}

	public static String readClass(String className) throws IOException {
		Scanner scanner = null;
		StringBuilder sb = new StringBuilder();
		try {
			Path path = Paths.get("src/main/java/com/acc/action/" + className);
			scanner = new Scanner(path);
			while (scanner.hasNextLine()) {
				sb.append(scanner.nextLine());
			}
		} finally {
			scanner.close();
		}
		return sb.toString();
	}

	public static Class<?> generateClass(String legacyCode, String className, String methodName, String methodLines,
			List<String> imports) throws CannotCompileException, Exception, IOException {
		ClassPool pool = ClassPool.getDefault();
		String s = null;
		// Read package import statements and configure the pool
		for (String packageName : imports) {
			String processedPackage = packageName.substring(7, packageName.indexOf(";"));
			char[] ch = processedPackage.toCharArray();
			char cj[] = new char[25000];//TODO: Make this dynamic
			for (int i = 0; i < ch.length; i++) {
				if (Character.isUpperCase(ch[i])) {
					break;
				}
				cj[i] = ch[i];
				s = new String(cj);
			}
			s = s.trim();
			String finalPackage = s.substring(0, s.length() - 1);
			pool.importPackage(finalPackage);
			pool.importPackage(modelPackage);// This is required to access Model object. Adding as default
		}
		int len = className.length();
		className = className.substring(0, len - 5);
		className = StringUtils.replace(className, "Action", "Controller");
		CtClass cc = pool.makeClass(className);
		ClassFile cfile = cc.getClassFile();
		ConstPool cpool = cfile.getConstPool();
		StringBuffer method = new StringBuffer();

		AnnotationsAttribute attr = new AnnotationsAttribute(cpool, AnnotationsAttribute.visibleTag);
		// Adding a controller annotation
		Annotation annot = new Annotation(controllerAnnotation, cpool);
		attr.addAnnotation(annot);
		cc.getClassFile().addAttribute(attr);

		method.append("public String ").append(methodName)
				.append("(ActionForm form, Model model, ActionMapping mapping, HttpServletRequest request) {");
		String replaceReturnStatement = StringUtils.replace(methodLines, "mapping.findForward(\"success\")",
				"\"view\"");
		methodLines = replaceReturnStatement;
		method.append(methodLines);
		method.append("}");
		cc.addMethod(CtMethod.make(method.toString(), cc));
		cc.writeFile();
		return cc.toClass();
	}

}