package com.ee.ctp;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import junit.framework.TestCase;

public class DtoMakeTest extends TestCase{

	private static final String RSP_CLASS_NAME = "RspUserPasswordUpdate";
	private static final String REQCLASSNAME = "ReqUserPasswordUpdate";
	private static Map<String, Integer> map = new HashMap<>();
	private static final String LINE = System.getProperty("line.separator");
	public void testMakeRsp() throws Exception {
		InputStream resourceAsStream = DtoMakeTest.class.getClassLoader().getResourceAsStream("ThostFtdcUserApiDataType.h");
		InputStream resourceAsStream2 = DtoMakeTest.class.getClassLoader().getResourceAsStream("dto.rsp");
		
		List<String> readLines = IOUtils.readLines(resourceAsStream);
		for (String line : readLines) {
			if(StringUtils.isNotBlank(line)) {
				if(line.startsWith("typedef")) {
					String subLine = line.replace(";", "");
					String[] split = subLine.split(" ");
					String type = split[1];
					String name = split[2];
					int value = 0;
					switch(type) {
					case "int":
						value = 4;
						break;
					case "double":
						value = 8;
						break;
					case "char":
						if(name.contains("[")) {
							int first = name.indexOf("[");
							int second = name.indexOf("]");
							value = Integer.parseInt(name.substring(first + 1, second));
							name = name.substring(0, first);
						}else {
							value = 1;
						}
						break;
					default:
						break;
					}
					map.put(name, value);
				}
			}
		}
		
		StringBuilder propertiesAll = new StringBuilder();
		StringBuilder methodParseFrom = new StringBuilder();
		StringBuilder classAll = new StringBuilder();
		classAll.append("public class " + RSP_CLASS_NAME + " {").append(LINE);
		
		methodParseFrom.append("public static " + RSP_CLASS_NAME + " parseFrom(ByteBuf body) {").append(LINE);
		methodParseFrom.append(RSP_CLASS_NAME + " info = new " + RSP_CLASS_NAME + "();").append(LINE);
		
		Map<String, Integer> propMap = new HashMap<>();
		List<String> readLines2 = IOUtils.readLines(resourceAsStream2);
		for (String line : readLines2) {
			if(StringUtils.isEmpty(line)) {
				continue;
			}
			if(line.startsWith("struct") || line.startsWith("{") | line.startsWith("}") || line.contains("/")) {
				continue;
			}
			
			String subLine = line.replace(";", "");
			String[] split = subLine.split("\t");
			if(split.length == 3) {
				String name = StringUtils.trimToEmpty(split[1]);
				String prop = StringUtils.trimToEmpty(split[2]);
				prop = StringUtils.uncapitalize(prop);
				int value = map.get(name); 
				propertiesAll.append("//").append(value).append(LINE);
				if(value == 4) {
					propertiesAll.append("private int ").append(prop).append(";").append(LINE);
					
					methodParseFrom.append("info.set"+ StringUtils.capitalize(prop) + "(body.readInt());").append(LINE);
				}else if(value == 8){
					propertiesAll.append("private double ").append(prop).append(";").append(LINE);
					methodParseFrom.append("info.set"+ StringUtils.capitalize(prop) + "(body.readDouble());").append(LINE);
				}else {
					propertiesAll.append("private String ").append(prop).append(";").append(LINE);
					
					methodParseFrom.append("byte[] "+ prop +" = new byte["+ value +"];").append(LINE);
					methodParseFrom.append("body.readBytes(" + prop + ");").append(LINE);
					methodParseFrom.append("info.set"+ StringUtils.capitalize(prop) +"(StringUtils.trimToEmpty(new String("+ prop +")));").append(LINE);
				}
				methodParseFrom.append(LINE);
				
				propMap.put(prop, value);
			}else {
				System.out.println("error:" + subLine);
			}
			
		}
		
		methodParseFrom.append(LINE).append("return info;").append("}");
		
		classAll.append(propertiesAll.toString()).append(LINE).append(methodParseFrom.toString()).append(LINE).append("}");
		System.out.println(classAll.toString());
		
		
	}
	
	public void testMakeReq() throws Exception {
		InputStream resourceAsStream = DtoMakeTest.class.getClassLoader().getResourceAsStream("ThostFtdcUserApiDataType.h");
		InputStream resourceAsStream2 = DtoMakeTest.class.getClassLoader().getResourceAsStream("dto.req");
		
		List<String> readLines = IOUtils.readLines(resourceAsStream);
		for (String line : readLines) {
			if(StringUtils.isNotBlank(line)) {
				if(line.startsWith("typedef")) {
					String subLine = line.replace(";", "");
					String[] split = subLine.split(" ");
					String type = split[1];
					String name = split[2];
					int value = 0;
					switch(type) {
					case "int":
						value = 4;
						break;
					case "double":
						value = 8;
						break;
					case "char":
						if(name.contains("[")) {
							int first = name.indexOf("[");
							int second = name.indexOf("]");
							value = Integer.parseInt(name.substring(first + 1, second));
							name = name.substring(0, first);
						}else {
							value = 1;
						}
						break;
					default:
						break;
					}
					map.put(name, value);
				}
			}
		}
		
		
		StringBuilder propertiesAll = new StringBuilder();
		StringBuilder methodWrite = new StringBuilder();
		StringBuilder classAll = new StringBuilder();
		StringBuilder methodProp = new StringBuilder();
		classAll.append("public class " + REQCLASSNAME + " {").append(LINE);
		methodWrite.append("public ByteBuf write(ByteBuf buffer) {").append(LINE);
		
		Map<String, Integer> propMap = new HashMap<>();
		List<String> readLines2 = IOUtils.readLines(resourceAsStream2);
		for (String line : readLines2) {
			if(StringUtils.isEmpty(line)) {
				continue;
			}
			if(line.startsWith("struct") || line.startsWith("{") | line.startsWith("}") || line.contains("/")) {
				continue;
			}
			
			String subLine = line.replace(";", "");
			String[] split = subLine.split("\t");
			if(split.length == 3) {
				String name = StringUtils.trimToEmpty(split[1]);
				String prop = StringUtils.trimToEmpty(split[2]);
				prop = StringUtils.uncapitalize(prop);
				int value = map.get(name); 
				if(value == 4) {
					propertiesAll.append("private int " + prop + ";").append(LINE);
					methodWrite.append("buffer.writeInt(" + prop + ");").append(LINE);
					
					methodProp.append("public void set" + StringUtils.capitalize(prop) + "(int " + prop + ") {").append(LINE);
					methodProp.append("this." + prop + " = " + prop + ";").append(LINE);
					methodProp.append("}").append(LINE);
				}else if(value == 8) {
					propertiesAll.append("private double " + prop + ";").append(LINE);
					methodWrite.append("buffer.writeDouble(" + prop + ");").append(LINE);
					
					methodProp.append("public void set" + StringUtils.capitalize(prop) + "(double " + prop + ") {").append(LINE);
					methodProp.append("this." + prop + " = " + prop + ";").append(LINE);
					methodProp.append("}").append(LINE);
				}else {
					propertiesAll.append("private byte[] " + prop + " = new byte[" + value + "];").append(LINE);
					methodWrite.append("buffer.writeBytes(" + prop + ");").append(LINE);
					methodProp.append("public void set" + StringUtils.capitalize(prop) + "(String " + prop + ") {").append(LINE);
					methodProp.append("if(StringUtils.isNotEmpty(" + prop + "))").append(LINE).append("\t");
					methodProp.append("System.arraycopy("+prop+".getBytes(), 0, this."+prop+", 0, "+prop+".getBytes().length);").append(LINE);
					methodProp.append("}").append(LINE);
				}
				
				
				propMap.put(prop, value);
			}else {
				System.out.println("error:" + subLine);
			}
			
		}
		
		methodWrite.append(LINE).append("return buffer;").append("}");
		
		classAll.append(propertiesAll.toString()).append(LINE)
		.append(methodProp.toString()).append(LINE)
		.append(methodWrite.toString()).append(LINE).append("}");
		System.out.println(classAll.toString());
		
	}
}
