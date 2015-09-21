import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;



public class Login
{

	public CookieStore getCookies() throws Exception
	{
		
		
		
		
		
		
		
		
		DefaultHttpClient hcc = new DefaultHttpClient();
		HttpGet httpget = new HttpGet("https://baidu.com/");
		HttpResponse response =hcc.execute(httpget);
		CookieStore cs =hcc.getCookieStore();
		sop(cs);
		// 第二步，用cookie获特定的token，用于模拟登陆的post参数
		DefaultHttpClient hc = new DefaultHttpClient();
		hc.setCookieStore(cs);
		HttpGet httpGet2 = new HttpGet(
		"https://passport.baidu.com/v2/api/?getapi&tpl=tb&apiver=v3"+
		 "&tt=1442241648893&class=login&gid=9572CD5-0CC2-48A5-8DE6-F16CABA73A27&logintype=dialogLogin&callback=bd__cbs__urvo7k");
		HttpResponse res = hc.execute(httpGet2);
		cs=hc.getCookieStore();
		BufferedReader br = new BufferedReader(new InputStreamReader(res.getEntity().getContent(),"utf-8"));
		//读取返回的内容
		String line = br.readLine();
		
		String reg = "\"token\" : \"(.*?)\"";
		Pattern p =Pattern.compile(reg);
		Matcher m = p.matcher(line);
		m.find();
		line=m.group();
		String token=line.replaceAll(reg, "$1");
		sop(token);
		
		
		
		DefaultHttpClient client3 = new DefaultHttpClient();
		client3.setCookieStore(cs);
		HttpPost post = new HttpPost("https://passport.baidu.com/v2/api/?login");
		
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		
		parameters.add(new BasicNameValuePair("staticpage",
		"http://tieba.baidu.com/tb/static-common/html/pass/v3Jump.html"));
		parameters.add(new BasicNameValuePair("charset", "UTF-8"));
		parameters.add(new BasicNameValuePair("token", token));
		parameters.add(new BasicNameValuePair("tpl", "tb"));
		parameters.add(new BasicNameValuePair("subpro", ""));
		parameters.add(new BasicNameValuePair("apiver", "v3"));
		parameters.add(new BasicNameValuePair("tt", "1442210667931"));
		parameters.add(new BasicNameValuePair("codestring", ""));
		parameters.add(new BasicNameValuePair("safeflg", "0"));
		parameters.add(new BasicNameValuePair("u", "http://tieba.baidu.com/?_t=1442210627998#"));
		parameters.add(new BasicNameValuePair("isPhone", "false"));
		
		parameters.add(new BasicNameValuePair("detect", "1"));
		parameters.add(new BasicNameValuePair("gid", "8AFC7B2-6F49-46A0-AD0B-9CB189ED7227"));
		
		parameters.add(new BasicNameValuePair("quick_user", "0"));
		
		
		parameters.add(new BasicNameValuePair("logintype", "dialogLogin"));
		
		parameters.add(new BasicNameValuePair("logLoginType", "pc_loginDialog"));
		
		parameters.add(new BasicNameValuePair("idc", "true"));
		
		parameters.add(new BasicNameValuePair("splogin", ""));
		parameters.add(new BasicNameValuePair("loginmerge", "true"));
		
		parameters.add(new BasicNameValuePair("splogin", "rate"));
		
		parameters.add(new BasicNameValuePair("username", "13104625915"));
		parameters.add(new BasicNameValuePair("password", "zx199551"));
		
		parameters.add(new BasicNameValuePair("verifycode", ""));
		
		parameters.add(new BasicNameValuePair("mem_pass", "on"));
		
		parameters.add(new BasicNameValuePair("ppui_logintime", "1245"));
		
		parameters.add(new BasicNameValuePair("callback", "parent.bd__pcbs__tzot6j"));
	
		HttpEntity postBodyEnt = new UrlEncodedFormEntity(parameters,"utf-8");
		post.setEntity(postBodyEnt);

		cs=client3.getCookieStore();
	
		HttpResponse re = client3.execute(post);
		
		BufferedReader br1 = new BufferedReader(new InputStreamReader(re.getEntity().getContent(),"utf-8"));
		//读取返回的内容
		line=null;
		while((line=br1.readLine())!=null)
		{
			sop(line);
		}
		
		
		sop(cs);
		return cs;
		

		
		
		
		
	}
	public static void sop(Object o)
	{
		System.out.println(o);
	}
	
}
