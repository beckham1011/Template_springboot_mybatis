package cn.bjjoy.bms.unsafe;

import java.lang.reflect.Field;

import sun.misc.Unsafe;

public class TestUnsafe {

	public static void main(String[] args) throws Exception {
		// 通过反射实例化Unsafe
		Field f = Unsafe.class.getDeclaredField("theUnsafe");
		f.setAccessible(true);
		
		Unsafe unsafe = (Unsafe) f.get(null);
		// 实例化Player
		Player player = (Player) unsafe.allocateInstance(Player.class);
		player.setName("li lei");
		System.out.println(player.getName());
	}

}
  
class Player {
	private String name;

	private Player() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}