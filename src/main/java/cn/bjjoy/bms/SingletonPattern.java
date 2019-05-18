package cn.bjjoy.bms;


public class SingletonPattern {

	public volatile static SingletonPattern instance = null ;
	
	public static SingletonPattern getInstance(){
		if(instance == null){
			synchronized (SingletonPattern.class) {
				if(instance == null){
					instance = new SingletonPattern();
				}
			}
		}
		return instance ;
	}
	
}
