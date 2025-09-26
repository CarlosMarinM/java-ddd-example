package tv.codely.shared.infrastructure.config;

import io.github.cdimascio.dotenv.Dotenv;
import tv.codely.shared.domain.Service;

@Service
public final class Parameter {
    private final Dotenv dotenv;

    public Parameter(Dotenv dotenv) {
        this.dotenv = dotenv;
    }

    public String get(String key) throws ParameterNotExist {
        final String value = this.dotenv.get(key);

        if (null == value) {
            throw new ParameterNotExist(key);
        }

        return value;
    }

    public Integer getInt(String key) throws ParameterNotExist {
        final String value = this.get(key);

        return Integer.parseInt(value);
    }
}
