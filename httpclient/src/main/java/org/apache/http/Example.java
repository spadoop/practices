package org.apache.http;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.http.client.ClientProtocolException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Example {

	public static void main(String[] args) throws ClientProtocolException, IOException {
		// TODO Auto-generated method stub
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, 355);
		Date date = calendar.getTime();
		/*Protocol myhttps = new Protocol("https", new MySecureProtocolSocketFactory (), 8443);
		 
		//•注册刚才创建的https 协议对象
		Protocol.registerProtocol("https", myhttps);
		
		
		DefaultHttpClient httpClient = new DefaultHttpClient();
		httpClient.getParams().setParameter(ClientPNames.COOKIE_POLICY,CookiePolicy.BROWSER_COMPATIBILITY);
		httpClient.setCookieStore(new BasicCookieStore());
		BasicClientCookie cookie = new BasicClientCookie("TGC", "eyJhbGciOiJIUzUxMiJ9.ZXlKaGJHY2lPaUprYVhJaUxDSmxibU1pT2lKQk1USTRRMEpETFVoVE1qVTJJbjAuLnVBYXlNVllpYjdGZW9JRXdHV1o4blEuWUdDbzR5SU5DT0Y4ZXNKT2JNUlZCX29uUTRYX1pOclRVeU1BZnUwUmlxUXJUdDFmcklPMmNDbkZwd3RYNFpleVgzU19CSjBJdG9ndWNScGxRQTAzc0lqczNqWjg2d1JBV2hJZUpRWVlhUGZJd3FyUDJob19nQnNGVDhEbFRzYlNHd0FKQ2lzOFRlSHlGMmRpMkEybVlubzJxWDc0UGowZzZXY09CNENkTkNIa0V0RnZsRkdfNWFjMzFpM05ST0JSQWgtU1ppdnc1elVNNTRPWGtqVjZTaVZpZkg0UUh4ajVfSlhhTDE0VExTNmZ5VUNnV2VRM3NRVXlkRERzQ3JqUlU1OTNYVVR1UDZnenpTOEM2SmpPNTFfWGZXY3VVaE05WFNOTWZDU1BYVnZ3end2ZTlRSUhLNE1GN2F2UnVLLVJKN3V4RGt5cmt1VzFqV055QThWUnlBLkJqM0kzVWw5aWVPTVd6Q0ZSQURFaXc.jEsh8lQs77-dcLos4lYI4ivmOJEAePVt1UQfG1jxoPWq9MydZxZwWiDe0ntcbAF5kCBxSV1YqQAFUaL4qSZp2A");
		cookie.setDomain("cas.sysu.edu.cn");
		cookie.setExpiryDate(date);
		cookie.setPath("/");
		httpClient.getCookieStore().addCookie(cookie); 
		
		cookie = new BasicClientCookie("JSESSIONID", "9EF1EFFD30E91479B5A74022E9DE49F5");
		cookie.setDomain("cas.sysu.edu.cn");
		cookie.setExpiryDate(date);
		cookie.setPath("/");
		httpClient.getCookieStore().addCookie(cookie); 
		
		HttpGet getter = new HttpGet("https://cas.sysu.edu.cn/cas/");
		
		HttpResponse response = httpClient.execute(getter);
		System.out.println(response.getEntity());*/
		
		Document doc = Jsoup.connect("https://cas.sysu.edu.cn/cas/")
				.userAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.104 Safari/537.36 Core/1.53.1708.400 QQBrowser/9.5.9635.400")
				.cookie("JSESSIONID", "3680E7AE3C878F9BC331CFE729FA5CE9")
				.cookie("TGC", "eyJhbGciOiJIUzUxMiJ9.ZXlKaGJHY2lPaUprYVhJaUxDSmxibU1pT2lKQk1USTRRMEpETFVoVE1qVTJJbjAuLnRFZ2E3d3lfam1FVm5VSnEwT050RGcub3YtSmw2bHQxV1ZJTjA3SmRqT3RJNWNhblROdU5TOGJjOTNOS1kwNlJHM2dNREFObUt5S0ZCQ0J2YkcwWTNLLUs0bVZoUTlMbHk2ZktaTjZDWlJBV3B4dHBRRngtenRjbjUwLWQ3N2l3RzF3WWtTY0hENU9rYWNzZE1Va0cxUjg1VEdGTnktS1RmaWVLRm1hVk5zU3M5Z3ZqWkZiWkdfRjI4b0haQWF5MExXbHFBMXNRLWNJa21TMk50dncxZ1k0WmVLSFk3dFQ2TDM2cTEwX0xTb0k0TTdIUUUxNXB1UXZjbDFYeGJMbm9aeHotVTZucmNvbTFmWWVhU2E0RlRlSG5QUHZpNVhEXzlaT25OTXlxUXdPUzktRzAxQXRiS3dGT3R1NTBmVFBsM1ZqenczZkstdDVucFVqNHlTWElKcXVkYllqSmkyT1hUSHdBQnVSYjNwU2FBLng1Nkk2aXBEcm43WjhNYTFXWU9TdEE.UR0z8MmzVUGC9urVScTlPYAyau_eu5Rc9yo_IKCw-4Pt3SQCmX-Ahx5u9lBE71wueeOncw9-ny5OkSs9GXrIRA")
				.header("Upgrade-Insecure-Requests", "1")
				.header("Host", "cas.sysu.edu.cn")
//				.header("Referer", "https://cas.sysu.edu.cn/")
                .header("accept", "zh-CN,zh;q=0.8")
                .header("accept-encoding", "gzip, deflate, sdch, br")
                .header("accept-language", "en-US,en;q=0.8")
                .header("Connection", "keep-alive")
                .header("origin", "https://cas.sysu.edu.cn")
                .referrer("https://cas.sysu.edu.cn/")
				.validateTLSCertificates(false).get();
		System.out.println(doc.toString());
		int[] a = new int[]{1,2};
	}

}
 class Solution {
    public int[] twoSum(int[] nums, int target) {
    	int i=0;
    	int j=0;
        for(;i<nums.length;i++){
            if(nums[i]>target)continue;
            for(j=i+1;j<nums.length;j++){
                if(nums[j]>target)continue;
                if(nums[i]+nums[j]==target) {
                    return new int[]{i,j};
                }
            }
        }
        return new int[]{0,0};
    }
}