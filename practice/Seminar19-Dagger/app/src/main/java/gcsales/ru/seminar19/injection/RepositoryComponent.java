package gcsales.ru.seminar19.injection;

import javax.inject.Singleton;

import dagger.Component;
import gcsales.ru.seminar19.domain.repository.Repository;

@Singleton
@Component(modules = RepositoryModule.class)
public interface RepositoryComponent {
    Repository repository();
}
