package csust.sign.test;

import csust.sign.bean.Dao.Impl.CourseDaoImpl;



public class TestDatabase {
	public static void main(String[] args) {
		final CourseDaoImpl c = new CourseDaoImpl();
		final CourseDaoImpl c1 = new CourseDaoImpl();
		final CourseDaoImpl c2 = new CourseDaoImpl();
		final CourseDaoImpl c3 = new CourseDaoImpl();
		
		c.getCoursesByStudentNum("1","0");
		System.out.println(c);
		
//		
//		Thread t1 = new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
//				c.getCoursesByStudentNum("1");
//				System.out.println(c);
//			}
//		});
//		Thread t2 = new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
//				c2.getCoursesByStudentNum("1");
//				System.out.println(c2);
//			}
//		});		
//		Thread t3 = new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
//				c1.getCoursesByStudentNum("1");
//				System.out.println(c1);
//			}
//		});
//		Thread t4 = new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
//				c3.getCoursesByStudentNum("1");
//				System.out.println(c3);
//			}
//		});
//		t1.start();
//		t2.start();
//		t3.start();
//		t4.start();
//		
	}
}
