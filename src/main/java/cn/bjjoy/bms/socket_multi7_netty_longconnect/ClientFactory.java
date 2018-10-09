package cn.bjjoy.bms.socket_multi7_netty_longconnect;

import java.util.List;

public interface ClientFactory {

	// ------------ 查询和新增 ------------
    Client get(final RemotingUrl url) throws Exception;

    // ------------ 查询 ------------
    List<Client> getAllClients() throws Exception;

    // ------------ 删除 ------------
    void remove(final RemotingUrl url) throws Exception;

	
}
