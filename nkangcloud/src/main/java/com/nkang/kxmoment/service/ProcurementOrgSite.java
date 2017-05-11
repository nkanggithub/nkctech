package com.nkang.kxmoment.service;

import java.util.ArrayList;
import java.util.List;
public class ProcurementOrgSite {

	private static final long serialVersionUID = -3163688545018414887L;
	private String orgId;
	private String dunsID;
	private String orgExtName;
	private String brTypeCode;
	private String brTypeName;
	private String baGroupId;
	private String baGroupName;
	private String addressL1;
	private String brUnitName;
	private String addressL2;
	private String addressL3;
	private String prmID;
	private String countryCode;
	private String countryName;
	private String pLocator;
	private String stateProvinceCode;
	private String stateProvinceName;
	private String cityId;
	private String cityName;
	private String phoneNumber;
	private String postalCode;
	private String faxNumber;
/*	//Tax Ids
	private List<Tax> taxIds;
	//Business relationship assignments
	private List<BizRelShipAssignment> brAssignments;
	//Business relationship item assignments
	private List<ExtendedProfileItemAssignment> extendedProfileItemAssignments;
	*/
	//Tax ID
	private String taxID;
	private String bpUsageName;
	private String partnerIndicator;
	private String customerIndicator;
	private String recordStatus;
	private String briRecordStatus;
	private String brRecordStatus;
	private String indSegCd;
	//CTY_DSTC_LG_NM
	private String district;
	private String searchLevel;
	private String orgReportingName;
	private String orgLegalName;
	private String sellIntoMarketCode;
	private String sellIntoMarketName;
	private String lglSellIntoMarketCode;
    private String lglSellIntoMarketName;
	private String criteriaType;
	private String briRowId;
	private String matchName;
	private String cScriptCode;
	private String brRowId;
	private String pbRelationshipIndicator;
	private String prmyOpsInsnRowId;
	private String otherPartySiteInstanceRowId;
	private String otrPrtySiteFaxTelNr;
	private String externalProfileLocatorId;
	private String pPreName;
	private String primaryBusinessInstanceIndicator;
	private String actionCode;
	private String bsnRshpPtnrPHPInd;
	private String perferredName;
	private String perferredNameForAccount;
	private String cScriptCodeForAccount;
	private String organizationNonLatinExtendedName;
	private String numberOfEmployee;
	private String nonlatinCity;
	private List<String> industrySegmentNames =  new ArrayList();
	private String industrySegmentName;
	private String isCompetitor;
	
	public ProcurementOrgSite(){}

	public ProcurementOrgSite(String orgId, String dunsID, String orgExtName,
			String brTypeCode, String brTypeName, String baGroupId,
			String baGroupName, String addressL1, String brUnitName,
			String addressL2, String addressL3, String prmID,
			String countryCode, String countryName, String pLocator,
			String stateProvinceCode, String stateProvinceName,
			String cityId, String cityName,
			String phoneNumber, String postalCode, String faxNumber,
			List<String> industrySegmentNames,
			//List<Tax> taxIds, 
			//List<BizRelShipAssignment> brAssignments,
			//List<ExtendedProfileItemAssignment> extendedProfileItemAssignments, String taxID,
			String bpUsageName, String partnerIndicator,
			String customerIndicator, String recordStatus,
			String briRecordStatus, String indSegCd, String district,
			String searchLevel, String orgReportingName, String orgLegalName,
			String sellIntoMarketCode, String sellIntoMarketName,
			String lglSellIntoMarketCode, String lglSellIntoMarketName,
			String criteriaType, String briRowId, String matchName,
			String cScriptCode, String brRowId, String pbRelationshipIndicator,
			String prmyOpsInsnRowId, String otherPartySiteInstanceRowId,
			String otrPrtySiteFaxTelNr, String externalProfileLocatorId,
			String pPreName, String primaryBusinessInstanceIndicator,
			String perferredName,String perferredNameForAccount,
			String cScriptCodeForAccount, String organizationNonLatinExtendedName,
			String numberOfEmployee, String nonlatinCity, String industrySegmentName,
			String isCompetitor) {
		super();
		this.orgId = orgId;
		this.dunsID = dunsID;
		this.orgExtName = orgExtName;
		this.brTypeCode = brTypeCode;
		this.brTypeName = brTypeName;
		this.baGroupId = baGroupId;
		this.baGroupName = baGroupName;
		this.addressL1 = addressL1;
		this.brUnitName = brUnitName;
		this.addressL2 = addressL2;
		this.addressL3 = addressL3;
		this.prmID = prmID;
		this.countryCode = countryCode;
		this.countryName = countryName;
		this.pLocator = pLocator;
		this.stateProvinceCode = stateProvinceCode;
		this.stateProvinceName = stateProvinceName;
		this.cityId = cityId;
		this.cityName = cityName;
		this.phoneNumber = phoneNumber;
		this.postalCode = postalCode;
		this.faxNumber = faxNumber;
		this.industrySegmentNames = industrySegmentNames;
		//this.taxIds = taxIds;
		//this.brAssignments = brAssignments;
		//this.extendedProfileItemAssignments = extendedProfileItemAssignments;
		this.taxID = taxID;
		this.bpUsageName = bpUsageName;
		this.partnerIndicator = partnerIndicator;
		this.customerIndicator = customerIndicator;
		this.recordStatus = recordStatus;
		this.briRecordStatus = briRecordStatus;
		this.indSegCd = indSegCd;
		this.district = district;
		this.searchLevel = searchLevel;
		this.orgReportingName = orgReportingName;
		this.orgLegalName = orgLegalName;
		this.sellIntoMarketCode = sellIntoMarketCode;
		this.sellIntoMarketName = sellIntoMarketName;
		this.lglSellIntoMarketCode = lglSellIntoMarketCode;
        this.lglSellIntoMarketName = lglSellIntoMarketName;
		this.criteriaType = criteriaType;
		this.briRowId = briRowId;
		this.matchName = matchName;
		this.cScriptCode = cScriptCode;
		this.brRowId = brRowId;
		this.pbRelationshipIndicator = pbRelationshipIndicator;
		this.prmyOpsInsnRowId = prmyOpsInsnRowId;
		this.otherPartySiteInstanceRowId = otherPartySiteInstanceRowId;
		this.otrPrtySiteFaxTelNr = otrPrtySiteFaxTelNr;
		this.externalProfileLocatorId = externalProfileLocatorId;
		this.pPreName = pPreName;
		this.primaryBusinessInstanceIndicator = primaryBusinessInstanceIndicator;
		this.perferredName = perferredName;
		this.perferredNameForAccount = perferredNameForAccount;
		this.cScriptCodeForAccount = cScriptCodeForAccount;
		this.organizationNonLatinExtendedName = organizationNonLatinExtendedName;
		this.numberOfEmployee = numberOfEmployee;
		this.nonlatinCity = nonlatinCity;
		this.industrySegmentName = industrySegmentName;
		this.isCompetitor = isCompetitor;
	}
	
	public String getIsCompetitor() {
		return isCompetitor;
	}

	public void setIsCompetitor(String isCompetitor) {
		this.isCompetitor = isCompetitor;
	}

	public String getIndustrySegmentName() {
		return industrySegmentName;
	}

	public void setIndustrySegmentName(String industrySegmentName) {
		this.industrySegmentName = industrySegmentName;
	}

	/**
	 * @return the stateProvinceCode
	 */
	public String getStateProvinceCode() {
		return stateProvinceCode;
	}


	/**
	 * @param stateProvinceCode the stateProvinceCode to set
	 */
	public void setStateProvinceCode(String stateProvinceCode) {
		this.stateProvinceCode = stateProvinceCode;
	}

	/**
	 * @return the cityId
	 */
	public String getCityId() {
		return cityId;
	}

	/**
	 * @param cityId the cityId to set
	 */
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	/**
	 * @return the orgExtName
	 */
	public String getOrgExtName() {
		return orgExtName;
	}

	/**
	 * @param orgExtName the orgExtName to set
	 */
	public void setOrgExtName(String orgExtName) {
		this.orgExtName = orgExtName;
	}

	/**
	 * @return the pPreName
	 */
	public String getpPreName() {
		return pPreName;
	}

	/**
	 * @param pPreName the pPreName to set
	 */
	public void setpPreName(String pPreName) {
		this.pPreName = pPreName;
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the postalCode
	 */
	public String getPostalCode() {
		return postalCode;
	}

	/**
	 * @param postalCode the postalCode to set
	 */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	/**
	 * @return the faxNumber
	 */
	public String getFaxNumber() {
		return faxNumber;
	}

	/**
	 * @param faxNumber the faxNumber to set
	 */
	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}

	/**
	 * @return the taxIds
	 */
	/*public List<Tax> getTaxIds() {
		return taxIds;
	}

	*//**
	 * @param taxIds the taxIds to set
	 *//*
	public void setTaxIds(List<Tax> taxIds) {
		this.taxIds = taxIds;
	}

	*//**
	 * @return the brAssignments
	 *//*
	public List<BizRelShipAssignment> getBrAssignments() {
		return brAssignments;
	}

	*//**
	 * @param brAssignments the brAssignments to set
	 *//*
	public void setBrAssignments(List<BizRelShipAssignment> brAssignments) {
		this.brAssignments = brAssignments;
	}

	*//**
	 * @return the briAssignments
	 *//*
	public List<ExtendedProfileItemAssignment> getExtendedProfileItemAssignments() {
		return extendedProfileItemAssignments;
	}

	*//**
	 * @param briAssignments the briAssignments to set
	 *//*
	public void setExtendedProfileItemAssignments(List<ExtendedProfileItemAssignment> extendedProfileItemAssignments) {
		this.extendedProfileItemAssignments = extendedProfileItemAssignments;
	}*/

	public List<String> getIndustrySegmentNames() {
		return industrySegmentNames;
	}

	public void setIndustrySegmentNames(List<String> industrySegmentNames) {
		this.industrySegmentNames = industrySegmentNames;
	}

	/**
	 * @return the orgId
	 */
	public String getOrgId() {
		return orgId;
	}
	/**
	 * @param orgId the orgId to set
	 */
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	

	/**
	 * @param addressL1 the addressL1 to set
	 */
	public void setAddressL1(String addressL1) {
		this.addressL1 = addressL1;
	}
	/**
	 * @return the addressL2
	 */
	public String getAddressL2() {
		return addressL2;
	}
	/**
	 * @param addressL2 the addressL2 to set
	 */
	public void setAddressL2(String addressL2) {
		this.addressL2 = addressL2;
	}
	/**
	 * @return the prmId
	 */
	public String getPrmID() {
		return prmID;
	}
	/**
	 * @param prmId the prmId to set
	 */
	public void setPrmID(String prmID) {
		this.prmID = prmID;
	}
	/**
	 * @return the addressL3
	 */
	public String getAddressL3() {
		return addressL3;
	}
	/**
	 * @param addressL3 the addressL3 to set
	 */
	public void setAddressL3(String addressL3) {
		this.addressL3 = addressL3;
	}
	/**
	 * @return the pLocator
	 */
	public String getpLocator() {
		return pLocator;
	}
	/**
	 * @param pLocator the pLocator to set
	 */
	public void setpLocator(String pLocator) {
		this.pLocator = pLocator;
	}
	/**
	 * @return the countryCode
	 */
	public String getCountryCode() {
		return countryCode;
	}
	/**
	 * @param countryCode the countryCode to set
	 */
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	/**
	 * @return the dunsID
	 */
	public String getDunsID() {
		return dunsID;
	}
	/**
	 * @param dunsID the dunsID to set
	 */
	public void setDunsID(String dunsID) {
		this.dunsID = dunsID;
	}
	/**
	 * @return taxID
	 */
	public String getTaxID() {
		return taxID;
	}
	/**
	 * @param taxID the taxID to set
	 */
	public void setTaxID(String taxID) {
		this.taxID = taxID;
	}

	/**
	 * @return recordStatus
	 */
	public String getRecordStatus() {
		String recordStatusFrom = recordStatus;
		if("LOC".equals(searchLevel)){
			recordStatusFrom = briRecordStatus;
		}
		
		if("N".equals(recordStatusFrom)){
			 return "ACTIVE";
		}else if("Y".equals(recordStatusFrom)){
			return "INACTIVE";
		}
		
		return recordStatus;
	}
	/**
	 * @param recordStatus the recordStatus to set
	 */
	public void setRecordStatus(String recordStatus) {
		this.recordStatus = recordStatus;
	}
	/**
	 * @return briRecordStatus
	 */
	public String getBriRecordStatus() {
		return briRecordStatus;
	}
	/**
	 * @param briRecordStatus the briRecordStatus to set
	 */
	public void setBriRecordStatus(String briRecordStatus) {
		this.briRecordStatus = briRecordStatus;
	}

	/**
	 * @return the indSegCd
	 */
	public String getIndSegCd() {
		return indSegCd;
	}

	/**
	 * @param indSegCd the indSegCd to set
	 */
	public void setIndSegCd(String indSegCd) {
		this.indSegCd = indSegCd;
	}

	/**
	 * @return the district
	 */
	public String getDistrict() {
		return district;
	}

	/**
	 * @param district the district to set
	 */
	public void setDistrict(String district) {
		this.district = district;
	}

	/**
	 * @return the searchLevel
	 */
	public String getSearchLevel() {
		return searchLevel;
	}

	/**
	 * @param searchLevel the searchLevel to set
	 */
	public void setSearchLevel(String searchLevel) {
		this.searchLevel = searchLevel;
	}

	/**
	 * @return the orgReportingName
	 */
	public String getOrgReportingName() {
		return orgReportingName;
	}

	/**
	 * @param orgReportingName the orgReportingName to set
	 */
	public void setOrgReportingName(String orgReportingName) {
		this.orgReportingName = orgReportingName;
	}

	/**
	 * @return the orgLlegalName
	 */
	public String getOrgLegalName() {
		return orgLegalName;
	}

	/**
	 * @param orgLlegalName the orgLlegalName to set
	 */
	public void setOrgLegalName(String orgLegalName) {
		this.orgLegalName = orgLegalName;
	}

	/**
	 * @return the sellIntoMarketCode
	 */
	public String getSellIntoMarketCode() {
		return sellIntoMarketCode;
	}

	/**
	 * @param sellIntoMarketCode the sellIntoMarketCode to set
	 */
	public void setSellIntoMarketCode(String sellIntoMarketCode) {
		this.sellIntoMarketCode = sellIntoMarketCode;
	}
	
	/**
	 * @return the criteriaType
	 */
	public String getCriteriaType() {
		return criteriaType;
	}

	/**
	 * @param criteriaType the criteriaType to set
	 */
	public void setCriteriaType(String criteriaType) {
		this.criteriaType = criteriaType;
	}
	
	/**
	 * @return the briRowId
	 */
	public String getBriRowId() {
		return briRowId;
	}

	/**
	 * @return the matchName
	 */
	public String getMatchName() {
		return matchName;
	}

	/**
	 * @param matchName the matchName to set
	 */
	public void setMatchName(String matchName) {
		this.matchName = matchName;
	}

	/**
	 * @param briRowId the briRowId to set
	 */
	public void setBriRowId(String briRowId) {
		this.briRowId = briRowId;
	}
	

	/**
	 * @return the brTypeName
	 */
	public String getBrTypeName() {
		if("Y".equalsIgnoreCase(brRecordStatus)){
			return brTypeName + " (Inactive)";
		}
		return brTypeName;
	}

	/**
	 * @param brTypeName the brTypeName to set
	 */
	public void setBrTypeName(String brTypeName) {
		this.brTypeName = brTypeName;
	}

	/**
	 * @return the baGroupName
	 */
	public String getBaGroupName() {
		if(null == baGroupName && "Y".equalsIgnoreCase(bsnRshpPtnrPHPInd)){
			return "PanHP";
		}
		return baGroupName;
	}

	/**
	 * @param baGroupName the baGroupName to set
	 */
	public void setBaGroupName(String baGroupName) {
		this.baGroupName = baGroupName;
	}

	/**
	 * @return the countryName
	 */
	public String getCountryName() {
		return countryName;
	}

	/**
	 * @param countryName the countryName to set
	 */
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	
	/**
	 * @return the sellIntoMarketName
	 */
	public String getSellIntoMarketName() {
		return sellIntoMarketName;
	}

	/**
	 * @param sellIntoMarketName the sellIntoMarketName to set
	 */
	public void setSellIntoMarketName(String sellIntoMarketName) {
		this.sellIntoMarketName = sellIntoMarketName;
	}

    /**
     * @return the lglSellIntoMarketCode
     */
    public String getLglSellIntoMarketCode() {
        return lglSellIntoMarketCode;
    }

    /**
     * @param lglSellIntoMarketCode the lglSellIntoMarketCode to set
     */
    public void setLglSellIntoMarketCode(String lglSellIntoMarketCode) {
        this.lglSellIntoMarketCode = lglSellIntoMarketCode;
    }

    /**
     * @return the lglSellIntoMarketName
     */
    public String getLglSellIntoMarketName() {
        return lglSellIntoMarketName;
    }

    
    /**
     * @param lglSellIntoMarketName the lglSellIntoMarketName to set
     */
    public void setLglSellIntoMarketName(String lglSellIntoMarketName) {
        this.lglSellIntoMarketName = lglSellIntoMarketName;
    }

    /**
	 * @return the brTypeCode
	 */
	public String getBrTypeCode() {
		return brTypeCode;
	}


	/**
	 * @param brTypeCode the brTypeCode to set
	 */
	public void setBrTypeCode(String brTypeCode) {
		this.brTypeCode = brTypeCode;
	}


	/**
	 * @return the baGroupId
	 */
	public String getBaGroupId() {
		return baGroupId;
	}


	/**
	 * @param baGroupId the baGroupId to set
	 */
	public void setBaGroupId(String baGroupId) {
		this.baGroupId = baGroupId;
	}


	/**
	 * @return the brUnitName
	 */
	public String getBrUnitName() {
		return brUnitName;
	}


	/**
	 * @param brUnitName the brUnitName to set
	 */
	public void setBrUnitName(String brUnitName) {
		this.brUnitName = brUnitName;
	}


	/**
	 * @return the stateProvinceName
	 */
	public String getStateProvinceName() {
		return stateProvinceName;
	}


	/**
	 * @param stateProvinceName the stateProvinceName to set
	 */
	public void setStateProvinceName(String stateProvinceName) {
		this.stateProvinceName = stateProvinceName;
	}


	/**
	 * @return the cityName
	 */
	public String getCityName() {
		return cityName;
	}


	/**
	 * @param cityName the cityName to set
	 */
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}


	/**
	 * @return the bpUsageName
	 */
	public String getBpUsageName() {
		return bpUsageName;
	}


	/**
	 * @param bpUsageName the bpUsageName to set
	 */
	public void setBpUsageName(String bpUsageName) {
		this.bpUsageName = bpUsageName;
	}


	/**
	 * @return the partnerIndicator
	 */
	public String getPartnerIndicator() {
		return partnerIndicator;
	}


	/**
	 * @param partnerIndicator the partnerIndicator to set
	 */
	public void setPartnerIndicator(String partnerIndicator) {
		this.partnerIndicator = partnerIndicator;
	}


	/**
	 * @return the customerIndicator
	 */
	public String getCustomerIndicator() {
		return customerIndicator;
	}


	/**
	 * @param customerIndicator the customerIndicator to set
	 */
	public void setCustomerIndicator(String customerIndicator) {
		this.customerIndicator = customerIndicator;
	}


	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	/**
	 * @return the addressL1
	 */
	public String getAddressL1() {
		return addressL1;
	}
	
	/**
	 * @return the cScriptCode
	 */
	public String getcScriptCode() {
		return cScriptCode;
	}


	/**
	 * @param cScriptCode the cScriptCode to set
	 */
	public void setcScriptCode(String cScriptCode) {
		this.cScriptCode = cScriptCode;
	}


	/**
	 * @return the brRowId
	 */
	public String getBrRowId() {
		return brRowId;
	}


	/**
	 * @param brRowId the brRowId to set
	 */
	public void setBrRowId(String brRowId) {
		this.brRowId = brRowId;
	}


	/**
	 * @return the pbRelationshipIndicator
	 */
	public String getPbRelationshipIndicator() {
		return pbRelationshipIndicator;
	}


	/**
	 * @param pbRelationshipIndicator the pbRelationshipIndicator to set
	 */
	public void setPbRelationshipIndicator(String pbRelationshipIndicator) {
		this.pbRelationshipIndicator = pbRelationshipIndicator;
	}


	/**
	 * @return the prmyOpsInsnRowId
	 */
	public String getPrmyOpsInsnRowId() {
		return prmyOpsInsnRowId;
	}


	/**
	 * @param prmyOpsInsnRowId the prmyOpsInsnRowId to set
	 */
	public void setPrmyOpsInsnRowId(String prmyOpsInsnRowId) {
		this.prmyOpsInsnRowId = prmyOpsInsnRowId;
	}


	/**
	 * @return the otherPartySiteInstanceRowId
	 */
	public String getOtherPartySiteInstanceRowId() {
		return otherPartySiteInstanceRowId;
	}


	/**
	 * @param otherPartySiteInstanceRowId the otherPartySiteInstanceRowId to set
	 */
	public void setOtherPartySiteInstanceRowId(String otherPartySiteInstanceRowId) {
		this.otherPartySiteInstanceRowId = otherPartySiteInstanceRowId;
	}


	/**
	 * @return the otrPrtySiteFaxTelNr
	 */
	public String getOtrPrtySiteFaxTelNr() {
		return otrPrtySiteFaxTelNr;
	}


	/**
	 * @param otrPrtySiteFaxTelNr the otrPrtySiteFaxTelNr to set
	 */
	public void setOtrPrtySiteFaxTelNr(String otrPrtySiteFaxTelNr) {
		this.otrPrtySiteFaxTelNr = otrPrtySiteFaxTelNr;
	}


	/**
	 * @return the externalProfileLocatorId
	 */
	public String getExternalProfileLocatorId() {
		return externalProfileLocatorId;
	}


	/**
	 * @param externalProfileLocatorId the externalProfileLocatorId to set
	 */
	public void setExternalProfileLocatorId(String externalProfileLocatorId) {
		this.externalProfileLocatorId = externalProfileLocatorId;
	}


	/**
	 * @return the primaryBusinessInstanceIndicator
	 */
	public String getPrimaryBusinessInstanceIndicator() {
		return primaryBusinessInstanceIndicator;
	}
	
	/**
	 * @return the true  primaryBusinessInstanceIndicator
	 */
	//@JsonIgnore
	public String getTruePrimaryBusinessInstanceIndicator() {
		return primaryBusinessInstanceIndicator;
	}
	

	/**
	 * @param primaryBusinessInstanceIndicator the primaryBusinessInstanceIndicator to set
	 */
	public void setPrimaryBusinessInstanceIndicator(
			String primaryBusinessInstanceIndicator) {
		this.primaryBusinessInstanceIndicator = primaryBusinessInstanceIndicator;
	}
	
	/**
	 * @return the actionCode
	 */
	public String getActionCode() {
		return actionCode == null ? "" : actionCode;
	}

	/**
	 * @param actionCode the actionCode to set
	 */
	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
	}

	/**
	 * @return the bsnRshpPtnrPHPInd
	 */
	public String getBsnRshpPtnrPHPInd() {
		return bsnRshpPtnrPHPInd;
	}

	/**
	 * @param bsnRshpPtnrPHPInd the bsnRshpPtnrPHPInd to set
	 */
	public void setBsnRshpPtnrPHPInd(String bsnRshpPtnrPHPInd) {
		this.bsnRshpPtnrPHPInd = bsnRshpPtnrPHPInd;
	}

	public String getPerferredNameForAccount() {
		return perferredNameForAccount;
	}

	public void setPerferredNameForAccount(String perferredNameForAccount) {
		this.perferredNameForAccount = perferredNameForAccount;
	}
	
	public String getcScriptCodeForAccount() {
		return cScriptCodeForAccount;
	}

	public void setcScriptCodeForAccount(String cScriptCodeForAccount) {
		this.cScriptCodeForAccount = cScriptCodeForAccount;
	}
	
	public String getOrganizationNonLatinExtendedName() {
		return organizationNonLatinExtendedName;
	}

	public void setOrganizationNonLatinExtendedName(
			String organizationNonLatinExtendedName) {
		this.organizationNonLatinExtendedName = organizationNonLatinExtendedName;
	}

	/**
	 * @return brRecordStatus
	 */
	public String getBrRecordStatus() {
		return brRecordStatus;
	}

	/**
	 * @param brRecordStatus the brRecordStatus to set
	 */
	public void setBrRecordStatus(String brRecordStatus) {
		this.brRecordStatus = brRecordStatus;
	}

	/**
	 * @return the perferredName
	 */
	public String getPerferredName() {
		return perferredName;
	}

	/**
	 * @param perferredName the perferredName to set
	 */
	public void setPerferredName(String perferredName) {
		this.perferredName = perferredName;
	}
	
	public String getNumberOfEmployee() {
		return numberOfEmployee;
	}

	public void setNumberOfEmployee(String numberOfEmployee) {
		this.numberOfEmployee = numberOfEmployee;
	}
	
	
	public String getNonlatinCity() {
		return nonlatinCity;
	}

	public void setNonlatinCity(String nonlatinCity) {
		this.nonlatinCity = nonlatinCity;
	}





	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((addressL1 == null) ? 0 : addressL1.hashCode());
		result = prime * result + ((addressL2 == null) ? 0 : addressL2.hashCode());
		result = prime * result + ((addressL3 == null) ? 0 : addressL3.hashCode());
		result = prime * result + ((baGroupId == null) ? 0 : baGroupId.hashCode());
		result = prime * result + ((bpUsageName == null) ? 0 : bpUsageName.hashCode());
		result = prime * result + ((brTypeCode == null) ? 0 : brTypeCode.hashCode());
		result = prime * result + ((brUnitName == null) ? 0 : brUnitName.hashCode());
		result = prime * result + ((briRecordStatus == null) ? 0 : briRecordStatus.hashCode());
		result = prime * result + ((briRowId == null) ? 0 : briRowId.hashCode());
		result = prime * result + ((cityName == null) ? 0 : cityName.hashCode());
		result = prime * result + ((countryCode == null) ? 0 : countryCode.hashCode());
		result = prime * result + ((customerIndicator == null) ? 0 : customerIndicator.hashCode());
		result = prime * result + ((district == null) ? 0 : district.hashCode());
		result = prime * result + ((dunsID == null) ? 0 : dunsID.hashCode());
		result = prime * result + ((faxNumber == null) ? 0 : faxNumber.hashCode());
		result = prime * result + ((indSegCd == null) ? 0 : indSegCd.hashCode());
		result = prime * result + ((orgExtName == null) ? 0 : orgExtName.hashCode());
		result = prime * result + ((orgId == null) ? 0 : orgId.hashCode());
		result = prime * result + ((orgLegalName == null) ? 0 : orgLegalName.hashCode());
		result = prime * result + ((orgReportingName == null) ? 0 : orgReportingName.hashCode());
		result = prime * result + ((pLocator == null) ? 0 : pLocator.hashCode());
		result = prime * result + ((pPreName == null) ? 0 : pPreName.hashCode());
		result = prime * result + ((partnerIndicator == null) ? 0 : partnerIndicator.hashCode());
		result = prime * result + ((pbRelationshipIndicator == null) ? 0 : pbRelationshipIndicator.hashCode());
		result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
		result = prime * result + ((postalCode == null) ? 0 : postalCode.hashCode());
		result = prime * result + ((prmID == null) ? 0 : prmID.hashCode());
		result = prime * result + ((recordStatus == null) ? 0 : recordStatus.hashCode());
		result = prime * result + ((sellIntoMarketCode == null) ? 0 : sellIntoMarketCode.hashCode());
		result = prime * result + ((lglSellIntoMarketCode == null) ? 0 : lglSellIntoMarketCode.hashCode());
		result = prime * result + ((stateProvinceName == null) ? 0 : stateProvinceName.hashCode());
		result = prime * result + ((taxID == null) ? 0 : taxID.hashCode());
		result = prime * result + ((brTypeName == null) ? 0 : brTypeName.hashCode());
		result = prime * result + ((baGroupName == null) ? 0 : baGroupName.hashCode());
		result = prime * result + ((countryName == null) ? 0 : countryName.hashCode());
		result = prime * result + ((sellIntoMarketName == null) ? 0 : sellIntoMarketName.hashCode());
		result = prime * result + ((lglSellIntoMarketName == null) ? 0 : lglSellIntoMarketName.hashCode());
		result = prime * result + ((perferredName == null) ? 0 : perferredName.hashCode());
		result = prime * result + ((perferredNameForAccount == null) ? 0 : perferredNameForAccount.hashCode());
		result = prime * result + ((cScriptCodeForAccount == null) ? 0 : cScriptCodeForAccount.hashCode());
		result = prime * result + ((organizationNonLatinExtendedName == null) ? 0 : organizationNonLatinExtendedName.hashCode());
		result = prime * result + ((numberOfEmployee == null) ? 0 : numberOfEmployee.hashCode());
		result = prime * result + ((nonlatinCity == null) ? 0 : nonlatinCity.hashCode());
		result = prime * result + ((industrySegmentNames == null) ? 0 : industrySegmentNames.hashCode());
		result = prime * result + ((industrySegmentName == null) ? 0 : industrySegmentName.hashCode());
		result = prime * result + ((isCompetitor == null) ? 0 : isCompetitor.hashCode());
		return result;
	}
}