package cn.bjjoy.bms.socket_multi4;

import java.util.Scanner;

public class AScanner {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in) ; // 从键盘接收数据
        System.out.print("输入数据：") ;
        String str =  scan.next();  // 接收数据
        do{
            System.out.println("输入的数据为：" + str + "\n") ;
        } while((str = scan.next()) != null);
		
	}
	
}
