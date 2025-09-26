package tv.codely.shared.infrastructure;

import tv.codely.shared.domain.Service;
import tv.codely.shared.domain.UuidGenerator;

import java.util.UUID;

@Service
public final class JavaUuidGenerator implements UuidGenerator {
    @Override
    public UUID generate() {
        return UUID.randomUUID();
    }
}
