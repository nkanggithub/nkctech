package com.nkang.kxmoment.util.SolrUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.params.MapSolrParams;
import org.apache.solr.common.params.SolrParams;

import com.nkang.kxmoment.baseobject.OrgOtherPartySiteInstance;
import com.nkang.kxmoment.baseobject.OrganizationSearch4Solr;
import com.nkang.kxmoment.util.StringUtils;

public class SolrClientUtils{
	private static final Logger logger = Logger.getLogger(SolrClientUtils.class);
	private static HttpSolrServer solrServer;
	static {
		initSolrConnection();
	}

	public static QueryResponse queryOrgInformations(OrganizationSearch4Solr orgCriteria, int section) {
		QueryResponse queryResponse = null;
        try {
        	int start = section*1000;
        	int end = (section*1000) + 1000;
        	SolrQuery query = getSolrQuery(orgCriteria, start, end);
			queryResponse = solrServer.query(query);
		} catch (SolrServerException e) {
			logger.error(e);
		}
		return queryResponse;
	}
	
	
	private static SolrQuery getSolrQuery(OrganizationSearch4Solr request, int start, int end) {
		//System.out.println("Start From " + start + " End to " + end);
		SolrQuery query = new SolrQuery();
		query.setHighlight(true).setStart(start);
        query.setTermsLimit(500);
        query.setRows(20000);
        Map<String, String> map = new HashMap<String, String>();
        map.put("wt", "json");
        map.put("indent", "true");
        StringBuilder queryPrams = new StringBuilder();
        queryPrams.append("{!q.op=OR}+isOutOfBusiness:false AND ");
        if (!StringUtils.isEmpty(request.getOrgId())) {
        	queryPrams.append("organizationId:" + request.getOrgId() + " AND ");
        }
        if (!StringUtils.isEmpty(request.getSiteinstanceId())) {
        	queryPrams.append("siteInstanceId:" + request.getSiteinstanceId() + " AND ");
        }
        List<String> orgIdList = request.getOrgIdList();
        if (orgIdList != null && orgIdList.size() > 0) {
        	String idsString = "+organizationId:(";
        	for (String orgId : orgIdList) {
            	idsString += orgId + " ";
			}
        	idsString = idsString.substring(0, idsString.length()-1) + ")";
        	queryPrams.append(idsString + " AND ");
        }
        if (!StringUtils.isEmpty(request.getTaxID())) {
        	queryPrams.append("taxIds:(\"" + request.getTaxID() + "\") AND ");
        }
        String orgName = request.getOrgName();
        if (!StringUtils.isEmpty(orgName)) {
        	orgName = "("+ orgName +")";
    		// LATIN
    		queryPrams.append("(+(organizationExtendedName:" + orgName + " ");
    		// NOLATIN
    		queryPrams.append("organizationNonLatinExtendedName:" + orgName + " ");
    		// ReportingName
    		queryPrams.append("organizationReportingName:" + orgName + " ");
    		// LegalName
    		queryPrams.append("organizationLegalName:" + orgName + " ");
    		// tradeName
    		queryPrams.append("tradeStyleNames:" + orgName + ")) AND ");
        }
        String cityName = request.getCityName();
        if (!StringUtils.isEmpty(cityName)) {
    		// LATIN
    		queryPrams.append("(+(latinCity:(\"" + cityName + "\") ");
    		// NOLATIN
    		queryPrams.append("nonlatinCity:(\"" + cityName + "\"))) AND ");
        }
        if (!StringUtils.isEmpty(request.getPostalCode())) {
            queryPrams.append("postalCode:(" + request.getPostalCode() + "*) AND ");
        }
        if (!StringUtils.isEmpty(request.getCountryCode())) {
            queryPrams.append("countryCode:(" + request.getCountryCode() + ") AND ");
        }
        String state = request.getStateProvinceName();
        if (!StringUtils.isEmpty(state)) {
        	if(state.matches("\\p{ASCII}+")){
        		// LATIN
                queryPrams.append("state:(\"" + state + "\") AND ");
        	}else{
        		// NOLATIN
                queryPrams.append("state:(\"" + state + "\") AND ");
        	}
        }

        queryPrams.append(" *:* ");
        
        SolrParams params = new MapSolrParams(map);
        
        query.add(params);
        query.setQuery(queryPrams.toString());
		return query;
	}
	
	
	private static void initSolrConnection() {
		if(solrServer == null){
			logger.info("start initial solr connection...");
			ResourceBundle bundle = ResourceBundle.getBundle("solrconfig");  
			String certificationPath = bundle.getString("certificationPath");
			String solrServerUrl;
		    solrServerUrl = bundle.getString("organizationaccountlucenesearch.url");
	        //String solrServerUrl = "http://c0050304.itcs.hp.com:8080/solr/organization-account";
	        System.setProperty("javax.net.ssl.trustStore", certificationPath);
	        HttpSolrServer server = new HttpSolrServer(solrServerUrl);
	        solrServer = server;
	        logger.info("finish initial solr connection...");
		}else{
			logger.info("solr is connectioned...");
		}
    }
	
	
	public static String getStringValue(SolrDocument doc , String fieldName) {
		String strValue = null ;
		Object value = doc.getFieldValue(fieldName);
		if(value!=null) {
			strValue = value.toString();
		}
		return strValue;
	}


	public static List<OrgOtherPartySiteInstance> getOPSIList(int section) {
		List<OrgOtherPartySiteInstance> opsiList  = new ArrayList<OrgOtherPartySiteInstance>();
		OrgOtherPartySiteInstance opsi;
		OrganizationSearch4Solr solrSearchParams = new OrganizationSearch4Solr();
		solrSearchParams.setCountryCode("KR");
		solrSearchParams.setCharScriptCode("LATN");
		solrSearchParams.setStateProvinceName("Jeju");
		//solrSearchParams.setOrgId("23074049");
/*		solrSearchParams.setSiteinstanceId("36742221");*/
		QueryResponse queryResponse = SolrClientUtils.queryOrgInformations(solrSearchParams, section);
		
		SolrDocumentList docs = queryResponse.getResults();
		
		Iterator<SolrDocument> orgInfosIterator = docs.iterator();
		while (orgInfosIterator.hasNext()) {
			opsi = new OrgOtherPartySiteInstance();
			SolrDocument resultDoc = (SolrDocument) orgInfosIterator.next();
			opsi.setAmid2(SolrClientUtils.getStringValue(resultDoc,"amid2"));
			opsi.setBranchIndicator(SolrClientUtils.getStringValue(resultDoc,"branchIndicator"));
			opsi.setCharScriptCode(SolrClientUtils.getStringValue(resultDoc,"charScriptCode"));
			opsi.setCityRegion(SolrClientUtils.getStringValue(resultDoc,"cityRegion"));
			opsi.setCountOfEmployee(SolrClientUtils.getStringValue(resultDoc,"countOfEmployee"));
			opsi.setCountryCode(SolrClientUtils.getStringValue(resultDoc,"countryCode"));
			opsi.setCountryName(SolrClientUtils.getStringValue(resultDoc,"countryName"));
			opsi.setCountryRegionCode(SolrClientUtils.getStringValue(resultDoc,"countryRegionCode"));
			opsi.setCountryRegionName(SolrClientUtils.getStringValue(resultDoc,"countryRegionName"));
			opsi.setDeletionIndicator(SolrClientUtils.getStringValue(resultDoc,"deletionIndicator"));
			opsi.setDomesticDuns(SolrClientUtils.getStringValue(resultDoc,"domesticDuns"));
			opsi.setDuns(SolrClientUtils.getStringValue(resultDoc,"duns"));
			opsi.setFocusAccountIndicator(SolrClientUtils.getStringValue(resultDoc,"focusAccountIndicator"));
			opsi.setGlobalAccountIndicator(SolrClientUtils.getStringValue(resultDoc,"globalAccountIndicator"));
			opsi.setGlobalDuns(SolrClientUtils.getStringValue(resultDoc,"globalDuns"));
			opsi.setGlobalDunsName(SolrClientUtils.getStringValue(resultDoc,"globalDunsName"));
			opsi.setHeadDuns(SolrClientUtils.getStringValue(resultDoc,"headDuns"));
			opsi.setHeadDunsName(SolrClientUtils.getStringValue(resultDoc,"headDunsName"));
			opsi.setHyperscaleAccountIndicator(SolrClientUtils.getStringValue(resultDoc,"hyperscaleAccountIndicator"));
			opsi.setIncludePartnerOrgIndicator(SolrClientUtils.getStringValue(resultDoc,"includePartnerOrgIndicator"));
			opsi.setIndicatorBags(SolrClientUtils.getStringValue(resultDoc,"indicatorBags"));
			opsi.setIndustrySegmentNames(SolrClientUtils.getStringValue(resultDoc,"industrySegmentNames"));
			opsi.setIndustryVerticalNames(SolrClientUtils.getStringValue(resultDoc,"industryVerticalNames"));
			opsi.setIsCompetitor(SolrClientUtils.getStringValue(resultDoc,"isCompetitor"));
			opsi.setIsGlobalAccount(SolrClientUtils.getStringValue(resultDoc,"isGlobalAccount"));
			opsi.setIsOutOfBusiness(SolrClientUtils.getStringValue(resultDoc,"isOutOfBusiness"));
			opsi.setLanguageCode(SolrClientUtils.getStringValue(resultDoc,"languageCode"));
			opsi.setLatinCity(SolrClientUtils.getStringValue(resultDoc,"latinCity"));
			opsi.setLatinStreet1LongName(SolrClientUtils.getStringValue(resultDoc,"latinStreet1LongName"));
			opsi.setMailingSiteDuns(SolrClientUtils.getStringValue(resultDoc,"mailingSiteDuns"));
			opsi.setNamedAccountIndicator(SolrClientUtils.getStringValue(resultDoc,"namedAccountIndicator"));
			opsi.setNonlatinCity(SolrClientUtils.getStringValue(resultDoc,"nonlatinCity"));
			opsi.setNonlatinStreet1LongName(SolrClientUtils.getStringValue(resultDoc,"nonlatinStreet1LongName"));
			opsi.setOnlyPresaleCustomer(SolrClientUtils.getStringValue(resultDoc,"onlyPresaleCustomer"));
			opsi.setOrganizationExtendedName(SolrClientUtils.getStringValue(resultDoc,"organizationExtendedName"));
			opsi.setOrganizationId(SolrClientUtils.getStringValue(resultDoc,"organizationId"));
			opsi.setOrganizationLegalName(SolrClientUtils.getStringValue(resultDoc,"organizationLegalName"));
			opsi.setOrganizationNonLatinExtendedName(SolrClientUtils.getStringValue(resultDoc,"organizationNonLatinExtendedName"));
			opsi.setOrganizationNonLatinLegalName(SolrClientUtils.getStringValue(resultDoc,"organizationNonLatinLegalName"));
			opsi.setOrganizationNonLatinReportingName(SolrClientUtils.getStringValue(resultDoc,"organizationNonLatinReportingName"));
			opsi.setOrganizationReportingName(SolrClientUtils.getStringValue(resultDoc,"organizationReportingName"));
			opsi.setOrgCountryCode(SolrClientUtils.getStringValue(resultDoc,"orgCountryCode"));
			opsi.setOrgCountryName(SolrClientUtils.getStringValue(resultDoc,"orgCountryName"));
			opsi.setParentCountryCode(SolrClientUtils.getStringValue(resultDoc,"parentCountryCode"));
			opsi.setParentOrganizationId(SolrClientUtils.getStringValue(resultDoc,"parentOrganizationId"));
			opsi.setParentOrganizationName(SolrClientUtils.getStringValue(resultDoc,"parentOrganizationName"));
			opsi.setPostalCode(SolrClientUtils.getStringValue(resultDoc,"postalCode"));
			opsi.setPostalCode2(SolrClientUtils.getStringValue(resultDoc,"postalCode2"));
			opsi.setPresalesId(SolrClientUtils.getStringValue(resultDoc,"presalesId"));
			opsi.setRad(SolrClientUtils.getStringValue(resultDoc,"rad"));
			opsi.setRadBags(SolrClientUtils.getStringValue(resultDoc,"radBags"));
			opsi.setReturnPartnerFlag(SolrClientUtils.getStringValue(resultDoc,"radBagsreturnPartnerFlag"));
			opsi.setRplStatusCode(SolrClientUtils.getStringValue(resultDoc,"rplStatusCode"));
			opsi.setRplStatusTime(SolrClientUtils.getStringValue(resultDoc,"rplStatusTime"));
			opsi.setSalesCoverageSegments(SolrClientUtils.getStringValue(resultDoc,"salesCoverageSegments"));
			opsi.setSiteDuns(SolrClientUtils.getStringValue(resultDoc,"siteDuns"));
			opsi.setSiteId(SolrClientUtils.getStringValue(resultDoc,"siteId"));
			opsi.setSiteInstanceId(SolrClientUtils.getStringValue(resultDoc,"siteInstanceId"));
			opsi.setSiteName(SolrClientUtils.getStringValue(resultDoc,"siteName"));
			opsi.setSlsCrgSegmtNameBags(SolrClientUtils.getStringValue(resultDoc,"slsCrgSegmtNameBags"));
			opsi.setState(SolrClientUtils.getStringValue(resultDoc,"state"));
			opsi.setStreetAddress1(SolrClientUtils.getStringValue(resultDoc,"streetAddress1"));
			opsi.setStreetAddress2(SolrClientUtils.getStringValue(resultDoc,"streetAddress2"));
			opsi.setStreetAddress3(SolrClientUtils.getStringValue(resultDoc,"streetAddress3"));
			opsi.setTargetSegmentNames(SolrClientUtils.getStringValue(resultDoc,"targetSegmentNames"));
			opsi.setTargetSubSegmentNames(SolrClientUtils.getStringValue(resultDoc,"targetSubSegmentNames"));
			opsi.setTaxIds(SolrClientUtils.getStringValue(resultDoc,"taxIds"));
			opsi.setTgtSegmtNameBags(SolrClientUtils.getStringValue(resultDoc,"tgtSegmtNameBags"));
			opsi.setTopAccountIndicator(SolrClientUtils.getStringValue(resultDoc,"topAccountIndicator"));
			opsi.setTopParentOrganizationId(SolrClientUtils.getStringValue(resultDoc,"topParentOrganizationId"));
			opsi.setTopParentOrganizationName(SolrClientUtils.getStringValue(resultDoc,"topParentOrganizationName"));
			opsi.setWorldRegion(SolrClientUtils.getStringValue(resultDoc,"worldRegion"));
			opsi.setWorldRegionPath(SolrClientUtils.getStringValue(resultDoc,"worldRegionPath"));			
			opsiList.add(opsi);
		}
		return opsiList;
	}
}
