package com.nkang.kxmoment.util;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.nkang.kxmoment.baseobject.BillOfSell;
import com.nkang.kxmoment.baseobject.ClientMeta;
import com.nkang.kxmoment.baseobject.PlatforRelated;

public class PoiUtil {
	 public List<BillOfSell> readXls() throws IOException  {
	
		 InputStream is = new FileInputStream("C:/Users/pengcha/Desktop/HP/MDL/AAA.xls");

	        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);

	        BillOfSell xlsDto = null;

	        List<BillOfSell> list = new ArrayList<BillOfSell>();

	        // 循环工作表Sheet

	        for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {

	            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);

	            if (hssfSheet == null) {
	                continue;
	            }
	            // 循环行Row
		            for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
	
		                HSSFRow hssfRow = hssfSheet.getRow(rowNum);
	
		                if (hssfRow == null) {
		                    continue;
		                }
		                xlsDto = new BillOfSell();
	
		                // 循环列Cell
			            for (int cellNum = 2; cellNum <=38; cellNum++) {
			            	
			                HSSFCell businessType = hssfRow.getCell(2);
		
			                xlsDto.setBusinessType(businessType+"");
		
			                HSSFCell sellType = hssfRow.getCell(3);
		
		
			                xlsDto.setSellType(sellType+"");
		
			                HSSFCell orderNumber = hssfRow.getCell(4);
		
			                xlsDto.setOrderNumber(orderNumber+"");
		
			                HSSFCell orderTime = hssfRow.getCell(5);
		
			                xlsDto.setOrderTime(orderTime+"");
		
			                HSSFCell customerName = hssfRow.getCell(6);
		
			                xlsDto.setCustomerName(customerName+"");
			                
			                HSSFCell currency = hssfRow.getCell(7);
			                xlsDto.setCurrency(currency+"");
			                HSSFCell parities = hssfRow.getCell(8);
			                xlsDto.setParities(parities+"");
			                HSSFCell salesDepartments = hssfRow.getCell(9);
			            	
			                xlsDto.setSalesDepartments(salesDepartments+"");
			                HSSFCell salesman = hssfRow.getCell(10);
			            	
			                xlsDto.setSalesman(salesman+"");
			                HSSFCell InventoryCoding = hssfRow.getCell(11);
			            	
			                xlsDto.setInventoryCoding(InventoryCoding+"");
			                HSSFCell inventoryCode = hssfRow.getCell(12);
			                xlsDto.setInventoryCode(inventoryCode+"");
			                HSSFCell inventoryName = hssfRow.getCell(13);
			                xlsDto.setInventoryName(inventoryName+"");
			                
			                HSSFCell specificationsModels = hssfRow.getCell(14);
			               
			                xlsDto.setSpecificationsModels(specificationsModels+"");
			                
			                HSSFCell measurement = hssfRow.getCell(15);
			                xlsDto.setMeasurement(measurement+"");
			                HSSFCell count = hssfRow.getCell(16);
			                xlsDto.setCount(count+"");
			                HSSFCell unitPrice = hssfRow.getCell(17);
			                xlsDto.setUnitPrice(unitPrice+"");
			                HSSFCell priceExcludingTax = hssfRow.getCell(18);
			                xlsDto.setPriceExcludingTax(priceExcludingTax+"");
			                HSSFCell noTaxAmount = hssfRow.getCell(19);
			                xlsDto.setNoTaxAmount(noTaxAmount+"");
			                HSSFCell tax = hssfRow.getCell(20);
			            	
			                xlsDto.setTax(tax+"");
			                HSSFCell totalPriceWithTax = hssfRow.getCell(21);
			            	
			                xlsDto.setTotalPriceWithTax(totalPriceWithTax+"");
			                HSSFCell taxRateString = hssfRow.getCell(22);
			            	
			                xlsDto.setTaxRateString(taxRateString+"");
			                HSSFCell deductible = hssfRow.getCell(23);
			            	
			                xlsDto.setDeductible(deductible+"");
			                
			                HSSFCell deductible2 = hssfRow.getCell(24);
			            	
			                xlsDto.setDeductible2(deductible2+"");
			                
			                HSSFCell advanceShipmentDate = hssfRow.getCell(25);
			            	
			                xlsDto.setAdvanceShipmentDate(advanceShipmentDate+"");
			                
			                HSSFCell ordersForChildTableID = hssfRow.getCell(26);
			                xlsDto.setOrdersForChildTableID(ordersForChildTableID+"");
			                
			                HSSFCell unfilledOrderCount = hssfRow.getCell(27);
			            	
			                xlsDto.setUnfilledOrderCount(unfilledOrderCount+"");
			                
			                HSSFCell noInvoiceCount = hssfRow.getCell(28);
			            	
			                xlsDto.setNoInvoiceCount(noInvoiceCount+"");
			                
			                HSSFCell reservedNum = hssfRow.getCell(29);
			            	
			                xlsDto.setReservedNum(reservedNum+"");
			                
			                HSSFCell notDeliverNum = hssfRow.getCell(30);
			            	
			                xlsDto.setNotDeliverNum(notDeliverNum+"");
			                
			                HSSFCell notDeliverAmount = hssfRow.getCell(31);
			                xlsDto.setNotDeliverAmount(notDeliverAmount+"");
			                HSSFCell noInvoiceCounts = hssfRow.getCell(32);
			            	
			                xlsDto.setNoInvoiceCounts(noInvoiceCounts+"");
			                HSSFCell noInvoiceAmount = hssfRow.getCell(33);
			                xlsDto.setNoInvoiceAmount(noInvoiceAmount+"");
			                HSSFCell amountPurchased = hssfRow.getCell(34);
			                xlsDto.setAmountPurchased(amountPurchased+"");
			                HSSFCell noamountPurchased = hssfRow.getCell(35);
			                xlsDto.setNoamountPurchased(noamountPurchased+"");
			                HSSFCell noProduction = hssfRow.getCell(36);
			                xlsDto.setNoProduction(noProduction+"");
			                HSSFCell noOutsourcing = hssfRow.getCell(37);
			            	
			                xlsDto.setNoOutsourcing(noOutsourcing+"");
			                
			                HSSFCell noImportVolume = hssfRow.getCell(38);
			            	
			                xlsDto.setNoImportVolume(noImportVolume+"");
			                
			            }
		                list.add(xlsDto);
		        }
		 
	        }
			return list;
	 }
	 
	
	 /*	 public void readAGM(InputStream is) throws FileNotFoundException{
		      //  HSSFWorkbook hssfWorkbook;
		        Jeffrey = new ArrayList<String>();
		        Antonio = new ArrayList<String>();
		        Nils = new ArrayList<String>();
		        China = new ArrayList<String>();
		        Other = new ArrayList<String>();
		        
				try {
					hssfWorkbook = new HSSFWorkbook(is);
					// 循环工作表Sheet
					    for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
				            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
				           
					            for (int rowNum = 0; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
					                HSSFRow hssfRow = hssfSheet.getRow(rowNum);
					                
					                if (hssfRow == null) {
					                    continue;
					                }
					                HSSFCell people = hssfRow.getCell(0);
					                if(people==null || "".equals(people+"") ){
					                	continue;
					                }else{
					                	 HSSFCell representative = hssfRow.getCell(1);
							                if(representative!=null && !"".equals(representative+"")){
							                	if("Jeffrey".equals(representative.toString().trim())){
							                		Jeffrey.add(people.toString().trim());
							                		continue;
							                	}
							                	if("Antonio".equals(representative.toString().trim())){
							                		Antonio.add(people.toString().trim());
							                		continue;
							                	}
							                	if("Nils".equals(representative.toString().trim())){
							                		Nils.add(people.toString().trim());
							                		continue;
							                	}
							                	if("China".equals(representative.toString().trim())){
							                		China.add(people.toString().trim());
							                		continue;
							                	}
							                	if("Other".equals(representative.toString().trim())){
							                		Other.add(people.toString().trim());
							                	}
							                }
					                }
					            }
					    }
				}catch (IOException e) {
					e.printStackTrace();
				}finally{
					if(is != null){
						try {
							is.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
	 }
	 */
	 
	 public PlatforRelated platformRelated(InputStream is) throws FileNotFoundException{
		 PlatforRelated platforRelated = new PlatforRelated();
		        HSSFWorkbook hssfWorkbook;
				try {
					hssfWorkbook = new HSSFWorkbook(is);
					// 循环工作表Sheet
					    for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
				            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
				            HSSFRow headRow = hssfSheet.getRow(0);
				            String headName="";
				            String heaName ="";
					            for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
					                HSSFRow hssfRow = hssfSheet.getRow(rowNum);
					                if (hssfRow == null) {
					                    continue;
					                }
					                for (int cellNum = 0; cellNum <headRow.getPhysicalNumberOfCells(); cellNum++) {
					                	headName = headRow.getCell(cellNum).getStringCellValue();
					                	if(headName!=null && "Assigned To".equals(headName.trim())){
					                		HSSFCell assignedTo = hssfRow.getCell(cellNum);
					                		 if(assignedTo==null || "".equals(assignedTo+"") ){
								                	platforRelated.setUnAssinged(platforRelated.getUnAssinged()+1);
								                	platforRelated.setRunMaintainMetricstotal(platforRelated.getRunMaintainMetricstotal()+1);
								                	continue;
								                }else{
								                	platforRelated.setRunMaintainMetricstotal(platforRelated.getRunMaintainMetricstotal()+1);
								                	for(int cellN = 0; cellN <headRow.getPhysicalNumberOfCells(); cellN++){
								                		heaName = headRow.getCell(cellN).getStringCellValue();
									                	if(heaName!=null && "Status".equals(heaName.trim())){
									                		
									                		HSSFCell status = hssfRow.getCell(cellN);
											                if(status!=null){
											                	if("Done".equals(status.toString().trim())){
											                		if(FileOperateUtil.Jeffrey.contains(assignedTo.toString().trim())){
											                			platforRelated.setDone_USA(platforRelated.getDone_USA()+1);
											                			continue;
											                		}else if(FileOperateUtil.Antonio.contains(assignedTo.toString().trim())){
											                			platforRelated.setDone_MEXICO(platforRelated.getDone_MEXICO()+1);
											                			continue;
											                		}else if(FileOperateUtil.Nils.contains(assignedTo.toString().trim())){
											                			platforRelated.setDone_EMEA(platforRelated.getDone_EMEA()+1);
											                			continue;
											                		}else if(FileOperateUtil.China.contains(assignedTo.toString().trim())){
											                			platforRelated.setDone_APJ(platforRelated.getDone_APJ()+1);
																	}else if(FileOperateUtil.Other.contains(assignedTo.toString().trim())){
																		continue;
																	}else{
																		List<String> ls = platforRelated.getOutNames();
																		if(ls==null){
																			ls=new ArrayList<String>();
																		}
																		if(!ls.contains(assignedTo.toString())){
																			ls.add(assignedTo.toString());
																		}
																		platforRelated.setOutNames(ls);
																	}
											                	}else if(!"New".equals(status.toString().trim())&&!"Done".equals(status.toString().trim())){
											                		if(FileOperateUtil.Jeffrey.contains(assignedTo.toString().trim())){
											                			platforRelated.setInProgress_USA(platforRelated.getInProgress_USA()+1);
											                			continue;
											                		}else if(FileOperateUtil.Antonio.contains(assignedTo.toString().trim())){
											                			platforRelated.setInProgress_MEXICO(platforRelated.getInProgress_MEXICO()+1);
											                			continue;
											                		}else if(FileOperateUtil.Nils.contains(assignedTo.toString().trim())){
											                			platforRelated.setInProgress_EMEA(platforRelated.getInProgress_EMEA()+1);
											                			continue;
											                		}else if(FileOperateUtil.China.contains(assignedTo.toString().trim())){
											                			platforRelated.setInProgress_APJ(platforRelated.getInProgress_APJ()+1);
																	}else if(FileOperateUtil.Other.contains(assignedTo.toString().trim())){
																		continue;
																	}else{
																		List<String> ls = platforRelated.getOutNames();
																		if(ls==null){
																			ls=new ArrayList<String>();
																		}
																		if(!ls.contains(assignedTo.toString())){
																			ls.add(assignedTo.toString());
																		}
																		platforRelated.setOutNames(ls);
																	}
											                	}else if("New".equals(status.toString().trim())){
											                		if(FileOperateUtil.Jeffrey.contains(assignedTo.toString().trim())){
											                			platforRelated.setInPlanning_USA(platforRelated.getInPlanning_USA()+1);
											                			continue;
											                		}else if(FileOperateUtil.Antonio.contains(assignedTo.toString().trim())){
											                			platforRelated.setInPlanning_MEXICO(platforRelated.getInPlanning_MEXICO()+1);
											                			continue;
											                		}else if(FileOperateUtil.Nils.contains(assignedTo.toString().trim())){
											                			platforRelated.setInPlanning_EMEA(platforRelated.getInPlanning_EMEA()+1);
											                			continue;
											                		}else if(FileOperateUtil.China.contains(assignedTo.toString().trim())){
											                			platforRelated.setInPlanning_APJ(platforRelated.getInPlanning_APJ()+1);
																	}else if(FileOperateUtil.Other.contains(assignedTo.toString().trim())){
																		continue;
																	}else{
																		List<String> ls = platforRelated.getOutNames();
																		if(ls==null){
																			ls=new ArrayList<String>();
																		}
																		if(!ls.contains(assignedTo.toString())){
																			ls.add(assignedTo.toString());
																		}
																		platforRelated.setOutNames(ls);
																	}
									                	}
								                	}
								                	 
															}
										                }
								                }
					                	}
					                }
					                
					    /*            HSSFCell assignedTo = hssfRow.getCell(0);
					                if(assignedTo==null || "".equals(assignedTo+"") ){
					                	platforRelated.setUnAssinged(platforRelated.getUnAssinged()+1);
					                	platforRelated.setRunMaintainMetricstotal(platforRelated.getRunMaintainMetricstotal()+1);
					                	continue;
					                }else{
					                	platforRelated.setRunMaintainMetricstotal(platforRelated.getRunMaintainMetricstotal()+1);
					                	 HSSFCell status = hssfRow.getCell(1);
							                if(status!=null){
							                	if("Done".equals(status.toString().trim())){
							                		if(FileOperateUtil.Jeffrey.contains(assignedTo.toString().trim())){
							                			platforRelated.setDone_USA(platforRelated.getDone_USA()+1);
							                			continue;
							                		}else if(FileOperateUtil.Antonio.contains(assignedTo.toString().trim())){
							                			platforRelated.setDone_MEXICO(platforRelated.getDone_MEXICO()+1);
							                			continue;
							                		}else if(FileOperateUtil.Nils.contains(assignedTo.toString().trim())){
							                			platforRelated.setDone_EMEA(platforRelated.getDone_EMEA()+1);
							                			continue;
							                		}else if(FileOperateUtil.China.contains(assignedTo.toString().trim())){
							                			platforRelated.setDone_APJ(platforRelated.getDone_APJ()+1);
													}else if(FileOperateUtil.Other.contains(assignedTo.toString().trim())){
														continue;
													}else{
														List<String> ls = platforRelated.getOutNames();
														if(ls==null){
															ls=new ArrayList<String>();
														}
														if(!ls.contains(assignedTo.toString())){
															ls.add(assignedTo.toString());
														}
														platforRelated.setOutNames(ls);
													}
							                		if("Jeremy Clark".equals(assignedTo.toString())||"Samson Jayaraj".equals(assignedTo.toString())||"Andrew Lewis".equals(assignedTo.toString())||"Tommy Lucas".equals(assignedTo.toString())||"Bhavesh Patel".equals(assignedTo.toString())){
							                			platforRelated.setDone_USA(platforRelated.getDone_USA()+1);
							                			continue;
							                		}else if("Leonardo Vallin Langarica".equals(assignedTo.toString())||"Luis Vasquez-Rodriguez".equals(assignedTo.toString())||"Azucena Rivera".equals(assignedTo.toString())||"Victor Carrillo".equals(assignedTo.toString())){
							                			platforRelated.setDone_MEXICO(platforRelated.getDone_MEXICO()+1);
							                			continue;
							                		}else if("slawomir zagrodny".equals(assignedTo.toString())||"Wojciech Grusznis".equals(assignedTo.toString())){
							                			platforRelated.setDone_EMEA(platforRelated.getDone_EMEA()+1);
							                			continue;
							                		}else {
							                			platforRelated.setDone_APJ(platforRelated.getDone_APJ()+1);
													}
							                		
							                	}else if(!"New".equals(status.toString().trim())&&!"Done".equals(status.toString().trim())){
							                		if(FileOperateUtil.Jeffrey.contains(assignedTo.toString().trim())){
							                			platforRelated.setInProgress_USA(platforRelated.getInProgress_USA()+1);
							                			continue;
							                		}else if(FileOperateUtil.Antonio.contains(assignedTo.toString().trim())){
							                			platforRelated.setInProgress_MEXICO(platforRelated.getInProgress_MEXICO()+1);
							                			continue;
							                		}else if(FileOperateUtil.Nils.contains(assignedTo.toString().trim())){
							                			platforRelated.setInProgress_EMEA(platforRelated.getInProgress_EMEA()+1);
							                			continue;
							                		}else if(FileOperateUtil.China.contains(assignedTo.toString().trim())){
							                			platforRelated.setInProgress_APJ(platforRelated.getInProgress_APJ()+1);
													}else if(FileOperateUtil.Other.contains(assignedTo.toString().trim())){
														continue;
													}else{
														List<String> ls = platforRelated.getOutNames();
														if(ls==null){
															ls=new ArrayList<String>();
														}
														if(!ls.contains(assignedTo.toString())){
															ls.add(assignedTo.toString());
														}
														platforRelated.setOutNames(ls);
													}
							                		if("Jeremy Clark".equals(assignedTo.toString())||"Samson Jayaraj".equals(assignedTo.toString())||"Andrew Lewis".equals(assignedTo.toString())||"Tommy Lucas".equals(assignedTo.toString())||"Bhavesh Patel".equals(assignedTo.toString())){
							                			platforRelated.setInProgress_USA(platforRelated.getInProgress_USA()+1);
							                			continue;
							                		}else if("Leonardo Vallin Langarica".equals(assignedTo.toString())||"Luis Vasquez-Rodriguez".equals(assignedTo.toString())||"Azucena Rivera".equals(assignedTo.toString())||"Victor Carrillo".equals(assignedTo.toString())){
							                			platforRelated.setInProgress_MEXICO(platforRelated.getInProgress_MEXICO()+1);
							                			continue;
							                		}else if("slawomir zagrodny".equals(assignedTo.toString())||"Wojciech Grusznis".equals(assignedTo.toString())){
							                			platforRelated.setInProgress_EMEA(platforRelated.getInProgress_EMEA()+1);
							                			continue;
							                		}else {
							                			platforRelated.setInProgress_APJ(platforRelated.getInProgress_APJ()+1);
													}
							                	}else if("New".equals(status.toString().trim())){
							                		if(FileOperateUtil.Jeffrey.contains(assignedTo.toString().trim())){
							                			platforRelated.setInPlanning_USA(platforRelated.getInPlanning_USA()+1);
							                			continue;
							                		}else if(FileOperateUtil.Antonio.contains(assignedTo.toString().trim())){
							                			platforRelated.setInPlanning_MEXICO(platforRelated.getInPlanning_MEXICO()+1);
							                			continue;
							                		}else if(FileOperateUtil.Nils.contains(assignedTo.toString().trim())){
							                			platforRelated.setInPlanning_EMEA(platforRelated.getInPlanning_EMEA()+1);
							                			continue;
							                		}else if(FileOperateUtil.China.contains(assignedTo.toString().trim())){
							                			platforRelated.setInPlanning_APJ(platforRelated.getInPlanning_APJ()+1);
													}else if(FileOperateUtil.Other.contains(assignedTo.toString().trim())){
														continue;
													}else{
														List<String> ls = platforRelated.getOutNames();
														if(ls==null){
															ls=new ArrayList<String>();
														}
														if(!ls.contains(assignedTo.toString())){
															ls.add(assignedTo.toString());
														}
														platforRelated.setOutNames(ls);
													}
							                		if("Jeremy Clark".equals(assignedTo.toString())||"Samson Jayaraj".equals(assignedTo.toString())||"Andrew Lewis".equals(assignedTo.toString())||"Tommy Lucas".equals(assignedTo.toString())||"Bhavesh Patel".equals(assignedTo.toString())){
							                			platforRelated.setInPlanning_USA(platforRelated.getInPlanning_USA()+1);
							                			continue;
							                		}else if("Leonardo Vallin Langarica".equals(assignedTo.toString())||"Luis Vasquez-Rodriguez".equals(assignedTo.toString())||"Azucena Rivera".equals(assignedTo.toString())||"Victor Carrillo".equals(assignedTo.toString())){
							                			platforRelated.setInPlanning_MEXICO(platforRelated.getInPlanning_MEXICO()+1);
							                			continue;
							                		}else if("slawomir zagrodny".equals(assignedTo.toString())||"Wojciech Grusznis".equals(assignedTo.toString())){
							                			platforRelated.setInPlanning_EMEA(platforRelated.getInPlanning_EMEA()+1);
							                			continue;
							                		}else {
							                			platforRelated.setInPlanning_APJ(platforRelated.getInPlanning_APJ()+1);
													}
												}
							                }
					                }*/
					               
					            }
					            
					    }
				} catch (IOException e) {
					e.printStackTrace();
				}finally{
					FileOperateUtil.Jeffrey.clear();
					FileOperateUtil.Antonio.clear();
					FileOperateUtil.Nils.clear();
					FileOperateUtil.China.clear();
					FileOperateUtil.Other.clear();
					if(is != null){
						try {
							is.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
				//System.out.println(platforRelated.getUnAssinged());
				return platforRelated;
	 } 
	 
	 

	 public PlatforRelated uploadReport(InputStream is) throws FileNotFoundException{
		 PlatforRelated platforRelated = new PlatforRelated();
		        HSSFWorkbook hssfWorkbook;
				try {
					hssfWorkbook = new HSSFWorkbook(is);
					    for (int numSheet = 0; numSheet < 1; numSheet++) {
				            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
				            HSSFRow headRow = hssfSheet.getRow(0);
				            String headName="";
				            String heaName ="";
					            for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
					                HSSFRow hssfRow = hssfSheet.getRow(rowNum);
					                if (hssfRow == null) {
					                    continue;
					                }
					                for (int cellNum = 0; cellNum <headRow.getPhysicalNumberOfCells(); cellNum++) {
					                	headName = headRow.getCell(cellNum).getStringCellValue();
					                	if(headName!=null && "Incident Assignee Email Name (RESTRICTED)".equals(headName.trim())){
					                		HSSFCell assignedTo = hssfRow.getCell(cellNum);
					                		 if(assignedTo==null || "".equals(assignedTo+"") ){
					                			 platforRelated.setUnAssinged(platforRelated.getUnAssinged()+1);
								                	platforRelated.setIMMetricstotal(platforRelated.getIMMetricstotal()+1);
								                	continue;
								                }else{
								                	for(int cellN = 0; cellN <headRow.getPhysicalNumberOfCells(); cellN++){
								                		heaName = headRow.getCell(cellN).getStringCellValue();
									                	if(heaName!=null && "Incident Current Status Description".equals(heaName.trim())){
									                		
									                		HSSFCell status = hssfRow.getCell(cellN);
											                if(status!=null){
											                	if("Closed".equals(status.toString().trim())){
											                		platforRelated.setIMMetricstotal(platforRelated.getIMMetricstotal()+1);
											                		
											                		if(FileOperateUtil.Jeffrey.contains(assignedTo.toString().trim())){
											                			platforRelated.setClosed_USA(platforRelated.getClosed_USA()+1);
											                			continue;
											                		}else if(FileOperateUtil.Antonio.contains(assignedTo.toString().trim())){
											                			platforRelated.setClosed_MEXICO(platforRelated.getClosed_MEXICO()+1);
											                			continue;
											                		}else if(FileOperateUtil.Nils.contains(assignedTo.toString().trim())){
											                			platforRelated.setClosed_EMEA(platforRelated.getClosed_EMEA()+1);
											                			continue;
											                		}else if(FileOperateUtil.China.contains(assignedTo.toString().trim())){
											                			platforRelated.setClosed_APJ(platforRelated.getClosed_APJ()+1);
																	}else if(FileOperateUtil.Other.contains(assignedTo.toString().trim())){
											                			platforRelated.setClosed_OTHER(platforRelated.getClosed_OTHER()+1);
											                			continue;
																	}else {
																		List<String> ls = platforRelated.getOutNames();
																		if(ls==null){
																			ls=new ArrayList<String>();
																		}
																		if(!ls.contains(assignedTo.toString())){
																			ls.add(assignedTo.toString());
																		}
																		platforRelated.setOutNames(ls);
																	}
											                		
																}
											                }
									                	}
											                
								                	}
					                		 
					          /*      		 
					                HSSFCell assignedTo = hssfRow.getCell(7);
					                if(assignedTo==null || "".equals(assignedTo+"") ){
					                	platforRelated.setUnAssinged(platforRelated.getUnAssinged()+1);
					                	platforRelated.setIMMetricstotal(platforRelated.getIMMetricstotal()+1);
					                	continue;
					                }else{
					                	 HSSFCell status = hssfRow.getCell(5);
							                if(status!=null){
							                	if("Closed".equals(status.toString().trim())){
							                		platforRelated.setIMMetricstotal(platforRelated.getIMMetricstotal()+1);
							                		
							                		if(FileOperateUtil.Jeffrey.contains(assignedTo.toString().trim())){
							                			platforRelated.setClosed_USA(platforRelated.getClosed_USA()+1);
							                			continue;
							                		}else if(FileOperateUtil.Antonio.contains(assignedTo.toString().trim())){
							                			platforRelated.setClosed_MEXICO(platforRelated.getClosed_MEXICO()+1);
							                			continue;
							                		}else if(FileOperateUtil.Nils.contains(assignedTo.toString().trim())){
							                			platforRelated.setClosed_EMEA(platforRelated.getClosed_EMEA()+1);
							                			continue;
							                		}else if(FileOperateUtil.China.contains(assignedTo.toString().trim())){
							                			platforRelated.setClosed_APJ(platforRelated.getClosed_APJ()+1);
													}else if(FileOperateUtil.Other.contains(assignedTo.toString().trim())){
							                			platforRelated.setClosed_OTHER(platforRelated.getClosed_OTHER()+1);
							                			continue;
													}else {
														List<String> ls = platforRelated.getOutNames();
														if(ls==null){
															ls=new ArrayList<String>();
														}
														if(!ls.contains(assignedTo.toString())){
															ls.add(assignedTo.toString());
														}
														platforRelated.setOutNames(ls);
													}
							                		
												}
							                }*/
								                }
					                	}
					                }
					            }
					    }
				} catch (IOException e) {
					e.printStackTrace();
				}finally{
					FileOperateUtil.Jeffrey.clear();
					FileOperateUtil.Antonio.clear();
					FileOperateUtil.Nils.clear();
					FileOperateUtil.China.clear();
					FileOperateUtil.Other.clear();
					if(is != null){
						try {
							is.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
				return platforRelated;
	 } 
	 
							                		
}
