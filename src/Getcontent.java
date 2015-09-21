import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Getcontent 
{
String url=null;
String filename = null;
String author=null;
String outline = null;
Getcontent(String url,String location,String author,String outline,int c)
{
	this.url=url;
	this.filename=location+author+"-"+c+".txt";
	this.author=author;
	this.outline=outline;
}
public static void sop(Object o)
{
 System.out.println(o);  
}
public void get() throws Exception
{
	PrintWriter fbw = new PrintWriter(new FileWriter(filename));
	fbw.println("作者为 "+author+" 主题为: "+outline);
	URLConnection conn = new URL(url).openConnection();
	BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
	String line = null;
	String reg = "class=\"d_post_content j_d_post_content \">            (.*?)</div><br>            </cc>        <br>        <div class=\"user-hide-post-down\"";
	String reg2 ="class=\"p_author_name j_user_card\" href=\"(.*?) target=\"_blank\">(.*?)</a>";
               
	Pattern p =Pattern.compile(reg);
	Pattern p2 =Pattern.compile(reg2);
	int count = 1;
	while((line = in.readLine())!=null)
	{	
		Matcher m2 = p2.matcher(line);
		if(m2.find())
		{
			fbw.print(count+"楼: ");
			count++;
			line = m2.group();
			line=line.replaceAll(reg2, "$2");
			fbw.print(line+" 回复作者: ");
			String line1="";
			while((line1 = in.readLine())!=null)
			{
				Matcher m = p.matcher(line1);
				if(m.find())
				{
					line1 = m.group();
					line1=line1.replaceAll(reg, "$1");
					fbw.println(line1);
					fbw.println("");
					fbw.flush();
					break;
				}
			}
		}
		
		
	}
	fbw.close();
	
}
}
