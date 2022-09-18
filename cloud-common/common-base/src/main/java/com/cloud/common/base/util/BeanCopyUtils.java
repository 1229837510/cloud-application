package com.cloud.common.base.util;

import com.cloud.common.base.funtion.BeanCopyUtilCallBack;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * @author fangcy
 * @date 2022-09-18
 */
public class BeanCopyUtils extends BeanUtils {

    private BeanCopyUtils() {
        // 防止实例化工具类
        throw new AssertionError("No " + BeanCopyUtils.class.getName() + " instances for you !");
    }

    /**
     * 集合数据的拷贝
     *
     * @param sources: 数据源类
     * @param target:  目标类::new(eg: UserVO::new)
     * @return
     */
    public static <S, T> List<T> copyListProperties(List<S> sources, Supplier<T> target) {
        return copyListProperties(sources, target, null);
    }


    /**
     * 带回调函数的集合数据的拷贝（可自定义字段拷贝规则）
     *
     * @param sources:  数据源类
     * @param target:   目标类::new(eg: UserVO::new)
     * @param callBack: 回调函数
     * @return List<UserDO> userDOList = new ArrayList();
     * userDOList.add(new UserDO(1L, "Van", 18, 1));
     * userDOList.add(new UserDO(2L, "VanVan", 20, 2));
     * List<UserVO> userVOList = BeanCopyUtil.copyListProperties(userDOList, UserVO::new, (userDO, userVO) ->{
     * // 这里可以定义特定的转换规则
     * userVO.setSex(SexEnum.getDescByCode(userDO.getSex()).getDesc());
     * });
     * log.info("userVOList:{}",userVOList);
     */
    public static <S, T> List<T> copyListProperties(List<S> sources, Supplier<T> target, BeanCopyUtilCallBack<S, T> callBack) {
        List<T> list = new ArrayList<>(sources.size());
        for (S source : sources) {
            T t = target.get();
            copyProperties(source, t);
            list.add(t);
            if (callBack != null) {
                // 回调
                callBack.callBack(source, t);
            }
        }
        return list;

    }

    /**
     * 描述: 封装BeanUtils.copyProperties, 实现类拷贝
     *
     * @param source      源对象
     * @param targetClass 目标对象Class
     * @param <T>         目标对象
     * @return
     */
    public static <T> T copyBean(Object source, Class<T> targetClass) {
        if (source == null) {
            return null;
        }
        try {
            T target = targetClass.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(source, target);
            return target;
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }

        throw new BeanCopyException(source, targetClass);
    }

    private static class BeanCopyException extends RuntimeException {

        public BeanCopyException(Object source, Class<?> targetClass) {
            super("Bean Copy Error: source=>" + source.toString() + ", targetClass=>" + targetClass.toString());
        }
    }
}

