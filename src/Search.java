import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Iterator;


public class Search {
	public void search() throws IOException
	{
	 BufferedReader fr = new BufferedReader(new InputStreamReader(System.in));
	 sop("�����ļ�Դ");
	 String l = fr.readLine();
	 //String l = "D:\\�½��ļ���\\�½��ļ��� ";
	 String ll [] =l.split("/");
		String location = ll[0];
		for(int x=1;x<ll.length;x++)
		{
			location = location+"//"+ll[x];
		}
		location = location+"//";
	sop("������۲�����ַ");
	 //l = fr.readLine();
	l="D:\\�½��ļ���\\�½��ļ��� (3)";
	 ll  =l.split("/");
	String location1 = ll[0];
		for(int x=1;x<ll.length;x++)
		{
			location1 = location1+"//"+ll[x];
		}
		location1 = location1+"//";
		File f=new File(location);
		File[] fl=f.listFiles();
		TreeSet<Count> theme = new TreeSet<Count>();
		TreeSet<Count> floor = new TreeSet<Count>();
		TreeSet<Count> count = new TreeSet<Count>();
		TreeSet<Count> comm = new TreeSet<Count>();
		//������������count(String name,int count)
		//ռ¥������
		//ˮ��ָ����
		//��������map<String--String,Integer> String��String���ֵ��������뺯�����ء�
		//������TreeSet�У�
		try
		{
			
		
		for(int x=0;x<fl.length;x++)
		{
			sop(x+1+":running");
			BufferedReader br = new BufferedReader(new FileReader(fl[x].toString()));
			String [] ss = br.readLine().split(" ");
			String author = ss[1];
			
			
			
			//ͳ������������
			sop(author);
			if(theme.contains(new Count(author,1)))
			{
				
				Iterator<Count> it =theme.iterator();
				while(it.hasNext())
				{
					Count persion = it.next();
					if(persion.equals(new Count(author,1)));
					{
						String name_p = persion.getName();
						int count_p = persion.getCount();
						theme.remove(persion);
						theme.add(new Count(name_p,count_p+1));
						break;
					}
				}
			}
			else
			{
				
				theme.add(new Count(author,1));
			}
			
			
			//���������
			/*
			if(count.contains(new Count(author,1)))
			{
				Iterator<Count> it =count.iterator();
				while(it.hasNext())
				{
					Count persion = it.next();
					if(persion.equals(new Count(author,1)));
					{
						String name_p = persion.getName();
						int count_p = persion.getCount();
						count.remove(persion);
						count.add(new Count(name_p,count_p+5));
						break;
					}
				}
			}
			else
			{
				count.add(new Count(author,5));
			}
			*/
			
			
			while(true)
			{
				String lines = br.readLine();
				if(lines==null)
				{
					break;
				}
				String[] line=lines.split(" ");
				
				String writer = line[1];
				
			
				
				//¥����һ��
				/*
				if(count.contains(new Count(writer,1)))
				{
					Iterator<Count> it =count.iterator();
					while(it.hasNext())
					{
						Count persion = it.next();
						if(persion.equals(new Count(writer,1)));
						{
							String name_p = persion.getName();
							int count_p = persion.getCount();
							count.remove(persion);
							count.add(new Count(name_p,count_p+1));
							break;
							
						}
					}
				}
				else
				{
					count.add(new Count(writer,1));
				}
				
				*/
				
				//ռ¥������һ
				if(floor.contains(new Count(writer,1)))
				{
					Iterator<Count> it =floor.iterator();
					while(it.hasNext())
					{
						Count persion = it.next();
						if(persion.equals(new Count(writer,1)));
						{
							String name_p = persion.getName();
							int count_p = persion.getCount();
							floor.remove(persion);
							floor.add(new Count(name_p,count_p+1));
							break;
						}
					}
				}
				else
				{
					floor.add(new Count(writer,1));
				}
				
				
				
				
				String name = getName(author,writer);
				
				//����֮�以����һ
				if(comm.contains(new Count(name,1)))
				{
					Iterator<Count> it =comm.iterator();
					while(it.hasNext())
					{
						Count persion = it.next();
						if(persion.equals(new Count(name,1)));
						{
							String name_p = persion.getName();
							int count_p = persion.getCount();
							comm.remove(persion);
							comm.add(new Count(name_p,count_p+1));
							break;
						}
					}
				}
				else
				{
					comm.add(new Count(name,1));
				}
				
				
				lines =br.readLine();
				if(lines==null)
					break;
			}
		}
		}
		catch(Exception e)
		{
			
		}
		PrintWriter pw_theme = new PrintWriter(new FileWriter(location1+"theme.txt"));
		PrintWriter pw_count = new PrintWriter(new FileWriter(location1+"count.txt"));
		PrintWriter pw_comm = new PrintWriter(new FileWriter(location1+"comm.txt"));
		PrintWriter pw_floor = new PrintWriter(new FileWriter(location1+"floor.txt"));
		Iterator<Count> it_theme=theme.iterator();
		Iterator<Count> it_count=count.iterator();
		Iterator<Count> it_comm=comm.iterator();
		Iterator<Count> it_floor=floor.iterator();
		while(it_theme.hasNext())
		{
			Count c = it_theme.next();
			pw_theme.println(c.getName()+"\t������"+c.getCount()+"��������");
			pw_theme.flush();
		}
		while(it_comm.hasNext())
		{
			Count c = it_comm.next();
			pw_comm.println(c.getName()+c.getCount()+"��");
			pw_comm.flush();
		}
		
		while(it_count.hasNext())
		{
			Count c = it_count.next();
			pw_count.println(c.getName()+"\t:��ˮ��ָ��Ϊ"+c.getCount());
			pw_count.flush();
		}
		
		while(it_floor.hasNext())
		{
			Count c = it_floor.next();
			pw_floor.println(c.getName()+"\t:ռ¥����Ϊ"+c.getCount());
			pw_floor.flush();
		}
	}
		public static void sop(Object o)
		 {
		  System.out.println(o);  
		 }
		public static String getName(String s1,String s2)
		{
			if(s1.compareTo(s2)>0)
				return s1+"\t��"+s2+"\t������";
			else 
				return s2+"\t��"+s1+"\t������";
		}
}
class Count implements Comparable
{
	private String name;
	private int count;
	Count(String name,int count)
	{
		this.name=name;
		this.count=count;
	}
	public int compareTo(Object o) 
	{
		
		Count c= (Count)o;
		if(this.count<c.getCount())
			return 1;
		if(this.getName().equals(c.getName()))
			return 0;
		else
			return -1;
	}
	public int getCount()
	{
		return count;
	}
	public String getName()
	{
		return name;
	}
	
	 public boolean equals(Object o)
	{
		Count c= (Count)o;
		if(this.getName().equals(c.getName()))
			return true;
			else
		return false;
	}
	 
	 public int hashCode()
	 {
		 System.out.println("hashCode");
		 return this.name.hashCode();
	 }
}