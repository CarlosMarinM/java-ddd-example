package tv.codely.shared.infrastructure.bus.query.spring;

import tv.codely.shared.domain.bus.query.Query;
import tv.codely.shared.domain.bus.query.QueryBus;
import tv.codely.shared.domain.bus.query.Response;

public class SpringQueryBus implements QueryBus {

    @Override
    public <R extends Response> R ask(Query<R> query) {
        return null;
    }
}
