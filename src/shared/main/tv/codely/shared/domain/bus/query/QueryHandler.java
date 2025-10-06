package tv.codely.shared.domain.bus.query;

import tv.codely.shared.domain.bus.Listener;

public interface QueryHandler<Q extends Query<R>, R extends Response> extends Listener<Q> {
    R ask(Q query);
}
