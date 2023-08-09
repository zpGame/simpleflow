package com.hunk.simpleflow.flow.conditions;

import static com.hunk.simpleflow.consts.Constant.*;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;

import com.hunk.simpleflow.enums.ConditionEnum;
import com.hunk.simpleflow.flow.IContext;
import com.hunk.simpleflow.utils.Tuple2;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created on 2023/7/28.
 *
 * @author norbit
 *     <p>条件工厂
 */
public class ConditionFactory {

    private static final String and = String.format(" %s ", ConditionEnum.and.name());
    private static final String or = String.format(" %s ", ConditionEnum.or.name());

    private final String condition;

    private final List<CompoundObject> conditions = new ArrayList<>();

    public static ConditionFactory of(String condition) {
        return new ConditionFactory(
                StrUtil.isBlank(condition) ? EMPTY : condition.replaceAll("\\s+", " "));
    }

    public ConditionFactory(String condition) {
        this.condition = condition;
    }

    public ConditionFactory parse() {
        List<String> fragmentation = fragmentation();
        List<String> functional = getFunctional();
        int tagDepth = 0;
        for (int i = 0; i < fragmentation.size(); i++) {
            String operator = i == 0 ? EMPTY : functional.get(i - 1);
            String condition = fragmentation.get(i);
            Tuple2<Integer, Integer> depth = getDepth(tagDepth, condition);
            tagDepth =  depth.getFirst();
            ConditionObject conditionObject = ConditionObject.of(condition);
            conditions.add(CompoundObject.of(i, depth.getSec(), operator, conditionObject));
        }
        return this;
    }

    private Tuple2<Integer, Integer> getDepth(int tagDepth, String condition) {
        boolean contain =
                !(condition.contains("NotEmpty(")
                        || condition.contains("NotEmpty (")
                        || condition.contains("empty(")
                        || condition.contains("empty ("));
        if (condition.contains("(") && contain) {
            int depth = tagDepth + StrUtil.count(condition, "(");
            return Tuple2.of(depth, depth);
        }
        if (condition.contains(")") && contain) {
            int depth = tagDepth - StrUtil.count(condition, ")");
            return Tuple2.of(depth, tagDepth);
        }
        return Tuple2.of(tagDepth, tagDepth);
    }

    private List<String> fragmentation() {
        List<String> strings = new ArrayList<>();
        Arrays.stream(condition.split(and))
                .map(o -> o.split(or))
                .forEach(o -> CollUtil.addAll(strings, o));
        return strings;
    }

    private List<String> getFunctional() {
        List<Functional> functional = new ArrayList<>();
        for (int i = 1; i <= StrUtil.count(condition, and); i++) {
            functional.add(Functional.of(and, StrUtil.ordinalIndexOf(condition, and, i)));
        }
        for (int i = 1; i <= StrUtil.count(condition, or); i++) {
            functional.add(Functional.of(or, StrUtil.ordinalIndexOf(condition, or, i)));
        }
        return functional.stream()
                .sorted(Comparator.comparing(Functional::getOps))
                .map(Functional::getSymbolFormat)
                .collect(Collectors.toList());
    }

    public boolean execute(IContext ctx) {
        Map<Integer, List<CompoundObject>> groupByDepth = conditions.stream().collect(Collectors.groupingBy(CompoundObject::getDepth));
        
        return false;
    }

}
