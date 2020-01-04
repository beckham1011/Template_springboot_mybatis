package cn.bjjoy.bms;

import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

public class TestAnnotationContext {
    @Override
    public String toString() {
        return super.toString();
    }

    public static void main(String[] args){
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.getBean("");
    }

}
