package cn.bjjoy.bms.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @ClassName CollectionUtils
 * @Description 集合类的工具类
 * @author guoyongfeng
 * @date 2017年8月22日 下午1:54:10
 */
@SuppressWarnings({ "unchecked","rawtypes"})
public class CollectionUtils {

    /** 排序规则 升序 */
    public static int ASC = 1;
    /** 排序规则 降序 */
    public static int DESC = 2;

    /**
     * 通过Set 去除List中的重复数据
     * @param paramList 要去除重复的List
     * @return List 处理后的List
     */
   
    public static List removeDuplicateObj(List paramList) {

        // 定义去重用的Set,并将List数据放入该Set
        Set tempSet = new HashSet(paramList);

        // 将Set中的集合，放到一个临时的链表中(tempList)
        Iterator<Object> iterator = tempSet.iterator();
        // 定义返回处理 后的集合对象
        List<Object> tempList = new ArrayList<Object>();
        while (iterator.hasNext()) {
        	Object object = iterator.next();
        	if (object instanceof Integer) {
        		tempList.add(((Integer) object).intValue());
    		}else if (object instanceof String) {
    			tempList.add(object.toString());
    		}else if (object instanceof Double) {
    			tempList.add(((Double) object).doubleValue());
    		}else if (object instanceof Float) {
    			tempList.add(((Float) object).floatValue());
    		}else if (object instanceof Long) {
    			tempList.add(((Long) object).longValue());
    		}else if (object instanceof Boolean) {
    			tempList.add(((Boolean) object).booleanValue());
    		}else if (object instanceof Date) {
    			tempList.add((Date) object);
    		}else {
    			tempList.add(object);
    		}
        }
        return tempList;
    }
    
    /**
     * 通过Set 去除List中的重复数据
     * @param paramList
     *            要去除重复的List
     * @return List 处理后的List
     */

    public static List<String[]> removeEmptyStrArr(List<String[]> paramList) {
        paramList.removeAll(Collections.singleton(null));
        // 定义返回处理 后的集合对象
        List<String[]> tempList = new ArrayList<String[]>();
        for (int i = 0; i < paramList.size() ; i++) {
            if (null != paramList.get(i) && paramList.get(i).length > 1) {
                tempList.add(paramList.get(i));
            }
        }
        return tempList;
    }

    /**
     * 对指定List进行排序
     * @param objects List数据
     * @param order 排序规则
     */
    public static void sort(List<?> objects, final int order) {

        // 实现Comparator接口实现排序
        Collections.sort(objects, new Comparator<Object>() {
            // 实现Comparator的compare方法
            public int compare(Object o1, Object o2) {

                // 判断是否为降序
                if (DESC == order) {
                    // 按降序排序
                    return o2.hashCode() - o1.hashCode();
                }
                // 如果不是降序
                else {
                    // 按升序排序
                    return o1.hashCode() - o2.hashCode();
                }
            }
        });
    }

    /**
     * 根据指定属性进行排序
     * @param objects 待排序集合
     * @param propertyName 属性名称
     * @param order 排序方式
     */
    public static void sortByProperty(
        List<?> objects,
        final String propertyName,
        final String propertyType,
        final int order) {

        // 实现Comparator接口实现排序
        Collections.sort(objects, new Comparator<Object>() {
            // 实现Comparator的compare方法
            public int compare(Object r1, Object r2) {
                // 取得类型
                Class clazz = r1.getClass();
                double result = 0;
                try {
                    Method method = null;
                    // 获取 get方法
                    method = clazz.getMethod("get" + propertyName.substring(0, 1).toUpperCase()
                            + propertyName.substring(1), new Class[] {});
                    // 如果是String 类型 则将方法执行的结果转换成String类型并用compareTo方法是行排序
                    if (String.class.getSimpleName().equals(propertyType)) {
                        if (DESC == order) {
                            result = (StringUtils.parseString(method.invoke(r2))).compareTo(StringUtils
                                    .parseString(method.invoke(r1)));
                        }
                        else {
                            result = (StringUtils.parseString(method.invoke(r1))).compareTo(StringUtils
                                    .parseString(method.invoke(r2)));
                        }
                    }
                    // 如果是Integer类型 则将方法执行的结果转换成Integer类型并排序
                    else if (Integer.class.getSimpleName().equals(propertyType)) {
                        if (DESC == order) {
                            result = ((Integer) method.invoke(r2)) - ((Integer) method.invoke(r1));
                        }
                        else {
                            result = ((Integer) method.invoke(r1)) - ((Integer) method.invoke(r2));
                        }
                    }
                    // 如果是Float类型 则将方法执行的结果转换成Float类型并排序
                    else if (Float.class.getSimpleName().equals(propertyType)) {
                        if (DESC == order) {
                            result = ((Float) method.invoke(r2)) - ((Float) method.invoke(r1));
                        }
                        else {
                            result = ((Float) method.invoke(r1)) - ((Float) method.invoke(r2));
                        }
                    }
                    // 如果是Double类型 则将方法执行的结果转换成Double类型并排序
                    else if (Double.class.getSimpleName().equals(propertyType)) {
                        if (DESC == order) {
                            result = ((Double) method.invoke(r2)) - ((Double) method.invoke(r1));
                        }
                        else {
                            result = ((Double) method.invoke(r1)) - ((Double) method.invoke(r2));
                        }
                    }
                    // 如果是Long类型 则将方法执行的结果转换成Long类型并排序
                    else if (Long.class.getSimpleName().equals(propertyType)) {
                        if (DESC == order) {
                            result = ((Long) method.invoke(r2)) - ((Long) method.invoke(r1));
                        }
                        else {
                            result = ((Long) method.invoke(r1)) - ((Long) method.invoke(r2));
                        }
                    }
                    // 如果是Date类型 则将方法执行的结果转换成Date类型并排序
                    else if (Date.class.getSimpleName().equals(propertyType)) {
                        if (DESC == order) {
                            result = ((Date) method.invoke(r2)).compareTo((Date) method.invoke(r1));
                        }
                        else {
                            result = ((Date) method.invoke(r1)).compareTo((Date) method.invoke(r2));
                        }
                    }
                } catch (SecurityException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                // 确定返回值
                if (result > 0) {
                    return 1;
                }
                else if (result < 0) {
                    return -1;
                }
                return 0;
            }
        });
    }

    /**
     * 判断集合是否存在下标
     * @param coll 需要判断的集合
     * @return boolean 返回true 或者 false
     */
    public static boolean existIndex(List<?> list, int index) {
        if (isEmpty(list))
            return false;

        if (list.size() > index) {
            try {
                list.get(index);
                return true;
            } catch (Exception e) {
            }
        }

        return false;
    }
    
    /**
     * 判断集合是否为空
     * @param coll 需要判断的集合
     * @return boolean 返回true 或者 false
     */
	public static boolean isEmpty(Collection coll) {
        return ((coll == null) || (coll.isEmpty()));
    }

    /**
     * 判断集合是否不为空
     * @param coll 需要判断的集合
     * @return boolean 返回true 或者 false
     */
	public static boolean isNotEmpty(Collection coll) {
        return (!(isEmpty(coll)));
    }
}