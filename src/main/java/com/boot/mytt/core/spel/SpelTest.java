package com.boot.mytt.core.spel;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

public class SpelTest {

    public static void main(String[] args) {

        // 1
        EvaluationContext context = new StandardEvaluationContext();
        context.setVariable("teset", "result a");

        // 2
        //创建SpEL表达式的解析器
        ExpressionParser parser=new SpelExpressionParser();
        //解析表达式'Hello '+' World!'
        Expression exp=parser.parseExpression("'Hello '+' World!'");
        //取出解析结果
        String result=exp.getValue().toString();
        //输出结果
        System.out.println(result);

        // 3
        System.out.println(parser.parseExpression("#teset").getValue(context, String.class));
    }
}
