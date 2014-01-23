package com.tdil.lojack.vlu;

import java.io.FileReader;
import java.io.IOException;

import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.prefs.CsvPreference;

public class TestCSV {

	private static CellProcessor[] getProcessors() {

		final CellProcessor[] processors = new CellProcessor[] { new NotNull(), // dni
				new NotNull(), // dominio
				new NotNull() // estado
		};

		return processors;
	}

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		String fileName = "/home/mgodoy/icarus/workspace/thalamus/LoJackWeb/JavaSource/com/tdil/lojack/vlu/vlu.csv";
		CsvBeanReader beanReader = null;
		try {
			beanReader = new CsvBeanReader(new FileReader(fileName),CsvPreference.STANDARD_PREFERENCE);

			// the header elements are used to map the values to the bean (names
			// must match)
			final String[] header = beanReader.getHeader(true);
			final CellProcessor[] processors = getProcessors();

			VLUImportRecord customer;
			while ((customer = beanReader.read(VLUImportRecord.class, header, processors)) != null) {
				System.out.println(String.format("lineNo=%s, rowNo=%s, customer=%s", beanReader.getLineNumber(), beanReader.getRowNumber(),
						customer));
			}

		} finally {
			if (beanReader != null) {
				beanReader.close();
			}
		}
	}

}
