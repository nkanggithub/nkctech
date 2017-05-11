package com.nkang.kxmoment.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.nkang.kxmoment.baseobject.ClientMeta;
import com.nkang.kxmoment.baseobject.PlatforRelated;
import com.nkang.kxmoment.util.Constants;

public class FileOperateUtil {
	 static List<String> Jeffrey;
	 static List<String> Antonio;
	 static List<String> Nils;
	 static List<String> China;
	 static List<String> Other;
	
	public static String FILEDIR = null;
    /**
     * 上传
     * @param request
     * @throws IOException
     */
    public static void upload(HttpServletRequest request) throws IOException{       
        MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = mRequest.getFileMap();       
        File file = new File(FILEDIR);
        if (!file.exists()) {
            file.mkdir();
        }
        Iterator<Map.Entry<String, MultipartFile>> it = fileMap.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<String, MultipartFile> entry = it.next();
            MultipartFile mFile = entry.getValue();
            if(mFile.getSize() != 0 && !"".equals(mFile.getName())){
                write(mFile.getInputStream(), new FileOutputStream(initFilePath(mFile.getOriginalFilename())));
            }
        }
    }
    private static String initFilePath(String name) {
        String dir = getFileDir(name) + "";
        File file = new File(FILEDIR + dir);
        if (!file.exists()) {
            file.mkdir();
        }
        Long num = new Date().getTime();
        Double d = Math.random()*num;
        return (file.getPath() + "/" + num + d.longValue() + "_" + name).replaceAll(" ", "-");
    }
    private static int getFileDir(String name) {
        return name.hashCode() & 0xf;
    }
    public static void download(String downloadfFileName, ServletOutputStream out) {
        try {
            FileInputStream in = new FileInputStream(new File(FILEDIR + "/" + downloadfFileName));
            write(in, out);
        } catch (FileNotFoundException e) {
            try {
                FileInputStream in = new FileInputStream(new File(FILEDIR + "/"
                        + new String(downloadfFileName.getBytes("iso-8859-1"),"utf-8")));
                write(in, out);
            } catch (IOException e1) {              
                e1.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }       
    }
    /**
     * 写入数据
     * @param in
     * @param out
     * @throws IOException
     */
    public static void write(InputStream in, OutputStream out) throws IOException{
        try{
            byte[] buffer = new byte[1024];
            int bytesRead = -1;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
            out.flush();
        } finally {
            try {
                in.close();
            }
            catch (IOException ex) {
            }
            try {
                out.close();
            }
            catch (IOException ex) {
            }
        }
    } 
    
    
    @SuppressWarnings({ "unchecked", "rawtypes", "unchecked" })
	public static Map<String, List> OperateOnPlatforRelated(InputStream is){
    	Map map = new HashMap<String, List>();
    	PoiUtil bos = new PoiUtil();
    		try {
    			PlatforRelated  platforRelated  = bos.platformRelated(is);
    			if(platforRelated!=null){
    				MongoDBBasic.updatePlatforRelated(platforRelated, Constants.clientCode);
    			}
    			List APJlt = new ArrayList<Integer>();
    			APJlt.add(platforRelated.getDone_APJ());
    			APJlt.add(platforRelated.getInProgress_APJ());
    			APJlt.add(platforRelated.getInPlanning_APJ());
    			List USAlt = new ArrayList<Integer>();
    			USAlt.add(platforRelated.getDone_USA());
    			USAlt.add(platforRelated.getInProgress_USA());
    			USAlt.add(platforRelated.getInPlanning_USA());
    			
    			List MEXICOlt = new ArrayList<Integer>();
    			MEXICOlt.add(platforRelated.getDone_MEXICO());
    			MEXICOlt.add(platforRelated.getInProgress_MEXICO());
    			MEXICOlt.add(platforRelated.getInPlanning_MEXICO());
    			
    			List EMEAlt = new ArrayList<Integer>();
    			EMEAlt.add(platforRelated.getDone_EMEA());
    			EMEAlt.add(platforRelated.getInProgress_EMEA());
    			EMEAlt.add(platforRelated.getInPlanning_EMEA());
    			
    			List outOfMapping = platforRelated.getOutNames();
    			
    			map.put("APJ", APJlt);
    			map.put("USA", USAlt);
    			map.put("MEXICO", MEXICOlt);
    			map.put("EMEA", EMEAlt);
    			map.put("OutOfMapping", outOfMapping);
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    			//System.out.println(e.getMessage());
    		}finally{
    			if(is != null){
    				try {
    					is.close();
    				} catch (IOException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
    			}
    		}
    	return map;
    }
    
    public static void readMetricsMapping() {
        Jeffrey = new ArrayList<String>();
        Antonio = new ArrayList<String>();
        Nils = new ArrayList<String>();
        China = new ArrayList<String>();
        Other = new ArrayList<String>();
        ClientMeta cm = new ClientMeta();
       
        cm=MongoDBBasic.QueryClientMeta(Constants.clientCode);
        String str = cm.getMetricsMapping();
       /* String str="";
        try {
        	  str = RestUtils.callQueryClientMetaByClientCode();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
        String[] ss =str.split(",");
		for(String s : ss){
			System.out.println(s);
			String[] sss =s.split("\\|");
			if(sss.length>1){
				if("Jeffrey".equals(sss[1].trim())){
					Jeffrey.add(sss[0].trim());
					continue;
				}
				if("Antonio".equals(sss[1].trim())){
            		Antonio.add(sss[0].trim());
            		continue;
            	}
            	if("Nils".equals(sss[1].trim())){
            		Nils.add(sss[0].trim());
            		continue;
            	}
            	if("China".equals(sss[1].trim())){
            		China.add(sss[0].trim());
            		continue;
            	}
            	if("Other".equals(sss[1].trim())){
            		Other.add(sss[0].trim());
            	}
				
			}
			
		}
 }
 
	/*  public static String readAGM(InputStream is){
    	PoiUtil bos = new PoiUtil();
    	String message="";
    	try {
    		bos.readAGM(is);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return message;
    	
    }
    */
    public static PlatforRelated OperateOnReport(InputStream is){
    	PoiUtil bos = new PoiUtil();
    	//Map<String,Integer> map = new HashMap<String,Integer>();
    	PlatforRelated  platforRelated=new PlatforRelated();
    		try {
    			 platforRelated  = bos.uploadReport(is);
    			 if(platforRelated!=null){
    				 MongoDBBasic.updatePlatforRelated(platforRelated,Constants.clientCode);
    			 }
    			
    			/*	
     			map.put("APJ", platforRelated.getClosed_APJ());
     			map.put("USA", platforRelated.getClosed_USA());
     			map.put("MEXICO", platforRelated.getClosed_MEXICO());
     			map.put("EMEA", platforRelated.getClosed_EMEA());
     			map.put("OTHER", platforRelated.getClosed_OTHER());*/
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    			//System.out.println(e.getMessage());
    		}finally{
    			if(is != null){
    				try {
    					is.close();
    				} catch (IOException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
    			}
    		}
    //	return "APJ : "+ platforRelated.getClosed_APJ()+"<br>"+"USA : "+ platforRelated.getClosed_USA()+"<br>"+"MEXICO : "+ platforRelated.getClosed_MEXICO()+"<br>"+"EMEA : "+ platforRelated.getClosed_EMEA()+"<br>"+"Other : "+ platforRelated.getClosed_OTHER();
			return platforRelated;
    }
}
