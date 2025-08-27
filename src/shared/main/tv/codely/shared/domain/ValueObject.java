package tv.codely.shared.domain;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EqualsAndHashCode
public abstract class ValueObject<T> {

    private final T value;

    public T value() {
        return this.value;
    }
}
