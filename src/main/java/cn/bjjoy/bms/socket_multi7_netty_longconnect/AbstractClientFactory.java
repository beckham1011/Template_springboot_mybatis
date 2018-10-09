package cn.bjjoy.bms.socket_multi7_netty_longconnect;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;


public abstract class AbstractClientFactory implements ClientFactory {

	// 长连接缓存，一个域名对应一个长连接
    private final Cache<RemotingUrl, Client> cacherClients = CacheBuilder.newBuilder()//
            .maximumSize(65535) // 单机长连接上限，超过上限采用LRU淘汰
            .expireAfterAccess(27, TimeUnit.MINUTES)// 连接长时间没有读写则删除
            .removalListener(new RemovalListener<RemotingUrl, Client>() {
                @Override
                public void onRemoval(RemovalNotification<RemotingUrl, Client> notification) {
                    // 关闭连接
                }
            })//
            .build();

    protected abstract Client createClient(RemotingUrl domain) throws Exception;

    @Override
    public Client get(RemotingUrl url) throws Exception {
        Client client = cacherClients.get(url, new Callable<Client>() {

            @Override
            public Client call() throws Exception {
                Client client = createClient(url);
                if (null != client) {
                    client.startHeartBeat();
                }
                return client;
            }

        });
        return client;
    }

    @Override
    public List<Client> getAllClients() throws Exception {
        List<Client> result = new ArrayList<Client>((int) cacherClients.size());
        result.addAll(cacherClients.asMap().values());
        return result;
    }

    @Override
    public void remove(RemotingUrl url) throws Exception {
        cacherClients.invalidate(url);
    }


	
}
