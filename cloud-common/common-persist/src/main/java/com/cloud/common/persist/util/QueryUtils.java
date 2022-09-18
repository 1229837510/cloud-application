package com.cloud.common.persist.util;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.common.base.util.StringUtil;
import com.cloud.common.base.web.Query;
import com.cloud.common.persist.vo.ConditionVo;
import org.springframework.beans.BeanUtils;
import org.springframework.util.ObjectUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xiaofang
 * @date 2022-9-3
 */
public class QueryUtils {
    private static Map<String, Object> exclude = new HashMap();

    public QueryUtils() {
    }

    public static QueryWrapper<?> parseWhereSql(String conditionJson) {
        QueryWrapper<?> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(conditionJson)) {
            List<ConditionVo> conditionList = JSON.parseArray(conditionJson, ConditionVo.class);
            if (CollectionUtils.isNotEmpty(conditionList)) {
                for (ConditionVo conditionVo : conditionList) {
                    switch (conditionVo.getType()) {
                        case "eq":
                            queryWrapper.eq(conditionVo.getColumn(), conditionVo.getValue());
                            break;
                        case "ne":
                            queryWrapper.ne(conditionVo.getColumn(), conditionVo.getValue());
                            break;
                        case "like":
                            queryWrapper.like(conditionVo.getColumn(), conditionVo.getValue());
                            break;
                        case "leftlike":
                            queryWrapper.likeLeft(conditionVo.getColumn(), conditionVo.getValue());
                            break;
                        case "rightlike":
                            queryWrapper.likeRight(conditionVo.getColumn(), conditionVo.getValue());
                            break;
                        case "notlike":
                            queryWrapper.notLike(conditionVo.getColumn(), conditionVo.getValue());
                            break;
                        case "gt":
                            queryWrapper.gt(conditionVo.getColumn(), conditionVo.getValue());
                            break;
                        case "lt":
                            queryWrapper.lt(conditionVo.getColumn(), conditionVo.getValue());
                            break;
                        case "ge":
                            queryWrapper.ge(conditionVo.getColumn(), conditionVo.getValue());
                            break;
                        case "le":
                            queryWrapper.le(conditionVo.getColumn(), conditionVo.getValue());
                            break;
                    }
                }
            }
        }
        return queryWrapper;
    }

    public static <T> IPage<T> getPage(Query query) {
        Page<T> page = new Page((long) query.getCurrent(), (long) query.getSize());
        if (!StringUtil.isEmpty(query.getOrder())) {
            String[] orderList = query.getOrder().split(",");

            for (int t = 0; t < orderList.length; ++t) {
                String i = orderList[t];
                i = StringUtil.humpToUnderline(i);
                if (i.endsWith("_asc")) {
                    page.addOrder(new OrderItem[]{OrderItem.asc(StringUtil.removeSuffix(i, "_asc"))});
                } else if (i.endsWith("_desc")) {
                    page.addOrder(new OrderItem[]{OrderItem.desc(StringUtil.removeSuffix(i, "_desc"))});
                } else {
                    page.addOrder(new OrderItem[]{OrderItem.asc(i)});
                }
            }
        }

        return page;
    }

    private static void buildCondition(Map<String, Object> paramMap, QueryWrapper<?> qw) {
        if (!ObjectUtils.isEmpty(paramMap)) {
            paramMap.forEach((k, v) -> {
                if (!ObjectUtils.isEmpty(k) && !k.endsWith("_ignore") && !ObjectUtils.isEmpty(v)) {
                    if (k.endsWith("_eq")) {
                        qw.eq(getColumn(k, "_eq"), v);
                    } else if (k.endsWith("_ne")) {
                        qw.ne(getColumn(k, "_ne"), v);
                    } else if (k.endsWith("_like")) {
                        qw.like(getColumn(k, "_like"), v);
                    } else if (k.endsWith("_gt")) {
                        qw.gt(getColumn(k, "_gt"), v);
                    } else if (k.endsWith("_ge")) {
                        qw.ge(getColumn(k, "_ge"), v);
                    } else if (k.endsWith("_lt")) {
                        qw.lt(getColumn(k, "_lt"), v);
                    } else if (k.endsWith("_le")) {
                        qw.le(getColumn(k, "_le"), v);
                    } else if (k.endsWith("_isNull")) {
                        qw.isNull(getColumn(k, "_isNull"));
                    } else if (k.endsWith("_notLike")) {
                        qw.notLike(getColumn(k, "_notLike"), v);
                    } else if (k.endsWith("_likeLeft")) {
                        qw.likeLeft(getColumn(k, "_likeLeft"), v);
                    } else if (k.endsWith("_likeRight")) {
                        qw.likeRight(getColumn(k, "_likeRight"), v);
                    } else if (k.endsWith("_isNotNull")) {
                        qw.isNotNull(getColumn(k, "_isNotNull"));
                    } else {
                        qw.eq(getColumn(k, "_eq"), v);
                    }

                }
            });
        }
    }

    private static String getColumn(String column, String keyword) {
        return StringUtil.humpToUnderline(StringUtil.removeSuffix(column, keyword));
    }

    public static <T> IPage<T> getPage(Map<String, Object> param) {
        Query query = new Query();
        query.setCurrent(Integer.getInteger((String) param.get("query.current"), 1));
        query.setSize(Integer.getInteger((String) param.get("query.size"), 10));
        query.setOrder((String) param.get("query.order"));
        return getPage(query);
    }

    public static <T> QueryWrapper<T> getQueryWrapper(T entity) {
        return new QueryWrapper(entity);
    }

    public static <T> QueryWrapper<T> getQueryWrapper(Map<String, Object> paramMap, Class<T> clazz) {
        return getQueryWrapper(paramMap, exclude, clazz);
    }

    public static <T> QueryWrapper<T> getQueryWrapper(Map<String, Object> paramMap, Map<String, Object> exclude, Class<T> clazz) {
        exclude.forEach((k, v) -> {
            paramMap.remove(k);
        });
        QueryWrapper<T> qw = new QueryWrapper();
        qw.setEntity(BeanUtils.instantiateClass(clazz));
        buildCondition(paramMap, qw);
        return qw;
    }

    static {
        exclude.put("query.current", "query.current");
        exclude.put("query.size", "query.size");
        exclude.put("query.order", "query.order");
    }
}
