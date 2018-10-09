package cn.bjjoy.bms.socket_multi3;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class TheadReader extends Thread {

	Socket socket;
    
	public TheadReader(Socket socket) {
        this.socket = socket;
    }
    
    @Override
    public void run() {
        try {
            while(true) {
                //利用输出流得到socket中的消息.
                InputStream is = socket.getInputStream();
                byte[] b = new byte[1024];
                //读取到字节b中，并获取字节的个数。
                int len = is.read(b);
                //转化为字符
                String str = new String(b,0,len);
                System.out.println(str);   
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        super.run();
    }

}
