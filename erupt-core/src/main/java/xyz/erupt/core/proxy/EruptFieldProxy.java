package xyz.erupt.core.proxy;

import lombok.SneakyThrows;
import org.aopalliance.intercept.MethodInvocation;
import xyz.erupt.annotation.EruptField;
import xyz.erupt.annotation.sub_field.Edit;
import xyz.erupt.annotation.sub_field.View;
import xyz.erupt.core.proxy.erupt_field.EditProxy;
import xyz.erupt.core.proxy.erupt_field.ViewProxy;

/**
 * @author YuePeng
 * date 2022/2/5 14:19
 */
public class EruptFieldProxy extends AnnotationProxy<EruptField, Void> {

    private final AnnotationProxy<Edit, EruptField> editAnnotationProxy = new EditProxy();

    @Override
    @SneakyThrows
    protected Object invocation(MethodInvocation invocation) {
        switch (invocation.getMethod().getName()) {
            case "edit":
                return AnnotationProxyPool.getOrPut(this.rawAnnotation.edit(), annotation ->
                        editAnnotationProxy.newProxy(annotation, this, this.field)
                );
            case "views":
                View[] views = this.rawAnnotation.views();
                View[] proxyViews = new View[views.length];
                for (int i = 0; i < views.length; i++) {
                    proxyViews[i] = AnnotationProxyPool.getOrPut(views[i], annotation ->
                            new ViewProxy().newProxy(annotation, this, this.field)
                    );
                }
                return proxyViews;
        }
        return this.invoke(invocation);
    }

}
