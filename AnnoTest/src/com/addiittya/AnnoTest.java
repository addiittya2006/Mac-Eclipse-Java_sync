//Testing for Custom made annotations and the Reflection API
//1.Instantiating a class object using the private Constructor.
//2.Declaring Self-Made Marker and non-Marker Annotations.
package com.addiittya;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

@Mainclass
public class AnnoTest {

	public static void main(String[] args) {
		
		try {
			Class<?> c1 = Class.forName("com.addiittya.Annotata");
			Class<?> c2 = Class.forName("com.addiittya.AnnoTest");
			Annotation[] arr1 = c1.getDeclaredAnnotations();
			System.out.println(" "+arr1.length);
			Annotation[] arr2 = c2.getDeclaredAnnotations();
			System.out.println(" "+arr2.length+"\n");
			Descriptor obj1 = (Descriptor) c1.getAnnotation(Descriptor.class);
			
			Constructor<?> con1 = c1.getDeclaredConstructor(Integer.class);
			
			con1.setAccessible(true);
			
			Object o1 = con1.newInstance(5);
			
			Annotata ob1k = (Annotata) o1;
			ob1k.display();
			System.out.println(obj1.developer());
			
			if(c1.isAnnotationPresent(Descriptor.class) && c2.isAnnotationPresent(Mainclass.class)){
				Annotata ab = new Annotata();
				ab.display();
				System.out.println(obj1.developer());
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Annotation not found");
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

}
