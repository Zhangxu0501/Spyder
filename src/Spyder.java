import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Spyder
{
	 public static void sop(Object o)
	 {
	  System.out.println(o);  
	 }
	 
	
	private PrintWriter pw= null;
	private String url =null;
	
	private String reg = "<a href=\"/p/(.*?)\" title=\"(.*?)\" target=\"_blank\" class=\"j_th_tit \">(.*?)</a></div>";
	private String reg1 = "title=\"\u4e3b\u9898\u4f5c\u8005: (.*?)\"";
	
	private Pattern p =Pattern.compile(reg);
	private Pattern p1 =Pattern.compile(reg1);
	
	 
	Spyder(String url,PrintWriter pw)
	{
		this.url=url;
		this.pw=pw;
	}
	public void getpage() throws Exception
	{
		
		URLConnection conn = new URL(url).openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
		
		String line = null;
		 while((line = in.readLine())!=null)
		 {
			 Matcher m = p.matcher(line);
			 if(m.find())
			 {
				 StringBuilder sb=new StringBuilder();
				 sb.append("地址为： http://tieba.baidu.com/p/");

				
				 String herf=m.group();
				
				 sb.append(herf.replaceAll(reg, "$1"));
			
				 
				 sb.append(" 主题为: "+herf.replaceAll(reg, "$2"));
				
				 String line1 = null;
				 
				 while((line1 = in.readLine())!=null)
				 {
					 Matcher m1 = p1.matcher(line1);
					 if(m1.find())
					 {
						 String author=m1.group();
						 sb.append(" 作者为: "+author.replaceAll(reg1, "$1"));
						 pw.println(sb);
						 pw.flush();
						 break;
					 }
				 }
			 }
		 }
		 in.close();
	
	}
		
}