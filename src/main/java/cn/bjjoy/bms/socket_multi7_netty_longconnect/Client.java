package cn.bjjoy.bms.socket_multi7_netty_longconnect;

public interface Client {

	//启动心跳检测
    public void startHeartBeat() throws Exception;

    //发送请求
    public void sendRequest(Object msg) throws Exception;

    public String getDomain();

    public boolean isConnected();

	
}
