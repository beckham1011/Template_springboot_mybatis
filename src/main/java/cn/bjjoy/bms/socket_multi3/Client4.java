package cn.bjjoy.bms.socket_multi3;

import java.net.Socket;

public class Client4 {

	public static void main(String[] args) throws Exception {
        Socket socket = new Socket("127.0.0.1",8888);
        new ThreadWriter(socket).start();
        new TheadReader(socket).start();
    }

}
