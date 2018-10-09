package cn.bjjoy.bms.socket_multi3;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ServerThread extends Thread {

	Socket socket;
    ArrayList<Socket> list;
    InputStream is;
    OutputStream os;
    String targetIP;
    
    public ServerThread(Socket socket, ArrayList<Socket> list , String targetIP) {
        this.socket = socket;
        this.list = list;
        this.targetIP = targetIP;
    }
    
    public ServerThread(Socket socket, ArrayList<Socket> list ) {
        this.socket = socket;
        this.list = list;
    }
    
    
    @Override
    public void run() {
        try {
            while(true) {
                //得到一个客户端的消息
                is = socket.getInputStream();
                byte[] b = new byte[1024];
                int len = is.read(b);
                String str = new String(b,0,len);
                System.out.println(str);
                for(Socket socket : list) {
                    //避免socket消息自己发给自己
                    if(this.socket != socket ) {
                    	if(targetIP != null && !"".equals(targetIP) && socket.getInetAddress().getHostAddress().equals( targetIP )){
                    		os = socket.getOutputStream();
                    		//输出流 写到socket中去
                    		os.write(str.getBytes());
                    	}else{
                    		os = socket.getOutputStream();
                    		//输出流 写到socket中去
                    		os.write(str.getBytes());
                    	}
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
