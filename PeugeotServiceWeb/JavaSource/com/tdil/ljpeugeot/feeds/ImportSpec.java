package com.tdil.ljpeugeot.feeds;

import org.supercsv.cellprocessor.ift.CellProcessor;

import com.tdil.ljpeugeot.model.DataImport;

public interface ImportSpec {

	public Class getRecordClass();
	
	public String getType();
	
	public CellProcessor[] getCellProcessor();

	public void processRow(Object importRecord, DataImport dataImport) throws Exception;

	public void importFinished(DataImport dataImport) throws Exception;
}
