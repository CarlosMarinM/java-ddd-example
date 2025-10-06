package tv.codely.shared.domain.bus;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public interface Listener<C> {

    default Class<C> listenTo() {
        final Type[] typeArguments = ((ParameterizedType) this.getClass().getGenericInterfaces()[0]).getActualTypeArguments();
        return (Class) typeArguments[0];
    }
}
