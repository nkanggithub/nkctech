package com.nkang.kxmoment.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import com.nkang.kxmoment.baseobject.OrgOtherPartySiteInstance;
import com.nkang.kxmoment.service.ProcurementOrgSite;

/**
 * @author ashraf
 * 
 */
public class CsvFileWriter {
	
	//Delimiter used in CSV file
	private static final String COMMA_DELIMITER = "|";
	private static final String EQU_DELIMITER = "=";
	private static final String NEW_LINE_SEPARATOR = "\n";
	
	//CSV file header
	private static final String FILE_HEADER = "SN, OPSIID,OPPTID,OPPTNAME,OPPTADDRESS,OPPTLAT, OPPTLNG,OPPTLATINNAME,OPPTCTRYCD,OPPTESTDYR,OPPTGBLHC, StateProvince, BRID,CityArea, OpptYrRev,BRIID,SegmentArea, IsCompetitor, IsCustomer, IsPartner, QualityGrade";

	public static void writeCsvFile(String fileName, List <OrgOtherPartySiteInstance> orgsites) throws IOException, FileNotFoundException {
		//FileWriter fileWriter = null;	
		OutputStreamWriter fileWriter = new OutputStreamWriter(new FileOutputStream(fileName),"UTF-8");
		try {
			//fileWriter = new FileWriter(fileName);

			int count = 1;
			for (OrgOtherPartySiteInstance os : orgsites) {
				//SN
				fileWriter.append(String.valueOf(count));
				fileWriter.append(EQU_DELIMITER);
				
				// get OPSI ID
				fileWriter.append(String.valueOf(os.getSiteInstanceId()));
				fileWriter.append(COMMA_DELIMITER);
				// get Org ID
				fileWriter.append(String.valueOf(os.getOrganizationId()));
				fileWriter.append(COMMA_DELIMITER);
				// get OPSI Name
				fileWriter.append(String.valueOf(os.getOrganizationNonLatinExtendedName()));
				fileWriter.append(COMMA_DELIMITER);
				// get OPSI Name
				fileWriter.append(String.valueOf(os.getNonlatinStreet1LongName()));
				fileWriter.append(COMMA_DELIMITER);

				fileWriter.append(String.valueOf("LAT"));
				fileWriter.append(COMMA_DELIMITER);
				
				fileWriter.append(String.valueOf("LNG"));
				fileWriter.append(COMMA_DELIMITER);
				
				//OrgExtName
				fileWriter.append(String.valueOf(os.getOrganizationExtendedName()));
				fileWriter.append(COMMA_DELIMITER);
				
				//OPPTCTRYCD
				fileWriter.append(String.valueOf(os.getNonlatinCity()));
				fileWriter.append(COMMA_DELIMITER);
				
				//OPPTESTDYR
				fileWriter.append("NA");
				fileWriter.append(COMMA_DELIMITER);

				//OPPTGBLHC
				fileWriter.append(String.valueOf(os.getCountOfEmployee()));
				fileWriter.append(COMMA_DELIMITER);
				
				//StateProvince
				fileWriter.append(String.valueOf(os.getState()));
				fileWriter.append(COMMA_DELIMITER);
				
				//BRID
				fileWriter.append("NA");
				fileWriter.append(COMMA_DELIMITER);
				
				//CITYAREA
				fileWriter.append(String.valueOf(os.getCityRegion()));
				fileWriter.append(COMMA_DELIMITER);
				
				//OpptYrRev
				fileWriter.append(String.valueOf("NA"));
				fileWriter.append(COMMA_DELIMITER);
				
				//BRIID
				fileWriter.append("NA");
				fileWriter.append(COMMA_DELIMITER);
				
				//SegmentArea
				fileWriter.append(String.valueOf(os.getSalesCoverageSegments()));
				fileWriter.append(COMMA_DELIMITER);
				
				//Competitor
				fileWriter.append(String.valueOf(os.getIsCompetitor()));
				fileWriter.append(COMMA_DELIMITER);
				
				//Customer
				fileWriter.append(String.valueOf(os.getOnlyPresaleCustomer()));
				fileWriter.append(COMMA_DELIMITER);
				
				//Partner
				fileWriter.append(String.valueOf(os.isReturnPartnerFlag()));
				fileWriter.append(COMMA_DELIMITER);
				
				//QualityGrade
				fileWriter.append(String.valueOf(""));
/*				fileWriter.append(COMMA_DELIMITER);
				
				fileWriter.append(String.valueOf(os.getStateProvinceName()));*/   
				fileWriter.append(NEW_LINE_SEPARATOR);
				
				count = count + 1;
			}
			System.out.println("CSV file was created successfully !!!");
			
		} catch (Exception e) {
			System.out.println("Error in CsvFileWriter !!!");
			e.printStackTrace();
		} finally {
			
			try {
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				System.out.println("Error while flushing/closing fileWriter !!!");
                e.printStackTrace();
			}
			
		}
	}

}