/**
 * download imgs of https://www.kaixin.com/
 *
 * Function：down img demo of Kaixin
 *
 *   ver     date           author
 * ──────────────────────────────────
 *   1.0     2018/03/20     bluetata
 *
 * Copyright (c) 2017, [https://github.com/bluetata] All Rights Reserved.
 */
package com.datacrawler.service.model.kaixin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.bluetata.datacrawler.common.util.jdbc.JdbcAdapter;
import com.bluetata.datacrawler.common.util.jdbc.MySQLBConnectionPool;

public class DownPicForKaixin {
private static 	String outPath="E://downImgForKainxin//1";
private static	String outPathFile ="";
private static	int fileName =1;


//static DataSource ds = MySQLBConnectionPool.dataSource;
//static JdbcAdapter adapter = new JdbcAdapter();
//static {
//	adapter.init(ds);
//}


	public static void main(String[] args) {
	  for (int i = 0; i <  19575575    ; i++) {
		  sqlGetUrl();
		
	}
		
		
		
	}
	 public static void sqlGetUrl() {
		 
		 try {
			Thread.sleep(1*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 String sql1 = "select KAIXIN_ID , TPDZ from  pl_kaixin_customer_info where ACTIVE_FLAG <>2 order by KAIXIN_ID LIMIT 1";
		 // String sql1 = "select RENREN_ID , TPDZ from  pl_renren_customer_info ";
		 
		 
		List  list1 = getSingleInfo(sql1);
		String id = "";
		String url ="";
		
		
		 if(list1.size() >0 ){
			 id = list1.get(0).toString();
			 url = list1.get(1).toString();
			 if(url.length()>0){
			 mkDestFile(outPath,id,url);		
				
			 }
				
			 System.out.println("id   "+id + "url    "+url);
			 
		 }else{
			 
			  System.out.println("list is  null ");
			 
		 }
		 
		 String	sql2 = "UPDATE pl_kaixin_customer_info SET ACTIVE_FLAG =2"
				+ ",UPDATE_DATE='" + getdate()
				+ "' WHERE KAIXIN_ID='" + id + "'";
//		         adapter.dbUpdate(sql2);
		         System.out.println("********TASK 运行ID："+id);	
     
  
	 
	 }
	 public static String getdate() {
			java.text.SimpleDateFormat formatter_f = new java.text.SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			java.util.Date currentTime_f = new java.util.Date(); // 得到当前系统时间
			String new_date_f = formatter_f.format(currentTime_f); // 将日期时间格式化
			return new_date_f;
		}
	 
        
     
	 
	 
	
	 
	 public static void mkDestFile( String destFileName ,String id,String url) {
            
	        //判断目标文件是否存在
		    if(id.length()<4) {
		    	destFileName = "E:\\downImgForKainxin\\0000";
		    } else {
		    	destFileName = "E:\\downImgForKainxin\\" + id.substring(0,4);
		    }
		    
	        File destFile = new File(destFileName);
	        if(destFile.exists() ) {
	        	
	           if(checkFileNum(destFileName)){
	        	   
	        	   fileName++;
	        	   
//	        	   File destFileTemp = new File(destFileName+fileName);
	        	   File destFileTemp = new File(destFileName);
	        	   boolean  creadok  =  destFileTemp.mkdirs();
	                if (creadok) {
	                	outPathFile = destFileName+fileName;
	                   System.out.println( " ok:创建文件夹成功！ " );
	               } else {
	                   System.out.println( " err:创建文件夹失败！ " );                    
	               } 
	                
	                
	           }else{
	        	outPathFile = destFileName;
	           }
                System.out.println("目标文件已存在!");

	        } else {
	        	
                    fileName++;
	        	   
//	        	   File destFileTemp = new File(destFileName+fileName);
                    File destFileTemp = new File(destFileName);
                    
	        	   boolean  creadok  =  destFileTemp.mkdirs();
	                if (creadok) {
	                	outPathFile = destFileName ;//+fileName;
	                	
	                   System.out.println( " ok:创建文件夹成功！ " );
	               } else {
	                   System.out.println( " err:创建文件夹失败！ " );
	               } 
                
	            if (!destFile.getParentFile().exists()) {
	                //如果目标文件所在的目录不存在，则创建目录
	                System.out.println("目标文件所在目录不存在，准备创建它");
	                
	                if (!destFile.getParentFile().mkdirs()) {
	                	
	                    System.out.println("复制文件失败，创建目标文件所在的目录失败!");
	                
	                }
	            }
	        }
	        
	        try {
				download( outPath,id,url);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	      
	      
	    }
	 
	 public static void download(String urlString,String id,String urlt) throws Exception {   
		 
		    // 构造URL   
		    URL url = new URL(urlt);   
		    // 打开连接   
		    URLConnection con = url.openConnection();   
		    // 输入流   
		    InputStream is = con.getInputStream();   
		  
		    // 1K的数据缓冲   
		    byte[] bs = new byte[1024];   
		    // 读取到的数据长度   
		    int len;   
		    // 输出的文件流   
		    OutputStream os = new FileOutputStream(outPathFile+"/"+id+".jpg");   
		    // 开始读取   
		    while ((len = is.read(bs)) != -1) {   
		      os.write(bs, 0, len);   
		    }   
		    // 完毕，关闭所有链接   
		    os.close();   
		    is.close();   
		  }   
	 
	 
	public static boolean checkFileNum(String filePath){ 
	  File file=new File(filePath);
	  boolean flag;
      String files[];
      files=file.list();
      int num = files.length;
      if(num >= 10000){
      System.out.println(filePath+"下文件夹个数："+num);
	    flag  = true;
      }else{
    	  
    	 flag = false;
      }
      return flag;
	}
	
//	public static List getSingleInfo(String sql) {
//		List list = new ArrayList();
//
//		try {
//			List list001 = adapter.dbSelect(sql);
//			if (list001.size() == 0)
//				return null;
//			for (int k = 0; k < 1; k++) {
//				list = (List) list001.get(k);
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//
//		}
//		return list;
//	}
}
