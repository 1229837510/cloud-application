package com.cloud.common.base.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import ma.glasnost.orika.DefaultFieldMapper;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory.Builder;
import ma.glasnost.orika.metadata.ClassMapBuilder;
import org.springframework.util.CollectionUtils;
/**
 * @author fangcy
 * @Description
 * @date 2022-11-29
 */
public class BeanCopyUtil {
    private static final MapperFactory FACTORY = (new Builder()).build();
    private static final Map<String, MapperFacade> CACHE_MAPPER = new ConcurrentHashMap();

    public BeanCopyUtil() {
    }

    public static <S, T> T copy(S sourceEntity, Class<T> targetClass) {
        return copy(sourceEntity, targetClass, (Map<String, String>) null);
    }

    public static <S, T> T copy(S sourceEntity, Class<T> targetClass, Map<String, String> refMap) {
        return sourceEntity == null ? null : getMapperFacade(sourceEntity.getClass(), targetClass, refMap).map(sourceEntity, targetClass);
    }

    public static <S, T> List<T> copyList(List<S> sourceEntityList, Class<T> targetClass) {
        return copyList(sourceEntityList, targetClass, (Map)null);
    }

    public static <S, T> List<T> copyList(List<S> sourceEntityList, Class<T> targetClass, Map<String, String> refMap) {
        if (sourceEntityList == null) {
            return null;
        } else {
            return (List)(sourceEntityList.size() == 0 ? new ArrayList(0) : getMapperFacade(sourceEntityList.get(0).getClass(), targetClass, refMap).mapAsList(sourceEntityList, targetClass));
        }
    }

    public static <V, P> void register(Class<V> source, Class<P> target, Map<String, String> refMap) {
        if (CollectionUtils.isEmpty(refMap)) {
            FACTORY.classMap(source, target).byDefault(new DefaultFieldMapper[0]).register();
        } else {
            ClassMapBuilder<V, P> classMapBuilder = FACTORY.classMap(source, target);
            refMap.forEach(classMapBuilder::field);
            classMapBuilder.byDefault(new DefaultFieldMapper[0]).register();
        }

    }

    private static <V, P> MapperFacade getMapperFacade(Class<V> source, Class<P> target, Map<String, String> refMap) {
        String mapKey = source.getCanonicalName() + ":" + target.getCanonicalName();
        if (CACHE_MAPPER.containsKey(mapKey)) {
            return (MapperFacade)CACHE_MAPPER.get(mapKey);
        } else {
            register(source, target, refMap);
            MapperFacade mapperFacade = FACTORY.getMapperFacade();
            CACHE_MAPPER.put(mapKey, mapperFacade);
            return mapperFacade;
        }
    }
}
