package cn.com.ws.base;

import java.lang.reflect.Field;  
import java.lang.reflect.Method;  
import java.lang.reflect.ParameterizedType;  
import java.lang.reflect.Type;  
import java.util.Arrays;  
import java.util.Collection;  
import java.util.List;  
import java.util.Set;  
  
import com.thoughtworks.xstream.converters.Converter;  
import com.thoughtworks.xstream.converters.MarshallingContext;  
import com.thoughtworks.xstream.converters.UnmarshallingContext;  
import com.thoughtworks.xstream.io.HierarchicalStreamReader;  
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;  

public class XMLNullConverter implements Converter {  
	  
    @SuppressWarnings("rawtypes")  
    private Class currentType;  
    private String clazzNames[] = { "User", "Order" };// 定义所要转换的对象及所包含的对象名称  
    private List<String> clazzNamesList;  
    
    public XMLNullConverter(String[] clazzNames) {
    	this.clazzNames = clazzNames;
    }
  
    @SuppressWarnings("rawtypes")  
    public boolean canConvert(Class type) {  
        currentType = type;  
        clazzNamesList = Arrays.asList(clazzNames);  
        if (clazzNamesList.contains(currentType.getSimpleName())) {  
            return true;  
        } else {  
            return false;  
        }  
    }  
  
    public void marshal(Object source, HierarchicalStreamWriter writer,  
            MarshallingContext context) {  
        try {  
            marshalSuper(source, writer, context, currentType);  
        } catch (Exception e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
  
        }  
    }  
  
    @SuppressWarnings({ "unchecked", "rawtypes" })  
    private Object getObj(Class clazz, String nodeName, Object source)  
            throws Exception {  
        Method method = clazz.getMethod("get"  
                + Character  
                        .toUpperCase(nodeName.substring(0, 1).toCharArray()[0])  
                + nodeName.substring(1));  
        Object obj = null;  
        obj = method.invoke(clazz.cast(source), new Object[0]);  
        return obj;  
    }  
  
    @SuppressWarnings({ "rawtypes" })  
    private void objConverter(Object source, HierarchicalStreamWriter writer,  
            MarshallingContext context, Class clazz, String nodeName,  
            Class fieldClazz) throws Exception {  
        Object obj = getObj(clazz, nodeName, source);  
        writer.startNode(nodeName);  
        marshalSuper(obj, writer, context, fieldClazz);  
        writer.endNode();  
    }  
  
    @SuppressWarnings({ "rawtypes" })  
    private void collectionConverter(Object source,  
            HierarchicalStreamWriter writer, MarshallingContext context,  
            Class clazz, String nodeName, Field field) throws Exception {  
        Type types[] = ((ParameterizedType) field.getGenericType())  
                .getActualTypeArguments();  
        Object obj = getObj(clazz, nodeName, source);  
        Collection collection = null;  
        if (field.getType().equals(List.class)) {  
            collection = (List) obj;  
        } else if (field.getType().equals(Set.class)) {  
            collection = (Set) obj;  
        }  
        writer.startNode(nodeName);  
        for (Object object : collection) {  
            String clazzName = ((Class) types[0]).getSimpleName();  
            writer.startNode(Character.toLowerCase(clazzName.substring(0, 1)  
                    .toCharArray()[0]) + clazzName.substring(1));  
            marshalSuper(object, writer, context, (Class) types[0]);  
            writer.endNode();  
        }  
        writer.endNode();  
    }  
  
    @SuppressWarnings({ "rawtypes" })  
    private void basicTypeConverter(Object source,  
            HierarchicalStreamWriter writer, MarshallingContext context,  
            Class clazz, String nodeName) throws Exception {  
        Object obj = getObj(clazz, nodeName, source);  
        writer.startNode(nodeName);  
        writer.setValue(obj == null ? "" : obj.toString());  
        writer.endNode();  
    }  
  
    @SuppressWarnings({ "rawtypes" })  
    private void marshalSuper(Object source, HierarchicalStreamWriter writer,  
            MarshallingContext context, Class clazz) throws Exception {  
        Field fields[] = clazz.getDeclaredFields();  
        for (Field field : fields) {  
            String nodeName = field.getName();  
            Class fieldClazz = field.getType();  
            if (clazzNamesList.contains(fieldClazz.getSimpleName())) {  
                objConverter(source, writer, context, clazz, nodeName,  
                        fieldClazz);  
            } else if (Arrays.asList(fieldClazz.getInterfaces()).contains(  
                    Collection.class)) {  
                collectionConverter(source, writer, context, clazz, nodeName,  
                        field);  
            } else {  
                basicTypeConverter(source, writer, context, clazz, nodeName);  
            }  
        }  
    }  
  
    public Object unmarshal(HierarchicalStreamReader reader,  
            UnmarshallingContext context) {  
        // TODO Auto-generated method stub  
        return null;  
    }  
  
}  