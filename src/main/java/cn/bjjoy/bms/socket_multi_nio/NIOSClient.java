package cn.bjjoy.bms.socket_multi_nio;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Scanner;
import java.util.Set;

import cn.bjjoy.bms.socket.ByteUtil;
 
public class NIOSClient {

    public static void main(String[] args) throws IOException {
        new NIOSClient(8082);
    }
	
	/*发送数据缓冲区*/
    private static ByteBuffer sBuffer = ByteBuffer.allocate(1024);
    
    /*接受数据缓冲区*/
    private static ByteBuffer rBuffer = ByteBuffer.allocate(1024);
    
    private static final String addressCode = "hsg666" ;
    
    private static final String DATA = "01 04 2C 80 00 00 00 80 00 00 00 00 00 00 00 45 34 C0 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 05 00 01 00 00 00 00 00 01 00 00 D5 B1";
    
    /*服务器端地址*/
    private InetSocketAddress SERVER;
    
    private static Selector selector;
    
    private static SocketChannel client;
    
    private static String receiveText;
    
    private static String sendText;
    
    private static int count = 0;
    
    public NIOSClient(int port) {
        SERVER = new InetSocketAddress("localhost", port);
        init();
    }
    
    public void init() {
        try {
            /* 
              * 客户端向服务器端发起建立连接请求 
              */
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            selector = Selector.open();
            socketChannel.register(selector, SelectionKey.OP_CONNECT);
            socketChannel.connect(SERVER);
            /* 
             * 轮询监听客户端上注册事件的发生 
             */
            while (true) {
                selector.select();
                Set<SelectionKey> keySet = selector.selectedKeys();
                for (final SelectionKey key : keySet) {
                    handle(key);
                };
                keySet.clear();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handle(SelectionKey selectionKey) throws IOException {
        if (selectionKey.isConnectable()) {
            /* 
             * 连接建立事件，已成功连接至服务器 
             */
            client = (SocketChannel)selectionKey.channel();
            if (client.isConnectionPending()) {
                client.finishConnect();
                System.out.println("connect success !");
                sBuffer.clear();
                sBuffer.put(addressCode.getBytes());
                sBuffer.flip();
                client.write(sBuffer);//发送信息至服务器  
                /* 原文来自站长网
                 * 启动线程一直监听客户端输入，有信息输入则发往服务器端 
                 * 因为输入流是阻塞的，所以单独线程监听 
                 */
                new Thread() {
                    @Override
                    public void run() {
                        while (true) {
                            sBuffer.clear();
                            Scanner cin = new Scanner(System.in);
                            sendText = cin.nextLine();
                            System.out.println(sendText);
                            /* 
                             * 未注册WRITE事件，因为大部分时间channel都是可以写的 
                             */
                            sendMsg(sendText);
                        }
                    };
                }.start();
            }
            //注册读事件  
            client.register(selector, SelectionKey.OP_READ);
        }
        else if (selectionKey.isReadable()) {
            /* 
             * 读事件触发 
             * 有从服务器端发送过来的信息，读取输出到屏幕上后，继续注册读事件 
             * 监听服务器端发送信息 
             */
            client = (SocketChannel)selectionKey.channel();
            rBuffer.clear();
            count = client.read(rBuffer);
            if (count > 0) {
                receiveText = new String(rBuffer.array(), 0, count);
                String txt = bytesTohex(rBuffer.array());
                test(rBuffer.array());
                System.out.println("txt:" + txt);
                System.out.println(receiveText);
                if(txt.indexOf("01041010001674")> -1 ) {
                	sendMsg(DATA);
                }
                client = (SocketChannel)selectionKey.channel();
                client.register(selector, SelectionKey.OP_READ);
            }
        }
    }
    
    public static void sendMsg(String msg) {
    	try {
			sBuffer.put(msg.getBytes("utf-8"));
			sBuffer.flip();
			client.write(sBuffer);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    
    public void test(byte[] dataBytes){
		String receiveTextCode1  = ByteUtil.toHexString1(dataBytes);
		String receiveTextCode2 = ByteUtil.binaryToHexString(dataBytes);
		String receiveTextCode3 = ByteUtil.bytesToHex(dataBytes);
		String receiveTextCode4 = ByteUtil.bytesToHex1(dataBytes);
		String receiveTextCode5 = ByteUtil.byteArrayToHexStr(dataBytes);
		String receiveTextCode7 = ByteUtil.bytesToHexFun1(dataBytes);
		String receiveTextCode8 = ByteUtil.bytesToHexFun3(dataBytes);
		
		System.out.println("=================================receiveTextCode1:" + receiveTextCode1);
		System.out.println("+++++++++++++++++++++++++++++++++receiveTextCode2:" + receiveTextCode2);
		System.out.println("+++++++++++++++++++++++++++++++++receiveTextCode3:" + receiveTextCode3);
		System.out.println("+++++++++++++++++++++++++++++++++receiveTextCode4:" + receiveTextCode4);
		System.out.println("+++++++++++++++++++++++++++++++++receiveTextCode5:" + receiveTextCode5);
		System.out.println("+++++++++++++++++++++++++++++++++receiveTextCode7:" + receiveTextCode7);
		System.out.println("+++++++++++++++++++++++++++++++++receiveTextCode8:" + receiveTextCode8);
		
	}
    
	public static String bytesTohex(byte[] bytes) {
        StringBuilder hex = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            byte b = bytes[i];
            boolean flag = false;
            if (b < 0) flag = true;
            int absB = Math.abs(b);
            if (flag) absB = absB | 0x80;
            String tmp = Integer.toHexString(absB & 0xFF);
            if (tmp.length() == 1) { //转化的十六进制不足两位，需要补0
                hex.append("0");
            }
            hex.append(tmp.toLowerCase());
        }
        return hex.toString();
    }
	
}
