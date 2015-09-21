import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.*;

class Test {
 public static void main(String[] args) throws Exception 
 {
	
	  BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	sop("请输入贴吧名称");
	 String name=br.readLine();
	 sop("请输入目标文件夹位置");
	 
	String l = new BufferedReader(new InputStreamReader(System.in)).readLine();
	String ll [] =l.split("/");
	String location = ll[0];
	
	for(int x=1;x<ll.length;x++)
	{
		location = location+"//"+ll[x];
	}
	location = location+"//";
	
	Thread.sleep(10000);
	 String urlname = "http://tieba.baidu.com/f?kw="+name+"&ie=utf-8&pn=";
	 PrintWriter pw = new PrintWriter(new FileWriter(location+"File.txt"));
	 
	int number = 0 ;
	String reg = "\u5171\u6709\u4e3b\u9898\u6570<span class=\"red_text\">(.*?)</span>\u4e2a，\u8d34\u5b50\u6570";
	Pattern p =Pattern.compile(reg);
	URLConnection conn = new URL(urlname+0).openConnection();
	BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
	String line = null;
	while((line = in.readLine())!=null)
	{
		Matcher m = p.matcher(line);
		if(m.find())
		{
			line = m.group();
			line=line.replaceAll(reg, "$1");
			number = Integer.parseInt(line);
			number = number/50/50*50*50;
		}
	}
	pw.println(name+"吧，共有主题帖数"+number+"个");
	pw.flush();
	 number=number/50;
	 for(int x=0;x<number;x+=50)
	 {
		 String url = urlname+x;
		 Spyder sp = new Spyder(url,pw);
		 sop("第"+(x/50+1)+"页帖子地址爬取完毕");
		 sp.getpage();
	 }
	 int count = 1;
	 BufferedReader fbr = new BufferedReader(new FileReader(location+"File.txt"));
	 String read="";
	 read=fbr.readLine();
	 while((read=fbr.readLine())!=null)
	 {
		 String lines [] = read.split(" ");
		
		 String url = lines[1];
		 String outline = lines[3];
		 String author = lines[5];
		 sop(url);
		 String a1[]=author.split("/");
		 String a2[]=outline.split("/");
		 author="";
		 outline="";
		 for(String s:a1)
			 author+=s;
		 for(String s:a2)
			 outline+=s;
		 try
		 {
			 new Getcontent(url,location,author,outline,count++).get();
		 }
		
		 catch(Exception e)
		 {
			 sop(url+"文件出错，不记录");
		 }
	 }
	 
	 
	 //Search s = new Search();
	 
		// s.search();	
	 
	
	 }
 
 

public static void sop(Object o)
 {
  System.out.println(o);  
 }
}


