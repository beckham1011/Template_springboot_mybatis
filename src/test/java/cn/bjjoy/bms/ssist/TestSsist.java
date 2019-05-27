//package cn.bjjoy.bms.ssist;
//
//import javassist.ClassPool;
//import javassist.CtClass;
//import javassist.CtField;
//import javassist.CtMethod;
//import javassist.CtNewMethod;
//
//public class TestSsist {
//
//	public void test() {
//		Point point = new Point();
//		ClassPool pool = ClassPool.getDefault();
//		CtClass cc = pool.makeClass("foo");
//		CtMethod mthd = CtNewMethod.make("public Integer getInteger() { return null; }", cc);
//		cc.addMethod(mthd);
//		CtField f = new CtField(CtClass.intType, "i", cc);
//		point.addField(f);
//		clazz = cc.toClass(); Object instance = class.newInstance();
//	}
//
//}
//
//class Point {
//	int x, y;
//
//	void move(int dx, int dy) {
//		x += dx;
//		y += dy;
//	}
//}
