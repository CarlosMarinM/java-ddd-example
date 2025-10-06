package tv.codely.shared.domain.bus.query;

import tv.codely.shared.domain.bus.Message;

public interface Query<R extends Response> extends Message {
}
