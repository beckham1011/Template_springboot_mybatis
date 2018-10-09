package cn.bjjoy.bms.socket_multi8_netty_longconnect;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.time.LocalDateTime;

public class MyClientHandler extends SimpleChannelInboundHandler<String> {

	@Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println(ctx.channel().remoteAddress());
        System.out.println("client output: " + msg);

        ctx.writeAndFlush("from client: " + LocalDateTime.now());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    /**
     * channel处于活动状态后的回调（链接建立好了过后）
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.channel().writeAndFlush("来与客户端的问候！");
    }

	
}
