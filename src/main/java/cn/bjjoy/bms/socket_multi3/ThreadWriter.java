package cn.bjjoy.bms.socket_multi3;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ThreadWriter extends Thread {

	private Socket socket;
	private Scanner sc;

	public ThreadWriter(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            sc = new Scanner(System.in);
            int i = 0;
            while(true) {
                System.out.println("第的" + i++ + "条消息:");
                String str = sc.next();
                OutputStream os = socket.getOutputStream();
                //将输入的字符写入到利用输出流写到socket中保存
                os.write(str.getBytes());
            } 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
