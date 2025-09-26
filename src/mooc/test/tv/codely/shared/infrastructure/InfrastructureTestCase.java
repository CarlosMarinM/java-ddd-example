package tv.codely.shared.infrastructure;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tv.codely.apps.Starter;

import javax.transaction.Transactional;

@SpringBootTest(classes = Starter.class)
@ExtendWith(MockitoExtension.class)
@Transactional
public abstract class InfrastructureTestCase {
}
