package com.acc.action;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
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

	public static void main(String[] args) throws Exception, Exception {
		try {

			// Read a class
			// TODO: If this works, read the entire folder structure and apply rules
			//

			int numberOfActionFiles = new File("src/main/java/com/acc/action/").listFiles().length;
			File[] files = new File("src/main/java/com/acc/action/").listFiles();
			for (int i = 0; i < numberOfActionFiles; i++) {
				String legacyCode = readClass(files[i].getName());

				// Check if source class extends Action
				boolean needsRefactoring = legacyCode.contains("extends Action") && !"StrutsActionConverter.java".equals(files[i].getName());

				if (needsRefactoring) {
					// Copy the contents between {}
					int methodIndex = legacyCode.indexOf("{", legacyCode.indexOf("{") + 1);
					String methodLines = legacyCode.substring(methodIndex + 1);
					methodLines = methodLines.substring(0, methodLines.length() - 2);
					// System.out.println("Method lines " + methodLines.substring(0,
					// methodLines.length()-2));
					System.out.println("Attempting to generate controller for " + files[i].getName());
					Class clazz = generateClass(legacyCode, files[i].getName(), methodName, methodLines);
					System.out.println("Class generated but will require manual fixes: " + clazz.getName());
				} else {
					System.out.println("No need to refactor... " + files[i].getName());
				}
			}
		} finally {

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

	public static Class generateClass(String legacyCode, String className, String methodName, String methodLines)
			throws CannotCompileException, Exception, IOException {
		ClassPool pool = ClassPool.getDefault();
		pool.importPackage("com.acc.form");
		pool.importPackage("com.acc.service");
		pool.importPackage("org.apache.struts.action");
		pool.importPackage("org.springframework.ui");
		pool.importPackage("javax.servlet.http");
		int len = className.length();
		className = className.substring(0, len - 5);
		CtClass cc = pool.makeClass(className);
		ClassFile cfile = cc.getClassFile();
		ConstPool cpool = cfile.getConstPool();
		StringBuffer method = new StringBuffer();

		AnnotationsAttribute attr = new AnnotationsAttribute(cpool, AnnotationsAttribute.visibleTag);
		// Adding a controller annotation
		Annotation annot = new Annotation(controllerAnnotation, cpool);
		attr.addAnnotation(annot);
		cc.getClassFile().addAttribute(attr);

		// TODO: playaround
		method.append("public String ").append(methodName).append(
				"(ActionForm form, Model model, ActionMapping mapping, HttpServletRequest request) {");
		String replaceReturnStatement = StringUtils.replace(methodLines, "mapping.findForward(\"success\")",
				"\"view\"");
		methodLines = replaceReturnStatement;
		method.append(methodLines);
		method.append("}");
		//System.out.println(method.toString());
		cc.addMethod(CtMethod.make(method.toString(), cc));
		cc.writeFile();
		return cc.toClass();
	}

}