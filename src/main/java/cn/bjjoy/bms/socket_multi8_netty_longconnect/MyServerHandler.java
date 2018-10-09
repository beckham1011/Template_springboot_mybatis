package cn.bjjoy.bms.socket_multi8_netty_longconnect;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.net.InetSocketAddress;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.bjjoy.bms.setting.constants.Constants;
import cn.bjjoy.bms.socket.ByteUtil;
import cn.bjjoy.bms.util.IPUtil;

public class MyServerHandler extends SimpleChannelInboundHandler<String> {
	
	Logger logger = LoggerFactory.getLogger(MyServerHandler.class) ;
	
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println(ctx.channel().remoteAddress() + ", " + msg);
        handlerMSG(ctx, msg) ;
        ctx.channel().writeAndFlush("from server: " + UUID.randomUUID());
    }

    /**
     * 对于异常的处理
     * @param ctx
     * @param cause
     * @throws Exception
     */
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    public void handlerMSG(ChannelHandlerContext ctx, String receiveText){
    	try {
    		if(Constants.All_MSG.equalsIgnoreCase(receiveText)){
				System.out.println(receiveText);
				//发送给所有客户端，要数据
				ctx.channel().writeAndFlush(Constants.MSG_8084);
//					dispatch2(Constants.MSG_8084);
				InetSocketAddress insocket = (InetSocketAddress) ctx.channel().remoteAddress();
	            String clientIP = insocket.getAddress().getHostAddress();
	            System.out.println("clientIP:" + clientIP);

//            	System.out.println(msgs.size());
//            	client = (SocketChannel)selectionKey.channel();
				//client.register(selector, SelectionKey.OP_READ);
			} else if(IPUtil.isIPv4(receiveText)){
				logger.info("====================  logger socket client receiveTextCode : " + receiveText);
			} else {
				String receiveTextCode = ByteUtil.binaryToHexString(receiveText.getBytes());
				logger.info("====================  logger socket client receiveTextCode : " + receiveTextCode);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    
}
