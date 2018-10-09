package cn.bjjoy.bms.socket_multi4;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

public class Client {

    InputStream is;
    
	
	private static final int PORT = 9991 ;
	
	static private Random r = new Random();

    public static void main(String[] args) {
    	Scanner scanner = null ;
        try {
            //1.创建客户端Socket，指定服务器地址和端口号
            Socket socket = new Socket("127.0.0.1", PORT);
            OutputStream os = socket.getOutputStream();
            DataOutputStream dos = new DataOutputStream(os) ;
            scanner = new Scanner(System.in) ;
            String inputinfo = scanner.next();
			do{
				PrintWriter pw = new PrintWriter(dos); //将对应客户端的聊天信息取出作为私聊内容发送出去
				if(pw != null) {
					pw.println(inputinfo);	
				}
				
				InputStream is = socket.getInputStream();
	            BufferedReader br = new BufferedReader(new InputStreamReader(is));
	            String info = null;
	            while ((info = br.readLine()) != null) {
	                System.out.println("我是客户端，服务器端返回的信息是：" + info);
	            }
            }while((inputinfo = scanner.next()) != null);
        } catch (IOException ex) {
        	ex.printStackTrace();
        }
    }

}
