/*******************************************************************************
 * Copyright 2021, MicroStep-MIS spol. s r.o. (www.microstep-mis.com)
 * All rights reserved.
 * ********************
 * This program/code is the exclusive and proprietary property of MicroStep-MIS.
 * Any unauthorized use of this program/code without the prior written consent of
 * MicroStep-MIS is strictly prohibited.
 * This document is for Internal use.
 ******************************************************************************/
package unidata_grib2;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import ucar.unidata.io.RandomAccessFile;
import ucar.grib.grib2.Grib2IdentificationSection;
import ucar.grib.grib2.Grib2Input;
import ucar.grib.grib2.Grib2Record;
import ucar.grib.grib2.Grib2GridDefinitionSection;
/**
 * @author janki
 *
 */
public class TestGrib2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("Syntax: java GRIB2FileTest <filename> <structureid (optional)>");
			return;
		}

		// Name of the GRIB2 file
		String filename = args[0];
		
		
		System.out.println("Reading file: " + filename);
		try {

			// Open GRIB2 file and read it
			InputStream inputstream;
			inputstream = Files.newInputStream(Paths.get(filename));
			RandomAccessFile gribFile = new RandomAccessFile(filename, "r");
			//gribFile.order(RandomAccessFile.LITTLE_ENDIAN);
			
			System.out.println(gribFile.length());
			Grib2IdentificationSection gribIdSecitonObj = new Grib2IdentificationSection(gribFile);
			System.out.println(gribIdSecitonObj.getProductTypeName());
			System.out.println(gribIdSecitonObj.getRefTime());
			System.out.println(gribIdSecitonObj.getBaseTime());
			
			Grib2Input g2i = new Grib2Input(gribFile);
			g2i.scan(false, false);
			
			List<Grib2Record> records = g2i.getRecords();
			System.out.println(records.size());
			
			Grib2GridDefinitionSection gribDefSection = new Grib2GridDefinitionSection(gribFile, true);
			System.out.println(gribDefSection.getLength());
		} catch (Exception e) {
			e.printStackTrace();
		}			

	}

}
