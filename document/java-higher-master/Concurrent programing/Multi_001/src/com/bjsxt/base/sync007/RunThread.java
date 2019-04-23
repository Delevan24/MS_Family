package com.bjsxt.base.sync007;

public class RunThread extends Thread{

	private volatile boolean isRunning = true;
	private void setRunning(boolean isRunning){
		this.isRunning = isRunning;
	}
	
	public void run(){
		System.out.println("进入run方法..");
		int i = 0;
		while(isRunning == true){//每次循环，判断条件变量isRunning时，都会嗅探主内存中的值，判断自己的值是不是和主内存不一致，不一致就重新读
			//..
		}
		System.out.println("线程停止");
	}
	
	public static void main(String[] args) throws InterruptedException {
		RunThread rt = new RunThread();
		rt.start();
		Thread.sleep(3000);
		rt.setRunning(false);
		//主线程中更改变量,运行到第7行this.isRunning = isRunning;发现这个变量是被volatile修饰
		//会修改主内存中这个变量的值，同时使
		System.out.println("isRunning的值已经被设置了false");
		Thread.sleep(1000);
		System.out.println(rt.isRunning);
	}
}
