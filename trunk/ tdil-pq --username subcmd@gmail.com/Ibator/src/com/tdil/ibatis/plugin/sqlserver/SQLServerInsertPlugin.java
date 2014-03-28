package com.tdil.ibatis.plugin.sqlserver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import org.mybatis.generator.api.FullyQualifiedTable;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.xml.Element;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;

public class SQLServerInsertPlugin extends PluginAdapter {

	public SQLServerInsertPlugin() {
		// TODO Auto-generated constructor stub
	}

	public boolean validate(List<String> warnings) {
		return true;
	}
	
	@Override
	public boolean sqlMapUpdateByExampleSelectiveElementGenerated(XmlElement element, IntrospectedTable arg1) {
		FullyQualifiedTable table = arg1.getFullyQualifiedTable();
		for (int i = 0; i < element.getElements().size(); i++) {
			Element elem = element.getElements().get(i);
			if (elem instanceof TextElement && elem.getFormattedContent(0).contains("update dbo.")) {
				element.getElements().set(i, new TextElement("update " + table.getAlias()));
				element.getElements().add(i + 2, new TextElement("from " + arg1.getFullyQualifiedTableNameAtRuntime() + " " + table.getAlias()));
			}
		}
		return true;
	}
	
	@Override
	public boolean sqlMapUpdateByExampleWithBLOBsElementGenerated(XmlElement element, IntrospectedTable arg1) {
		FullyQualifiedTable table = arg1.getFullyQualifiedTable();
		for (int i = 0; i < element.getElements().size(); i++) {
			Element elem = element.getElements().get(i);
			if (elem instanceof TextElement && elem.getFormattedContent(0).contains("update dbo.")) {
				element.getElements().set(i, new TextElement("update " + table.getAlias()));
				for (int j = i + 1; j < element.getElements().size(); j++) {
					Element elem1 = element.getElements().get(j);
					if (elem1 instanceof TextElement && !elem1.getFormattedContent(0).contains(",")) {
						element.getElements().add(j + 1, new TextElement("from " + arg1.getFullyQualifiedTableNameAtRuntime() + " " + table.getAlias()));
						return true;
					}
				}
			}
		}
		return true;
	}
	
	@Override
	public boolean sqlMapUpdateByExampleWithoutBLOBsElementGenerated(XmlElement element, IntrospectedTable arg1) {
		FullyQualifiedTable table = arg1.getFullyQualifiedTable();
		for (int i = 0; i < element.getElements().size(); i++) {
			Element elem = element.getElements().get(i);
			if (elem instanceof TextElement && elem.getFormattedContent(0).contains("update dbo.")) {
				element.getElements().set(i, new TextElement("update " + table.getAlias()));
				for (int j = i + 1; j < element.getElements().size(); j++) {
					Element elem1 = element.getElements().get(j);
					if (elem1 instanceof TextElement && !elem1.getFormattedContent(0).contains(",")) {
						element.getElements().add(j + 1, new TextElement("from " + arg1.getFullyQualifiedTableNameAtRuntime() + " " + table.getAlias()));
						return true;
					}
				}
			}
		}
		return true;
	}
	
	
	@Override
	public boolean sqlMapDeleteByExampleElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
		FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();
		for (int i = 0; i < element.getElements().size(); i++) {
			Element elem = element.getElements().get(i);
			if (elem instanceof TextElement && elem.getFormattedContent(0).contains("delete from "))
				replaceDeleteStatement(element, table, i);
		}
		return true;
	}

	private void replaceDeleteStatement(XmlElement element, FullyQualifiedTable table, int index) {
		StringBuilder sb = new StringBuilder();
		String alias = table.getAlias();
		if (alias != null && alias.length() > 0) {
			sb.append("delete " + alias + " from ");
			sb.append(table.getFullyQualifiedTableNameAtRuntime());
			sb.append(" AS ");
			sb.append(alias);
			element.getElements().set(index, new TextElement(sb.toString()));
		} 
	}
	
}
