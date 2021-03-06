package com.mockrunner.util.common;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class FieldUtil
{
    /**
     * Returns all non-static fields declared by the specified class and its
     * superclasses. The returned array contains the methods of all classes
     * in the inheritance hierarchy, starting with the methods of the
     * most general superclass, which is <code>java.lang.Object</code>.
     * @param theClass the class whose methods are examined
     * @return the array of field arrays
     */
    public static Field[][] getFieldsSortedByInheritanceHierarchy(Class theClass)
    {
        List hierarchyList = new ArrayList();
        Class[] hierarchyClasses = ClassUtil.getInheritanceHierarchy(theClass);
        for(int ii = 0; ii < hierarchyClasses.length; ii++)
        {
            addFieldsForClass(hierarchyList, hierarchyClasses[ii]);
        }
        return (Field[][])hierarchyList.toArray(new Field[hierarchyList.size()][]);
    }
    
    private static void addFieldsForClass(List hierarchyList, Class clazz)
    {
        List methodList = new ArrayList();
        Field[] fields = clazz.getDeclaredFields();
        for(int ii = 0; ii < fields.length; ii++)
        {
            if(!Modifier.isStatic(fields[ii].getModifiers()))
            {
                methodList.add(fields[ii]);
            }
        }
        hierarchyList.add(methodList.toArray(new Field[methodList.size()]));
    }
}
