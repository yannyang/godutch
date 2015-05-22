package yann.study.utility;

import java.lang.reflect.Array;       
import java.lang.reflect.Constructor;       
import java.lang.reflect.Field;       
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Reflection {
    /**     
	    * �õ�ĳ������Ĺ�������     
	    *     
	    * @param owner, fieldName     
	    * @return �����Զ���     
	    * @throws Exception     
	    *     
	    */      
	   public Object getProperty(Object owner, String fieldName) throws Exception {       
	       Class ownerClass = owner.getClass();       
	     
	       Field field = ownerClass.getField(fieldName);       
	     
	       Object property = field.get(owner);       
	      
	        return property;       
	    }       
	     
	    /**     
	    * �õ�ĳ��ľ�̬��������     
	    *     
	    * @param className   ����     
	    * @param fieldName   ������     
	    * @return �����Զ���     
	    * @throws Exception     
	    */      
	   public Object getStaticProperty(String className, String fieldName)       
	           throws Exception {       
	       Class ownerClass = Class.forName(className);       
	      
	       Field field = ownerClass.getField(fieldName);       
	     
	       Object property = field.get(ownerClass);       
	     
	       return property;       
	    }       
	     
	     
	    /**     
	    * ִ��ĳ���󷽷�     
	    *     
	    * @param owner     
	    *            ����     
	    * @param methodName     
	    *            ������     
	    * @param args     
	    *            ����     
	    * @return ��������ֵ     
	    * @throws Exception     
	    */      
	   public Object invokeMethod(Object owner, String methodName, Object[] args)       
	           throws Exception {       
	     
	       Class ownerClass = owner.getClass();       
	     
	       Class[] argsClass = new Class[args.length];       
	     
	       for (int i = 0, j = args.length; i < j; i++) {       
	           argsClass[i] = args[i].getClass();       
	       }       
	    
	       Method method = ownerClass.getMethod(methodName, argsClass);       
	     
	       return method.invoke(owner, args);       
	   }       


	     /**     
	    * ִ��ĳ��ľ�̬����     
	    *     
	    * @param className     
	    *            ����     
	    * @param methodName     
	    *            ������     
	    * @param args     
	    *            ��������     
	    * @return ִ�з������صĽ��     
	    * @throws Exception     
	    */      
	   public Object invokeStaticMethod(String className, String methodName,       
	           Object[] args) throws Exception {       
	       Class ownerClass = Class.forName(className);       

	       Class[] argsClass = new Class[args.length];       


	       for (int i = 0, j = args.length; i < j; i++) {       
	           argsClass[i] = args[i].getClass();       
	       }       

	       Method method = ownerClass.getMethod(methodName, argsClass);       

	       return method.invoke(null, args);       
	   }       



	    /**     
	    * �½�ʵ��     
	    * @param className  ����  
	    * @param args    ���캯���Ĳ���   
	    * ����޹��������args ��дΪ null  
	    * @return �½���ʵ��     
	    * @throws Exception     
	    */      
	   public Object newInstance(String className, Object[] args,Class[] argsType) throws NoSuchMethodException, SecurityException, ClassNotFoundException, 
	   InstantiationException, IllegalAccessException, 
	   IllegalArgumentException, InvocationTargetException 
 {       
	       Class newoneClass = Class.forName(className);       

	       if(args == null){   
	           return newoneClass.newInstance();   

	      }else{   
//	           Class[] argsClass = new Class[args.length];       
//
//	           for (int i = 0, j = args.length; i < j; i++) {       
//	               argsClass[i] = args[i].getClass();       
//	           }       
//
//	           Constructor cons = newoneClass.getConstructor(argsClass);  
	    	  Constructor cons = newoneClass.getConstructor(argsType);

	           return cons.newInstance(args);      
	       }   

	   }       



	    /**     
	    * �ǲ���ĳ�����ʵ��     
	    * @param obj ʵ��     
	    * @param cls ��     
	    * @return ��� obj �Ǵ����ʵ�����򷵻� true     
	    */      
	   public boolean isInstance(Object obj, Class cls) {       
	       return cls.isInstance(obj);       
	   }       

	    /**     
	    * �õ������е�ĳ��Ԫ��     
	    * @param array ����     
	    * @param index ����     
	    * @return ����ָ��������������������ֵ     
	    */      
	   public Object getByArray(Object array, int index) {       
	       return Array.get(array,index);       
	   }  
	   
	   public Class<?> GetClassListByPackage(String pPackage) {
		   Package _Package = Package.getPackage(pPackage);
		   Class<?> _List =	_Package.getClass();
		   
		   return _List;
	   }
}
