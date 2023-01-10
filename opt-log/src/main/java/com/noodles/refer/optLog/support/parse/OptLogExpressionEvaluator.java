package com.noodles.refer.optLog.support.parse;

import org.springframework.context.expression.CachedExpressionEvaluator;
import org.springframework.expression.Expression;
import org.springframework.expression.common.TemplateParserContext;

import java.lang.reflect.Method;

/**
 * @author Noodles
 * @since 2023/1/6 9:57
 */
public class OptLogExpressionEvaluator extends CachedExpressionEvaluator {

    public OptLogEvaluationContext createOptLogEvaluationContext(Method method, Object[] args) {
        return new OptLogEvaluationContext(method, args, getParameterNameDiscoverer());
    }

    public String parseTemplate(Method method, Object[] args, String content) {
        OptLogEvaluationContext evaluationContext = createOptLogEvaluationContext(method, args);

        Expression expression = getParser().parseExpression(content, new TemplateParserContext());
        return expression.getValue(evaluationContext, String.class);
    }

    public void parseTemplate(Method method, Object[] args, String[] contents) {
        OptLogEvaluationContext evaluationContext = createOptLogEvaluationContext(method, args);
        TemplateParserContext parserContext = new TemplateParserContext();
        for (int i = 0; i < contents.length; i++) {
            Expression expression = getParser().parseExpression(contents[i], parserContext);
            contents[i] = expression.getValue(evaluationContext, String.class);
        }
    }

}
