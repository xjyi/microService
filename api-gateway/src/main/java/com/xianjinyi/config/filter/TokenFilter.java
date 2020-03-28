package com.xianjinyi.config.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.apache.http.protocol.RequestContent;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_DECORATION_FILTER_ORDER;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/**
 * @author: xianjinyi
 * @date 2020/03/28
 */
@Component
public class TokenFilter extends ZuulFilter {
    @Override
    public String filterType() {
        // 表示前置类型
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
//        某个顺序的前一个
        return PRE_DECORATION_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        // 是否过滤
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        // 过滤器逻辑
        // 当前上下文
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();

        String token = request.getParameter("token");
        if (StringUtils.isBlank(token)){
            // 拦截的方式
            currentContext.setSendZuulResponse(false);
            currentContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
        }


        return null;
    }
}
