package tv.codely.shared.infrastructure;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import tv.codely.apps.Starter;

@ContextConfiguration(classes = Starter.class)
@SpringBootTest
@ExtendWith(MockitoExtension.class)
public abstract class InfrastructureTestCase {
}
