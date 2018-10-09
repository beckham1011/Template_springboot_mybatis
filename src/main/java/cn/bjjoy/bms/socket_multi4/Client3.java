package cn.bjjoy.bms.socket_multi4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class Client3 {

    InputStream is;
    OutputStream os;
	
	private static final int PORT = 9991 ;
	
    public static void main(String[] args) {
        try {
            //1.创建客户端Socket，指定服务器地址和端口号
            Socket socket = new Socket("127.0.0.1", PORT);
            while(true){
            	InputStream is = socket.getInputStream();
	            BufferedReader br = new BufferedReader(new InputStreamReader(is));
	            String info = null;
	            while ((info = br.readLine()) != null) {
	                System.out.println("我是客户端，服务器端返回的信息是：" + info);
	            }
            }
        } catch (IOException ex) {
        	ex.printStackTrace();
        }
    }

}
