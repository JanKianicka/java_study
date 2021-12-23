/*******************************************************************************
 * Copyright 2021, MicroStep-MIS spol. s r.o. (www.microstep-mis.com)
 * All rights reserved.
 * ********************
 * This program/code is the exclusive and proprietary property of MicroStep-MIS.
 * Any unauthorized use of this program/code without the prior written consent of
 * MicroStep-MIS is strictly prohibited.
 * This document is for Internal use.
 ******************************************************************************/
package unidata_netCDF4All;

import ucar.unidata.io.RandomAccessFile;

import java.io.File;
import java.io.IOException;

import com.google.common.collect.ImmutableList;

import ucar.ma2.Array;
import ucar.nc2.NetcdfFile;
import ucar.nc2.Variable;
import ucar.nc2.grib.grib2.Grib2RecordScanner;
import ucar.nc2.grib.grib2.Grib2SectionIdentification;
import ucar.nc2.grib.grib2.Grib2SectionProductDefinition;


public class TestGrib2Reading {


	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("Syntax: java GRIB2FileTest <filename> <structureid (optional)>");
			return;
		}

		// Name of the GRIB2 file
		String filename = args[0];
		System.out.println("Reading file: " + filename);
		File f = new File(filename);
		
		try {
			NetcdfFile of = ucar.nc2.dataset.NetcdfDataset.openFile(filename, null);
			System.out.println(of.getDetailInfo());
			ImmutableList<Variable> vars = of.getVariables();
			for(Variable var: vars) {
				//System.out.println(var.getName());
				if(var.getName().contains("T2")){
					System.out.println("Found T2");
					Array arr = var.read();
					System.out.println("T2 size:" + arr.shapeToString());
					System.out.println("T2 desription:" + var.getDescription());
					System.out.println("T2 shape:" + arr.getDouble(10));
				}
						
				//if(var.getName())
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		try {
//			RandomAccessFile gribFile = new RandomAccessFile(filename, "r");
//			System.out.println(gribFile.getLocation());
//			System.out.println(gribFile.getBufferSize());
//			Grib2RecordScanner reader = new Grib2RecordScanner(gribFile);
//			while (reader.hasNext()) {
//		            ucar.nc2.grib.grib2.Grib2Record gr = reader.next();
//		            System.out.println(gr.toString());
//		            // For our WRF grib2 data nothing is happening here
//			}
//			Grib2SectionIdentification gribSectionIdent = new Grib2SectionIdentification(gribFile);
//			System.out.println(gribSectionIdent.toString());
//			Grib2SectionProductDefinition gribProdDef = new Grib2SectionProductDefinition(gribFile);
//			System.out.println(gribProdDef.getLength());
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}				
	}

}
