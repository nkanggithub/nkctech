package com.nkang.kxmoment.util.BosUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.baidubce.auth.BceCredentials;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.bos.BosClient;
import com.baidubce.services.bos.BosClientConfiguration;
import com.baidubce.services.bos.model.BucketSummary;
import com.baidubce.services.bos.model.CannedAccessControlList;
import com.baidubce.services.bos.model.Grant;
import com.baidubce.services.bos.model.Grantee;
import com.baidubce.services.bos.model.PutObjectResponse;
import com.nkang.kxmoment.util.Constants;


public class MyBosClient {

	public static BosClient client = null;
	static{
		BosClientConfiguration config = new BosClientConfiguration();
		config.setCredentials(new DefaultBceCredentials(Constants.BAIDU_APPKEY, Constants.BAIDU_APPSECRET));
		config.setMaxConnections(10);
		config.setConnectionTimeoutInMillis(5000);
		config.setSocketTimeoutInMillis(2000);
	    config.setEndpoint(Constants.bosDomain);  
		client = new BosClient(config);
	}
}
