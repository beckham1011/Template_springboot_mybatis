
public class Test {
	public static void main(String[] args) {
		SubsubClass ssc = new SubsubClass(3, 6, 9);
		ssc.show();
	}
}

class SuperClass {
	int a;
	int b;

	public SuperClass(int x, int y) {
		this.a = x;
		this.b = y;
	}
}

class SubClass extends SuperClass {
	int b;
	int c;

	public SubClass(int xx, int yy, int zz) {
		super(xx, yy);
		this.b = b + zz;
		this.c = a + b;
	}
}

class SubsubClass extends SubClass {
	int a;

	public SubsubClass(int xx, int yy, int zz) {
		super(xx, yy, zz);
		this.a = a + b + c;
	}

	public void show() {
		System.out.println("a=" + a + ";b=" + b + ";c=" + c);
	}
}
